package net.edu.resprouted.block.entity.custom;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.interfaces.ImplementedInventory;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.recipe.custom.EvaporatingBasinRecipe;
import net.edu.resprouted.recipe.custom.EvaporatingBasinRecipeInput;
import net.edu.resprouted.util.ModTags;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EvaporatingBasinBlockEntity extends BlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private static final long MB_PER_TICK = 1;
    private static final long EV_BOOSTER = 2;

    public EvaporatingBasinBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EVAPORATING_BASIN_BE, pos, state);
    }
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        //Fluidos
        NbtCompound fluidNbt = new NbtCompound();
        SingleVariantStorage.writeNbt(basin, FluidVariant.CODEC, fluidNbt, registryLookup);
        nbt.put("FluidStorage", fluidNbt);
    }
    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        inventory.clear();
        Inventories.readNbt(nbt, inventory, registryLookup);
        //Fluidos
        if (nbt.contains("FluidStorage", NbtElement.COMPOUND_TYPE)) {
            SingleVariantStorage.readNbt(basin, FluidVariant.CODEC, FluidVariant::blank, nbt.getCompound("FluidStorage"), registryLookup
            );
        }
        super.readNbt(nbt, registryLookup);
    }
    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
    private long progress;

    public static void tick(World world, BlockPos pos, BlockState state, EvaporatingBasinBlockEntity be) {
        if (world.isClient) return;
        FluidVariant fluid = be.basin.getResource();
        long amt = be.basin.getAmount();
        if (fluid.isBlank() || amt == 0) {
            be.progress = 0;
            return;
        }
        var in  = new EvaporatingBasinRecipeInput(fluid, amt);
        var opt = world.getRecipeManager().listAllOfType(ModRecipes.EV_BASIN_TYPE).stream().filter(e -> e.value().matches(in, world)).findFirst();
        if (opt.isEmpty()) {
            be.progress = 0;
            return;
        }
        EvaporatingBasinRecipe recipe = opt.get().value();

        try (var tx = Transaction.openOuter()) {
            BlockPos below = pos.down();
            BlockState belowState = world.getBlockState(below);
            boolean boosted = belowState.isIn(ModTags.Blocks.EVAPORATING_BOOSTERS);
            long mbPerTick = boosted ? MB_PER_TICK * EV_BOOSTER : MB_PER_TICK;
            long extracted = be.basin.extract(fluid, mbPerTick, tx);
            if (extracted == mbPerTick) {
                tx.commit();
                be.progress += mbPerTick;

            }
        }
        if (be.progress >= recipe.fluidCost()) {
            be.progress -= recipe.fluidCost();
            be.spawnOrStore(recipe.output().copy());
            world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
        }
    }
    private void spawnOrStore(ItemStack stack) {
        ItemStack slot = inventory.getFirst();
        if (slot.isEmpty()) {
            inventory.set(0, stack);
        } else if (ItemStack.areItemsAndComponentsEqual(slot, stack)
                && slot.getCount() + stack.getCount() <= slot.getMaxCount()) {
            slot.increment(stack.getCount());
        } else {
            ItemScatterer.spawn(world, pos, DefaultedList.ofSize(1, stack));
        }
        markDirty();
    }
    public final SingleVariantStorage<FluidVariant> basin = new SingleVariantStorage<>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }
        @Override
        protected long getCapacity(FluidVariant fluidVariant) {
            return 6 * FluidConstants.BUCKET;
        }
        @Override
        protected void onFinalCommit() {
            markDirty();
            if (world != null && !world.isClient) {
                world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_LISTENERS);
            }
        }
        @Override
        protected boolean canInsert(FluidVariant variant) {
            return true;
        }
        @Override
        protected boolean canExtract(FluidVariant variant) {
            return true;
        }
    };
    public FluidVariant getFluid() {
        for (StorageView<FluidVariant> view : basin) {
            if (!view.isResourceBlank() && view.getAmount() > 0) {
                return view.getResource();
            }
        }
        return FluidVariant.blank();
    }
}

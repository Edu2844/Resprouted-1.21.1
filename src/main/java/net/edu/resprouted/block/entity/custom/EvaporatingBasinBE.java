package net.edu.resprouted.block.entity.custom;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.abstracts.AbstractFluidStorageBlockEntity;
import net.edu.resprouted.block.interfaces.ImplementedInventory;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.recipe.custom.EvaporatingBasinRecipe;
import net.edu.resprouted.recipe.Input.EvaporatingBasinRecipeInput;
import net.edu.resprouted.util.FluidUtils;
import net.edu.resprouted.util.ModTags;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EvaporatingBasinBE extends AbstractFluidStorageBlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private static final long MB_PER_TICK = 81;
    private static final long EV_BOOSTER = 2;
    private long progress;

    public EvaporatingBasinBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EVAPORATING_BASIN_BE, pos, state, FluidConstants.BUCKET * 6);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        inventory.clear();
        Inventories.readNbt(nbt, inventory, registryLookup);
    }

    public static void tick(World world, BlockPos pos, BlockState state, EvaporatingBasinBE be) {
        if (world.isClient) return;
        FluidVariant fluid = be.getFluidStorage().getResource();
        long amt = be.getFluidStorage().getAmount();

        if (fluid.isBlank() || amt == 0) {
            be.progress = 0;
            return;
        }

        var in  = new EvaporatingBasinRecipeInput(fluid, amt);
        var opt = world.getRecipeManager().listAllOfType(ModRecipes.EV_BASIN_TYPE)
                .stream().filter(e -> e.value().matches(in, world)).findFirst();

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
            long extracted = be.getFluidStorage().extract(fluid, mbPerTick, tx);

            if (extracted == mbPerTick) {
                tx.commit();
                be.progress += mbPerTick;
            }
        }

        long cost = FluidUtils.convertMbToDroplets(recipe.fluidCost());

        if (be.progress >= cost) {
            be.progress -= cost;
            be.spawnOrStore(recipe.output().copy());
            world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
        }
    }

    private void spawnOrStore(ItemStack stack) {
        ItemStack slot = inventory.getFirst();

        if (slot.isEmpty()) {
            inventory.set(0, stack);
        } else if (ItemStack.areItemsAndComponentsEqual(slot, stack) && slot.getCount() + stack.getCount() <= slot.getMaxCount()) {
            slot.increment(stack.getCount());
        } else {
            assert world != null;
            ItemScatterer.spawn(world, pos, DefaultedList.ofSize(1, stack));
        }

        markDirty();
    }
}

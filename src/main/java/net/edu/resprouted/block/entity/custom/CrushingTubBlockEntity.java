package net.edu.resprouted.block.entity.custom;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.interfaces.ImplementedInventory;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.recipe.custom.CrushingTubRecipe;
import net.edu.resprouted.recipe.Input.CrushingTubRecipeInput;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
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
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import java.util.Optional;

public class CrushingTubBlockEntity extends BlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public CrushingTubBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRUSHING_TUB_BE, pos, state);
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
        SingleVariantStorage.writeNbt(crushing_tub, FluidVariant.CODEC, fluidNbt, registryLookup);
        nbt.put("Fluid", fluidNbt);

    }
    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        inventory.clear();
        Inventories.readNbt(nbt, inventory, registryLookup);
        //Fluidos
        if (nbt.contains("Fluid", NbtElement.COMPOUND_TYPE)) {
            SingleVariantStorage.readNbt(
                    crushing_tub,
                    FluidVariant.CODEC,
                    FluidVariant::blank,
                    nbt.getCompound("Fluid"),
                    registryLookup
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
    public final SingleFluidStorage crushing_tub = SingleFluidStorage.withFixedCapacity(FluidConstants.BUCKET * 8, this::update);
    private void update() {
        markDirty();
        if(world != null)
            world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
    }
    public SingleFluidStorage getFluidTankProvider(Direction direction) {
        return this.crushing_tub;
    }
    public FluidVariant getFluid() {
        for (StorageView<FluidVariant> view : crushing_tub) {
            if (!view.isResourceBlank() && view.getAmount() > 0) {
                return view.getResource();
            }
        }
        return FluidVariant.blank();
    }
    public Optional<CrushingTubRecipe> findMatchingRecipe() {
        if (world == null || world.isClient) return Optional.empty();

        return world.getRecipeManager()
                .getFirstMatch(ModRecipes.CRUSHING_TUB_TYPE, new CrushingTubRecipeInput(getStack(0)), world)
                .map(RecipeEntry::value);
    }
}

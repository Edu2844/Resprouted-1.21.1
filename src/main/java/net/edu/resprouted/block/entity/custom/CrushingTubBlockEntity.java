package net.edu.resprouted.block.entity.custom;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.entity.ImplementedInventory;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.recipe.custom.CrushingTubRecipe;
import net.edu.resprouted.recipe.custom.CrushingTubRecipeInput;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
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
        SingleVariantStorage.writeNbt(fluidStorage, FluidVariant.CODEC, fluidNbt, registryLookup);
        nbt.put("FluidStorage", fluidNbt);

    }
    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        inventory.clear();
        Inventories.readNbt(nbt, inventory, registryLookup);
        //Fluidos
        if (nbt.contains("FluidStorage", NbtElement.COMPOUND_TYPE)) {
            SingleVariantStorage.readNbt(
                    fluidStorage,
                    FluidVariant.CODEC,
                    FluidVariant::blank,
                    nbt.getCompound("FluidStorage"),
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
    //Logica de almacenamiento de fluido
    public final SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<>() {

        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }
        @Override
        protected long getCapacity(FluidVariant fluidVariant) {
            return 8 * FluidConstants.BUCKET;
        }
        @Override
        protected boolean canInsert(FluidVariant variant) {
            return true;
        }
        @Override
        protected void onFinalCommit() {
            markDirty();
            if (world != null && !world.isClient) {
                world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_LISTENERS);
            }
        }
        @Override
        protected boolean canExtract(FluidVariant variant) {
            return true;
        }
    };
    public FluidVariant getFluid() {
        for (StorageView<FluidVariant> view : fluidStorage) {
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

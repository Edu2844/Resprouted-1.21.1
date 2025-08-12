package net.edu.resprouted.block.entity.custom;

import net.edu.resprouted.block.ModBlockEntities;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class LiquidBarrelBlockEntity extends BlockEntity {
    public LiquidBarrelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LIQUID_BARREL_BE, pos, state);
    }
    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        NbtCompound fluidNbt = new NbtCompound();
        SingleVariantStorage.writeNbt(liquidbarrel, FluidVariant.CODEC, fluidNbt, registryLookup);
        nbt.put("Fluid", fluidNbt);
    }
    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        if (nbt.contains("Fluid", NbtElement.COMPOUND_TYPE)) {
            SingleVariantStorage.readNbt(liquidbarrel, FluidVariant.CODEC, FluidVariant::blank, nbt.getCompound("Fluid"), registryLookup);
        }
    }
    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
    public final SingleFluidStorage liquidbarrel = SingleFluidStorage.withFixedCapacity(FluidConstants.BUCKET * 16, this::update);
    private void update() {
        markDirty();
        if(world != null)
            world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
    }
    public SingleFluidStorage getFluidTankProvider(Direction direction) {
        return this.liquidbarrel;
    }
    public FluidVariant getFluid() {
        for (StorageView<FluidVariant> view : liquidbarrel) {
            if (!view.isResourceBlank() && view.getAmount() > 0) {
                return view.getResource();
            }
        }
        return FluidVariant.blank();
    }
}

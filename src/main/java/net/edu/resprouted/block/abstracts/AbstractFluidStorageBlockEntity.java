package net.edu.resprouted.block.abstracts;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractFluidStorageBlockEntity extends BlockEntity {
    private final SingleFluidStorage fluidStorage;

    public AbstractFluidStorageBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, long capacity) {
        super(type, pos, state);
        this.fluidStorage = SingleFluidStorage.withFixedCapacity(capacity, this::markDirtyAndUpdate);
    }

    private void markDirtyAndUpdate() {
        markDirty();
        if (world != null) {
            world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
        }
    }

    public final SingleFluidStorage getFluidStorage() {
        return fluidStorage;
    }

    public SingleFluidStorage getFluidTankProvider() {
        return fluidStorage;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        NbtCompound fluidNbt = new NbtCompound();
        SingleVariantStorage.writeNbt(fluidStorage, FluidVariant.CODEC, fluidNbt, registryLookup);
        nbt.put("Fluid", fluidNbt);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        if (nbt.contains("Fluid", NbtElement.COMPOUND_TYPE)) {
            SingleVariantStorage.readNbt(fluidStorage, FluidVariant.CODEC, FluidVariant::blank,
                    nbt.getCompound("Fluid"), registryLookup);
        }
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
}

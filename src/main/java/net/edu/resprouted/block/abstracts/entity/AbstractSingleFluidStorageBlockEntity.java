package net.edu.resprouted.block.abstracts.entity;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class AbstractSingleFluidStorageBlockEntity extends BlockEntity {
    public final SingleFluidStorage fluidStorage;

    public AbstractSingleFluidStorageBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, long capacity) {
        super(type, pos, state);
        this.fluidStorage = SingleFluidStorage.withFixedCapacity(capacity, this::markDirtyAndUpdate);
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

    @Override
    protected void readComponents(BlockEntity.ComponentsAccess components) {
        super.readComponents(components);

        NbtCompound nbt = components.getOrDefault(DataComponentTypes.BLOCK_ENTITY_DATA, NbtComponent.DEFAULT).copyNbt();
        if (nbt.contains("Fluid", NbtElement.COMPOUND_TYPE)) {
            RegistryWrapper.WrapperLookup registryLookup = world != null ? world.getRegistryManager() : BuiltinRegistries.createWrapperLookup();
            SingleVariantStorage.readNbt(fluidStorage, FluidVariant.CODEC, FluidVariant::blank,
                    nbt.getCompound("Fluid"), registryLookup);
        }
    }

    @Override
    protected void addComponents(ComponentMap.Builder componentMapBuilder) {
        super.addComponents(componentMapBuilder);

        if (!fluidStorage.isResourceBlank() && fluidStorage.getAmount() > 0) {
            NbtCompound blockEntityNbt = new NbtCompound();
            NbtCompound fluidNbt = new NbtCompound();

            RegistryWrapper.WrapperLookup registryLookup = world != null ? world.getRegistryManager() : BuiltinRegistries.createWrapperLookup();
            SingleVariantStorage.writeNbt(fluidStorage, FluidVariant.CODEC, fluidNbt, registryLookup);

            blockEntityNbt.put("Fluid", fluidNbt);
            blockEntityNbt.putString("id", Objects.requireNonNull(Registries.BLOCK_ENTITY_TYPE.getId(this.getType())).toString());
            componentMapBuilder.add(DataComponentTypes.BLOCK_ENTITY_DATA, NbtComponent.of(blockEntityNbt));
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
}

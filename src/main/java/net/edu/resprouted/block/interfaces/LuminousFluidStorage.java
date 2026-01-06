package net.edu.resprouted.block.interfaces;

import net.edu.resprouted.block.abstracts.entity.AbstractSingleFluidStorageBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public interface LuminousFluidStorage {
    IntProperty LIGHT_LEVEL = IntProperty.of("light_level", 0, 15);

    /**
     * Gets the BlockEntity containing the fluid
     */
    @Nullable
    BlockEntity getBlockEntity(World world, BlockPos pos);

    /**
     * Extracts the FluidVariant from the BlockEntity
     */
    @Nullable
    default FluidVariant getFluidVariant(BlockEntity be) {
        if (be instanceof AbstractSingleFluidStorageBlockEntity fluidBE) {
            return fluidBE.getFluidStorage().getResource();
        }
        return null;
    }

    /**
     * Calculates the light level based on the fluid
     */
    default int calculateFluidLightLevel(World world, BlockPos pos) {
        BlockEntity be = getBlockEntity(world, pos);
        if (be == null) return 0;

        FluidVariant fluid = getFluidVariant(be);

        if (fluid == null || fluid.isBlank()) {
            return 0;
        }

        // Get fluid luminosity
        int fluidLightLevel = fluid.getFluid().getDefaultState().getBlockState().getLuminance();

        return Math.min(fluidLightLevel, 15);
    }

    /**
     * Updates the block and its light level
     */
    default void updateBlockWithLight(BlockEntity be, World world, BlockPos pos, BlockState state) {
        if (be instanceof BlockEntity blockEntity) {
            blockEntity.markDirty();
        }

        int lightLevel = calculateFluidLightLevel(world, pos);

        // Update blockstate if light changed
        if (state.contains(LIGHT_LEVEL) && state.get(LIGHT_LEVEL) != lightLevel) {
            world.setBlockState(pos, state.with(LIGHT_LEVEL, lightLevel), Block.NOTIFY_ALL);
        } else {
            world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
        }
    }
}
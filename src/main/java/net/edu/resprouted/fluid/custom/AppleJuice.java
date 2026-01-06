package net.edu.resprouted.fluid.custom;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;

public abstract class AppleJuice extends BaseFluid {
    @Override
    public Fluid getFlowing() {
        return ModFluids.APPLE_JUICE_FLOWING;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.APPLE_JUICE;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.APPLE_JUICE_BUCKET;
    }

    @Override
    public BlockState toBlockState(FluidState state) {
        return ModBlocks.APPLE_JUICE.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    public static class Flowing extends AppleJuice {
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }
    }

    public static class Still extends AppleJuice {
        public int getLevel(FluidState state) {
            return 8;
        }
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}


package net.edu.resprouted.fluid.custom;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.fluid.ModFluids;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.state.StateManager;

public abstract class Cider extends BaseFluid {

    @Override
    public Fluid getFlowing() {
        return ModFluids.CIDER_FLOWING;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.CIDER;
    }

    @Override
    public BlockState toBlockState(FluidState state) {
        return ModBlocks.CIDER.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    public static class Flowing extends Cider {
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }
    }

    public static class Still extends Cider {
        public int getLevel(FluidState state) {
            return 8;
        }

        public boolean isStill(FluidState state) {
            return true;
        }
    }
}

package net.edu.resprouted.fluid.custom;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.fluid.ModFluids;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.state.StateManager;

public abstract class Ale extends BaseFluid{

    @Override
    public Fluid getFlowing() {
        return ModFluids.ALE_FLOWING;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.ALE;
    }

    @Override
    public BlockState toBlockState(FluidState state) {
        return ModBlocks.ALE.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    public static class Flowing extends Ale {
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }
    }

    public static class Still extends Ale {
        public int getLevel(FluidState state) {
            return 8;
        }
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}

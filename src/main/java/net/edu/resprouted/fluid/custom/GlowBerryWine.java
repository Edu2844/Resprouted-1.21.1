package net.edu.resprouted.fluid.custom;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.fluid.ModFluids;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.state.StateManager;

public abstract class GlowBerryWine extends BaseFluid {

    @Override
    public Fluid getFlowing() {
        return ModFluids.GLOW_BERRY_WINE_FLOWING;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.GLOW_BERRY_WINE;
    }

    @Override
    public BlockState toBlockState(FluidState state) {
        return ModBlocks.GLOW_BERRY_WINE.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    public static class Flowing extends GlowBerryWine {
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }
    }

    public static class Still extends GlowBerryWine {
        public int getLevel(FluidState state) {
            return 8;
        }

        public boolean isStill(FluidState state) {
            return true;
        }
    }
}

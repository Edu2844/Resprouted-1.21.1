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

public abstract class GlowBerryJuice extends BaseFluid {

    @Override
    public Fluid getFlowing() {
        return ModFluids.GLOW_BERRY_JUICE_FLOWING;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.GLOW_BERRY_JUICE;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.GLOW_BERRY_JUICE_BUCKET;
    }

    @Override
    public BlockState toBlockState(FluidState state) {
        return ModBlocks.GLOW_BERRY_JUICE.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    public static class Flowing extends GlowBerryJuice {
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }
    }

    public static class Still extends GlowBerryJuice {
        public int getLevel(FluidState state) {
            return 8;
        }
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}


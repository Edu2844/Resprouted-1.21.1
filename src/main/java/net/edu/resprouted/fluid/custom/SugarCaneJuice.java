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

public class SugarCaneJuice extends BaseFluid{
    @Override
    public Fluid getFlowing() {
        return ModFluids.SUGAR_CANE_JUICE_FLOWING;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.SUGAR_CANE_JUICE;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.SUGAR_CANE_JUICE_BUCKET;
    }

    @Override
    public BlockState toBlockState(FluidState state) {
        return ModBlocks.SUGAR_CANE_JUICE.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    public static class Flowing extends SugarCaneJuice {
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }
    }

    public static class Still extends SugarCaneJuice {
        @Override
        public int getLevel(FluidState state) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}

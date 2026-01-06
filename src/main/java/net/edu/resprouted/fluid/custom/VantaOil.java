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
import net.minecraft.world.WorldView;

public class VantaOil extends BaseFluid{

    @Override
    public Fluid getFlowing() {
        return ModFluids.VANTA_OIL_FLOWING;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.VANTA_OIL;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.VANTA_OIL_BUCKET;
    }

    @Override
    public BlockState toBlockState(FluidState state) {
        return ModBlocks.VANTA_OIL.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    @Override
    public int getTickRate(WorldView world) {
        return 15;
    }

    public static class Flowing extends VantaOil {
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }
    }

    public static class Still extends VantaOil {
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

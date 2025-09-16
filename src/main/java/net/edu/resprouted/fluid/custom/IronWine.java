package net.edu.resprouted.fluid.custom;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.fluid.ModFluids;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;

import java.util.Optional;

public abstract class IronWine extends BaseFluid{

    @Override
    public Fluid getFlowing() {
        return ModFluids.IRON_WINE_FLOWING;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.IRON_WINE_STILL;
    }

    @Override
    public Optional<SoundEvent> getBucketFillSound() {
        return Optional.of(SoundEvents.ITEM_BUCKET_FILL);
    }

    public BlockState toBlockState(FluidState state) {
        return ModBlocks.IRON_WINE_FLUID_BLOCK.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    public boolean matchesType(Fluid fluid) {
        return fluid == ModFluids.IRON_WINE_STILL || fluid == ModFluids.IRON_WINE_FLOWING;
    }

    public static class Flowing extends IronWine {
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }
        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }
    }

    public static class Still extends IronWine {
        public int getLevel(FluidState state) {
            return 8;
        }
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}

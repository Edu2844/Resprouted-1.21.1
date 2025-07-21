package net.edu.resprouted.fluid.custom;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;

import java.util.Optional;

public abstract class GoldenAppleJuice extends BaseFluid {

    @Override
    public Fluid getFlowing() {
        return ModFluids.GOLDEN_APPLE_JUICE_FLOWING;
    }
    @Override
    public Fluid getStill() {
        return ModFluids.GOLDEN_APPLE_JUICE_STILL;
    }
    @Override
    public Item getBucketItem() {
        return ModItems.GOLDEN_APPLE_JUICE_BUCKET;
    }
    @Override
    public Optional<SoundEvent> getBucketFillSound() {
        return Optional.of(SoundEvents.ITEM_BUCKET_FILL);

    }
    public BlockState toBlockState(FluidState state) {
        return ModBlocks.GOLDEN_APPLE_JUICE_FLUID_BLOCK.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }
    public boolean matchesType(Fluid fluid) {
        return fluid == ModFluids.GOLDEN_APPLE_JUICE_STILL || fluid == ModFluids.GOLDEN_APPLE_JUICE_FLOWING;
    }
    public static class Flowing extends GoldenAppleJuice {
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }
        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }
    }
    public static class Still extends GoldenAppleJuice {
        public int getLevel(FluidState state) {
            return 8;
        }
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}


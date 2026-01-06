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
import net.minecraft.world.WorldView;
import java.util.Optional;

public abstract class HoneyFluid extends BaseFluid {
    @Override
    public Fluid getFlowing() {
        return ModFluids.HONEY_FLOWING;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.HONEY;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.HONEY_BUCKET;
    }

    @Override
    public Optional<SoundEvent> getBucketFillSound() {
        return Optional.of(SoundEvents.BLOCK_HONEY_BLOCK_PLACE);
    }

    @Override
    public BlockState toBlockState(FluidState state) {
        return ModBlocks.HONEY.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    @Override
    protected int getMaxFlowDistance(WorldView world) {
        return 2;
    }

    @Override
    public int getLevelDecreasePerBlock(WorldView world) {
        return 3;
    }

    @Override
    public int getTickRate(WorldView world) {
        return 25;
    }

    public static class Flowing extends HoneyFluid{
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }
    }

    public static class Still extends HoneyFluid {
        public int getLevel(FluidState state) {
            return 8;
        }

        public boolean isStill(FluidState state) {
            return true;
        }
    }
}


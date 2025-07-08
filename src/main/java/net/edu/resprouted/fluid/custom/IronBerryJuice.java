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


public abstract class IronBerryJuice extends HoneyFluid {

    @Override
    public Fluid getFlowing() {
        return ModFluids.IRON_BERRY_JUICE_FLOWING;
    }
    @Override
    public Fluid getStill() {
        return ModFluids.IRON_BERRY_JUICE_STILL;
    }
    @Override
    public Item getBucketItem() {
        return ModItems.IRON_BERRY_JUICE_BUCKET;
    }
    @Override
    public Optional<SoundEvent> getBucketFillSound() {
        return Optional.of(SoundEvents.ITEM_BUCKET_FILL);
    }
    public BlockState toBlockState(FluidState state) {
        return ModBlocks.IRON_BERRY_JUICE_FLUID_BLOCK.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }
    public boolean matchesType(Fluid fluid) {
        return fluid == ModFluids.IRON_BERRY_JUICE_STILL || fluid == ModFluids.IRON_BERRY_JUICE_FLOWING;
    }
    @Override
    protected int getMaxFlowDistance(WorldView world) {
        return 4;
    }
    public int getLevelDecreasePerBlock(WorldView world) {
        return 2;
    }
    @Override
    public int getTickRate(WorldView world) {
        return 5;
    }
    public static class Flowing extends IronBerryJuice {
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }
        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }
        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }
    }
    public static class Still extends IronBerryJuice {
        public int getLevel(FluidState state) {
            return 8;
        }
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}


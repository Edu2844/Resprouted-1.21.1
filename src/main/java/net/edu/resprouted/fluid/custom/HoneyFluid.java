package net.edu.resprouted.fluid.custom;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import java.util.Optional;


public abstract class HoneyFluid extends FlowableFluid {

    @Override
    public Fluid getFlowing() {
        return ModFluids.HONEY_FLOWING;
    }
    @Override
    public Fluid getStill() {
        return ModFluids.HONEY_STILL;
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
    protected boolean isInfinite(World world) {
        return false;
    }
    public BlockState toBlockState(FluidState state) {
        return ModBlocks.HONEY_FLUID_BLOCK.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropStacks(state, world, pos, blockEntity);
    }
    public boolean matchesType(Fluid fluid) {
        return fluid == ModFluids.HONEY_STILL || fluid == ModFluids.HONEY_FLOWING;
    }
    @Override
    protected int getMaxFlowDistance(WorldView world) {
        return 2;
    }
    public int getLevelDecreasePerBlock(WorldView world) {
        return 3;
    }
    @Override
    public int getTickRate(WorldView world) {
        return 25;
    }
    public boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return false;
    }
    @Override
    protected float getBlastResistance() {
        return 100.0F;
    }
    public static class Flowing extends HoneyFluid{
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
    public static class Still extends HoneyFluid {
        public int getLevel(FluidState state) {
            return 5;
        }
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}


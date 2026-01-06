package net.edu.resprouted.block.custom.agriculture;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class AppleTreeBlock extends CropBlock {
    public static final int MAX_AGE = 3;
    public static final IntProperty AGE = IntProperty.of("age", 0, 3);
    private static final VoxelShape[] SHAPE_TO_AGE = new VoxelShape[]{
            Block.createCuboidShape(6.0F, 0.0F, 6.0F, 10.0F, 6.0F, 10.0F),
            Block.createCuboidShape(3.0F, 0.0F, 3.0F, 13.0F,12.0F, 13.0F),
            Block.createCuboidShape(2.0F, 0.0F, 2.0F, 14.0F,12.0F, 14.0F),
            Block.createCuboidShape(2.0F, 0.0F, 2.0F, 14.0F,12.0F, 14.0F),
    };
    public AppleTreeBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(Blocks.FARMLAND)|| floor.isOf(Blocks.GRASS_BLOCK)|| floor.isOf(Blocks.DIRT)|| floor.isOf(ModBlocks.FERTILE_SOIL)|| floor.isOf(Blocks.PODZOL);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.APPLE_SEEDS;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE_TO_AGE[this.getAge(state)];
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(AGE) < MAX_AGE && random.nextInt(5) == 0 && world.getBaseLightLevel(pos, 0) >= 9) {
            performGrowth(world, pos, state);
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        performGrowth(world, pos, state);
    }

    private void performGrowth(ServerWorld world, BlockPos pos, BlockState state) {
        if (state.get(AGE) < MAX_AGE) {

            int i = world.random.nextBoolean() ? 1 : 2;
            int j = Math.min(state.get(AGE) + i, MAX_AGE);

            world.setBlockState(pos, state.with(AGE, j), Block.NOTIFY_LISTENERS);

            if (j == MAX_AGE) {
                transformToSapling(world, pos);
            }
        } else {
            transformToSapling(world, pos);
        }
    }

    private void transformToSapling(ServerWorld world, BlockPos pos) {
        world.setBlockState(pos, ModBlocks.APPLE_SAPLING.getDefaultState(), Block.NOTIFY_ALL | Block.FORCE_STATE | Block.SKIP_DROPS);
    }
}

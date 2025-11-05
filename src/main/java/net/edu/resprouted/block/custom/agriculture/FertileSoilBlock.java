package net.edu.resprouted.block.custom.agriculture;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class FertileSoilBlock extends FarmlandBlock {
    //public static final float GROWTH_BOOST = 0.15f;

    public FertileSoilBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FarmlandBlock.MOISTURE, 7));
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(MOISTURE) < 7) {
            world.setBlockState(pos, state.with(MOISTURE, 7), Block.NOTIFY_LISTENERS);
        }
        /*BlockPos cropPos = pos.up();
        BlockState cropState = world.getBlockState(cropPos);
        Block block = cropState.getBlock();

        if (cropState.getBlock() instanceof CropBlock crop) {

            if (random.nextFloat() < GROWTH_BOOST) {

                if (crop.canGrow(world, random, cropPos, cropState)) {
                    crop.applyGrowth(world, cropPos, cropState);
                }
            }
        }
        else if (block instanceof SugarCaneBlock && random.nextFloat() < GROWTH_BOOST) {

            if (world.getBlockState(cropPos.up()).isAir()) {
                world.setBlockState(cropPos.up(), Blocks.SUGAR_CANE.getDefaultState());
            }
        }*/
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.handleFallDamage(fallDistance, 1.0F, entity.getDamageSources().fall());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);
    }
}

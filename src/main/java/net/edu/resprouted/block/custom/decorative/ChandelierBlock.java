package net.edu.resprouted.block.custom.decorative;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.WorldView;

public class ChandelierBlock extends FallingBlock {
    public static final MapCodec<ChandelierBlock> CODEC = createCodec(ChandelierBlock::new);
    protected static final VoxelShape COLLISION_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F);
    protected static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 1.0),
            Block.createCuboidShape(0.0, 0.0, 15.0, 16.0, 4.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 1.0, 1.0, 4.0, 15.0),
            Block.createCuboidShape(15.0, 0.0, 1.0, 16.0, 4.0, 15.0),
            Block.createCuboidShape(5.0, 15.0, 5.0, 11.0, 16.0, 11.0)
    );

    public ChandelierBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends FallingBlock> getCodec() {
        return CODEC;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!isSupported(world, pos)) {
            super.scheduledTick(state, world, pos, random);
        }
    }

    private boolean isSupported(WorldView world, BlockPos pos) {
        BlockState aboveState = world.getBlockState(pos.up());
        return !aboveState.isAir();
    }

    @Override
    protected void configureFallingBlockEntity(FallingBlockEntity entity) {
        entity.setHurtEntities(2.0F, 40);
    }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        if (!fallingBlockEntity.isSilent()) {
            world.syncWorldEvent(WorldEvents.ANVIL_LANDS, pos, 0);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return COLLISION_SHAPE;
    }
}

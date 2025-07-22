package net.edu.resprouted.block.custom.decorative;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class ChandelierBlock extends FallingBlock {
    public static final MapCodec<ChandelierBlock> CODEC = createCodec(ChandelierBlock::new);;
    protected static final VoxelShape SHAPE = createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F);

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
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}

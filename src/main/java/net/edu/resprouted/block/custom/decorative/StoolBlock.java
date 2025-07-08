package net.edu.resprouted.block.custom.decorative;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class StoolBlock extends ChairBlock {
    private static final VoxelShape SHAPE = Block.createCuboidShape(2.0F, 7.0F, 2.0F, 14.0F, 9.0F, 14.0F);

    public StoolBlock(Settings settings) {
        super(settings);
    }
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}

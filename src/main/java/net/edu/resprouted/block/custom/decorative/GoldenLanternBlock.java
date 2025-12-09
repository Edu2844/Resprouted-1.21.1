package net.edu.resprouted.block.custom.decorative;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class GoldenLanternBlock extends LanternBlock {
    protected static final VoxelShape STANDING_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(7.0, 9.0, 7.0, 9.0, 10.0, 9.0), Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 9.0, 10.0)
    );

    public GoldenLanternBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return STANDING_SHAPE;
    }
}

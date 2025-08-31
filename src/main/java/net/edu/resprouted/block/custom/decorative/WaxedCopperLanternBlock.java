package net.edu.resprouted.block.custom.decorative;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class WaxedCopperLanternBlock extends LanternBlock {
    protected static final VoxelShape STANDING_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5.0, 5.0, 5.0, 11.0, 7.0, 11.0),
            Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 5.0, 12.0)
    );
    protected static final VoxelShape HANGING_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5.0, 8.0, 5.0, 11.0, 10.0, 11.0),
            Block.createCuboidShape(4.0, 3.0, 4.0, 12.0, 8.0, 12.0)
    );
    public WaxedCopperLanternBlock(Settings settings) {
        super(settings);
    }
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(HANGING) ? HANGING_SHAPE : STANDING_SHAPE;
    }
}

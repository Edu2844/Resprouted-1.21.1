package net.edu.resprouted.block.custom.decorative;

import net.edu.resprouted.block.interfaces.IAdvancedRotationPlacement;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;

public class BlockClayWallDiag extends Block implements IAdvancedRotationPlacement {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public BlockClayWallDiag(Settings settings) {
        super(settings);
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getStateForAdvancedRotationPlacement(this.getDefaultState(), FACING, ctx);
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }
    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }
}

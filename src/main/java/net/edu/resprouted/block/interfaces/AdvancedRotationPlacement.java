package net.edu.resprouted.block.interfaces;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.Direction;

public interface AdvancedRotationPlacement {

    default BlockState getStateForAdvancedRotationPlacement(BlockState state, DirectionProperty direction, ItemPlacementContext ctx) {
        Direction facing = ctx.getSide();
        double hitX = ctx.getHitPos().x - ctx.getBlockPos().getX();
        double hitZ = ctx.getHitPos().z - ctx.getBlockPos().getZ();

        if (facing.getAxis() == Direction.Axis.Y) {
            double hitXFromCenter = hitX - 0.5;
            double hitZFromCenter = hitZ - 0.5;

            if (Math.max(Math.abs(hitXFromCenter), Math.abs(hitZFromCenter)) == Math.abs(hitXFromCenter)) {
                return state.with(direction, (hitXFromCenter > 0) ? Direction.EAST : Direction.WEST);

            } else {
                return state.with(direction, (hitZFromCenter > 0) ? Direction.SOUTH : Direction.NORTH);
            }
        }

        return state.with(direction, facing.getOpposite());
    }
}

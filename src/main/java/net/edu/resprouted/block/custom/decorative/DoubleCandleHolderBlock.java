package net.edu.resprouted.block.custom.decorative;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DoubleCandleHolderBlock extends CandleHolderBlock{
    public static final MapCodec<DoubleCandleHolderBlock> CODEC = MapCodec.unit(DoubleCandleHolderBlock::new);
    protected static final VoxelShape STANDING_SHAPE = Block.createCuboidShape(4, 0, 4, 12, 15, 12);
    protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(3, 0, 10, 13, 14, 16);
    protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(3, 0, 0, 13, 14, 6);
    protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(10, 0, 3, 16, 14, 13);
    protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0, 0, 3, 6, 14, 13);

    public static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;

    public DoubleCandleHolderBlock() {
        super();
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.UP)
                .with(LIT, false)
                .with(AXIS, Direction.Axis.Z));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT, AXIS);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        World world = ctx.getWorld();

        if (ctx.getSide() == Direction.UP) {
            Direction playerFacing = ctx.getHorizontalPlayerFacing();
            BlockState upState = this.getDefaultState()
                    .with(FACING, Direction.UP)
                    .with(AXIS, playerFacing.getAxis());
            if (upState.canPlaceAt(world, pos)) {
                return upState;
            }
        }
        else if (ctx.getSide() != Direction.DOWN) {
            BlockState wallState = this.getDefaultState().with(FACING, ctx.getSide());
            if (wallState.canPlaceAt(world, pos)) {
                return wallState;
            }
        }
        for (Direction direction : ctx.getPlacementDirections()) {
            if (direction != Direction.DOWN) {
                BlockState fallbackState = this.getDefaultState().with(FACING, direction);
                if (fallbackState.canPlaceAt(world, pos)) {
                    return fallbackState;
                }
            }
        }
        return null;
    }

    @Override
    protected Iterable<Vec3d> getParticleOffsets(BlockState state) {
        Direction direction = state.get(FACING);
        Direction.Axis axis = state.get(AXIS);
        List<Vec3d> offsets = new ArrayList<>();

        double x = 0.5D;
        double y = 0.7D;
        double z = 0.5D;

        if (direction.getAxis().isHorizontal()) {
            final double candleOffset = 0.23125;
            Direction opposite = direction.getOpposite();

            x += 0.2609375D * opposite.getOffsetX();
            y += 0.25D;
            z += 0.2609375D * opposite.getOffsetZ();

            if (direction.getAxis() == Direction.Axis.X) {
                offsets.add(new Vec3d(x, y, z + candleOffset));
                offsets.add(new Vec3d(x, y, z - candleOffset));
            } else {
                offsets.add(new Vec3d(x + candleOffset, y, z));
                offsets.add(new Vec3d(x - candleOffset, y, z));
            }
        } else {
            final double candleOffset = 0.1875;
            y += 0.33D;

            if (axis == Direction.Axis.X) {
                offsets.add(new Vec3d(x, y, z + candleOffset));
                offsets.add(new Vec3d(x, y, z - candleOffset));
            } else {
                offsets.add(new Vec3d(x + candleOffset, y, z));
                offsets.add(new Vec3d(x - candleOffset, y, z));
            }
        }

        return offsets;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        return switch (direction) {
            case EAST -> EAST_SHAPE;
            case WEST -> WEST_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case NORTH -> NORTH_SHAPE;
            default -> STANDING_SHAPE;
        };
    }
}


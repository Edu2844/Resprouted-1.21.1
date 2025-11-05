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
        BlockState blockState = this.getDefaultState();
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        Direction[] directions = ctx.getPlacementDirections();

        for (Direction direction : directions) {
            if (direction != Direction.DOWN) {
                Direction facing = (direction == Direction.UP) ? Direction.UP : direction.getOpposite();
                BlockState testState = blockState.with(FACING, facing);

                if (direction == Direction.UP) {
                    Direction playerFacing = ctx.getHorizontalPlayerFacing();
                    testState = testState.with(AXIS, playerFacing.getAxis());
                } else {

                    testState = testState.with(AXIS, direction.getAxis());
                }

                if (testState.canPlaceAt(world, pos)) {
                    return testState;
                }
            }
        }
        return null;
    }

    @Override
    protected Iterable<Vec3d> getParticleOffsets(BlockState state) {
        Direction direction = state.get(FACING);
        boolean isWall = direction.getAxis().isHorizontal();

        double x = 0.5;
        double y = isWall ? 0.94 : 1.02;
        double z = 0.5;
        final double offset = isWall ? 0.22 : 0.16;

        if (isWall) {
            Direction opposite = direction.getOpposite();
            x += 0.24 * opposite.getOffsetX();
            z += 0.24 * opposite.getOffsetZ();
        }

        Direction.Axis effectiveAxis = isWall ? direction.getAxis() : state.get(AXIS);
        double offsetX = effectiveAxis == Direction.Axis.X ? 0 : offset;
        double offsetZ = effectiveAxis == Direction.Axis.X ? offset : 0;

        return List.of(
                new Vec3d(x + offsetX, y, z + offsetZ),
                new Vec3d(x - offsetX, y, z - offsetZ)
        );
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


package net.edu.resprouted.block.custom.decorative;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class OrnateLanternBlock extends Block implements Waterloggable{
    public static final MapCodec<OrnateLanternBlock> CODEC = createCodec(OrnateLanternBlock::new);
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty HANGING = Properties.HANGING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty WALL = BooleanProperty.of("wall");

    protected static final VoxelShape STANDING_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5.0, 1.0, 5.0, 11.0, 8.0, 11.0),
            Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 1.0, 10.0),
            Block.createCuboidShape(4.0, 8.0, 4.0, 12.0, 9.0, 12.0),
            Block.createCuboidShape(6.0, 9.0, 6.0, 10.0, 11.0, 10.0),
            Block.createCuboidShape(7.0, 11.0, 7.0, 9.0, 15.0, 9.0)
    );

    protected static final VoxelShape HANGING_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5.0, 1.0, 5.0, 11.0, 8.0, 11.0),
            Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 1.0, 10.0),
            Block.createCuboidShape(6.0, 15.0, 6.0, 10.0, 16.0, 10.0),
            Block.createCuboidShape(4.0, 8.0, 4.0, 12.0, 9.0, 12.0),
            Block.createCuboidShape(6.0, 9.0, 6.0, 10.0, 11.0, 10.0),
            Block.createCuboidShape(7.0, 11.0, 6.5, 9.0, 15.0, 9.5)
    );

    protected static final VoxelShape WALL_NORTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5.0, 2.0, 5.0, 11.0, 9.0, 11.0),
            Block.createCuboidShape(6.0, 1.0, 6.0, 10.0, 2.0, 10.0),
            Block.createCuboidShape(4.0, 9.0, 4.0, 12.0, 10.0, 12.0),
            Block.createCuboidShape(6.0, 10.0, 6.0, 10.0, 12.0, 10.0),
            Block.createCuboidShape(6.5, 12.0, 7.0, 9.5, 16.0, 9.0),
            Block.createCuboidShape(6.0, 0.0, 15.0, 10.0, 4.0, 16.0),
            Block.createCuboidShape(7.5, 0.0, 7.0, 8.5, 4.0, 15.0)
    );

    protected static final VoxelShape WALL_SOUTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5.0, 2.0, 5.0, 11.0, 9.0, 11.0),
            Block.createCuboidShape(6.0, 1.0, 6.0, 10.0, 2.0, 10.0),
            Block.createCuboidShape(4.0, 9.0, 4.0, 12.0, 10.0, 12.0),
            Block.createCuboidShape(6.0, 10.0, 6.0, 10.0, 12.0, 10.0),
            Block.createCuboidShape(6.5, 12.0, 7.0, 9.5, 16.0, 9.0),
            Block.createCuboidShape(6.0, 0.0, 0.0, 10, 4.0, 1.0),
            Block.createCuboidShape(7.5, 0.0, 1.0, 8.5, 4.0, 9.0)
    );

    protected static final VoxelShape WALL_WEST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5.0, 2.0, 5.0, 11.0, 9.0, 11.0),
            Block.createCuboidShape(6.0, 1.0, 6.0, 10.0, 2.0, 10.0),
            Block.createCuboidShape(4.0, 9.0, 4.0, 12.0, 10.0, 12.0),
            Block.createCuboidShape(6.0, 10.0, 6.0, 10.0, 12.0, 10.0),
            Block.createCuboidShape(6.5, 12.0, 7.0, 9.5, 16.0, 9.0),
            Block.createCuboidShape(15.0, 0.0, 6.0, 16.0, 4.0, 10.0),
            Block.createCuboidShape(7.0, 0.0, 7.5, 15.0, 4.0, 8.5)
    );

    protected static final VoxelShape WALL_EAST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5.0, 2.0, 5.0, 11.0, 9.0, 11.0),
            Block.createCuboidShape(6.0, 1.0, 6.0, 10.0, 2.0, 10.0),
            Block.createCuboidShape(4.0, 9.0, 4.0, 12.0, 10.0, 12.0),
            Block.createCuboidShape(6.0, 10.0, 6.0, 10.0, 12.0, 10.0),
            Block.createCuboidShape(6.5, 12.0, 7.0, 9.5, 16.0, 9.0),
            Block.createCuboidShape(0.0, 0.0, 6.0, 1.0, 4.0, 10.0),
            Block.createCuboidShape(1.0, 0.0, 7.5, 9.0, 4.0, 8.5)
    );

    public OrnateLanternBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(HANGING, false)
                .with(WATERLOGGED, false)
                .with(WALL, false)
                .with(FACING, Direction.NORTH)
        );
    }

    @Override
    public MapCodec<OrnateLanternBlock> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());

        for (Direction direction : ctx.getPlacementDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                BlockState blockState = this.getDefaultState()
                        .with(HANGING, direction == Direction.UP)
                        .with(WALL, false);

                if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                    return blockState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
                }
            } else {
                BlockState wallState = this.getDefaultState()
                        .with(WALL, true)
                        .with(HANGING, false)
                        .with(FACING, direction.getOpposite());

                if (wallState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                    return wallState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
                }
            }
        }

        return null;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(WALL)) {
            return switch (state.get(FACING)) {
                case NORTH -> WALL_NORTH_SHAPE;
                case SOUTH -> WALL_SOUTH_SHAPE;
                case WEST -> WALL_WEST_SHAPE;
                case EAST -> WALL_EAST_SHAPE;
                default -> STANDING_SHAPE;
            };
        }
        return state.get(HANGING) ? HANGING_SHAPE : STANDING_SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HANGING, WATERLOGGED, WALL, FACING);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = attachedDirection(state).getOpposite();
        return Block.sideCoversSmallSquare(world, pos.offset(direction), direction.getOpposite());
    }

    protected static Direction attachedDirection(BlockState state) {
        if (state.get(WALL)) {
            return state.get(FACING);
        }
        return state.get(HANGING) ? Direction.DOWN : Direction.UP;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState,
            WorldAccess world, BlockPos pos, BlockPos neighborPos) {

        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return attachedDirection(state).getOpposite() == direction && !state.canPlaceAt(world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }
}
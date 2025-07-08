package net.edu.resprouted.block.custom.agriculture;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChainBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import java.util.HashSet;
import java.util.Set;

public class RopeBlock extends ChainBlock{
    public static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;
    public static final BooleanProperty HAS_KNOT = BooleanProperty.of("has_knot");
    protected static final VoxelShape X_SHAPE_KNOT = VoxelShapes.combine(Block.createCuboidShape(0.0F, 6.5F, 6.5F, 16.0F, 9.5F, 9.5F), Block.createCuboidShape(6.5F, 0.0F, 6.5F, 9.5F, 6.5F, 9.5F), BooleanBiFunction.OR);
    protected static final VoxelShape Z_SHAPE_KNOT = VoxelShapes.combine(Block.createCuboidShape(6.5F, 6.5F, 0.0F, 9.5F, 9.5F, 16.0F), Block.createCuboidShape(6.5F, 0.0F, 6.5F, 9.5F, 6.5F, 9.5F), BooleanBiFunction.OR);

    public RopeBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(AXIS, Direction.Axis.Y)
                .with(WATERLOGGED, false)
                .with(HAS_KNOT, false));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS,WATERLOGGED, HAS_KNOT);
    }
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return hasSupport(state, world, pos);
    }
    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!hasSupport(state, world, pos)) {
            world.breakBlock(pos, true);
            for (Direction dir : Direction.values()) {
                BlockPos adjacent = pos.offset(dir);
                BlockState adjacentState = world.getBlockState(adjacent);
                if (adjacentState.getBlock() instanceof RopeBlock) {
                    world.scheduleBlockTick(adjacent, this, 1);
                }
            }
        }
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction.Axis axis = ctx.getSide().getAxis();
        BlockPos pos = ctx.getBlockPos();
        World world = ctx.getWorld();
        boolean knot = false;
        if (axis != Direction.Axis.Y) {
            BlockState below = world.getBlockState(pos.down());
            if (below.getBlock() instanceof RopeBlock && below.get(RopeBlock.AXIS) == Direction.Axis.Y) {
                knot = true;
            }
        }
        return this.getDefaultState().with(AXIS, axis).with(HAS_KNOT, knot);
    }
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!hasSupport(state, world, pos)) {
            world.scheduleBlockTick(pos, this, 1);
        }
        if (state.get(AXIS) != Direction.Axis.Y && direction == Direction.DOWN) {
            boolean knot = neighborState.getBlock() instanceof RopeBlock &&
                    neighborState.get(RopeBlock.AXIS) == Direction.Axis.Y;
            state = state.with(HAS_KNOT, knot);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);

    }
    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return context.getSide() != Direction.UP && super.canReplace(state, context);
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction.Axis axis = state.get(AXIS);
        boolean hasKnot = state.getOrEmpty(HAS_KNOT).orElse(false);
        return switch (axis) {
            case X -> hasKnot ? X_SHAPE_KNOT : X_SHAPE;
            case Z -> hasKnot ? Z_SHAPE_KNOT : Z_SHAPE;
            default -> Y_SHAPE;
        };
    }
    private boolean hasSupport(BlockState state, WorldView world, BlockPos pos) {
        Direction.Axis axis = state.get(AXIS);

        if (axis == Direction.Axis.Y) {
            BlockPos up = pos.up();
            BlockState upState = world.getBlockState(up);
            return upState.isSolidBlock(world, up) || upState.getBlock() instanceof RopeBlock;
        } else {
            return hasHorizontalSupport(world, pos, new HashSet<>(), axis);
        }
    }
    private boolean hasHorizontalSupport(WorldView world, BlockPos pos, Set<BlockPos> visited, Direction.Axis axis) {
        if (visited.contains(pos) || visited.size() > 20) return false;
        visited.add(pos);

        for (Direction dir : Direction.Type.HORIZONTAL) {
            if (dir.getAxis() != axis) continue;

            BlockPos offset = pos.offset(dir);
            BlockState neighbor = world.getBlockState(offset);

            boolean isSameRope = neighbor.getBlock() instanceof RopeBlock &&
                    neighbor.get(RopeBlock.AXIS) == axis;

            boolean isStakeWithRope = neighbor.getBlock() instanceof StakeBlock &&
                    neighbor.get(StakeBlock.HAS_ROPE);

            boolean isSolid = neighbor.isSideSolidFullSquare(world, offset, dir.getOpposite());

            if (isSolid || isStakeWithRope) return true;

            if (isSameRope && hasHorizontalSupport(world, offset, visited, axis)) return true;
        }
        return false;
    }
}

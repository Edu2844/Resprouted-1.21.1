package net.edu.resprouted.block.custom.agriculture;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChainBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class RopeBlock extends ChainBlock{
    public static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;
    public static final BooleanProperty HAS_KNOT = BooleanProperty.of("has_knot");
    protected static final VoxelShape Y_SHAPE = Block.createCuboidShape(7.0, 0.0, 7.0, 9.0, 16.0, 9.0);
    protected static final VoxelShape Z_SHAPE = Block.createCuboidShape(7.0, 7.0, 0.0, 9.0, 9.0, 16.0);
    protected static final VoxelShape X_SHAPE = Block.createCuboidShape(0.0, 7.0, 7.0, 16.0, 9.0, 9.0);
    private static final VoxelShape X_SHAPE_KNOT = VoxelShapes.union(
            Block.createCuboidShape(0, 7.0, 7.0, 16, 9.0, 9.0),
            Block.createCuboidShape(7.0, 0, 7.0, 9.0, 7.0, 9.0)

    );
    private static final VoxelShape Z_SHAPE_KNOT = VoxelShapes.union(
            Block.createCuboidShape(7.0, 7.0, 0, 9.0, 9.0, 16),
            Block.createCuboidShape(7.0, 0, 7.0, 9.0, 7.0, 9.0)

    );
    public RopeBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState()
                .with(AXIS, Direction.Axis.Y)
                .with(WATERLOGGED, false)
                .with(HAS_KNOT, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS, WATERLOGGED, HAS_KNOT);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction.Axis axis = state.get(AXIS);

        if (axis == Direction.Axis.Y) {
            return isSideSupported(world, pos, state, Direction.UP);
        } else {
            return isSideSupported(world, pos, state, Direction.WEST) ||
                    isSideSupported(world, pos, state, Direction.EAST) ||
                    isSideSupported(world, pos, state, Direction.NORTH) ||
                    isSideSupported(world, pos, state, Direction.SOUTH);
        }
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return context.getSide() != Direction.UP && super.canReplace(state, context);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!hasSupport(state, world, pos)) {
            world.breakBlock(pos, true);

            for (Direction dir : Direction.values()) {
                BlockPos adj = pos.offset(dir);

                if (world.getBlockState(adj).getBlock() instanceof RopeBlock) {
                    world.scheduleBlockTick(adj, this, 1);
                }
            }
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction.Axis axis = ctx.getSide().getAxis();

        boolean knot = axis != Direction.Axis.Y
                && ctx.getWorld().getBlockState(ctx.getBlockPos().down()).getBlock() instanceof RopeBlock rope
                && rope.getDefaultState().get(AXIS) == Direction.Axis.Y;

        return getDefaultState().with(AXIS, axis).with(HAS_KNOT, knot);
    }
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos fromPos, boolean notify) {
        Direction dir = null;
        BlockPos diff = fromPos.subtract(pos);

        if (diff.getX() != 0) {
            dir = diff.getX() < 0 ? Direction.WEST : Direction.EAST;
        } else if (diff.getY() != 0) {
            dir = diff.getY() < 0 ? Direction.DOWN : Direction.UP;
        } else if (diff.getZ() != 0) {
            dir = diff.getZ() < 0 ? Direction.NORTH : Direction.SOUTH;
        }

        if (dir != null && dir.getAxis() == state.get(AXIS)) {
            boolean sideSupported = isSideSupported(world, pos, state, dir);

            if (!sideSupported) {

                if (dir == Direction.UP) {
                    world.breakBlock(pos, true);
                    return;
                } else if (!isBlockSupported(world, pos, state)) {
                    world.breakBlock(pos, true);
                    return;
                }
            }
        }

        super.neighborUpdate(state, world, pos, sourceBlock, fromPos, notify);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction dir, BlockState neighbor, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(AXIS) != Direction.Axis.Y && dir == Direction.DOWN) {
            boolean knot = neighbor.getBlock() instanceof RopeBlock
                    && neighbor.get(RopeBlock.AXIS) == Direction.Axis.Y;

            state = state.with(HAS_KNOT, knot);

            if (knot && !isBlockSupported(world, pos, state)) {
                if (world instanceof ServerWorld serverWorld) {
                    serverWorld.scheduleBlockTick(pos, this, 1);
                }
            }
        }

        return super.getStateForNeighborUpdate(state, dir, neighbor, world, pos, neighborPos);
    }
    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.getItem() != asItem())
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        Direction side = hit.getSide(); if (side.getAxis() == state.get(AXIS) && side != Direction.UP)
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        BlockPos.Mutable cursor = pos.mutableCopy();

        for (int length = 1; length <= 64; length++) {
            cursor.move(Direction.DOWN);
            BlockState target = world.getBlockState(cursor);

            if (target.isAir()) {
                BlockState newRope = getDefaultState().with(AXIS, Direction.Axis.Y);

                if (world.setBlockState(cursor, newRope, Block.NOTIFY_ALL)) {
                    if (!player.getAbilities().creativeMode)
                        stack.decrement(1);

                    world.playSound(null, cursor, getSoundGroup(newRope).getPlaceSound(),
                            SoundCategory.BLOCKS, 1.0F, 0.8F + world.getRandom().nextFloat() * 0.4F);

                    return ItemActionResult.SUCCESS; }
                break;
            }

            if (!(target.getBlock() instanceof RopeBlock) || target.get(AXIS) != Direction.Axis.Y)
                break;
        }

        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(AXIS)) {
            case X -> state.get(HAS_KNOT) ? X_SHAPE_KNOT : X_SHAPE;
            case Z -> state.get(HAS_KNOT) ? Z_SHAPE_KNOT : Z_SHAPE;
            default -> Y_SHAPE;
        };
    }

    private boolean hasSupport(BlockState state, WorldView world, BlockPos pos) {
        return isBlockSupported(world, pos, state);
    }

    private boolean isBlockSupported(WorldView world, BlockPos pos, BlockState state) {
        return switch (state.get(AXIS)) {
            case X -> {
                boolean west = isSideSupported(world, pos, state, Direction.WEST);
                boolean east = isSideSupported(world, pos, state, Direction.EAST);
                yield west && east;
            }
            case Y -> isSideSupported(world, pos, state, Direction.UP);
            case Z -> {
                boolean north = isSideSupported(world, pos, state, Direction.NORTH);
                boolean south = isSideSupported(world, pos, state, Direction.SOUTH);
                yield north && south;
            }
        };
    }

    private boolean isSideSupported(WorldView world, BlockPos pos, BlockState state, Direction facing) {
        if (facing == Direction.DOWN) return false;

        BlockPos checkPos = pos.offset(facing);
        BlockState neighbor = world.getBlockState(checkPos);

        boolean isSame = neighbor.getBlock() instanceof RopeBlock && (neighbor.get(AXIS) == state.get(AXIS)
                        || (state.get(AXIS) == Direction.Axis.Y && facing.getAxis() == Direction.Axis.Y));

        boolean isSolid = neighbor.isSideSolidFullSquare(world, checkPos, facing.getOpposite());
        boolean isGrapeLeaves = neighbor.getBlock() instanceof GrapeLeavesBlock;
        boolean isTiedStake = neighbor.getBlock() instanceof StakeBlock && neighbor.get(StakeBlock.HAS_ROPE);

        return isSame || isSolid || isGrapeLeaves || isTiedStake;
    }

}

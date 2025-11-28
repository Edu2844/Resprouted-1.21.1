package net.edu.resprouted.block.custom.agriculture;

import net.edu.resprouted.block.custom.decorative.CustomChainBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class RopeBlock extends CustomChainBlock {
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
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (state.get(AXIS) == Direction.Axis.Y) {
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
        if (!isBlockSupported(world, pos, state)) {
            world.breakBlock(pos, true);

            for (Direction dir : Direction.values()) {
                BlockPos adj = pos.offset(dir);
                BlockState adjState = world.getBlockState(adj);

                if (adjState.getBlock().getClass() == this.getClass()) {
                    world.scheduleBlockTick(adj, this, 1);
                }
            }
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos fromPos, boolean notify) {
        Direction b = null;
        BlockPos c = fromPos.subtract(pos);

        if (c.getX() != 0) {
            b = c.getX() < 0 ? Direction.WEST : Direction.EAST;
        } else if (c.getY() != 0) {
            b = c.getY() < 0 ? Direction.DOWN : Direction.UP;
        } else if (c.getZ() != 0) {
            b = c.getZ() < 0 ? Direction.NORTH : Direction.SOUTH;
        }

        if (b != null && b.getAxis() == state.get(AXIS)) {
            boolean sideSupported = isSideSupported(world, pos, state, b);

            if (!sideSupported) {
                if (b == Direction.UP) {
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
        state = super.getStateForNeighborUpdate(state, dir, neighbor, world, pos, neighborPos);

        if (state.get(AXIS) != Direction.Axis.Y && dir == Direction.DOWN) {
            boolean knot = neighbor.getBlock().getClass() == this.getClass()
                    && neighbor.get(AXIS) == Direction.Axis.Y;

            if (knot && !isBlockSupported(world, pos, state)) {
                if (world instanceof ServerWorld serverWorld) {
                    serverWorld.scheduleBlockTick(pos, this, 1);
                }
            }
        }

        return state;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient && entity instanceof ArrowEntity arrow) {
            if (isArrowInAABB(world, pos, state, arrow)) {
                world.breakBlock(pos, true);
            }
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(HAS_KNOT)) {
            return switch (state.get(AXIS)) {
                case X -> X_SHAPE_KNOT;
                case Z -> Z_SHAPE_KNOT;
                default -> Y_SHAPE;
            };
        }
        return switch (state.get(AXIS)) {
            case X -> X_SHAPE;
            case Z -> Z_SHAPE;
            default -> Y_SHAPE;
        };
    }

    @SuppressWarnings("all")
    private boolean isBlockSupported(WorldView world, BlockPos pos, BlockState state) {
        if (state.get(AXIS) == Direction.Axis.X) {
            return isSideSupported(world, pos, state, Direction.WEST) &&
                    isSideSupported(world, pos, state, Direction.EAST);
        } else if (state.get(AXIS) == Direction.Axis.Y) {
            return isSideSupported(world, pos, state, Direction.UP);
        } else if (state.get(AXIS) == Direction.Axis.Z) {
            return isSideSupported(world, pos, state, Direction.NORTH) &&
                    isSideSupported(world, pos, state, Direction.SOUTH);
        }
        return false;
    }

    private boolean isSideSupported(WorldView world, BlockPos pos, BlockState state, Direction facing) {
        if (facing == Direction.DOWN) return false;

        BlockState neighbor = world.getBlockState(pos.offset(facing));

        boolean isSame = neighbor.getBlock().getClass() == this.getClass() && (neighbor.get(AXIS) == state.get(AXIS)
                || (state.get(AXIS) == Direction.Axis.Y && facing.getAxis() == Direction.Axis.Y));

        boolean isSolid = neighbor.isSideSolidFullSquare(world, pos.offset(facing), facing.getOpposite());
        boolean isGrapeLeaves = neighbor.getBlock() instanceof GrapeLeavesBlock;
        boolean isTiedStake = neighbor.getBlock() instanceof StakeBlock && neighbor.get(StakeBlock.HAS_ROPE);

        return isSame || isSolid || isGrapeLeaves || isTiedStake;
    }

    protected boolean isArrowInAABB(World world, BlockPos pos, BlockState state, ArrowEntity arrow) {
        double xExp = (state.get(AXIS) == Direction.Axis.X) ? 0 : 0.125;
        double yExp = (state.get(AXIS) == Direction.Axis.Y) ? 0 : 0.125;
        double zExp = (state.get(AXIS) == Direction.Axis.Z) ? 0 : 0.125;

        VoxelShape shape = getOutlineShape(state, world, pos, ShapeContext.absent());
        Box aabb = shape.getBoundingBox().expand(xExp, yExp, zExp).offset(pos);

        return arrow.getBoundingBox().intersects(aabb);
    }
}
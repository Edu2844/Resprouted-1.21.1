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
import java.util.HashSet;
import java.util.Set;

public class RopeBlock extends ChainBlock{
    public static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;
    public static final BooleanProperty HAS_KNOT = BooleanProperty.of("has_knot");
    private static final VoxelShape X_SHAPE_KNOT = VoxelShapes.union(
            Block.createCuboidShape(0, 6.5, 6.5, 16, 9.5, 9.5),
            Block.createCuboidShape(6.5, 0, 6.5, 9.5, 6.5, 9.5)
    );
    private static final VoxelShape Z_SHAPE_KNOT = VoxelShapes.union(
            Block.createCuboidShape(6.5, 6.5, 0, 9.5, 9.5, 16),
            Block.createCuboidShape(6.5, 0, 6.5, 9.5, 6.5, 9.5)
    );
    public RopeBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState()
                .with(AXIS, Direction.Axis.Y)
                .with(WATERLOGGED, false)
                .with(HAS_KNOT, false));
    }

    // ========= PROPIEDADES Y ESTADO =========
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS, WATERLOGGED, HAS_KNOT);
    }
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return hasSupport(state, world, pos);
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
                && ctx.getWorld().getBlockState(ctx.getBlockPos().down()).getBlock() instanceof RopeBlock br
                && br.getDefaultState().get(AXIS) == Direction.Axis.Y;

        return getDefaultState().with(AXIS, axis).with(HAS_KNOT, knot);
    }
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos fromPos, boolean notify) {

        Direction dir = Direction.fromVector(fromPos.getX() - pos.getX(), fromPos.getY() - pos.getY(), fromPos.getZ() - pos.getZ());

        if (dir != null && dir.getAxis() == state.get(AXIS)) {
            if (dir == Direction.UP || !isBlockSupported(world, pos, state)) {
                world.breakBlock(pos, true);
                return;
            }
        }
        super.neighborUpdate(state, world, pos, sourceBlock, fromPos, notify);
    }
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction dir, BlockState neighbor, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!hasSupport(state, world, pos)) {
            world.scheduleBlockTick(pos, this, 1);
        }
        if (state.get(AXIS) != Direction.Axis.Y && dir == Direction.DOWN) {
            boolean knot = neighbor.getBlock() instanceof RopeBlock &&
                    neighbor.get(RopeBlock.AXIS) == Direction.Axis.Y;
            state = state.with(HAS_KNOT, knot);
        }
        return super.getStateForNeighborUpdate(state, dir, neighbor, world, pos, neighborPos);
    }

    // ========= INTERACCIÓN =========
    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {


        if (stack.getItem() != asItem()) return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        Direction side = hit.getSide();
        if (side.getAxis() == state.get(AXIS) && side != Direction.UP)
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        if (state.get(AXIS) != Direction.Axis.Y) {
            Direction.Axis axis = state.get(AXIS);
            Direction dir1 = (axis == Direction.Axis.X) ? Direction.WEST : Direction.NORTH;
            Direction dir2 = dir1.getOpposite();

            BlockState left = world.getBlockState(pos.offset(dir1));
            BlockState right = world.getBlockState(pos.offset(dir2));

            boolean hasBridge = left.getBlock() instanceof RopeBlock && left.get(AXIS) == axis
                    && right.getBlock() instanceof RopeBlock && right.get(AXIS) == axis;

            if (!hasBridge) {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        }
        BlockPos.Mutable cursor = pos.mutableCopy();
        for (int length = 1; length <= 64; length++) {
            cursor.move(Direction.DOWN);
            BlockState target = world.getBlockState(cursor);

            if (target.isAir()) {
                BlockState newRope = getDefaultState().with(AXIS, Direction.Axis.Y);
                if (world.setBlockState(cursor, newRope, Block.NOTIFY_ALL)) {
                    if (!player.getAbilities().creativeMode) stack.decrement(1);
                    world.playSound(null, cursor, getSoundGroup(newRope).getPlaceSound(),
                            SoundCategory.BLOCKS, 1.0F, 0.8F + world.getRandom().nextFloat() * 0.4F);
                    return ItemActionResult.SUCCESS;
                }
                break;
            }
            if (!(target.getBlock() instanceof RopeBlock) || target.get(AXIS) != Direction.Axis.Y)
                break;
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    // ========= FORMA =========
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(AXIS)) {
            case X -> state.get(HAS_KNOT) ? X_SHAPE_KNOT : X_SHAPE;
            case Z -> state.get(HAS_KNOT) ? Z_SHAPE_KNOT : Z_SHAPE;
            default -> Y_SHAPE;
        };
    }

    // ========= AUXILIARES =========
    private boolean hasSupport(BlockState state, WorldView world, BlockPos pos) {
        return state.get(AXIS) == Direction.Axis.Y ? hasVerticalSupport(world, pos) : hasHorizontalSupport(world, pos, new HashSet<>(), state.get(AXIS));
    }
    private boolean hasVerticalSupport(WorldView world, BlockPos pos) {
        BlockState up = world.getBlockState(pos.up());
        Block upBlock = up.getBlock();
        return up.isSolidBlock(world, pos.up())
                || upBlock instanceof RopeBlock
                || upBlock instanceof StakeBlock
                || upBlock instanceof GrapeLeavesBlock;
    }
    private boolean hasHorizontalSupport(WorldView world, BlockPos pos, Set<BlockPos> visited, Direction.Axis axis) {
        if (!visited.add(pos) || visited.size() > 20)
            return false;
        for (Direction dir : Direction.Type.HORIZONTAL) {
            if (dir.getAxis() != axis) continue;
            BlockPos off = pos.offset(dir);
            BlockState neighbor = world.getBlockState(off);

            if (neighbor.isSideSolidFullSquare(world, off, dir.getOpposite()) || (neighbor.getBlock() instanceof StakeBlock && neighbor.get(StakeBlock.HAS_ROPE)))
                return true;

            if (neighbor.getBlock() instanceof RopeBlock && neighbor.get(AXIS) == axis && hasHorizontalSupport(world, off, visited, axis))
                return true;
        }
        return false;
    }
    private boolean isBlockSupported(WorldView world, BlockPos pos, BlockState state) {
        return switch (state.get(AXIS)) {
            case X -> isSideSupported(world, pos, state, Direction.WEST) && isSideSupported(world, pos, state, Direction.EAST);
            case Y -> isSideSupported(world, pos, state, Direction.UP);
            case Z -> isSideSupported(world, pos, state, Direction.NORTH) && isSideSupported(world, pos, state, Direction.SOUTH);
        };
    }
    private boolean isSideSupported(WorldView world, BlockPos pos, BlockState state, Direction facing) {
        if (facing == Direction.DOWN) return false;
        BlockPos off = pos.offset(facing);
        BlockState test = world.getBlockState(off);

        return test.isSideSolidFullSquare(world, off, facing.getOpposite())
                || (test.getBlock() instanceof StakeBlock && test.get(StakeBlock.HAS_ROPE))
                || (test.getBlock() instanceof RopeBlock &&
                ((state.get(AXIS) == Direction.Axis.Y && facing.getAxis() == Direction.Axis.Y) || test.get(AXIS) == state.get(AXIS)));
    }
}

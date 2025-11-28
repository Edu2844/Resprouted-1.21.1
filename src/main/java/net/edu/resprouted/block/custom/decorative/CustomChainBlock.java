package net.edu.resprouted.block.custom.decorative;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChainBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class CustomChainBlock extends ChainBlock {
    public static final BooleanProperty HAS_KNOT = BooleanProperty.of("has_knot");

    private static final VoxelShape X_SHAPE_KNOT = VoxelShapes.union(
            Block.createCuboidShape(0, 7.0, 7.0, 16, 9.0, 9.0),
            Block.createCuboidShape(7.0, 0, 7.0, 9.0, 7.0, 9.0)
    );
    private static final VoxelShape Z_SHAPE_KNOT = VoxelShapes.union(
            Block.createCuboidShape(7.0, 7.0, 0, 9.0, 9.0, 16),
            Block.createCuboidShape(7.0, 0, 7.0, 9.0, 7.0, 9.0)
    );

    public CustomChainBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState()
                .with(AXIS, Direction.Axis.Y)
                .with(WATERLOGGED, false)
                .with(HAS_KNOT, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(HAS_KNOT);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = super.getPlacementState(ctx);
        if (state == null) return null;

        Direction.Axis axis = state.get(AXIS);
        BlockState below = ctx.getWorld().getBlockState(ctx.getBlockPos().down());

        boolean knot = axis != Direction.Axis.Y && below.getBlock().getClass() == this.getClass() && below.get(AXIS) == Direction.Axis.Y;

        return state.with(HAS_KNOT, knot);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction dir, BlockState neighbor, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        BlockState newState = super.getStateForNeighborUpdate(state, dir, neighbor, world, pos, neighborPos);

        if (state.get(AXIS) != Direction.Axis.Y && dir == Direction.DOWN) {
            boolean knot = neighbor.getBlock().getClass() == this.getClass() && neighbor.get(AXIS) == Direction.Axis.Y;
            newState = newState.with(HAS_KNOT, knot);
        }

        return newState;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(HAS_KNOT)) {
            return switch (state.get(AXIS)) {
                case X -> X_SHAPE_KNOT;
                case Z -> Z_SHAPE_KNOT;
                default -> super.getOutlineShape(state, world, pos, context);
            };
        }
        return super.getOutlineShape(state, world, pos, context);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.getItem() != asItem())
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        Direction d = hit.getSide();
        if (d.getAxis() == state.get(AXIS) && d != Direction.UP)
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        BlockPos.Mutable e = pos.mutableCopy();

        for (int length = 1; length <= 64; length++) {
            e.move(Direction.DOWN);
            BlockState f = world.getBlockState(e);

            if (f.isAir()) {
                BlockState newOne = getDefaultState().with(AXIS, Direction.Axis.Y);

                if (world.setBlockState(e, newOne, Block.NOTIFY_ALL)) {
                    if (!player.getAbilities().creativeMode)
                        stack.decrement(1);

                    world.playSound(null, e, getSoundGroup(newOne).getPlaceSound(),
                            SoundCategory.BLOCKS, 1.0F, 0.8F + world.getRandom().nextFloat() * 0.4F);

                    return ItemActionResult.SUCCESS;
                }
                break;
            }

            if (!f.isOf(this) || f.get(AXIS) != Direction.Axis.Y)
                break;
        }

        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
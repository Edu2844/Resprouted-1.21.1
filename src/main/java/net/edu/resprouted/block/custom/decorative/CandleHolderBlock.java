package net.edu.resprouted.block.custom.decorative;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CandleHolderBlock extends AbstractCandleBlock implements Waterloggable{
    public static final MapCodec<CandleHolderBlock> CODEC = MapCodec.unit(CandleHolderBlock::new);
    public static final DirectionProperty FACING = DirectionProperty.of("facing", direction -> direction != Direction.DOWN);
    protected static final VoxelShape STANDING_SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 15.0, 10.0F);
    protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(6.0, 0.0, 11.0, 10.0, 15.0, 16.0);
    protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(6.0, 0.0, 0.0, 10.0, 15.0, 5.0);
    protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(11.0, 0.0, 6.0, 16.0, 15.0, 10.0);
    protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0.0, 0.0, 6.0, 5.0, 15.0, 10.0);
    public static final BooleanProperty LIT = AbstractCandleBlock.LIT;

    public CandleHolderBlock() {
        super(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).luminance(state -> state.get(LIT) ? 15 : 0).strength(1.0f));
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.UP).with(LIT, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        WorldView world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        Direction[] directions = ctx.getPlacementDirections();

        for (Direction direction : directions) {
            if (direction != Direction.DOWN) {
                Direction facing = (direction == Direction.UP) ? Direction.UP : direction.getOpposite();
                blockState = blockState.with(FACING, facing);
                if (blockState.canPlaceAt(world, pos)) {
                    return blockState;
                }
            }
        }
        return null;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return state.get(FACING).getOpposite() == direction && !state.canPlaceAt(world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos attachedPos = pos.offset(direction.getOpposite());
        BlockState attachedState = world.getBlockState(attachedPos);

        return attachedState.isSideSolidFullSquare(world, attachedPos, direction);
    }

    @Override
    public ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if ((stack.isOf(Items.FLINT_AND_STEEL) || stack.isOf(Items.FIRE_CHARGE)) && !state.get(LIT)) {
            if (stack.isOf(Items.FLINT_AND_STEEL)) {
                world.setBlockState(pos, state.with(LIT, true), Block.NOTIFY_ALL_AND_REDRAW);
                world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
                player.getStackInHand(hand).damage(1, player, LivingEntity.getSlotForHand(hand));
                return ItemActionResult.SUCCESS;

            } else if (stack.isOf(Items.FIRE_CHARGE)) {
                world.setBlockState(pos, state.with(LIT, true), Block.NOTIFY_ALL_AND_REDRAW);
                world.playSound(null, pos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0F, (world.random.nextFloat() - world.random.nextFloat()) * 0.2F + 1.0F);

                if (!player.isCreative()) {
                    stack.decrement(1);
                }
                return ItemActionResult.SUCCESS;
            }
        }

        if (stack.isEmpty() && player.getAbilities().allowModifyWorld && state.get(LIT)) {
            if (!world.isClient) {
                extinguish(player, state, world, pos);
            }
            return ItemActionResult.SUCCESS;
        }

        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected MapCodec<? extends AbstractCandleBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected Iterable<Vec3d> getParticleOffsets(BlockState state) {
        Direction dir = state.get(FACING);
        double x = 0.5;
        double y = 1.0;
        double z = 0.5;

        if (dir.getAxis().isHorizontal()) {
            Direction opp = dir.getOpposite();
            x += 0.27 * opp.getOffsetX();
            z += 0.27 * opp.getOffsetZ();
        }
        return List.of(new Vec3d(x, y, z));
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

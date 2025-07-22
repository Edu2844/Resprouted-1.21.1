package net.edu.resprouted.block.custom.decorative;

import net.edu.resprouted.util.ModTags;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class CandleHolderBlock extends Block {
    public static final DirectionProperty FACING = Properties.FACING;
    protected static final VoxelShape STANDING_SHAPE = Block.createCuboidShape(6.4F, 0.0F, 6.4F, 9.6F, 15.0F, 9.6F);
    protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(5.6F, 0.0F, 11.2F, 10.4F, 12.8F, 16.0F);
    protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(5.6F, 0.0F, 0.0F, 10.4F, 12.8F, 4.8F);
    protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(11.2F, 0.0F, 5.6F, 16.0F, 12.8F, 10.4F);
    protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 5.6F, 4.8F, 12.8F, 10.4F);
    public static final BooleanProperty LIT = Properties.LIT;

    public CandleHolderBlock() {
        super(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).luminance(state -> state.get(LIT) ? 15 : 0).strength(1.0f));
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.UP).with(LIT, false));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING,LIT);
    }
    @Override
    public ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.isIn(ModTags.Items.IGNITERS)) {
            if (!state.get(LIT)) {
                if (!world.isClient()) {
                    world.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
                    world.setBlockState(pos, state.with(LIT, true), Block.NOTIFY_ALL);
                    if (!player.getAbilities().creativeMode) {
                        stack.damage(1, player, LivingEntity.getSlotForHand(hand));
                    }
                }
                return ItemActionResult.SUCCESS;
            }
        } else if (stack.isEmpty()) {
            if (state.get(LIT)) {
                if (!world.isClient()) {
                    world.playSound(null, pos, SoundEvents.BLOCK_CANDLE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.setBlockState(pos, state.with(LIT, false), Block.NOTIFY_ALL);
                }
                return ItemActionResult.SUCCESS;
            }
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
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
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos blockPos = pos.offset(direction.getOpposite());
        return direction == Direction.UP ?
                Block.sideCoversSmallSquare(world, blockPos, Direction.UP) ||
                        world.getBlockState(blockPos).isSideSolid(world, blockPos, Direction.UP, SideShapeType.CENTER)
                : world.getBlockState(blockPos).isSideSolid(world, blockPos, direction, SideShapeType.CENTER);
    }
    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction clickedFace = ctx.getSide();
        BlockPos pos = ctx.getBlockPos();
        World world = ctx.getWorld();
        if (clickedFace == Direction.UP) {
            BlockState upState = this.getDefaultState().with(FACING, Direction.UP);
            if (upState.canPlaceAt(world, pos)) {
                return upState;
            }
        }
        else if (clickedFace != Direction.DOWN) {
            BlockState wallState = this.getDefaultState().with(FACING, clickedFace);
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
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return state.get(FACING).getOpposite() == direction && !state.canPlaceAt(world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!state.get(LIT)) return;
        Direction direction = state.get(FACING);
        double centerX = (double)pos.getX() + 0.5D;
        double centerY = (double)pos.getY() + 0.97D;
        double centerZ = (double)pos.getZ() + 0.5D;

        if (direction.getAxis().isHorizontal()) {
            Direction opposite = direction.getOpposite();
            world.addParticle(ParticleTypes.SMOKE,
                    centerX + 0.27D * opposite.getOffsetX(), centerY, centerZ + 0.27D * opposite.getOffsetZ(), 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.FLAME,
                    centerX + 0.27D * opposite.getOffsetX(), centerY, centerZ + 0.27D * opposite.getOffsetZ(), 0.0D, 0.0D, 0.0D);
        } else {
            world.addParticle(ParticleTypes.SMOKE, centerX, centerY, centerZ, 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.FLAME, centerX, centerY, centerZ, 0.0D, 0.0D, 0.0D);
        }
    }
    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }
    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }
}

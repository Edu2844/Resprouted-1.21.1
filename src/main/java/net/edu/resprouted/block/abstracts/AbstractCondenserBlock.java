package net.edu.resprouted.block.abstracts;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
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
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractCondenserBlock extends BlockWithEntity {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty BOTTOM = BooleanProperty.of("bottom");
    public static final BooleanProperty LIT = BooleanProperty.of("lit");
    private final VoxelShape topShape;
    private final MapCodec<? extends AbstractCondenserBlock> codec;

    protected AbstractCondenserBlock(Settings settings, VoxelShape topShape, MapCodec<? extends AbstractCondenserBlock> codec) {
        super(settings);
        this.topShape = topShape;
        this.codec = codec;
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(BOTTOM, true)
                .with(LIT, false));
    }

    // ========= ABSTRACT METHODS =========
    protected abstract BlockEntity createSpecificBlockEntity(BlockPos pos, BlockState state);
    protected abstract boolean hasRequiredRetorts(World world, BlockPos pos, BlockState state);
    protected abstract BlockEntityType<?> getBlockEntityType();

    // ========= PROPERTIES & STATE =========
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, BOTTOM, LIT);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return codec;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return state.get(BOTTOM) ? createSpecificBlockEntity(pos, state) : null;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        World world = ctx.getWorld();

        if (!world.getBlockState(pos.up()).isReplaceable()) {
            return null;
        }
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(BOTTOM, true);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (state.get(BOTTOM)) {
            BlockPos topPos = pos.up();
            world.setBlockState(topPos, state.with(BOTTOM, false), Block.NOTIFY_ALL);
        }
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof AbstractCondenserBlockEntity condenser) {
                ItemScatterer.spawn(world, pos, condenser);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockPos otherPos = state.get(BOTTOM) ? pos.up() : pos.down();
        if (world.getBlockState(otherPos).isOf(this)) {
            world.setBlockState(otherPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
        }
        return super.onBreak(world, pos, state, player);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (state.get(BOTTOM)) {
            return world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP)
                    && world.getBlockState(pos.up()).isReplaceable();
        }
        return world.getBlockState(pos.down()).isOf(this);
    }

    // ========= INTERACTION =========
    @Override
    public ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockPos targetPos = state.get(BOTTOM) ? pos : pos.down();
        BlockState targetState = world.getBlockState(targetPos);

        if (!targetState.isOf(this))
            return ItemActionResult.FAIL;

        if (!hasRequiredRetorts(world, targetPos, targetState))
            return ItemActionResult.FAIL;

        if (!(world.getBlockEntity(targetPos) instanceof AbstractCondenserBlockEntity condenserBE))
            return ItemActionResult.CONSUME;

        if (player.isSneaking() && stack.isEmpty()) {
            if (!world.isClient) {
                try (Transaction transaction = Transaction.openOuter()) {
                    long extracted = condenserBE.getFluidStorage().extract(FluidVariant.of(Fluids.WATER), Long.MAX_VALUE, transaction);

                    if (extracted > 0) {
                        transaction.commit();
                        world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
                        world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS);
                        return ItemActionResult.SUCCESS;
                    }
                }
            }
            return ItemActionResult.CONSUME;
        }

        long amount = FluidConstants.BUCKET;
        try (Transaction transaction = Transaction.openOuter()) {
            // Fill
            if (stack.isOf(Items.WATER_BUCKET)) {
                if (condenserBE.getFluidStorage().insert(FluidVariant.of(Fluids.WATER), amount, transaction) == amount) {
                    if (!player.isCreative()) {
                        stack.decrement(1);
                        player.getInventory().insertStack(new ItemStack(Items.BUCKET));
                    }
                    transaction.commit();
                    world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
                    world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    return ItemActionResult.SUCCESS;
                }
                return ItemActionResult.CONSUME;
            }
            // Empty
            if (stack.isOf(Items.BUCKET)) {
                if (condenserBE.getFluidStorage().extract(FluidVariant.of(Fluids.WATER), amount, transaction) == amount) {
                    if (!player.isCreative()) {
                        stack.decrement(1);
                        player.getInventory().insertStack(new ItemStack(Items.WATER_BUCKET));
                    }
                    transaction.commit();
                    world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
                    world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    return ItemActionResult.SUCCESS;
                }
                return ItemActionResult.CONSUME;
            }
        }

        if (!world.isClient) {
            player.openHandledScreen(condenserBE);
            return ItemActionResult.SUCCESS;
        }

        return ItemActionResult.CONSUME;
    }

    // ========= FORM =========
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(BOTTOM) ? VoxelShapes.fullCube() : topShape;
    }

    // ========= TICKER =========
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world.isClient()) {
            return validateTicker(type, getBlockEntityType(),
                    (world1, pos, state1, be) -> ((AbstractCondenserBlockEntity) be).clientTick(world1, pos, state1));
        }
        return validateTicker(type, getBlockEntityType(),
                (world1, pos, state1, be) -> ((AbstractCondenserBlockEntity) be).serverTick(world1, pos, state1));
    }
}

package net.edu.resprouted.block.custom.alchemy;

import com.mojang.serialization.MapCodec;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.entity.custom.AdvancedCondenserBlockEntity;
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
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class AdvancedCondenserBlock extends BlockWithEntity {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty BOTTOM = BooleanProperty.of("bottom");
    public static final BooleanProperty LIT = BooleanProperty.of("lit");
    private static final VoxelShape TOP_SHAPE = VoxelShapes.combineAndSimplify(
            Block.createCuboidShape(0, 0, 0, 16, 12, 16),
            Block.createCuboidShape(4, 12, 4, 12, 16, 12),
            BooleanBiFunction.OR
    );
    public static final MapCodec<AdvancedCondenserBlock> CODEC = AdvancedCondenserBlock.createCodec(AdvancedCondenserBlock::new);

    public AdvancedCondenserBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(BOTTOM, true)
                .with(LIT, false)
        );
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, BOTTOM, LIT);
    }
    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }
    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return state.get(BOTTOM) ? new AdvancedCondenserBlockEntity(pos, state) : null;
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
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof AdvancedCondenserBlockEntity) {
                ItemScatterer.spawn(world, pos, ((AdvancedCondenserBlockEntity) blockEntity));
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
            return world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP) && world.getBlockState(pos.up()).isReplaceable();
        }
        return world.getBlockState(pos.down()).isOf(this);
    }
    @Override
    public ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockPos targetPos = state.get(BOTTOM) ? pos : pos.down();
        BlockState targetState = world.getBlockState(targetPos);

        if (!targetState.isOf(this))
            return ItemActionResult.FAIL;

        if (!hasRetorts(world, targetPos, targetState))
            return ItemActionResult.FAIL;

        if (!(world.getBlockEntity(targetPos) instanceof AdvancedCondenserBlockEntity advcondenserBE))
            return ItemActionResult.CONSUME;

        long amount = FluidConstants.BUCKET;
        try (Transaction transaction = Transaction.openOuter()) {
            //Fill
            if (stack.isOf(Items.WATER_BUCKET)) {
                if (advcondenserBE.advanced_condenser.insert(FluidVariant.of(Fluids.WATER), amount, transaction) == amount) {
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
            //Empty
            if (stack.isOf(Items.BUCKET)) {
                if (advcondenserBE.advanced_condenser.extract(FluidVariant.of(Fluids.WATER), amount, transaction) == amount) {
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
        if (!world.isClient && advcondenserBE.hasFluid()) {
            player.openHandledScreen(advcondenserBE);
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.CONSUME;
    }
    public boolean hasRetorts(World world, BlockPos pos, BlockState state) {
        if (state.getBlock() != this) {
            return false;
        }
        Direction facing = state.get(FACING);
        BlockPos eastPos = pos.east();
        BlockPos westPos = pos.west();
        BlockPos northPos = pos.north();
        BlockPos southPos = pos.south();

        switch (facing) {
            case NORTH:
                if (!world.getBlockState(eastPos).isOf(ModBlocks.ADVANCED_RETORT))
                return false;
            if (!world.getBlockState(westPos).isOf(ModBlocks.ADVANCED_RETORT))
                return false;
            if (!world.getBlockState(southPos).isOf(ModBlocks.ADVANCED_RETORT))
                return false;
            if (world.getBlockState(eastPos).get(RetortBlock.FACING) != Direction.EAST)
                return false;
            if (world.getBlockState(westPos).get(RetortBlock.FACING) != Direction.WEST)
                return false;
            if (world.getBlockState(southPos).get(RetortBlock.FACING) != Direction.SOUTH)
                return false;
            break;
            case SOUTH:
                if (!world.getBlockState(eastPos).isOf(ModBlocks.ADVANCED_RETORT))
                    return false;
                if (!world.getBlockState(westPos).isOf(ModBlocks.ADVANCED_RETORT))
                    return false;
                if (!world.getBlockState(northPos).isOf(ModBlocks.ADVANCED_RETORT))
                    return false;
                if (world.getBlockState(eastPos).get(RetortBlock.FACING) != Direction.EAST)
                    return false;
                if (world.getBlockState(westPos).get(RetortBlock.FACING) != Direction.WEST)
                    return false;
                if (world.getBlockState(northPos).get(RetortBlock.FACING) != Direction.NORTH)
                    return false;
                break;
            case WEST:
                if (!world.getBlockState(northPos).isOf(ModBlocks.ADVANCED_RETORT))
                    return false;
                if (!world.getBlockState(southPos).isOf(ModBlocks.ADVANCED_RETORT))
                    return false;
                if (!world.getBlockState(eastPos).isOf(ModBlocks.ADVANCED_RETORT))
                    return false;
                if (world.getBlockState(northPos).get(RetortBlock.FACING) != Direction.NORTH)
                    return false;
                if (world.getBlockState(southPos).get(RetortBlock.FACING) != Direction.SOUTH)
                    return false;
                if (world.getBlockState(eastPos).get(RetortBlock.FACING) != Direction.EAST)
                    return false;
                break;
            case EAST:
                if (!world.getBlockState(northPos).isOf(ModBlocks.ADVANCED_RETORT))
                    return false;
                if (!world.getBlockState(southPos).isOf(ModBlocks.ADVANCED_RETORT))
                    return false;
                if (!world.getBlockState(westPos).isOf(ModBlocks.ADVANCED_RETORT))
                    return false;
                if (world.getBlockState(northPos).get(RetortBlock.FACING) != Direction.NORTH)
                    return false;
                if (world.getBlockState(southPos).get(RetortBlock.FACING) != Direction.SOUTH)
                    return false;
                if (world.getBlockState(westPos).get(RetortBlock.FACING) != Direction.WEST)
                    return false;
                break;
            default:
                return false;
        }
        return true;
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(BOTTOM) ? VoxelShapes.fullCube() : TOP_SHAPE;
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world.isClient()) {
            return validateTicker(type, ModBlockEntities.ADVANCED_CONDENSER_BE,
                    (world1, pos, state1, be) -> be.clientTick(world1, pos, state1));
        }
        return validateTicker(type, ModBlockEntities.ADVANCED_CONDENSER_BE,
                (world1, pos, state1, be) -> be.serverTick(world1, pos, state1));
    }
}

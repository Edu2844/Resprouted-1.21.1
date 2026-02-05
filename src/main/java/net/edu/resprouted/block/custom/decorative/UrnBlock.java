package net.edu.resprouted.block.custom.decorative;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.entity.custom.decorative.UrnBlockEntity;
import net.edu.resprouted.util.block.BlockUtils;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class UrnBlock extends BlockWithEntity implements Waterloggable{
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final MapCodec<UrnBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(DyeColor.CODEC.optionalFieldOf("color").forGetter(block -> Optional.ofNullable(block.color)), createSettingsCodec())
                    .apply(instance, (color, settings) -> new UrnBlock(settings, color.orElse(null)))
    );
    public static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2.0, 2.0, 2.0, 14.0, 11.0, 14.0),
            Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 2.0, 12.0),
            Block.createCuboidShape(5.0, 11.0, 5.0, 11.0, 14.0, 11.0),
            Block.createCuboidShape(4.0, 14.0, 4.0, 12.0, 16.0, 12.0)
    );

    public UrnBlock(Settings settings, @Nullable DyeColor color) {
        super(settings);
        this.color = color;
        setDefaultState(getDefaultState().with(WATERLOGGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (Resprouted.COMMON_CONFIG.getGeneral().isEnableDyeByRightClick()) {
            return BlockUtils.tryToDye(world, pos, state, player, stack, UrnBlock::get);
        }

        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof UrnBlockEntity) {
                player.openHandledScreen((UrnBlockEntity)blockEntity);
            }
            return ActionResult.CONSUME;
        }
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock()) && !(newState.getBlock() instanceof UrnBlock)) {
            ItemScatterer.onStateReplaced(state, newState, world, pos);
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof UrnBlockEntity) {
            ((UrnBlockEntity)blockEntity).tick();
        }
    }

    @Override
    protected boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Nullable
    private final DyeColor color;

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new UrnBlockEntity(this.color, pos, state);
    }

    @Nullable
    public static DyeColor getColor(Block block) {
        return block instanceof UrnBlock ? ((UrnBlock)block).getColor() : null;
    }

    public static Block get(@Nullable DyeColor dyeColor) {
        if (dyeColor == null) {
            return ModBlocks.URN;
        } else {
            return switch (dyeColor) {
                case WHITE -> ModBlocks.WHITE_URN;
                case ORANGE -> ModBlocks.ORANGE_URN;
                case MAGENTA -> ModBlocks.MAGENTA_URN;
                case LIGHT_BLUE -> ModBlocks.LIGHT_BLUE_URN;
                case YELLOW -> ModBlocks.YELLOW_URN;
                case LIME -> ModBlocks.LIME_URN;
                case PINK -> ModBlocks.PINK_URN;
                case GRAY -> ModBlocks.GRAY_URN;
                case LIGHT_GRAY -> ModBlocks.LIGHT_GRAY_URN;
                case CYAN -> ModBlocks.CYAN_URN;
                case BLUE -> ModBlocks.BLUE_URN;
                case BROWN -> ModBlocks.BROWN_URN;
                case GREEN -> ModBlocks.GREEN_URN;
                case RED -> ModBlocks.RED_URN;
                case BLACK -> ModBlocks.BLACK_URN;
                case PURPLE -> ModBlocks.PURPLE_URN;
            };
        }
    }

    @Nullable
    public DyeColor getColor() {
        return this.color;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }
}

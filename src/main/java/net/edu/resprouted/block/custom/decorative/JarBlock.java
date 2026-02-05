package net.edu.resprouted.block.custom.decorative;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.util.block.BlockUtils;
import net.edu.resprouted.block.entity.custom.decorative.JarBlockEntity;
import net.edu.resprouted.block.interfaces.LuminousFluidStorage;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
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
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class JarBlock extends BlockWithEntity implements LuminousFluidStorage, Waterloggable {
    public static final BooleanProperty OPEN = Properties.OPEN;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final MapCodec<JarBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(DyeColor.CODEC.optionalFieldOf("color").forGetter(block -> Optional.ofNullable(block.color)), createSettingsCodec())
                    .apply(instance, (color, settings) -> new JarBlock(settings, color.orElse(null)))
    );
    public static final VoxelShape CLOSED_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(4.0, 1.0, 4.0, 12.0, 10.0, 12.0),
            Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 1.0, 11.0),
            Block.createCuboidShape(5.0, 10.0, 5.0, 11.0, 12.0, 11.0),
            Block.createCuboidShape(4.0, 12.0, 4.0, 12.0, 14.0, 12.0)
    );
    public static final VoxelShape OPEN_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(4.0, 1.0, 4.0, 12.0, 10.0, 12.0),
            Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 1.0, 11.0),
            Block.createCuboidShape(5.0, 10.0, 5.0, 11.0, 12.0, 11.0)
    );

    @Nullable
    private final DyeColor color;

    public JarBlock(Settings settings, @Nullable DyeColor color) {
        super(settings);
        this.color = color;
        setDefaultState(getStateManager().getDefaultState().with(OPEN, false).with(WATERLOGGED, false).with(LIGHT_LEVEL, 0));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(OPEN, WATERLOGGED, LIGHT_LEVEL);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new JarBlockEntity(this.color, pos, state);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        BlockUtils.appendFluidTooltip(stack, tooltip);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof JarBlockEntity jar) {
            if (!world.isClient && player.isCreative() && !jar.getFluidStorage().isResourceBlank()) {
                Block coloredBlock = JarBlock.get(jar.getColor());
                ItemStack stack = new ItemStack(coloredBlock);
                stack.applyComponentsFrom(jar.createComponentMap());

                ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
            }
        }
        return super.onBreak(world, pos, state, player);
    }

    @Override
    protected List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        BlockEntity blockEntity = builder.getOptional(LootContextParameters.BLOCK_ENTITY);
        if (blockEntity instanceof JarBlockEntity jar) {
            Block coloredBlock = JarBlock.get(jar.getColor());
            ItemStack stack = new ItemStack(coloredBlock);
            stack.applyComponentsFrom(jar.createComponentMap());
            return List.of(stack);
        }
        return List.of(new ItemStack(this));
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockPos dripPos = PointedDripstoneBlock.getDripPos(world, pos);
        if (dripPos != null) {
            Fluid fluid = PointedDripstoneBlock.getDripFluid(world, dripPos);
            if (fluid != Fluids.EMPTY && BlockUtils.canBeFilledByDripstone(fluid)) {
                if (world.getBlockEntity(pos) instanceof JarBlockEntity jar) {
                    BlockUtils.fillFromDripstone(state, world, pos, fluid, jar.getFluidStorage(), this);
                }
            }
        }
    }

    @Override
    public void precipitationTick(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation) {
        if (world.getBlockEntity(pos) instanceof JarBlockEntity jar) {
            BlockUtils.handlePrecipitation(state, world, pos, precipitation, jar.getFluidStorage(), this);
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (Resprouted.COMMON_CONFIG.getGeneral().isEnableDyeByRightClick()) {
            ItemActionResult result = BlockUtils.tryToDye(world, pos, state, player, stack, JarBlock::get);
            if (result != ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION) {
                return result;
            }
        }

        if (player.isSneaking()) {
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof JarBlockEntity)) {
            return ItemActionResult.FAIL;
        }

        if (!state.get(OPEN)) {
            if (!world.isClient) {
                player.sendMessage(Text.translatable("message.resprouted.jar_closed").formatted(Formatting.RESET), true);
            }
            return ItemActionResult.CONSUME;
        }

        return BlockUtils.handleFluidInteraction(world, pos, player, hand, hit, this, state);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (player.isSneaking()) {
            if (!world.isClient) {
                boolean isOpen = state.get(OPEN);
                world.setBlockState(pos, state.with(OPEN, !isOpen));
                world.playSound(null, pos, SoundEvents.BLOCK_DECORATED_POT_HIT, SoundCategory.BLOCKS, 1.0F, 2.0F);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Nullable
    public static DyeColor getColor(Block block) {
        return block instanceof JarBlock ? ((JarBlock)block).getColor() : null;
    }

    public static Block get(@Nullable DyeColor dyeColor) {
        if (dyeColor == null) {
            return ModBlocks.JAR;
        } else {
            return switch (dyeColor) {
                case WHITE -> ModBlocks.WHITE_JAR;
                case ORANGE -> ModBlocks.ORANGE_JAR;
                case MAGENTA -> ModBlocks.MAGENTA_JAR;
                case LIGHT_BLUE -> ModBlocks.LIGHT_BLUE_JAR;
                case YELLOW -> ModBlocks.YELLOW_JAR;
                case LIME -> ModBlocks.LIME_JAR;
                case PINK -> ModBlocks.PINK_JAR;
                case GRAY -> ModBlocks.GRAY_JAR;
                case LIGHT_GRAY -> ModBlocks.LIGHT_GRAY_JAR;
                case CYAN -> ModBlocks.CYAN_JAR;
                case BLUE -> ModBlocks.BLUE_JAR;
                case BROWN -> ModBlocks.BROWN_JAR;
                case GREEN -> ModBlocks.GREEN_JAR;
                case RED -> ModBlocks.RED_JAR;
                case BLACK -> ModBlocks.BLACK_JAR;
                case PURPLE -> ModBlocks.PURPLE_JAR;
            };
        }
    }

    @Nullable
    public DyeColor getColor() {
        return this.color;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (!state.get(OPEN)){
            return CLOSED_SHAPE;
        }
        return OPEN_SHAPE;
    }

    @Override
    protected boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    @Override
    public @Nullable BlockEntity getBlockEntity(World world, BlockPos pos) {
        return world.getBlockEntity(pos);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? validateTicker(type, ModBlockEntities.JAR_BE, JarBlockEntity::tick) : null;
    }
}

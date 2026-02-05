package net.edu.resprouted.block.custom.decorative;

import com.mojang.serialization.MapCodec;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.util.block.BlockUtils;
import net.edu.resprouted.block.entity.custom.decorative.LiquidBarrelBlockEntity;
import net.edu.resprouted.block.interfaces.LuminousFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
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
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
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

public class LiquidBarrelBlock extends BlockWithEntity implements LuminousFluidStorage, Waterloggable{
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 1.0, 12.0),
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 16.0, 4.0),
            Block.createCuboidShape(2.0, 0.0, 12.0, 14.0, 16.0, 14.0),
            Block.createCuboidShape(12.0, 0.0, 4.0, 14.0, 16.0, 12.0),
            Block.createCuboidShape(2.0, 0.0, 4.0, 4.0, 16.0, 12.0)
    );

    private static final VoxelShape COLLISION = VoxelShapes.union(
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 1.0, 14.0),
            Block.createCuboidShape(2.0, 1.0, 2.0, 14.0, 16.0, 2.5),
            Block.createCuboidShape(2.0, 1.0, 13.5, 14.0, 16.0, 14.0),
            Block.createCuboidShape(13.5, 1.0, 2.5, 14.0, 16.0, 13.5),
            Block.createCuboidShape(2.0, 1.0, 2.5, 2.5, 16.0, 13.5)
    );
    public static final MapCodec<LiquidBarrelBlock> CODEC = LiquidBarrelBlock.createCodec(LiquidBarrelBlock::new);

    public LiquidBarrelBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(LIGHT_LEVEL, 0).with(WATERLOGGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIGHT_LEVEL, WATERLOGGED);
    }

    @Override
    @Nullable
    public BlockEntity getBlockEntity(World world, BlockPos pos) {
        return world.getBlockEntity(pos);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new LiquidBarrelBlockEntity(pos, state);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        BlockUtils.appendFluidTooltip(stack, tooltip);
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof LiquidBarrelBlockEntity barrel) {
            if (!world.isClient && player.isCreative() && !barrel.getFluidStorage().isResourceBlank()) {
                ItemStack stack = new ItemStack(this);
                stack.applyComponentsFrom(barrel.createComponentMap());

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
        if (blockEntity instanceof LiquidBarrelBlockEntity barrel) {
            ItemStack stack = new ItemStack(this);
            stack.applyComponentsFrom(barrel.createComponentMap());
            return List.of(stack);
        }
        return List.of(new ItemStack(this));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }


    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world.isClient || !entity.isOnFire()) return;

        if (world.getBlockEntity(pos) instanceof LiquidBarrelBlockEntity barrel) {
            FluidVariant fluid = barrel.getFluidStorage().getResource();
            long amount = barrel.getFluidStorage().getAmount();

            if (!fluid.isBlank() && amount >= FluidConstants.BOTTLE) {
                if (isEntityTouchingFluid(pos, entity, barrel)) {
                    if (entity instanceof LivingEntity livingEntity) {
                        livingEntity.extinguish();
                        world.syncWorldEvent(1009, pos, 0);
                    }
                }
            }
        }
    }

    private boolean isEntityTouchingFluid(BlockPos pos, Entity entity, LiquidBarrelBlockEntity barrel) {
        Box entityBox = entity.getBoundingBox();
        double fluidHeight = pos.getY() + getFluidHeight(barrel);

        if (entity.getY() >= fluidHeight || entityBox.maxY <= pos.getY() + 0.0625) {
            return false;
        }
        double interiorMin = 0.25;
        double interiorMax = 0.75;

        return entityBox.maxX > pos.getX() + interiorMin && entityBox.minX < pos.getX() + interiorMax
                && entityBox.maxZ > pos.getZ() + interiorMin && entityBox.minZ < pos.getZ() + interiorMax;
    }

    private double getFluidHeight(LiquidBarrelBlockEntity barrel) {
        long amount = barrel.getFluidStorage().getAmount();

        if (amount <= 0) return 0.0625;

        long capacity = barrel.getFluidStorage().getCapacity();
        double fillPercentage = (double) amount / capacity;

        return 0.0625 + (0.8125 * fillPercentage);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockPos dripPos = PointedDripstoneBlock.getDripPos(world, pos);
        if (dripPos != null) {
            Fluid fluid = PointedDripstoneBlock.getDripFluid(world, dripPos);
            if (fluid != Fluids.EMPTY && BlockUtils.canBeFilledByDripstone(fluid)) {
                if (world.getBlockEntity(pos) instanceof LiquidBarrelBlockEntity barrel) {
                    BlockUtils.fillFromDripstone(state, world, pos, fluid, barrel.getFluidStorage(), this);
                }
            }
        }
    }

    @Override
    public void precipitationTick(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation) {
        if (world.getBlockEntity(pos) instanceof LiquidBarrelBlockEntity barrel) {
            BlockUtils.handlePrecipitation(state, world, pos, precipitation, barrel.getFluidStorage(), this);
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.isSneaking()) {
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof LiquidBarrelBlockEntity)) {
            return ItemActionResult.FAIL;
        }

        return BlockUtils.handleFluidInteraction(world, pos, player, hand, hit, this, state);
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
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? validateTicker(type, ModBlockEntities.LIQUID_BARREL_BE, LiquidBarrelBlockEntity::tick) : null;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return COLLISION;
    }
}
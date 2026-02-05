package net.edu.resprouted.block.custom.decorative;

import com.mojang.serialization.MapCodec;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.entity.custom.decorative.CabinetBlockEntity;
import net.edu.resprouted.block.enums.CabinetType;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoorHinge;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.DoubleInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

public class CabinetBlock extends BlockWithEntity implements Waterloggable, InventoryProvider {
    public static final BooleanProperty OPEN = BooleanProperty.of("open");
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final MapCodec<CabinetBlock> CODEC = createCodec(CabinetBlock::new);
    public static final EnumProperty<DoorHinge> HINGE = Properties.DOOR_HINGE;
    public static final EnumProperty<CabinetType> CABINET_TYPE = EnumProperty.of("cabinet_type", CabinetType.class);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private static final DoubleBlockProperties.PropertyRetriever<CabinetBlockEntity, Optional<Inventory>> INVENTORY_RETRIEVER = new DoubleBlockProperties.PropertyRetriever<>() {
        public Optional<Inventory> getFromBoth(CabinetBlockEntity first, CabinetBlockEntity second) {
            return Optional.of(new DoubleInventory(second, first));
        }

        public Optional<Inventory> getFrom(CabinetBlockEntity single) {
            return Optional.of(single);
        }

        public Optional<Inventory> getFallback() {
            return Optional.empty();
        }
    };

    private static final DoubleBlockProperties.PropertyRetriever<CabinetBlockEntity, Optional<NamedScreenHandlerFactory>> NAME_RETRIEVER = new DoubleBlockProperties.PropertyRetriever<>() {
        public Optional<NamedScreenHandlerFactory> getFromBoth(CabinetBlockEntity first, CabinetBlockEntity second) {
            final Inventory inventory = new DoubleInventory(second, first);
            return Optional.of(new NamedScreenHandlerFactory() {
                @Nullable
                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                    if (first.checkUnlocked(player) && second.checkUnlocked(player)) {
                        first.generateLoot(player);
                        second.generateLoot(player);
                        return GenericContainerScreenHandler.createGeneric9x6(syncId, inv, inventory);
                    } else {
                        return null;
                    }
                }

                @Override
                public Text getDisplayName() {
                    if (first.hasCustomName()) {
                        return first.getDisplayName();
                    } else {
                        return second.hasCustomName() ? second.getDisplayName() : Text.translatable("container.resprouted.cabinet_double");
                    }
                }
            });
        }

        public Optional<NamedScreenHandlerFactory> getFrom(CabinetBlockEntity single) {
            return Optional.of(single);
        }

        public Optional<NamedScreenHandlerFactory> getFallback() {
            return Optional.empty();
        }
    };

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    public CabinetBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState()
                .with(OPEN, false)
                .with(FACING, Direction.NORTH)
                .with(CABINET_TYPE, CabinetType.SINGLE)
                .with(HINGE, DoorHinge.LEFT)
                .with(WATERLOGGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN, CABINET_TYPE, HINGE, WATERLOGGED);
    }

    public static DoubleBlockProperties.Type getDoubleBlockType(BlockState state) {
        CabinetType type = state.get(CABINET_TYPE);
        if (type == CabinetType.SINGLE) {
            return DoubleBlockProperties.Type.SINGLE;
        } else {
            return type == CabinetType.BOTTOM ? DoubleBlockProperties.Type.FIRST : DoubleBlockProperties.Type.SECOND;
        }
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        CabinetType type = state.get(CABINET_TYPE);

        if (type != CabinetType.SINGLE) {
            Direction expectedDir = type == CabinetType.TOP ? Direction.DOWN : Direction.UP;

            if (direction == expectedDir) {
                if (!(neighborState.getBlock() instanceof CabinetBlock)
                        || neighborState.get(CABINET_TYPE) != type.getOpposite()
                        || neighborState.get(FACING) != state.get(FACING)
                        || neighborState.get(HINGE) != state.get(HINGE)) {
                    return state.with(CABINET_TYPE, CabinetType.SINGLE);
                }
            }
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CabinetBlockEntity(pos, state);
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(getInventory(this, state, world, pos, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        BlockPos below = pos.down();
        Direction facing = ctx.getHorizontalPlayerFacing().getOpposite();
        DoorHinge hinge = getHingeSide(ctx);
        FluidState fluidState = world.getFluidState(pos);

        BlockState belowState = world.getBlockState(below);

        if (belowState.getBlock() instanceof CabinetBlock
                && belowState.get(FACING) == facing
                && belowState.get(CABINET_TYPE) == CabinetType.SINGLE
                && belowState.get(HINGE) == hinge) {

            return getDefaultState()
                    .with(FACING, facing)
                    .with(CABINET_TYPE, CabinetType.TOP)
                    .with(OPEN, false)
                    .with(HINGE, hinge)
                    .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        }

        return getDefaultState()
                .with(FACING, facing)
                .with(CABINET_TYPE, CabinetType.SINGLE)
                .with(OPEN, false)
                .with(HINGE, hinge)
                .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    private DoorHinge getHingeSide(ItemPlacementContext context) {
        Direction facing = context.getHorizontalPlayerFacing();
        double hitX = context.getHitPos().x - context.getBlockPos().getX();
        double hitZ = context.getHitPos().z - context.getBlockPos().getZ();

        return switch (facing) {
            case NORTH -> hitX >= 0.5 ? DoorHinge.RIGHT : DoorHinge.LEFT;
            case SOUTH -> hitX < 0.5 ? DoorHinge.RIGHT : DoorHinge.LEFT;
            case WEST -> hitZ < 0.5 ? DoorHinge.RIGHT : DoorHinge.LEFT;
            case EAST -> hitZ >= 0.5 ? DoorHinge.RIGHT : DoorHinge.LEFT;
            default -> DoorHinge.LEFT;
        };
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient) {
            CabinetType type = state.get(CABINET_TYPE);

            if (type != CabinetType.SINGLE) {
                Direction dirToOther = type == CabinetType.TOP ? Direction.DOWN : Direction.UP;
                BlockPos otherPos = pos.offset(dirToOther);
                BlockState otherState = world.getBlockState(otherPos);

                if (otherState.getBlock() instanceof CabinetBlock && otherState.get(CABINET_TYPE) == CabinetType.SINGLE) {
                    world.setBlockState(otherPos, otherState.with(CABINET_TYPE, type.getOpposite()), 3);
                }
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        ItemScatterer.onStateReplaced(state, newState, world, pos);
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient)
            return ActionResult.SUCCESS;

        if (hit.getSide() != state.get(FACING)) {
            return ActionResult.PASS;
        }

        if (isBlocked(world, pos)) {
            return ActionResult.CONSUME;
        }

        NamedScreenHandlerFactory factory = createScreenHandlerFactory(state, world, pos);
        if (factory != null) {
            player.openHandledScreen(factory);
        }

        return ActionResult.CONSUME;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public DoubleBlockProperties.PropertySource<? extends CabinetBlockEntity> getBlockEntitySource(BlockState state, World world, BlockPos pos, boolean ignoreBlocked) {
        BiPredicate<WorldAccess, BlockPos> blockPredicate = ignoreBlocked ? (w, p) -> false : this::isBlocked;

        return DoubleBlockProperties.toPropertySource(
                ModBlockEntities.CABINET_BE,
                CabinetBlock::getDoubleBlockType,
                CabinetBlock::getDirectionTowardsOtherHalf,
                FACING,
                state,
                world,
                pos,
                blockPredicate
        );
    }

    private static Direction getDirectionTowardsOtherHalf(BlockState state) {
        CabinetType type = state.get(CABINET_TYPE);
        return type == CabinetType.BOTTOM ? Direction.UP : Direction.DOWN;
    }

    @Nullable
    @Override
    protected NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return getBlockEntitySource(state, world, pos, false).apply(NAME_RETRIEVER).orElse(null);
    }

    @Nullable
    public static Inventory getInventory(CabinetBlock block, BlockState state, World world, BlockPos pos, boolean ignoreBlocked) {
        return block.getBlockEntitySource(state, world, pos, ignoreBlocked).apply(INVENTORY_RETRIEVER).orElse(null);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof CabinetBlockEntity cabinetBE) {
            cabinetBE.onScheduledTick();
        }
    }

    private boolean isBlocked(WorldAccess world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return hasBlockInFront(world, pos, state) || hasCatInFront(world, pos, state);
    }

    private boolean hasBlockInFront(WorldAccess world, BlockPos pos, BlockState state) {
        BlockPos frontPos = pos.offset(state.get(FACING));
        BlockState frontState = world.getBlockState(frontPos);
        return frontState.isSolidBlock(world, frontPos);
    }

    private static boolean hasCatInFront(WorldAccess world, BlockPos pos, BlockState state) {
        Direction facing = state.get(FACING);
        BlockPos frontPos = pos.offset(facing);

        List<CatEntity> list = world.getNonSpectatingEntities(
                CatEntity.class, new Box(frontPos.getX(), frontPos.getY(), frontPos.getZ(),
                        frontPos.getX() + 1, frontPos.getY() + 1, frontPos.getZ() + 1));

        for (CatEntity catEntity : list) {
            if (catEntity.isInSittingPose()) {
                return true;
            }
        }

        return false;
    }

    @Nullable
    @Override
    public SidedInventory getInventory(BlockState state, WorldAccess world, BlockPos pos) {
        Inventory inventory = getInventory(this, state, (World) world, pos, false);

        if (inventory == null) {
            return null;
        }

        return new SidedInventory() {
            @Override
            public int[] getAvailableSlots(Direction side) {
                int[] slots = new int[inventory.size()];
                for (int i = 0; i < slots.length; i++) {
                    slots[i] = i;
                }
                return slots;
            }

            @Override
            public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
                return true;
            }

            @Override
            public boolean canExtract(int slot, ItemStack stack, Direction dir) {
                return true;
            }

            @Override
            public int size() {
                return inventory.size();
            }

            @Override
            public boolean isEmpty() {
                return inventory.isEmpty();
            }

            @Override
            public ItemStack getStack(int slot) {
                return inventory.getStack(slot);
            }

            @Override
            public ItemStack removeStack(int slot, int amount) {
                return inventory.removeStack(slot, amount);
            }

            @Override
            public ItemStack removeStack(int slot) {
                return inventory.removeStack(slot);
            }

            @Override
            public void setStack(int slot, ItemStack stack) {
                inventory.setStack(slot, stack);
            }

            @Override
            public void markDirty() {
                inventory.markDirty();
            }

            @Override
            public boolean canPlayerUse(PlayerEntity player) {
                return inventory.canPlayerUse(player);
            }

            @Override
            public void clear() {
                inventory.clear();
            }
        };
    }
}
package net.edu.resprouted.block.custom.decorative;

import com.mojang.serialization.MapCodec;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.entity.custom.CabinetBlockEntity;
import net.edu.resprouted.block.enums.CabinetType;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.DoorHinge;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
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
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class CabinetBlock extends AbstractCabinetBlock<CabinetBlockEntity> implements Waterloggable {
    public static final BooleanProperty OPEN = BooleanProperty.of("open");
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final MapCodec<CabinetBlock> CODEC = CabinetBlock.createCodec(CabinetBlock::new);
    public static final EnumProperty<DoorHinge> HINGE = Properties.DOOR_HINGE;
    public static final EnumProperty<CabinetType> CABINET_TYPE = EnumProperty.of("cabinet_type", CabinetType.class);

    public CabinetBlock(Settings settings) {
        super(settings, () -> ModBlockEntities.CABINET_BE);
        setDefaultState(getStateManager().getDefaultState()
                .with(OPEN, false)
                .with(FACING, Direction.NORTH)
                .with(CABINET_TYPE, CabinetType.SINGLE)
                .with(HINGE, DoorHinge.LEFT));
    }

    // ========= PROPIEDADES Y ESTADO =========
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN, CABINET_TYPE, HINGE);
    }
    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
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
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof Inventory inv) {
            return ScreenHandler.calculateComparatorOutput(inv);
        }
        return 0;
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        BlockPos below = pos.down();

        Direction facing = ctx.getHorizontalPlayerFacing().getOpposite();
        DoorHinge hinge = getHingeSide(ctx);
        BlockState state = getDefaultState()
                .with(FACING, facing)
                .with(CABINET_TYPE, CabinetType.SINGLE)
                .with(OPEN, false)
                .with(HINGE, hinge);

        BlockState belowState = world.getBlockState(below);
        if (belowState.getBlock() instanceof CabinetBlock &&
                belowState.get(CabinetBlock.FACING) == facing &&
                belowState.get(CabinetBlock.CABINET_TYPE) == CabinetType.SINGLE &&
                belowState.get(CabinetBlock.HINGE) == hinge) {
            return state.with(CABINET_TYPE, CabinetType.TOP);
        }
        return state;
    }
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient) {
            CabinetType type = state.get(CABINET_TYPE);
            Direction facing = state.get(FACING);
            BlockPos otherPos;

            if (type == CabinetType.TOP) {
                otherPos = pos.down();
            } else if (type == CabinetType.BOTTOM) {
                otherPos = pos.up();
            } else {
                BlockPos belowPos = pos.down();
                BlockState belowState = world.getBlockState(belowPos);
                if (belowState.getBlock() instanceof CabinetBlock &&
                        belowState.get(CABINET_TYPE) == CabinetType.SINGLE &&
                        belowState.get(FACING) == facing &&
                        belowState.get(HINGE) == state.get(HINGE)) {
                    world.setBlockState(pos, state.with(CABINET_TYPE, CabinetType.TOP));
                    world.setBlockState(belowPos, belowState.with(CABINET_TYPE, CabinetType.BOTTOM));
                }
                return;
            }

            BlockState otherState = world.getBlockState(otherPos);
            if (otherState.getBlock() instanceof CabinetBlock &&
                    otherState.get(CABINET_TYPE) == CabinetType.SINGLE &&
                    otherState.get(FACING) == facing &&
                    otherState.get(HINGE) == state.get(HINGE)) {
                world.setBlockState(otherPos, otherState.with(CABINET_TYPE,
                        type == CabinetType.TOP ? CabinetType.BOTTOM : CabinetType.TOP));
            }
        }
    }
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            ItemScatterer.spawn(world, pos, (Inventory) world.getBlockEntity(pos));

            CabinetType type = state.get(CABINET_TYPE);
            BlockPos otherPos = type == CabinetType.TOP ? pos.down() : type == CabinetType.BOTTOM ? pos.up() : null;

            if (otherPos != null) {
                BlockState otherState = world.getBlockState(otherPos);
                if (otherState.getBlock() instanceof CabinetBlock && otherState.get(CABINET_TYPE) != CabinetType.SINGLE) {
                    world.setBlockState(otherPos, otherState.with(CABINET_TYPE, CabinetType.SINGLE));
                }
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : (w, pos, s, be) -> {
            if (be instanceof CabinetBlockEntity cabinet) {
                CabinetType cabinetType = s.get(CabinetBlock.CABINET_TYPE);
                BlockPos bottomPos = cabinetType == CabinetType.TOP ? pos.down() : pos;

                if (cabinetType != CabinetType.TOP) {
                    cabinet.stateManager.updateViewerCount(w, bottomPos, w.getBlockState(bottomPos));
                }
            }
        };
    }
    private static @NotNull NamedScreenHandlerFactory getNamedScreenHandlerFactory(CabinetBlockEntity self, CabinetType type, CabinetBlockEntity other) {
        CabinetBlockEntity lower = type == CabinetType.BOTTOM ? self : other;
        CabinetBlockEntity upper = type == CabinetType.BOTTOM ? other : self;
        return new NamedScreenHandlerFactory() {
            @Override
            public Text getDisplayName() {
                return Text.translatable("container.resprouted.cabinet_double");
            }
            @Override
            public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                return GenericContainerScreenHandler.createGeneric9x6(syncId, inv, new DoubleInventory(lower, upper));
            }
        };
    }
    @Override
    public DoubleBlockProperties.PropertySource<CabinetBlockEntity> getBlockEntitySource(BlockState state, World world, BlockPos pos, boolean ignoreBlocked) {
        return DoubleBlockProperties.toPropertySource(
                ModBlockEntities.CABINET_BE,
                (BlockState s) -> {
                    CabinetType type = s.get(CABINET_TYPE);
                    return switch (type) {
                        case SINGLE -> DoubleBlockProperties.Type.SINGLE;
                        case BOTTOM -> DoubleBlockProperties.Type.FIRST;
                        case TOP -> DoubleBlockProperties.Type.SECOND;
                    };
                },
                (BlockState s) -> s.get(FACING), FACING, state, world, pos, (w, p) -> {
                    BlockEntity be = w.getBlockEntity(p);
                    return be instanceof CabinetBlockEntity;
                }
        );
    }
    private DoorHinge getHingeSide(ItemPlacementContext context) {
        Direction facing = context.getHorizontalPlayerFacing();
        Vec3d hit = context.getHitPos();
        BlockPos blockPos = context.getBlockPos();
        double hitX = hit.x - blockPos.getX();
        double hitZ = hit.z - blockPos.getZ();

        return switch (facing) {
            case NORTH -> hitX >= 0.5 ? DoorHinge.RIGHT : DoorHinge.LEFT;
            case SOUTH -> hitX < 0.5 ? DoorHinge.RIGHT : DoorHinge.LEFT;
            case WEST  -> hitZ < 0.5 ? DoorHinge.RIGHT : DoorHinge.LEFT;
            case EAST  -> hitZ >= 0.5 ? DoorHinge.RIGHT : DoorHinge.LEFT;
            default    -> DoorHinge.LEFT;
        };
    }
    // ========= INTERACCIÓN =========
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;

        BlockEntity selfBE = world.getBlockEntity(pos);
        if (!(selfBE instanceof CabinetBlockEntity self)) return ActionResult.PASS;

        CabinetType type = state.get(CABINET_TYPE);
        BlockPos otherPos = switch (type) {
            case BOTTOM -> pos.up();
            case TOP -> pos.down();
            default -> null;
        };
        CabinetBlockEntity other = null;
        if (otherPos != null) {
            BlockEntity be = world.getBlockEntity(otherPos);
            if (be instanceof CabinetBlockEntity cbe) {
                other = cbe;
            }
        }
        if (type == CabinetType.SINGLE || other == null) {
            player.openHandledScreen(self);
        } else {
            NamedScreenHandlerFactory factory = getNamedScreenHandlerFactory(self, type, other);
            player.openHandledScreen(factory);
        }
        return ActionResult.CONSUME;
    }

    // ========= FORMA Y TRANSFORMACIONES =========
    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}

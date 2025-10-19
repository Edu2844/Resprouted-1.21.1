package net.edu.resprouted.block.custom.decorative;

import com.mojang.serialization.MapCodec;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.abstracts.AbstractCabinetBlock;
import net.edu.resprouted.block.entity.custom.CabinetBE;
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
import org.jetbrains.annotations.Nullable;

public class CabinetBlock extends AbstractCabinetBlock<CabinetBE> implements Waterloggable {
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
        return new CabinetBE(pos, state);
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        Inventory inv = getCombinedInventory(world, state, pos);
        return ScreenHandler.calculateComparatorOutput(inv);
    }

    @Nullable
    private Inventory getCombinedInventory(World world, BlockState state, BlockPos pos) {
        CabinetType type = state.get(CABINET_TYPE);
        BlockEntity be = world.getBlockEntity(pos);

        if (!(be instanceof CabinetBE self)) return null;

        if (type == CabinetType.SINGLE) return self;

        BlockPos otherPos = type == CabinetType.TOP ? pos.down() : pos.up();
        BlockEntity otherBe = world.getBlockEntity(otherPos);

        if (otherBe instanceof CabinetBE other) {
            return new DoubleInventory(
                    type == CabinetType.BOTTOM ? self : other,
                    type == CabinetType.BOTTOM ? other : self
            );
        }
        return self;
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
            if (be instanceof CabinetBE cabinet) {
                CabinetType cabinetType = s.get(CabinetBlock.CABINET_TYPE);
                BlockPos bottomPos = cabinetType == CabinetType.TOP ? pos.down() : pos;

                if (cabinetType != CabinetType.TOP) {
                    cabinet.stateManager.updateViewerCount(w, bottomPos, w.getBlockState(bottomPos));
                }
            }
        };
    }

    private static @NotNull NamedScreenHandlerFactory getNamedScreenHandlerFactory(CabinetBE self, CabinetType type, CabinetBE other) {
        CabinetBE lower = type == CabinetType.BOTTOM ? self : other;
        CabinetBE upper = type == CabinetType.BOTTOM ? other : self;
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

        if (hit.getSide() != state.get(FACING)) {
            return ActionResult.PASS;
        }

        if (isBlocked(world, pos, state)) {
            return ActionResult.CONSUME;
        }

        if (!(world.getBlockEntity(pos) instanceof CabinetBE sbe)) return ActionResult.PASS;

        CabinetType type = state.get(CABINET_TYPE);
        BlockPos otherPos = switch (type) {
            case BOTTOM -> pos.up();
            case TOP -> pos.down();
            default -> null;
        };
        CabinetBE other = null;
        if (otherPos != null) {
            if (world.getBlockEntity(otherPos) instanceof CabinetBE cbe) {
                other = cbe;
            }
        }
        if (type == CabinetType.SINGLE || other == null) {
            player.openHandledScreen(sbe);
        } else {
            NamedScreenHandlerFactory factory = getNamedScreenHandlerFactory(sbe, type, other);
            player.openHandledScreen(factory);
        }
        return ActionResult.CONSUME;
    }

    private boolean isBlocked(World world, BlockPos pos, BlockState state) {

        BlockPos frontPos = pos.offset(state.get(FACING));
        BlockState frontState = world.getBlockState(frontPos);

        if (frontState.isSolidBlock(world, frontPos)) {
            return true;
        }
        CabinetType type = state.get(CABINET_TYPE);
        if (type != CabinetType.SINGLE) {
            BlockPos otherPos = type == CabinetType.TOP ? pos.down() : pos.up();
            BlockState otherState = world.getBlockState(otherPos);

            if (otherState.getBlock() instanceof CabinetBlock) {
                Direction otherFacing = otherState.get(FACING);
                BlockPos otherFrontPos = otherPos.offset(otherFacing);
                BlockState otherFrontState = world.getBlockState(otherFrontPos);

                return otherFrontState.isSolidBlock(world, otherFrontPos);
            }
        }
        return false;
    }

    // ========= FORMA Y TRANSFORMACIONES =========
    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}

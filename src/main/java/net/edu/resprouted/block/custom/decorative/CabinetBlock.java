package net.edu.resprouted.block.custom.decorative;

import com.mojang.serialization.MapCodec;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.entity.custom.CabinetBlockEntity;
import net.edu.resprouted.block.enums.CabinetType;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
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
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CabinetBlock extends AbstractCabinetBlock<CabinetBlockEntity> {
    public static final BooleanProperty OPEN = BooleanProperty.of("open");
    public static final EnumProperty<CabinetType> CABINET_TYPE = EnumProperty.of("cabinet_type", CabinetType.class);
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final MapCodec<CabinetBlock> CODEC = CabinetBlock.createCodec(CabinetBlock::new);
    public CabinetBlock(Settings settings) {
        super(settings, () -> ModBlockEntities.CABINET_BE);
        setDefaultState(getStateManager().getDefaultState()
                .with(OPEN, false)
                .with(FACING, Direction.NORTH)
                .with(CABINET_TYPE, CabinetType.SINGLE));
    }
    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }
    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CabinetBlockEntity(pos, state);
    }
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(OPEN, false)
                .with(CABINET_TYPE, CabinetType.SINGLE);
    }
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient) {
            Direction facing = state.get(FACING);

            BlockPos above = pos.up();
            BlockPos below = pos.down();

            BlockState aboveState = world.getBlockState(above);
            BlockState belowState = world.getBlockState(below);

            boolean combined = false;

            if (belowState.getBlock() instanceof CabinetBlock &&
                    belowState.get(CABINET_TYPE) == CabinetType.SINGLE &&
                    belowState.get(FACING) == facing) {

                world.setBlockState(pos, state.with(CABINET_TYPE, CabinetType.TOP));
                world.setBlockState(below, belowState.with(CABINET_TYPE, CabinetType.BOTTOM));
                combined = true;
            } else if (aboveState.getBlock() instanceof CabinetBlock &&
                    aboveState.get(CABINET_TYPE) == CabinetType.SINGLE &&
                    aboveState.get(FACING) == facing) {

                world.setBlockState(pos, state.with(CABINET_TYPE, CabinetType.BOTTOM));
                world.setBlockState(above, aboveState.with(CABINET_TYPE, CabinetType.TOP));
                combined = true;
            }
            if (!combined) {
                world.setBlockState(pos, state.with(CABINET_TYPE, CabinetType.SINGLE));
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
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN, CABINET_TYPE);
    }
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
                (BlockState s) -> Direction.NORTH, FACING, state, world, pos, (w, p) -> {
                    BlockEntity be = w.getBlockEntity(p);
                    return be instanceof CabinetBlockEntity;
                }
        );
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
}

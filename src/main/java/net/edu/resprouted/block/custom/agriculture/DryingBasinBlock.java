package net.edu.resprouted.block.custom.agriculture;

import com.mojang.serialization.MapCodec;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.entity.custom.DryingBasinBlockEntity;
import net.edu.resprouted.block.interfaces.LuminousFluidStorage;
import net.edu.resprouted.fluid.data.FluidItemInteractionHelper;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DryingBasinBlock extends BlockWithEntity implements LuminousFluidStorage {
    private static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 1.0F, 12.0),
            Block.createCuboidShape(2.0, 0.0, 4.0, 4.0, 6.0F, 12.0),
            Block.createCuboidShape(12.0, 0.0, 4.0, 14.0, 6.0F, 12.0),
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 6.0F, 4.0),
            Block.createCuboidShape(2.0, 0.0, 12.0, 14.0, 6.0F, 14.0)
    );
    public static final MapCodec<DryingBasinBlock> CODEC = DryingBasinBlock.createCodec(DryingBasinBlock::new);

    public DryingBasinBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(LIGHT_LEVEL, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIGHT_LEVEL);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DryingBasinBlockEntity(pos,state);
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
    @Nullable
    public BlockEntity getBlockEntity(World world, BlockPos pos) {
        return world.getBlockEntity(pos);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof DryingBasinBlockEntity basin)) {
            return ItemActionResult.FAIL;
        }

        Storage<FluidVariant> fluidStorage = FluidStorage.SIDED.find(world, pos, hit.getSide());
        if (fluidStorage == null) {
            return ItemActionResult.FAIL;
        }

        ItemActionResult fluidResult = FluidItemInteractionHelper.onFluidStorageUse(player, stack, fluidStorage, world, pos, true, true);

        if (fluidResult == ItemActionResult.SUCCESS) {
            updateBlockWithLight(basin, world, pos, state);
            return ItemActionResult.SUCCESS;
        }

        ItemStack stored = basin.getStack(0);
        if (stack.isEmpty()) {
            return handleItemExtraction(basin, world, pos, state, player, stored);
        }

        return ItemActionResult.CONSUME;
    }

    private ItemActionResult handleItemExtraction(DryingBasinBlockEntity basin, World world, BlockPos pos, BlockState state, PlayerEntity player, ItemStack stored) {
        if (stored.isEmpty()) return ItemActionResult.FAIL;

        player.getInventory().offerOrDrop(stored.copy());
        basin.setStack(0, ItemStack.EMPTY);

        world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8f, 1.5f);
        updateBlockWithLight(basin, world, pos, state);

        return ItemActionResult.SUCCESS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof DryingBasinBlockEntity basin) {
                ItemStack storedStack = basin.getStack(0);

                if (!storedStack.isEmpty()) {
                    ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), storedStack);
                }
            }
        }

        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (type != ModBlockEntities.DRYING_BASIN_BE) return null;

        if (!world.isClient) {
            return (w, pos, st, be) -> {
                if (be instanceof DryingBasinBlockEntity basin)
                    DryingBasinBlockEntity.serverTick(w, pos, st, basin);
            };
        }

        return validateTicker(type, ModBlockEntities.DRYING_BASIN_BE, DryingBasinBlockEntity::clientTick);
    }
}

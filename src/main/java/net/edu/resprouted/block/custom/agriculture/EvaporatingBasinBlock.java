package net.edu.resprouted.block.custom.agriculture;

import com.mojang.serialization.MapCodec;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.entity.custom.EvaporatingBasinBE;
import net.edu.resprouted.fluid.util.FluidInteractionHelper;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
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

public class EvaporatingBasinBlock extends BlockWithEntity {
    private static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(4.0F, 0.0F, 4.0F, 12.0F, 1.0F, 12.0F),
            Block.createCuboidShape(2.0F, 0.0F, 4.0F, 4.0F, 6.0F, 12.0F),
            Block.createCuboidShape(12.0F, 0.0F, 4.0F, 14.0F, 6.0F, 12.0F),
            Block.createCuboidShape(2.0F, 0.0F, 2.0F, 14.0F, 6.0F, 4.0F),
            Block.createCuboidShape(2.0F, 0.0F, 12.0F, 14.0F, 6.0F, 14.0F)
    );
    public static final MapCodec<EvaporatingBasinBlock> CODEC = EvaporatingBasinBlock.createCodec(EvaporatingBasinBlock::new);

    public EvaporatingBasinBlock(Settings settings) {
        super(settings);
    }
    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EvaporatingBasinBE(pos,state);
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
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!(blockEntity instanceof EvaporatingBasinBE basin)) {
            return ItemActionResult.FAIL;
        }
        ItemStack basinStack = basin.getStack(0);
        var storage = FluidStorage.SIDED.find(world, pos, hit.getSide());

        if (storage == null) {
            return ItemActionResult.FAIL;
        }
        ItemActionResult result = FluidInteractionHelper.handleFluidUse(player, stack, storage, world, pos, true, true);

        if (result == ItemActionResult.SUCCESS) {
            basin.markDirty();
            world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
            return ItemActionResult.SUCCESS;
        }
        if (stack.isEmpty()) {
            //Sacar ítem
            if (!basinStack.isEmpty()) {
                player.getInventory().offerOrDrop(basinStack.copy());
                basin.setStack(0, ItemStack.EMPTY);
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8f, 1.5f);
                basin.markDirty();
                world.updateListeners(pos, state, state, 0);
                return ItemActionResult.SUCCESS;
            }
        }
        return ItemActionResult.CONSUME;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof EvaporatingBasinBE basin) {
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
        if (world.isClient) return null;
        if (type != ModBlockEntities.EVAPORATING_BASIN_BE) return null;

        return (w, pos, st, be) -> {
            if (be instanceof EvaporatingBasinBE basin)
                EvaporatingBasinBE.tick(w, pos, st, basin);
        };
    }
}

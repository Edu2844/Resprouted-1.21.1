package net.edu.resprouted.block.custom.agriculture;

import com.mojang.serialization.MapCodec;
import net.edu.resprouted.block.entity.custom.CrushingTubBE;
import net.edu.resprouted.fluid.data.FluidContainerMapping;
import net.edu.resprouted.fluid.util.FluidInteractionHelper;
import net.edu.resprouted.recipe.custom.CrushingTubRecipe;
import net.edu.resprouted.resource.reload.FluidContainerLoader;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import java.util.Optional;

public class CrushingTubBlock extends BlockWithEntity {
    private static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 9.0, 16.0);
    public static final MapCodec<CrushingTubBlock> CODEC = CrushingTubBlock.createCodec(CrushingTubBlock::new);

    public CrushingTubBlock(Settings settings) {
        super(settings);
    }

    // ========= PROPIEDADES Y ESTADO =========
    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CrushingTubBE(pos, state);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof CrushingTubBE crushingTub) {
                ItemStack storedStack = crushingTub.getStack(0);

                if (!storedStack.isEmpty()) {
                    ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), storedStack);
                }
            }
        }

        super.onStateReplaced(state, world, pos, newState, moved);
    }

    // ========= INTERACCIÓN =========
    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!(blockEntity instanceof CrushingTubBE crushingTub)) {
            return ItemActionResult.FAIL;
        }

        /*--- Fluid logic ---*/
        var storage = FluidStorage.SIDED.find(world, pos, hit.getSide());
        boolean hasFluid = false;

        if (storage != null) {
            for (StorageView<FluidVariant> view : storage) {

                if (!view.isResourceBlank() && view.getAmount() > 0) {
                    hasFluid = true;

                    break;
                }
            }

            ItemActionResult result;

            if (hasFluid) {
                result = FluidInteractionHelper.handleFluidUse(player, stack, storage, world, pos, false, true);

                if (result == ItemActionResult.SUCCESS) {

                    crushingTub.markDirty();
                    world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);

                    return ItemActionResult.CONSUME;
                }
            }
        }

        /*--- Inventory logic ---*/
        ItemStack tubStack = crushingTub.getStack(0);

        if (stack.isEmpty()) {
            // Extract ítem
            if (!tubStack.isEmpty()) {
                player.getInventory().offerOrDrop(tubStack.copy());
                crushingTub.setStack(0, ItemStack.EMPTY);

                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8f, 1.5f);

                crushingTub.markDirty();
                world.updateListeners(pos, state, state, 0);

                return ItemActionResult.SUCCESS;
            }
        } else {
            // Insert ítem
            boolean isFluidContainer = isFluidContainer(stack);

            //Block fluid container items if has fluid
            if (!(hasFluid && isFluidContainer)) {

                if (tubStack.isEmpty()) {
                    crushingTub.setStack(0, stack.copy());
                    player.setStackInHand(hand, ItemStack.EMPTY);

                    world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8f, 1f);

                    crushingTub.markDirty();
                    world.updateListeners(pos, state, state, 0);

                    return ItemActionResult.SUCCESS;

                } else if (ItemStack.areItemsAndComponentsEqual(tubStack, stack)) {
                    int maxTransfer = Math.min(stack.getCount(), tubStack.getMaxCount() - tubStack.getCount());

                    if (maxTransfer > 0) {
                        tubStack.increment(maxTransfer);
                        stack.decrement(maxTransfer);

                        world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8f, 1f);

                        crushingTub.markDirty();
                        world.updateListeners(pos, state, state, 0);

                        return ItemActionResult.SUCCESS;
                    }
                }
            }
        }

        return ItemActionResult.FAIL;
    }

    private boolean isFluidContainer(ItemStack stack) {
        for (FluidContainerMapping mapping : FluidContainerLoader.getEntries()) {

            if (ItemStack.areItemsEqual(stack, mapping.fullItem()) || stack.isOf(mapping.emptyItem().getItem())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.handleFallDamage(fallDistance, 1.0F, entity.getDamageSources().fall());
        if (!(entity instanceof PlayerEntity)) return;

        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof CrushingTubBE crushingTub)) return;

        ItemStack tubStack = crushingTub.getStack(0);
        if (tubStack.isEmpty()) return;

        Optional<CrushingTubRecipe> opt = crushingTub.findMatchingRecipe();
        if (opt.isEmpty()) return;

        CrushingTubRecipe recipe = opt.get();
        FluidVariant recipeFluid  = recipe.fluidOutput();
        FluidVariant currentFluid = crushingTub.getFluidStorage().getResource();
        long currentAmount = crushingTub.getFluidStorage().getAmount();

        boolean canInsertFluid = recipeFluid.isBlank() || currentFluid.isBlank() || currentFluid.equals(recipeFluid);
        boolean overCapacity = !recipeFluid.isBlank() && (currentAmount + recipe.fluidAmount() > FluidConstants.BUCKET * 8);

        if (!canInsertFluid || overCapacity) return;

        Item itemBeforeCrush = tubStack.getItem();
        ItemStack visualStack = tubStack.copyWithCount(1);

        //Crushing
        try (Transaction tx = Transaction.openOuter()) {
            boolean shouldCommit = true;

            if (!recipeFluid.isBlank() && recipe.fluidAmount() > 0) {
                long inserted = crushingTub.getFluidStorage().insert(recipeFluid, recipe.fluidAmount(), tx);

                if (inserted != recipe.fluidAmount()) shouldCommit = false;
            }
            if (shouldCommit) {
                crushingTub.removeStack(0, 1);
                tx.commit();

            } else return;
        }
        crushingTub.markDirty();
        world.updateListeners(pos, state, state, Block.NOTIFY_ALL);

        if (world instanceof ServerWorld serverWorld && !world.isClient) {
            serverWorld.getChunkManager().markForUpdate(pos);
        }
        SoundEvent crushSound = (itemBeforeCrush == Items.GRAVEL) ? SoundEvents.BLOCK_GRAVEL_BREAK : SoundEvents.BLOCK_SLIME_BLOCK_FALL;
        world.playSound(null, pos, crushSound, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (world instanceof ServerWorld serverWorld && !visualStack.isEmpty()) {
            serverWorld.spawnParticles(new ItemStackParticleEffect
                    (ParticleTypes.ITEM, visualStack), pos.getX() + 0.5, pos.getY() + 0.6,
                    pos.getZ() + 0.5, 5, 0.2, 0.1, 0.2, 0.05);
        }
        if (!world.isClient && recipe.outputItem() != null && !recipe.outputItem().isEmpty()) {

            if (world.random.nextInt(100) < recipe.outputChance()) {

                ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, recipe.outputItem().copy());

                itemEntity.setVelocity(world.random.nextTriangular(0.0, 0.1), 0.2, world.random.nextTriangular(0.0, 0.1));

                world.spawnEntity(itemEntity);
            }
        }
    }

    // ========= FORMA Y TRANSFORMACIONES =========
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}


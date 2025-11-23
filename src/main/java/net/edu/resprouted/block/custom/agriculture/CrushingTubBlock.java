package net.edu.resprouted.block.custom.agriculture;

import com.mojang.serialization.MapCodec;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.entity.custom.CrushingTubBlockEntity;
import net.edu.resprouted.fluid.data.FluidContainerMapping;
import net.edu.resprouted.fluid.util.FluidInteractionHelper;
import net.edu.resprouted.recipe.custom.CrushingTubRecipe;
import net.edu.resprouted.resource.reload.FluidContainerLoader;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
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

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CrushingTubBlockEntity(pos, state);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity be = world.getBlockEntity(pos);

            if (be instanceof CrushingTubBlockEntity crushingTub) {
                ItemStack i = crushingTub.getStack(0);

                if (!i.isEmpty()) {
                    ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), i);
                }
            }
        }

        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? validateTicker(type, ModBlockEntities.CRUSHING_TUB_BE, CrushingTubBlockEntity::tick) : null;
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof CrushingTubBlockEntity ct)) {
            return ItemActionResult.FAIL;
        }

        Storage<FluidVariant> fluidStorage = FluidStorage.SIDED.find(world, pos, hit.getSide());
        boolean hasFluid = hasFluidInStorage(fluidStorage);

        if (hasFluid) {
            ItemActionResult fluidResult = FluidInteractionHelper.onFluidStorageUse(player, stack, fluidStorage, world, pos, false, true
            );

            if (fluidResult == ItemActionResult.SUCCESS) {
                updateBlock(ct, world, pos, state);
                return ItemActionResult.CONSUME;
            }
        }

        ItemStack stored = ct.getStack(0);
        boolean isHoldingFluidContainer = isFluidContainer(stack);

        if (hasFluid && isHoldingFluidContainer) {
            return ItemActionResult.FAIL;
        }

        if (stack.isEmpty()) {
            return handleItemExtraction(ct, world, pos, state, player, stored);
        } else {
            return handleItemInsertion(ct, world, pos, state, player, hand, stack, stored);
        }
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.handleFallDamage(fallDistance, 1.0F, entity.getDamageSources().fall());
        if (!(entity instanceof PlayerEntity) || world.isClient) return;

        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof CrushingTubBlockEntity ct)) return;

        ItemStack input = ct.getStack(0);
        if (input.isEmpty()) return;

        Optional<CrushingTubRecipe> m = ct.findMatchingRecipe();
        if (m.isEmpty()) return;

        CrushingTubRecipe recipe = m.get();
        FluidVariant recipeFluid = recipe.fluidOutput();
        FluidVariant currentFluid = ct.getFluidStorage().getResource();
        long currentAmount = ct.getFluidStorage().getAmount();

        boolean canInsertFluid = recipeFluid.isBlank() || currentFluid.isBlank() || currentFluid.equals(recipeFluid);
        boolean overCapacity = !recipeFluid.isBlank() && (currentAmount + recipe.fluidAmount() > FluidConstants.BUCKET * 8);
        if (!canInsertFluid || overCapacity) return;

        if (!processCrushing(ct, recipe)) return;

        ct.markDirty();
        world.updateListeners(pos, state, state, Block.NOTIFY_ALL);
        if (world instanceof ServerWorld server) {
            server.getChunkManager().markForUpdate(pos);
        }

        playEffects(world, pos, input, recipe);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    private boolean isFluidContainer(ItemStack stack) {
        for (FluidContainerMapping mapping : FluidContainerLoader.getEntries()) {

            if (ItemStack.areItemsEqual(stack, mapping.fullItem()) || stack.isOf(mapping.emptyItem().getItem())) {
                return true;
            }
        }

        return false;
    }

    private boolean hasFluidInStorage(Storage<FluidVariant> storage) {
        if (storage == null) return false;

        for (StorageView<FluidVariant> view : storage) {
            if (!view.isResourceBlank() && view.getAmount() > 0) {
                return true;
            }
        }
        return false;
    }

    private ItemActionResult handleItemExtraction(CrushingTubBlockEntity ct, World world, BlockPos pos, BlockState state, PlayerEntity player, ItemStack stored) {
        if (stored.isEmpty()) return ItemActionResult.FAIL;

        player.getInventory().offerOrDrop(stored.copy());
        ct.setStack(0, ItemStack.EMPTY);

        world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8f, 1.5f);
        updateBlock(ct, world, pos, state);

        return ItemActionResult.SUCCESS;
    }

    private ItemActionResult handleItemInsertion(CrushingTubBlockEntity ct, World world, BlockPos pos, BlockState state, PlayerEntity player, Hand hand, ItemStack stack, ItemStack stored) {
        if (stored.isEmpty()) {

            //Insert items
            ct.setStack(0, stack.copy());
            player.setStackInHand(hand, ItemStack.EMPTY);

            world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8f, 1f);
            updateBlock(ct, world, pos, state);

            return ItemActionResult.SUCCESS;

        } else if (ItemStack.areItemsAndComponentsEqual(stored, stack)) {

            //Stack items
            int maxTransfer = Math.min(stack.getCount(), stored.getMaxCount() - stored.getCount());

            if (maxTransfer > 0) {
                stored.increment(maxTransfer);
                stack.decrement(maxTransfer);

                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8f, 1f);
                updateBlock(ct, world, pos, state);

                return ItemActionResult.SUCCESS;
            }
        }

        return ItemActionResult.FAIL;
    }

    private void updateBlock(CrushingTubBlockEntity ct, World world, BlockPos pos, BlockState state) {
        ct.markDirty();
        world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
    }

    private boolean processCrushing(CrushingTubBlockEntity ct, CrushingTubRecipe recipe) {
        try (Transaction tx = Transaction.openOuter()) {
            FluidVariant recipeFluid = recipe.fluidOutput();

            if (!recipeFluid.isBlank() && recipe.fluidAmount() > 0) {
                long inserted = ct.getFluidStorage().insert(recipeFluid, recipe.fluidAmount(), tx);
                if (inserted != recipe.fluidAmount()) return false;
            }

            ct.removeStack(0, 1);
            tx.commit();
            return true;
        }
    }

    private void playEffects(World world, BlockPos pos, ItemStack input, CrushingTubRecipe recipe) {
        SoundEvent crushSound = (input.getItem() == Items.GRAVEL) ? SoundEvents.BLOCK_GRAVEL_BREAK : SoundEvents.BLOCK_SLIME_BLOCK_FALL;
        world.playSound(null, pos, crushSound, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!(world instanceof ServerWorld serverWorld)) return;

        ItemStack particleStack = input.copyWithCount(1);
        serverWorld.spawnParticles(new ItemStackParticleEffect(ParticleTypes.ITEM, particleStack), pos.getX() + 0.5, pos.getY() + 0.6, pos.getZ() + 0.5,
                5, 0.2, 0.1, 0.2, 0.05
        );

        spawnOutputItem(serverWorld, pos, recipe);
    }

    private void spawnOutputItem(ServerWorld world, BlockPos pos, CrushingTubRecipe recipe) {
        if (recipe.outputItem() == null || recipe.outputItem().isEmpty()) return;
        if (world.random.nextInt(100) >= recipe.outputChance()) return;

        float midHeight = EntityType.ITEM.getHeight() / 2.0F;
        double x = pos.getX() + 0.5 + world.random.nextDouble() * 0.5 - 0.25;
        double y = pos.getY() + 0.5 + world.random.nextDouble() * 0.5 - 0.25 - midHeight;
        double z = pos.getZ() + 0.5 + world.random.nextDouble() * 0.5 - 0.25;

        ItemEntity item = new ItemEntity(world, x, y, z, recipe.outputItem().copy());
        item.setVelocity(world.random.nextTriangular(0.0, 0.1), 0.2 + world.random.nextDouble() * 0.1,
                world.random.nextTriangular(0.0, 0.1)
        );
        item.setPickupDelay(10);
        world.spawnEntity(item);
    }
}


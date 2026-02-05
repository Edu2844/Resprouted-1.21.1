package net.edu.resprouted.block.entity.custom.agriculture;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.abstracts.entity.AbstractAnimatedSingleFluidStorageBlockEntity;
import net.edu.resprouted.block.interfaces.ImplementedInventory;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.recipe.custom.DryingBasinRecipe;
import net.edu.resprouted.recipe.Input.DryingBasinRecipeInput;
import net.edu.resprouted.util.misc.ModTags;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DryingBasinBlockEntity extends AbstractAnimatedSingleFluidStorageBlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private static final long DRYING_BOOSTER = 2;
    private int progress = 0;

    public DryingBasinBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DRYING_BASIN_BE, pos, state, FluidConstants.BUCKET * 2);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("drying_basin.progress", progress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        inventory.clear();
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("drying_basin.progress");
    }

    @SuppressWarnings("unused")
    public static void clientTick(World world, BlockPos pos, BlockState state, DryingBasinBlockEntity entity) {
        entity.tickFluidAnimation();
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, DryingBasinBlockEntity be) {
        FluidVariant fluid = be.getFluidStorage().getResource();
        long amt = be.getFluidStorage().getAmount();

        if (fluid.isBlank() || amt == 0) {
            be.progress = 0;
            return;
        }

        ItemStack storedItem = be.inventory.getFirst();
        var in = new DryingBasinRecipeInput(fluid, amt, storedItem);
        var opt = world.getRecipeManager().listAllOfType(ModRecipes.DRYING_TYPE)
                .stream().filter(e -> e.value().matches(in, world)).findFirst();

        if (opt.isEmpty()) {
            be.progress = 0;
            return;
        }

        DryingBasinRecipe recipe = opt.get().value();

        // Check if block below is a booster
        BlockPos below = pos.down();
        BlockState belowState = world.getBlockState(below);
        boolean boosted = belowState.isIn(ModTags.Blocks.DRYING_BOOSTERS) || (belowState.getBlock() instanceof CampfireBlock && belowState.get(CampfireBlock.LIT));
        int tickIncrement = boosted ? (int) DRYING_BOOSTER : 1;

        // Increment progress
        be.progress += tickIncrement;

        int craftTime = recipe.craftTime();

        // When crafting is complete
        if (be.progress >= craftTime) {
            try (var tx = Transaction.openOuter()) {
                long extracted = be.getFluidStorage().extract(fluid, recipe.fluidCost(), tx);

                if (extracted == recipe.fluidCost()) {
                    tx.commit();
                    be.progress = 0;
                    be.spawnOrStore(recipe.output().copy());
                    world.playSound(null, pos, SoundEvents.BLOCK_WET_SPONGE_DRIES, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
                } else {
                    // Not enough fluid, reset progress
                    be.progress = 0;
                }
            }
        }

        be.markDirty();
    }

    private void spawnOrStore(ItemStack stack) {
        ItemStack slot = inventory.getFirst();

        if (slot.isEmpty()) {
            inventory.set(0, stack);
        } else if (ItemStack.areItemsAndComponentsEqual(slot, stack) && slot.getCount() + stack.getCount() <= slot.getMaxCount()) {
            slot.increment(stack.getCount());
        } else {
            assert world != null;
            ItemScatterer.spawn(world, pos, DefaultedList.ofSize(1, stack));
        }
        markDirty();
    }
}
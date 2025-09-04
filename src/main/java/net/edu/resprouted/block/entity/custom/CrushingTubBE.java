package net.edu.resprouted.block.entity.custom;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.interfaces.ImplementedInventory;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.recipe.custom.CrushingTubRecipe;
import net.edu.resprouted.recipe.Input.CrushingTubRecipeInput;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import java.util.Optional;

public class CrushingTubBE extends FluidStorageBE implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public CrushingTubBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRUSHING_TUB_BE, pos, state, FluidConstants.BUCKET * 8);
    }
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
    }
    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        inventory.clear();
        Inventories.readNbt(nbt, inventory, registryLookup);
    }
    public Optional<CrushingTubRecipe> findMatchingRecipe() {
        if (world == null || world.isClient) return Optional.empty();

        return world.getRecipeManager()
                .getFirstMatch(ModRecipes.CRUSHING_TUB_TYPE, new CrushingTubRecipeInput(getStack(0)), world)
                .map(RecipeEntry::value);
    }
}

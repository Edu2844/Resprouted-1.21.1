package net.edu.resprouted.recipe.Input;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record DryingBasinRecipeInput(FluidVariant fluid, long amount, ItemStack itemStack) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return slot == 0 ? itemStack : ItemStack.EMPTY;
    }

    @Override
    public int getSize() {
        return 1;
    }
}
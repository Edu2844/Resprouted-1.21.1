package net.edu.resprouted.recipe.Input;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record CrushingTubRecipeInput(ItemStack input) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return input;
    }
    @Override
    public int getSize() {
        return 1;
    }
}

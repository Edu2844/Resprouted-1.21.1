package net.edu.resprouted.recipe.Input;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record EvaporatingBasinRecipeInput(FluidVariant fluid, long amount) implements RecipeInput {
    @Override public ItemStack getStackInSlot(int slot) {
        return ItemStack.EMPTY;
    }
    @Override public int getSize() {
        return 0;
    }
}
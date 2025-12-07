package net.edu.resprouted.recipe.Input;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.util.math.BlockPos;

public record CondenserRecipeInput(ItemStack inputA, ItemStack inputB, ItemStack fuel, ItemStack bottle, BlockPos pos) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case 0 -> inputA;
            case 1 -> inputB;
            case 2 -> fuel;
            case 3 -> bottle;
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public int getSize() {
        return 4;
    }
}
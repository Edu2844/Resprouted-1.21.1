package net.edu.resprouted.recipe.Input;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.util.math.BlockPos;

public record AdvancedCondenserRecipeInput(ItemStack inputA, ItemStack inputB, ItemStack inputC, ItemStack modifier, ItemStack fuel, ItemStack bottle, BlockPos pos) implements RecipeInput {

    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case 0 -> inputA;
            case 1 -> inputB;
            case 2 -> inputC;
            case 3 -> modifier;
            case 4 -> fuel;
            case 5 -> bottle;
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public int getSize() {
        return 6;
    }
}
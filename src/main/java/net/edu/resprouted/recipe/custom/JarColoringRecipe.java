package net.edu.resprouted.recipe.custom;

import net.edu.resprouted.block.custom.decorative.JarBlock;
import net.edu.resprouted.recipe.ModRecipes;
import net.minecraft.block.Block;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

public class JarColoringRecipe extends SpecialCraftingRecipe {
    public JarColoringRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    public boolean matches(CraftingRecipeInput craftingRecipeInput, World world) {
        int i = 0;
        int j = 0;

        for (int k = 0; k < craftingRecipeInput.getSize(); k++) {
            ItemStack itemStack = craftingRecipeInput.getStackInSlot(k);
            if (!itemStack.isEmpty()) {
                if (Block.getBlockFromItem(itemStack.getItem()) instanceof JarBlock) {
                    i++;
                } else {
                    if (!(itemStack.getItem() instanceof DyeItem)) {
                        return false;
                    }

                    j++;
                }

                if (j > 1 || i > 1) {
                    return false;
                }
            }
        }

        return i == 1;
    }

    public ItemStack craft(CraftingRecipeInput craftingRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup) {
        ItemStack itemStack = ItemStack.EMPTY;
        DyeItem dyeItem = null;

        for (int i = 0; i < craftingRecipeInput.getSize(); i++) {
            ItemStack itemStack2 = craftingRecipeInput.getStackInSlot(i);
            if (!itemStack2.isEmpty()) {
                Item item = itemStack2.getItem();
                if (Block.getBlockFromItem(item) instanceof JarBlock) {
                    itemStack = itemStack2;
                } else if (item instanceof DyeItem) {
                    dyeItem = (DyeItem)item;
                }
            }
        }

        Block block = JarBlock.get(dyeItem != null ? dyeItem.getColor() : null);
        return itemStack.copyComponentsToNewStack(block, 1);
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 1;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.JAR_COLORING;
    }
}
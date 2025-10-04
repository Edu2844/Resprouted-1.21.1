package net.edu.resprouted.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.recipe.custom.OilingRecipe;
import net.edu.resprouted.util.RecipeUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("removal")
public class JEIVanillaExtensions {

    public static ICraftingCategoryExtension<OilingRecipe> createOilingExtension() {
        return new ICraftingCategoryExtension<>() {

            @Override
            public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull ICraftingGridHelper craftingGridHelper, @NotNull IFocusGroup focuses) {
                List<ItemStack> validFoods = getValidFoods();
                if (validFoods.isEmpty()) return;

                List<List<ItemStack>> inputs = new ArrayList<>();
                inputs.add(validFoods);
                inputs.add(List.of(new ItemStack(ModItems.OLIVE_OIL_BOTTLE)));

                craftingGridHelper.createAndSetInputs(builder, VanillaTypes.ITEM_STACK, inputs, 0, 0);

                List<ItemStack> oiledFoods = validFoods.stream()
                        .map(stack -> {
                            ItemStack oiled = stack.copy();
                            oiled.set(ModDataComponentTypes.OILED, true);
                            return oiled;
                        })
                        .collect(Collectors.toList());

                craftingGridHelper.createAndSetOutputs(builder, VanillaTypes.ITEM_STACK, oiledFoods);
            }

            private List<ItemStack> getValidFoods() {
                return Registries.ITEM.stream()
                        .filter(item -> RecipeUtils.isValidFood(new ItemStack(item)))
                        .map(ItemStack::new)
                        .collect(Collectors.toList());
            }
        };
    }
}






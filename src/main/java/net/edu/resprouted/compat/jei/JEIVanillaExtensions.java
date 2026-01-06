package net.edu.resprouted.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.recipe.custom.OliveOilingRecipe;
import net.edu.resprouted.util.RecipeUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("removal")
public class JEIVanillaExtensions {
    public static ICraftingCategoryExtension<OliveOilingRecipe> createOilingExtension() {
        return new ICraftingCategoryExtension<>() {

            @Override
            public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull ICraftingGridHelper craftingGridHelper, @NotNull IFocusGroup focuses) {
                List<ItemStack> validFoods = getValidFoods();
                if (validFoods.isEmpty()) return;

                List<ItemStack> foods = new ArrayList<>();
                List<ItemStack> outPuts = new ArrayList<>();

                List<IFocus<?>> allFocuses = focuses.getAllFocuses();

                boolean foundSpecificFocus = false;

                for (ItemStack food : validFoods) {
                    ItemStack oiled = food.copy();
                    oiled.set(ModDataComponentTypes.OLIVE_OILED, true);

                    for (IFocus<?> focus : allFocuses) {
                        ITypedIngredient<?> typedIngredient = focus.getTypedValue();

                        if (typedIngredient.getType() == VanillaTypes.ITEM_STACK) {
                            ItemStack stack = (ItemStack) typedIngredient.getIngredient();

                            if (areStacksEqual(stack, food)) {
                                foods.clear();
                                foods.add(food);
                                outPuts.clear();
                                outPuts.add(oiled);
                                foundSpecificFocus = true;
                                break;
                            }

                            if (areStacksEqual(stack, oiled)) {
                                foods.clear();
                                foods.add(food);
                                outPuts.clear();
                                outPuts.add(oiled);
                                foundSpecificFocus = true;
                                break;
                            }
                        }
                    }

                    if (foundSpecificFocus) {
                        break;
                    }

                    foods.add(food);
                    outPuts.add(oiled);
                }

                List<List<ItemStack>> inputs = new ArrayList<>();
                inputs.add(foods);
                inputs.add(List.of(new ItemStack(ModItems.OLIVE_OIL_BOTTLE)));

                craftingGridHelper.createAndSetInputs(builder, VanillaTypes.ITEM_STACK, inputs, 0, 0);
                craftingGridHelper.createAndSetOutputs(builder, VanillaTypes.ITEM_STACK, outPuts);
            }

            private boolean areStacksEqual(ItemStack stack1, ItemStack stack2) {
                if (!stack1.isOf(stack2.getItem())) {
                    return false;
                }

                return stack1.getComponents().equals(stack2.getComponents());
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






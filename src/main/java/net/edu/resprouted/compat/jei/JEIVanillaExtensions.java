package net.edu.resprouted.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.edu.resprouted.block.custom.decorative.JarBlock;
import net.edu.resprouted.block.custom.decorative.UrnBlock;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.recipe.custom.JarColoringRecipe;
import net.edu.resprouted.recipe.custom.OliveOilingRecipe;
import net.edu.resprouted.recipe.custom.UrnColoringRecipe;
import net.edu.resprouted.util.recipe.RecipeUtils;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.DyeColor;
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

    public static ICraftingCategoryExtension<UrnColoringRecipe> createUrnColoringExtension() {
        return new ICraftingCategoryExtension<>() {

            @Override
            public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull ICraftingGridHelper craftingGridHelper, @NotNull IFocusGroup focuses) {
                List<ItemStack> urns = getAllUrns();
                List<ItemStack> dyes = new ArrayList<>();
                List<ItemStack> outputs = new ArrayList<>();


                Optional<IFocus<ItemStack>> inputFocus = focuses.getItemStackFocuses(RecipeIngredientRole.INPUT).findFirst();

                Optional<IFocus<ItemStack>> outputFocus = focuses.getItemStackFocuses(RecipeIngredientRole.OUTPUT).findFirst();

                if (inputFocus.isPresent()) {
                    ItemStack focusedInput = inputFocus.get().getTypedValue().getIngredient();

                    DyeColor dyeColor = null;
                    for (DyeColor color : DyeColor.values()) {
                        if (focusedInput.isOf(DyeItem.byColor(color))) {
                            dyeColor = color;
                            break;
                        }
                    }

                    if (dyeColor != null) {
                        dyes.add(new ItemStack(DyeItem.byColor(dyeColor)));
                        outputs.add(new ItemStack(UrnBlock.get(dyeColor)));

                        List<List<ItemStack>> inputs = new ArrayList<>();
                        inputs.add(urns);
                        inputs.add(dyes);

                        craftingGridHelper.createAndSetInputs(builder, VanillaTypes.ITEM_STACK, inputs, 0, 0);
                        craftingGridHelper.createAndSetOutputs(builder, VanillaTypes.ITEM_STACK, outputs);
                    } else {
                        for (DyeColor color : DyeColor.values()) {
                            dyes.add(new ItemStack(DyeItem.byColor(color)));
                            outputs.add(new ItemStack(UrnBlock.get(color)));
                        }

                        List<ItemStack> repeatedUrns = new ArrayList<>();
                        for (int i = 0; i < dyes.size(); i++) {
                            repeatedUrns.add(focusedInput.copy());
                        }

                        List<List<ItemStack>> inputs = new ArrayList<>();
                        inputs.add(repeatedUrns);
                        inputs.add(dyes);

                        craftingGridHelper.createAndSetInputs(builder, VanillaTypes.ITEM_STACK, inputs, 0, 0);
                        craftingGridHelper.createAndSetOutputs(builder, VanillaTypes.ITEM_STACK, outputs);
                    }

                } else if (outputFocus.isPresent()) {
                    ItemStack focusedStack = outputFocus.get().getTypedValue().getIngredient();

                    DyeColor focusedColor = null;
                    for (DyeColor color : DyeColor.values()) {
                        ItemStack coloredUrn = new ItemStack(UrnBlock.get(color));
                        if (ItemStack.areItemsAndComponentsEqual(focusedStack, coloredUrn)) {
                            focusedColor = color;
                            break;
                        }
                    }

                    if (focusedColor != null) {
                        dyes.add(new ItemStack(DyeItem.byColor(focusedColor)));
                        outputs.add(new ItemStack(UrnBlock.get(focusedColor)));

                        List<List<ItemStack>> inputs = new ArrayList<>();
                        inputs.add(urns);
                        inputs.add(dyes);

                        craftingGridHelper.createAndSetInputs(builder, VanillaTypes.ITEM_STACK, inputs, 0, 0);
                        craftingGridHelper.createAndSetOutputs(builder, VanillaTypes.ITEM_STACK, outputs);
                    }

                } else {
                    for (DyeColor color : DyeColor.values()) {
                        dyes.add(new ItemStack(DyeItem.byColor(color)));
                        outputs.add(new ItemStack(UrnBlock.get(color)));
                    }

                    List<List<ItemStack>> inputs = new ArrayList<>();
                    inputs.add(urns);
                    inputs.add(dyes);

                    craftingGridHelper.createAndSetInputs(builder, VanillaTypes.ITEM_STACK, inputs, 0, 0);
                    craftingGridHelper.createAndSetOutputs(builder, VanillaTypes.ITEM_STACK, outputs);
                }
            }

            private List<ItemStack> getAllUrns() {
                List<ItemStack> urns = new ArrayList<>();
                urns.add(new ItemStack(UrnBlock.get(null)));

                for (DyeColor color : DyeColor.values()) {
                    urns.add(new ItemStack(UrnBlock.get(color)));
                }

                return urns;
            }
        };
    }

    public static ICraftingCategoryExtension<JarColoringRecipe> createJarColoringExtension() {
        return new ICraftingCategoryExtension<>() {

            @Override
            public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull ICraftingGridHelper craftingGridHelper, @NotNull IFocusGroup focuses) {
                List<ItemStack> jars = getAllJars();
                List<ItemStack> dyes = new ArrayList<>();
                List<ItemStack> outputs = new ArrayList<>();

                Optional<IFocus<ItemStack>> inputFocus = focuses.getItemStackFocuses(RecipeIngredientRole.INPUT).findFirst();

                Optional<IFocus<ItemStack>> outputFocus = focuses.getItemStackFocuses(RecipeIngredientRole.OUTPUT).findFirst();

                if (inputFocus.isPresent()) {
                    ItemStack focusedInput = inputFocus.get().getTypedValue().getIngredient();

                    DyeColor dyeColor = null;
                    for (DyeColor color : DyeColor.values()) {
                        if (focusedInput.isOf(DyeItem.byColor(color))) {
                            dyeColor = color;
                            break;
                        }
                    }

                    if (dyeColor != null) {
                        dyes.add(new ItemStack(DyeItem.byColor(dyeColor)));
                        outputs.add(new ItemStack(JarBlock.get(dyeColor)));

                        List<List<ItemStack>> inputs = new ArrayList<>();
                        inputs.add(jars);
                        inputs.add(dyes);

                        craftingGridHelper.createAndSetInputs(builder, VanillaTypes.ITEM_STACK, inputs, 0, 0);
                        craftingGridHelper.createAndSetOutputs(builder, VanillaTypes.ITEM_STACK, outputs);
                    } else {
                        for (DyeColor color : DyeColor.values()) {
                            dyes.add(new ItemStack(DyeItem.byColor(color)));
                            outputs.add(new ItemStack(JarBlock.get(color)));
                        }

                        List<ItemStack> repeatedJars = new ArrayList<>();
                        for (int i = 0; i < dyes.size(); i++) {
                            repeatedJars.add(focusedInput.copy());
                        }

                        List<List<ItemStack>> inputs = new ArrayList<>();
                        inputs.add(repeatedJars);
                        inputs.add(dyes);

                        craftingGridHelper.createAndSetInputs(builder, VanillaTypes.ITEM_STACK, inputs, 0, 0);
                        craftingGridHelper.createAndSetOutputs(builder, VanillaTypes.ITEM_STACK, outputs);
                    }

                } else if (outputFocus.isPresent()) {
                    ItemStack focusedStack = outputFocus.get().getTypedValue().getIngredient();

                    DyeColor focusedColor = null;
                    for (DyeColor color : DyeColor.values()) {
                        ItemStack coloredJar = new ItemStack(JarBlock.get(color));
                        if (ItemStack.areItemsAndComponentsEqual(focusedStack, coloredJar)) {
                            focusedColor = color;
                            break;
                        }
                    }

                    if (focusedColor != null) {
                        dyes.add(new ItemStack(DyeItem.byColor(focusedColor)));
                        outputs.add(new ItemStack(JarBlock.get(focusedColor)));

                        List<List<ItemStack>> inputs = new ArrayList<>();
                        inputs.add(jars);
                        inputs.add(dyes);

                        craftingGridHelper.createAndSetInputs(builder, VanillaTypes.ITEM_STACK, inputs, 0, 0);
                        craftingGridHelper.createAndSetOutputs(builder, VanillaTypes.ITEM_STACK, outputs);
                    }

                } else {
                    for (DyeColor color : DyeColor.values()) {
                        dyes.add(new ItemStack(DyeItem.byColor(color)));
                        outputs.add(new ItemStack(JarBlock.get(color)));
                    }

                    List<List<ItemStack>> inputs = new ArrayList<>();
                    inputs.add(jars);
                    inputs.add(dyes);

                    craftingGridHelper.createAndSetInputs(builder, VanillaTypes.ITEM_STACK, inputs, 0, 0);
                    craftingGridHelper.createAndSetOutputs(builder, VanillaTypes.ITEM_STACK, outputs);
                }
            }

            private List<ItemStack> getAllJars() {
                List<ItemStack> jars = new ArrayList<>();
                jars.add(new ItemStack(JarBlock.get(null)));

                for (DyeColor color : DyeColor.values()) {
                    jars.add(new ItemStack(JarBlock.get(color)));
                }

                return jars;
            }
        };
    }
}






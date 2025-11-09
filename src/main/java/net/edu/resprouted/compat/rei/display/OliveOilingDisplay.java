package net.edu.resprouted.compat.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.edu.resprouted.compat.rei.category.OliveOilingCategory;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.util.RecipeUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OliveOilingDisplay  extends BasicDisplay {
    public OliveOilingDisplay() {
        super(
                getInputs(),
                getOutputs()
        );
    }

    private static List<EntryIngredient> getInputs() {
        List<Item> validFoods = getValidFoods();
        List<EntryIngredient> inputs = new ArrayList<>();

        if (!validFoods.isEmpty()) {
            List<ItemStack> foodStacks = validFoods.stream()
                    .map(ItemStack::new)
                    .collect(Collectors.toList());
            inputs.add(EntryIngredients.ofItemStacks(foodStacks));
        }

        inputs.add(EntryIngredients.of(ModItems.OLIVE_OIL_BOTTLE));

        return inputs;
    }

    private static List<EntryIngredient> getOutputs() {
        List<ItemStack> oiledFoods = getOiledFoods();
        return List.of(EntryIngredients.ofItemStacks(oiledFoods));
    }

    private static List<Item> getValidFoods() {
        return Registries.ITEM.stream()
                .filter(item -> RecipeUtils.isValidFood(new ItemStack(item)))
                .collect(Collectors.toList());
    }

    private static List<ItemStack> getOiledFoods() {
        return getValidFoods().stream()
                .map(item -> {
                    ItemStack oiledStack = new ItemStack(item);
                    oiledStack.set(ModDataComponentTypes.OLIVE_OILED, true);
                    return oiledStack;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return OliveOilingCategory.ID;
    }
}
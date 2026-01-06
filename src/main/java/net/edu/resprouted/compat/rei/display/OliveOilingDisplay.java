package net.edu.resprouted.compat.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.edu.resprouted.compat.rei.category.OliveOilingCategory;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class OliveOilingDisplay extends BasicDisplay {
    public OliveOilingDisplay(Item foodItem) {
        super(getInputs(foodItem), getOutputs(foodItem));
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return OliveOilingCategory.ID;
    }

    private static List<EntryIngredient> getInputs(Item foodItem) {
        List<EntryIngredient> inputs = new ArrayList<>();
        inputs.add(EntryIngredients.of(foodItem));
        inputs.add(EntryIngredients.of(ModItems.OLIVE_OIL_BOTTLE));

        return inputs;
    }

    private static List<EntryIngredient> getOutputs(Item foodItem) {
        ItemStack oiledStack = new ItemStack(foodItem);
        oiledStack.set(ModDataComponentTypes.OLIVE_OILED, true);
        return List.of(EntryIngredients.of(oiledStack));
    }
}
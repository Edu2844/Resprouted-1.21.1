package net.edu.resprouted.compat.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.edu.resprouted.block.custom.decorative.JarBlock;
import net.edu.resprouted.compat.rei.category.JarColoringCategory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;

import java.util.ArrayList;
import java.util.List;

public class JarColoringDisplay extends BasicDisplay {
    public JarColoringDisplay(DyeColor color) {
        super(getInputs(color), getOutputs(color));
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return JarColoringCategory.ID;
    }

    private static List<EntryIngredient> getInputs(DyeColor color) {
        List<EntryIngredient> inputs = new ArrayList<>();

        List<EntryStack<?>> allJars = new ArrayList<>();
        allJars.add(EntryStacks.of(new ItemStack(JarBlock.get(null))));
        for (DyeColor c : DyeColor.values()) {
            allJars.add(EntryStacks.of(new ItemStack(JarBlock.get(c))));
        }

        inputs.add(EntryIngredient.of(allJars));
        inputs.add(EntryIngredients.of(DyeItem.byColor(color)));
        return inputs;
    }

    private static List<EntryIngredient> getOutputs(DyeColor color) {
        return List.of(EntryIngredients.of(new ItemStack(JarBlock.get(color))));
    }
}
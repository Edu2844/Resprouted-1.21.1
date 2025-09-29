package net.edu.resprouted.compat.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.edu.resprouted.compat.rei.category.CondenserCategory;
import net.edu.resprouted.recipe.custom.CondenserRecipe;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.ArrayList;
import java.util.List;

public class CondenserDisplay extends BasicDisplay {
    private final RegistryEntry<StatusEffect> effect;
    private final int duration;
    private final int amplifier;

    public CondenserDisplay(RecipeEntry<CondenserRecipe> entry) {
        super(createInputEntries(entry.value()),
                List.of(EntryIngredient.of(EntryStacks.of(entry.value().getResult(null))))
        );
        this.effect = entry.value().effect();
        this.duration = entry.value().duration();
        this.amplifier = entry.value().amplifier();
    }
    private static List<EntryIngredient> createInputEntries(CondenserRecipe recipe) {
        List<EntryIngredient> inputs = new ArrayList<>();

        for (Ingredient ingredient : recipe.ingredients()) {
            inputs.add(EntryIngredients.ofIngredient(ingredient));
        }

        return inputs;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CondenserCategory.ID;
    }

    public RegistryEntry<StatusEffect> getEffect() {
        return effect;
    }

    public int getDuration() {
        return duration;
    }

    public int getAmplifier() {
        return amplifier;
    }
}

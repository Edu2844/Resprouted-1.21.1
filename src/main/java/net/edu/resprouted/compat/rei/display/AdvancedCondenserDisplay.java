package net.edu.resprouted.compat.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.edu.resprouted.compat.rei.category.AdvancedCondenserCategory;
import net.edu.resprouted.recipe.custom.AdvancedCondenserRecipe;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdvancedCondenserDisplay extends BasicDisplay {
    private final RegistryEntry<StatusEffect> effect;
    private final int duration;
    private final int amplifier;
    private final Ingredient modifier;

    public AdvancedCondenserDisplay(RecipeEntry<AdvancedCondenserRecipe> entry) {
        super(createInputEntries(entry.value()),
                List.of(EntryIngredients.ofItemStacks(
                        List.of(entry.value().getResult(null))
                ))
        );
        this.effect = entry.value().effect();
        this.duration = entry.value().duration();
        this.amplifier = entry.value().amplifier();
        this.modifier = entry.value().modifier().orElse(null);
    }

    private static List<EntryIngredient> createInputEntries(AdvancedCondenserRecipe recipe) {
        List<EntryIngredient> inputs = new ArrayList<>();

        for (Ingredient ingredient : recipe.ingredients()) {
            inputs.add(EntryIngredients.ofIngredient(ingredient));
        }

        recipe.modifier().ifPresent(mod -> inputs.add(EntryIngredients.ofIngredient(mod)));

        return inputs;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return AdvancedCondenserCategory.ID;
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

    public Optional<Ingredient> getModifier() {
        return Optional.ofNullable(modifier);
    }
}

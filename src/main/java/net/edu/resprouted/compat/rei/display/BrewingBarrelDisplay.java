package net.edu.resprouted.compat.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.edu.resprouted.compat.rei.category.BrewingBarrelCategory;
import net.edu.resprouted.recipe.custom.BrewingBarrelRecipe;
import net.minecraft.fluid.Fluid;

import java.util.List;

public class BrewingBarrelDisplay extends BasicDisplay {
    private final Fluid inputFluid;
    private final Fluid outputFluid;

    public BrewingBarrelDisplay(BrewingBarrelRecipe recipe) {
        super(List.of(EntryIngredients.of(recipe.inputFluid(), 81000), EntryIngredients.of(recipe.outputFluid(), 1000)),
                List.of(EntryIngredient.of(EntryStacks.of(recipe.outputFluid(), 81000)))
        );
        this.inputFluid = recipe.inputFluid();
        this.outputFluid = recipe.outputFluid();
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return BrewingBarrelCategory.ID;
    }

    public Fluid getInputFluid() {
        return inputFluid;
    }

    public Fluid getOutputFluid() {
        return outputFluid;
    }
}

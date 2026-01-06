package net.edu.resprouted.compat.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.edu.resprouted.compat.rei.category.CrushingTubCategory;
import net.edu.resprouted.recipe.custom.CrushingTubRecipe;
import net.edu.resprouted.recipe.helper.RecipeOutput;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.recipe.RecipeEntry;

import java.util.List;

public class CrushingTubDisplay extends BasicDisplay {
    private final FluidVariant fluid;
    private final long amount;
    private final List<RecipeOutput> outputs;

    public CrushingTubDisplay(RecipeEntry<CrushingTubRecipe> entry) {
        super(
                List.of(EntryIngredients.ofIngredient(entry.value().inputItem())),
                List.of()
        );
        this.outputs = entry.value().outputItems();
        this.fluid = entry.value().fluidOutput();
        this.amount = entry.value().fluidAmount();
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CrushingTubCategory.ID;
    }

    public FluidVariant getFluid() {
        return fluid;
    }

    public long getAmount() {
        return amount;
    }

    public List<RecipeOutput> getOutputs() {
        return outputs;
    }
}
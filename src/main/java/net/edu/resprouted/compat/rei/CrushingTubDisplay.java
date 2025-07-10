package net.edu.resprouted.compat.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.edu.resprouted.recipe.custom.CrushingTubRecipe;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.recipe.RecipeEntry;

import java.util.List;

public class CrushingTubDisplay extends BasicDisplay {
    private final FluidVariant fluid;
    private final long amount;

    public CrushingTubDisplay(RecipeEntry<CrushingTubRecipe> entry) {
        super(
                List.of(EntryIngredients.ofIngredient(entry.value().inputItem())),
                entry.value().outputItem() != null
                        ? List.of(EntryIngredient.of(EntryStacks.of(entry.value().outputItem())))
                        : List.of()
        );
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
}

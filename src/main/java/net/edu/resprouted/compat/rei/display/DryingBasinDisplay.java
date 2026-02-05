package net.edu.resprouted.compat.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.edu.resprouted.compat.rei.category.DryingBasinCategory;
import net.edu.resprouted.recipe.custom.DryingBasinRecipe;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.recipe.RecipeEntry;

import java.util.List;

public class DryingBasinDisplay extends BasicDisplay {
    private final FluidVariant fluid;
    private final long amount;
    private final int craftTime;

    public DryingBasinDisplay(RecipeEntry<DryingBasinRecipe> entry) {
        super(List.of(EntryIngredients.of(entry.value().fluidInput().getFluid(), entry.value().fluidCost())), List.of(EntryIngredient.of(EntryStacks.of(entry.value().output()))));
        this.fluid = entry.value().fluidInput();
        this.amount = entry.value().fluidCost();
        this.craftTime = entry.value().craftTime();
    }

    public static DryingBasinDisplay of(RecipeEntry<DryingBasinRecipe> entry) {
        return new DryingBasinDisplay(entry);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return DryingBasinCategory.ID;
    }

    public FluidVariant getFluid() {
        return fluid;
    }

    public long getAmount() {
        return amount;
    }

    public int getCraftTime() {
        return craftTime;
    }
}

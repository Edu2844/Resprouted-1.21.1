package net.edu.resprouted.compat.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.recipe.custom.CrushingTubRecipe;

public class REIClient implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CrushingTubCategory());
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(CrushingTubRecipe.class, ModRecipes.CRUSHING_TUB_TYPE, CrushingTubDisplay::new);
    }
}

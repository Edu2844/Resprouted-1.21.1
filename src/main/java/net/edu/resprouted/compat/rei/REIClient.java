package net.edu.resprouted.compat.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import net.edu.resprouted.compat.rei.category.CrushingTubCategory;
import net.edu.resprouted.compat.rei.category.EvaporatingBasinCategory;
import net.edu.resprouted.compat.rei.display.CrushingTubDisplay;
import net.edu.resprouted.compat.rei.display.EvaporatingBasinDisplay;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.recipe.custom.CrushingTubRecipe;
import net.edu.resprouted.recipe.custom.EvaporatingBasinRecipe;

public class REIClient implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CrushingTubCategory());
        registry.add(new EvaporatingBasinCategory());
    }
    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(CrushingTubRecipe.class, ModRecipes.CRUSHING_TUB_TYPE, CrushingTubDisplay::new);
        registry.registerRecipeFiller(EvaporatingBasinRecipe.class, ModRecipes.EV_BASIN_TYPE, EvaporatingBasinDisplay::new);
    }
}

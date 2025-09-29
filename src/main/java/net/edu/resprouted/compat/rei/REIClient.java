package net.edu.resprouted.compat.rei;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.compat.rei.category.*;
import net.edu.resprouted.compat.rei.display.*;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.recipe.custom.*;
import net.edu.resprouted.screen.custom.AdvancedCondenserScreen;
import net.edu.resprouted.screen.custom.BasicCondenserScreen;
import net.edu.resprouted.screen.custom.BrewingBarrelScreen;

public class REIClient implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CrushingTubCategory());
        registry.add(new EvaporatingBasinCategory());
        registry.add(new CondenserCategory());
        registry.add(new AdvancedCondenserCategory());
        registry.add(new BrewingBarrelCategory());

        registry.addWorkstations(CondenserCategory.ID, EntryStacks.of(ModBlocks.CONDENSER));
        registry.addWorkstations(AdvancedCondenserCategory.ID, EntryStacks.of(ModBlocks.ADVANCED_CONDENSER));
        registry.addWorkstations(BrewingBarrelCategory.ID, EntryStacks.of(ModBlocks.BREWING_BARREL));

    }
    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(CrushingTubRecipe.class, ModRecipes.CRUSHING_TUB_TYPE, CrushingTubDisplay::new);
        registry.registerRecipeFiller(EvaporatingBasinRecipe.class, ModRecipes.EV_BASIN_TYPE, EvaporatingBasinDisplay::new);
        registry.registerRecipeFiller(CondenserRecipe.class, ModRecipes.CONDENSER_TYPE, CondenserDisplay::new);
        registry.registerRecipeFiller(AdvancedCondenserRecipe.class, ModRecipes.ADVANCED_CONDENSER_TYPE, AdvancedCondenserDisplay::new);

        BrewingBarrelRecipe.RECIPES.forEach(recipe -> registry.add(new BrewingBarrelDisplay(recipe)));
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerContainerClickArea(
                new Rectangle(60, 38, 30, 8),
                BasicCondenserScreen.class,
                CondenserCategory.ID
        );

        registry.registerContainerClickArea(
                new Rectangle(60, 38, 30, 8),
                AdvancedCondenserScreen.class,
                AdvancedCondenserCategory.ID
        );

        registry.registerContainerClickArea(
                new Rectangle(85, 39, 21, 7),
                BrewingBarrelScreen.class,
                BrewingBarrelCategory.ID
        );
    }
}

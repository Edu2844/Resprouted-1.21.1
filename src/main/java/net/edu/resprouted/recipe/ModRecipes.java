package net.edu.resprouted.recipe;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.recipe.custom.CrushingTubRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static final RecipeSerializer<CrushingTubRecipe> CRUSHING_TUB_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(Resprouted.MOD_ID, "crushing_tub"),
            new CrushingTubRecipe.CrushingTubRecipeSerializer());

    public static final RecipeType<CrushingTubRecipe> CRUSHING_TUB_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(Resprouted.MOD_ID, "crushing_tub"),
            new RecipeType<CrushingTubRecipe>() {
                @Override
                public String toString() {
                    return "crushing_tub";
                }
            });


    public static void registerRecipes() {
        Resprouted.LOGGER.info("Registering Custom Recipes for " + Resprouted.MOD_ID);
    }
}

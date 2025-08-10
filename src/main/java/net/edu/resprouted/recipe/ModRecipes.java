package net.edu.resprouted.recipe;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.recipe.custom.CondenserRecipe;
import net.edu.resprouted.recipe.custom.CrushingTubRecipe;
import net.edu.resprouted.recipe.custom.EvaporatingBasinRecipe;
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

    public static final RecipeType<EvaporatingBasinRecipe> EV_BASIN_TYPE = Registry.register(
            Registries.RECIPE_TYPE,Identifier.of(Resprouted.MOD_ID,"evaporating_basin"),
            new RecipeType<EvaporatingBasinRecipe>() {
                @Override
                public String toString() {
                    return "evaporating_basin";
                }
            });

    public static final RecipeSerializer<EvaporatingBasinRecipe> EV_BASIN_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(Resprouted.MOD_ID,"evaporating_basin"),
            new EvaporatingBasinRecipe.Serializer());


    public static final RecipeType<CondenserRecipe> CONDENSER_TYPE = Registry.register(
            Registries.RECIPE_TYPE,Identifier.of(Resprouted.MOD_ID,"condenser"),
            new RecipeType<CondenserRecipe>() {
                @Override
                public String toString() {
                    return "condenser";
                }
            });

    public static final RecipeSerializer<CondenserRecipe> CONDENSER_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(Resprouted.MOD_ID,"condenser"),
            new CondenserRecipe.Serializer());

    public static void registerRecipes() {
        Resprouted.LOGGER.info("Registering Custom Recipes for " + Resprouted.MOD_ID);

    }
}

package net.edu.resprouted.recipe;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.recipe.custom.*;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    // Crushing Tub
    public static final RecipeSerializer<CrushingTubRecipe> CRUSHING_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(Resprouted.MOD_ID, "crushing"),
            new CrushingTubRecipe.CrushingTubRecipeSerializer());

    public static final RecipeType<CrushingTubRecipe> CRUSHING_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(Resprouted.MOD_ID, "crushing"),
            new RecipeType<CrushingTubRecipe>() {
                @Override
                public String toString() {
                    return "crushing";
                }
            });

    // Drying Basin
    public static final RecipeSerializer<DryingBasinRecipe> DRYING_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(Resprouted.MOD_ID,"drying"),
            new DryingBasinRecipe.Serializer());

    public static final RecipeType<DryingBasinRecipe> DRYING_TYPE = Registry.register(
            Registries.RECIPE_TYPE,Identifier.of(Resprouted.MOD_ID,"drying"),
            new RecipeType<DryingBasinRecipe>() {
                @Override
                public String toString() {
                    return "drying";
                }
            });

    // Condenser
    public static final RecipeSerializer<BasicCondenserRecipe> CONDENSER_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(Resprouted.MOD_ID,"basic_alchemy"),
            new BasicCondenserRecipe.Serializer());

    public static final RecipeType<BasicCondenserRecipe> CONDENSER_TYPE = Registry.register(
            Registries.RECIPE_TYPE,Identifier.of(Resprouted.MOD_ID,"basic_alchemy"),
            new RecipeType<BasicCondenserRecipe>() {
                @Override
                public String toString() {
                    return "basic_alchemy";
                }
            });

    // Advanced Condenser
    public static final RecipeSerializer<AdvancedCondenserRecipe> ADVANCED_CONDENSER_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(Resprouted.MOD_ID,"advanced_alchemy"),
            new AdvancedCondenserRecipe.Serializer());

    public static final RecipeType<AdvancedCondenserRecipe> ADVANCED_CONDENSER_TYPE = Registry.register(
            Registries.RECIPE_TYPE,Identifier.of(Resprouted.MOD_ID,"advanced_alchemy"),
            new RecipeType<AdvancedCondenserRecipe>() {
                @Override
                public String toString() {
                    return "advanced_alchemy";
                }
            });

    // Oiling
    public static final RecipeSerializer<OliveOilingRecipe> OLIVE_OIL_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(Resprouted.MOD_ID, "olive_oiling"),
                    new OliveOilingRecipe.OilingRecipeSerializer());

    // Vanta Oiling
    public static final RecipeSerializer<VantaOilingRecipe> VANTA_OIL_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(Resprouted.MOD_ID, "vanta_oiling"),
                    new VantaOilingRecipe.VantaOilRecipeSerializer());

    public static void registerRecipes() {
        Resprouted.LOGGER.info("Registering Custom Recipes for " + Resprouted.MOD_ID);
    }
}

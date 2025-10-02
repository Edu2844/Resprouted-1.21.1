package net.edu.resprouted.compat.jei.recipes;

import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.recipe.custom.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;

import java.util.ArrayList;
import java.util.List;

public class ResproutedJEIRecipes {

    public static List<CrushingTubRecipe> getCrushingTubRecipes() {
        if (MinecraftClient.getInstance().world == null) {
            return List.of();
        }

        RecipeManager recipeManager = MinecraftClient.getInstance().world.getRecipeManager();
        List<RecipeEntry<CrushingTubRecipe>> crushingRecipeEntries =
                recipeManager.listAllOfType(ModRecipes.CRUSHING_TUB_TYPE);

        return crushingRecipeEntries.stream()
                .map(RecipeEntry::value)
                .toList();
    }

    public static List<EvaporatingBasinRecipe> getEvaporatingBasinRecipes() {
        if (MinecraftClient.getInstance().world == null) {
            return List.of();
        }

        RecipeManager recipeManager = MinecraftClient.getInstance().world.getRecipeManager();
        List<RecipeEntry<EvaporatingBasinRecipe>> evaporatingRecipeEntries =
                recipeManager.listAllOfType(ModRecipes.EV_BASIN_TYPE);

        return evaporatingRecipeEntries.stream()
                .map(RecipeEntry::value)
                .toList();
    }


    public static List<CondenserRecipe> getCondenserRecipes() {
        if (MinecraftClient.getInstance().world == null) {
            return List.of();
        }
        RecipeManager recipeManager = MinecraftClient.getInstance().world.getRecipeManager();
        List<RecipeEntry<CondenserRecipe>> condenserRecipeEntries =
                recipeManager.listAllOfType(ModRecipes.CONDENSER_TYPE);
        return condenserRecipeEntries.stream()
                .map(RecipeEntry::value)
                .toList();
    }

    public static List<AdvancedCondenserRecipe> getAdvancedCondenserRecipes() {
        if (MinecraftClient.getInstance().world == null) {
            return List.of();
        }
        RecipeManager recipeManager = MinecraftClient.getInstance().world.getRecipeManager();
        List<RecipeEntry<AdvancedCondenserRecipe>> advancedCondenserRecipeEntries =
                recipeManager.listAllOfType(ModRecipes.ADVANCED_CONDENSER_TYPE);
        return advancedCondenserRecipeEntries.stream()
                .map(RecipeEntry::value)
                .toList();
    }

    public static List<BrewingBarrelRecipe> getBrewingBarrelRecipes() {
        return new ArrayList<>(BrewingBarrelRecipe.RECIPES);
    }
}
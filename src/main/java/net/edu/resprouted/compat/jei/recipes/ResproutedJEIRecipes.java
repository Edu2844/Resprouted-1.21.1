package net.edu.resprouted.compat.jei.recipes;

import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.recipe.custom.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import java.util.ArrayList;
import java.util.List;

public class ResproutedJEIRecipes {

    public static List<CrushingTubRecipe> getCrushingRecipes() {
        if (MinecraftClient.getInstance().world == null) {
            return List.of();
        }

        RecipeManager recipeManager = MinecraftClient.getInstance().world.getRecipeManager();
        List<RecipeEntry<CrushingTubRecipe>> crushingRecipeEntries = recipeManager.listAllOfType(ModRecipes.CRUSHING_TYPE);

        return crushingRecipeEntries.stream().map(RecipeEntry::value).toList();
    }

    public static List<DryingBasinRecipe> getDryingRecipes() {
        if (MinecraftClient.getInstance().world == null) {
            return List.of();
        }

        RecipeManager recipeManager = MinecraftClient.getInstance().world.getRecipeManager();
        List<RecipeEntry<DryingBasinRecipe>> DryingRecipeEntries = recipeManager.listAllOfType(ModRecipes.DRYING_TYPE);

        return DryingRecipeEntries.stream().map(RecipeEntry::value).toList();
    }

    public static List<BasicCondenserRecipe> getBasicAlchemyRecipes() {
        if (MinecraftClient.getInstance().world == null) {
            return List.of();
        }
        RecipeManager recipeManager = MinecraftClient.getInstance().world.getRecipeManager();
        List<RecipeEntry<BasicCondenserRecipe>> condenserRecipeEntries = recipeManager.listAllOfType(ModRecipes.CONDENSER_TYPE);

        return condenserRecipeEntries.stream().map(RecipeEntry::value).toList();
    }

    public static List<AdvancedCondenserRecipe> getAdvancedAlchemyRecipes() {
        if (MinecraftClient.getInstance().world == null) {
            return List.of();
        }
        RecipeManager recipeManager = MinecraftClient.getInstance().world.getRecipeManager();
        List<RecipeEntry<AdvancedCondenserRecipe>> advancedCondenserRecipeEntries = recipeManager.listAllOfType(ModRecipes.ADVANCED_CONDENSER_TYPE);

        return advancedCondenserRecipeEntries.stream().map(RecipeEntry::value).toList();
    }

    public static List<BrewingBarrelRecipe> getBrewingRecipes() {
        return new ArrayList<>(BrewingBarrelRecipe.RECIPES);
    }
}
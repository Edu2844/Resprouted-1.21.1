package net.edu.resprouted.recipe.custom;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.util.FluidUtils;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.math.random.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record BrewingBarrelRecipe(Fluid outputFluid, Fluid inputFluid) {
    public static final List<BrewingBarrelRecipe> RECIPES = new ArrayList<>();

    public boolean matches(FluidVariant input, long inputAmount, FluidVariant aux, long auxAmount) {
        if (inputFluid == null || input == null || input.isBlank() || inputAmount <= 0) {
            return false;
        }

        boolean inputMatches = input.getFluid() == inputFluid;

        if (aux != null && !aux.isBlank() && auxAmount > 0) {
            boolean auxMatches = (aux.getFluid() == outputFluid);
            return inputMatches && auxMatches;
        }

        return inputMatches;
    }

    public FluidVariant getResult(FluidVariant input, long inputAmount, FluidVariant aux, long auxAmount, Random random) {
        if (aux == null || aux.isBlank() || auxAmount <= 0 || !matches(input, inputAmount, aux, auxAmount)) {
            return getResult(input, inputAmount, random);
        }

        if (matches(input, inputAmount, aux, auxAmount) && outputFluid != null) {
            if (FluidUtils.hasQuality(aux)) {
                float auxQuality = FluidUtils.getQuality(aux);
                int minChange = getMinBrewQualityChange();
                int maxChange = getMaxBrewQualityChange();
                if (maxChange < minChange) maxChange = minChange;

                int brewQualityChange = random.nextInt((maxChange - minChange) + 1) + minChange;
                float quality = Math.max(Math.min(((brewQualityChange + (int) (100 * auxQuality)) / 100F), 1), 0);

                return FluidUtils.withQuality(outputFluid, quality);
            } else {
                return FluidUtils.withQuality(outputFluid, getBaseQuality(random));
            }
        }
        return null;
    }

    public FluidVariant getResult(FluidVariant input, long inputAmount, Random random) {
        if (matches(input, inputAmount, null, 0) && outputFluid != null) {
            return FluidUtils.withQuality(outputFluid, getBaseQuality(random));
        }
        return null;
    }

    private float getBaseQuality(Random random) {
        return (5 + random.nextInt(71)) / 100F;
    }

    public static void register(BrewingBarrelRecipe recipe) {
        RECIPES.add(recipe);
    }

    public static int getMinBrewQualityChange() {
        return Resprouted.COMMON_CONFIG.brewing.getMinBrewQualityChange();
    }

    public static int getMaxBrewQualityChange() {
        return Resprouted.COMMON_CONFIG.brewing.getMaxBrewQualityChange();
    }

    public static Optional<BrewingBarrelRecipe> findMatchingRecipe(FluidVariant input, long inputAmount, FluidVariant auxiliary, long auxAmount) {
        return RECIPES.stream()
                .filter(recipe -> recipe.matches(input, inputAmount, auxiliary, auxAmount))
                .findFirst();
    }

    public static List<BrewingBarrelRecipe> getRecipes() {
        return new ArrayList<>(RECIPES);
    }
}
package net.edu.resprouted.recipe.custom;

import dev.architectury.fluid.FluidStack;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.util.FluidUtils;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.math.random.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record BrewingBarrelRecipe(Fluid outputFluid, Fluid inputFluid) {
    public static final List<BrewingBarrelRecipe> RECIPES = new ArrayList<>();

    public boolean matches(FluidStack in, FluidStack aux) {
        if (inputFluid != null && in != null && in.getFluid() != null) {
            boolean inputMatches = in.getFluid() == inputFluid;

            if (aux != null && aux.getFluid() != null) {
                boolean auxMatches = (aux.getFluid() == outputFluid);
                return inputMatches && auxMatches;
            }

            return inputMatches;
        }

        return false;
    }

    public FluidStack getResult(FluidStack in, FluidStack aux, Random random) {
        if (aux == null || aux.getFluid() == null || !matches(in, aux)) {
            return getResult(in, random);
        }

        if (matches(in, aux) && outputFluid != null) {
            FluidStack out = FluidStack.create(outputFluid, in.getAmount());

            if (FluidUtils.hasQuality(aux)) {

                float auxQuality = FluidUtils.getQuality(aux);
                int minChange = getMinBrewQualityChange();
                int maxChange = getMaxBrewQualityChange();
                if (maxChange < minChange) maxChange = minChange;

                int brewQualityChange = random.nextInt((maxChange - minChange) + 1) + minChange;

                float quality = Math.max(Math.min(((brewQualityChange + (int) (100 * auxQuality)) / 100F), 1), 0);

                FluidUtils.setQuality(out, quality);

            } else {
                FluidUtils.setQuality(out, getBaseQuality(random));
            }

            return out;
        }

        return null;
    }

    public FluidStack getResult(FluidStack in, Random random) {

        if (matches(in, null) && outputFluid != null) {
            FluidStack out = FluidStack.create(outputFluid, in.getAmount());
            FluidUtils.setQuality(out, getBaseQuality(random));

            return out;
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
        return Resprouted.CONFIG.getMinBrewQualityChange();
    }

    public static int getMaxBrewQualityChange() {
        return Resprouted.CONFIG.getMaxBrewQualityChange();
    }

    public static Optional<BrewingBarrelRecipe> findMatchingRecipe(FluidStack input, FluidStack auxiliary) {
        return RECIPES.stream()
                .filter(recipe -> recipe.matches(input, auxiliary))
                .findFirst();
    }

    public static List<BrewingBarrelRecipe> getRecipes() {
        return new ArrayList<>(RECIPES);
    }

}

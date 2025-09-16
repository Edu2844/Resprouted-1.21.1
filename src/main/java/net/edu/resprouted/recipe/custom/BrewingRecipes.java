package net.edu.resprouted.recipe.custom;

import dev.architectury.fluid.FluidStack;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.util.FluidQualityHelper;
import net.minecraft.util.math.random.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BrewingRecipes {
    public static final List<BrewingRecipe> RECIPES = new ArrayList<>();

    public static void registerRecipes() {
        register(new BrewingRecipe(
                FluidStack.create(ModFluids.ALE_STILL, 81000),
                FluidStack.create(ModFluids.ALE_WORT_STILL, 81000)
        ));
        register(new BrewingRecipe(
                FluidStack.create(ModFluids.IRON_WINE_STILL, 81000),
                FluidStack.create(ModFluids.IRON_BERRY_JUICE_STILL, 81000)
        ));
    }

    public static void register(BrewingRecipe recipe) {
        RECIPES.add(recipe);
    }
    public static int getMinBrewQualityChange() {
        return Resprouted.CONFIG.getMinBrewQualityChange();
    }

    public static int getMaxBrewQualityChange() {
        return Resprouted.CONFIG.getMaxBrewQualityChange();
    }
    public static Optional<BrewingRecipe> findMatchingRecipe(FluidStack input, FluidStack auxiliary) {
        return RECIPES.stream()
                .filter(recipe -> recipe.matches(input, auxiliary))
                .findFirst();
    }

    public record BrewingRecipe(FluidStack output, FluidStack input) {

        public boolean matches(FluidStack in, FluidStack aux) {

            if (input != null && input.getFluid() != null && in != null && in.getFluid() != null) {
                boolean inputMatches = in.getFluid() == input.getFluid();

                if (aux != null && aux.getFluid() != null) {
                    assert output != null;
                    boolean auxMatches = (aux.getFluid() == output.getFluid());

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

            if (matches(in, aux) && output != null && output.getFluid() != null) {
                FluidStack out = output.copy();
                out.setAmount(in.getAmount());

                if (FluidQualityHelper.hasQuality(aux)) {
                    float auxQuality = FluidQualityHelper.getQuality(aux);

                    int minChange = getMinBrewQualityChange();
                    int maxChange = getMaxBrewQualityChange();

                    if (maxChange < minChange) {
                        maxChange = minChange;
                    }

                    int brewQualityChange = random.nextInt((maxChange - minChange) + 1) + minChange;

                    float quality = Math.max(Math.min(
                            ((brewQualityChange + (int) (100 * auxQuality)) / 100F), 1), 0
                    );

                    FluidQualityHelper.setQuality(out, quality);
                } else {
                    FluidQualityHelper.setQuality(out, getBaseQuality(random));
                }
                return out;
            }
            return null;
        }

        public FluidStack getResult(FluidStack in, Random random) {
            if (matches(in, null) && output != null && output.getFluid() != null) {
                FluidStack out = output.copy();
                out.setAmount(in.getAmount());
                FluidQualityHelper.setQuality(out, getBaseQuality(random));
                return out;
            }
            return null;
        }

        private float getBaseQuality(Random random) {
            return (5 + random.nextInt(71)) / 100F;
        }

        @Override
        public FluidStack input() {
            return input.copy();
        }

        @Override
        public FluidStack output() {
            return output.copy();
        }
    }
}


package net.edu.resprouted.util;

import dev.architectury.fluid.FluidStack;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.minecraft.fluid.Fluid;

public class FluidUtils {
    private static final float DEFAULT_QUALITY = 0f;

    public static long convertDropletsToMb(long droplets) {
        return (droplets / 81);
    }

    public static long convertMbToDroplets(long mb) {
        return mb * 81;
    }

    // Booze Fluids
    public static float getQuality(FluidStack stack) {
        if (stack == null || stack.isEmpty()) return DEFAULT_QUALITY;
        Float quality = stack.get(ModDataComponentTypes.FLUID_QUALITY);
        return quality != null ? quality : DEFAULT_QUALITY;
    }

    public static void setQuality(FluidStack stack, float quality) {
        if (stack != null && !stack.isEmpty()) {
            stack.set(ModDataComponentTypes.FLUID_QUALITY,
                    Math.max(0f, Math.min(1f, quality)));
        }
    }

    public static FluidStack withQuality(Fluid fluid, long amount, float quality) {
        FluidStack stack = FluidStack.create(fluid, amount);
        setQuality(stack, quality);
        return stack;
    }

    public static boolean hasQuality(FluidStack stack) {
        return stack != null && !stack.isEmpty() &&
                stack.get(ModDataComponentTypes.FLUID_QUALITY) != null;
    }
}

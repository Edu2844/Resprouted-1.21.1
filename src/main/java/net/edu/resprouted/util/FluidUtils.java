package net.edu.resprouted.util;

import net.edu.resprouted.component.ModDataComponentTypes;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.component.ComponentChanges;
import net.minecraft.fluid.Fluid;

import java.util.Optional;

public class FluidUtils {
    private static final float DEFAULT_QUALITY = 0f;

    public static long convertDropletsToMb(long droplets) {
        return (droplets / 81);
    }

    public static long convertMbToDroplets(long mb) {
        return mb * 81;
    }

    // Booze Fluids
    public static float getQuality(FluidVariant variant) {
        if (variant == null || variant.isBlank()) return DEFAULT_QUALITY;

        Optional<? extends Float> qualityOpt = variant.getComponents().get(ModDataComponentTypes.FLUID_QUALITY);

        if (qualityOpt != null && qualityOpt.isPresent()) {
            return qualityOpt.get();
        }

        return DEFAULT_QUALITY;
    }

    public static FluidVariant withQuality(Fluid fluid, float quality) {
        ComponentChanges.Builder builder = ComponentChanges.builder();
        builder.add(ModDataComponentTypes.FLUID_QUALITY,
                Math.max(0f, Math.min(1f, quality)));
        return FluidVariant.of(fluid, builder.build());
    }

    public static FluidVariant withQuality(FluidVariant variant, float quality) {
        ComponentChanges.Builder builder = ComponentChanges.builder();
        builder.add(ModDataComponentTypes.FLUID_QUALITY,
                Math.max(0f, Math.min(1f, quality)));
        return variant.withComponentChanges(builder.build());
    }

    public static boolean hasQuality(FluidVariant variant) {
        if (variant == null || variant.isBlank()) return false;

        Optional<? extends Float> qualityOpt = variant.getComponents().get(ModDataComponentTypes.FLUID_QUALITY);
        return qualityOpt != null && qualityOpt.isPresent();
    }
}
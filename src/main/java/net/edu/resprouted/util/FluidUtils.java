package net.edu.resprouted.util;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

public class FluidUtils {
    public FluidVariant fluidVariant;
    public long amount;

    public FluidUtils(FluidVariant variant, long amount) {
        this.fluidVariant = variant;
        this.amount = amount;
    }
    public static long convertDropletsToMb(long droplets) {
        return (droplets / 81);
    }
    public static long convertMbToDroplets(long mb) {
        return mb * 81;
    }
}

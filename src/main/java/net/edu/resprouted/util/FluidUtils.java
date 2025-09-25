package net.edu.resprouted.util;

public class FluidUtils {

    public static long convertDropletsToMb(long droplets) {
        return (droplets / 81);
    }
    public static long convertMbToDroplets(long mb) {
        return mb * 81;
    }
}

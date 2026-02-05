package net.edu.resprouted.compat;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.compat.farmersdelight.FarmersDelightItems;

public class ModCompat {
    public static void initialize() {
        if (Resprouted.isModLoaded(Resprouted.FARMERS_DELIGHT_MOD_ID)) {
            FarmersDelightItems.initialize();
        }
    }
}

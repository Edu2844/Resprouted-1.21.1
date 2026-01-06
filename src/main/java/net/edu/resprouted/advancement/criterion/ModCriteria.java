package net.edu.resprouted.advancement.criterion;

import net.edu.resprouted.Resprouted;
import net.minecraft.advancement.criterion.Criteria;

public class ModCriteria {
    public static final FluidQualityInventoryCriterion FLUID_QUALITY_INVENTORY =
            Criteria.register("fluid_quality_inventory", new FluidQualityInventoryCriterion());

    public static void registerCriteria(){
        Resprouted.LOGGER.info("Registering Criteria for " + Resprouted.MOD_ID);
    }
}
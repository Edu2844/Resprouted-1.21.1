package net.edu.resprouted.world;

import net.edu.resprouted.Resprouted;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class ModFoliagePlacerTypes {


    private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String name, FoliagePlacerType<P> type) {
        return Registry.register(Registries.FOLIAGE_PLACER_TYPE,
                Identifier.of(Resprouted.MOD_ID, name),
                type);
    }
    public static void registerModFoliagePlacers() {
        Resprouted.LOGGER.info("Registering Foliage Placers for " + Resprouted.MOD_ID);
    }
}

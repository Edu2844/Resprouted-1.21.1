package net.edu.resprouted.world;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.world.tree.CustomIronWoodFoliagePlacer;
import net.edu.resprouted.world.tree.CustomOliveFoliagePlacer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class ModFoliagePlacerTypes {
    public static final FoliagePlacerType<CustomOliveFoliagePlacer> CUSTOM_OLIVE_PLACER =
            register("custom_olive_placer", new FoliagePlacerType<>(CustomOliveFoliagePlacer.CODEC));

    public static final FoliagePlacerType<CustomIronWoodFoliagePlacer> CUSTOM_IRONWOOD_PLACER =
            register("custom_ironwood_placer", new FoliagePlacerType<>(CustomIronWoodFoliagePlacer.CODEC));

    private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String name, FoliagePlacerType<P> type) {
        return Registry.register(Registries.FOLIAGE_PLACER_TYPE,
                Identifier.of(Resprouted.MOD_ID, name),
                type);
    }
    public static void registerModFoliagePlacers() {
        Resprouted.LOGGER.info("Registering Foliage Placers for " + Resprouted.MOD_ID);
    }
}

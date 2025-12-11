package net.edu.resprouted.world;

import net.edu.resprouted.Resprouted;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class ModTrunkPlacerTypes {

    private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String name, TrunkPlacerType<P> type) {
        return Registry.register(Registries.TRUNK_PLACER_TYPE,
                Identifier.of(Resprouted.MOD_ID, name), type);
    }

    public static void registerModTrunkPlacers() {
        Resprouted.LOGGER.info("Registering Trunk Placers for " + Resprouted.MOD_ID);
    }
}
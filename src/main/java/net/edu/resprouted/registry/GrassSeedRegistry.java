package net.edu.resprouted.registry;

import net.edu.resprouted.item.custom.StakeCropSeedItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrassSeedRegistry {
    private static final List<StakeCropSeedItem> REGISTERED_SEEDS = new ArrayList<>();

    public static void registerSeed(StakeCropSeedItem seed) {
        REGISTERED_SEEDS.add(seed);
    }

    public static List<StakeCropSeedItem> getRegisteredSeeds() {
        return Collections.unmodifiableList(REGISTERED_SEEDS);
    }
}
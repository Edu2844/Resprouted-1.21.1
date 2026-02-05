package net.edu.resprouted.util.misc;

import net.edu.resprouted.item.custom.StakeCropSeedItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrassSeedsRegistry {
    private static final List<StakeCropSeedItem> REGISTERED_SEEDS = new ArrayList<>();

    public static void registerSeed(StakeCropSeedItem seed) {
        if (seed instanceof StakeCropSeedItem){
            REGISTERED_SEEDS.add(seed);
        } else {
            throw new IllegalArgumentException("The item must be an instance of StakeCropSeedItem");
        }

    }

    public static List<StakeCropSeedItem> getRegisteredSeeds() {
        return Collections.unmodifiableList(REGISTERED_SEEDS);
    }
}
package net.edu.resprouted.world;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> IRONWOOD_PLACED_KEY = registerKey("ironwood_placed");

    public static final RegistryKey<PlacedFeature> OLIVE_PLACED_KEY = registerKey("olive_placed");

    public static final RegistryKey<PlacedFeature> ALOE_VERA_PLACED_KEY = registerKey("aloe_vera_placed");

    public static final RegistryKey<PlacedFeature> BLOOD_ORCHID_PLACED_KEY = registerKey("blood_orchid_placed");

    public static final RegistryKey<PlacedFeature> CHAMOMILE_PLACED_KEY = registerKey("chamomile_placed");

    public static final RegistryKey<PlacedFeature> CLOUDSBLUFF_PLACED_KEY = registerKey("cloudsbluff_placed");

    public static final RegistryKey<PlacedFeature> COHOSH_PLACED_KEY = registerKey("cohosh_placed");

    public static final RegistryKey<PlacedFeature> GINSENG_PLACED_KEY = registerKey("ginseng_placed");

    public static final RegistryKey<PlacedFeature> HORSETAIL_PLACED_KEY = registerKey("horsetail_placed");

    public static final RegistryKey<PlacedFeature> MARSHMALLOW_PLACED_KEY = registerKey("marshmalow_placed");

    public static final RegistryKey<PlacedFeature> WIND_THISTLE_PLACED_KEY = registerKey("wind_thistle_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, IRONWOOD_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.IRONWOOD_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(0, 0.05f, 1), ModBlocks.IRONWOOD_SAPLING));

        register(context, OLIVE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OLIVE_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(0, 0.05f, 1), ModBlocks.OLIVE_SAPLING));

        register(context, ALOE_VERA_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.ALOE_VERA_KEY),
                RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        register(context, BLOOD_ORCHID_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOOD_ORCHID_KEY),
                RarityFilterPlacementModifier.of(8), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        register(context, CHAMOMILE_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.CHAMOMILE_KEY),
                RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        register(context, CLOUDSBLUFF_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.CLOUDSBLUFF_KEY),
                RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        register(context, COHOSH_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.COHOSH_KEY),
                RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        register(context, GINSENG_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.GINSENG_KEY),
                RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        register(context, HORSETAIL_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.HORSETAIL_KEY),
                RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        register(context, MARSHMALLOW_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.MARSHMALLOW_KEY),
                RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        register(context, WIND_THISTLE_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.WIND_THISTLE_KEY),
                RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Resprouted.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}

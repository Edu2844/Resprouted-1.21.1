package net.edu.resprouted.world;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

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
    public static final RegistryKey<PlacedFeature> VANTA_LILY_PLACED_KEY = registerKey("vanta_lily_placed");
    public static final RegistryKey<PlacedFeature> MARSHMALLOW_PLACED_KEY = registerKey("marshmalow_placed");
    public static final RegistryKey<PlacedFeature> WIND_THISTLE_PLACED_KEY = registerKey("wind_thistle_placed");
    public static final RegistryKey<PlacedFeature> CORE_ROOT_PLACED_KEY = registerKey("core_root_placed");
    public static final RegistryKey<PlacedFeature> DEATHSTALK_MUSHROOM_PLACED_KEY = registerKey("deathstalk_mushroom_placed");
    public static final RegistryKey<PlacedFeature> MOONCAP_CAVE_PLACED_KEY = registerKey("mooncap_cave_placed");
    public static final RegistryKey<PlacedFeature> MOONCAP_SURFACE_PLACED_KEY = registerKey("mooncap_surface_placed");
    public static final RegistryKey<PlacedFeature> SLATE_PLACED_KEY = registerKey("slate_placed");


    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        // Ironwood
        register(context, IRONWOOD_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.IRONWOOD_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(0, 0.05f, 1), ModBlocks.IRONWOOD_SAPLING));

        // Olive
        register(context, OLIVE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OLIVE_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(0, 0.05f, 1), ModBlocks.OLIVE_SAPLING));

        // Aloe Vera
        register(context, ALOE_VERA_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.ALOE_VERA_KEY),
                RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        // Blood Orchid
        register(context, BLOOD_ORCHID_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOOD_ORCHID_KEY),
                RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        // Chamomile
        register(context, CHAMOMILE_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.CHAMOMILE_KEY),
                RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        // Vanta Lily
        register(context, VANTA_LILY_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.VANTA_LILY_KEY),
                RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        // Cloudsbluff
        register(context, CLOUDSBLUFF_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.CLOUDSBLUFF_KEY),
                RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        // Cohosh
        register(context, COHOSH_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.COHOSH_KEY),
                RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        // Ginseng
        register(context, GINSENG_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.GINSENG_KEY),
                RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        // Horsetail
        register(context, HORSETAIL_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.HORSETAIL_KEY),
                RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        // Marsh Mallow
        register(context, MARSHMALLOW_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.MARSHMALLOW_KEY),
                RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        // Wind Thistle
        register(context, WIND_THISTLE_PLACED_KEY , configuredFeatures.getOrThrow(ModConfiguredFeatures.WIND_THISTLE_KEY),
                RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        // Death Stalk Mushroom
        register(context,
                DEATHSTALK_MUSHROOM_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.DEATHSTALK_MUSHROOM_KEY),
                RarityFilterPlacementModifier.of(2), SquarePlacementModifier.of(),
                PlacedFeatures.BOTTOM_TO_TOP_RANGE,
                BiomePlacementModifier.of()
        );

        // Core Root
        register(context, CORE_ROOT_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CORE_ROOT_KEY),
                CountPlacementModifier.of(10), SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.fixed(-48), YOffset.fixed(52)),
                EnvironmentScanPlacementModifier.of(Direction.UP, BlockPredicate.solid(), 12),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)),
                BiomePlacementModifier.of());

        // Mooncap Caves
        register(context, MOONCAP_CAVE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures. MOONCAP_KEY),
                CountPlacementModifier.of(4),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(35)),
                BiomePlacementModifier.of());

        // Mooncap Plains
        register(context, MOONCAP_SURFACE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures. MOONCAP_KEY),
                RarityFilterPlacementModifier.of(42), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        // Slate
        register(context, SLATE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SLATE_KEY),
                ModOrePlacement.modifiersWithCount(10, HeightRangePlacementModifier.trapezoid(YOffset.fixed(7), YOffset.fixed(80))));

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

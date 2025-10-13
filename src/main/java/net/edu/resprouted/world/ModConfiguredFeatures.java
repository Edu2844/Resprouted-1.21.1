package net.edu.resprouted.world;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.custom.agriculture.CustomMushroomBlock;
import net.edu.resprouted.block.custom.agriculture.HerbBlock;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.foliage.PineFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> IRONWOOD_KEY = registerKey("ironwood");
    public static final RegistryKey<ConfiguredFeature<?, ?>> OLIVE_KEY = registerKey("olive");
    public static final RegistryKey<ConfiguredFeature<?, ?>> APPLE_KEY = registerKey("apple");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ALOE_VERA_KEY = registerKey("aloe_vera_herb");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BLOOD_ORCHID_KEY = registerKey("blood_orchid_herb");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CHAMOMILE_KEY = registerKey("chamomile_herb");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CLOUDSBLUFF_KEY = registerKey("cloudsbluff_herb");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COHOSH_KEY = registerKey("cohosh_herb");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GINSENG_KEY = registerKey("ginseng_herb");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HORSETAIL_KEY = registerKey("horsetail_herb");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MARSHMALLOW_KEY = registerKey("marshmalow_herb");
    public static final RegistryKey<ConfiguredFeature<?, ?>> WIND_THISTLE_KEY = registerKey("wind_thistle_herb");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CORE_ROOT_KEY = registerKey("core_root_herb");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MOONCAP_KEY = registerKey("mooncap_mushroom");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEATHSTALK_MUSHROOM_KEY = registerKey("deathstalk_mushroom");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SLATE_KEY = registerKey("slate");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {

        //Iron Wood Tree
        register(context, IRONWOOD_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.IRONWOOD_LOG),
                new StraightTrunkPlacer(10, 2, 0),
                BlockStateProvider.of(ModBlocks.IRONWOOD_LEAVES),
                new PineFoliagePlacer(ConstantIntProvider.create(3),ConstantIntProvider.create(0), ConstantIntProvider.create(3)),
                new TwoLayersFeatureSize(3, 1, 2)
        ).build());

        //Olive Tree
        register(context, OLIVE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.OLIVE_LOG),
                new LargeOakTrunkPlacer(4, 2, 2),
                BlockStateProvider.of(ModBlocks.OLIVE_LEAVES),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(3),3),
                new TwoLayersFeatureSize(3, 0, 2)
        ).build());

        //Apple Tree
        register(context, APPLE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.OAK_LOG),
                new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.of(ModBlocks.APPLE_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)
        ).build());

        //Aloe Vera
        register(context, ALOE_VERA_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.ALOE_VERA_BLOCK
                                .getDefaultState().with(HerbBlock.AGE, 6))),
                        List.of(Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.RED_SAND)));

        //Blood Orchid
        register(context, BLOOD_ORCHID_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.BLOOD_ORCHID_BLOCK
                                .getDefaultState().with(HerbBlock.AGE, 6))),
                        List.of(Blocks.GRASS_BLOCK)));

        //Chamomile
        register(context, CHAMOMILE_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.CHAMOMILE_BLOCK
                                .getDefaultState().with(HerbBlock.AGE, 6))),
                        List.of(Blocks.GRASS_BLOCK)));

        //Cloudsbluff
        register(context, CLOUDSBLUFF_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.CLOUDSBLUFF_BLOCK
                                .getDefaultState().with(HerbBlock.AGE, 6))),
                        List.of(Blocks.GRASS_BLOCK)));
        //Cohosh
        register(context, COHOSH_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.COHOSH_BLOCK
                                .getDefaultState().with(HerbBlock.AGE, 6))),
                        List.of(Blocks.GRASS_BLOCK)));

        //Ginseng
        register(context, GINSENG_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.GINSENG_BLOCK
                                .getDefaultState().with(HerbBlock.AGE, 6))),
                        List.of(Blocks.GRASS_BLOCK)));

        //Horsetail
        register(context, HORSETAIL_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.HORSETAIL_BLOCK
                                .getDefaultState().with(HerbBlock.AGE, 6))),
                        List.of(Blocks.GRASS_BLOCK)));

        //Marsh Mallow
        register(context, MARSHMALLOW_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.MARSHMALLOW_BLOCK
                                .getDefaultState().with(HerbBlock.AGE, 6))),
                        List.of(Blocks.GRASS_BLOCK)));

        //Wind Thistle
        register(context, WIND_THISTLE_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.WIND_THISTLE_BLOCK
                                .getDefaultState().with(HerbBlock.AGE, 6))),
                        List.of(Blocks.GRASS_BLOCK)));

        //DeathStalk Mushroom
        register(context, DEATHSTALK_MUSHROOM_KEY, Feature.RANDOM_PATCH,
                new RandomPatchFeatureConfig(
                        96,
                        7,
                        3,
                        PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.DEATHSTALK_MUSHROOM
                                        .getDefaultState().with(CustomMushroomBlock.AGE, 3))))
                ));

        //Core Root
        register(context, CORE_ROOT_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.CORE_ROOT
                                .getDefaultState().with(CustomMushroomBlock.AGE, 3))),
                        List.of(Blocks.STONE, Blocks.TUFF, Blocks.ANDESITE, Blocks.DIORITE, Blocks.CLAY, Blocks.DEEPSLATE)));

        //Mooncap Mushroom
        register(context, MOONCAP_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.MOONCAP_MUSHROOM
                                .getDefaultState().with(CustomMushroomBlock.AGE, 3))),
                        List.of(Blocks.GRASS_BLOCK, Blocks.STONE, Blocks.DEEPSLATE, Blocks.TUFF)));

        //Slate
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        List<OreFeatureConfig.Target> limestoneTargets = List.of(
                OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.SLATE.getDefaultState())
        );

        register(context, SLATE_KEY, Feature.ORE, new OreFeatureConfig(limestoneTargets, 33));
    }
    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Resprouted.MOD_ID, name));
    }
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
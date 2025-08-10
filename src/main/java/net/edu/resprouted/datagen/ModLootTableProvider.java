package net.edu.resprouted.datagen;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.custom.agriculture.BlueBerrieBush;
import net.edu.resprouted.block.custom.agriculture.CustomMushroomBlock;
import net.edu.resprouted.block.custom.agriculture.HerbBlock;
import net.edu.resprouted.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);

    }
    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        //SLATE
        addDrop(ModBlocks.SLATE);
        addDrop(ModBlocks.POLISHED_SLATE);
        addDrop(ModBlocks.POLISHED_SLATE_STAIRS);
        addDrop(ModBlocks.POLISHED_SLATE_SLAB);
        addDrop(ModBlocks.POLISHED_SLATE_WALL);
        addDrop(ModBlocks.CHISELED_SLATE);
        addDrop(ModBlocks.SLATE_BRICKS);
        addDrop(ModBlocks.SLATE_BRICK_SLAB);
        addDrop(ModBlocks.SLATE_BRICK_STAIRS);
        addDrop(ModBlocks.SLATE_BRICK_WALL);
        addDrop(ModBlocks.SLATE_ROOFS);
        addDrop(ModBlocks.SLATE_ROOF_SLAB);
        addDrop(ModBlocks.SLATE_ROOF_STAIRS);
        addDrop(ModBlocks.SLATE_PILLAR);

        //STONE
        addDrop(ModBlocks.POLISHED_STONE);
        addDrop(ModBlocks.STONE_PILLAR);

        //ANDESITE
        addDrop(ModBlocks.ANDESITE_BRICKS);
        addDrop(ModBlocks.CHISELED_ANDESITE);
        addDrop(ModBlocks.CRACKED_ANDESITE_BRICKS);
        addDrop(ModBlocks.ANDESITE_PILLAR);

        //DIORITE
        addDrop(ModBlocks.DIORITE_BRICKS);
        addDrop(ModBlocks.CHISELED_DIORITE);
        addDrop(ModBlocks.CRACKED_DIORITE_BRICKS);
        addDrop(ModBlocks.DIORITE_PILLAR);

        //GRANITE
        addDrop(ModBlocks.GRANITE_BRICKS);
        addDrop(ModBlocks.CHISELED_GRANITE);
        addDrop(ModBlocks.CRACKED_GRANITE_BRICKS);
        addDrop(ModBlocks.GRANITE_PILLAR);

        //SANDSTONE
        addDrop(ModBlocks.POLISHED_SANDSTONE);
        addDrop(ModBlocks.SANDSTONE_BRICKS);
        addDrop(ModBlocks.SANDSTONE_PILLAR);

        //RED SANDSTONE
        addDrop(ModBlocks.POLISHED_RED_SANDSTONE);
        addDrop(ModBlocks.RED_SANDSTONE_BRICKS);
        addDrop(ModBlocks.RED_SANDSTONE_PILLAR);

        //WROUGHT IRON
        addDrop(ModBlocks.WROUGHT_IRON_BLOCK);
        addDrop(ModBlocks.CUT_WROUGHT_IRON_BLOCK);

        //CLAY WALL
        addDrop(ModBlocks.CLAY_WALL);
        addDrop(ModBlocks.CLAY_WALL_CROSS);
        addDrop(ModBlocks.CLAY_WALL_DIAGONAL);

        //AGRICULTURE
        addDrop(ModBlocks.FERTILE_SOIL);

        //CHAIRS
        addDrop(ModBlocks.OAK_CHAIR);
        addDrop(ModBlocks.DARK_OAK_CHAIR);
        addDrop(ModBlocks.BIRCH_CHAIR);
        addDrop(ModBlocks.SPRUCE_CHAIR);
        addDrop(ModBlocks.ACACIA_CHAIR);
        addDrop(ModBlocks.JUNGLE_CHAIR);
        addDrop(ModBlocks.BAMBOO_CHAIR);
        addDrop(ModBlocks.CRIMSON_CHAIR);
        addDrop(ModBlocks.WARPED_CHAIR);
        addDrop(ModBlocks.IRONWOOD_CHAIR);
        addDrop(ModBlocks.OLIVE_CHAIR);

        //STOOLS
        addDrop(ModBlocks.OAK_STOOL);

        //TABLE
        addDrop(ModBlocks.OAK_TABLE);
        addDrop(ModBlocks.DARK_OAK_TABLE);
        addDrop(ModBlocks.MANGROVE_TABLE);
        addDrop(ModBlocks.BIRCH_TABLE);
        addDrop(ModBlocks.SPRUCE_TABLE);
        addDrop(ModBlocks.ACACIA_TABLE);
        addDrop(ModBlocks.JUNGLE_TABLE);
        addDrop(ModBlocks.BAMBOO_TABLE);
        addDrop(ModBlocks.CRIMSON_TABLE);
        addDrop(ModBlocks.WARPED_TABLE);
        addDrop(ModBlocks.IRONWOOD_TABLE);
        addDrop(ModBlocks.OLIVE_TABLE);

        //IRONWOOD
        addDrop(ModBlocks.IRONWOOD_LOG);
        addDrop(ModBlocks.STRIPPED_IRONWOOD_LOG);
        addDrop(ModBlocks.IRONWOOD_PLANKS);
        addDrop(ModBlocks.IRONWOOD_SAPLING);
        addDrop(ModBlocks.IRONWOOD_STAIRS);
        addDrop(ModBlocks.IRONWOOD_SLAB, slabDrops(ModBlocks.IRONWOOD_SLAB));
        addDrop(ModBlocks.IRONWOOD_BUTTON);
        addDrop(ModBlocks.IRONWOOD_PRESSURE_PLATE);
        addDrop(ModBlocks.IRONWOOD_FENCE);
        addDrop(ModBlocks.IRONWOOD_FENCE_GATE);
        addDrop(ModBlocks.IRONWOOD_DOOR, doorDrops(ModBlocks.IRONWOOD_DOOR));
        addDrop(ModBlocks.IRONWOOD_TRAPDOOR);

        //OLIVE
        addDrop(ModBlocks.OLIVE_LOG);
        addDrop(ModBlocks.STRIPPED_OLIVE_LOG);
        addDrop(ModBlocks.OLIVE_PLANKS);
        addDrop(ModBlocks.OLIVE_SAPLING);
        addDrop(ModBlocks.OLIVE_STAIRS);
        addDrop(ModBlocks.OLIVE_SLAB, slabDrops(ModBlocks.OLIVE_SLAB));
        addDrop(ModBlocks.OLIVE_BUTTON);
        addDrop(ModBlocks.OLIVE_PRESSURE_PLATE);
        addDrop(ModBlocks.OLIVE_FENCE);
        addDrop(ModBlocks.OLIVE_FENCE_GATE);
        addDrop(ModBlocks.OLIVE_DOOR, doorDrops(ModBlocks.IRONWOOD_DOOR));
        addDrop(ModBlocks.OLIVE_TRAPDOOR);

        //PAINTED PLANKS
        addDrop(ModBlocks.BLACK_PAINTED_PLANKS);
        addDrop(ModBlocks.BLUE_PAINTED_PLANKS);
        addDrop(ModBlocks.BROWN_PAINTED_PLANKS);
        addDrop(ModBlocks.CYAN_PAINTED_PLANKS);
        addDrop(ModBlocks.GRAY_PAINTED_PLANKS);
        addDrop(ModBlocks.GREEN_PAINTED_PLANKS);
        addDrop(ModBlocks.LIGHT_BLUE_PAINTED_PLANKS);
        addDrop(ModBlocks.LIME_PAINTED_PLANKS);
        addDrop(ModBlocks.MAGENTA_PAINTED_PLANKS);
        addDrop(ModBlocks.ORANGE_PAINTED_PLANKS);
        addDrop(ModBlocks.PINK_PAINTED_PLANKS);
        addDrop(ModBlocks.PURPLE_PAINTED_PLANKS);
        addDrop(ModBlocks.RED_PAINTED_PLANKS);
        addDrop(ModBlocks.LIGHT_GRAY_PAINTED_PLANKS);
        addDrop(ModBlocks.WHITE_PAINTED_PLANKS);
        addDrop(ModBlocks.YELLOW_PAINTED_PLANKS);

        //CHAIN
        addDrop(ModBlocks.GOLDEN_CHAIN);
        addDrop(ModBlocks.COPPER_CHAIN);
        addDrop(ModBlocks.EXPOSED_COPPER_CHAIN);
        addDrop(ModBlocks.WEATHERED_COPPER_CHAIN);
        addDrop(ModBlocks.OXIDIZED_COPPER_CHAIN);
        addDrop(ModBlocks.WAXED_COPPER_CHAIN);
        addDrop(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN);
        addDrop(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN);
        addDrop(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN);

        //AGRICULTURE
        addDrop(ModBlocks.ROPE);
        addDrop(ModBlocks.STAKE);
        addDrop(ModBlocks.CRUSHING_TUB);
        addDrop(ModBlocks.EVAPORATING_BASIN);
        addDrop(ModBlocks.APPLE_LEAVES, leavesDrops(Blocks.OAK_LEAVES, ModBlocks.APPLE_SAPLING, 0.0625f));

        //ALCHEMY
        addDrop(ModBlocks.CONDENSER);
        addDrop(ModBlocks.RETORT);

        //CHANDELIER
        addDrop(ModBlocks.CHANDELIER);
        addDrop(ModBlocks.GOLDEN_CHANDELIER);

        addDrop(ModBlocks.COPPER_CHANDELIER);
        addDrop(ModBlocks.EXPOSED_COPPER_CHANDELIER);
        addDrop(ModBlocks.WEATHERED_COPPER_CHANDELIER);
        addDrop(ModBlocks.OXIDIZED_COPPER_CHANDELIER);
        addDrop(ModBlocks.WAXED_COPPER_CHANDELIER);
        addDrop(ModBlocks.WAXED_EXPOSED_COPPER_CHANDELIER);
        addDrop(ModBlocks.WAXED_WEATHERED_COPPER_CHANDELIER);
        addDrop(ModBlocks.WAXED_OXIDIZED_COPPER_CHANDELIER);

        //LANTERNS
        addDrop(ModBlocks.GOLDEN_LANTERN);
        addDrop(ModBlocks.GOLDEN_SOUL_LANTERN);
        addDrop(ModBlocks.COPPER_LANTERN);
        addDrop(ModBlocks.EXPOSED_COPPER_LANTERN);
        addDrop(ModBlocks.WEATHERED_COPPER_LANTERN);
        addDrop(ModBlocks.OXIDIZED_COPPER_LANTERN);
        addDrop(ModBlocks.WAXED_COPPER_LANTERN);
        addDrop(ModBlocks.WAXED_EXPOSED_COPPER_LANTERN);
        addDrop(ModBlocks.WAXED_WEATHERED_COPPER_LANTERN);
        addDrop(ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN);
        addDrop(ModBlocks.EXPOSED_COPPER_SOUL_LANTERN);
        addDrop(ModBlocks.WEATHERED_COPPER_SOUL_LANTERN);
        addDrop(ModBlocks.OXIDIZED_COPPER_SOUL_LANTERN);
        addDrop(ModBlocks.WAXED_COPPER_SOUL_LANTERN);
        addDrop(ModBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN);
        addDrop(ModBlocks.WAXED_WEATHERED_COPPER_SOUL_LANTERN);
        addDrop(ModBlocks.WAXED_OXIDIZED_COPPER_SOUL_LANTERN);

        //CANDLE HOLDERS
        addDrop(ModBlocks.IRON_CANDLE_HOLDER);


        //HERBS
        BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(ModBlocks.ALOE_VERA_BLOCK)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.ALOE_VERA_BLOCK, this.cropDrops(ModBlocks.ALOE_VERA_BLOCK, ModItems.ALOE_VERA, ModItems.ALOE_VERA, builder2));

        BlockStatePropertyLootCondition.Builder builder3 = BlockStatePropertyLootCondition.builder(ModBlocks.HORSETAIL_BLOCK)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.HORSETAIL_BLOCK, this.cropDrops(ModBlocks.HORSETAIL_BLOCK, ModItems.HORSETAIL, ModItems.HORSETAIL, builder3));

        BlockStatePropertyLootCondition.Builder builder4 = BlockStatePropertyLootCondition.builder(ModBlocks.COHOSH_BLOCK)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.COHOSH_BLOCK, this.cropDrops(ModBlocks.COHOSH_BLOCK, ModItems.COHOSH, ModItems.COHOSH, builder4));

        BlockStatePropertyLootCondition.Builder builder5 = BlockStatePropertyLootCondition.builder(ModBlocks.CHAMOMILE_BLOCK)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.CHAMOMILE_BLOCK, this.cropDrops(ModBlocks.CHAMOMILE_BLOCK, ModItems.CHAMOMILE, ModItems.CHAMOMILE, builder5));

        BlockStatePropertyLootCondition.Builder builder6 = BlockStatePropertyLootCondition.builder(ModBlocks.CLOUDSBLUFF_BLOCK)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.CLOUDSBLUFF_BLOCK, this.cropDrops(ModBlocks.CLOUDSBLUFF_BLOCK, ModItems.CLOUDSBLUFF, ModItems.CLOUDSBLUFF, builder6));

        BlockStatePropertyLootCondition.Builder builder7 = BlockStatePropertyLootCondition.builder(ModBlocks.BLOOD_ORCHID_BLOCK)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.BLOOD_ORCHID_BLOCK, this.cropDrops(ModBlocks.BLOOD_ORCHID_BLOCK, ModItems.BLOOD_ORCHID, ModItems.BLOOD_ORCHID, builder7));

        BlockStatePropertyLootCondition.Builder builder8 = BlockStatePropertyLootCondition.builder(ModBlocks.GINSENG_BLOCK)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.GINSENG_BLOCK, this.cropDrops(ModBlocks.GINSENG_BLOCK, ModItems.GINSENG, ModItems.GINSENG, builder8));

        BlockStatePropertyLootCondition.Builder builder9 = BlockStatePropertyLootCondition.builder(ModBlocks.MARSHMALLOW_BLOCK)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.MARSHMALLOW_BLOCK, this.cropDrops(ModBlocks.MARSHMALLOW_BLOCK, ModItems.MARSH_MALLOW, ModItems.MARSH_MALLOW, builder9));

        BlockStatePropertyLootCondition.Builder builder10 = BlockStatePropertyLootCondition.builder(ModBlocks.VANTA_LILY_BLOCK)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.VANTA_LILY_BLOCK, this.cropDrops(ModBlocks.VANTA_LILY_BLOCK, ModItems.VANTA_LILY, ModItems.VANTA_LILY, builder10));

        BlockStatePropertyLootCondition.Builder builder11 = BlockStatePropertyLootCondition.builder(ModBlocks.VANTA_LILY_BLOCK)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.WIND_THISTLE_BLOCK, this.cropDrops(ModBlocks.WIND_THISTLE_BLOCK, ModItems.WIND_THISTLE, ModItems.WIND_THISTLE, builder11));

        BlockStatePropertyLootCondition.Builder builder12 = BlockStatePropertyLootCondition.builder(ModBlocks.MOONCAP_MUSHROOM)
                .properties(StatePredicate.Builder.create().exactMatch(CustomMushroomBlock.AGE, CustomMushroomBlock.MAX_AGE));
        this.addDrop(ModBlocks.MOONCAP_MUSHROOM, this.cropDrops(ModBlocks.MOONCAP_MUSHROOM, ModItems.MOONCAP_MUSHROOM, ModItems.MOONCAP_MUSHROOM, builder12));

        BlockStatePropertyLootCondition.Builder builder13 = BlockStatePropertyLootCondition.builder(ModBlocks.DEATHSTALK_MUSHROOM)
                .properties(StatePredicate.Builder.create().exactMatch(CustomMushroomBlock.AGE, CustomMushroomBlock.MAX_AGE));
        this.addDrop(ModBlocks.DEATHSTALK_MUSHROOM, this.cropDrops(ModBlocks.DEATHSTALK_MUSHROOM, ModItems.DEATHSTALK_MUSHROOM, ModItems.DEATHSTALK_MUSHROOM, builder13));

        BlockStatePropertyLootCondition.Builder builder14 = BlockStatePropertyLootCondition.builder(ModBlocks.CORE_ROOT)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.CORE_ROOT, this.cropDrops(ModBlocks.CORE_ROOT, ModItems.CORE_ROOT, ModItems.CORE_ROOT, builder14));

        //BUSH
        this.addDrop(ModBlocks.BLUE_BERRY_BUSH,
                block -> this.applyExplosionDecay(
                        block, LootTable.builder().pool(LootPool.builder().conditionally(
                                                BlockStatePropertyLootCondition.builder(ModBlocks.BLUE_BERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(BlueBerrieBush.AGE, 3))
                                        )
                                        .with(ItemEntry.builder(ModItems.BLUE_BERRIES))
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                                        .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                        ).pool(LootPool.builder().conditionally(
                                        BlockStatePropertyLootCondition.builder(ModBlocks.BLUE_BERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(BlueBerrieBush.AGE, 2))
                                ).with(ItemEntry.builder(ModItems.BLUE_BERRIES))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE))))));
    }
}
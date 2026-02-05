package net.edu.resprouted.datagen;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.custom.agriculture.CustomBushBlock;
import net.edu.resprouted.block.custom.agriculture.CustomMushroomBlock;
import net.edu.resprouted.block.custom.agriculture.HerbBlock;
import net.edu.resprouted.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
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

        // Slate
        addDrop(ModBlocks.SLATE, drops(ModBlocks.SLATE, ModBlocks.COBBLED_SLATE));
        addDrop(ModBlocks.COBBLED_SLATE);
        addDrop(ModBlocks.COBBLED_SLATE_STAIRS);
        addDrop(ModBlocks.COBBLED_SLATE_SLAB);
        addDrop(ModBlocks.COBBLED_SLATE_WALL);
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

        // Stone
        addDrop(ModBlocks.POLISHED_STONE);
        addDrop(ModBlocks.POLISHED_STONE_SLAB);
        addDrop(ModBlocks.POLISHED_STONE_STAIRS);
        addDrop(ModBlocks.POLISHED_STONE_WALL);
        addDrop(ModBlocks.STONE_PILLAR);
        addDrop(ModBlocks.GARGOYLE);

        // Andesite
        addDrop(ModBlocks.ANDESITE_BRICKS);
        addDrop(ModBlocks.ANDESITE_BRICK_SLAB);
        addDrop(ModBlocks.ANDESITE_BRICK_STAIRS);
        addDrop(ModBlocks.ANDESITE_BRICK_WALL);
        addDrop(ModBlocks.CHISELED_ANDESITE);
        addDrop(ModBlocks.CRACKED_ANDESITE_BRICKS);
        addDrop(ModBlocks.ANDESITE_PILLAR);

        // Diorite
        addDrop(ModBlocks.DIORITE_BRICKS);
        addDrop(ModBlocks.DIORITE_BRICK_SLAB);
        addDrop(ModBlocks.DIORITE_BRICK_STAIRS);
        addDrop(ModBlocks.DIORITE_BRICK_WALL);
        addDrop(ModBlocks.CHISELED_DIORITE);
        addDrop(ModBlocks.CRACKED_DIORITE_BRICKS);
        addDrop(ModBlocks.DIORITE_PILLAR);

        // Granite
        addDrop(ModBlocks.GRANITE_BRICKS);
        addDrop(ModBlocks.GRANITE_BRICK_SLAB);
        addDrop(ModBlocks.GRANITE_BRICK_STAIRS);
        addDrop(ModBlocks.GRANITE_BRICK_WALL);
        addDrop(ModBlocks.CHISELED_GRANITE);
        addDrop(ModBlocks.CRACKED_GRANITE_BRICKS);
        addDrop(ModBlocks.GRANITE_PILLAR);

        // Sandstone
        addDrop(ModBlocks.SANDSTONE_BRICKS);
        addDrop(ModBlocks.SANDSTONE_BRICK_SLAB);
        addDrop(ModBlocks.SANDSTONE_BRICK_STAIRS);
        addDrop(ModBlocks.SANDSTONE_BRICK_WALL);
        addDrop(ModBlocks.SANDSTONE_PILLAR);

        // Red Sandstone
        addDrop(ModBlocks.RED_SANDSTONE_BRICKS);
        addDrop(ModBlocks.RED_SANDSTONE_BRICK_SLAB);
        addDrop(ModBlocks.RED_SANDSTONE_BRICK_STAIRS);
        addDrop(ModBlocks.RED_SANDSTONE_BRICK_WALL);
        addDrop(ModBlocks.RED_SANDSTONE_PILLAR);

        // Wrought Iron
        addDrop(ModBlocks.WROUGHT_IRON_BLOCK);
        addDrop(ModBlocks.WROUGHT_IRON_BULB);
        addDrop(ModBlocks.CUT_WROUGHT_IRON_BLOCK);
        addDrop(ModBlocks.CUT_WROUGHT_IRON_STAIRS);
        addDrop(ModBlocks.CUT_WROUGHT_IRON_SLAB);
        addDrop(ModBlocks.WROUGHT_IRON_BARS);

        // Clay Walls
        addDrop(ModBlocks.CLAY_WALL);
        addDrop(ModBlocks.CLAY_WALL_CROSS);
        addDrop(ModBlocks.CLAY_WALL_DIAGONAL);

        // Chairs
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

        // Stools
        addDrop(ModBlocks.OAK_STOOL);

        // Tables
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

        // Ironwood
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

        // Olive
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
        addDrop(ModBlocks.OLIVE_DOOR, doorDrops(ModBlocks.OLIVE_DOOR));
        addDrop(ModBlocks.OLIVE_TRAPDOOR);

        // Painted Planks
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

        // Cabinets
        addDrop(ModBlocks.OAK_CABINET);
        addDrop(ModBlocks.SPRUCE_CABINET);
        addDrop(ModBlocks.BIRCH_CABINET);
        addDrop(ModBlocks.JUNGLE_CABINET);
        addDrop(ModBlocks.ACACIA_CABINET);
        addDrop(ModBlocks.DARK_OAK_CABINET);
        addDrop(ModBlocks.MANGROVE_CABINET);
        addDrop(ModBlocks.CHERRY_CABINET);
        addDrop(ModBlocks.CRIMSON_CABINET);
        addDrop(ModBlocks.WARPED_CABINET);
        addDrop(ModBlocks.IRONWOOD_CABINET);
        addDrop(ModBlocks.OLIVE_CABINET);

        // Chains
        addDrop(ModBlocks.GOLDEN_CHAIN);
        addDrop(ModBlocks.COPPER_CHAIN);
        addDrop(ModBlocks.EXPOSED_COPPER_CHAIN);
        addDrop(ModBlocks.WEATHERED_COPPER_CHAIN);
        addDrop(ModBlocks.OXIDIZED_COPPER_CHAIN);
        addDrop(ModBlocks.WAXED_COPPER_CHAIN);
        addDrop(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN);
        addDrop(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN);
        addDrop(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN);

        // Agriculture
        addDrop(ModBlocks.ROPE);
        addDrop(ModBlocks.STAKE);
        addDrop(ModBlocks.CRUSHING_TUB);
        addDrop(ModBlocks.DRYING_BASIN);
        addDrop(ModBlocks.BREWING_BARREL);
        addDrop(ModBlocks.FERTILE_SOIL);

        //Unfired
        addDrop(ModBlocks.UNFIRED_DRYING_BASIN);
        addDrop(ModBlocks.UNFIRED_JAR);
        addDrop(ModBlocks.UNFIRED_URN);

        // Urn
        addDrop(ModBlocks.URN);
        addDrop(ModBlocks.WHITE_URN);
        addDrop(ModBlocks.ORANGE_URN);
        addDrop(ModBlocks.MAGENTA_URN);
        addDrop(ModBlocks.LIGHT_BLUE_URN);
        addDrop(ModBlocks.YELLOW_URN);
        addDrop(ModBlocks.LIME_URN);
        addDrop(ModBlocks.PINK_URN);
        addDrop(ModBlocks.GRAY_URN);
        addDrop(ModBlocks.LIGHT_GRAY_URN);
        addDrop(ModBlocks.CYAN_URN);
        addDrop(ModBlocks.PURPLE_URN);
        addDrop(ModBlocks.BLUE_URN);
        addDrop(ModBlocks.BROWN_URN);
        addDrop(ModBlocks.GREEN_URN);
        addDrop(ModBlocks.RED_URN);
        addDrop(ModBlocks.BLACK_URN);

        // Alchemy
        addDrop(ModBlocks.BASIC_CONDENSER);
        addDrop(ModBlocks.ADVANCED_CONDENSER);
        addDrop(ModBlocks.BASIC_RETORT);
        addDrop(ModBlocks.ADVANCED_RETORT);

        // Chanderiers
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

        // Lanterns
        addDrop(ModBlocks.ORNATE_LANTERN);
        addDrop(ModBlocks.ORNATE_SOUL_LANTERN);
        addDrop(ModBlocks.ORNATE_GOLD_LANTERN);
        addDrop(ModBlocks.ORNATE_GOLD_SOUL_LANTERN);
        addDrop(ModBlocks.ORNATE_COPPER_LANTERN);
        addDrop(ModBlocks.EXPOSED_ORNATE_COPPER_LANTERN);
        addDrop(ModBlocks.WEATHERED_ORNATE_COPPER_LANTERN);
        addDrop(ModBlocks.OXIDIZED_ORNATE_COPPER_LANTERN);
        addDrop(ModBlocks.WAXED_ORNATE_COPPER_LANTERN);
        addDrop(ModBlocks.WAXED_EXPOSED_ORNATE_COPPER_LANTERN);
        addDrop(ModBlocks.WAXED_WEATHERED_ORNATE_COPPER_LANTERN);
        addDrop(ModBlocks.WAXED_OXIDIZED_ORNATE_COPPER_LANTERN);
        addDrop(ModBlocks.EXPOSED_ORNATE_COPPER_SOUL_LANTERN);
        addDrop(ModBlocks.WEATHERED_ORNATE_COPPER_SOUL_LANTERN);
        addDrop(ModBlocks.OXIDIZED_ORNATE_COPPER_SOUL_LANTERN);
        addDrop(ModBlocks.WAXED_ORNATE_COPPER_SOUL_LANTERN);
        addDrop(ModBlocks.WAXED_EXPOSED_ORNATE_COPPER_SOUL_LANTERN);
        addDrop(ModBlocks.WAXED_WEATHERED_ORNATE_COPPER_SOUL_LANTERN);
        addDrop(ModBlocks.WAXED_OXIDIZED_ORNATE_COPPER_SOUL_LANTERN);

        // Iron Candle Holders
        addDrop(ModBlocks.IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.WHITE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.LIGHT_GRAY_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.GRAY_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.BLACK_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.BROWN_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.RED_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.ORANGE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.YELLOW_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.LIME_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.GREEN_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.CYAN_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.LIGHT_BLUE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.BLUE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.PURPLE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.MAGENTA_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.PINK_IRON_CANDLE_HOLDER);

        // Double Iron Candle Holders
        addDrop(ModBlocks.DOUBLE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.WHITE_DOUBLE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.LIGHT_GRAY_DOUBLE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.GRAY_DOUBLE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.BLACK_DOUBLE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.BROWN_DOUBLE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.RED_DOUBLE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.ORANGE_DOUBLE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.YELLOW_DOUBLE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.LIME_DOUBLE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.GREEN_DOUBLE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.CYAN_DOUBLE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.LIGHT_BLUE_DOUBLE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.BLUE_DOUBLE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.PURPLE_DOUBLE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.MAGENTA_DOUBLE_IRON_CANDLE_HOLDER);
        addDrop(ModBlocks.PINK_DOUBLE_IRON_CANDLE_HOLDER);

        // Golden Candle Holders
        addDrop(ModBlocks.GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.WHITE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.LIGHT_GRAY_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.GRAY_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.BLACK_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.BROWN_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.RED_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.ORANGE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.YELLOW_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.LIME_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.GREEN_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.CYAN_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.LIGHT_BLUE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.BLUE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.PURPLE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.MAGENTA_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.PINK_GOLDEN_CANDLE_HOLDER);

        // Double Golden Candle Holders
        addDrop(ModBlocks.DOUBLE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.WHITE_DOUBLE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.LIGHT_GRAY_DOUBLE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.GRAY_DOUBLE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.BLACK_DOUBLE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.BROWN_DOUBLE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.RED_DOUBLE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.ORANGE_DOUBLE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.YELLOW_DOUBLE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.LIME_DOUBLE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.GREEN_DOUBLE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.CYAN_DOUBLE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.LIGHT_BLUE_DOUBLE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.BLUE_DOUBLE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.PURPLE_DOUBLE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.MAGENTA_DOUBLE_GOLDEN_CANDLE_HOLDER);
        addDrop(ModBlocks.PINK_DOUBLE_GOLDEN_CANDLE_HOLDER);

        // Copper Candle Holders
        addDrop(ModBlocks.COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WHITE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.LIGHT_GRAY_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.GRAY_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.BLACK_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.BROWN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.RED_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.ORANGE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.YELLOW_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.LIME_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.GREEN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.CYAN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.LIGHT_BLUE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.BLUE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.PURPLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.MAGENTA_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.PINK_COPPER_CANDLE_HOLDER);

        // Exposed Copper Candle Holders
        addDrop(ModBlocks.EXPOSED_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_WHITE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_LIGHT_GRAY_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_GRAY_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_BLACK_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_BROWN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_RED_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_ORANGE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_YELLOW_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_LIME_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_GREEN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_CYAN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_LIGHT_BLUE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_BLUE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_PURPLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_MAGENTA_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_PINK_COPPER_CANDLE_HOLDER);

        // Weathered Copper Candle Holders
        addDrop(ModBlocks.WEATHERED_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_WHITE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_LIGHT_GRAY_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_GRAY_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_BLACK_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_BROWN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_RED_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_ORANGE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_YELLOW_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_LIME_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_GREEN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_CYAN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_LIGHT_BLUE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_BLUE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_PURPLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_MAGENTA_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_PINK_COPPER_CANDLE_HOLDER);

        // Oxidized Copper Candle Holders
        addDrop(ModBlocks.OXIDIZED_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_WHITE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_LIGHT_GRAY_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_GRAY_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_BLACK_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_BROWN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_RED_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_ORANGE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_YELLOW_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_LIME_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_GREEN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_CYAN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_LIGHT_BLUE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_BLUE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_PURPLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_MAGENTA_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_PINK_COPPER_CANDLE_HOLDER);

        // Waxed Copper Candle Holders
        addDrop(ModBlocks.WAXED_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WHITE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_LIGHT_GRAY_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_GRAY_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_BLACK_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_BROWN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_RED_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_ORANGE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_YELLOW_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_LIME_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_GREEN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_CYAN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_LIGHT_BLUE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_BLUE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_PURPLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_MAGENTA_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_PINK_COPPER_CANDLE_HOLDER);

        // Waxed Exposed Copper Candle Holders
        addDrop(ModBlocks.WAXED_EXPOSED_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_WHITE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_LIGHT_GRAY_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_GRAY_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_BLACK_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_BROWN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_RED_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_ORANGE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_YELLOW_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_LIME_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_GREEN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_CYAN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_LIGHT_BLUE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_BLUE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_PURPLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_MAGENTA_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_PINK_COPPER_CANDLE_HOLDER);

        // Waxed Weathered Copper Candle Holders
        addDrop(ModBlocks.WAXED_WEATHERED_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_WHITE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_LIGHT_GRAY_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_GRAY_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_BLACK_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_BROWN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_RED_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_ORANGE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_YELLOW_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_LIME_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_GREEN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_CYAN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_LIGHT_BLUE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_BLUE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_PURPLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_MAGENTA_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_PINK_COPPER_CANDLE_HOLDER);

        // Waxed Oxidized Copper Candle Holders
        addDrop(ModBlocks.WAXED_OXIDIZED_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_WHITE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_LIGHT_GRAY_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_GRAY_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_BLACK_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_BROWN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_RED_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_ORANGE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_YELLOW_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_LIME_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_GREEN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_CYAN_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_LIGHT_BLUE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_BLUE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_PURPLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_MAGENTA_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_PINK_COPPER_CANDLE_HOLDER);

        // Double Copper Candle Holders
        addDrop(ModBlocks.DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WHITE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.BLACK_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.BROWN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.RED_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.YELLOW_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.LIME_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.GREEN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.CYAN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.PINK_DOUBLE_COPPER_CANDLE_HOLDER);

        // Exposed Double Copper Candle Holders
        addDrop(ModBlocks.EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);

        // Weathered Double Copper Candle Holders
        addDrop(ModBlocks.WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);

        // Oxidized Double Copper Candle Holders
        addDrop(ModBlocks.OXIDIZED_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_RED_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.OXIDIZED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);

        // Waxed Double Copper Candle Holders
        addDrop(ModBlocks.WAXED_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_RED_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);

        addDrop(ModBlocks.WAXED_EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);

        addDrop(ModBlocks.WAXED_WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);

        addDrop(ModBlocks.WAXED_OXIDIZED_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_RED_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);
        addDrop(ModBlocks.WAXED_OXIDIZED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);

        // Herbs
        BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(ModBlocks.ALOE_VERA)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.ALOE_VERA, this.cropDrops(ModBlocks.ALOE_VERA, ModItems.ALOE_VERA, ModItems.ALOE_VERA, builder2));

        BlockStatePropertyLootCondition.Builder builder3 = BlockStatePropertyLootCondition.builder(ModBlocks.HORSETAIL)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.HORSETAIL, this.cropDrops(ModBlocks.HORSETAIL, ModItems.HORSETAIL, ModItems.HORSETAIL, builder3));

        BlockStatePropertyLootCondition.Builder builder4 = BlockStatePropertyLootCondition.builder(ModBlocks.COHOSH)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.COHOSH, this.cropDrops(ModBlocks.COHOSH, ModItems.COHOSH, ModItems.COHOSH, builder4));

        BlockStatePropertyLootCondition.Builder builder5 = BlockStatePropertyLootCondition.builder(ModBlocks.CHAMOMILE)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.CHAMOMILE, this.cropDrops(ModBlocks.CHAMOMILE, ModItems.CHAMOMILE, ModItems.CHAMOMILE, builder5));

        BlockStatePropertyLootCondition.Builder builder6 = BlockStatePropertyLootCondition.builder(ModBlocks.CLOUDSBLUFF)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.CLOUDSBLUFF, this.cropDrops(ModBlocks.CLOUDSBLUFF, ModItems.CLOUDSBLUFF, ModItems.CLOUDSBLUFF, builder6));

        BlockStatePropertyLootCondition.Builder builder7 = BlockStatePropertyLootCondition.builder(ModBlocks.BLOOD_ORCHID)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.BLOOD_ORCHID, this.cropDrops(ModBlocks.BLOOD_ORCHID, ModItems.BLOOD_ORCHID, ModItems.BLOOD_ORCHID, builder7));

        BlockStatePropertyLootCondition.Builder builder8 = BlockStatePropertyLootCondition.builder(ModBlocks.GINSENG)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.GINSENG, this.cropDrops(ModBlocks.GINSENG, ModItems.GINSENG, ModItems.GINSENG, builder8));

        BlockStatePropertyLootCondition.Builder builder9 = BlockStatePropertyLootCondition.builder(ModBlocks.MARSHMALLOW)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.MARSHMALLOW, this.cropDrops(ModBlocks.MARSHMALLOW, ModItems.MARSH_MALLOW, ModItems.MARSH_MALLOW, builder9));

        BlockStatePropertyLootCondition.Builder builder10 = BlockStatePropertyLootCondition.builder(ModBlocks.VANTA_LILY)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.VANTA_LILY, this.cropDrops(ModBlocks.VANTA_LILY, ModItems.VANTA_LILY, ModItems.VANTA_LILY, builder10));

        BlockStatePropertyLootCondition.Builder builder11 = BlockStatePropertyLootCondition.builder(ModBlocks.VANTA_LILY)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.WIND_THISTLE, this.cropDrops(ModBlocks.WIND_THISTLE, ModItems.WIND_THISTLE, ModItems.WIND_THISTLE, builder11));

        BlockStatePropertyLootCondition.Builder builder12 = BlockStatePropertyLootCondition.builder(ModBlocks.MOONCAP_MUSHROOM)
                .properties(StatePredicate.Builder.create().exactMatch(CustomMushroomBlock.AGE, CustomMushroomBlock.MAX_AGE));
        this.addDrop(ModBlocks.MOONCAP_MUSHROOM, this.cropDrops(ModBlocks.MOONCAP_MUSHROOM, ModItems.MOONCAP_MUSHROOM, ModItems.MOONCAP_MUSHROOM, builder12));

        BlockStatePropertyLootCondition.Builder builder13 = BlockStatePropertyLootCondition.builder(ModBlocks.DEATHSTALK_MUSHROOM)
                .properties(StatePredicate.Builder.create().exactMatch(CustomMushroomBlock.AGE, CustomMushroomBlock.MAX_AGE));
        this.addDrop(ModBlocks.DEATHSTALK_MUSHROOM, this.cropDrops(ModBlocks.DEATHSTALK_MUSHROOM, ModItems.DEATHSTALK_MUSHROOM, ModItems.DEATHSTALK_MUSHROOM, builder13));

        BlockStatePropertyLootCondition.Builder builder14 = BlockStatePropertyLootCondition.builder(ModBlocks.CORE_ROOT)
                .properties(StatePredicate.Builder.create().exactMatch(HerbBlock.AGE, HerbBlock.MAX_AGE));
        this.addDrop(ModBlocks.CORE_ROOT, this.cropDrops(ModBlocks.CORE_ROOT, ModItems.CORE_ROOT, ModItems.CORE_ROOT, builder14));

        // Potted
        this.addPottedPlantDrops(ModBlocks.POTTED_OLIVE_SAPLING);
        this.addPottedPlantDrops(ModBlocks.POTTED_IRONWOOD_SAPLING);
        this.addPottedPlantDrops(ModBlocks.POTTED_ALOE_VERA);
        this.addPottedPlantDrops(ModBlocks.POTTED_BLOOD_ORCHID);
        this.addPottedPlantDrops(ModBlocks.POTTED_CHAMOMILE);
        this.addPottedPlantDrops(ModBlocks.POTTED_CLOUDSBLUFF);
        this.addPottedPlantDrops(ModBlocks.POTTED_COHOSH);
        this.addPottedPlantDrops(ModBlocks.POTTED_CORE_ROOT);
        this.addPottedPlantDrops(ModBlocks.POTTED_DEATHSTALK_MUSHROOM);
        this.addPottedPlantDrops(ModBlocks.POTTED_GINSENG);
        this.addPottedPlantDrops(ModBlocks.POTTED_HORSETAIL);
        this.addPottedPlantDrops(ModBlocks.POTTED_MARSHMALLOW);
        this.addPottedPlantDrops(ModBlocks.POTTED_MOONCAP_MUSHROOM);
        this.addPottedPlantDrops(ModBlocks.POTTED_VANTA_LILY);
        this.addPottedPlantDrops(ModBlocks.POTTED_WIND_THISTLE);

        // Bushes
        this.addDrop(ModBlocks.TEST_BERRY_BUSH,
                block -> this.applyExplosionDecay(
                        block, LootTable.builder().pool(LootPool.builder().conditionally(
                                                BlockStatePropertyLootCondition.builder(ModBlocks.TEST_BERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(CustomBushBlock.AGE, 3))
                                        )
                                        .with(ItemEntry.builder(ModItems.TEST_BERRIES))
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                                        .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                        ).pool(LootPool.builder().conditionally(
                                        BlockStatePropertyLootCondition.builder(ModBlocks.TEST_BERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(CustomBushBlock.AGE, 2))
                                ).with(ItemEntry.builder(ModItems.TEST_BERRIES))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE))))));

    }
}
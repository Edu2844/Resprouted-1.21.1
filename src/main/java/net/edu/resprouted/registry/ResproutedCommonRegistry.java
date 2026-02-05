package net.edu.resprouted.registry;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.abstracts.AbstractCondenserBlock;
import net.edu.resprouted.block.abstracts.entity.AbstractCondenserBlockEntity;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.recipe.custom.BrewingBarrelRecipe;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

import static net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry.registerOxidizableBlockPair;
import static net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry.registerWaxableBlockPair;
import static net.fabricmc.fabric.api.registry.StrippableBlockRegistry.register;

public class ResproutedCommonRegistry {
    public static void registerModStuffs(){
        registerStrippables();
        registerFlammables();
        registerOxidizablesAndWaxables();
        registerCompostables();
        registerFluidStorages();
        registerItemStorages();
        registerBrewingBarrelRecipes();
    }

    public static void registerStrippables() {
        register(ModBlocks.IRONWOOD_LOG, ModBlocks.STRIPPED_IRONWOOD_LOG);
        register(ModBlocks.IRONWOOD_WOOD, ModBlocks.STRIPPED_IRONWOOD_WOOD);
        register(ModBlocks.OLIVE_LOG, ModBlocks.STRIPPED_OLIVE_LOG);
        register(ModBlocks.OLIVE_WOOD, ModBlocks.STRIPPED_OLIVE_WOOD);
    }

    public static void registerFlammables() {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();

        // Leaves
        registry.add(ModBlocks.IRONWOOD_LEAVES,30,60);
        registry.add(ModBlocks.OLIVE_LEAVES,30,60);
        registry.add(ModBlocks.APPLE_LEAVES,30,60);
        registry.add(ModBlocks.GRAPE_LEAVES,30,60);

        // Wood
        registry.add(ModBlocks.IRONWOOD_LOG,5,5);
        registry.add(ModBlocks.IRONWOOD_WOOD,5,5);
        registry.add(ModBlocks.STRIPPED_IRONWOOD_LOG,5,5);
        registry.add(ModBlocks.STRIPPED_IRONWOOD_WOOD,5,5);
        registry.add(ModBlocks.OLIVE_LOG,5,5);
        registry.add(ModBlocks.OLIVE_WOOD,5,5);
        registry.add(ModBlocks.STRIPPED_OLIVE_LOG,5,5);
        registry.add(ModBlocks.STRIPPED_OLIVE_WOOD,5,5);

        // Planks
        registry.add(ModBlocks.IRONWOOD_PLANKS,5,20);
        registry.add(ModBlocks.IRONWOOD_STAIRS,5,20);
        registry.add(ModBlocks.IRONWOOD_SLAB,5,20);
        registry.add(ModBlocks.IRONWOOD_DOOR,5,20);
        registry.add(ModBlocks.IRONWOOD_TRAPDOOR,5,20);
        registry.add(ModBlocks.IRONWOOD_BUTTON,5,20);
        registry.add(ModBlocks.IRONWOOD_PRESSURE_PLATE,5,20);
        registry.add(ModBlocks.IRONWOOD_FENCE,5,20);
        registry.add(ModBlocks.IRONWOOD_FENCE_GATE,5,20);

        registry.add(ModBlocks.OLIVE_PLANKS,5,20);
        registry.add(ModBlocks.OLIVE_STAIRS,5,20);
        registry.add(ModBlocks.OLIVE_SLAB,5,20);
        registry.add(ModBlocks.OLIVE_DOOR,5,20);
        registry.add(ModBlocks.OLIVE_TRAPDOOR,5,20);
        registry.add(ModBlocks.OLIVE_BUTTON,5,20);
        registry.add(ModBlocks.OLIVE_PRESSURE_PLATE,5,20);
        registry.add(ModBlocks.OLIVE_FENCE,5,20);
        registry.add(ModBlocks.OLIVE_FENCE_GATE,5,20);

        registry.add(ModBlocks.BLACK_PAINTED_PLANKS,5,20);
        registry.add(ModBlocks.BLUE_PAINTED_PLANKS,5,20);
        registry.add(ModBlocks.BROWN_PAINTED_PLANKS,5,20);
        registry.add(ModBlocks.CYAN_PAINTED_PLANKS,5,20);
        registry.add(ModBlocks.GRAY_PAINTED_PLANKS,5,20);
        registry.add(ModBlocks.GREEN_PAINTED_PLANKS,5,20);
        registry.add(ModBlocks.LIGHT_BLUE_PAINTED_PLANKS,5,20);
        registry.add(ModBlocks.LIME_PAINTED_PLANKS,5,20);
        registry.add(ModBlocks.MAGENTA_PAINTED_PLANKS,5,20);
        registry.add(ModBlocks.ORANGE_PAINTED_PLANKS,5,20);
        registry.add(ModBlocks.PINK_PAINTED_PLANKS,5,20);
        registry.add(ModBlocks.PURPLE_PAINTED_PLANKS,5,20);
        registry.add(ModBlocks.RED_PAINTED_PLANKS,5,20);
        registry.add(ModBlocks.LIGHT_GRAY_PAINTED_PLANKS,5,20);
        registry.add(ModBlocks.WHITE_PAINTED_PLANKS,5,20);
        registry.add(ModBlocks.YELLOW_PAINTED_PLANKS,5,20);

        // Tables
        registry.add(ModBlocks.OAK_TABLE,5,20);
        registry.add(ModBlocks.DARK_OAK_TABLE,5,20);
        registry.add(ModBlocks.MANGROVE_TABLE,5,20);
        registry.add(ModBlocks.BIRCH_TABLE,5,20);
        registry.add(ModBlocks.SPRUCE_TABLE,5,20);
        registry.add(ModBlocks.ACACIA_TABLE,5,20);
        registry.add(ModBlocks.JUNGLE_TABLE,5,20);
        registry.add(ModBlocks.BAMBOO_TABLE,5,20);
        registry.add(ModBlocks.CRIMSON_TABLE,5,20);
        registry.add(ModBlocks.WARPED_TABLE,5,20);
        registry.add(ModBlocks.IRONWOOD_TABLE,5,20);
        registry.add(ModBlocks.OLIVE_TABLE,5,20);

        // Chairs
        registry.add(ModBlocks.OAK_CHAIR,5,20);
        registry.add(ModBlocks.DARK_OAK_CHAIR,5,20);
        registry.add(ModBlocks.MANGROVE_CHAIR,5,20);
        registry.add(ModBlocks.BIRCH_CHAIR,5,20);
        registry.add(ModBlocks.SPRUCE_CHAIR,5,20);
        registry.add(ModBlocks.ACACIA_CHAIR,5,20);
        registry.add(ModBlocks.JUNGLE_CHAIR,5,20);
        registry.add(ModBlocks.BAMBOO_CHAIR,5,20);
        registry.add(ModBlocks.CRIMSON_CHAIR,5,20);
        registry.add(ModBlocks.WARPED_CHAIR,5,20);
        registry.add(ModBlocks.IRONWOOD_CHAIR,5,20);
        registry.add(ModBlocks.OLIVE_CHAIR,5,20);

        // Stools
        registry.add(ModBlocks.OAK_STOOL,5,20);
        registry.add(ModBlocks.DARK_OAK_STOOL,5,20);
        registry.add(ModBlocks.MANGROVE_STOOL,5,20);
        registry.add(ModBlocks.BIRCH_STOOL,5,20);
        registry.add(ModBlocks.SPRUCE_STOOL,5,20);
        registry.add(ModBlocks.ACACIA_STOOL,5,20);
        registry.add(ModBlocks.JUNGLE_STOOL,5,20);
        registry.add(ModBlocks.BAMBOO_STOOL,5,20);
        registry.add(ModBlocks.CRIMSON_STOOL,5,20);
        registry.add(ModBlocks.WARPED_STOOL,5,20);
        registry.add(ModBlocks.IRONWOOD_STOOL,5,20);
        registry.add(ModBlocks.OLIVE_STOOL,5,20);

        // Crops
        registry.add(ModBlocks.TOMATO_CROP,30,60);
        registry.add(ModBlocks.CHILI_CROP,30,60);
        registry.add(ModBlocks.GRAPE_STEM,5,5);

        // Other
        registry.add(ModBlocks.ROPE, 20, 60);
        registry.add(ModBlocks.CRUSHING_TUB,5,20);
        registry.add(ModBlocks.LIQUID_BARREL,5,20);
        registry.add(ModBlocks.STAKE,5,20);

    }

    public static void registerOxidizablesAndWaxables() {
        // Chains
        registerOxidizableBlockPair(ModBlocks.COPPER_CHAIN, ModBlocks.EXPOSED_COPPER_CHAIN);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_CHAIN, ModBlocks.WEATHERED_COPPER_CHAIN);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_CHAIN, ModBlocks.OXIDIZED_COPPER_CHAIN);

        registerWaxableBlockPair(ModBlocks.COPPER_CHAIN, ModBlocks.WAXED_COPPER_CHAIN);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_CHAIN, ModBlocks.WAXED_EXPOSED_COPPER_CHAIN);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_CHAIN, ModBlocks.WAXED_EXPOSED_COPPER_CHAIN);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_CHAIN, ModBlocks.WAXED_WEATHERED_COPPER_CHAIN);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_CHAIN, ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN);

        // Lanterns
        registerOxidizableBlockPair(ModBlocks.ORNATE_COPPER_LANTERN, ModBlocks.EXPOSED_ORNATE_COPPER_LANTERN);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_ORNATE_COPPER_LANTERN, ModBlocks.WEATHERED_ORNATE_COPPER_LANTERN);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_ORNATE_COPPER_LANTERN, ModBlocks.OXIDIZED_ORNATE_COPPER_LANTERN);

        registerWaxableBlockPair(ModBlocks.ORNATE_COPPER_LANTERN, ModBlocks.WAXED_ORNATE_COPPER_LANTERN);
        registerWaxableBlockPair(ModBlocks.EXPOSED_ORNATE_COPPER_LANTERN, ModBlocks.WAXED_EXPOSED_ORNATE_COPPER_LANTERN);
        registerWaxableBlockPair(ModBlocks.EXPOSED_ORNATE_COPPER_LANTERN, ModBlocks.WAXED_EXPOSED_ORNATE_COPPER_LANTERN);
        registerWaxableBlockPair(ModBlocks.WEATHERED_ORNATE_COPPER_LANTERN, ModBlocks.WAXED_WEATHERED_ORNATE_COPPER_LANTERN);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_ORNATE_COPPER_LANTERN, ModBlocks.WAXED_OXIDIZED_ORNATE_COPPER_LANTERN);

        registerOxidizableBlockPair(ModBlocks.ORNATE_COPPER_SOUL_LANTERN, ModBlocks.EXPOSED_ORNATE_COPPER_SOUL_LANTERN);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_ORNATE_COPPER_SOUL_LANTERN, ModBlocks.WEATHERED_ORNATE_COPPER_SOUL_LANTERN);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_ORNATE_COPPER_SOUL_LANTERN, ModBlocks.OXIDIZED_ORNATE_COPPER_SOUL_LANTERN);

        registerWaxableBlockPair(ModBlocks.ORNATE_COPPER_SOUL_LANTERN, ModBlocks.WAXED_ORNATE_COPPER_SOUL_LANTERN);
        registerWaxableBlockPair(ModBlocks.EXPOSED_ORNATE_COPPER_SOUL_LANTERN, ModBlocks.WAXED_EXPOSED_ORNATE_COPPER_SOUL_LANTERN);
        registerWaxableBlockPair(ModBlocks.EXPOSED_ORNATE_COPPER_SOUL_LANTERN, ModBlocks.WAXED_EXPOSED_ORNATE_COPPER_SOUL_LANTERN);
        registerWaxableBlockPair(ModBlocks.WEATHERED_ORNATE_COPPER_SOUL_LANTERN, ModBlocks.WAXED_WEATHERED_ORNATE_COPPER_SOUL_LANTERN);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_ORNATE_COPPER_SOUL_LANTERN, ModBlocks.WAXED_OXIDIZED_ORNATE_COPPER_SOUL_LANTERN);

        // Chandeliers
        registerOxidizableBlockPair(ModBlocks.COPPER_CHANDELIER, ModBlocks.EXPOSED_COPPER_CHANDELIER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_CHANDELIER, ModBlocks.WEATHERED_COPPER_CHANDELIER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_CHANDELIER, ModBlocks.OXIDIZED_COPPER_CHANDELIER);

        registerWaxableBlockPair(ModBlocks.COPPER_CHANDELIER, ModBlocks.WAXED_COPPER_CHANDELIER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_CHANDELIER, ModBlocks.WAXED_EXPOSED_COPPER_CHANDELIER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_CHANDELIER, ModBlocks.WAXED_WEATHERED_COPPER_CHANDELIER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_CHANDELIER, ModBlocks.WAXED_OXIDIZED_COPPER_CHANDELIER);

        // Candle Holders
        registerOxidizableBlockPair(ModBlocks.COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_COPPER_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.BLACK_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_BLACK_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_BLACK_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_BLACK_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_BLACK_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_BLACK_COPPER_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.BLUE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_BLUE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_BLUE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_BLUE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_BLUE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_BLUE_COPPER_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.BROWN_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_BROWN_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_BROWN_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_BROWN_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_BROWN_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_BROWN_COPPER_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.CYAN_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_CYAN_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_CYAN_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_CYAN_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_CYAN_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_CYAN_COPPER_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.GRAY_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_GRAY_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_GRAY_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_GRAY_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_GRAY_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_GRAY_COPPER_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.GREEN_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_GREEN_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_GREEN_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_GREEN_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_GREEN_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_GREEN_COPPER_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.LIGHT_BLUE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_LIGHT_BLUE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_LIGHT_BLUE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_LIGHT_BLUE_COPPER_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.LIGHT_GRAY_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_LIGHT_GRAY_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_LIGHT_GRAY_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_LIGHT_GRAY_COPPER_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.LIME_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_LIME_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_LIME_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_LIME_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_LIME_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_LIME_COPPER_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.MAGENTA_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_MAGENTA_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_MAGENTA_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_MAGENTA_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_MAGENTA_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_MAGENTA_COPPER_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.ORANGE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_ORANGE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_ORANGE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_ORANGE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_ORANGE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_ORANGE_COPPER_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.PINK_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_PINK_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_PINK_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_PINK_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_PINK_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_PINK_COPPER_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.PURPLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_PURPLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_PURPLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_PURPLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_PURPLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_PURPLE_COPPER_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.RED_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_RED_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_RED_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_RED_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_RED_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_RED_COPPER_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.WHITE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_WHITE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_WHITE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_WHITE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_WHITE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_WHITE_COPPER_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.YELLOW_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_YELLOW_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_YELLOW_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_YELLOW_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_YELLOW_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_YELLOW_COPPER_CANDLE_HOLDER);

        // Waxable
        registerWaxableBlockPair(ModBlocks.COPPER_CANDLE_HOLDER, ModBlocks.WAXED_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_COPPER_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.BLACK_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_BLACK_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_BLACK_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_BLACK_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_BLACK_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_BLACK_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_BLACK_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_BLACK_COPPER_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.BLUE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_BLUE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_BLUE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_BLUE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_BLUE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_BLUE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_BLUE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_BLUE_COPPER_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.BROWN_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_BROWN_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_BROWN_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_BROWN_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_BROWN_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_BROWN_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_BROWN_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_BROWN_COPPER_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.CYAN_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_CYAN_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_CYAN_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_CYAN_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_CYAN_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_CYAN_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_CYAN_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_CYAN_COPPER_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.GRAY_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_GRAY_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_GRAY_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_GRAY_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_GRAY_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_GRAY_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_GRAY_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_GRAY_COPPER_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.GREEN_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_GREEN_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_GREEN_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_GREEN_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_GREEN_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_GREEN_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_GREEN_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_GREEN_COPPER_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.LIGHT_BLUE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_LIGHT_BLUE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_LIGHT_BLUE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_LIGHT_BLUE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_LIGHT_BLUE_COPPER_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.LIGHT_GRAY_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_LIGHT_GRAY_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_LIGHT_GRAY_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_LIGHT_GRAY_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_LIGHT_GRAY_COPPER_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.LIME_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_LIME_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_LIME_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_LIME_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_LIME_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_LIME_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_LIME_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_LIME_COPPER_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.MAGENTA_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_MAGENTA_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_MAGENTA_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_MAGENTA_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_MAGENTA_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_MAGENTA_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_MAGENTA_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_MAGENTA_COPPER_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.ORANGE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_ORANGE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_ORANGE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_ORANGE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_ORANGE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_ORANGE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_ORANGE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_ORANGE_COPPER_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.PINK_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_PINK_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_PINK_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_PINK_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_PINK_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_PINK_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_PINK_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_PINK_COPPER_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.PURPLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_PURPLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_PURPLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_PURPLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_PURPLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_PURPLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_PURPLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_PURPLE_COPPER_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.RED_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_RED_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_RED_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_RED_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_RED_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_RED_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_RED_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_RED_COPPER_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.WHITE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WHITE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_WHITE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_WHITE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_WHITE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_WHITE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_WHITE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_WHITE_COPPER_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.YELLOW_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_YELLOW_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_YELLOW_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_YELLOW_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_YELLOW_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_YELLOW_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_YELLOW_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_YELLOW_COPPER_CANDLE_HOLDER);

        // Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_DOUBLE_COPPER_CANDLE_HOLDER);

        // Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_DOUBLE_COPPER_CANDLE_HOLDER);

        // Black Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.BLACK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);

        // Black Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.BLACK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);

        // Blue Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);

        // Blue Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);

        // Brown Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.BROWN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);

        // Brown Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.BROWN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);

        // Cyan Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.CYAN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);

        // Cyan Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.CYAN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);

        // Gray Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);

        // Gray Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);

        // Green Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.GREEN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);

        // Green Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.GREEN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);

        // Light Blue Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);

        // Light Blue Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);

        // Light Gray Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);

        // Light Gray Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);

        // Lime Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.LIME_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);

        // Lime Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.LIME_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);

        // Magenta Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);

        // Magenta Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);

        // Orange Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);

        // Orange Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);

        // Pink Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.PINK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);

        // Pink Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.PINK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);

        // Purple Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);

        // Purple Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);

        // Red Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.RED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_RED_DOUBLE_COPPER_CANDLE_HOLDER);

        // Red Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.RED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_RED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_RED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_RED_DOUBLE_COPPER_CANDLE_HOLDER);

        // White Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.WHITE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);

        // White Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.WHITE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);

        // Yellow Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER);

        // Yellow Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER);
    }

    public static void registerCompostables() {
        CompostingChanceRegistry registry = CompostingChanceRegistry.INSTANCE;
        registry.add(ModItems.ALOE_VERA, 0.5F);
        registry.add(ModItems.HORSETAIL, 0.5F);
        registry.add(ModItems.COHOSH, 0.5F);
        registry.add(ModItems.CHAMOMILE, 0.5F);
        registry.add(ModItems.CLOUDSBLUFF, 0.5F);
        registry.add(ModItems.BLOOD_ORCHID, 0.5F);
        registry.add(ModItems.VANTA_LILY, 0.5F);
        registry.add(ModItems.MARSH_MALLOW, 0.5F);
        registry.add(ModItems.CORE_ROOT, 0.5F);
        registry.add(ModItems.MOONCAP_MUSHROOM, 0.5F);
        registry.add(ModItems.DEATHSTALK_MUSHROOM, 0.5F);
        registry.add(ModItems.IRON_BERRIES, 0.5F);
        registry.add(ModItems.TOMATO, 0.5F);
        registry.add(ModItems.TOMATO_SEEDS, 0.5F);
        registry.add(ModItems.CHILI_PEPPER, 0.5F);
        registry.add(ModItems.CHILI_PEPPER_SEEDS, 0.5F);
        registry.add(ModItems.GRAPES, 0.5F);
        registry.add(ModItems.GRAPE_SEEDS, 0.5F);
        registry.add(ModItems.OLIVES, 0.5F);
    }

    public static void registerFluidStorages() {
        FluidStorage.SIDED.registerForBlockEntity((liquidJarBE, direction) -> liquidJarBE.getFluidTankProvider(), ModBlockEntities.JAR_BE);
        FluidStorage.SIDED.registerForBlockEntity((crushingTubBE, direction) -> crushingTubBE.getFluidTankProvider(), ModBlockEntities.CRUSHING_TUB_BE);
        FluidStorage.SIDED.registerForBlockEntity((liquidBarrelBE, direction) -> liquidBarrelBE.getFluidTankProvider(), ModBlockEntities.LIQUID_BARREL_BE);
        FluidStorage.SIDED.registerForBlockEntity((dryingBasinBE, direction) -> dryingBasinBE.getFluidTankProvider(), ModBlockEntities.DRYING_BASIN_BE);
        FluidStorage.SIDED.registerForBlocks((world, pos, state, blockEntity, direction) -> {
            BlockPos bottomPos = state.get(AbstractCondenserBlock.BOTTOM) ? pos : pos.down();
            BlockEntity bottomBE = world.getBlockEntity(bottomPos);

            if (bottomBE instanceof AbstractCondenserBlockEntity condenserBE) {
                return condenserBE.getFluidTankProvider();
            }

            return null;
        }, ModBlocks.BASIC_CONDENSER, ModBlocks.ADVANCED_CONDENSER);
    }

    public static void registerItemStorages() {
        ItemStorage.SIDED.registerForBlockEntity((cabinet, direction) -> InventoryStorage.of(cabinet.getCombinedInventory(), direction), ModBlockEntities.CABINET_BE);
    }

    public static void registerBrewingBarrelRecipes(){
        BrewingBarrelRecipe.register(new BrewingBarrelRecipe(ModFluids.ALE, ModFluids.ALE_WORT));
        BrewingBarrelRecipe.register(new BrewingBarrelRecipe(ModFluids.IRON_WINE, ModFluids.IRON_BERRY_JUICE));
        BrewingBarrelRecipe.register(new BrewingBarrelRecipe(ModFluids.WINE, ModFluids.GRAPE_JUICE));
        BrewingBarrelRecipe.register(new BrewingBarrelRecipe(ModFluids.SWEET_BERRY_WINE, ModFluids.SWEET_BERRY_JUICE));
        BrewingBarrelRecipe.register(new BrewingBarrelRecipe(ModFluids.CIDER, ModFluids.APPLE_JUICE));
        BrewingBarrelRecipe.register(new BrewingBarrelRecipe(ModFluids.MEAD, ModFluids.HONEY));
        BrewingBarrelRecipe.register(new BrewingBarrelRecipe(ModFluids.AMBROSIA, ModFluids.GOLDEN_APPLE_JUICE));
        BrewingBarrelRecipe.register(new BrewingBarrelRecipe(ModFluids.GLOW_BERRY_WINE, ModFluids.GLOW_BERRY_JUICE));
        BrewingBarrelRecipe.register(new BrewingBarrelRecipe(ModFluids.RUM, ModFluids.SUGAR_CANE_JUICE));
    }
}

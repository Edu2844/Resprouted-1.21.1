package net.edu.resprouted.registry;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.recipe.custom.BrewingBarrelRecipe;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;

import static net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry.registerOxidizableBlockPair;
import static net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry.registerWaxableBlockPair;
import static net.fabricmc.fabric.api.registry.StrippableBlockRegistry.register;

public class ResproutedRegistry {
    public static void RegisterModStuffs(){
        registerStrippables();
        registerFlammables();
        registerOxidizablesAndWaxables();
        registerCompostables();
        registerFluidStorages();
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
        registry.add(ModBlocks.IRONWOOD_LOG,5,5);
        registry.add(ModBlocks.IRONWOOD_WOOD,5,5);
        registry.add(ModBlocks.STRIPPED_IRONWOOD_LOG,5,5);
        registry.add(ModBlocks.STRIPPED_IRONWOOD_WOOD,5,5);
        registry.add(ModBlocks.IRONWOOD_PLANKS,5,20);
        registry.add(ModBlocks.IRONWOOD_LEAVES,30,60);
        registry.add(ModBlocks.OLIVE_LOG,5,5);
        registry.add(ModBlocks.OLIVE_WOOD,5,5);
        registry.add(ModBlocks.STRIPPED_OLIVE_LOG,5,5);
        registry.add(ModBlocks.STRIPPED_OLIVE_WOOD,5,5);
        registry.add(ModBlocks.OLIVE_PLANKS,5,20);
        registry.add(ModBlocks.OLIVE_LEAVES,30,60);
        registry.add(ModBlocks.APPLE_LEAVES,30,60);
        registry.add(ModBlocks.TOMATO_CROP,30,60);
        registry.add(ModBlocks.CHILI_CROP,30,60);
        registry.add(ModBlocks.CRUSHING_TUB,5,20);
        registry.add(ModBlocks.LIQUID_BARREL,5,20);
        registry.add(ModBlocks.STAKE,5,20);
        registry.add(ModBlocks.GRAPE_STEM,5,5);
        registry.add(ModBlocks.GRAPE_LEAVES,30,60);
    }

    public static void registerOxidizablesAndWaxables() {
        //Chain
        registerOxidizableBlockPair(ModBlocks.COPPER_CHAIN, ModBlocks.EXPOSED_COPPER_CHAIN);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_CHAIN, ModBlocks.WEATHERED_COPPER_CHAIN);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_CHAIN, ModBlocks.OXIDIZED_COPPER_CHAIN);

        registerWaxableBlockPair(ModBlocks.COPPER_CHAIN, ModBlocks.WAXED_COPPER_CHAIN);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_CHAIN, ModBlocks.WAXED_EXPOSED_COPPER_CHAIN);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_CHAIN, ModBlocks.WAXED_EXPOSED_COPPER_CHAIN);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_CHAIN, ModBlocks.WAXED_WEATHERED_COPPER_CHAIN);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_CHAIN, ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN);

        //Lantern
        registerOxidizableBlockPair(ModBlocks.COPPER_LANTERN, ModBlocks.EXPOSED_COPPER_LANTERN);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_LANTERN, ModBlocks.WEATHERED_COPPER_LANTERN);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_LANTERN, ModBlocks.OXIDIZED_COPPER_LANTERN);

        registerWaxableBlockPair(ModBlocks.COPPER_LANTERN, ModBlocks.WAXED_COPPER_LANTERN);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_LANTERN, ModBlocks.WAXED_EXPOSED_COPPER_LANTERN);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_LANTERN, ModBlocks.WAXED_EXPOSED_COPPER_LANTERN);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_LANTERN, ModBlocks.WAXED_WEATHERED_COPPER_LANTERN);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_LANTERN, ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN);

        registerOxidizableBlockPair(ModBlocks.COPPER_SOUL_LANTERN, ModBlocks.EXPOSED_COPPER_SOUL_LANTERN);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_SOUL_LANTERN, ModBlocks.WEATHERED_COPPER_SOUL_LANTERN);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_SOUL_LANTERN, ModBlocks.OXIDIZED_COPPER_SOUL_LANTERN);

        registerWaxableBlockPair(ModBlocks.COPPER_SOUL_LANTERN, ModBlocks.WAXED_COPPER_SOUL_LANTERN);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_SOUL_LANTERN, ModBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_SOUL_LANTERN, ModBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_SOUL_LANTERN, ModBlocks.WAXED_WEATHERED_COPPER_SOUL_LANTERN);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_SOUL_LANTERN, ModBlocks.WAXED_OXIDIZED_COPPER_SOUL_LANTERN);

        //Chandelier
        registerOxidizableBlockPair(ModBlocks.COPPER_CHANDELIER, ModBlocks.EXPOSED_COPPER_CHANDELIER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_CHANDELIER, ModBlocks.WEATHERED_COPPER_CHANDELIER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_CHANDELIER, ModBlocks.OXIDIZED_COPPER_CHANDELIER);

        registerWaxableBlockPair(ModBlocks.COPPER_CHANDELIER, ModBlocks.WAXED_COPPER_CHANDELIER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_CHANDELIER, ModBlocks.WAXED_EXPOSED_COPPER_CHANDELIER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_CHANDELIER, ModBlocks.WAXED_WEATHERED_COPPER_CHANDELIER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_CHANDELIER, ModBlocks.WAXED_OXIDIZED_COPPER_CHANDELIER);

        //Candle Holder
        registerOxidizableBlockPair(ModBlocks.COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_COPPER_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.COPPER_BLACK_CANDLE_HOLDER, ModBlocks.EXPOSED_COPPER_BLACK_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_BLACK_CANDLE_HOLDER, ModBlocks.WEATHERED_COPPER_BLACK_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_BLACK_CANDLE_HOLDER, ModBlocks.OXIDIZED_COPPER_BLACK_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.COPPER_BLUE_CANDLE_HOLDER, ModBlocks.EXPOSED_COPPER_BLUE_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_BLUE_CANDLE_HOLDER, ModBlocks.WEATHERED_COPPER_BLUE_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_BLUE_CANDLE_HOLDER, ModBlocks.OXIDIZED_COPPER_BLUE_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.COPPER_BROWN_CANDLE_HOLDER, ModBlocks.EXPOSED_COPPER_BROWN_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_BROWN_CANDLE_HOLDER, ModBlocks.WEATHERED_COPPER_BROWN_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_BROWN_CANDLE_HOLDER, ModBlocks.OXIDIZED_COPPER_BROWN_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.COPPER_CYAN_CANDLE_HOLDER, ModBlocks.EXPOSED_COPPER_CYAN_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_CYAN_CANDLE_HOLDER, ModBlocks.WEATHERED_COPPER_CYAN_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_CYAN_CANDLE_HOLDER, ModBlocks.OXIDIZED_COPPER_CYAN_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.COPPER_GRAY_CANDLE_HOLDER, ModBlocks.EXPOSED_COPPER_GRAY_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_GRAY_CANDLE_HOLDER, ModBlocks.WEATHERED_COPPER_GRAY_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_GRAY_CANDLE_HOLDER, ModBlocks.OXIDIZED_COPPER_GRAY_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.COPPER_GREEN_CANDLE_HOLDER, ModBlocks.EXPOSED_COPPER_GREEN_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_GREEN_CANDLE_HOLDER, ModBlocks.WEATHERED_COPPER_GREEN_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_GREEN_CANDLE_HOLDER, ModBlocks.OXIDIZED_COPPER_GREEN_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.COPPER_LIGHT_BLUE_CANDLE_HOLDER, ModBlocks.EXPOSED_COPPER_LIGHT_BLUE_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_LIGHT_BLUE_CANDLE_HOLDER, ModBlocks.WEATHERED_COPPER_LIGHT_BLUE_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_LIGHT_BLUE_CANDLE_HOLDER, ModBlocks.OXIDIZED_COPPER_LIGHT_BLUE_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.COPPER_LIGHT_GRAY_CANDLE_HOLDER, ModBlocks.EXPOSED_COPPER_LIGHT_GRAY_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_LIGHT_GRAY_CANDLE_HOLDER, ModBlocks.WEATHERED_COPPER_LIGHT_GRAY_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_LIGHT_GRAY_CANDLE_HOLDER, ModBlocks.OXIDIZED_COPPER_LIGHT_GRAY_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.COPPER_LIME_CANDLE_HOLDER, ModBlocks.EXPOSED_COPPER_LIME_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_LIME_CANDLE_HOLDER, ModBlocks.WEATHERED_COPPER_LIME_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_LIME_CANDLE_HOLDER, ModBlocks.OXIDIZED_COPPER_LIME_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.COPPER_MAGENTA_CANDLE_HOLDER, ModBlocks.EXPOSED_COPPER_MAGENTA_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_MAGENTA_CANDLE_HOLDER, ModBlocks.WEATHERED_COPPER_MAGENTA_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_MAGENTA_CANDLE_HOLDER, ModBlocks.OXIDIZED_COPPER_MAGENTA_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.COPPER_ORANGE_CANDLE_HOLDER, ModBlocks.EXPOSED_COPPER_ORANGE_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_ORANGE_CANDLE_HOLDER, ModBlocks.WEATHERED_COPPER_ORANGE_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_ORANGE_CANDLE_HOLDER, ModBlocks.OXIDIZED_COPPER_ORANGE_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.COPPER_PINK_CANDLE_HOLDER, ModBlocks.EXPOSED_COPPER_PINK_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_PINK_CANDLE_HOLDER, ModBlocks.WEATHERED_COPPER_PINK_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_PINK_CANDLE_HOLDER, ModBlocks.OXIDIZED_COPPER_PINK_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.COPPER_PURPLE_CANDLE_HOLDER, ModBlocks.EXPOSED_COPPER_PURPLE_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_PURPLE_CANDLE_HOLDER, ModBlocks.WEATHERED_COPPER_PURPLE_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_PURPLE_CANDLE_HOLDER, ModBlocks.OXIDIZED_COPPER_PURPLE_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.COPPER_RED_CANDLE_HOLDER, ModBlocks.EXPOSED_COPPER_RED_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_RED_CANDLE_HOLDER, ModBlocks.WEATHERED_COPPER_RED_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_RED_CANDLE_HOLDER, ModBlocks.OXIDIZED_COPPER_RED_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.COPPER_WHITE_CANDLE_HOLDER, ModBlocks.EXPOSED_COPPER_WHITE_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_WHITE_CANDLE_HOLDER, ModBlocks.WEATHERED_COPPER_WHITE_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_WHITE_CANDLE_HOLDER, ModBlocks.OXIDIZED_COPPER_WHITE_CANDLE_HOLDER);

        registerOxidizableBlockPair(ModBlocks.COPPER_YELLOW_CANDLE_HOLDER, ModBlocks.EXPOSED_COPPER_YELLOW_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_YELLOW_CANDLE_HOLDER, ModBlocks.WEATHERED_COPPER_YELLOW_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_YELLOW_CANDLE_HOLDER, ModBlocks.OXIDIZED_COPPER_YELLOW_CANDLE_HOLDER);

        //waxable
        registerWaxableBlockPair(ModBlocks.COPPER_CANDLE_HOLDER, ModBlocks.WAXED_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_COPPER_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.COPPER_BLACK_CANDLE_HOLDER, ModBlocks.WAXED_COPPER_BLACK_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_BLACK_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_COPPER_BLACK_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_BLACK_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_COPPER_BLACK_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_BLACK_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_COPPER_BLACK_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.COPPER_BLUE_CANDLE_HOLDER, ModBlocks.WAXED_COPPER_BLUE_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_BLUE_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_COPPER_BLUE_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_BLUE_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_COPPER_BLUE_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_BLUE_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_COPPER_BLUE_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.COPPER_BROWN_CANDLE_HOLDER, ModBlocks.WAXED_COPPER_BROWN_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_BROWN_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_COPPER_BROWN_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_BROWN_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_COPPER_BROWN_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_BROWN_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_COPPER_BROWN_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.COPPER_CYAN_CANDLE_HOLDER, ModBlocks.WAXED_COPPER_CYAN_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_CYAN_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_COPPER_CYAN_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_CYAN_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_COPPER_CYAN_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_CYAN_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_COPPER_CYAN_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.COPPER_GRAY_CANDLE_HOLDER, ModBlocks.WAXED_COPPER_GRAY_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_GRAY_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_COPPER_GRAY_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_GRAY_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_COPPER_GRAY_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_GRAY_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_COPPER_GRAY_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.COPPER_GREEN_CANDLE_HOLDER, ModBlocks.WAXED_COPPER_GREEN_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_GREEN_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_COPPER_GREEN_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_GREEN_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_COPPER_GREEN_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_GREEN_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_COPPER_GREEN_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.COPPER_LIGHT_BLUE_CANDLE_HOLDER, ModBlocks.WAXED_COPPER_LIGHT_BLUE_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_LIGHT_BLUE_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_COPPER_LIGHT_BLUE_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_LIGHT_BLUE_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_COPPER_LIGHT_BLUE_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_LIGHT_BLUE_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_COPPER_LIGHT_BLUE_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.COPPER_LIGHT_GRAY_CANDLE_HOLDER, ModBlocks.WAXED_COPPER_LIGHT_GRAY_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_LIGHT_GRAY_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_COPPER_LIGHT_GRAY_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_LIGHT_GRAY_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_COPPER_LIGHT_GRAY_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_LIGHT_GRAY_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_COPPER_LIGHT_GRAY_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.COPPER_LIME_CANDLE_HOLDER, ModBlocks.WAXED_COPPER_LIME_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_LIME_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_COPPER_LIME_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_LIME_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_COPPER_LIME_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_LIME_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_COPPER_LIME_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.COPPER_MAGENTA_CANDLE_HOLDER, ModBlocks.WAXED_COPPER_MAGENTA_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_MAGENTA_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_COPPER_MAGENTA_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_MAGENTA_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_COPPER_MAGENTA_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_MAGENTA_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_COPPER_MAGENTA_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.COPPER_ORANGE_CANDLE_HOLDER, ModBlocks.WAXED_COPPER_ORANGE_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_ORANGE_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_COPPER_ORANGE_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_ORANGE_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_COPPER_ORANGE_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_ORANGE_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_COPPER_ORANGE_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.COPPER_PINK_CANDLE_HOLDER, ModBlocks.WAXED_COPPER_PINK_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_PINK_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_COPPER_PINK_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_PINK_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_COPPER_PINK_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_PINK_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_COPPER_PINK_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.COPPER_PURPLE_CANDLE_HOLDER, ModBlocks.WAXED_COPPER_PURPLE_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_PURPLE_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_COPPER_PURPLE_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_PURPLE_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_COPPER_PURPLE_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_PURPLE_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_COPPER_PURPLE_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.COPPER_RED_CANDLE_HOLDER, ModBlocks.WAXED_COPPER_RED_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_RED_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_COPPER_RED_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_RED_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_COPPER_RED_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_RED_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_COPPER_RED_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.COPPER_WHITE_CANDLE_HOLDER, ModBlocks.WAXED_COPPER_WHITE_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_WHITE_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_COPPER_WHITE_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_WHITE_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_COPPER_WHITE_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_WHITE_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_COPPER_WHITE_CANDLE_HOLDER);

        registerWaxableBlockPair(ModBlocks.COPPER_YELLOW_CANDLE_HOLDER, ModBlocks.WAXED_COPPER_YELLOW_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_YELLOW_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_COPPER_YELLOW_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_YELLOW_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_COPPER_YELLOW_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_YELLOW_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_COPPER_YELLOW_CANDLE_HOLDER);
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
        FluidStorage.SIDED.registerForBlockEntity((crushingTubBE, direction2) -> crushingTubBE.getFluidTankProvider(), ModBlockEntities.CRUSHING_TUB_BE);
        FluidStorage.SIDED.registerForBlockEntity((liquidBarrelBE, direction1) -> liquidBarrelBE.getFluidTankProvider(), ModBlockEntities.LIQUID_BARREL_BE);
        FluidStorage.SIDED.registerForBlockEntity((evaporatingBasinBE, direction) -> evaporatingBasinBE.getFluidTankProvider(), ModBlockEntities.EVAPORATING_BASIN_BE);
        FluidStorage.SIDED.registerForBlockEntity((condenserBE, direction1) -> condenserBE.getFluidTankProvider(), ModBlockEntities.CONDENSER_BE);
        FluidStorage.SIDED.registerForBlockEntity((advancedCondenserBE, direction) -> advancedCondenserBE.getFluidTankProvider(), ModBlockEntities.ADVANCED_CONDENSER_BE);

    }

    public static void registerBrewingBarrelRecipes(){
        BrewingBarrelRecipe.register(new BrewingBarrelRecipe(ModFluids.ALE_STILL, ModFluids.ALE_WORT_STILL));
        BrewingBarrelRecipe.register(new BrewingBarrelRecipe(ModFluids.IRON_WINE_STILL, ModFluids.IRON_BERRY_JUICE_STILL));
        BrewingBarrelRecipe.register(new BrewingBarrelRecipe(ModFluids.WINE_STILL, ModFluids.GRAPE_JUICE_STILL));
        BrewingBarrelRecipe.register(new BrewingBarrelRecipe(ModFluids.SWEET_BERRY_WINE_STILL, ModFluids.SWEET_BERRY_JUICE_STILL));
        BrewingBarrelRecipe.register(new BrewingBarrelRecipe(ModFluids.CIDER_STILL, ModFluids.APPLE_JUICE_STILL));
        BrewingBarrelRecipe.register(new BrewingBarrelRecipe(ModFluids.MEAD_STILL, ModFluids.HONEY_STILL));
        BrewingBarrelRecipe.register(new BrewingBarrelRecipe(ModFluids.AMBROSIA_STILL, ModFluids.GOLDEN_APPLE_JUICE_STILL));
        BrewingBarrelRecipe.register(new BrewingBarrelRecipe(ModFluids.GLOW_BERRY_WINE_STILL, ModFluids.GLOW_BERRY_JUICE_STILL));
        BrewingBarrelRecipe.register(new BrewingBarrelRecipe(ModFluids.RUM_STILL, ModFluids.SUGAR_CANE_JUICE_STILL));
    }
}

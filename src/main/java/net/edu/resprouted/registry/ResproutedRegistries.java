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

public class ResproutedRegistries {
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
        registry.add(ModBlocks.ROPE, 20, 60);
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

        //Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_DOUBLE_COPPER_CANDLE_HOLDER);

        //Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_DOUBLE_COPPER_CANDLE_HOLDER);

        //Black Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.BLACK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);

        //Black Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.BLACK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER);

        //Blue Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);

        //Blue Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);

        //Brown Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.BROWN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);

        //Brown Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.BROWN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER);

        //Cyan Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.CYAN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);

        //Cyan Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.CYAN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER);

        //Gray Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);

        //Gray Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);

        //Green Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.GREEN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);

        //Green Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.GREEN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER);

        //Light Blue Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);

        //Light Blue Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER);

        //Light Gray Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);

        //Light Gray Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER);

        //Lime Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.LIME_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);

        //Lime Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.LIME_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_LIME_DOUBLE_COPPER_CANDLE_HOLDER);

        //Magenta Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);

        // Magenta Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER);

        //Orange Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);

        //Orange Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER);

        //Pink Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.PINK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);

        //Pink Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.PINK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_PINK_DOUBLE_COPPER_CANDLE_HOLDER);

        //Purple Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);

        //Purple Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER);

        //Red Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.RED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_RED_DOUBLE_COPPER_CANDLE_HOLDER);

        //Red Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.RED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_RED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_RED_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_RED_DOUBLE_COPPER_CANDLE_HOLDER);

        //White Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.WHITE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);

        //White Double Copper Candle Holders - Waxables
        registerWaxableBlockPair(ModBlocks.WHITE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WAXED_OXIDIZED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER);

        //Yellow Double Copper Candle Holders
        registerOxidizableBlockPair(ModBlocks.YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.EXPOSED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.WEATHERED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, ModBlocks.OXIDIZED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER);

        //Yellow Double Copper Candle Holders - Waxables
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

package net.edu.resprouted.datagen;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.custom.agriculture.CustomBushBlock;
import net.edu.resprouted.block.custom.agriculture.CustomMushroomBlock;
import net.edu.resprouted.block.custom.agriculture.HerbBlock;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.registry.ResproutedBoatTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // =================================================
        // ||                   SLATE                     ||
        // =================================================
        blockStateModelGenerator.registerMirrorable(ModBlocks.SLATE);

        BlockStateModelGenerator.BlockTexturePool pclate = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.COBBLED_SLATE);
        pclate.stairs(ModBlocks.COBBLED_SLATE_STAIRS);
        pclate.slab(ModBlocks.COBBLED_SLATE_SLAB);
        pclate.wall(ModBlocks.COBBLED_SLATE_WALL);

        BlockStateModelGenerator.BlockTexturePool pslate = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_SLATE);
        pslate.stairs(ModBlocks.POLISHED_SLATE_STAIRS);
        pslate.slab(ModBlocks.POLISHED_SLATE_SLAB);

        BlockStateModelGenerator.BlockTexturePool slateb = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SLATE_BRICKS);
        slateb.stairs(ModBlocks.SLATE_BRICK_STAIRS);
        slateb.wall(ModBlocks.SLATE_BRICK_WALL);
        slateb.slab(ModBlocks.SLATE_BRICK_SLAB);

        BlockStateModelGenerator.BlockTexturePool slater = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SLATE_ROOFS);
        slater.stairs(ModBlocks.SLATE_ROOF_STAIRS);
        slater.slab(ModBlocks.SLATE_ROOF_SLAB);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_SLATE);

        // =================================================
        // ||                 ANDESITE                    ||
        // =================================================
        BlockStateModelGenerator.BlockTexturePool pandesite = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.ANDESITE_BRICKS);
        pandesite.stairs(ModBlocks.ANDESITE_BRICK_STAIRS);
        pandesite.wall(ModBlocks.ANDESITE_BRICK_WALL);
        pandesite.slab(ModBlocks.ANDESITE_BRICK_SLAB);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_ANDESITE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_ANDESITE);

        // =================================================
        // ||                  DIORITE                    ||
        // =================================================
        BlockStateModelGenerator.BlockTexturePool pdiorite = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DIORITE_BRICKS);
        pdiorite.stairs(ModBlocks.DIORITE_BRICK_STAIRS);
        pdiorite.wall(ModBlocks.DIORITE_BRICK_WALL);
        pdiorite.slab(ModBlocks.DIORITE_BRICK_SLAB);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_DIORITE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_DIORITE);

        // =================================================
        // ||                  GRANITE                    ||
        // =================================================
        BlockStateModelGenerator.BlockTexturePool pgranite = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GRANITE_BRICKS);
        pgranite.stairs(ModBlocks.GRANITE_BRICK_STAIRS);
        pgranite.wall(ModBlocks.GRANITE_BRICK_WALL);
        pgranite.slab(ModBlocks.GRANITE_BRICK_SLAB);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_GRANITE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_GRANITE);

        // =================================================
        // ||                   STONE                     ||
        // =================================================
        BlockStateModelGenerator.BlockTexturePool pstone = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_STONE);
        pstone.stairs(ModBlocks.POLISHED_STONE_STAIRS);
        pstone.wall(ModBlocks.POLISHED_STONE_WALL);
        pstone.slab(ModBlocks.POLISHED_STONE_SLAB);

        // =================================================
        // ||                SANDSTONE                    ||
        // =================================================

        BlockStateModelGenerator.BlockTexturePool psbrick = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SANDSTONE_BRICKS);
        psbrick.stairs(ModBlocks.SANDSTONE_BRICK_STAIRS);
        psbrick.wall(ModBlocks.SANDSTONE_BRICK_WALL);
        psbrick.slab(ModBlocks.SANDSTONE_BRICK_SLAB);

        // =================================================
        // ||                RED SANDSTONE                ||
        // =================================================

        BlockStateModelGenerator.BlockTexturePool prsbrick = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.RED_SANDSTONE_BRICKS);
        prsbrick.stairs(ModBlocks.RED_SANDSTONE_BRICK_STAIRS);
        prsbrick.wall(ModBlocks.RED_SANDSTONE_BRICK_WALL);
        prsbrick.slab(ModBlocks.RED_SANDSTONE_BRICK_SLAB);

        // =================================================
        // ||                WROUGHT IRON                 ||
        // =================================================
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WROUGHT_IRON_BLOCK);
        blockStateModelGenerator.registerCopperBulb(ModBlocks.WROUGHT_IRON_BULB);
   

        BlockStateModelGenerator.BlockTexturePool pwiron = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CUT_WROUGHT_IRON_BLOCK);
        pwiron.stairs(ModBlocks.CUT_WROUGHT_IRON_STAIRS);
        pwiron.slab(ModBlocks.CUT_WROUGHT_IRON_SLAB);

        // =================================================
        // ||                   CLAY WALL                 ||
        // =================================================
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CLAY_WALL);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CLAY_WALL_CROSS);

        // =================================================
        // ||                    OLIVE                    ||
        // =================================================
        BlockStateModelGenerator.BlockTexturePool OlivePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.OLIVE_PLANKS);
        blockStateModelGenerator.registerSingleton(ModBlocks.OLIVE_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerLog(ModBlocks.OLIVE_LOG).log(ModBlocks.OLIVE_LOG).wood(ModBlocks.OLIVE_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_OLIVE_LOG).log(ModBlocks.STRIPPED_OLIVE_LOG).wood(ModBlocks.STRIPPED_OLIVE_WOOD);
        OlivePool.stairs(ModBlocks.OLIVE_STAIRS);
        OlivePool.slab(ModBlocks.OLIVE_SLAB);
        OlivePool.button(ModBlocks.OLIVE_BUTTON);
        OlivePool.pressurePlate(ModBlocks.OLIVE_PRESSURE_PLATE);
        OlivePool.fence(ModBlocks.OLIVE_FENCE);
        OlivePool.fenceGate(ModBlocks.OLIVE_FENCE_GATE);
        blockStateModelGenerator.registerDoor(ModBlocks.OLIVE_DOOR);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.OLIVE_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);


        // =================================================
        // ||                  IRONWOOD                   ||
        // =================================================
        BlockStateModelGenerator.BlockTexturePool IronwoodPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.IRONWOOD_PLANKS);
        blockStateModelGenerator.registerSingleton(ModBlocks.IRONWOOD_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerLog(ModBlocks.IRONWOOD_LOG).log(ModBlocks.IRONWOOD_LOG).wood(ModBlocks.IRONWOOD_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_IRONWOOD_LOG).log(ModBlocks.STRIPPED_IRONWOOD_LOG).wood(ModBlocks.STRIPPED_IRONWOOD_WOOD);
        IronwoodPool.stairs(ModBlocks.IRONWOOD_STAIRS);
        IronwoodPool.slab(ModBlocks.IRONWOOD_SLAB);
        IronwoodPool.button(ModBlocks.IRONWOOD_BUTTON);
        IronwoodPool.pressurePlate(ModBlocks.IRONWOOD_PRESSURE_PLATE);
        IronwoodPool.fence(ModBlocks.IRONWOOD_FENCE);
        IronwoodPool.fenceGate(ModBlocks.IRONWOOD_FENCE_GATE);
        blockStateModelGenerator.registerDoor(ModBlocks.IRONWOOD_DOOR);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.IRONWOOD_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        // =================================================
        // ||               PAINTED PLANKS                ||
        // =================================================
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLACK_PAINTED_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLUE_PAINTED_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BROWN_PAINTED_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CYAN_PAINTED_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GRAY_PAINTED_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GREEN_PAINTED_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LIGHT_BLUE_PAINTED_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LIME_PAINTED_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MAGENTA_PAINTED_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORANGE_PAINTED_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PINK_PAINTED_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURPLE_PAINTED_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RED_PAINTED_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LIGHT_GRAY_PAINTED_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WHITE_PAINTED_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.YELLOW_PAINTED_PLANKS);


        // =================================================
        // ||                  CHAIRS                     ||
        // =================================================
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.OAK_CHAIR);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.DARK_OAK_CHAIR);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.ACACIA_CHAIR);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.BAMBOO_CHAIR);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.BIRCH_CHAIR);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.CHERRY_CHAIR);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.CRIMSON_CHAIR);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.JUNGLE_CHAIR);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.MANGROVE_CHAIR);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.SPRUCE_CHAIR);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.WARPED_CHAIR);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.IRONWOOD_CHAIR);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.OLIVE_CHAIR);

        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.GARGOYLE);

        // =================================================
        // ||                  STOOLS                     ||
        // =================================================
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.OAK_STOOL);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.DARK_OAK_STOOL);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.ACACIA_STOOL);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.BAMBOO_STOOL);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.BIRCH_STOOL);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.CHERRY_STOOL);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.CRIMSON_STOOL);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.JUNGLE_STOOL);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.MANGROVE_STOOL);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.SPRUCE_STOOL);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.WARPED_STOOL);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.IRONWOOD_STOOL);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.OLIVE_STOOL);

        // =================================================
        // ||                   HERBS                     ||
        // =================================================
        blockStateModelGenerator.registerCrop(ModBlocks.ALOE_VERA_BLOCK, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.HORSETAIL_BLOCK, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.COHOSH_BLOCK, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.CHAMOMILE_BLOCK, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.CLOUDSBLUFF_BLOCK, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.BLOOD_ORCHID_BLOCK, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.GINSENG_BLOCK, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.MARSHMALLOW_BLOCK, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.VANTA_LILY_BLOCK, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.WIND_THISTLE_BLOCK, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.MOONCAP_MUSHROOM, CustomMushroomBlock.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.registerCrop(ModBlocks.DEATHSTALK_MUSHROOM, CustomMushroomBlock.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.registerCrop(ModBlocks.CORE_ROOT, CustomMushroomBlock.AGE, 0, 1, 2, 3);

        // =================================================
        // ||                AGRICULTURE                  ||
        // =================================================
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.APPLE_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        // =================================================
        // ||                   BUSHES                    ||
        // =================================================
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.TEST_BERRY_BUSH, BlockStateModelGenerator.TintType.NOT_TINTED,
                CustomBushBlock.AGE, 0, 1, 2, 3);

        // =================================================
        // ||             COPPER CANDLE HOLDER            ||
        // =================================================
        registerCopperCandleHolders(blockStateModelGenerator);
        registerDoubleCopperCandleHolders(blockStateModelGenerator);

        // =================================================
        // ||              IRON CANDLE HOLDER             ||
        // =================================================
        registerIronCandleHolders(blockStateModelGenerator);
        registerDoubleIronCandleHolders(blockStateModelGenerator);

        // =================================================
        // ||                COPPER LANTERN               ||
        // =================================================

        blockStateModelGenerator.registerLantern(ModBlocks.COPPER_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.EXPOSED_COPPER_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.WEATHERED_COPPER_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.OXIDIZED_COPPER_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.WAXED_COPPER_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.WAXED_EXPOSED_COPPER_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.WAXED_WEATHERED_COPPER_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN);

        blockStateModelGenerator.registerLantern(ModBlocks.COPPER_SOUL_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.EXPOSED_COPPER_SOUL_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.WEATHERED_COPPER_SOUL_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.OXIDIZED_COPPER_SOUL_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.WAXED_COPPER_SOUL_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.WAXED_WEATHERED_COPPER_SOUL_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.WAXED_OXIDIZED_COPPER_SOUL_LANTERN);

        // =================================================
        // ||             GOLDEN CANDLE HOLDER            ||
        // =================================================
        registerGoldenCandleHolders(blockStateModelGenerator);
        registerDoubleGoldenCandleHolders(blockStateModelGenerator);
    }
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.GOLDEN_DUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.TINY_GOLDEN_DUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_DUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.TINY_IRON_DUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.TINY_GLOWSTONE_DUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.OLIVES, Models.GENERATED);
        itemModelGenerator.register(ModItems.ELIXIR_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ELIXIR_ICON, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHILI_PEPPER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHILI_PEPPER_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.GHOST_PEPPER, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_BERRIES, Models.GENERATED);
        itemModelGenerator.register(ModItems.GRAPES, Models.GENERATED);
        itemModelGenerator.register(ModItems.GRAPE_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CATALOG, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOMATO, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOMATO_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.APPLE_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.HONEY_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.APPLE_JUICE_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.APPLE_JUICE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_APPLE_JUICE_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_APPLE_JUICE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GRAPE_JUICE_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.GRAPE_JUICE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SWEET_BERRY_JUICE_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.SWEET_BERRY_JUICE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.OLIVE_OIL_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.OLIVE_OIL_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.VANTA_OIL_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.VANTA_OIL_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ALE_WORT_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.ALE_WORT_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOW_BERRY_JUICE_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOW_BERRY_JUICE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_BERRY_JUICE_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_BERRY_JUICE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SUGAR_CANE_JUICE_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.SUGAR_CANE_JUICE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPICY_LAMB_STEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.HONEY_GLAZED_CARROTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.ALE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_WINE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SWEET_BERRY_WINE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOW_BERRY_WINE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WINE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CIDER_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MEAD_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RUM_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMBROSIA_BOTTLE, Models.GENERATED);
        ResproutedBoatTypes.getAllBoatItems().forEach(item -> itemModelGenerator.register(item, Models.GENERATED));
    }

    private void registerIronCandleHolders(BlockStateModelGenerator blockStateModelGenerator) {
        ModTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.IRON_CANDLE_HOLDER, "iron_candle_holder", "");
        ModTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.WHITE_IRON_CANDLE_HOLDER, "iron_candle_holder", "white");
        ModTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_GRAY_IRON_CANDLE_HOLDER, "iron_candle_holder", "light_gray");
        ModTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.GRAY_IRON_CANDLE_HOLDER, "iron_candle_holder", "gray");
        ModTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.BLACK_IRON_CANDLE_HOLDER, "iron_candle_holder", "black");
        ModTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.BROWN_IRON_CANDLE_HOLDER, "iron_candle_holder", "brown");
        ModTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.RED_IRON_CANDLE_HOLDER, "iron_candle_holder", "red");
        ModTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.ORANGE_IRON_CANDLE_HOLDER, "iron_candle_holder", "orange");
        ModTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.YELLOW_IRON_CANDLE_HOLDER, "iron_candle_holder", "yellow");
        ModTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.LIME_IRON_CANDLE_HOLDER, "iron_candle_holder", "lime");
        ModTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.CYAN_IRON_CANDLE_HOLDER, "iron_candle_holder", "cyan");
        ModTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_BLUE_IRON_CANDLE_HOLDER, "iron_candle_holder", "light_blue");
        ModTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.BLUE_IRON_CANDLE_HOLDER, "iron_candle_holder", "blue");
        ModTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.PURPLE_IRON_CANDLE_HOLDER, "iron_candle_holder", "purple");
        ModTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.MAGENTA_IRON_CANDLE_HOLDER, "iron_candle_holder", "magenta");
        ModTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.PINK_IRON_CANDLE_HOLDER, "iron_candle_holder", "pink");
        ModTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.GREEN_IRON_CANDLE_HOLDER, "iron_candle_holder", "green"
        );
    }

    private void registerDoubleIronCandleHolders(BlockStateModelGenerator blockStateModelGenerator) {
        ModTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "");
        ModTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.WHITE_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "white");
        ModTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_GRAY_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "light_gray");
        ModTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.GRAY_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "gray");
        ModTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.BLACK_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "black");
        ModTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.BROWN_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "brown");
        ModTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.RED_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "red");
        ModTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.ORANGE_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "orange");
        ModTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.YELLOW_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "yellow");
        ModTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.LIME_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "lime");
        ModTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.CYAN_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "cyan");
        ModTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_BLUE_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "light_blue");
        ModTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.BLUE_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "blue");
        ModTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.PURPLE_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "purple");
        ModTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.MAGENTA_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "magenta");
        ModTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.PINK_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "pink");
        ModTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.GREEN_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "green");
    }

    private void registerGoldenCandleHolders(BlockStateModelGenerator blockStateModelGenerator) {
        ModTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "");
        ModTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.WHITE_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "white");
        ModTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_GRAY_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "light_gray");
        ModTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.GRAY_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "gray");
        ModTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.BLACK_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "black");
        ModTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.BROWN_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "brown");
        ModTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.RED_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "red");
        ModTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.ORANGE_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "orange");
        ModTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.YELLOW_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "yellow");
        ModTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.LIME_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "lime");
        ModTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.CYAN_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "cyan");
        ModTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_BLUE_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "light_blue");
        ModTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.BLUE_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "blue");
        ModTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.PURPLE_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "purple");
        ModTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.MAGENTA_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "magenta");
        ModTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.PINK_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "pink");
        ModTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.GREEN_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "green");
    }

    private void registerDoubleGoldenCandleHolders(BlockStateModelGenerator blockStateModelGenerator) {
        ModTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "");
        ModTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.WHITE_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "white");
        ModTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_GRAY_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "light_gray");
        ModTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.GRAY_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "gray");
        ModTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.BLACK_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "black");
        ModTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.BROWN_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "brown");
        ModTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.RED_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "red");
        ModTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.ORANGE_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "orange");
        ModTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.YELLOW_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "yellow");
        ModTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.LIME_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "lime");
        ModTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.CYAN_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "cyan");
        ModTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_BLUE_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "light_blue");
        ModTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.BLUE_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "blue");
        ModTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.PURPLE_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "purple");
        ModTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.MAGENTA_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "magenta");
        ModTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.PINK_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "pink");
        ModTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.GREEN_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "green");
    }

    private void registerCopperCandleHolders(BlockStateModelGenerator blockStateModelGenerator) {
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.COPPER_CANDLE_HOLDER, "copper_candle_holder", "");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_COPPER_CANDLE_HOLDER, "copper_candle_holder", "");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "");

        // ============ BLACK ============
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.BLACK_COPPER_CANDLE_HOLDER, "copper_candle_holder", "black");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_BLACK_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "black");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_BLACK_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "black");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_BLACK_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "black");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_BLACK_COPPER_CANDLE_HOLDER, "copper_candle_holder", "black");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_BLACK_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "black");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_BLACK_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "black");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_BLACK_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "black");

        // ============ BLUE ============
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.BLUE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "blue");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_BLUE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "blue");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_BLUE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "blue");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_BLUE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "blue");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_BLUE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "blue");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_BLUE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "blue");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_BLUE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "blue");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_BLUE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "blue");

        // ============ BROWN ============
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.BROWN_COPPER_CANDLE_HOLDER, "copper_candle_holder", "brown");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_BROWN_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "brown");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_BROWN_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "brown");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_BROWN_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "brown");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_BROWN_COPPER_CANDLE_HOLDER, "copper_candle_holder", "brown");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_BROWN_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "brown");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_BROWN_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "brown");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_BROWN_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "brown");

        // ============ CYAN ============
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.CYAN_COPPER_CANDLE_HOLDER, "copper_candle_holder", "cyan");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_CYAN_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "cyan");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_CYAN_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "cyan");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_CYAN_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "cyan");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_CYAN_COPPER_CANDLE_HOLDER, "copper_candle_holder", "cyan");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_CYAN_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "cyan");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_CYAN_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "cyan");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_CYAN_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "cyan");

        // ============ GRAY ============
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.GRAY_COPPER_CANDLE_HOLDER, "copper_candle_holder", "gray");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_GRAY_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "gray");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_GRAY_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "gray");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_GRAY_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "gray");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_GRAY_COPPER_CANDLE_HOLDER, "copper_candle_holder", "gray");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_GRAY_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "gray");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_GRAY_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "gray");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_GRAY_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "gray");

        // ============ GREEN ============
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.GREEN_COPPER_CANDLE_HOLDER, "copper_candle_holder", "green");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_GREEN_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "green");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_GREEN_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "green");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_GREEN_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "green");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_GREEN_COPPER_CANDLE_HOLDER, "copper_candle_holder", "green");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_GREEN_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "green");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_GREEN_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "green");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_GREEN_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "green");

        // ============ LIGHT BLUE ============
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_BLUE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "light_blue");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "light_blue");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "light_blue");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "light_blue");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "light_blue");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "light_blue");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "light_blue");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "light_blue");

        // ============ LIGHT GRAY ============
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_GRAY_COPPER_CANDLE_HOLDER, "copper_candle_holder", "light_gray");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "light_gray");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "light_gray");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "light_gray");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, "copper_candle_holder", "light_gray");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "light_gray");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "light_gray");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "light_gray");

        // ============ LIME ============
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.LIME_COPPER_CANDLE_HOLDER, "copper_candle_holder", "lime");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_LIME_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "lime");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_LIME_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "lime");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_LIME_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "lime");

        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_LIME_COPPER_CANDLE_HOLDER, "copper_candle_holder", "lime");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_LIME_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "lime");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_LIME_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "lime");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_LIME_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "lime");

        // ============ MAGENTA ============
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.MAGENTA_COPPER_CANDLE_HOLDER, "copper_candle_holder", "magenta");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_MAGENTA_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "magenta");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_MAGENTA_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "magenta");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_MAGENTA_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "magenta");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_MAGENTA_COPPER_CANDLE_HOLDER, "copper_candle_holder", "magenta");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_MAGENTA_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "magenta");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_MAGENTA_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "magenta");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_MAGENTA_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "magenta");

        // ============ ORANGE ============
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.ORANGE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "orange");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_ORANGE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "orange");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_ORANGE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "orange");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_ORANGE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "orange");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_ORANGE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "orange");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_ORANGE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "orange");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_ORANGE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "orange");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_ORANGE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "orange");

        // ============ PINK ============
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.PINK_COPPER_CANDLE_HOLDER, "copper_candle_holder", "pink");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_PINK_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "pink");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_PINK_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "pink");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_PINK_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "pink");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_PINK_COPPER_CANDLE_HOLDER, "copper_candle_holder", "pink");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_PINK_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "pink");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_PINK_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "pink");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_PINK_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "pink");

        // ============ PURPLE ============
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.PURPLE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "purple");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_PURPLE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "purple");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_PURPLE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "purple");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_PURPLE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "purple");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_PURPLE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "purple");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_PURPLE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "purple");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_PURPLE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "purple");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_PURPLE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "purple");

        // ============ RED ============
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.RED_COPPER_CANDLE_HOLDER, "copper_candle_holder", "red");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_RED_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "red");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_RED_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "red");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_RED_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "red");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_RED_COPPER_CANDLE_HOLDER, "copper_candle_holder", "red");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_RED_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "red");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_RED_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "red");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_RED_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "red");

        // ============ WHITE ============
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WHITE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "white");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_WHITE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "white");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_WHITE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "white");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_WHITE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "white");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WHITE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "white");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_WHITE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "white");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_WHITE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "white");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_WHITE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "white");

        // ============ YELLOW ============
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.YELLOW_COPPER_CANDLE_HOLDER, "copper_candle_holder", "yellow");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_YELLOW_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "yellow");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_YELLOW_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "yellow");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_YELLOW_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "yellow");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_YELLOW_COPPER_CANDLE_HOLDER, "copper_candle_holder", "yellow");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_YELLOW_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "yellow");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_YELLOW_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "yellow");
        ModTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_YELLOW_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "yellow");
    }

    private void registerDoubleCopperCandleHolders(BlockStateModelGenerator blockStateModelGenerator) {
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "");

        // ============ BLACK ============
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.BLACK_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "black");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "black");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "black");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "black");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "black");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "black");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "black");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "black");

        // ============ BLUE ============
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "blue");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "blue");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "blue");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "blue");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "blue");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "blue");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "blue");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "blue");

        // ============ BROWN ============
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.BROWN_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "brown");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "brown");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "brown");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "brown");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "brown");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "brown");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "brown");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "brown");

        // ============ CYAN ============
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.CYAN_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "cyan");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "cyan");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "cyan");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "cyan");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "cyan");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "cyan");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "cyan");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "cyan");

        // ============ GRAY ============
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "gray");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "gray");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "gray");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "gray");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "gray");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "gray");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "gray");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "gray");

        // ============ GREEN ============
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.GREEN_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "green");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "green");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "green");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "green");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "green");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "green");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "green");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "green");

        // ============ LIGHT BLUE ============
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "light_blue");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "light_blue");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "light_blue");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "light_blue");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "light_blue");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "light_blue");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "light_blue");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "light_blue");

        // ============ LIGHT GRAY ============
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "light_gray");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "light_gray");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "light_gray");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "light_gray");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "light_gray");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "light_gray");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "light_gray");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "light_gray");

        // ============ LIME ============
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.LIME_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "lime");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "lime");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "lime");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "lime");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "lime");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "lime");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "lime");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "lime");

        // ============ MAGENTA ============
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "magenta");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "magenta");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "magenta");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "magenta");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "magenta");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "magenta");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "magenta");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "magenta");

        // ============ ORANGE ============
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "orange");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "orange");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "orange");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "orange");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "orange");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "orange");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "orange");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "orange");

        // ============ PINK ============
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.PINK_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "pink");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "pink");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "pink");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "pink");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "pink");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "pink");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "pink");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "pink");

        // ============ PURPLE ============
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "purple");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "purple");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "purple");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "purple");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "purple");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "purple");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "purple");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "purple");

        // ============ RED ============
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.RED_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "red");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "red");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "red");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_RED_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "red");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_RED_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "red");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "red");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "red");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_RED_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "red");

        // ============ WHITE ============
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WHITE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "white");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "white");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "white");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "white");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "white");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "white");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "white");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "white");

        // ============ YELLOW ============
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "yellow");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "yellow");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "yellow");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "yellow");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "yellow");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "yellow");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "yellow");
        ModTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "yellow");
    }
}

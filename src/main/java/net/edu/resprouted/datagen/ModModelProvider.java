package net.edu.resprouted.datagen;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.custom.agriculture.CustomBushBlock;
import net.edu.resprouted.block.custom.agriculture.CustomMushroomBlock;
import net.edu.resprouted.block.custom.agriculture.HerbBlock;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.registry.ResproutedBoatTypes;
import net.edu.resprouted.registry.ResproutedWoodTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.WoodType;
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
        //blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.OLIVE_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);


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
        //blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.IRONWOOD_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

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
        // ||                  CABINETS                   ||
        // =================================================
        ModCabinetTemplateModels.registerCabinetModels(blockStateModelGenerator, ModBlocks.ACACIA_CABINET, WoodType.ACACIA.name());
        ModCabinetTemplateModels.registerCabinetModels(blockStateModelGenerator, ModBlocks.CHERRY_CABINET, WoodType.CHERRY.name());
        ModCabinetTemplateModels.registerCabinetModels(blockStateModelGenerator, ModBlocks.OAK_CABINET, WoodType.OAK.name());
        ModCabinetTemplateModels.registerCabinetModels(blockStateModelGenerator, ModBlocks.DARK_OAK_CABINET, WoodType.DARK_OAK.name());
        ModCabinetTemplateModels.registerCabinetModels(blockStateModelGenerator, ModBlocks.BIRCH_CABINET, WoodType.BIRCH.name());
        ModCabinetTemplateModels.registerCabinetModels(blockStateModelGenerator, ModBlocks.SPRUCE_CABINET, WoodType.SPRUCE.name());
        ModCabinetTemplateModels.registerCabinetModels(blockStateModelGenerator, ModBlocks.MANGROVE_CABINET, WoodType.MANGROVE.name());
        ModCabinetTemplateModels.registerCabinetModels(blockStateModelGenerator, ModBlocks.JUNGLE_CABINET, WoodType.JUNGLE.name());
        ModCabinetTemplateModels.registerCabinetModels(blockStateModelGenerator, ModBlocks.CRIMSON_CABINET, WoodType.CRIMSON.name());
        ModCabinetTemplateModels.registerCabinetModels(blockStateModelGenerator, ModBlocks.WARPED_CABINET, WoodType.WARPED.name());
        ModCabinetTemplateModels.registerCabinetModels(blockStateModelGenerator, ModBlocks.IRONWOOD_CABINET, ResproutedWoodTypes.getName(ResproutedWoodTypes.IRONWOOD));
        ModCabinetTemplateModels.registerCabinetModels(blockStateModelGenerator, ModBlocks.OLIVE_CABINET, ResproutedWoodTypes.getName(ResproutedWoodTypes.OLIVE));

        // =================================================
        // ||                   HERBS                     ||
        // =================================================
        blockStateModelGenerator.registerCrop(ModBlocks.ALOE_VERA, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.HORSETAIL, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.COHOSH, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.CHAMOMILE, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.CLOUDSBLUFF, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.BLOOD_ORCHID, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.GINSENG, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.MARSHMALLOW, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.VANTA_LILY, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.WIND_THISTLE, HerbBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
        blockStateModelGenerator.registerCrop(ModBlocks.MOONCAP_MUSHROOM, CustomMushroomBlock.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.registerCrop(ModBlocks.DEATHSTALK_MUSHROOM, CustomMushroomBlock.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.registerCrop(ModBlocks.CORE_ROOT, CustomMushroomBlock.AGE, 0, 1, 2, 3);

        // =================================================
        // ||                   POTTED                    ||
        // =================================================
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.OLIVE_SAPLING, ModBlocks.POTTED_OLIVE_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.IRONWOOD_SAPLING, ModBlocks.POTTED_IRONWOOD_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        // =================================================
        // ||                AGRICULTURE                  ||
        // =================================================
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.APPLE_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        // =================================================
        // ||                   BUSHES                    ||
        // =================================================
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.TEST_BERRY_BUSH, BlockStateModelGenerator.TintType.NOT_TINTED, CustomBushBlock.AGE, 0, 1, 2, 3);

        // =================================================
        // ||              IRON CANDLE HOLDER             ||
        // =================================================
        registerIronCandleHolders(blockStateModelGenerator);
        registerDoubleIronCandleHolders(blockStateModelGenerator);

        // =================================================
        // ||             GOLDEN CANDLE HOLDER            ||
        // =================================================
        registerGoldenCandleHolders(blockStateModelGenerator);
        registerDoubleGoldenCandleHolders(blockStateModelGenerator);

        // =================================================
        // ||             COPPER CANDLE HOLDER            ||
        // =================================================
        registerCopperCandleHolders(blockStateModelGenerator);
        registerDoubleCopperCandleHolders(blockStateModelGenerator);

        // =================================================
        // ||               ORNATE LANTERN                ||
        // =================================================

        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.ORNATE_LANTERN, blockStateModelGenerator);
        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.ORNATE_SOUL_LANTERN, blockStateModelGenerator);
        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.ORNATE_GOLD_LANTERN, blockStateModelGenerator);
        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.ORNATE_GOLD_SOUL_LANTERN, blockStateModelGenerator);

        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.ORNATE_COPPER_LANTERN, blockStateModelGenerator);
        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.EXPOSED_ORNATE_COPPER_LANTERN, blockStateModelGenerator);
        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.WEATHERED_ORNATE_COPPER_LANTERN, blockStateModelGenerator);
        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.OXIDIZED_ORNATE_COPPER_LANTERN, blockStateModelGenerator);
        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.WAXED_ORNATE_COPPER_LANTERN, blockStateModelGenerator);
        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.WAXED_EXPOSED_ORNATE_COPPER_LANTERN, blockStateModelGenerator);
        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.WAXED_WEATHERED_ORNATE_COPPER_LANTERN, blockStateModelGenerator);
        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.WAXED_OXIDIZED_ORNATE_COPPER_LANTERN, blockStateModelGenerator);
        // Sould Fire
        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.ORNATE_COPPER_SOUL_LANTERN, blockStateModelGenerator);
        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.EXPOSED_ORNATE_COPPER_SOUL_LANTERN, blockStateModelGenerator);
        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.WEATHERED_ORNATE_COPPER_SOUL_LANTERN, blockStateModelGenerator);
        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.OXIDIZED_ORNATE_COPPER_SOUL_LANTERN, blockStateModelGenerator);
        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.WAXED_ORNATE_COPPER_SOUL_LANTERN, blockStateModelGenerator);
        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.WAXED_EXPOSED_ORNATE_COPPER_SOUL_LANTERN, blockStateModelGenerator);
        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.WAXED_WEATHERED_ORNATE_COPPER_SOUL_LANTERN, blockStateModelGenerator);
        ModOrnateLanternTemplateModels.registerOrnateLantern(ModBlocks.WAXED_OXIDIZED_ORNATE_COPPER_SOUL_LANTERN, blockStateModelGenerator);

    }
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.GOLDEN_DUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.TINY_GOLDEN_DUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_DUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.TALLOW, Models.GENERATED);
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
        ModCandleHolderTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.IRON_CANDLE_HOLDER, "iron_candle_holder", "");
        ModCandleHolderTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.WHITE_IRON_CANDLE_HOLDER, "iron_candle_holder", "white");
        ModCandleHolderTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_GRAY_IRON_CANDLE_HOLDER, "iron_candle_holder", "light_gray");
        ModCandleHolderTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.GRAY_IRON_CANDLE_HOLDER, "iron_candle_holder", "gray");
        ModCandleHolderTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.BLACK_IRON_CANDLE_HOLDER, "iron_candle_holder", "black");
        ModCandleHolderTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.BROWN_IRON_CANDLE_HOLDER, "iron_candle_holder", "brown");
        ModCandleHolderTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.RED_IRON_CANDLE_HOLDER, "iron_candle_holder", "red");
        ModCandleHolderTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.ORANGE_IRON_CANDLE_HOLDER, "iron_candle_holder", "orange");
        ModCandleHolderTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.YELLOW_IRON_CANDLE_HOLDER, "iron_candle_holder", "yellow");
        ModCandleHolderTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.LIME_IRON_CANDLE_HOLDER, "iron_candle_holder", "lime");
        ModCandleHolderTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.CYAN_IRON_CANDLE_HOLDER, "iron_candle_holder", "cyan");
        ModCandleHolderTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_BLUE_IRON_CANDLE_HOLDER, "iron_candle_holder", "light_blue");
        ModCandleHolderTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.BLUE_IRON_CANDLE_HOLDER, "iron_candle_holder", "blue");
        ModCandleHolderTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.PURPLE_IRON_CANDLE_HOLDER, "iron_candle_holder", "purple");
        ModCandleHolderTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.MAGENTA_IRON_CANDLE_HOLDER, "iron_candle_holder", "magenta");
        ModCandleHolderTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.PINK_IRON_CANDLE_HOLDER, "iron_candle_holder", "pink");
        ModCandleHolderTemplateModels.registerIronCandleHolderModels(blockStateModelGenerator, ModBlocks.GREEN_IRON_CANDLE_HOLDER, "iron_candle_holder", "green"
        );
    }

    private void registerDoubleIronCandleHolders(BlockStateModelGenerator blockStateModelGenerator) {
        ModCandleHolderTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "");
        ModCandleHolderTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.WHITE_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "white");
        ModCandleHolderTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_GRAY_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "light_gray");
        ModCandleHolderTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.GRAY_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "gray");
        ModCandleHolderTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.BLACK_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "black");
        ModCandleHolderTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.BROWN_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "brown");
        ModCandleHolderTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.RED_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "red");
        ModCandleHolderTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.ORANGE_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "orange");
        ModCandleHolderTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.YELLOW_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "yellow");
        ModCandleHolderTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.LIME_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "lime");
        ModCandleHolderTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.CYAN_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "cyan");
        ModCandleHolderTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_BLUE_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "light_blue");
        ModCandleHolderTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.BLUE_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "blue");
        ModCandleHolderTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.PURPLE_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "purple");
        ModCandleHolderTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.MAGENTA_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "magenta");
        ModCandleHolderTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.PINK_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "pink");
        ModCandleHolderTemplateModels.registerDoubleIronCandleHolderModels(blockStateModelGenerator, ModBlocks.GREEN_DOUBLE_IRON_CANDLE_HOLDER, "double_iron_candle_holder", "green");
    }

    private void registerGoldenCandleHolders(BlockStateModelGenerator blockStateModelGenerator) {
        ModCandleHolderTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "");
        ModCandleHolderTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.WHITE_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "white");
        ModCandleHolderTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_GRAY_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "light_gray");
        ModCandleHolderTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.GRAY_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "gray");
        ModCandleHolderTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.BLACK_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "black");
        ModCandleHolderTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.BROWN_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "brown");
        ModCandleHolderTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.RED_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "red");
        ModCandleHolderTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.ORANGE_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "orange");
        ModCandleHolderTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.YELLOW_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "yellow");
        ModCandleHolderTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.LIME_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "lime");
        ModCandleHolderTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.CYAN_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "cyan");
        ModCandleHolderTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_BLUE_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "light_blue");
        ModCandleHolderTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.BLUE_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "blue");
        ModCandleHolderTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.PURPLE_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "purple");
        ModCandleHolderTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.MAGENTA_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "magenta");
        ModCandleHolderTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.PINK_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "pink");
        ModCandleHolderTemplateModels.registerGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.GREEN_GOLDEN_CANDLE_HOLDER, "golden_candle_holder", "green");
    }

    private void registerDoubleGoldenCandleHolders(BlockStateModelGenerator blockStateModelGenerator) {
        ModCandleHolderTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "");
        ModCandleHolderTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.WHITE_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "white");
        ModCandleHolderTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_GRAY_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "light_gray");
        ModCandleHolderTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.GRAY_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "gray");
        ModCandleHolderTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.BLACK_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "black");
        ModCandleHolderTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.BROWN_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "brown");
        ModCandleHolderTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.RED_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "red");
        ModCandleHolderTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.ORANGE_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "orange");
        ModCandleHolderTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.YELLOW_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "yellow");
        ModCandleHolderTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.LIME_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "lime");
        ModCandleHolderTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.CYAN_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "cyan");
        ModCandleHolderTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_BLUE_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "light_blue");
        ModCandleHolderTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.BLUE_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "blue");
        ModCandleHolderTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.PURPLE_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "purple");
        ModCandleHolderTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.MAGENTA_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "magenta");
        ModCandleHolderTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.PINK_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "pink");
        ModCandleHolderTemplateModels.registerDoubleGoldenCandleHolderModels(blockStateModelGenerator, ModBlocks.GREEN_DOUBLE_GOLDEN_CANDLE_HOLDER, "double_golden_candle_holder", "green");
    }

    private void registerCopperCandleHolders(BlockStateModelGenerator blockStateModelGenerator) {
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.COPPER_CANDLE_HOLDER, "copper_candle_holder", "");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_COPPER_CANDLE_HOLDER, "copper_candle_holder", "");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "");

        // ============ BLACK ============
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.BLACK_COPPER_CANDLE_HOLDER, "copper_candle_holder", "black");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_BLACK_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "black");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_BLACK_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "black");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_BLACK_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "black");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_BLACK_COPPER_CANDLE_HOLDER, "copper_candle_holder", "black");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_BLACK_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "black");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_BLACK_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "black");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_BLACK_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "black");

        // ============ BLUE ============
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.BLUE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "blue");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_BLUE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "blue");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_BLUE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "blue");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_BLUE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "blue");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_BLUE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "blue");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_BLUE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "blue");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_BLUE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "blue");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_BLUE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "blue");

        // ============ BROWN ============
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.BROWN_COPPER_CANDLE_HOLDER, "copper_candle_holder", "brown");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_BROWN_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "brown");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_BROWN_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "brown");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_BROWN_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "brown");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_BROWN_COPPER_CANDLE_HOLDER, "copper_candle_holder", "brown");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_BROWN_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "brown");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_BROWN_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "brown");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_BROWN_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "brown");

        // ============ CYAN ============
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.CYAN_COPPER_CANDLE_HOLDER, "copper_candle_holder", "cyan");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_CYAN_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "cyan");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_CYAN_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "cyan");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_CYAN_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "cyan");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_CYAN_COPPER_CANDLE_HOLDER, "copper_candle_holder", "cyan");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_CYAN_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "cyan");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_CYAN_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "cyan");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_CYAN_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "cyan");

        // ============ GRAY ============
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.GRAY_COPPER_CANDLE_HOLDER, "copper_candle_holder", "gray");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_GRAY_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "gray");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_GRAY_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "gray");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_GRAY_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "gray");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_GRAY_COPPER_CANDLE_HOLDER, "copper_candle_holder", "gray");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_GRAY_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "gray");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_GRAY_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "gray");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_GRAY_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "gray");

        // ============ GREEN ============
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.GREEN_COPPER_CANDLE_HOLDER, "copper_candle_holder", "green");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_GREEN_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "green");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_GREEN_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "green");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_GREEN_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "green");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_GREEN_COPPER_CANDLE_HOLDER, "copper_candle_holder", "green");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_GREEN_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "green");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_GREEN_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "green");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_GREEN_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "green");

        // ============ LIGHT BLUE ============
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_BLUE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "light_blue");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "light_blue");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "light_blue");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "light_blue");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "light_blue");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "light_blue");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "light_blue");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "light_blue");

        // ============ LIGHT GRAY ============
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_GRAY_COPPER_CANDLE_HOLDER, "copper_candle_holder", "light_gray");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "light_gray");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "light_gray");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "light_gray");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, "copper_candle_holder", "light_gray");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "light_gray");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "light_gray");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "light_gray");

        // ============ LIME ============
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.LIME_COPPER_CANDLE_HOLDER, "copper_candle_holder", "lime");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_LIME_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "lime");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_LIME_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "lime");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_LIME_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "lime");

        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_LIME_COPPER_CANDLE_HOLDER, "copper_candle_holder", "lime");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_LIME_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "lime");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_LIME_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "lime");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_LIME_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "lime");

        // ============ MAGENTA ============
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.MAGENTA_COPPER_CANDLE_HOLDER, "copper_candle_holder", "magenta");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_MAGENTA_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "magenta");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_MAGENTA_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "magenta");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_MAGENTA_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "magenta");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_MAGENTA_COPPER_CANDLE_HOLDER, "copper_candle_holder", "magenta");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_MAGENTA_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "magenta");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_MAGENTA_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "magenta");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_MAGENTA_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "magenta");

        // ============ ORANGE ============
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.ORANGE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "orange");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_ORANGE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "orange");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_ORANGE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "orange");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_ORANGE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "orange");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_ORANGE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "orange");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_ORANGE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "orange");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_ORANGE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "orange");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_ORANGE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "orange");

        // ============ PINK ============
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.PINK_COPPER_CANDLE_HOLDER, "copper_candle_holder", "pink");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_PINK_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "pink");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_PINK_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "pink");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_PINK_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "pink");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_PINK_COPPER_CANDLE_HOLDER, "copper_candle_holder", "pink");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_PINK_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "pink");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_PINK_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "pink");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_PINK_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "pink");

        // ============ PURPLE ============
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.PURPLE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "purple");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_PURPLE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "purple");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_PURPLE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "purple");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_PURPLE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "purple");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_PURPLE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "purple");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_PURPLE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "purple");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_PURPLE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "purple");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_PURPLE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "purple");

        // ============ RED ============
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.RED_COPPER_CANDLE_HOLDER, "copper_candle_holder", "red");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_RED_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "red");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_RED_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "red");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_RED_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "red");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_RED_COPPER_CANDLE_HOLDER, "copper_candle_holder", "red");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_RED_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "red");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_RED_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "red");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_RED_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "red");

        // ============ WHITE ============
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WHITE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "white");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_WHITE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "white");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_WHITE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "white");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_WHITE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "white");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WHITE_COPPER_CANDLE_HOLDER, "copper_candle_holder", "white");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_WHITE_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "white");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_WHITE_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "white");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_WHITE_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "white");

        // ============ YELLOW ============
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.YELLOW_COPPER_CANDLE_HOLDER, "copper_candle_holder", "yellow");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_YELLOW_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "yellow");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_YELLOW_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "yellow");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_YELLOW_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "yellow");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_YELLOW_COPPER_CANDLE_HOLDER, "copper_candle_holder", "yellow");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_YELLOW_COPPER_CANDLE_HOLDER, "exposed_copper_candle_holder", "yellow");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_YELLOW_COPPER_CANDLE_HOLDER, "weathered_copper_candle_holder", "yellow");
        ModCandleHolderTemplateModels.registerCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_YELLOW_COPPER_CANDLE_HOLDER, "oxidized_copper_candle_holder", "yellow");
    }

    private void registerDoubleCopperCandleHolders(BlockStateModelGenerator blockStateModelGenerator) {
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "");

        // ============ BLACK ============
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.BLACK_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "black");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "black");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "black");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "black");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "black");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "black");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "black");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "black");

        // ============ BLUE ============
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "blue");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "blue");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "blue");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "blue");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "blue");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "blue");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "blue");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "blue");

        // ============ BROWN ============
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.BROWN_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "brown");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "brown");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "brown");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "brown");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "brown");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "brown");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "brown");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "brown");

        // ============ CYAN ============
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.CYAN_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "cyan");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "cyan");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "cyan");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "cyan");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "cyan");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "cyan");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "cyan");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "cyan");

        // ============ GRAY ============
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "gray");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "gray");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "gray");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "gray");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "gray");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "gray");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "gray");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "gray");

        // ============ GREEN ============
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.GREEN_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "green");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "green");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "green");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "green");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "green");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "green");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "green");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "green");

        // ============ LIGHT BLUE ============
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "light_blue");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "light_blue");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "light_blue");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "light_blue");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "light_blue");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "light_blue");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "light_blue");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "light_blue");

        // ============ LIGHT GRAY ============
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "light_gray");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "light_gray");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "light_gray");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "light_gray");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "light_gray");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "light_gray");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "light_gray");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "light_gray");

        // ============ LIME ============
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.LIME_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "lime");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "lime");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "lime");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "lime");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "lime");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "lime");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "lime");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "lime");

        // ============ MAGENTA ============
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "magenta");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "magenta");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "magenta");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "magenta");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "magenta");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "magenta");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "magenta");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "magenta");

        // ============ ORANGE ============
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "orange");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "orange");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "orange");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "orange");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "orange");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "orange");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "orange");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "orange");

        // ============ PINK ============
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.PINK_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "pink");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "pink");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "pink");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "pink");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "pink");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "pink");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "pink");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "pink");

        // ============ PURPLE ============
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "purple");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "purple");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "purple");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "purple");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "purple");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "purple");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "purple");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "purple");

        // ============ RED ============
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.RED_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "red");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "red");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "red");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_RED_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "red");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_RED_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "red");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "red");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "red");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_RED_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "red");

        // ============ WHITE ============
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WHITE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "white");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "white");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "white");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "white");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "white");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "white");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "white");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "white");

        // ============ YELLOW ============
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "yellow");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.EXPOSED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "yellow");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WEATHERED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "yellow");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.OXIDIZED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "yellow");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, "double_copper_candle_holder", "yellow");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, "exposed_double_copper_candle_holder", "yellow");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, "weathered_double_copper_candle_holder", "yellow");
        ModCandleHolderTemplateModels.registerDoubleCopperCandleHolderModels(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, "oxidized_double_copper_candle_holder", "yellow");
    }
}

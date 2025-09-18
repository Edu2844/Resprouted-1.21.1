package net.edu.resprouted.datagen;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.custom.agriculture.BlueBerrieBush;
import net.edu.resprouted.block.custom.agriculture.CustomMushroomBlock;
import net.edu.resprouted.block.custom.agriculture.HerbBlock;
import net.edu.resprouted.item.ModItems;
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
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ANDESITE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_ANDESITE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_ANDESITE);

        // =================================================
        // ||                  DIORITE                    ||
        // =================================================
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DIORITE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_DIORITE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_DIORITE);

        // =================================================
        // ||                  GRANITE                    ||
        // =================================================
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GRANITE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_GRANITE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_GRANITE);

        // =================================================
        // ||                   STONE                     ||
        // =================================================
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_STONE);

        // =================================================
        // ||                SANDSTONE                    ||
        // =================================================
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_SANDSTONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SANDSTONE_BRICKS);

        // =================================================
        // ||                RED SANDSTONE                ||
        // =================================================
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_RED_SANDSTONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RED_SANDSTONE_BRICKS);

        // =================================================
        // ||                WROUGHT IRON                 ||
        // =================================================
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WROUGHT_IRON_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CUT_WROUGHT_IRON_BLOCK);

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
        blockStateModelGenerator.registerTrapdoor(ModBlocks.OLIVE_TRAPDOOR);
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
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.BLUE_BERRY_BUSH, BlockStateModelGenerator.TintType.NOT_TINTED,
                BlueBerrieBush.AGE, 0, 1, 2, 3);

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
        itemModelGenerator.register(ModItems.LAMB_STEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.HONEY_GLAZED_CARROTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.OLIVE_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.OLIVE_CHEST_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRONWOOD_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRONWOOD_CHEST_BOAT, Models.GENERATED);

        itemModelGenerator.register(ModItems.ALE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_WINE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SWEET_BERRY_WINE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOW_BERRY_WINE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WINE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CIDER_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MEAD_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMBROSIA_BOTTLE, Models.GENERATED);
    }
}

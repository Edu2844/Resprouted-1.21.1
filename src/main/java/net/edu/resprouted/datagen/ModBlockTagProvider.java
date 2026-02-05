package net.edu.resprouted.datagen;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.util.misc.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                        // Slate
                .add(ModBlocks.ANDESITE_BRICKS,
                        ModBlocks.ANDESITE_BRICK_STAIRS,
                        ModBlocks.ANDESITE_BRICK_WALL,
                        ModBlocks.ANDESITE_BRICK_SLAB,
                        ModBlocks.CRACKED_ANDESITE_BRICKS,
                        ModBlocks.CHISELED_ANDESITE,
                        ModBlocks.ANDESITE_PILLAR,

                        // Diorite
                        ModBlocks.DIORITE_BRICKS,
                        ModBlocks.DIORITE_BRICK_STAIRS,
                        ModBlocks.DIORITE_BRICK_WALL,
                        ModBlocks.DIORITE_BRICK_SLAB,
                        ModBlocks.CRACKED_DIORITE_BRICKS,
                        ModBlocks.CHISELED_DIORITE,
                        ModBlocks.DIORITE_PILLAR,

                        // Granite
                        ModBlocks.GRANITE_BRICKS,
                        ModBlocks.GRANITE_BRICK_STAIRS,
                        ModBlocks.GRANITE_BRICK_WALL,
                        ModBlocks.GRANITE_BRICK_SLAB,
                        ModBlocks.CRACKED_GRANITE_BRICKS,
                        ModBlocks.CHISELED_GRANITE,
                        ModBlocks.GRANITE_PILLAR,

                        // Stone
                        ModBlocks.POLISHED_STONE,
                        ModBlocks.POLISHED_STONE_STAIRS,
                        ModBlocks.POLISHED_STONE_WALL,
                        ModBlocks.POLISHED_STONE_SLAB,
                        ModBlocks.STONE_PILLAR,
                        ModBlocks.GARGOYLE,

                        // Sandstone
                        ModBlocks.SANDSTONE_BRICKS,
                        ModBlocks.SANDSTONE_BRICK_STAIRS,
                        ModBlocks.SANDSTONE_BRICK_WALL,
                        ModBlocks.SANDSTONE_BRICK_SLAB,
                        ModBlocks.SANDSTONE_PILLAR,

                        // Red Sandstone
                        ModBlocks.RED_SANDSTONE_BRICKS,
                        ModBlocks.RED_SANDSTONE_BRICK_STAIRS,
                        ModBlocks.RED_SANDSTONE_BRICK_WALL,
                        ModBlocks.RED_SANDSTONE_BRICK_SLAB,
                        ModBlocks.RED_SANDSTONE_PILLAR,

                        // Wrought Iron
                        ModBlocks.WROUGHT_IRON_BLOCK,
                        ModBlocks.CUT_WROUGHT_IRON_BLOCK,
                        ModBlocks.CUT_WROUGHT_IRON_STAIRS,
                        ModBlocks.CUT_WROUGHT_IRON_SLAB,
                        ModBlocks.WROUGHT_IRON_BULB,
                        ModBlocks.WROUGHT_IRON_BARS,

                        // Chains
                        ModBlocks.GOLDEN_CHAIN,
                        ModBlocks.COPPER_CHAIN,
                        ModBlocks.EXPOSED_COPPER_CHAIN,
                        ModBlocks.WEATHERED_COPPER_CHAIN,
                        ModBlocks.OXIDIZED_COPPER_CHAIN,
                        ModBlocks.WAXED_COPPER_CHAIN,
                        ModBlocks.WAXED_EXPOSED_COPPER_CHAIN,
                        ModBlocks.WAXED_WEATHERED_COPPER_CHAIN,
                        ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN,

                        // Chandeliers
                        ModBlocks.CHANDELIER,
                        ModBlocks.GOLDEN_CHANDELIER,
                        ModBlocks.COPPER_CHANDELIER,
                        ModBlocks.EXPOSED_COPPER_CHANDELIER,
                        ModBlocks.WEATHERED_COPPER_CHANDELIER,
                        ModBlocks.OXIDIZED_COPPER_CHANDELIER,
                        ModBlocks.WAXED_COPPER_CHANDELIER,
                        ModBlocks.WAXED_EXPOSED_COPPER_CHANDELIER,
                        ModBlocks.WAXED_WEATHERED_COPPER_CHANDELIER,
                        ModBlocks.WAXED_OXIDIZED_COPPER_CHANDELIER,

                        // Lanterns
                        ModBlocks.ORNATE_LANTERN,
                        ModBlocks.ORNATE_SOUL_LANTERN,
                        ModBlocks.ORNATE_GOLD_LANTERN,
                        ModBlocks.ORNATE_GOLD_SOUL_LANTERN,
                        ModBlocks.ORNATE_COPPER_LANTERN,
                        ModBlocks.EXPOSED_ORNATE_COPPER_LANTERN,
                        ModBlocks.WEATHERED_ORNATE_COPPER_LANTERN,
                        ModBlocks.OXIDIZED_ORNATE_COPPER_LANTERN,
                        ModBlocks.WAXED_ORNATE_COPPER_LANTERN,
                        ModBlocks.WAXED_EXPOSED_ORNATE_COPPER_LANTERN,
                        ModBlocks.WAXED_WEATHERED_ORNATE_COPPER_LANTERN,
                        ModBlocks.WAXED_OXIDIZED_ORNATE_COPPER_LANTERN,
                        ModBlocks.ORNATE_COPPER_SOUL_LANTERN,
                        ModBlocks.EXPOSED_ORNATE_COPPER_SOUL_LANTERN,
                        ModBlocks.WEATHERED_ORNATE_COPPER_SOUL_LANTERN,
                        ModBlocks.OXIDIZED_ORNATE_COPPER_SOUL_LANTERN,
                        ModBlocks.WAXED_ORNATE_COPPER_SOUL_LANTERN,
                        ModBlocks.WAXED_EXPOSED_ORNATE_COPPER_SOUL_LANTERN,
                        ModBlocks.WAXED_WEATHERED_ORNATE_COPPER_SOUL_LANTERN,
                        ModBlocks.WAXED_OXIDIZED_ORNATE_COPPER_SOUL_LANTERN,

                        ModBlocks.DRYING_BASIN,

                        //Jar
                        ModBlocks.JAR,
                        ModBlocks.WHITE_JAR,
                        ModBlocks.ORANGE_JAR,
                        ModBlocks.MAGENTA_JAR,
                        ModBlocks.LIGHT_BLUE_JAR,
                        ModBlocks.YELLOW_JAR,
                        ModBlocks.LIME_JAR,
                        ModBlocks.PINK_JAR,
                        ModBlocks.GRAY_JAR,
                        ModBlocks.LIGHT_GRAY_JAR,
                        ModBlocks.CYAN_JAR,
                        ModBlocks.PURPLE_JAR,
                        ModBlocks.BLUE_JAR,
                        ModBlocks.BROWN_JAR,
                        ModBlocks.GREEN_JAR,
                        ModBlocks.RED_JAR,
                        ModBlocks.BLACK_JAR,

                        //Urn
                        ModBlocks.URN,
                        ModBlocks.WHITE_URN,
                        ModBlocks.ORANGE_URN,
                        ModBlocks.MAGENTA_URN,
                        ModBlocks.LIGHT_BLUE_URN,
                        ModBlocks.YELLOW_URN,
                        ModBlocks.LIME_URN,
                        ModBlocks.PINK_URN,
                        ModBlocks.GRAY_URN,
                        ModBlocks.LIGHT_GRAY_URN,
                        ModBlocks.CYAN_URN,
                        ModBlocks.PURPLE_URN,
                        ModBlocks.BLUE_URN,
                        ModBlocks.BROWN_URN,
                        ModBlocks.GREEN_URN,
                        ModBlocks.RED_URN,
                        ModBlocks.BLACK_URN,

                        // Alchemy
                        ModBlocks.BASIC_CONDENSER,
                        ModBlocks.ADVANCED_CONDENSER,
                        ModBlocks.BASIC_RETORT,
                        ModBlocks.ADVANCED_RETORT,

                        // Iron Candle Holders
                        ModBlocks.IRON_CANDLE_HOLDER,
                        ModBlocks.WHITE_IRON_CANDLE_HOLDER,
                        ModBlocks.LIGHT_GRAY_IRON_CANDLE_HOLDER,
                        ModBlocks.GRAY_IRON_CANDLE_HOLDER,
                        ModBlocks.BLACK_IRON_CANDLE_HOLDER,
                        ModBlocks.BROWN_IRON_CANDLE_HOLDER,
                        ModBlocks.RED_IRON_CANDLE_HOLDER,
                        ModBlocks.ORANGE_IRON_CANDLE_HOLDER,
                        ModBlocks.YELLOW_IRON_CANDLE_HOLDER,
                        ModBlocks.LIME_IRON_CANDLE_HOLDER,
                        ModBlocks.GREEN_IRON_CANDLE_HOLDER,
                        ModBlocks.CYAN_IRON_CANDLE_HOLDER,
                        ModBlocks.LIGHT_BLUE_IRON_CANDLE_HOLDER,
                        ModBlocks.BLUE_IRON_CANDLE_HOLDER,
                        ModBlocks.PURPLE_IRON_CANDLE_HOLDER,
                        ModBlocks.MAGENTA_IRON_CANDLE_HOLDER,
                        ModBlocks.PINK_IRON_CANDLE_HOLDER,

                        // Double Iron Candle Holders
                        ModBlocks.DOUBLE_IRON_CANDLE_HOLDER,
                        ModBlocks.WHITE_DOUBLE_IRON_CANDLE_HOLDER,
                        ModBlocks.LIGHT_GRAY_DOUBLE_IRON_CANDLE_HOLDER,
                        ModBlocks.GRAY_DOUBLE_IRON_CANDLE_HOLDER,
                        ModBlocks.BLACK_DOUBLE_IRON_CANDLE_HOLDER,
                        ModBlocks.BROWN_DOUBLE_IRON_CANDLE_HOLDER,
                        ModBlocks.RED_DOUBLE_IRON_CANDLE_HOLDER,
                        ModBlocks.ORANGE_DOUBLE_IRON_CANDLE_HOLDER,
                        ModBlocks.YELLOW_DOUBLE_IRON_CANDLE_HOLDER,
                        ModBlocks.LIME_DOUBLE_IRON_CANDLE_HOLDER,
                        ModBlocks.GREEN_DOUBLE_IRON_CANDLE_HOLDER,
                        ModBlocks.CYAN_DOUBLE_IRON_CANDLE_HOLDER,
                        ModBlocks.LIGHT_BLUE_DOUBLE_IRON_CANDLE_HOLDER,
                        ModBlocks.BLUE_DOUBLE_IRON_CANDLE_HOLDER,
                        ModBlocks.PURPLE_DOUBLE_IRON_CANDLE_HOLDER,
                        ModBlocks.MAGENTA_DOUBLE_IRON_CANDLE_HOLDER,
                        ModBlocks.PINK_DOUBLE_IRON_CANDLE_HOLDER,

                        // Golden Candle Holders
                        ModBlocks.GOLDEN_CANDLE_HOLDER,
                        ModBlocks.WHITE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.LIGHT_GRAY_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.GRAY_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.BLACK_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.BROWN_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.RED_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.ORANGE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.YELLOW_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.LIME_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.GREEN_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.CYAN_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.LIGHT_BLUE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.BLUE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.PURPLE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.MAGENTA_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.PINK_GOLDEN_CANDLE_HOLDER,

                        // Double Golden Candle Holders
                        ModBlocks.DOUBLE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.WHITE_DOUBLE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.LIGHT_GRAY_DOUBLE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.GRAY_DOUBLE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.BLACK_DOUBLE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.BROWN_DOUBLE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.RED_DOUBLE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.ORANGE_DOUBLE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.YELLOW_DOUBLE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.LIME_DOUBLE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.GREEN_DOUBLE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.CYAN_DOUBLE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.LIGHT_BLUE_DOUBLE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.BLUE_DOUBLE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.PURPLE_DOUBLE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.MAGENTA_DOUBLE_GOLDEN_CANDLE_HOLDER,
                        ModBlocks.PINK_DOUBLE_GOLDEN_CANDLE_HOLDER,

                        // Copper Candle Holders
                        ModBlocks.COPPER_CANDLE_HOLDER,
                        ModBlocks.WHITE_COPPER_CANDLE_HOLDER,
                        ModBlocks.LIGHT_GRAY_COPPER_CANDLE_HOLDER,
                        ModBlocks.GRAY_COPPER_CANDLE_HOLDER,
                        ModBlocks.BLACK_COPPER_CANDLE_HOLDER,
                        ModBlocks.BROWN_COPPER_CANDLE_HOLDER,
                        ModBlocks.RED_COPPER_CANDLE_HOLDER,
                        ModBlocks.ORANGE_COPPER_CANDLE_HOLDER,
                        ModBlocks.YELLOW_COPPER_CANDLE_HOLDER,
                        ModBlocks.LIME_COPPER_CANDLE_HOLDER,
                        ModBlocks.GREEN_COPPER_CANDLE_HOLDER,
                        ModBlocks.CYAN_COPPER_CANDLE_HOLDER,
                        ModBlocks.LIGHT_BLUE_COPPER_CANDLE_HOLDER,
                        ModBlocks.BLUE_COPPER_CANDLE_HOLDER,
                        ModBlocks.PURPLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.MAGENTA_COPPER_CANDLE_HOLDER,
                        ModBlocks.PINK_COPPER_CANDLE_HOLDER,

                        // Exposed Copper Candle Holders
                        ModBlocks.EXPOSED_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_WHITE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_LIGHT_GRAY_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_GRAY_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_BLACK_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_BROWN_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_RED_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_ORANGE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_YELLOW_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_LIME_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_GREEN_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_CYAN_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_LIGHT_BLUE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_BLUE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_PURPLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_MAGENTA_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_PINK_COPPER_CANDLE_HOLDER,

                        // Weathered Copper Candle Holders
                        ModBlocks.WEATHERED_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_WHITE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_LIGHT_GRAY_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_GRAY_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_BLACK_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_BROWN_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_RED_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_ORANGE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_YELLOW_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_LIME_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_GREEN_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_CYAN_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_LIGHT_BLUE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_BLUE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_PURPLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_MAGENTA_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_PINK_COPPER_CANDLE_HOLDER,

                        // Oxidized Copper Candle Holders
                        ModBlocks.OXIDIZED_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_WHITE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_LIGHT_GRAY_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_GRAY_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_BLACK_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_BROWN_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_RED_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_ORANGE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_YELLOW_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_LIME_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_GREEN_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_CYAN_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_LIGHT_BLUE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_BLUE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_PURPLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_MAGENTA_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_PINK_COPPER_CANDLE_HOLDER,

                        // Waxed Copper Candle Holders
                        ModBlocks.WAXED_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WHITE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_LIGHT_GRAY_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_GRAY_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_BLACK_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_BROWN_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_RED_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_ORANGE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_YELLOW_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_LIME_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_GREEN_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_CYAN_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_LIGHT_BLUE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_BLUE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_PURPLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_MAGENTA_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_PINK_COPPER_CANDLE_HOLDER,

                        // Waxed Exposed Copper Candle Holders
                        ModBlocks.WAXED_EXPOSED_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_WHITE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_LIGHT_GRAY_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_GRAY_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_BLACK_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_BROWN_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_RED_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_ORANGE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_YELLOW_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_LIME_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_GREEN_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_CYAN_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_LIGHT_BLUE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_BLUE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_PURPLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_MAGENTA_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_PINK_COPPER_CANDLE_HOLDER,

                        // Waxed Weathered Copper Candle Holders
                        ModBlocks.WAXED_WEATHERED_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_WHITE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_LIGHT_GRAY_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_GRAY_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_BLACK_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_BROWN_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_RED_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_ORANGE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_YELLOW_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_LIME_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_GREEN_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_CYAN_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_LIGHT_BLUE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_BLUE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_PURPLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_MAGENTA_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_PINK_COPPER_CANDLE_HOLDER,

                        // Waxed Oxidized Copper Candle Holders
                        ModBlocks.WAXED_OXIDIZED_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_WHITE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_LIGHT_GRAY_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_GRAY_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_BLACK_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_BROWN_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_RED_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_ORANGE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_YELLOW_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_LIME_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_GREEN_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_CYAN_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_LIGHT_BLUE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_BLUE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_PURPLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_MAGENTA_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_PINK_COPPER_CANDLE_HOLDER,

                        // Double Copper Candle Holders
                        ModBlocks.DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WHITE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.GRAY_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.BLACK_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.BROWN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.RED_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.ORANGE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.YELLOW_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.LIME_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.GREEN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.CYAN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.BLUE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.PURPLE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.PINK_DOUBLE_COPPER_CANDLE_HOLDER,

                        // Exposed Double Copper Candle Holders
                        ModBlocks.EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER,

                        // Weathered Double Copper Candle Holders
                        ModBlocks.WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER,

                        // Oxidized Double Copper Candle Holders
                        ModBlocks.OXIDIZED_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_RED_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_LIME_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.OXIDIZED_PINK_DOUBLE_COPPER_CANDLE_HOLDER,

                        // Waxed Double Copper Candle Holders
                        ModBlocks.WAXED_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_RED_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_LIME_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_PINK_DOUBLE_COPPER_CANDLE_HOLDER,

                        // Waxed Exposed Double Copper Candle Holders
                        ModBlocks.WAXED_EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER,

                        // Waxed Weathered Double Copper Candle Holders
                        ModBlocks.WAXED_WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER,

                        // Waxed Oxidized Double Copper Candle Holders
                        ModBlocks.WAXED_OXIDIZED_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_RED_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_LIME_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER,
                        ModBlocks.WAXED_OXIDIZED_PINK_DOUBLE_COPPER_CANDLE_HOLDER
                );
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                        // Ironwood
                .add(ModBlocks.IRONWOOD_LOG,
                        ModBlocks.STRIPPED_IRONWOOD_LOG,
                        ModBlocks.IRONWOOD_PLANKS,
                        ModBlocks.IRONWOOD_FENCE,
                        ModBlocks.IRONWOOD_FENCE_GATE,
                        ModBlocks.IRONWOOD_STAIRS,
                        ModBlocks.IRONWOOD_SLAB,
                        ModBlocks.IRONWOOD_DOOR,
                        ModBlocks.IRONWOOD_TRAPDOOR,
                        ModBlocks.IRONWOOD_PRESSURE_PLATE,
                        ModBlocks.IRONWOOD_BUTTON,
                        ModBlocks.IRONWOOD_SIGN,
                        ModBlocks.IRONWOOD_WALL_SIGN,
                        ModBlocks.IRONWOOD_HANGING_SIGN,
                        ModBlocks.IRONWOOD_WALL_HANGING_SIGN,

                        // Olive
                        ModBlocks.OLIVE_LOG,
                        ModBlocks.STRIPPED_OLIVE_LOG,
                        ModBlocks.OLIVE_PLANKS,
                        ModBlocks.OLIVE_FENCE,
                        ModBlocks.OLIVE_FENCE_GATE,
                        ModBlocks.OLIVE_STAIRS,
                        ModBlocks.OLIVE_SLAB,
                        ModBlocks.OLIVE_DOOR,
                        ModBlocks.OLIVE_TRAPDOOR,
                        ModBlocks.OLIVE_PRESSURE_PLATE,
                        ModBlocks.OLIVE_BUTTON,
                        ModBlocks.OLIVE_HANGING_SIGN,
                        ModBlocks.OLIVE_WALL_HANGING_SIGN,
                        ModBlocks.OLIVE_SIGN,
                        ModBlocks.OLIVE_WALL_SIGN,

                        // Chairs
                        ModBlocks.OAK_CHAIR,
                        ModBlocks.DARK_OAK_CHAIR,
                        ModBlocks.BIRCH_CHAIR,
                        ModBlocks.SPRUCE_CHAIR,
                        ModBlocks.ACACIA_CHAIR,
                        ModBlocks.JUNGLE_CHAIR,
                        ModBlocks.BAMBOO_CHAIR,
                        ModBlocks.CRIMSON_CHAIR,
                        ModBlocks.WARPED_CHAIR,
                        ModBlocks.IRONWOOD_CHAIR,
                        ModBlocks.OLIVE_CHAIR,

                        // Stools
                        ModBlocks.OAK_STOOL,
                        ModBlocks.DARK_OAK_STOOL,
                        ModBlocks.BIRCH_STOOL,
                        ModBlocks.SPRUCE_STOOL,
                        ModBlocks.ACACIA_STOOL,
                        ModBlocks.JUNGLE_STOOL,
                        ModBlocks.BAMBOO_STOOL,
                        ModBlocks.CRIMSON_STOOL,
                        ModBlocks.WARPED_STOOL,
                        ModBlocks.IRONWOOD_STOOL,
                        ModBlocks.OLIVE_STOOL,

                        // Tables
                        ModBlocks.OAK_TABLE,
                        ModBlocks.DARK_OAK_TABLE,
                        ModBlocks.MANGROVE_TABLE,
                        ModBlocks.BIRCH_TABLE,
                        ModBlocks.SPRUCE_TABLE,
                        ModBlocks.ACACIA_TABLE,
                        ModBlocks.JUNGLE_TABLE,
                        ModBlocks.BAMBOO_TABLE,
                        ModBlocks.CRIMSON_TABLE,
                        ModBlocks.WARPED_TABLE,
                        ModBlocks.IRONWOOD_TABLE,
                        ModBlocks.OLIVE_TABLE,

                        // Painted Planks
                        ModBlocks.BLACK_PAINTED_PLANKS,
                        ModBlocks.BLUE_PAINTED_PLANKS,
                        ModBlocks.BROWN_PAINTED_PLANKS,
                        ModBlocks.CYAN_PAINTED_PLANKS,
                        ModBlocks.GRAY_PAINTED_PLANKS,
                        ModBlocks.GREEN_PAINTED_PLANKS,
                        ModBlocks.LIGHT_BLUE_PAINTED_PLANKS,
                        ModBlocks.LIME_PAINTED_PLANKS,
                        ModBlocks.MAGENTA_PAINTED_PLANKS,
                        ModBlocks.ORANGE_PAINTED_PLANKS,
                        ModBlocks.PINK_PAINTED_PLANKS,
                        ModBlocks.PURPLE_PAINTED_PLANKS,
                        ModBlocks.RED_PAINTED_PLANKS,
                        ModBlocks.LIGHT_GRAY_PAINTED_PLANKS,
                        ModBlocks.WHITE_PAINTED_PLANKS,
                        ModBlocks.YELLOW_PAINTED_PLANKS,

                        // Cabinets
                        ModBlocks.OAK_CABINET,
                        ModBlocks.SPRUCE_CABINET,
                        ModBlocks.BIRCH_CABINET,
                        ModBlocks.JUNGLE_CABINET,
                        ModBlocks.ACACIA_CABINET,
                        ModBlocks.DARK_OAK_CABINET,
                        ModBlocks.MANGROVE_CABINET,
                        ModBlocks.CHERRY_CABINET,
                        ModBlocks.CRIMSON_CABINET,
                        ModBlocks.WARPED_CABINET,
                        ModBlocks.IRONWOOD_CABINET,
                        ModBlocks.OLIVE_CABINET,

                        // Agriculture
                        ModBlocks.STAKE,
                        ModBlocks.CRUSHING_TUB,
                        ModBlocks.LIQUID_BARREL,
                        ModBlocks.BREWING_BARREL
                );

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.FERTILE_SOIL,
                        ModBlocks.CLAY_WALL,
                        ModBlocks.CLAY_WALL_CROSS,
                        ModBlocks.CLAY_WALL_DIAGONAL,
                        ModBlocks.UNFIRED_DRYING_BASIN,
                        ModBlocks.UNFIRED_JAR,
                        ModBlocks.UNFIRED_URN
                );

        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
                .add(ModBlocks.IRONWOOD_HANGING_SIGN,
                        ModBlocks.OLIVE_HANGING_SIGN
                );

        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
                .add(ModBlocks.IRONWOOD_WALL_HANGING_SIGN,
                        ModBlocks.OLIVE_WALL_HANGING_SIGN
                );

        getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
                .add(ModBlocks.IRONWOOD_WALL_SIGN,
                        ModBlocks.OLIVE_WALL_SIGN
                );

        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
                .add(ModBlocks.IRONWOOD_SIGN,
                        ModBlocks.OLIVE_SIGN
                );

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.IRONWOOD_LOG,
                        ModBlocks.IRONWOOD_PLANKS,
                        ModBlocks.STRIPPED_IRONWOOD_LOG,
                        ModBlocks.OLIVE_LOG,
                        ModBlocks.OLIVE_WOOD,
                        ModBlocks.STRIPPED_OLIVE_LOG
                );

        getOrCreateTagBuilder(BlockTags.SAPLINGS)
                .add(ModBlocks.IRONWOOD_SAPLING,
                        ModBlocks.OLIVE_SAPLING);

        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.IRONWOOD_FENCE,
                        ModBlocks.OLIVE_FENCE);

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.IRONWOOD_FENCE_GATE,
                        ModBlocks.OLIVE_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.IRONWOOD_BUTTON,
                        ModBlocks.OLIVE_BUTTON);

        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.IRONWOOD_STAIRS,
                        ModBlocks.OLIVE_STAIRS);

        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.IRONWOOD_TRAPDOOR,
                        ModBlocks.OLIVE_TRAPDOOR);

        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.IRONWOOD_SLAB,
                        ModBlocks.OLIVE_SLAB);

        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.IRONWOOD_PRESSURE_PLATE,
                        ModBlocks.OLIVE_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT)
                .add(ModBlocks.ROPE);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.COBBLED_SLATE_WALL,
                        ModBlocks.POLISHED_SLATE_WALL,
                        ModBlocks.SLATE_BRICK_WALL,
                        ModBlocks.POLISHED_STONE_WALL,
                        ModBlocks.ANDESITE_BRICK_WALL,
                        ModBlocks.DIORITE_BRICK_WALL,
                        ModBlocks.GRANITE_BRICK_WALL,
                        ModBlocks.SANDSTONE_BRICK_WALL,
                        ModBlocks.RED_SANDSTONE_BRICK_WALL
                );

        getOrCreateTagBuilder(BlockTags.CLIMBABLE)
                .add(ModBlocks.ROPE,
                        ModBlocks.GOLDEN_CHAIN,
                        ModBlocks.COPPER_CHAIN,
                        ModBlocks.EXPOSED_COPPER_CHAIN,
                        ModBlocks.WEATHERED_COPPER_CHAIN,
                        ModBlocks.OXIDIZED_COPPER_CHAIN,
                        ModBlocks.WAXED_COPPER_CHAIN,
                        ModBlocks.WAXED_EXPOSED_COPPER_CHAIN,
                        ModBlocks.WAXED_WEATHERED_COPPER_CHAIN,
                        ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN
                );

        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_IRONWOOD_SAPLING,
                        ModBlocks.POTTED_OLIVE_SAPLING,
                        ModBlocks.POTTED_ALOE_VERA,
                        ModBlocks.POTTED_BLOOD_ORCHID,
                        ModBlocks.POTTED_CHAMOMILE,
                        ModBlocks.POTTED_CLOUDSBLUFF,
                        ModBlocks.POTTED_COHOSH,
                        ModBlocks.POTTED_CORE_ROOT,
                        ModBlocks.POTTED_DEATHSTALK_MUSHROOM,
                        ModBlocks.POTTED_GINSENG,
                        ModBlocks.POTTED_HORSETAIL,
                        ModBlocks.POTTED_MARSHMALLOW,
                        ModBlocks.POTTED_MOONCAP_MUSHROOM,
                        ModBlocks.POTTED_VANTA_LILY,
                        ModBlocks.POTTED_WIND_THISTLE
                );

        getOrCreateTagBuilder(ModTags.Blocks.PRESERVES_FARMLAND)
                .add(ModBlocks.STAKE,
                        ModBlocks.CHILI_CROP,
                        ModBlocks.TOMATO_CROP);

        getOrCreateTagBuilder(BlockTags.BAMBOO_PLANTABLE_ON)
                .add(ModBlocks.FERTILE_SOIL);

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.IRONWOOD_LEAVES,
                        ModBlocks.OLIVE_LEAVES,
                        ModBlocks.APPLE_LEAVES,
                        ModBlocks.GRAPE_LEAVES
                );

        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.IRONWOOD_DOOR,
                        ModBlocks.OLIVE_DOOR
                );


        getOrCreateTagBuilder(ModTags.Blocks.FERTILE_SOILS)
                .add(ModBlocks.FERTILE_SOIL);

        getOrCreateTagBuilder(ModTags.Blocks.ALLOWS_HERBS_GROWTH)
                .add(Blocks.DIRT,
                        Blocks.GRASS_BLOCK,
                        Blocks.PODZOL,
                        Blocks.FARMLAND
                );

        getOrCreateTagBuilder(ModTags.Blocks.DRYING_BOOSTERS)
                .add(Blocks.MAGMA_BLOCK,
                        Blocks.FIRE,
                        Blocks.SOUL_FIRE,
                        Blocks.LAVA
                );

        getOrCreateTagBuilder(ModTags.Blocks.DIFFERENT_TUB_RENDER)
                .add(Blocks.GRAVEL,
                        Blocks.SAND,
                        Blocks.DIRT,
                        Blocks.GRASS_BLOCK,
                        Blocks.FARMLAND,
                        Blocks.BARREL,
                        ModBlocks.FERTILE_SOIL
                );

        getOrCreateTagBuilder(ModTags.Blocks.JARS)
                .add(ModBlocks.BLACK_JAR,
                        ModBlocks.BLUE_JAR,
                        ModBlocks.BROWN_JAR,
                        ModBlocks.CYAN_JAR,
                        ModBlocks.GRAY_JAR,
                        ModBlocks.GREEN_JAR,
                        ModBlocks.LIGHT_BLUE_JAR,
                        ModBlocks.LIGHT_GRAY_JAR,
                        ModBlocks.LIME_JAR,
                        ModBlocks.JAR,
                        ModBlocks.MAGENTA_JAR,
                        ModBlocks.ORANGE_JAR,
                        ModBlocks.PINK_JAR,
                        ModBlocks.PURPLE_JAR,
                        ModBlocks.RED_JAR,
                        ModBlocks.WHITE_JAR,
                        ModBlocks.YELLOW_JAR
                );

        getOrCreateTagBuilder(ModTags.Blocks.URNS)
                .add(ModBlocks.URN,
                        ModBlocks.WHITE_URN,
                        ModBlocks.ORANGE_URN,
                        ModBlocks.MAGENTA_URN,
                        ModBlocks.LIGHT_BLUE_URN,
                        ModBlocks.YELLOW_URN,
                        ModBlocks.LIME_URN,
                        ModBlocks.PINK_URN,
                        ModBlocks.GRAY_URN,
                        ModBlocks.LIGHT_GRAY_URN,
                        ModBlocks.CYAN_URN,
                        ModBlocks.PURPLE_URN,
                        ModBlocks.BLUE_URN,
                        ModBlocks.BROWN_URN,
                        ModBlocks.GREEN_URN,
                        ModBlocks.RED_URN,
                        ModBlocks.BLACK_URN
                );
    }
}

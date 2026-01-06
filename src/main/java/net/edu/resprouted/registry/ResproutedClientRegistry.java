package net.edu.resprouted.registry;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.entity.renderer.CrushingTubRenderer;
import net.edu.resprouted.block.entity.renderer.DryingBasinRenderer;
import net.edu.resprouted.block.entity.renderer.LiquidBarrelRenderer;
import net.edu.resprouted.effect.render.FullMetalHudOverlay;
import net.edu.resprouted.entity.ModEntities;
import net.edu.resprouted.entity.client.ChairEntityRenderer;
import net.edu.resprouted.entity.client.StoolEntityRenderer;
import net.edu.resprouted.entity.client.TomatoEntityRenderer;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.item.custom.BoozeBottleItem;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRenderHandler;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.FoliageColors;
import org.jetbrains.annotations.Nullable;

public class ResproutedClientRegistry {
    public static void registerModClientStuffs(){
        registerBlockEntityRenderers();
        registerEntityRenderers();
        registerBlockRenderers();
        registerFluidTexture();
        registerBlockColors();
        registerItemColors();
        registerHudRenderers();
        registerModelPredicatesForEatingAnimationsCompat();
    }

    public static void registerBlockEntityRenderers() {
        BlockEntityRendererFactories.register(ModBlockEntities.CRUSHING_TUB_BE, CrushingTubRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.LIQUID_BARREL_BE, LiquidBarrelRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.DRYING_BASIN_BE, DryingBasinRenderer::new);
    }

    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(ModEntities.CHAIR, ChairEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.STOOL, StoolEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.THROWN_TOMATO, TomatoEntityRenderer::new);
    }

    public static void registerBlockRenderers() {
        BlockRenderLayerMap registry = BlockRenderLayerMap.INSTANCE;
        registry.putBlock(ModBlocks.OLIVE_SAPLING, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.IRONWOOD_SAPLING, RenderLayer.getCutout());

        registry.putBlock(ModBlocks.GARGOYLE, RenderLayer.getCutout());

        // Herbs
        registry.putBlock(ModBlocks.ALOE_VERA, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.HORSETAIL, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.CHAMOMILE, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COHOSH, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.CLOUDSBLUFF, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BLOOD_ORCHID, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.GINSENG, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.MARSHMALLOW, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.VANTA_LILY, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WIND_THISTLE, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.MOONCAP_MUSHROOM, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.DEATHSTALK_MUSHROOM, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.CORE_ROOT, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.APPLE_LEAVES, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BREWING_BARREL, RenderLayer.getCutout());

        // Agriculture
        registry.putBlock(ModBlocks.GRAPE_STEM, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.GRAPE_LEAVES, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.TOMATO_CROP, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.CHILI_CROP, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.APPLE_TREE, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.APPLE_SAPLING, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIQUID_BARREL, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.CRUSHING_TUB, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.DRYING_BASIN, RenderLayer.getCutout());

        // Bushes
        registry.putBlock(ModBlocks.TEST_BERRY_BUSH, RenderLayer.getCutout());

        // Chains
        registry.putBlock(ModBlocks.GOLDEN_CHAIN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_CHAIN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_CHAIN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_CHAIN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_CHAIN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_CHAIN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN, RenderLayer.getCutout());

        // Chandeliers
        registry.putBlock(ModBlocks.CHANDELIER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.GOLDEN_CHANDELIER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_CHANDELIER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_CHANDELIER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_CHANDELIER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_CHANDELIER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_CHANDELIER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_CHANDELIER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_CHANDELIER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_CHANDELIER, RenderLayer.getCutout());

        // Lanterns
        registry.putBlock(ModBlocks.ORNATE_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.ORNATE_SOUL_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.ORNATE_GOLD_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.ORNATE_GOLD_SOUL_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.ORNATE_COPPER_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_ORNATE_COPPER_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_ORNATE_COPPER_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_ORNATE_COPPER_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_ORNATE_COPPER_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_ORNATE_COPPER_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_ORNATE_COPPER_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_ORNATE_COPPER_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.ORNATE_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_ORNATE_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_ORNATE_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_ORNATE_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_ORNATE_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_ORNATE_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_ORNATE_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_ORNATE_COPPER_SOUL_LANTERN, RenderLayer.getCutout());

        // Iron Candle Holders
        registry.putBlock(ModBlocks.IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WHITE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIGHT_GRAY_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.GRAY_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BLACK_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BROWN_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.RED_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.ORANGE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.YELLOW_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIME_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.CYAN_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIGHT_BLUE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BLUE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.PURPLE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.MAGENTA_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.PINK_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.GREEN_IRON_CANDLE_HOLDER, RenderLayer.getCutout());

        // Double Iron Candle Holders
        registry.putBlock(ModBlocks.DOUBLE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WHITE_DOUBLE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIGHT_GRAY_DOUBLE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.GRAY_DOUBLE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BLACK_DOUBLE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BROWN_DOUBLE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.RED_DOUBLE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.ORANGE_DOUBLE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.YELLOW_DOUBLE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIME_DOUBLE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.CYAN_DOUBLE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIGHT_BLUE_DOUBLE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BLUE_DOUBLE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.PURPLE_DOUBLE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.MAGENTA_DOUBLE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.PINK_DOUBLE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.GREEN_DOUBLE_IRON_CANDLE_HOLDER, RenderLayer.getCutout());

        // Golden Candle Holders
        registry.putBlock(ModBlocks.GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WHITE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIGHT_GRAY_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.GRAY_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BLACK_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BROWN_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.RED_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.ORANGE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.YELLOW_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIME_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.CYAN_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIGHT_BLUE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BLUE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.PURPLE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.MAGENTA_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.PINK_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.GREEN_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());

        // Double Golden Candle Holders
        registry.putBlock(ModBlocks.DOUBLE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WHITE_DOUBLE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIGHT_GRAY_DOUBLE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.GRAY_DOUBLE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BLACK_DOUBLE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BROWN_DOUBLE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.RED_DOUBLE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.ORANGE_DOUBLE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.YELLOW_DOUBLE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIME_DOUBLE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.CYAN_DOUBLE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIGHT_BLUE_DOUBLE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BLUE_DOUBLE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.PURPLE_DOUBLE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.MAGENTA_DOUBLE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.PINK_DOUBLE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.GREEN_DOUBLE_GOLDEN_CANDLE_HOLDER, RenderLayer.getCutout());

        // Copper Candle Holders
        registry.putBlock(ModBlocks.COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WHITE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIGHT_GRAY_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.GRAY_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BLACK_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BROWN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.RED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.ORANGE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.YELLOW_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIME_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.GREEN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.CYAN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIGHT_BLUE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BLUE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.PURPLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.MAGENTA_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.PINK_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());

        // Exposed Copper Candle Holder
        registry.putBlock(ModBlocks.EXPOSED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_WHITE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_GRAY_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_BLACK_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_BROWN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_RED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_ORANGE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_YELLOW_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_LIME_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_GREEN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_CYAN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_BLUE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_PURPLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_MAGENTA_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_PINK_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());

        // Weathered Copper Candle Holder
        registry.putBlock(ModBlocks.WEATHERED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_WHITE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_GRAY_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_BLACK_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_BROWN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_RED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_ORANGE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_YELLOW_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_LIME_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_GREEN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_CYAN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_BLUE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_PURPLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_MAGENTA_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_PINK_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());

        // Oxidized Copper Candle Holder
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_WHITE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_GRAY_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_BLACK_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_BROWN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_RED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_ORANGE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_YELLOW_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_LIME_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_GREEN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_CYAN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_BLUE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_PURPLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_MAGENTA_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_PINK_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());

        // Waxed Copper Candle Holder
        registry.putBlock(ModBlocks.WAXED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WHITE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_GRAY_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_BLACK_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_BROWN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_RED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_ORANGE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_YELLOW_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_LIME_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_GREEN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_CYAN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_BLUE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_PURPLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_MAGENTA_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_PINK_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());

        // Waxed Exposed Copper Candle Holder
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_WHITE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_GRAY_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_BLACK_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_BROWN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_RED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_ORANGE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_YELLOW_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_LIME_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_GREEN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_CYAN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_BLUE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_PURPLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_MAGENTA_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_PINK_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());

        // Waxed Weathered Copper Candle Holder
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_WHITE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_GRAY_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_BLACK_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_BROWN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_RED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_ORANGE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_YELLOW_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_LIME_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_GREEN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_CYAN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_BLUE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_PURPLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_MAGENTA_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_PINK_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());

        // Waxed Oxidized Copper Candle Holder
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_WHITE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_LIGHT_GRAY_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_GRAY_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_BLACK_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_BROWN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_RED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_ORANGE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_YELLOW_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_LIME_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_GREEN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_CYAN_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_LIGHT_BLUE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_BLUE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_PURPLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_MAGENTA_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_PINK_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());

        // Double Copper Candle Holders
        registry.putBlock(ModBlocks.DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WHITE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.GRAY_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BLACK_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BROWN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.RED_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIME_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.GREEN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.CYAN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BLUE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.PINK_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());

        // Exposed Double Copper Candle Holders
        registry.putBlock(ModBlocks.EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());

        // Weathered Double Copper Candle Holders
        registry.putBlock(ModBlocks.WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());

        // Oxidized Double Copper Candle Holders
        registry.putBlock(ModBlocks.OXIDIZED_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_RED_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());

        // Waxed Double Copper Candle Holders
        registry.putBlock(ModBlocks.WAXED_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_RED_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());

        registry.putBlock(ModBlocks.WAXED_EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());

        registry.putBlock(ModBlocks.WAXED_WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());

        registry.putBlock(ModBlocks.WAXED_OXIDIZED_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_RED_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_LIME_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_PINK_DOUBLE_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());

        // Doors&Trapdoors
        registry.putBlock(ModBlocks.IRONWOOD_DOOR, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.IRONWOOD_TRAPDOOR, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OLIVE_DOOR, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OLIVE_TRAPDOOR, RenderLayer.getCutout());

        // Bar
        registry.putBlock(ModBlocks.WROUGHT_IRON_BARS, RenderLayer.getCutout());

        // Fluids
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.GOLDEN_APPLE_JUICE, ModFluids.GOLDEN_APPLE_JUICE_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.SWEET_BERRY_JUICE, ModFluids.SWEET_BERRY_JUICE_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.SUGAR_CANE_JUICE, ModFluids.SUGAR_CANE_JUICE_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.GLOW_BERRY_JUICE, ModFluids.GLOW_BERRY_JUICE_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.IRON_BERRY_JUICE, ModFluids.IRON_BERRY_JUICE_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.APPLE_JUICE, ModFluids.APPLE_JUICE_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.GRAPE_JUICE, ModFluids.GRAPE_JUICE_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.VANTA_OIL, ModFluids.VANTA_OIL_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.OLIVE_OIL, ModFluids.OLIVE_OIL_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.ALE_WORT, ModFluids.ALE_WORT_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.HONEY, ModFluids.HONEY_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.POTION, ModFluids.POTION_FLOWING);

        // Fluid Booze
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.SWEET_BERRY_WINE, ModFluids.SWEET_BERRY_WINE_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.GLOW_BERRY_WINE, ModFluids.GLOW_BERRY_WINE_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.IRON_WINE, ModFluids.IRON_WINE_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.AMBROSIA, ModFluids.AMBROSIA_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.CIDER, ModFluids.CIDER_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.WINE, ModFluids.WINE_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.MEAD, ModFluids.MEAD_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.ALE, ModFluids.ALE_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.RUM, ModFluids.RUM_FLOWING);

        // Sign
        TexturedRenderLayers.SIGN_TYPE_TEXTURES.put(ResproutedWoodTypes.IRONWOOD, TexturedRenderLayers.getSignTextureId(ResproutedWoodTypes.IRONWOOD));
        TexturedRenderLayers.SIGN_TYPE_TEXTURES.put(ResproutedWoodTypes.OLIVE, TexturedRenderLayers.getSignTextureId(ResproutedWoodTypes.OLIVE));

        // Potted
        registry.putBlock(ModBlocks.POTTED_IRONWOOD_SAPLING, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.POTTED_OLIVE_SAPLING, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.POTTED_ALOE_VERA, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.POTTED_BLOOD_ORCHID, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.POTTED_CHAMOMILE, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.POTTED_CLOUDSBLUFF, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.POTTED_COHOSH, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.POTTED_CORE_ROOT, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.POTTED_DEATHSTALK_MUSHROOM, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.POTTED_GINSENG, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.POTTED_HORSETAIL, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.POTTED_MARSHMALLOW, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.POTTED_MOONCAP_MUSHROOM, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.POTTED_VANTA_LILY, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.POTTED_WIND_THISTLE, RenderLayer.getCutout());
    }

    public static void registerFluidTexture() {
        FluidRenderHandlerRegistry registry = FluidRenderHandlerRegistry.INSTANCE;
        // Honey
        registry.register(ModFluids.HONEY, ModFluids.HONEY_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/honey_still"),
                Identifier.of("resprouted:block/fluid/honey_flow"),
                Identifier.of("resprouted:block/fluid/honey_overlay")
        ));

        // Apple Juice
        registry.register(ModFluids.APPLE_JUICE, ModFluids.APPLE_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/apple_juice_still"),
                Identifier.of("resprouted:block/fluid/apple_juice_flow"),
                Identifier.of("resprouted:block/fluid/apple_juice_overlay")
        ));

        // Golden Apple Juice
        registry.register(ModFluids.GOLDEN_APPLE_JUICE, ModFluids.GOLDEN_APPLE_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/golden_apple_juice_still"),
                Identifier.of("resprouted:block/fluid/golden_apple_juice_flow"),
                Identifier.of("resprouted:block/fluid/golden_apple_juice_overlay")
        ));

        // Grape Juice
        registry.register(ModFluids.GRAPE_JUICE, ModFluids.GRAPE_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/grape_juice_still"),
                Identifier.of("resprouted:block/fluid/grape_juice_flow"),
                Identifier.of("resprouted:block/fluid/grape_juice_overlay")
        ));

        // Sweet Berry Juice
        registry.register(ModFluids.SWEET_BERRY_JUICE, ModFluids.SWEET_BERRY_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/sweet_berry_juice_still"),
                Identifier.of("resprouted:block/fluid/sweet_berry_juice_flow"),
                Identifier.of("resprouted:block/fluid/sweet_berry_juice_overlay")
        ));

        // Olive Oil
        registry.register(ModFluids.OLIVE_OIL, ModFluids.OLIVE_OIL_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/olive_oil_still"),
                Identifier.of("resprouted:block/fluid/olive_oil_flow"),
                Identifier.of("resprouted:block/fluid/olive_oil_overlay")
        ));

        // Vanta Oil
        registry.register(ModFluids.VANTA_OIL, ModFluids.VANTA_OIL_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/vanta_oil_still"),
                Identifier.of("resprouted:block/fluid/vanta_oil_flow"),
                Identifier.of("resprouted:block/fluid/vanta_oil_overlay")
        ));

        // Glow Berry Juice
        registry.register(ModFluids.GLOW_BERRY_JUICE, ModFluids.GLOW_BERRY_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/glow_berry_juice_still"),
                Identifier.of("resprouted:block/fluid/glow_berry_juice_flow"),
                Identifier.of("resprouted:block/fluid/glow_berry_juice_overlay")
        ));

        // Iron Berry Juice
        registry.register(ModFluids.IRON_BERRY_JUICE, ModFluids.IRON_BERRY_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/iron_berry_juice_still"),
                Identifier.of("resprouted:block/fluid/iron_berry_juice_flow"),
                Identifier.of("resprouted:block/fluid/iron_berry_juice_overlay")
        ));

        // Ale Wort
        registry.register(ModFluids.ALE_WORT, ModFluids.ALE_WORT_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/ale_wort_still"),
                Identifier.of("resprouted:block/fluid/ale_wort_flow"),
                Identifier.of("resprouted:block/fluid/ale_wort_overlay")
        ));

        // Sugar Cane
        registry.register(ModFluids.SUGAR_CANE_JUICE, ModFluids.SUGAR_CANE_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/sugar_cane_juice_still"),
                Identifier.of("resprouted:block/fluid/sugar_cane_juice_flow"),
                Identifier.of("resprouted:block/fluid/sugar_cane_juice_overlay")
        ));

        // Ale
        registry.register(ModFluids.ALE, ModFluids.ALE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/booze/ale_still"),
                Identifier.of("resprouted:block/fluid/booze/ale_flow"),
                Identifier.of("resprouted:block/fluid/booze/ale_overlay")
        ));

        // Iron Wine
        registry.register(ModFluids.IRON_WINE, ModFluids.IRON_WINE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/booze/iron_wine_still"),
                Identifier.of("resprouted:block/fluid/booze/iron_wine_flow"),
                Identifier.of("resprouted:block/fluid/booze/iron_wine_overlay")
        ));

        // Cider
        registry.register(ModFluids.CIDER, ModFluids.CIDER_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/booze/cider_still"),
                Identifier.of("resprouted:block/fluid/booze/cider_flow"),
                Identifier.of("resprouted:block/fluid/booze/cider_overlay")
        ));

        // Mead
        registry.register(ModFluids.MEAD, ModFluids.MEAD_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/booze/mead_still"),
                Identifier.of("resprouted:block/fluid/booze/mead_flow"),
                Identifier.of("resprouted:block/fluid/booze/mead_overlay")
        ));

        // Wine
        registry.register(ModFluids.WINE, ModFluids.WINE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/booze/wine_still"),
                Identifier.of("resprouted:block/fluid/booze/wine_flow"),
                Identifier.of("resprouted:block/fluid/booze/wine_overlay")
        ));

        // Sweet Berry Wine
        registry.register(ModFluids.SWEET_BERRY_WINE, ModFluids.SWEET_BERRY_WINE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/booze/sweet_berry_wine_still"),
                Identifier.of("resprouted:block/fluid/booze/sweet_berry_wine_flow"),
                Identifier.of("resprouted:block/fluid/booze/sweet_berry_wine_overlay")
        ));

        // Glow Berry Wine
        registry.register(ModFluids.GLOW_BERRY_WINE, ModFluids.GLOW_BERRY_WINE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/booze/glow_berry_wine_still"),
                Identifier.of("resprouted:block/fluid/booze/glow_berry_wine_flow"),
                Identifier.of("resprouted:block/fluid/booze/glow_berry_wine_overlay")
        ));

        // Rum
        registry.register(ModFluids.RUM, ModFluids.RUM_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/booze/rum_still"),
                Identifier.of("resprouted:block/fluid/booze/rum_flow"),
                Identifier.of("resprouted:block/fluid/booze/rum_overlay")
        ));


        // Ambrosia
        registry.register(ModFluids.AMBROSIA, ModFluids.AMBROSIA_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/booze/ambrosia_still"),
                Identifier.of("resprouted:block/fluid/booze/ambrosia_flow"),
                Identifier.of("resprouted:block/fluid/booze/ambrosia_overlay")
        ));

        // Potion
        registry.register(ModFluids.POTION, ModFluids.POTION_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/potion_still"),
                Identifier.of("minecraft:block/water_flow")
        ));
        FluidVariantRendering.register(ModFluids.POTION, new FluidVariantRenderHandler() {
            private static final int DEFAULT_COLOR = 0x385DC6;

            @Override
            public Sprite[] getSprites(FluidVariant fluidVariant) {
                SpriteAtlasTexture atlas = MinecraftClient.getInstance().getBakedModelManager()
                        .getAtlas(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE);

                Sprite potionStillSprite = atlas.getSprite(Identifier.of("resprouted:block/fluid/potion_still"));
                Sprite waterFlowSprite = atlas.getSprite(Identifier.of("minecraft:block/water_flow"));

                return new Sprite[]{potionStillSprite, waterFlowSprite};
            }

            @Override
            public int getColor(FluidVariant fluidVariant, @Nullable BlockRenderView view, @Nullable BlockPos pos) {
                if (fluidVariant == null || fluidVariant.isBlank()) {
                    return DEFAULT_COLOR;
                }

                ComponentMap components = fluidVariant.getComponentMap();
                PotionContentsComponent potionContents = components.get(DataComponentTypes.POTION_CONTENTS);

                if (potionContents == null) {
                    return DEFAULT_COLOR;
                }

                return potionContents.getColor();
            }
        });
    }

    public static void registerBlockColors() {
        ColorProviderRegistry<Block, BlockColorProvider> blockColors = ColorProviderRegistry.BLOCK;

        blockColors.register(
                (state, world, pos, tintIndex) -> world != null && pos != null
                        ? BiomeColors.getFoliageColor(world, pos)
                        : FoliageColors.getDefaultColor(),
                ModBlocks.APPLE_LEAVES,
                ModBlocks.GRAPE_LEAVES
        );
    }

    public static void registerItemColors() {
        ColorProviderRegistry<ItemConvertible, ItemColorProvider> itemColors = ColorProviderRegistry.ITEM;

        itemColors.register(
                (stack, tintIndex) -> FoliageColors.getDefaultColor(),
                ModBlocks.APPLE_LEAVES,
                ModBlocks.GRAPE_LEAVES
        );
        itemColors.register(
                (stack, tintIndex) -> tintIndex > 0
                        ? -1
                        : ColorHelper.Argb.fullAlpha(stack.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT).getColor()),
                ModItems.ELIXIR_BOTTLE
        );
    }

    public static void registerHudRenderers() {
        HudRenderCallback.EVENT.register(new FullMetalHudOverlay());
    }

    public static void registerModelPredicatesForEatingAnimationsCompat() {
        // Register for Elixir Bottle
        ModelPredicateProviderRegistry.register(
                ModItems.ELIXIR_BOTTLE,
                Identifier.of("drinking"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F
        );

        ModelPredicateProviderRegistry.register(
                ModItems.ELIXIR_BOTTLE,
                Identifier.of("drink"),
                (stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0F;
                    }
                    return entity.getActiveItem() != stack
                            ? 0.0F
                            : (float) (stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / 30.0F;
                }
        );

        // Register for all BoozeBottleItem instances
        for (Item item : Registries.ITEM) {
            if (item instanceof BoozeBottleItem) {
                ModelPredicateProviderRegistry.register(
                        item,
                        Identifier.of("drinking"),
                        (stack, world, entity, seed) -> entity != null
                                && entity.isUsingItem()
                                && entity.getActiveItem() == stack ? 1.0F : 0.0F
                );

                ModelPredicateProviderRegistry.register(
                        item,
                        Identifier.of("drink"),
                        (stack, world, entity, seed) -> {
                            if (entity == null) {
                                return 0.0F;
                            }
                            return entity.getActiveItem() != stack
                                    ? 0.0F
                                    : (float) (stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / 30.0F;
                        }
                );
            }
        }
    }
}

package net.edu.resprouted.registry;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.entity.renderer.CrushingTubBERenderer;
import net.edu.resprouted.block.entity.renderer.EvaporatingBasinRenderer;
import net.edu.resprouted.block.entity.renderer.LiquidBarrelRenderer;
import net.edu.resprouted.effect.render.FullMetalHudOverlay;
import net.edu.resprouted.effect.render.FullMetalLayer;
import net.edu.resprouted.effect.render.IronSkinLayer;
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
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.block.Block;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.FoliageColors;

import java.util.Optional;


public class ResproutedClientRegistries {
    public static void RegisterModClientStuffs(){
        registerBlockEntityRenderers();
        registerEntityRenderers();
        registerBlockRenderers();
        registerFluidColors();
        registerBlockColors();
        registerItemColors();
        registerHudRenderers();
        registerLivingEntityRenderers();
        registerEatingAnimationsCompat();
    }

    public static void registerBlockEntityRenderers() {
        BlockEntityRendererFactories.register(ModBlockEntities.CRUSHING_TUB_BE, CrushingTubBERenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.LIQUID_BARREL_BE, LiquidBarrelRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.EVAPORATING_BASIN_BE, EvaporatingBasinRenderer::new);
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
        //Herbs
        registry.putBlock(ModBlocks.ALOE_VERA_BLOCK, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.HORSETAIL_BLOCK, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.CHAMOMILE_BLOCK, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COHOSH_BLOCK, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.CLOUDSBLUFF_BLOCK, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BLOOD_ORCHID_BLOCK, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.GINSENG_BLOCK, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.MARSHMALLOW_BLOCK, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.VANTA_LILY_BLOCK, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WIND_THISTLE_BLOCK, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.MOONCAP_MUSHROOM, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.DEATHSTALK_MUSHROOM, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.CORE_ROOT, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.APPLE_LEAVES, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.BREWING_BARREL, RenderLayer.getCutout());
        //Agriculture
        registry.putBlock(ModBlocks.GRAPE_STEM, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.GRAPE_LEAVES, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.TOMATO_CROP, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.CHILI_CROP, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.APPLE_TREE, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.APPLE_SAPLING, RenderLayer.getCutout());
        //Bushes
        registry.putBlock(ModBlocks.BLUE_BERRY_BUSH, RenderLayer.getCutout());
        //Chains
        registry.putBlock(ModBlocks.GOLDEN_CHAIN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_CHAIN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_CHAIN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_CHAIN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_CHAIN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_CHAIN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN, RenderLayer.getCutout());
        //Chandeliers
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
        //Lanterns
        registry.putBlock(ModBlocks.GOLDEN_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.GOLDEN_SOUL_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        //Candle Holder
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
        //Golden Candle Holder
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
        //Copper Candle Holder
        registry.putBlock(ModBlocks.COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_WHITE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_LIGHT_GRAY_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_GRAY_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_BLACK_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_BROWN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_RED_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_ORANGE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_YELLOW_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_LIME_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_GREEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_CYAN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_LIGHT_BLUE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_BLUE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_PURPLE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_MAGENTA_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.COPPER_PINK_CANDLE_HOLDER, RenderLayer.getCutout());
        //Exposed Copper Candle Holder
        registry.putBlock(ModBlocks.EXPOSED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_WHITE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_LIGHT_GRAY_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_GRAY_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_BLACK_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_BROWN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_RED_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_ORANGE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_YELLOW_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_LIME_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_GREEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_CYAN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_LIGHT_BLUE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_BLUE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_PURPLE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_MAGENTA_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.EXPOSED_COPPER_PINK_CANDLE_HOLDER, RenderLayer.getCutout());
        //Weathered Copper Candle Holder
        registry.putBlock(ModBlocks.WEATHERED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_WHITE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_LIGHT_GRAY_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_GRAY_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_BLACK_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_BROWN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_RED_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_ORANGE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_YELLOW_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_LIME_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_GREEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_CYAN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_LIGHT_BLUE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_BLUE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_PURPLE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_MAGENTA_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WEATHERED_COPPER_PINK_CANDLE_HOLDER, RenderLayer.getCutout());
        //Oxidized Copper Candle Holder
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_WHITE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_LIGHT_GRAY_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_GRAY_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_BLACK_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_BROWN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_RED_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_ORANGE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_YELLOW_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_LIME_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_GREEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_CYAN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_LIGHT_BLUE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_BLUE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_PURPLE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_MAGENTA_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OXIDIZED_COPPER_PINK_CANDLE_HOLDER, RenderLayer.getCutout());
        //Waxed Copper Candle Holder
        registry.putBlock(ModBlocks.WAXED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_WHITE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_LIGHT_GRAY_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_GRAY_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_BLACK_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_BROWN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_RED_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_ORANGE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_YELLOW_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_LIME_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_GREEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_CYAN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_LIGHT_BLUE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_BLUE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_PURPLE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_MAGENTA_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_COPPER_PINK_CANDLE_HOLDER, RenderLayer.getCutout());
        //Waxed Exposed Copper Candle Holder
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_WHITE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_LIGHT_GRAY_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_GRAY_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_BLACK_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_BROWN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_RED_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_ORANGE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_YELLOW_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_LIME_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_GREEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_CYAN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_LIGHT_BLUE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_BLUE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_PURPLE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_MAGENTA_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_PINK_CANDLE_HOLDER, RenderLayer.getCutout());
        //Waxed Weathered Copper Candle Holder
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_WHITE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_LIGHT_GRAY_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_GRAY_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_BLACK_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_BROWN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_RED_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_ORANGE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_YELLOW_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_LIME_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_GREEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_CYAN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_LIGHT_BLUE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_BLUE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_PURPLE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_MAGENTA_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_PINK_CANDLE_HOLDER, RenderLayer.getCutout());
        //Waxed Oxidized Copper Candle Holder
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_WHITE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_LIGHT_GRAY_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_GRAY_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_BLACK_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_BROWN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_RED_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_ORANGE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_YELLOW_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_LIME_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_GREEN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_CYAN_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_LIGHT_BLUE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_BLUE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_PURPLE_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_MAGENTA_CANDLE_HOLDER, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_PINK_CANDLE_HOLDER, RenderLayer.getCutout());
        //Doors&Trapdoors
        registry.putBlock(ModBlocks.IRONWOOD_DOOR, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.IRONWOOD_TRAPDOOR, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OLIVE_DOOR, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.OLIVE_TRAPDOOR, RenderLayer.getCutout());
        //Bar
        registry.putBlock(ModBlocks.WROUGHT_IRON_BARS, RenderLayer.getCutout());
        //Fluids
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.HONEY_STILL, ModFluids.HONEY_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.APPLE_JUICE_STILL, ModFluids.APPLE_JUICE_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.GOLDEN_APPLE_JUICE_STILL, ModFluids.GOLDEN_APPLE_JUICE_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.GRAPE_JUICE_STILL, ModFluids.GRAPE_JUICE_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.SWEET_BERRY_JUICE_STILL, ModFluids.SWEET_BERRY_JUICE_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.OLIVE_OIL_STILL, ModFluids.OLIVE_OIL_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.VANTA_OIL_STILL, ModFluids.VANTA_OIL_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.GLOW_BERRY_JUICE_STILL, ModFluids.GLOW_BERRY_JUICE_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.IRON_BERRY_JUICE_STILL, ModFluids.IRON_BERRY_JUICE_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.ALE_WORT_STILL, ModFluids.ALE_WORT_FLOWING);
        registry.putFluids(RenderLayer.getTranslucent(), ModFluids.SUGAR_CANE_JUICE_STILL, ModFluids.SUGAR_CANE_JUICE_FLOWING);

        TexturedRenderLayers.SIGN_TYPE_TEXTURES.put(ResproutedWoodTypes.IRONWOOD, TexturedRenderLayers.getSignTextureId(ResproutedWoodTypes.IRONWOOD));
        TexturedRenderLayers.SIGN_TYPE_TEXTURES.put(ResproutedWoodTypes.OLIVE, TexturedRenderLayers.getSignTextureId(ResproutedWoodTypes.OLIVE));

    }

    public static void registerFluidColors() {
        FluidRenderHandlerRegistry registry = FluidRenderHandlerRegistry.INSTANCE;
        //Honey
        registry.register(ModFluids.HONEY_STILL, ModFluids.HONEY_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/honey_still"),
                Identifier.of("resprouted:block/fluid/honey_flow"),
                Identifier.of("resprouted:block/fluid/honey_still")
        ));
        //Apple Juice
        registry.register(ModFluids.APPLE_JUICE_STILL, ModFluids.APPLE_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/apple_juice_still"),
                Identifier.of("resprouted:block/fluid/apple_juice_flow"),
                Identifier.of("resprouted:block/fluid/apple_juice_overlay")
        ));
        //Golden Apple Juice
        registry.register(ModFluids.GOLDEN_APPLE_JUICE_STILL, ModFluids.GOLDEN_APPLE_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/golden_apple_juice_still"),
                Identifier.of("resprouted:block/fluid/golden_apple_juice_flow"),
                Identifier.of("resprouted:block/fluid/golden_apple_juice_overlay")
        ));
        //Grape Juice
        registry.register(ModFluids.GRAPE_JUICE_STILL, ModFluids.GRAPE_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/grape_juice_still"),
                Identifier.of("resprouted:block/fluid/grape_juice_flow"),
                Identifier.of("resprouted:block/fluid/grape_juice_overlay")
        ));
        //Sweet Berry Juice
        registry.register(ModFluids.SWEET_BERRY_JUICE_STILL, ModFluids.SWEET_BERRY_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/sweet_berry_juice_still"),
                Identifier.of("resprouted:block/fluid/sweet_berry_juice_flow"),
                Identifier.of("resprouted:block/fluid/sweet_berry_juice_overlay")
        ));
        //Olive Oil
        registry.register(ModFluids.OLIVE_OIL_STILL, ModFluids.OLIVE_OIL_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/olive_oil_still"),
                Identifier.of("resprouted:block/fluid/olive_oil_flow"),
                Identifier.of("resprouted:block/fluid/olive_oil_overlay")
        ));
        //Vanta Oil
        registry.register(ModFluids.VANTA_OIL_STILL, ModFluids.VANTA_OIL_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/vanta_oil_still"),
                Identifier.of("resprouted:block/fluid/vanta_oil_flow"),
                Identifier.of("resprouted:block/fluid/vanta_oil_overlay")
        ));
        //Glow Berry Juice
        registry.register(ModFluids.GLOW_BERRY_JUICE_STILL, ModFluids.GLOW_BERRY_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/glow_berry_juice_still"),
                Identifier.of("resprouted:block/fluid/glow_berry_juice_flow"),
                Identifier.of("resprouted:block/fluid/glow_berry_juice_overlay")
        ));
        //Iron Berry Juice
        registry.register(ModFluids.IRON_BERRY_JUICE_STILL, ModFluids.IRON_BERRY_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/iron_berry_juice_still"),
                Identifier.of("resprouted:block/fluid/iron_berry_juice_flow"),
                Identifier.of("resprouted:block/fluid/iron_berry_juice_overlay")
        ));
        //Ale Wort
        registry.register(ModFluids.ALE_WORT_STILL, ModFluids.ALE_WORT_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/ale_wort_still"),
                Identifier.of("resprouted:block/fluid/ale_wort_flow"),
                Identifier.of("resprouted:block/fluid/ale_wort_overlay")
        ));
        //Sugar Cane
        registry.register(ModFluids.SUGAR_CANE_JUICE_STILL, ModFluids.SUGAR_CANE_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/sugar_cane_juice_still"),
                Identifier.of("resprouted:block/fluid/sugar_cane_juice_flow"),
                Identifier.of("resprouted:block/fluid/sugar_cane_juice_overlay")
        ));
        //Ale
        registry.register(ModFluids.ALE_STILL, ModFluids.ALE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/booze/ale_still"),
                Identifier.of("resprouted:block/fluid/booze/ale_flow"),
                Identifier.of("resprouted:block/fluid/booze/ale_overlay")
        ));
        //Iron Wine
        registry.register(ModFluids.IRON_WINE_STILL, ModFluids.IRON_WINE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/booze/iron_wine_still"),
                Identifier.of("resprouted:block/fluid/booze/iron_wine_flow"),
                Identifier.of("resprouted:block/fluid/booze/iron_wine_overlay")
        ));
        //Cider
        registry.register(ModFluids.CIDER_STILL, ModFluids.CIDER_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/booze/cider_still"),
                Identifier.of("resprouted:block/fluid/booze/cider_flow"),
                Identifier.of("resprouted:block/fluid/booze/cider_overlay")
        ));
        //Mead
        registry.register(ModFluids.MEAD_STILL, ModFluids.MEAD_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/booze/mead_still"),
                Identifier.of("resprouted:block/fluid/booze/mead_flow"),
                Identifier.of("resprouted:block/fluid/booze/mead_overlay")
        ));
        //Wine
        registry.register(ModFluids.WINE_STILL, ModFluids.WINE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/booze/wine_still"),
                Identifier.of("resprouted:block/fluid/booze/wine_flow"),
                Identifier.of("resprouted:block/fluid/booze/wine_overlay")
        ));
        //Sweet Berry Wine
        registry.register(ModFluids.SWEET_BERRY_WINE_STILL, ModFluids.SWEET_BERRY_WINE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/booze/sweet_berry_wine_still"),
                Identifier.of("resprouted:block/fluid/booze/sweet_berry_wine_flow"),
                Identifier.of("resprouted:block/fluid/booze/sweet_berry_wine_overlay")
        ));
        //Glow Berry Wine
        registry.register(ModFluids.GLOW_BERRY_WINE_STILL, ModFluids.GLOW_BERRY_WINE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/booze/glow_berry_wine_still"),
                Identifier.of("resprouted:block/fluid/booze/glow_berry_wine_flow"),
                Identifier.of("resprouted:block/fluid/booze/glow_berry_wine_overlay")
        ));
        //Rum
        registry.register(ModFluids.RUM_STILL, ModFluids.RUM_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/booze/rum_still"),
                Identifier.of("resprouted:block/fluid/booze/rum_flow"),
                Identifier.of("resprouted:block/fluid/booze/rum_overlay")
        ));
        //Ambrosia
        registry.register(ModFluids.AMBROSIA_STILL, ModFluids.AMBROSIA_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/booze/ambrosia_still"),
                Identifier.of("resprouted:block/fluid/booze/ambrosia_flow"),
                Identifier.of("resprouted:block/fluid/booze/ambrosia_overlay")
        ));
    }

    public static void registerBlockColors() {
        ColorProviderRegistry<Block, BlockColorProvider> registry = ColorProviderRegistry.BLOCK;
        BlockColorProvider foliageColorProvider = (state, world, pos, tintIndex) ->
                (world == null || pos == null) ? FoliageColors.getDefaultColor() : BiomeColors.getFoliageColor(world, pos);

        registry.register(foliageColorProvider, ModBlocks.APPLE_LEAVES, ModBlocks.GRAPE_LEAVES);
    }

    public static void registerItemColors() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getDefaultColor(), ModBlocks.APPLE_LEAVES
        );

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex != 0 ? -1 :
                        Optional.ofNullable(stack.get(DataComponentTypes.POTION_CONTENTS))
                                .filter(potion -> potion.getColor() != -1)
                                .map(PotionContentsComponent::getColor)
                                .orElse(0x385DC6), ModItems.ELIXIR_BOTTLE
        );
    }

    public static void registerEatingAnimationsCompat() {
        registerDrinkingAnimationCompat(ModItems.ELIXIR_BOTTLE);

        for (Item item : Registries.ITEM) {
            if (item instanceof BoozeBottleItem) {
                registerDrinkingAnimationCompat(item);
            }
        }
    }

    private static void registerDrinkingAnimationCompat(Item item) {
        ModelPredicateProviderRegistry.register(item,
                Identifier.of("drinking"),
                (itemStack, clientWorld, livingEntity, seed) -> {
                    if (livingEntity == null) {
                        return 0.0F;
                    }
                    return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
                });

        ModelPredicateProviderRegistry.register(item,
                Identifier.of("drink"),
                (itemStack, clientWorld, livingEntity, seed) -> {
                    if (livingEntity == null) {
                        return 0.0F;
                    }
                    return livingEntity.getActiveItem() != itemStack ? 0.0F :
                            (itemStack.getMaxUseTime(livingEntity) - livingEntity.getItemUseTimeLeft()) / 30.0F;
                });
    }

    public static void registerHudRenderers() {
        HudRenderCallback.EVENT.register(new FullMetalHudOverlay());
    }

    public static void registerLivingEntityRenderers() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, renderContext) -> {
            if (entityType == EntityType.PLAYER && entityRenderer instanceof FeatureRendererContext<?, ?> genericContext) {
                try {
                    @SuppressWarnings("unchecked")
                    FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>> typedContext = (FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>>) genericContext;
                    registrationHelper.register(new IronSkinLayer(typedContext));
                    registrationHelper.register(new FullMetalLayer(typedContext));
                } catch (ClassCastException e) {
                    System.err.println("Could not register player layer: " + e.getMessage());
                }
            }
        });
    }
}

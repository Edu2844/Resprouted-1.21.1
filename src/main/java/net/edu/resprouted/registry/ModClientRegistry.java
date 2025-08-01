package net.edu.resprouted.registry;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.entity.renderer.CrushingTubBERenderer;
import net.edu.resprouted.block.entity.renderer.EvaporatingBasinRenderer;
import net.edu.resprouted.block.entity.renderer.LiquidBarrelRenderer;
import net.edu.resprouted.effect.render.FullMetalLayer;
import net.edu.resprouted.effect.render.FullMetalOverlay;
import net.edu.resprouted.effect.render.IronSkinLayer;
import net.edu.resprouted.entity.ModEntities;
import net.edu.resprouted.entity.client.ChairEntityRenderer;
import net.edu.resprouted.entity.client.TomatoEntityRenderer;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.ModItems;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.block.Block;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;

public class ModClientRegistry {
    public static void RegisterModClientStuffs(){
        registerBlockEntityRenderers();
        registerEntityRenderers();
        registerBlockRenderers();
        registerFluidRenderers();
        registerBlockColors();
        registerItemColors();
        registerHudRenderers();
        registerLivingEntityRenderers();
    }
    public static void registerBlockEntityRenderers() {
        BlockEntityRendererFactories.register(ModBlockEntities.CRUSHING_TUB_BE, CrushingTubBERenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.LIQUID_BARREL_BE, LiquidBarrelRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.EVAPORATING_BASIN_BE, EvaporatingBasinRenderer::new);

    }
    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(ModEntities.CHAIR, ChairEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.THROWN_TOMATO, TomatoEntityRenderer::new);
        TerraformBoatClientHelper.registerModelLayers(ModEntities.OLIVE_BOAT, false);
        TerraformBoatClientHelper.registerModelLayers(ModEntities.IRONWOOD_BOAT, false);
    }
    public static void registerBlockRenderers() {
        BlockRenderLayerMap registry = BlockRenderLayerMap.INSTANCE;
        registry.putBlock(ModBlocks.OLIVE_SAPLING, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.IRONWOOD_SAPLING, RenderLayer.getCutout());
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
        //Doors&Trapdoors
        registry.putBlock(ModBlocks.IRONWOOD_DOOR, RenderLayer.getCutout());
        registry.putBlock(ModBlocks.IRONWOOD_TRAPDOOR, RenderLayer.getCutout());
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
    }
    public static void registerFluidRenderers() {
        FluidRenderHandlerRegistry registry = FluidRenderHandlerRegistry.INSTANCE;
        //Honey
        registry.register(ModFluids.HONEY_STILL, ModFluids.HONEY_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/honey_still"),
                Identifier.of("resprouted:block/fluid/honey_flow")
        ));
        //Apple Juice
        registry.register(ModFluids.APPLE_JUICE_STILL, ModFluids.APPLE_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/apple_juice_still"),
                Identifier.of("resprouted:block/fluid/apple_juice_flow")
        ));
        //Golden Apple Juice
        registry.register(ModFluids.GOLDEN_APPLE_JUICE_STILL, ModFluids.GOLDEN_APPLE_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/golden_apple_juice_still"),
                Identifier.of("resprouted:block/fluid/golden_apple_juice_flow")
        ));
        //Grape Juice
        registry.register(ModFluids.GRAPE_JUICE_STILL, ModFluids.GRAPE_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/grape_juice_still"),
                Identifier.of("resprouted:block/fluid/grape_juice_flow")
        ));
        //Sweet Berry Juice
        registry.register(ModFluids.SWEET_BERRY_JUICE_STILL, ModFluids.SWEET_BERRY_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/sweet_berry_juice_still"),
                Identifier.of("resprouted:block/fluid/sweet_berry_juice_flow")
        ));
        //Olive Oil
        registry.register(ModFluids.OLIVE_OIL_STILL, ModFluids.OLIVE_OIL_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/olive_oil_still"),
                Identifier.of("resprouted:block/fluid/olive_oil_flow")
        ));
        //Vanta Oil
        registry.register(ModFluids.VANTA_OIL_STILL, ModFluids.VANTA_OIL_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/vanta_oil_still"),
                Identifier.of("resprouted:block/fluid/vanta_oil_flow")
        ));
        //Glow Berry Juice
        registry.register(ModFluids.GLOW_BERRY_JUICE_STILL, ModFluids.GLOW_BERRY_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/glow_berry_juice_still"),
                Identifier.of("resprouted:block/fluid/glow_berry_juice_flow")
        ));
        //Iron Berry Juice
        registry.register(ModFluids.IRON_BERRY_JUICE_STILL, ModFluids.IRON_BERRY_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/iron_berry_juice_still"),
                Identifier.of("resprouted:block/fluid/iron_berry_juice_flow")
        ));
        //Ale Wort
        registry.register(ModFluids.ALE_WORT_STILL, ModFluids.ALE_WORT_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/ale_wort_still"),
                Identifier.of("resprouted:block/fluid/ale_wort_flow")
        ));
    }
    public static void registerBlockColors() {
        ColorProviderRegistry<Block, BlockColorProvider> registry = ColorProviderRegistry.BLOCK;

        registry.register((state, world, pos, tintIndex) -> {
                    if (world == null || pos == null) {
                        return 0x79C05A;
                    }
                    return BiomeColors.getFoliageColor(world, pos);
                },
                ModBlocks.APPLE_LEAVES);

        registry.register((state, world, pos, tintIndex) -> {
                    if (world == null || pos == null) {
                        return 0x79C05A;
                    }
                    return BiomeColors.getFoliageColor(world, pos);
                },
                ModBlocks.GRAPE_LEAVES);
    }
    public static void registerItemColors() {
        ColorProviderRegistry<ItemConvertible, ItemColorProvider> registry = ColorProviderRegistry.ITEM;

        registry.register((stack, tintIndex) -> 0x48B518, ModBlocks.APPLE_LEAVES.asItem());

        registry.register((stack, tintIndex) -> {
            if (tintIndex == 0) {
                PotionContentsComponent potionContents = stack.get(DataComponentTypes.POTION_CONTENTS);
                if (potionContents != null && potionContents.getColor() != -1) {
                    return potionContents.getColor();
                }
                return 0x385DC6;
            }
            return -1;
        }, ModItems.ELIXIR_BOTTLE);
    }
    public static void registerHudRenderers() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> FullMetalOverlay.render(drawContext));

    }
    public static void registerLivingEntityRenderers() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, renderContext) -> {
            if (entityType == EntityType.PLAYER && entityRenderer instanceof FeatureRendererContext<?, ?> genericContext) {
                try {
                    @SuppressWarnings("unchecked")
                    FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>> typedContext =
                            (FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>>) genericContext;
                    registrationHelper.register(new IronSkinLayer(typedContext));
                    registrationHelper.register(new FullMetalLayer(typedContext));
                } catch (ClassCastException e) {
                    System.err.println("No se pudo registrar capa para jugador: " + e.getMessage());
                }
            }
        });
    }
}

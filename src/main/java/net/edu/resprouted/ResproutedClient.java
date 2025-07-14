package net.edu.resprouted;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.entity.renderer.CrushingTubBERenderer;
import net.edu.resprouted.block.entity.renderer.EvaporatingBasinRenderer;
import net.edu.resprouted.block.entity.renderer.LiquidBarrelRenderer;
import net.edu.resprouted.effect.render.FullMetalLayer;
import net.edu.resprouted.effect.render.FullMetalOverlay;
import net.edu.resprouted.effect.render.IronSkinLayer;
import net.edu.resprouted.entity.ModEntities;
import net.edu.resprouted.entity.client.ChairRenderer;
import net.edu.resprouted.entity.client.TomatoEntityRenderer;
import net.edu.resprouted.event.ModKeybinds;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

@SuppressWarnings("unchecked")
public class ResproutedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModKeybinds.registerKeybinds();
        // =================================================
        // ||             FLUID STORAGES RENDER           ||
        // =================================================
        BlockEntityRendererFactories.register(ModBlockEntities.CRUSHING_TUB_BE, CrushingTubBERenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.LIQUID_BARREL_BE, LiquidBarrelRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.EVAPORATING_BASIN_BE, EvaporatingBasinRenderer::new);
        // =================================================
        // ||              GRAPE LEAVES COLOR             ||
        // =================================================
        ColorProviderRegistry.BLOCK.register(
                (state, world, pos, tintIndex) -> {
                    if (world == null || pos == null) {
                        return 0x79C05A;
                    }
                    return BiomeColors.getFoliageColor(world, pos);
                },
                ModBlocks.GRAPE_LEAVES
        );
        // =================================================
        // ||              APPLE LEAVES COLOR             ||
        // =================================================
        //Block
        ColorProviderRegistry.BLOCK.register(
                (state, world, pos, tintIndex) -> {
                    if (world == null || pos == null) {
                        return 0x79C05A;
                    }
                    return BiomeColors.getFoliageColor(world, pos);
                },
                ModBlocks.APPLE_LEAVES
        );
        //Item
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return 0x48B518; // Color del item
        }, ModBlocks.APPLE_LEAVES.asItem());

        // =================================================
        // ||               FULL METAL RENDER             ||
        // =================================================
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> FullMetalOverlay.render(drawContext));

        // =================================================
        // ||                 EFFECT LAYER                ||
        // =================================================
        //Iron skin
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityType == EntityType.PLAYER && entityRenderer instanceof FeatureRendererContext<?, ?> featureContext) {
                FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>> typedContext = (FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>>) featureContext;
                registrationHelper.register(new IronSkinLayer(typedContext));
            }
        });
        //Full metal
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityType == EntityType.PLAYER && entityRenderer instanceof FeatureRendererContext<?, ?> featureContext) {
                FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>> typedContext = (FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>>) featureContext;
                registrationHelper.register(new FullMetalLayer(typedContext));
            }
        });

        // =================================================
        // ||                CHAIR RENDER                 ||
        // =================================================
        EntityRendererRegistry.register(ModEntities.CHAIR, ChairRenderer::new);

        // =================================================
        // ||                TOMATO RENDER                ||
        // =================================================
        EntityRendererRegistry.register(ModEntities.THROWN_TOMATO, TomatoEntityRenderer::new);

        // =================================================
        // ||                SAPLINGS RENDER              ||
        // =================================================
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OLIVE_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.IRONWOOD_SAPLING, RenderLayer.getCutout());

        // =================================================
        // ||                  HERBS RENDER               ||
        // =================================================
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ALOE_VERA_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HORSETAIL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CHAMOMILE_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COHOSH_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CLOUDSBLUFF_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLOOD_ORCHID_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GINSENG_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MARSHMALLOW_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VANTA_LILY_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WIND_THISTLE_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOONCAP_MUSHROOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DEATHSTALK_MUSHROOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CORE_ROOT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.APPLE_LEAVES, RenderLayer.getCutout());

        // =================================================
        // ||                 AGRICULTURE                 ||
        // =================================================
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRAPE_STEM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRAPE_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TOMATO_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CHILI_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.APPLE_TREE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.APPLE_SAPLING, RenderLayer.getCutout());

        // =================================================
        // ||                 BUSHES RENDER               ||
        // =================================================
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUE_BERRY_BUSH, RenderLayer.getCutout());

        // =================================================
        // ||                 CHAINS RENDER               ||
        // =================================================
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GOLDEN_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPOSED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEATHERED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OXIDIZED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN, RenderLayer.getCutout());

        // =================================================
        // ||                 CHANDELIERS                 ||
        // =================================================
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CHANDELIER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GOLDEN_CHANDELIER, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_CHANDELIER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPOSED_COPPER_CHANDELIER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEATHERED_COPPER_CHANDELIER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OXIDIZED_COPPER_CHANDELIER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_COPPER_CHANDELIER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_CHANDELIER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_CHANDELIER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_CHANDELIER, RenderLayer.getCutout());

        // =================================================
        // ||                LANTERNS RENDER              ||
        // =================================================
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GOLDEN_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GOLDEN_SOUL_LANTERN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPOSED_COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEATHERED_COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OXIDIZED_COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPOSED_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEATHERED_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OXIDIZED_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_SOUL_LANTERN, RenderLayer.getCutout());

        // =================================================
        // ||           DOORS AND TRAPDOORS RENDER        ||
        // =================================================
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.IRONWOOD_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.IRONWOOD_TRAPDOOR, RenderLayer.getCutout());

        // =================================================
        // ||                 FLUIDS RENDER               ||
        // =================================================
        //Honey
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.HONEY_STILL, ModFluids.HONEY_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("resprouted:block/fluid/honey_still"),
                Identifier.of("resprouted:block/fluid/honey_flow")
        ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.HONEY_STILL, ModFluids.HONEY_FLOWING);

        //Apple Juice
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.APPLE_JUICE_STILL, ModFluids.APPLE_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("minecraft:block/water_still"),
                Identifier.of("minecraft:block/water_flow"),
                0xC67F37
        ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.APPLE_JUICE_STILL, ModFluids.APPLE_JUICE_FLOWING);

        //Golden Apple Juice
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.GOLDEN_APPLE_JUICE_STILL, ModFluids.GOLDEN_APPLE_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("minecraft:block/water_still"),
                Identifier.of("minecraft:block/water_flow"),
                0xFFEE3D
        ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.GOLDEN_APPLE_JUICE_STILL, ModFluids.GOLDEN_APPLE_JUICE_FLOWING);

        //Grape Juice
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.GRAPE_JUICE_STILL, ModFluids.GRAPE_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("minecraft:block/water_still"),
                Identifier.of("minecraft:block/water_flow"),
                0x512D4A
        ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.GRAPE_JUICE_STILL, ModFluids.GRAPE_JUICE_FLOWING);

        //Sweet Berry Juice
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.SWEET_BERRY_JUICE_STILL, ModFluids.SWEET_BERRY_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("minecraft:block/water_still"),
                Identifier.of("minecraft:block/water_flow"),
                0x87202E
        ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.SWEET_BERRY_JUICE_STILL, ModFluids.SWEET_BERRY_JUICE_FLOWING);

        //Olive Oil
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.OLIVE_OIL_STILL, ModFluids.OLIVE_OIL_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("minecraft:block/water_still"),
                Identifier.of("minecraft:block/water_flow"),
                0xB5A426
        ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.OLIVE_OIL_STILL, ModFluids.OLIVE_OIL_FLOWING);

        //Glow Berry Juice
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.GLOW_BERRY_JUICE_STILL, ModFluids.GLOW_BERRY_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("minecraft:block/water_still"),
                Identifier.of("minecraft:block/water_flow"),
                0xF19645
        ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.GLOW_BERRY_JUICE_STILL, ModFluids.GLOW_BERRY_JUICE_FLOWING);

        //Iron Berry Juice
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.IRON_BERRY_JUICE_STILL, ModFluids.IRON_BERRY_JUICE_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("minecraft:block/water_still"),
                Identifier.of("minecraft:block/water_flow"),
                0x8B8989
        ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.IRON_BERRY_JUICE_STILL, ModFluids.IRON_BERRY_JUICE_FLOWING);

        // =================================================
        // ||             ELIXIR BOTTLE COLOR             ||
        // =================================================
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
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
}

package net.edu.resprouted.entity.client;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.effect.render.EffectOverlayLayer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback.RegistrationHelper;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ModEntityRenderers {
    private static final Identifier IRON_SKIN_TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/entity/layer/iron_skin_layer.png");
    private static final Identifier FULL_METAL_TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/entity/layer/full_metal_layer.png");

    public static void registerLivingEntityRenderers() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register(
                (entityType, entityRenderer, registrationHelper, context) -> {
                    //Player
                    if (entityType == EntityType.PLAYER) {
                        registerPlayerLayers(entityRenderer, registrationHelper);
                    }
                    // ======================================
                    // ||             HOSTILES             ||
                    // ======================================
                    //Zombie
                    if (entityType == EntityType.ZOMBIE) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    //Husk
                    if (entityType == EntityType.HUSK) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    //Skeleton
                    if (entityType == EntityType.SKELETON) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    //Stray
                    if (entityType == EntityType.STRAY) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    //Wither Skeleton
                    if (entityType == EntityType.WITHER_SKELETON) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    //Creeper
                    if (entityType == EntityType.CREEPER) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    //Slime
                    if (entityType == EntityType.SLIME) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    //Witch
                    if (entityType == EntityType.WITCH) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    //Vindicator
                    if (entityType == EntityType.VINDICATOR) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    //Pillager
                    if (entityType == EntityType.PILLAGER) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    //Evoker
                    if (entityType == EntityType.EVOKER) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    // ======================================
                    // ||              NEUTRAL             ||
                    // ======================================
                    //Fox
                    if (entityType == EntityType.FOX) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    //Enderman
                    if (entityType == EntityType.ENDERMAN) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    //Spider
                    if (entityType == EntityType.SPIDER) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    //Cave Spider
                    if (entityType == EntityType.CAVE_SPIDER) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    //Goat
                    if (entityType == EntityType.GOAT) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    //Llama
                    if (entityType == EntityType.LLAMA) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }

                    // ======================================
                    // ||              PACIFIC             ||
                    // ======================================
                    //Sheep
                    if (entityType == EntityType.SHEEP) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    //Pig
                    if (entityType == EntityType.PIG) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    //Chicken
                    if (entityType == EntityType.CHICKEN) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                    //Cow
                    if (entityType == EntityType.COW) {
                        registerGenericMobLayers(entityRenderer, registrationHelper);
                    }
                }
        );
    }

    @SuppressWarnings("unchecked")
    private static void registerPlayerLayers(LivingEntityRenderer<?, ?> entityRenderer, RegistrationHelper registrationHelper) {
        if (entityRenderer instanceof FeatureRendererContext<?, ?> context) {
            try {
                var typedContext = (FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>>) context;

                //Iron Skin Layer
                registrationHelper.register(new EffectOverlayLayer<>(
                        typedContext,
                        IRON_SKIN_TEXTURE,
                        player -> player.hasStatusEffect(ModEffects.IRON_SKIN)
                ));

                //Full Metal Layer
                registrationHelper.register(new EffectOverlayLayer<>(
                        typedContext,
                        FULL_METAL_TEXTURE,
                        player -> player.hasStatusEffect(ModEffects.FULL_METAL)
                ));

                Resprouted.LOGGER.info("Successfully registered player effect layers");
            } catch (ClassCastException e) {
                Resprouted.LOGGER.error("Failed to register player effect layers", e);
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void registerGenericMobLayers(LivingEntityRenderer<?, ?> entityRenderer, RegistrationHelper registrationHelper) {
        try {
            //Iron Skin Layer
            registrationHelper.register(new EffectOverlayLayer(
                    entityRenderer,
                    IRON_SKIN_TEXTURE,
                    entity -> entity instanceof LivingEntity living && living.hasStatusEffect(ModEffects.IRON_SKIN)
            ));

            //Full Metal Layer
            registrationHelper.register(new EffectOverlayLayer(
                    entityRenderer,
                    FULL_METAL_TEXTURE,
                    entity -> entity instanceof LivingEntity living && living.hasStatusEffect(ModEffects.FULL_METAL)
            ));

            Resprouted.LOGGER.info("Successfully registered mob effect layers for {}", entityRenderer.getClass().getSimpleName());
        } catch (Exception e) {
            Resprouted.LOGGER.error("Failed to register mob effect layers", e);
        }
    }
}
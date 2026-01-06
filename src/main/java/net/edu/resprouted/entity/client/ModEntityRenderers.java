package net.edu.resprouted.entity.client;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.effect.render.EffectOverlayLayer;
import net.edu.resprouted.entity.interfaces.ResproutedEntityExtension;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback.RegistrationHelper;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ModEntityRenderers {
    private static final Identifier IRON_SKIN_TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/entity/layer/iron_skin_layer.png");
    private static final Identifier FULL_METAL_TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/entity/layer/full_metal_layer.png");

    public static void registerLivingEntityRenderers() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register(
                (entityType, entityRenderer, registrationHelper, context) -> registerEffectLayers(entityRenderer, registrationHelper)
        );

        Resprouted.LOGGER.info("Successfully registered effect overlay layers");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void registerEffectLayers(LivingEntityRenderer<?, ?> entityRenderer, RegistrationHelper registrationHelper) {
        try {
            registrationHelper.register(new EffectOverlayLayer(
                    entityRenderer,
                    IRON_SKIN_TEXTURE,
                    entity -> entity instanceof ResproutedEntityExtension ext && ext.resprouted$hasIronSkin()
            ));

            registrationHelper.register(new EffectOverlayLayer(
                    entityRenderer,
                    FULL_METAL_TEXTURE,
                    entity -> entity instanceof ResproutedEntityExtension ext && ext.resprouted$hasFullMetal()
            ));
        } catch (Exception e) {
            Resprouted.LOGGER.warn("Failed to register effect layers for {}",
                    entityRenderer.getClass().getSimpleName(), e);
        }
    }
}
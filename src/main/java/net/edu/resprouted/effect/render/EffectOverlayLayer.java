package net.edu.resprouted.effect.render;

import net.edu.resprouted.ResproutedClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

import java.util.function.Predicate;

@Environment(EnvType.CLIENT)
public class EffectOverlayLayer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    private final Identifier texture;
    private final Predicate<T> shouldRender;

    public EffectOverlayLayer(FeatureRendererContext<T, M> context, Identifier texture, Predicate<T> shouldRender) {
        super(context);
        this.texture = texture;
        this.shouldRender = shouldRender;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float headYaw, float headPitch) {

        String entityId = EntityType.getId(entity.getType()).toString();

        if (ResproutedClient.CLIENT_CONFIG.setEntityBlacklisted(entityId)) {
            return;
        }

        if (shouldRender.test(entity) && !entity.isInvisible()) {
            M model = getContextModel();
            model.animateModel(entity, limbSwing, limbSwingAmount, partialTick);
            model.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch);

            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucentCull(texture));
            model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        }
    }
}
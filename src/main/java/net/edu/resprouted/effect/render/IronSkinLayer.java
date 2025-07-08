package net.edu.resprouted.effect.render;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.effect.ModEffects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class IronSkinLayer extends FeatureRenderer<PlayerEntity, PlayerEntityModel<PlayerEntity>> {
    private static final Identifier TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/entity/layer/iron_skin_layer.png");

    public IronSkinLayer(FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>> context) {
        super(context);
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, PlayerEntity player, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float headYaw, float headPitch) {
        if (player.hasStatusEffect(ModEffects.IRON_SKIN) && !player.isInvisible()) {
            PlayerEntityModel<PlayerEntity> model = getContextModel();
            model.animateModel(player, limbSwing, limbSwingAmount, partialTick);
            model.setAngles(player, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch);
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucentCull(TEXTURE));
            model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        }
    }
}

package net.edu.resprouted.entity.client;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.entity.custom.ThrownTomatoEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TomatoEntityRenderer extends EntityRenderer<ThrownTomatoEntity> {
    private static final Identifier TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/item/tomato.png");
    private final ItemRenderer itemRenderer;
    private final float scale;

    public TomatoEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.itemRenderer = ctx.getItemRenderer();
        this.scale = 1.0f;
    }

    @Override
    public void render(ThrownTomatoEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.scale(this.scale, this.scale, this.scale);
        matrices.multiply(this.dispatcher.getRotation());
        this.itemRenderer.renderItem(
                entity.getStack(),
                ModelTransformationMode.GROUND,
                light,
                OverlayTexture.DEFAULT_UV,
                matrices,
                vertexConsumers,
                entity.getWorld(),
                entity.getId()
        );
        matrices.pop();
    }

    @Override
    public Identifier getTexture(ThrownTomatoEntity entity) {
        return TEXTURE;
    }
}
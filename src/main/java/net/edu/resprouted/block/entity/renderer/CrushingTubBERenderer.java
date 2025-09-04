package net.edu.resprouted.block.entity.renderer;

import net.edu.resprouted.block.entity.custom.CrushingTubBE;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.*;
import java.util.Random;


public class CrushingTubBERenderer extends AbstractFluidStorageRenderer<CrushingTubBE> {

    public CrushingTubBERenderer(BlockEntityRendererFactory.Context ctx) {
        super(ctx, 0.0625f, 0.5f);
    }

    @Override
    protected void renderCustomContent(CrushingTubBE entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        if (!entity.getStack(0).isEmpty()) {
            ItemStack stack = entity.getStack(0);

            ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

            Random random = new Random(entity.getPos().asLong());
            light = getLightLevel(entity.getWorld(), entity.getPos());

            matrices.push();
            matrices.translate(0.5f, 0.1f, 0.5f + 0.002f);
            matrices.scale(0.55f, 0.55f, 0.55f);

            int itemCount = stack.getCount();
            int renderPasses = Math.min(16, (int) Math.ceil(itemCount / 4f));
            float yOffset = 0;

            for (int i = 0; i < renderPasses; i++) {
                matrices.push();
                matrices.translate(0, yOffset, 0);
                if (i > 0) {
                    matrices.translate(
                            (random.nextFloat() - 0.5f) * 0.1f,
                            0,
                            (random.nextFloat() - 0.5f) * 0.1f
                    );
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(random.nextFloat() * 360));
                }
                if (stack.getItem() instanceof BlockItem) {
                    matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                } else {
                    matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                    matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(45));
                }
                BakedModel model = itemRenderer.getModel(stack, entity.getWorld(), null, 0);
                VertexConsumer cutoutBuffer = vertexConsumers.getBuffer(RenderLayer.getCutout());
                itemRenderer.renderItem(
                        stack,
                        ModelTransformationMode.GUI,
                        false,
                        matrices,
                        layer -> cutoutBuffer,
                        light,
                        OverlayTexture.DEFAULT_UV,
                        model
                );
                matrices.pop();
                yOffset += 0.05f;
            }
            matrices.pop();
        }
    }
}

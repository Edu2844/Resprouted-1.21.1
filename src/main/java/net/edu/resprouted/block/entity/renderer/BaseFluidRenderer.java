package net.edu.resprouted.block.entity.renderer;

import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.item.BlockItem;
import net.minecraft.util.math.*;

import java.util.Random;

@SuppressWarnings("all")
public abstract class BaseFluidRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {

    protected BaseFluidRenderer(Context ctx) {
    }

    protected void renderFluidSprite(FluidVariant fluid, World world, BlockPos pos,
                                     MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                                     float y, float minX, float maxX, float minZ, float maxZ,
                                     int light, int overlay) {

        int color = FluidVariantRendering.getColor(fluid, world, pos);
        Sprite sprite = FluidVariantRendering.getSprites(fluid)[0];
        RenderLayer renderLayer = RenderLayers.getFluidLayer(fluid.getFluid().getDefaultState());
        VertexConsumer buffer = vertexConsumers.getBuffer(renderLayer);

        matrices.push();
        matrices.translate(0, y, 0);
        MatrixStack.Entry entry = matrices.peek();

        drawQuad(buffer, entry, minX, 0, minZ, maxX, 0, maxZ, sprite.getMinU(), sprite.getMinV(), sprite.getMaxU(), sprite.getMaxV(), color, light, overlay);

        matrices.pop();
    }


    protected void renderItemStack(ItemStack stack, World world, BlockPos pos,
                                   MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                                   float baseX, float baseY, float baseZ,
                                   float scale, int light) {

        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        Random random = new Random(pos.asLong());

        matrices.push();
        matrices.translate(baseX, baseY, baseZ);
        matrices.scale(scale, scale, scale);

        int itemCount = stack.getCount();
        int renderPasses = Math.min(16, (int) Math.ceil(itemCount / 4f));
        float yOffset = 0;
        boolean isBlockItem = stack.getItem() instanceof BlockItem;

        BakedModel model = itemRenderer.getModel(stack, world, null, 0);
        VertexConsumer cutoutBuffer = vertexConsumers.getBuffer(RenderLayer.getCutout());

        for (int i = 0; i < renderPasses; i++) {
            matrices.push();
            matrices.translate(0, yOffset, 0);

            if (i > 0) {
                matrices.translate((random.nextFloat() - 0.5f) * 0.1f, 0, (random.nextFloat() - 0.5f) * 0.1f);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(random.nextFloat() * 360));
            }

            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
            if (!isBlockItem) {
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(45));
            }

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

    protected static void drawQuad(VertexConsumer vertexConsumer,
                                   MatrixStack.Entry entry,
                                   float x1, float y1, float z1,
                                   float x2, float y2, float z2,
                                   float minU, float minV,
                                   float maxU, float maxV,
                                   int color,
                                   int light, int overlay) {

        int a = (color >> 24) & 0xFF;
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;

        vertexConsumer.vertex(entry, x1, y1, z1)
                .color(r, g, b, a)
                .texture(minU, minV)
                .light(light)
                .overlay(overlay)
                .normal(0.0F, 1.0F, 0.0F);

        vertexConsumer.vertex(entry, x1, y2, z2)
                .color(r, g, b, a)
                .texture(minU, maxV)
                .light(light)
                .overlay(overlay)
                .normal(0.0F, 1.0F, 0.0F);

        vertexConsumer.vertex(entry, x2, y2, z2)
                .color(r, g, b, a)
                .texture(maxU, maxV)
                .light(light)
                .overlay(overlay)
                .normal(0.0F, 1.0F, 0.0F);

        vertexConsumer.vertex(entry, x2, y1, z1)
                .color(r, g, b, a)
                .texture(maxU, minV)
                .light(light)
                .overlay(overlay)
                .normal(0.0F, 1.0F, 0.0F);
    }

    protected int getLightLevel(World world, BlockPos pos) {
        if (world == null) return LightmapTextureManager.MAX_LIGHT_COORDINATE;
        return LightmapTextureManager.pack(
                world.getLightLevel(LightType.BLOCK, pos),
                world.getLightLevel(LightType.SKY, pos)
        );
    }
}
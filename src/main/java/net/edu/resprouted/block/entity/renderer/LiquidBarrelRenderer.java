package net.edu.resprouted.block.entity.renderer;

import net.edu.resprouted.block.entity.custom.LiquidBarrelBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import org.joml.Matrix4f;

public class LiquidBarrelRenderer implements BlockEntityRenderer<LiquidBarrelBlockEntity> {
    public LiquidBarrelRenderer(BlockEntityRendererFactory.Context context) {
    }
    @Override
    public void render(LiquidBarrelBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        light = getLightLevel(entity.getWorld(), entity.getPos());
        FluidVariant fluid = entity.getFluid();
        long amount = entity.liquidbarrel.getAmount();
        long capacity = entity.liquidbarrel.getCapacity();

        if (!fluid.isBlank() && amount > 0) {
            float fillPercentage = MathHelper.clamp((float) amount / capacity, 0, 1);
            int color = FluidVariantRendering.getColor(fluid, entity.getWorld(), entity.getPos());
            Sprite sprite = FluidVariantRendering.getSprites(fluid)[0];

            RenderLayer renderLayer = RenderLayers.getFluidLayer(fluid.getFluid().getDefaultState());
            VertexConsumer buffer = vertexConsumers.getBuffer(renderLayer);

            float fluidHeight = 0.0625f + fillPercentage * 0.937f;

            matrices.push();
            matrices.translate(0, fluidHeight - 0.0005f, 0);
            Matrix4f matrix = matrices.peek().getPositionMatrix();

            float min = 0.03125f;
            float max = 0.96875f;

            float u1 = sprite.getMinU();
            float v1 = sprite.getMinV();
            float u2 = sprite.getMaxU();
            float v2 = sprite.getMaxV();

            int a = (color >> 24) & 0xFF;
            int r = (color >> 16) & 0xFF;
            int g = (color >> 8) & 0xFF;
            int b = (color) & 0xFF;

            buffer.vertex(matrix, min, 0, min).color(r, g, b, a).texture(u1, v1).light(light).normal(0, 1, 0);
            buffer.vertex(matrix, min, 0, max).color(r, g, b, a).texture(u1, v2).light(light).normal(0, 1, 0);
            buffer.vertex(matrix, max, 0, max).color(r, g, b, a).texture(u2, v2).light(light).normal(0, 1, 0);
            buffer.vertex(matrix, max, 0, min).color(r, g, b, a).texture(u2, v1).light(light).normal(0, 1, 0);

            matrices.pop();
        }
    }
    private int getLightLevel(World world, BlockPos pos) {
        if (world == null) return LightmapTextureManager.MAX_LIGHT_COORDINATE;
        return LightmapTextureManager.pack(
                world.getLightLevel(LightType.BLOCK, pos),
                world.getLightLevel(LightType.SKY, pos)
        );
    }
}

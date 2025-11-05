package net.edu.resprouted.block.entity.renderer;

import net.edu.resprouted.block.entity.custom.LiquidBarrelBlockEntity;
import net.edu.resprouted.util.SmoothFloat;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.block.entity.BlockEntity;
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

import java.util.HashMap;
import java.util.Map;

public class LiquidBarrelRenderer implements BlockEntityRenderer<LiquidBarrelBlockEntity> {

    private final Map<BlockPos, SmoothFloat> fluidAnimations = new HashMap<>();

    public LiquidBarrelRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(LiquidBarrelBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        //Item Render
        light = getLightLevel(entity.getWorld(), entity.getPos());
        FluidVariant fluid = entity.getFluidStorage().getResource();
        long amount = entity.getFluidStorage().getAmount();
        long capacity = entity.getFluidStorage().getCapacity();

        if (!fluid.isBlank() && amount > 0) {
            BlockPos pos = entity.getPos();
            SmoothFloat animation = fluidAnimations.computeIfAbsent(pos, k -> new SmoothFloat());

            float targetHeight = MathHelper.clamp((float) amount / capacity, 0, 1);
            animation.update(targetHeight, 0.15f);

            float fillPercentage = animation.get(tickDelta);
            float heightMultiplier = 0.937f;
            float minHeight = 0.0625f;
            float fluidHeight = minHeight + fillPercentage * heightMultiplier;

            int color = FluidVariantRendering.getColor(fluid, entity.getWorld(), entity.getPos());
            Sprite sprite = FluidVariantRendering.getSprites(fluid)[0];

            RenderLayer renderLayer = RenderLayers.getFluidLayer(fluid.getFluid().getDefaultState());
            VertexConsumer buffer = vertexConsumers.getBuffer(renderLayer);

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

        cleanupAnimations(entity.getWorld());
    }

    private void cleanupAnimations(World world) {
        if (world == null) return;

        fluidAnimations.keySet().removeIf(pos -> {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            return !(blockEntity instanceof LiquidBarrelBlockEntity);
        });
    }

    private int getLightLevel(World world, BlockPos pos) {
        if (world == null) return LightmapTextureManager.MAX_LIGHT_COORDINATE;
        return LightmapTextureManager.pack(
                world.getLightLevel(LightType.BLOCK, pos),
                world.getLightLevel(LightType.SKY, pos)
        );
    }
}
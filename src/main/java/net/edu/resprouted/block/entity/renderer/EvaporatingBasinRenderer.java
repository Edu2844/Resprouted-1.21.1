package net.edu.resprouted.block.entity.renderer;

import net.edu.resprouted.block.entity.custom.EvaporatingBasinBE;
import net.edu.resprouted.util.SmoothFloat;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import org.joml.Matrix4f;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EvaporatingBasinRenderer implements BlockEntityRenderer<EvaporatingBasinBE> {

    private final Map<BlockPos, SmoothFloat> fluidAnimations = new HashMap<>();

    public EvaporatingBasinRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(EvaporatingBasinBE entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        //Item Render
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

        //Fluid Render
        FluidVariant fluid = entity.getFluidStorage().getResource();
        long amount = entity.getFluidStorage().getAmount();
        long capacity = entity.getFluidStorage().getCapacity();

        if (fluid.isBlank() || amount <= 0) {

            BlockPos pos = entity.getPos();
            if (fluidAnimations.containsKey(pos)) {
                SmoothFloat animation = fluidAnimations.get(pos);
                animation.update(0f, 0.5f);
                if (animation.get(tickDelta) < 0.01f) {
                    fluidAnimations.remove(pos);
                }
            }
            return;
        }

        BlockPos pos = entity.getPos();
        SmoothFloat animation = fluidAnimations.computeIfAbsent(pos, k -> new SmoothFloat());

        float targetFill = MathHelper.clamp((float) amount / capacity, 0, 1);
        animation.update(targetFill, 0.15f);

        float fillPercentage = animation.get(tickDelta);
        float baseY = 1f / 16f;
        float maxHeight = 5f / 16f;
        float y = baseY + fillPercentage * maxHeight;

        int argb = FluidVariantRendering.getColor(fluid, entity.getWorld(), entity.getPos());
        int a = (argb >>> 24) & 0xFF;
        int r = (argb >>> 16) & 0xFF;
        int g = (argb >>>  8) & 0xFF;
        int b =  argb         & 0xFF;

        Sprite sprite = FluidVariantRendering.getSprites(fluid)[0];
        RenderLayer renderLayer = RenderLayers.getFluidLayer(fluid.getFluid().getDefaultState());
        VertexConsumer buf = vertexConsumers.getBuffer(renderLayer);

        final float MIN = 4f / 16f;
        final float MAX = 12f / 16f;

        matrices.push();
        matrices.translate(0, y - 0.0005f, 0);
        Matrix4f mat = matrices.peek().getPositionMatrix();

        float u1 = sprite.getMinU();
        float v1 = sprite.getMinV();
        float u2 = sprite.getMaxU();
        float v2 = sprite.getMaxV();

        buf.vertex(mat, MIN, 0, MIN).color(r, g, b, a).texture(u1, v1).light(light).normal(0, 1, 0);
        buf.vertex(mat, MIN, 0, MAX).color(r, g, b, a).texture(u1, v2).light(light).normal(0, 1, 0);
        buf.vertex(mat, MAX, 0, MAX).color(r, g, b, a).texture(u2, v2).light(light).normal(0, 1, 0);
        buf.vertex(mat, MAX, 0, MIN).color(r, g, b, a).texture(u2, v1).light(light).normal(0, 1, 0);

        matrices.pop();

        cleanupAnimations(entity.getWorld());
    }

    private void cleanupAnimations(World world) {
        if (world == null) return;

        fluidAnimations.keySet().removeIf(pos -> {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            return !(blockEntity instanceof EvaporatingBasinBE);
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

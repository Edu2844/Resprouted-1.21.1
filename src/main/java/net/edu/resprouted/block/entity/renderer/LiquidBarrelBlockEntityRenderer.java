package net.edu.resprouted.block.entity.renderer;

import net.edu.resprouted.block.entity.custom.decorative.LiquidBarrelBlockEntity;
import net.edu.resprouted.util.render.RenderUtils;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;

@SuppressWarnings("unused")
public class LiquidBarrelBlockEntityRenderer implements BlockEntityRenderer<LiquidBarrelBlockEntity> {
    public LiquidBarrelBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(LiquidBarrelBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        BlockState state = entity.getCachedState();
        FluidVariant fluid = entity.getFluidStorage().getResource();
        long amount = entity.getFluidStorage().getAmount();
        if (!fluid.isBlank() && amount > 0) {
            // Calculate fluid level based on capacity (This is for the fluid animation)
            float fillPercentage = entity.getFluidHeight(tickDelta);
            // Without fluid animation:
            // float fillPercentage = MathHelper.clamp((float) amount / entity.getFluidStorage().getCapacity(), 0, 1);

            // Get fluid visual properties
            int color = FluidVariantRendering.getColor(fluid, entity.getWorld(), entity.getPos());
            Sprite sprite = RenderUtils.getFluidSprite(fluid);
            RenderLayer renderLayer = TexturedRenderLayers.getEntityTranslucentCull();
            VertexConsumer buffer = vertexConsumers.getBuffer(renderLayer);

            final float BASE_HEIGHT = 0.0625f; // Minimum fluid height (1 px: 1/16 = 0.0625)
            final float MAX_FILL_HEIGHT = 0.875f; // Maximum fluid height (14 px: 14/16 = 0.875)
            final float Z_OFFSET = 0.0005f; // Tiny offset to prevent z-fighting
            final float MIN_XZ = 0.25f; // 4 pixels from edge (4/16 = 0.25)
            final float MAX_XZ = 0.75f; // 12 pixels from origin (12/16 = 0.75)

            // Calculate final fluid surface height
            float fluidHeight = BASE_HEIGHT + fillPercentage * MAX_FILL_HEIGHT - Z_OFFSET;

            RenderUtils.renderFluidQuad(matrices, buffer, sprite, color, fluidHeight, MIN_XZ, MAX_XZ, MIN_XZ, MAX_XZ, light);
        }
    }
}
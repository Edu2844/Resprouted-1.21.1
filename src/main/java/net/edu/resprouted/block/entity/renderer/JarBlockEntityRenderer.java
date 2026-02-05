package net.edu.resprouted.block.entity.renderer;

import net.edu.resprouted.block.custom.decorative.JarBlock;
import net.edu.resprouted.block.entity.custom.decorative.JarBlockEntity;
import net.edu.resprouted.util.render.RenderUtils;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;

@SuppressWarnings("unused")
public class JarBlockEntityRenderer implements BlockEntityRenderer<JarBlockEntity> {
    public JarBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }
    @Override
    public void render(JarBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        FluidVariant fluid = entity.getFluidStorage().getResource();
        long amount = entity.getFluidStorage().getAmount();
        BlockState state = entity.getCachedState();
        if (!state.get(JarBlock.OPEN)) return;
        if (!fluid.isBlank() && amount > 0) {
            // Calculate fluid level based on capacity (This is for the fluid animation)
            float fillPercentage = entity.getFluidHeight(tickDelta);
            // Without fluid animation:
            // float fillPercentage = MathHelper.clamp((float) amount / entity.getFluidStorage().getCapacity(), 0, 1);

            // Get fluid visual properties
            int color = FluidVariantRendering.getColor(fluid, entity.getWorld(), entity.getPos());
            Sprite sprite = RenderUtils.getFluidSprite(fluid);
            RenderLayer renderLayer = RenderLayers.getFluidLayer(fluid.getFluid().getDefaultState());
            VertexConsumer buffer = vertexConsumers.getBuffer(renderLayer);

            final float BASE_HEIGHT = 0.125f; // Minimum fluid height (2 px: 2/16 = 0.125)
            final float MAX_FILL_HEIGHT = 0.4375f; // Maximum fluid height (7 px: 7/16 = 0.4375)
            final float Z_OFFSET = 0.0005f; // Tiny offset to prevent z-fighting
            final float MIN_XZ = 0.25f; // 4 pixels from edge (4/16 = 0.25)
            final float MAX_XZ = 0.6875f; // 12 pixels from origin (11/16 = 0.6875)

            // Calculate final fluid surface height
            float fluidHeight = BASE_HEIGHT + fillPercentage * MAX_FILL_HEIGHT - Z_OFFSET;

            RenderUtils.renderFluidQuad(matrices, buffer, sprite, color, fluidHeight, MIN_XZ, MAX_XZ, MIN_XZ, MAX_XZ, light);
        }
    }
}

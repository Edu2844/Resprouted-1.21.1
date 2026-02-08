package net.edu.resprouted.block.entity.renderer;

import net.edu.resprouted.block.entity.custom.agriculture.DryingBasinBlockEntity;
import net.edu.resprouted.util.render.RenderUtils;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.Sprite;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import java.util.Random;

@SuppressWarnings("unused")
public class DryingBasinBlockEntityRenderer implements BlockEntityRenderer<DryingBasinBlockEntity> {
    public DryingBasinBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }
    @Override
    public void render(DryingBasinBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        // Item Render
        if (!entity.getStack(0).isEmpty()) {
            renderItems(entity, matrices, vertexConsumers);
        }

        // Fluid Render
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
            final float MAX_FILL_HEIGHT = 0.25f; // Maximum fluid height (4 px: 4/16 = 0.25)
            final float Z_OFFSET = 0.0005f; // Tiny offset to prevent z-fighting
            final float MIN_XZ = 0.25f; // 4 pixels from edge (4/16 = 0.25)
            final float MAX_XZ = 0.75f; // 12 pixels from origin (12/16 = 0.75)

            // Calculate final fluid surface height
            float fluidHeight = BASE_HEIGHT + fillPercentage * MAX_FILL_HEIGHT - Z_OFFSET;

            RenderUtils.renderFluidQuad(matrices, buffer, sprite, color, fluidHeight, MIN_XZ, MAX_XZ, MIN_XZ, MAX_XZ, light);
        }
    }

    private void renderItems(DryingBasinBlockEntity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers) {
        ItemStack stack = entity.getStack(0);
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        Random random = new Random(entity.getPos().asLong());
        int light = getLightLevel(entity.getWorld(), entity.getPos());
        int itemCount = stack.getCount();

        final float ITEM_SCALE = 0.55f;
        final float BASE_HEIGHT = 0.1f;
        final float Z_OFFSET = 0.002f; // To prevent z-fighting
        final float LAYER_SPACING = 0.05f; // Vertical distance between item layers
        final int MAX_LAYERS = 16;

        matrices.push();

        // Position items in the center
        matrices.translate(0.5f, BASE_HEIGHT, 0.5f + Z_OFFSET);
        matrices.scale(ITEM_SCALE, ITEM_SCALE, ITEM_SCALE);

        // How many visual layers we need based on stack size
        int renderLayers = Math.min(MAX_LAYERS, (int) Math.ceil(itemCount / 4f));
        float currentHeight = 0;

        // Force cutout render layer so items render before translucent fluids
        BakedModel model = itemRenderer.getModel(stack, entity.getWorld(), null, 0);
        VertexConsumer cutoutBuffer = vertexConsumers.getBuffer(RenderLayer.getCutout());

        for (int layer = 0; layer < renderLayers; layer++) {
            matrices.push();
            matrices.translate(0, currentHeight, 0);

            // Variation after the first layer
            if (layer > 0) {
                float scatterX = (random.nextFloat() - 0.5f) * 0.1f;
                float scatterZ = (random.nextFloat() - 0.5f) * 0.1f;
                float randomRotation = random.nextFloat() * 360;

                matrices.translate(scatterX, 0, scatterZ);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(randomRotation));
            }

            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));

            if (!(stack.getItem() instanceof BlockItem)) {
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(45));
            }

            itemRenderer.renderItem(stack, ModelTransformationMode.GUI, false, matrices,
                    renderlayer -> cutoutBuffer,
                    light,
                    OverlayTexture.DEFAULT_UV,
                    model
            );
            matrices.pop();
            currentHeight += LAYER_SPACING;
        }
        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        if (world == null) return LightmapTextureManager.MAX_LIGHT_COORDINATE;
        return LightmapTextureManager.pack(
                world.getLightLevel(LightType.BLOCK, pos),
                world.getLightLevel(LightType.SKY, pos)
        );
    }
}
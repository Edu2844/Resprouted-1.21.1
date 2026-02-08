package net.edu.resprouted.block.entity.renderer;

import net.edu.resprouted.block.entity.custom.agriculture.CrushingTubBlockEntity;
import net.edu.resprouted.util.misc.ModTags;
import net.edu.resprouted.util.render.RenderUtils;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.block.Block;
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
import net.minecraft.util.math.*;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import java.util.Random;

@SuppressWarnings("unused")
public class CrushingTubBlockEntityRenderer implements BlockEntityRenderer<CrushingTubBlockEntity> {
    public CrushingTubBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }
    @Override
    public void render(CrushingTubBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
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
            final float MAX_FILL_HEIGHT = 0.5f; // Maximum fluid height (8 px: 8/16 = 0.5)
            final float Z_OFFSET = 0.0005f; // Tiny offset to prevent z-fighting
            final float MIN_XZ = 0.125f; // 2 pixels from edge (2/16 = 0.125)
            final float MAX_XZ = 0.875f; // 14 pixels from origin (14/16 = 0.875)

            // Calculate final fluid surface height
            float fluidHeight = BASE_HEIGHT + fillPercentage * MAX_FILL_HEIGHT - Z_OFFSET;

            RenderUtils.renderFluidQuad(matrices, buffer, sprite, color, fluidHeight, MIN_XZ, MAX_XZ, MIN_XZ, MAX_XZ, light);
        }
    }

    private void renderItems(CrushingTubBlockEntity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers) {
        ItemStack stack = entity.getStack(0);
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        Random random = new Random(entity.getPos().asLong());
        int light = getLightLevel(entity.getWorld(), entity.getPos());
        int itemCount = stack.getCount();
        boolean isBlockItem = stack.getItem() instanceof BlockItem;

        // Check if the block should render vertically
        boolean useVerticalRender = false;
        if (isBlockItem) {
            Block block = ((BlockItem) stack.getItem()).getBlock();
            useVerticalRender = block.getDefaultState().isIn(ModTags.Blocks.DIFFERENT_TUB_RENDER);
        }
        matrices.push();

        if (useVerticalRender) {
            final float PIXEL = 0.0625f; // One pixel (1/16 of a block)
            final float SCALE_FACTOR = 0.53f;
            final float BLOCK_SCALE = 0.55f;
            final int ITEMS_PER_LAYER = 16; // Render one block every 16 items

            float startHeight = PIXEL * (2 + (SCALE_FACTOR / 2));
            matrices.translate(0.5f, startHeight, 0.5f);

            // Add some random offset so blocks don't look perfectly aligned
            float initialOffsetX = (random.nextFloat() - 0.5f) / 4;
            float initialOffsetZ = (random.nextFloat() - 0.5f) / 4;
            matrices.translate(initialOffsetX, 0, initialOffsetZ);

            matrices.scale(BLOCK_SCALE, BLOCK_SCALE, BLOCK_SCALE);

            BakedModel model = itemRenderer.getModel(stack, entity.getWorld(), null, 0);

            // Render blocks stacking upward
            for (int i = 0; i < itemCount; i++) {
                if (i % ITEMS_PER_LAYER == 0) {
                    // Things to create a pile
                    float layerOffsetX = (random.nextFloat() - 0.5f) / 4;
                    float layerOffsetZ = (random.nextFloat() - 0.5f) / 4;

                    // Move up by 2 pixels, adjusted with the scale
                    float layerSpacing = (PIXEL * 2) / BLOCK_SCALE;

                    matrices.translate(layerOffsetX, layerSpacing, layerOffsetZ);
                    itemRenderer.renderItem(stack, ModelTransformationMode.NONE, false, matrices,
                            vertexConsumers,
                            light,
                            OverlayTexture.DEFAULT_UV,
                            model
                    );
                }
            }

        } else {
            final float ITEM_SCALE = 0.55f;
            final float BASE_HEIGHT = 0.1f;
            final float Z_OFFSET = 0.002f; // To prevent z-fighting
            final float LAYER_SPACING = 0.05f; // Vertical distance between item layers
            final int MAX_LAYERS = 16;

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
                        renderLayer -> cutoutBuffer,
                        light,
                        OverlayTexture.DEFAULT_UV,
                        model
                );
                matrices.pop();
                currentHeight += LAYER_SPACING;
            }
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
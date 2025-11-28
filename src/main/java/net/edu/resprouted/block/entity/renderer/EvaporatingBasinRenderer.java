package net.edu.resprouted.block.entity.renderer;

import net.edu.resprouted.block.entity.custom.EvaporatingBasinBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory.*;
import net.minecraft.client.util.math.MatrixStack;

public class EvaporatingBasinRenderer extends BaseFluidStorageRenderer<EvaporatingBasinBlockEntity> {
    private static final float MIN = 4f / 16f;
    private static final float MAX = 12f / 16f;
    private static final float BASE_Y = 1f / 16f;
    private static final float MAX_HEIGHT = 5f / 16f;
    private static final float Y_OFFSET = -0.0005f;

    public EvaporatingBasinRenderer(Context ctx) {
        super(ctx);
    }

    @Override
    public void render(EvaporatingBasinBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        //Item Render
        if (!entity.getStack(0).isEmpty()) {
            light = getLightLevel(entity.getWorld(), entity.getPos());

            renderItemStack(entity.getStack(0),
                    entity.getWorld(),
                    entity.getPos(),
                    matrices, vertexConsumers,
                    0.5f, 0.1f, 0.5f + 0.002f,
                    0.55f, light);
        }

        //Fluid Render
        FluidVariant fluid = entity.getFluidStorage().getResource();
        if (fluid.isBlank() || entity.getFluidStorage().getAmount() <= 0) return;

        float fillPercentage = entity.getFluidHeight(tickDelta);
        float y = BASE_Y + fillPercentage * MAX_HEIGHT;

        renderFluidSprite(fluid, entity.getWorld(),
                entity.getPos(),
                matrices, vertexConsumers,
                y + Y_OFFSET, MIN, MAX, MIN, MAX,
                getLightLevel(entity.getWorld(), entity.getPos()), overlay);
    }
}
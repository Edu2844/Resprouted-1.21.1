package net.edu.resprouted.block.entity.renderer;

import net.edu.resprouted.block.entity.custom.LiquidBarrelBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory.*;
import net.minecraft.client.util.math.MatrixStack;

public class LiquidBarrelRenderer extends BaseFluidStorageRenderer<LiquidBarrelBlockEntity> {
    private static final float MIN = 0.03125f;
    private static final float MAX = 0.96875f;
    private static final float HEIGHT_MULTIPLIER = 0.877f; //0.937 full
    private static final float MIN_HEIGHT = 0.0625f;
    private static final float Y_OFFSET = -0.0005f;

    public LiquidBarrelRenderer(Context ctx) {
        super(ctx);
    }

    @Override
    public void render(LiquidBarrelBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        FluidVariant fluid = entity.getFluidStorage().getResource();
        if (fluid.isBlank() || entity.getFluidStorage().getAmount() <= 0) return;

        float fillPercentage = entity.getFluidHeight(tickDelta);
        float fluidHeight = MIN_HEIGHT + fillPercentage * HEIGHT_MULTIPLIER;

        renderFluidSprite(fluid, entity.getWorld(),
                entity.getPos(),
                matrices, vertexConsumers,
                fluidHeight + Y_OFFSET, MIN, MAX, MIN, MAX,
                getLightLevel(entity.getWorld(), entity.getPos()), overlay);
    }
}

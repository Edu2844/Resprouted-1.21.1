package net.edu.resprouted.block.entity.renderer;

import net.edu.resprouted.block.entity.custom.LiquidBarrelBE;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;

public class LiquidBarrelRenderer extends AbstractFluidStorageRenderer<LiquidBarrelBE> {
    public LiquidBarrelRenderer(BlockEntityRendererFactory.Context context) {
        super(context, 0.0625f, 0.937f);
    }

    @Override
    protected void renderCustomContent(LiquidBarrelBE entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

    }
}
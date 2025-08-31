package net.edu.resprouted.entity.client;

import net.edu.resprouted.entity.custom.StoolEntity;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class StoolEntityRenderer extends EntityRenderer<StoolEntity> {
    public StoolEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }
    @Override
    public Identifier getTexture(StoolEntity entity) {
        return null;
    }
    @Override
    public boolean shouldRender(StoolEntity entity, Frustum frustum, double x, double y, double z) {
        return true;
    }
}

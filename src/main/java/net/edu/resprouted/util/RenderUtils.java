package net.edu.resprouted.util;

import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.texture.MissingSprite;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.math.MathHelper;
import org.joml.Matrix4f;

public class RenderUtils {

    public static Sprite getFluidSprite(FluidVariant fluid) {
        Sprite[] sprites = FluidVariantRendering.getSprites(fluid);
        SpriteAtlasTexture atlas = getBlockAtlas();

        if (sprites == null || sprites.length == 0) {
            return atlas.getSprite(MissingSprite.getMissingSpriteId());
        }
        return atlas.getSprite(sprites[0].getContents().getId());
    }

    private static SpriteAtlasTexture getBlockAtlas() {
        return MinecraftClient.getInstance().getBakedModelManager().getAtlas(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE);
    }

    public static void renderFluidQuad(MatrixStack matrices, VertexConsumer buffer, Sprite sprite, int color, float height, float minX, float maxX, float minZ, float maxZ, int light) {
        matrices.push();
        matrices.translate(0, height, 0);
        Matrix4f matrix = matrices.peek().getPositionMatrix();

        float minU = sprite.getMinU();
        float minV = sprite.getMinV();
        float maxU = sprite.getMaxU();
        float maxV = sprite.getMaxV();

        int alpha = (color >> 24) & 0xFF;
        int red = (color >> 16) & 0xFF;
        int green = (color >> 8) & 0xFF;
        int blue = color & 0xFF;

        buffer.vertex(matrix, minX, 0, minZ).color(red, green, blue, alpha).texture(minU, minV).light(light).normal(0, 1, 0);
        buffer.vertex(matrix, minX, 0, maxZ).color(red, green, blue, alpha).texture(minU, maxV).light(light).normal(0, 1, 0);
        buffer.vertex(matrix, maxX, 0, maxZ).color(red, green, blue, alpha).texture(maxU, maxV).light(light).normal(0, 1, 0);
        buffer.vertex(matrix, maxX, 0, minZ).color(red, green, blue, alpha).texture(maxU, minV).light(light).normal(0, 1, 0);

        matrices.pop();
    }

    //Borrowed and modified from 1.21-Tutorial-Mod by DaRealTurtyWurty
    //Source:
    //https://github.com/DaRealTurtyWurty/1.21-Tutorial-Mod/blob/main/src/client/java/dev/turtywurty/tutorialmod/util/ScreenUtils.java

    public static void renderTiledSprite(DrawContext context, Sprite sprite, int x, int y, int width, int height, float red, float green, float blue, float alpha) {
        int spriteWidth = sprite.getContents().getWidth();
        int spriteHeight = sprite.getContents().getHeight();

        int xCount = MathHelper.floor((float) width / spriteWidth);
        int yCount = MathHelper.floor((float) height / spriteHeight);
        int xRemainder = width % spriteWidth;
        int yRemainder = height % spriteHeight;

        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                int x1 = x + (i * spriteWidth);
                int y1 = y + (j * spriteHeight);

                context.drawSprite(x1, y1, 0, spriteWidth, spriteHeight, sprite, red, green, blue, alpha);
            }

            if(yRemainder > 0) {
                int x1 = x + (i * spriteWidth);
                int y1 = y + (yCount * spriteHeight);

                context.drawSprite(x1, y1, 0, spriteWidth, yRemainder, sprite, red, green, blue, alpha);
            }
        }

        if(xRemainder > 0) {
            for (int j = 0; j < yCount; j++) {
                int x1 = x + (xCount * spriteWidth);
                int y1 = y + (j * spriteHeight);

                context.drawSprite(x1, y1, 0, xRemainder, spriteHeight, sprite, red, green, blue, alpha);
            }

            if(yRemainder > 0) {
                int x1 = x + (xCount * spriteWidth);
                int y1 = y + (yCount * spriteHeight);

                context.drawSprite(x1, y1, 0, xRemainder, yRemainder, sprite, red, green, blue, alpha);
            }
        }
    }
}
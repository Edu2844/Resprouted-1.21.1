package net.edu.resprouted.util;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.Sprite;

public class ScreenUtils {
    public static void renderTiledSprite(DrawContext context, Sprite sprite, int x, int y, int width, int height, float red, float green, float blue, float alpha) {
        int spriteWidth = sprite.getContents().getWidth();
        int spriteHeight = sprite.getContents().getHeight();

        int xTiles = width / spriteWidth;
        int yTiles = height / spriteHeight;
        int xRemainder = width % spriteWidth;
        int yRemainder = height % spriteHeight;

        context.setShaderColor(red, green, blue, alpha);

        for (int i = 0; i <= xTiles; i++) {
            int tileWidth = (i == xTiles) ? xRemainder : spriteWidth;
            if (tileWidth <= 0) continue;

            for (int j = 0; j <= yTiles; j++) {
                int tileHeight = (j == yTiles) ? yRemainder : spriteHeight;
                if (tileHeight <= 0) continue;

                int posX = x + i * spriteWidth;
                int posY = y + j * spriteHeight;

                context.drawSprite(posX, posY, 0, tileWidth, tileHeight, sprite
                );
            }
        }
        context.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
    }
}

package net.edu.resprouted.util;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.math.MathHelper;

//Borrowed and modified from 1.21-Tutorial-Mod by DaRealTurtyWurty
//Source:
//https://github.com/DaRealTurtyWurty/1.21-Tutorial-Mod/blob/main/src/client/java/dev/turtywurty/tutorialmod/util/ScreenUtils.java

public class ScreenUtils {
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

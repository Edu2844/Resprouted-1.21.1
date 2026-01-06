package net.edu.resprouted.util;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class PageUtils {
    public static final int PAGE_WIDTH = 112;
    public static final int PAGE_HEIGHT = 150;
    public static final int LEFT_PAGE_X = 18;
    public static final int LEFT_PAGE_Y = 5;
    public static final int RIGHT_PAGE_X = 155;
    public static final int RIGHT_PAGE_Y = 5;

    private final DrawContext context;
    private final TextRenderer textRenderer;
    private final int baseX;
    private final int baseY;

    public PageUtils(DrawContext context, TextRenderer textRenderer, int baseX, int baseY) {
        this.context = context;
        this.textRenderer = textRenderer;
        this.baseX = baseX;
        this.baseY = baseY;
    }

    public static PageUtils createLeftPage(DrawContext context, TextRenderer textRenderer, int bookX, int bookY) {
        return new PageUtils(context, textRenderer, bookX + LEFT_PAGE_X, bookY + LEFT_PAGE_Y);
    }

    public static PageUtils createRightPage(DrawContext context, TextRenderer textRenderer, int bookX, int bookY) {
        return new PageUtils(context, textRenderer, bookX + RIGHT_PAGE_X, bookY + RIGHT_PAGE_Y);
    }

    public void drawTitle(Text text, int relY, int color) {
        int textWidth = textRenderer.getWidth(text);
        int x = baseX + (PAGE_WIDTH + 15 - textWidth) / 2;
        context.drawText(textRenderer, text, x, baseY + relY, color, false);
    }

    public void drawParagraph(Text text, int relY, int color) {
        drawParagraph(text, relY, PAGE_WIDTH, color);
    }

    public void drawParagraph(Text text, int relY, int maxWidth, int color) {
        List<OrderedText> lines = textRenderer.wrapLines(text, maxWidth);
        for (int i = 0; i < lines.size(); i++) {
            context.drawText(textRenderer, lines.get(i), baseX + 8, baseY + relY + (i * textRenderer.fontHeight), color, false);
        }
    }

    public void drawText(Text text, int relX, int relY, int color) {
        context.drawText(textRenderer, text, baseX + relX, baseY + relY, color, false);
    }

    public void drawIcon(Identifier texture, int relX, int relY, int width, int height) {
        context.drawTexture(texture, baseX + relX, baseY + relY, 0, 0, width, height, width, height);
    }

    public void drawItemStackIcon(ItemStack stack, int x, int y) {
        context.drawItem(stack, baseX + x, baseY + y);
    }

    public boolean isMouseOverEntry(double mouseX, double mouseY, int entryY, int width, int height) {
        int entryX = 8;
        return mouseX >= baseX + entryX && mouseX <= baseX + entryX + width && mouseY >= baseY + entryY && mouseY <= baseY + entryY + height;
    }

    public DrawContext getContext() {
        return context;
    }

    public int getBaseX() {
        return baseX;
    }

    public int getBaseY() {
        return baseY;
    }
}

package net.edu.resprouted.book;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.util.PageUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

@Environment(EnvType.CLIENT)
public class CategoryListPage {
    private static final Identifier SEPARATOR = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/separator.png");
    private static final Identifier MENU_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/menu_icon.png");
    private static final Identifier MENU_ICON_HOVER = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/menu_icon_hover.png");

    private static final int COLOR = 0x82755E;
    private static final int SEP_WIDTH = 94;
    private static final int SEP_HEIGHT = 3;
    private static final int MAIN_MENU_X = 93;
    private static final int MAIN_MENU_Y = 165;
    private static final int MAIN_MENU_WIDTH = 22;
    private static final int MAIN_MENU_HEIGHT = 25;
    private static final int MAIN_MENU_HOVER_WIDTH = 22;
    private static final int MAIN_MENU_HOVER_HEIGHT = 35;

    private final CatalogScreen screen;

    public CategoryListPage(CatalogScreen screen) {
        this.screen = screen;
    }

    public void render(PageUtils leftPage, PageUtils rightPage, int mouseX, int mouseY) {
        CatalogData.Category selectedCategory = screen.getSelectedCategory();
        if (selectedCategory == null) return;

        leftPage.drawTitle(Text.translatable("text.category.resprouted." + selectedCategory.getName()).formatted(Formatting.BOLD), 10, COLOR);
        leftPage.drawIcon(SEPARATOR, 12, 22, SEP_WIDTH, SEP_HEIGHT);

        List<CatalogData.Entry> entries = selectedCategory.getEntries();
        int startY = 30;
        int spacing = 17;
        int startYr = 10;
        int entryWidth = PageUtils.PAGE_WIDTH;
        int entryHeight = 16;

        int leftIndex = 0;
        int rightIndex = 0;

        for (CatalogData.Entry entry : entries) {
            int y;
            PageUtils target;

            if (entry.getSide() == CatalogData.Entry.Side.LEFT) {
                y = startY + leftIndex * spacing;
                leftIndex++;
                target = leftPage;
            } else {
                y = startYr + rightIndex * spacing;
                rightIndex++;
                target = rightPage;
            }

            if (target.isMouseOverEntry(mouseX, mouseY, y, entryWidth, entryHeight)) {
                drawEntryHoverBackground(target, y, entryWidth, entryHeight);
            }

            target.drawItemStackIcon(entry.getIcon(), 10, y);
            target.drawText((entry.getName()), 30, y + 4, COLOR);
        }

        renderMainMenuButton(leftPage.getContext(), mouseX, mouseY);
    }

    private void renderMainMenuButton(DrawContext context, int mouseX, int mouseY) {
        int bookX = screen.getBookX();
        int bookY = screen.getBookY();

        drawButtonWithTooltip(context, MENU_ICON, bookX + MAIN_MENU_X, bookY + MAIN_MENU_Y, MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT,
                Text.translatable("text.resprouted.back_menu"),
                mouseX,
                mouseY
        );
    }

    private void drawButtonWithTooltip(DrawContext context, Identifier texture, int x, int y, int width, int height, Text tooltip, int mouseX, int mouseY) {
        boolean isHovered = screen.isMouseOver(mouseX, mouseY, x, y, width, height);

        if (texture == MENU_ICON && isHovered) {
            texture = MENU_ICON_HOVER;
            width = MAIN_MENU_HOVER_WIDTH;
            height = MAIN_MENU_HOVER_HEIGHT;
        }

        context.drawTexture(texture, x, y, 0, 0, width, height, width, height);

        if (isHovered) {
            context.drawTooltip(screen.getTextRenderer(), tooltip, mouseX, mouseY);
        }
    }

    private void drawEntryHoverBackground(PageUtils page, int y, int width, int height) {
        int x = 8;
        int backgroundColor = 0x80E2DDCC;

        DrawContext context = page.getContext();
        context.fill(page.getBaseX() + x, page.getBaseY() + y, page.getBaseX() + x + width, page.getBaseY() + y + height, backgroundColor);
    }

    public boolean handleClick(double mouseX, double mouseY) {
        CatalogData.Category selectedCategory = screen.getSelectedCategory();
        if (selectedCategory == null) return false;

        //Entry clicks
        if (handleEntryClicks(mouseX, mouseY)) return true;

        //Menu button
        int bookX = screen.getBookX();
        int bookY = screen.getBookY();
        if (screen.isMouseOver(mouseX, mouseY, bookX + MAIN_MENU_X, bookY + MAIN_MENU_Y, MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT)) {
            screen.setCurrentState(CatalogScreen.PageState.MAIN_MENU);
            screen.setSelectedCategory(null);
            screen.playPageTurnSound();
            return true;
        }

        return false;
    }

    private boolean handleEntryClicks(double mouseX, double mouseY) {
        CatalogData.Category selectedCategory = screen.getSelectedCategory();
        List<CatalogData.Entry> entries = selectedCategory.getEntries();
        int startY = 30;
        int spacing = 17;
        int startYr = 10;
        int entryWidth = PageUtils.PAGE_WIDTH;
        int entryHeight = 16;

        int leftIndex = 0;
        int rightIndex = 0;

        int bookX = screen.getBookX();
        int bookY = screen.getBookY();

        for (CatalogData.Entry entry : entries) {
            int y;
            int pageX, pageY;

            if (entry.getSide() == CatalogData.Entry.Side.LEFT) {
                y = startY + leftIndex * spacing;
                leftIndex++;
                pageX = bookX + PageUtils.LEFT_PAGE_X + 8;
                pageY = bookY + PageUtils.LEFT_PAGE_Y + y;
            } else {
                y = startYr + rightIndex * spacing;
                rightIndex++;
                pageX = bookX + PageUtils.RIGHT_PAGE_X + 8;
                pageY = bookY + PageUtils.RIGHT_PAGE_Y + y;
            }

            if (screen.isMouseOver(mouseX, mouseY, pageX, pageY, entryWidth, entryHeight)) {
                if (screen.getSelectedEntry() != entry || screen.getCurrentState() != CatalogScreen.PageState.ENTRY_PAGES) {
                    screen.setSelectedEntry(entry);
                    screen.setCurrentPageIndex(0);
                    screen.setCurrentState(CatalogScreen.PageState.ENTRY_PAGES);
                    screen.playPageTurnSound();
                }
                return true;
            }
        }

        return false;
    }
}
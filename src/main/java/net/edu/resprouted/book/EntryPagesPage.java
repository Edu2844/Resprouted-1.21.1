package net.edu.resprouted.book;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.util.PageUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

@Environment(EnvType.CLIENT)
public class EntryPagesPage {
    private static final Identifier SEPARATOR = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/separator.png");
    private static final Identifier MENU_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/menu_icon.png");
    private static final Identifier MENU_ICON_HOVER = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/menu_icon_hover.png");
    private static final Identifier CATEGORY_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/category_back.png");
    private static final Identifier PREV_BUTTON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/previous_page.png");
    private static final Identifier NEXT_BUTTON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/next_page.png");

    private static final int COLOR = 0x82755E;
    private static final int SEP_WIDTH = 94;
    private static final int SEP_HEIGHT = 3;
    private static final int PREV_BUTTON_X = 10;
    private static final int PREV_BUTTON_Y = 148;
    private static final int PREV_BUTTON_WIDTH = 13;
    private static final int PREV_BUTTON_HEIGHT = 15;
    private static final int NEXT_BUTTON_X = 268;
    private static final int NEXT_BUTTON_Y = 148;
    private static final int NEXT_BUTTON_WIDTH = 13;
    private static final int NEXT_BUTTON_HEIGHT = 15;
    private static final int MAIN_MENU_X = 93;
    private static final int MAIN_MENU_Y = 165;
    private static final int MAIN_MENU_WIDTH = 22;
    private static final int MAIN_MENU_HEIGHT = 25;
    private static final int MAIN_MENU_HOVER_WIDTH = 22;
    private static final int MAIN_MENU_HOVER_HEIGHT = 35;
    private static final int BACK_CATEGORY_X = 9;
    private static final int BACK_CATEGORY_Y = 5;
    private static final int BACK_CATEGORY_WIDTH = 13;
    private static final int BACK_CATEGORY_HEIGHT = 15;

    private final CatalogScreen screen;

    public EntryPagesPage(CatalogScreen screen) {
        this.screen = screen;
    }

    public void render(PageUtils leftPage, PageUtils rightPage, int mouseX, int mouseY) {
        CatalogData.Entry selectedEntry = screen.getSelectedEntry();
        if (selectedEntry == null) return;

        List<CatalogData.Page> pages = selectedEntry.getPages();
        int currentPageIndex = screen.getCurrentPageIndex();
        if (pages.isEmpty() || currentPageIndex >= pages.size()) return;

        CatalogData.Page page = pages.get(currentPageIndex);

        leftPage.drawTitle((selectedEntry.getName()), 10, COLOR);
        leftPage.drawIcon(SEPARATOR, 12, 22, SEP_WIDTH, SEP_HEIGHT);

        if (page instanceof CatalogData.TextPage textPage) {
            leftPage.drawParagraph(textPage.getLeftText(), 30, COLOR);

            if (textPage.getRightText() != null) {
                rightPage.drawParagraph(textPage.getRightText(), 10, COLOR);
            }
        }

        //Render navigation buttons
        renderEntryNavigationButtons(leftPage.getContext(), mouseX, mouseY);
    }

    private void renderEntryNavigationButtons(DrawContext context, int mouseX, int mouseY) {
        CatalogData.Entry selectedEntry = screen.getSelectedEntry();
        if (selectedEntry == null) return;

        List<CatalogData.Page> pages = selectedEntry.getPages();
        int currentPageIndex = screen.getCurrentPageIndex();
        int bookX = screen.getBookX();
        int bookY = screen.getBookY();

        //Prev button
        if (currentPageIndex > 0) {
            drawButtonWithTooltip(
                    context,
                    PREV_BUTTON,
                    bookX + PREV_BUTTON_X,
                    bookY + PREV_BUTTON_Y,
                    PREV_BUTTON_WIDTH,
                    PREV_BUTTON_HEIGHT,
                    Text.translatable("text.resprouted.previous_page"),
                    mouseX,
                    mouseY
            );
        }

        //Next button
        if (currentPageIndex < pages.size() - 1) {
            drawButtonWithTooltip(
                    context,
                    NEXT_BUTTON,
                    bookX + NEXT_BUTTON_X,
                    bookY + NEXT_BUTTON_Y,
                    NEXT_BUTTON_WIDTH,
                    NEXT_BUTTON_HEIGHT,
                    Text.translatable("text.resprouted.next_page"),
                    mouseX,
                    mouseY
            );
        }

        //Back to category button
        drawButtonWithTooltip(
                context,
                CATEGORY_ICON,
                bookX + BACK_CATEGORY_X,
                bookY + BACK_CATEGORY_Y,
                BACK_CATEGORY_WIDTH,
                BACK_CATEGORY_HEIGHT,
                Text.translatable("text.resprouted.back_category"),
                mouseX,
                mouseY
        );

        //Menu button
        drawButtonWithTooltip(
                context,
                MENU_ICON,
                bookX + MAIN_MENU_X,
                bookY + MAIN_MENU_Y,
                MAIN_MENU_WIDTH,
                MAIN_MENU_HEIGHT,
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

    public boolean handleClick(double mouseX, double mouseY) {
        CatalogData.Entry selectedEntry = screen.getSelectedEntry();
        if (selectedEntry == null) return false;

        List<CatalogData.Page> pages = selectedEntry.getPages();
        int currentPageIndex = screen.getCurrentPageIndex();
        int bookX = screen.getBookX();
        int bookY = screen.getBookY();

        //Prev page
        if (screen.isMouseOver(mouseX, mouseY, bookX + PREV_BUTTON_X, bookY + PREV_BUTTON_Y,
                PREV_BUTTON_WIDTH, PREV_BUTTON_HEIGHT) && currentPageIndex > 0) {
            screen.setCurrentPageIndex(currentPageIndex - 1);
            screen.playPageTurnSound();
            return true;
        }

        //Next page
        if (screen.isMouseOver(mouseX, mouseY, bookX + NEXT_BUTTON_X, bookY + NEXT_BUTTON_Y,
                NEXT_BUTTON_WIDTH, NEXT_BUTTON_HEIGHT) && currentPageIndex < pages.size() - 1) {
            screen.setCurrentPageIndex(currentPageIndex + 1);
            screen.playPageTurnSound();
            return true;
        }

        //Back to category
        if (screen.isMouseOver(mouseX, mouseY, bookX + BACK_CATEGORY_X, bookY + BACK_CATEGORY_Y,
                BACK_CATEGORY_WIDTH, BACK_CATEGORY_HEIGHT)) {
            screen.setCurrentState(CatalogScreen.PageState.CATEGORY_LIST);
            screen.setSelectedEntry(null);
            screen.playPageTurnSound();
            return true;
        }

        //Menu
        if (screen.isMouseOver(mouseX, mouseY, bookX + MAIN_MENU_X, bookY + MAIN_MENU_Y,
                MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT)) {
            screen.setCurrentState(CatalogScreen.PageState.MAIN_MENU);
            screen.setSelectedEntry(null);
            screen.setSelectedCategory(null);
            screen.playPageTurnSound();
            return true;
        }

        return false;
    }
}

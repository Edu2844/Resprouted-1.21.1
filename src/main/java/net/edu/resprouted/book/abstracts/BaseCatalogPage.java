package net.edu.resprouted.book.abstracts;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.book.CatalogScreen;
import net.edu.resprouted.book.widget.CatalogButtonWidget;
import net.edu.resprouted.util.misc.PageUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public abstract class BaseCatalogPage {
    protected static final Identifier SEPARATOR = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/separator.png");
    protected static final Identifier MENU_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/menu_icon.png");
    protected static final Identifier MENU_ICON_HOVER = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/menu_icon_hover.png");

    protected static final int COLOR = 0x82755E;
    protected static final int SEP_WIDTH = 94;
    protected static final int SEP_HEIGHT = 3;

    protected final CatalogScreen screen;
    protected final List<ClickableWidget> widgets = new ArrayList<>();

    public BaseCatalogPage(CatalogScreen screen) {
        this.screen = screen;
    }

    /**
     * Called when the page becomes active. Override to add widgets.
     */
    public void init() {
        widgets.clear();
    }

    /**
     * Render the page content
     */
    public abstract void render(PageUtils leftPage, PageUtils rightPage, int mouseX, int mouseY);

    /**
     * Handle click events. Returns true if click was handled.
     */
    public abstract boolean handleClick(double mouseX, double mouseY);

    /**
     * Get all widgets for this page
     */
    public List<ClickableWidget> getWidgets() {
        return widgets;
    }

    /**
     * Create and add a menu button widget
     */
    protected CatalogButtonWidget createMenuButton(int bookX, int bookY) {
        return CatalogButtonWidget
                .builder(MENU_ICON, button -> {
                    screen.setCurrentState(CatalogScreen.PageState.MAIN_MENU);
                    screen.setSelectedEntry(null);
                    screen.setSelectedCategory(null);
                    screen.playPageTurnSound();
                })
                .position(bookX + 93, bookY + 165)
                .size(22, 25)
                .textureSize(22, 25)
                .hoverTexture(MENU_ICON_HOVER, 22, 35)
                .tooltip(Text.translatable("text.resprouted.back_menu"))
                .build();
    }

    /**
     * Create navigation button (prev/next)
     */
    protected CatalogButtonWidget createNavigationButton(Identifier texture, int x, int y, int width, int height, Text tooltip, ButtonWidget.PressAction action) {
        return CatalogButtonWidget
                .builder(texture, action)
                .position(x, y)
                .size(width, height)
                .tooltip(tooltip)
                .build();
    }

    /**
     * Draw entry hover background
     */
    protected void drawEntryHoverBackground(PageUtils page, int y, int width, int height) {
        int x = 8;
        int backgroundColor = 0x80E2DDCC;
        DrawContext context = page.getContext();
        context.fill(
                page.getBaseX() + x,
                page.getBaseY() + y,
                page.getBaseX() + x + width,
                page.getBaseY() + y + height,
                backgroundColor
        );
    }
}
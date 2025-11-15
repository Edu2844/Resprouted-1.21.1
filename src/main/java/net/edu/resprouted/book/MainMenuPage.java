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
public class MainMenuPage {
    private static final Identifier MENU_TITLE = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/menu_title.png");
    private static final Identifier INDEX_TITLE = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/index_title.png");
    private static final Identifier AGRICULTURE_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/agriculture.png");
    private static final Identifier DECORATION_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/decoration.png");
    private static final Identifier PRODUCTION_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/production.png");

    private static final int COLOR = 0x82755E;
    private static final int ANIMATION_FRAMES = 8;
    private static final int FRAME_HEIGHT = 64;
    private static final int FRAME_WIDTH = 64;
    private static final int FRAME_DURATION = 25;

    private int currentHoveredIcon = -1;
    private long hoverStartTime = -1;

    private final CatalogScreen screen;

    public MainMenuPage(CatalogScreen screen) {
        this.screen = screen;
    }

    public void render(PageUtils leftPage, PageUtils rightPage, int mouseX, int mouseY) {
        leftPage.drawIcon(MENU_TITLE, 0, 11, 120, 20);
        leftPage.drawParagraph(Text.translatable("text.resprouted.main"), 40, COLOR);
        rightPage.drawIcon(INDEX_TITLE, 15, 11, 91, 20);

        int[] iconXs = {43, 43, 43};
        int[] iconYs = {35, 73, 110};
        int displaySize = 32;

        List<CatalogData.Category> categories = CatalogData.CATEGORIES;
        int rightPageX = screen.getBookX() + PageUtils.RIGHT_PAGE_X;
        int rightPageY = screen.getBookY() + PageUtils.RIGHT_PAGE_Y;

        //Detect Hover
        int hoveredIcon = -1;
        for (int i = 0; i < categories.size(); i++) {
            int x = iconXs[i];
            int y = iconYs[i];
            if (screen.isMouseOver(mouseX, mouseY, rightPageX + x, rightPageY + y, displaySize, displaySize)) {
                hoveredIcon = i;
                break;
            }
        }

        //Update hover and anim
        if (hoveredIcon != currentHoveredIcon) {
            currentHoveredIcon = hoveredIcon;
            if (hoveredIcon != -1) {
                hoverStartTime = System.currentTimeMillis();
            }
        }

        //Render icons
        for (int i = 0; i < categories.size(); i++) {
            CatalogData.Category category = categories.get(i);
            int x = iconXs[i];
            int y = iconYs[i];

            Identifier iconTexture = switch (category.getName()) {
                case "agriculture" -> AGRICULTURE_ICON;
                case "decoration" -> DECORATION_ICON;
                case "production" -> PRODUCTION_ICON;
                default -> null;
            };

            if (iconTexture != null) {
                if (i == currentHoveredIcon && hoverStartTime != -1) {
                    long elapsed = System.currentTimeMillis() - hoverStartTime;
                    int frame = (int) (elapsed / FRAME_DURATION);

                    if (frame >= ANIMATION_FRAMES - 1) {
                        frame = ANIMATION_FRAMES - 1;
                    }

                    renderAnimatedIcon(rightPage, iconTexture, x, y, displaySize, displaySize, frame);
                } else {
                    renderAnimatedIcon(rightPage, iconTexture, x, y, displaySize, displaySize, 0);
                }

                if (i == hoveredIcon) {
                    rightPage.getContext().drawTooltip(screen.getTextRenderer(),
                            Text.translatable("text.category.resprouted." + category.getName()), mouseX, mouseY);
                }
            }
        }
    }

    private void renderAnimatedIcon(PageUtils page, Identifier texture, int x, int y, int width, int height, int frame) {
        DrawContext context = page.getContext();
        int baseX = page.getBaseX();
        int baseY = page.getBaseY();

        context.drawTexture(texture,
                baseX + x, baseY + y,
                width, height,
                0, frame * FRAME_HEIGHT,
                FRAME_WIDTH, FRAME_HEIGHT,
                FRAME_WIDTH, FRAME_HEIGHT * ANIMATION_FRAMES);
    }

    public boolean handleClick(double mouseX, double mouseY) {
        int rightPageX = screen.getBookX() + PageUtils.RIGHT_PAGE_X;
        int rightPageY = screen.getBookY() + PageUtils.RIGHT_PAGE_Y;

        int[] iconXs = {43, 43, 43};
        int[] iconYs = {35, 73, 110};
        int iconSize = 32;
        List<CatalogData.Category> categories = CatalogData.CATEGORIES;

        for (int i = 0; i < categories.size(); i++) {
            int x = iconXs[i];
            int y = iconYs[i];

            if (screen.isMouseOver(mouseX, mouseY, rightPageX + x, rightPageY + y, iconSize, iconSize)) {
                CatalogData.Category category = categories.get(i);
                if (screen.getSelectedCategory() != category || screen.getCurrentState() != CatalogScreen.PageState.CATEGORY_LIST) {
                    screen.setSelectedCategory(category);
                    screen.setCurrentState(CatalogScreen.PageState.CATEGORY_LIST);
                    screen.playPageTurnSound();
                }
                return true;
            }
        }
        return false;
    }
}
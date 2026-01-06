package net.edu.resprouted.book.pages;


import net.edu.resprouted.Resprouted;
import net.edu.resprouted.book.CatalogData;
import net.edu.resprouted.book.CatalogScreen;
import net.edu.resprouted.book.abstracts.BaseCatalogPage;
import net.edu.resprouted.book.widget.AnimatedIconWidget;
import net.edu.resprouted.util.PageUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

@Environment(EnvType.CLIENT)
public class MainMenuPage extends BaseCatalogPage {
    private static final Identifier MENU_TITLE = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/menu_title.png");
    private static final Identifier INDEX_TITLE = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/index_title.png");
    private static final Identifier AGRICULTURE_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/agriculture.png");
    private static final Identifier DECORATION_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/decoration.png");
    private static final Identifier PRODUCTION_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/production.png");

    private static final int[] ICON_XS = {43, 43, 43};
    private static final int[] ICON_YS = {35, 73, 110};
    private static final int ICON_SIZE = 32;

    public MainMenuPage(CatalogScreen screen) {
        super(screen);
    }

    @Override
    public void init() {
        super.init();

        List<CatalogData.Category> categories = CatalogData.CATEGORIES;
        int rightPageX = screen.getBookX() + PageUtils.RIGHT_PAGE_X;
        int rightPageY = screen.getBookY() + PageUtils.RIGHT_PAGE_Y;

        // Create animated icon widgets for each category
        for (int i = 0; i < categories.size(); i++) {
            CatalogData.Category category = categories.get(i);
            Identifier iconTexture = getCategoryIcon(category.getName());

            if (iconTexture != null) {
                final int index = i;
                AnimatedIconWidget iconWidget = AnimatedIconWidget
                        .builder(iconTexture, button -> selectCategory(index))
                        .position(rightPageX + ICON_XS[i], rightPageY + ICON_YS[i])
                        .displaySize(ICON_SIZE)
                        .animation(8, 64, 64, 25)
                        .tooltip(Text.translatable("text.category.resprouted." + category.getName()))
                        .build();

                widgets.add(iconWidget);
            }
        }
    }

    @Override
    public void render(PageUtils leftPage, PageUtils rightPage, int mouseX, int mouseY) {
        leftPage.drawIcon(MENU_TITLE, 0, 11, 120, 20);
        leftPage.drawParagraph(Text.translatable("text.resprouted.main"), 40, COLOR);
        rightPage.drawIcon(INDEX_TITLE, 15, 11, 91, 20);
    }

    @Override
    public boolean handleClick(double mouseX, double mouseY) {
        return false;
    }

    private void selectCategory(int index) {
        List<CatalogData.Category> categories = CatalogData.CATEGORIES;
        if (index >= 0 && index < categories.size()) {
            CatalogData.Category category = categories.get(index);
            if (screen.getSelectedCategory() != category || screen.getCurrentState() != CatalogScreen.PageState.CATEGORY_LIST) {
                screen.setSelectedCategory(category);
                screen.setCurrentState(CatalogScreen.PageState.CATEGORY_LIST);
                screen.playPageTurnSound();
            }
        }
    }

    private Identifier getCategoryIcon(String categoryName) {
        return switch (categoryName) {
            case "agriculture" -> AGRICULTURE_ICON;
            case "decoration" -> DECORATION_ICON;
            case "production" -> PRODUCTION_ICON;
            default -> null;
        };
    }
}
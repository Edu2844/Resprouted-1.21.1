package net.edu.resprouted.book;

import com.mojang.blaze3d.systems.RenderSystem;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.util.PageUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class CatalogScreen extends Screen {

    private static final Identifier BACKGROUND = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/book.png");
    private static final Identifier SEPARATOR = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/separator.png");
    private static final Identifier AGRICULTURE_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/agriculture.png");
    private static final Identifier DECORATION_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/decoration.png");
    private static final Identifier PRODUCTION_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/production.png");
    private static final Identifier MENU_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/menu_icon.png");
    private static final Identifier CATEGORY_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/category_back.png");
    private static final Identifier PREV_BUTTON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/previous_page.png");
    private static final Identifier NEXT_BUTTON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/next_page.png");

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
    private static final int MAIN_MENU_HEIGHT = 31;

    private static final int BACK_CATEGORY_X = 285;
    private static final int BACK_CATEGORY_Y = 14;
    private static final int BACK_CATEGORY_WIDTH = 30;
    private static final int BACK_CATEGORY_HEIGHT = 24;

    private static final int BOOK_WIDTH = 293;
    private static final int BOOK_HEIGHT = 184;
    private static final int TEXTURE_SIZE = 512;

    private static final int COLOR = 0x4E3A2E;

    private enum PageState { MAIN_MENU, CATEGORY_LIST, ENTRY_PAGES }
    private PageState currentState = PageState.MAIN_MENU;
    private CatalogData.Category selectedCategory;
    private CatalogData.Entry selectedEntry;
    private int currentPageIndex = 0;

    public CatalogScreen() {
        super(Text.empty());
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        super.renderInGameBackground(context);

        int bookX = (this.width - BOOK_WIDTH) / 2;
        int bookY = (this.height - BOOK_HEIGHT) / 2;

        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, BACKGROUND);
        context.drawTexture(BACKGROUND, bookX, bookY, 0, 0, BOOK_WIDTH, BOOK_HEIGHT, TEXTURE_SIZE, TEXTURE_SIZE);

        PageUtils leftPage = PageUtils.createLeftPage(context, textRenderer, bookX, bookY);
        PageUtils rightPage = PageUtils.createRightPage(context, textRenderer, bookX, bookY);

        switch (currentState) {
            case MAIN_MENU -> renderMainMenu(leftPage, rightPage);
            case CATEGORY_LIST -> renderCategoryList(leftPage, rightPage);
            case ENTRY_PAGES -> renderEntryPages(leftPage, rightPage);
        }
        if (currentState == PageState.CATEGORY_LIST) {
            context.drawTexture(MENU_ICON, bookX + MAIN_MENU_X, bookY + MAIN_MENU_Y,
                    0, 0, MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT,
                    MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
        }
        if (currentState == PageState.ENTRY_PAGES && selectedEntry != null) {
            List<CatalogData.Page> pages = selectedEntry.getPages();

            // Botón Prev
            if (currentPageIndex > 0) {
                context.drawTexture(PREV_BUTTON, bookX + PREV_BUTTON_X, bookY + PREV_BUTTON_Y,
                        0, 0, PREV_BUTTON_WIDTH, PREV_BUTTON_HEIGHT,
                        PREV_BUTTON_WIDTH, PREV_BUTTON_HEIGHT);
            }

            // Botón Next
            if (currentPageIndex < pages.size() - 1) {
                context.drawTexture(NEXT_BUTTON, bookX + NEXT_BUTTON_X, bookY + NEXT_BUTTON_Y,
                        0, 0, NEXT_BUTTON_WIDTH, NEXT_BUTTON_HEIGHT,
                        NEXT_BUTTON_WIDTH, NEXT_BUTTON_HEIGHT);
            }

            // Back to Category
            context.drawTexture(CATEGORY_ICON, bookX + BACK_CATEGORY_X, bookY + BACK_CATEGORY_Y,
                    0, 0, BACK_CATEGORY_WIDTH, BACK_CATEGORY_HEIGHT,
                    BACK_CATEGORY_WIDTH, BACK_CATEGORY_HEIGHT);

            // Main Menu
            context.drawTexture(MENU_ICON, bookX + MAIN_MENU_X, bookY + MAIN_MENU_Y,
                    0, 0, MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT,
                    MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int bookX = (this.width - BOOK_WIDTH) / 2;
        int bookY = (this.height - BOOK_HEIGHT) / 2;
        int rightPageX = bookX + PageUtils.RIGHT_PAGE_X;
        int rightPageY = bookY + PageUtils.RIGHT_PAGE_Y;

        if (currentState == PageState.MAIN_MENU) {
            int[] iconXs = {10, 10, 10};
            int[] iconYs = {35, 73, 110};
            int iconSize = 32;
            List<CatalogData.Category> categories = CatalogData.CATEGORIES;

            for (int i = 0; i < categories.size(); i++) {
                int x = iconXs[i];
                int y = iconYs[i];

                if (isMouseOver(mouseX, mouseY, rightPageX + x, rightPageY + y, iconSize, iconSize)) {
                    if (selectedCategory != categories.get(i) || currentState != PageState.CATEGORY_LIST) {
                        selectedCategory = categories.get(i);
                        currentState = PageState.CATEGORY_LIST;
                        playPageClickSound();
                    }
                    return true;
                }
            }
        }
        // CATEGORY LIST
        if (currentState == PageState.CATEGORY_LIST && selectedCategory != null) {
            List<CatalogData.Entry> entries = selectedCategory.getEntries();
            int startY = 30, spacing = 17, iconSize = 16, startYr = 10;

            int leftIndex = 0;
            int rightIndex = 0;

            for (CatalogData.Entry entry : entries) {
                int y;
                int pageX, pageY;

                if (entry.getSide() == CatalogData.Entry.Side.LEFT) {
                    y = startY + leftIndex * spacing;
                    leftIndex++;
                    pageX = bookX + PageUtils.LEFT_PAGE_X;
                    pageY = bookY + PageUtils.LEFT_PAGE_Y;
                } else {
                    y = startYr + rightIndex * spacing;
                    rightIndex++;
                    pageX = bookX + PageUtils.RIGHT_PAGE_X;
                    pageY = bookY + PageUtils.RIGHT_PAGE_Y;
                }

                if (isMouseOver(mouseX, mouseY, pageX + 10, pageY + y, iconSize, iconSize)) {
                    if (selectedEntry != entry || currentState != PageState.ENTRY_PAGES) {
                        selectedEntry = entry;
                        currentPageIndex = 0;
                        currentState = PageState.ENTRY_PAGES;
                        playPageClickSound();
                    }
                    return true;
                }
            }

            if (isMouseOver(mouseX, mouseY, bookX + MAIN_MENU_X, bookY + MAIN_MENU_Y,
                    MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT)) {
                currentState = PageState.MAIN_MENU;
                selectedCategory = null;
                playPageClickSound();
                return true;
            }
        }

        if (currentState == PageState.ENTRY_PAGES && selectedEntry != null) {

            // Prev
            if (isMouseOver(mouseX, mouseY, bookX + PREV_BUTTON_X, bookY + PREV_BUTTON_Y,
                    PREV_BUTTON_WIDTH, PREV_BUTTON_HEIGHT)) {
                if (currentPageIndex > 0) {
                    currentPageIndex--;
                    playPageTurnSound();
                }
                return true;
            }

            // Next
            if (isMouseOver(mouseX, mouseY, bookX + NEXT_BUTTON_X, bookY + NEXT_BUTTON_Y,
                    NEXT_BUTTON_WIDTH, NEXT_BUTTON_HEIGHT)) {
                if (currentPageIndex < selectedEntry.getPages().size() - 1) {
                    currentPageIndex++;
                    playPageTurnSound();
                }
                return true;
            }

            // Back to Category
            if (isMouseOver(mouseX, mouseY, bookX + BACK_CATEGORY_X, bookY + BACK_CATEGORY_Y,
                    BACK_CATEGORY_WIDTH, BACK_CATEGORY_HEIGHT)) {
                currentState = PageState.CATEGORY_LIST;
                selectedEntry = null;
                playPageTurnSound();
                return true;
            }

            // Main Menu
            if (isMouseOver(mouseX, mouseY, bookX + MAIN_MENU_X, bookY + MAIN_MENU_Y,
                    MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT)) {
                currentState = PageState.MAIN_MENU;
                selectedEntry = null;
                selectedCategory = null;
                playPageClickSound();
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
    private void playPageTurnSound() {
        if (this.client != null && this.client.getSoundManager() != null) {
            this.client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F));
        }
    }
    private void playPageClickSound() {
        if (this.client != null && this.client.getSoundManager() != null) {
            this.client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
        }
    }

    private boolean isMouseOver(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    private void renderMainMenu(PageUtils leftPage, PageUtils rightPage) {
        leftPage.drawTitle(Text.translatable("item.resprouted.catalog"), 10, COLOR);
        leftPage.drawIcon(SEPARATOR,12,22,94,5);
        leftPage.drawParagraph(Text.translatable("text.resprouted.main"), 40, COLOR);
        rightPage.drawTitle(Text.translatable("text.resprouted.categories"), 10, COLOR);
        rightPage.drawIcon(SEPARATOR,12,22,94,5);


        int[] iconXs = {10, 10, 10};
        int[] iconYs = {35, 73, 110};
        int iconSize = 32;

        List<CatalogData.Category> categories = CatalogData.CATEGORIES;

        for (int i = 0; i < categories.size(); i++) {
            CatalogData.Category category = categories.get(i);

            Identifier iconTexture = switch (category.getName()) {
                case "agriculture" -> AGRICULTURE_ICON;
                case "decoration" -> DECORATION_ICON;
                case "production" -> PRODUCTION_ICON;
                default -> null;
            };

            int x = iconXs[i];
            int y = iconYs[i];

            rightPage.drawIcon(iconTexture, x, y, iconSize, iconSize);
            rightPage.drawText(Text.translatable("text.category.resprouted." + category.getName()),
                    50, y + 12, COLOR);
        }
    }

    private void renderCategoryList(PageUtils leftPage, PageUtils rightPage) {
        if (selectedCategory == null) return;

        leftPage.drawTitle(Text.translatable("text.category.resprouted." + selectedCategory.getName()).formatted(Formatting.BOLD), 10, COLOR);
        leftPage.drawIcon(SEPARATOR,12,22,94,5);

        List<CatalogData.Entry> entries = selectedCategory.getEntries();
        int startY = 30, spacing = 17, startYr = 10;

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

            target.drawItemStackIcon(entry.getIcon(), 10, y);
            target.drawText((entry.getName()), 30, y + 4, COLOR);
        }
    }

    private void renderEntryPages(PageUtils leftPage, PageUtils rightPage) {
        if (selectedEntry == null) return;

        List<CatalogData.Page> pages = selectedEntry.getPages();
        if (pages.isEmpty()) return;

        CatalogData.Page page = pages.get(currentPageIndex);

        leftPage.drawTitle((selectedEntry.getName()), 10, COLOR);
        leftPage.drawIcon(SEPARATOR,12,22,94,5);

        if (page instanceof CatalogData.TextPage textPage) {
            leftPage.drawParagraph(textPage.getLeftText(), 40, COLOR);

            if (textPage.getRightText() != null) {
                rightPage.drawParagraph(textPage.getRightText(), 10, COLOR);
            }
        }
    }
}

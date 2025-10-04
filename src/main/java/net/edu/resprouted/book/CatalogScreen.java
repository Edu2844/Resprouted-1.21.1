package net.edu.resprouted.book;

import com.mojang.blaze3d.systems.RenderSystem;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.util.PageUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.List;

@Environment(EnvType.CLIENT)
public class CatalogScreen extends Screen {
    private static final Identifier BACKGROUND = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/book.png");
    private static final Identifier SEPARATOR = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/separator.png");
    private static final Identifier AGRICULTURE_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/agriculture.png");
    private static final Identifier DECORATION_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/decoration.png");
    private static final Identifier PRODUCTION_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/production.png");
    private static final Identifier MENU_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/menu_icon.png");
    private static final Identifier MENU_TITLE = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/menu_title.png");
    private static final Identifier INDEX_TITLE = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/index_title.png");
    private static final Identifier CATEGORY_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/category_back.png");
    private static final Identifier PREV_BUTTON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/previous_page.png");
    private static final Identifier NEXT_BUTTON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/next_page.png");

    private static final int BOOK_WIDTH = 293, BOOK_HEIGHT = 184;
    private static final int TEXTURE_SIZE = 512;
    private static final int COLOR = 0x82755E;

    private static final int PREV_BUTTON_X = 10, PREV_BUTTON_Y = 148;
    private static final int PREV_BUTTON_WIDTH = 13, PREV_BUTTON_HEIGHT = 15;
    private static final int NEXT_BUTTON_X = 268, NEXT_BUTTON_Y = 148;
    private static final int NEXT_BUTTON_WIDTH = 13, NEXT_BUTTON_HEIGHT = 15;
    private static final int MAIN_MENU_X = 93, MAIN_MENU_Y = 165;
    private static final int MAIN_MENU_WIDTH = 22, MAIN_MENU_HEIGHT = 31;
    private static final int BACK_CATEGORY_X = 9, BACK_CATEGORY_Y = 5;
    private static final int BACK_CATEGORY_WIDTH = 13, BACK_CATEGORY_HEIGHT = 15;
    private static final int SEP_WIDTH = 94, SEP_HEIGHT = 3;

    private static final int ANIMATION_FRAMES = 8;
    private static final int FRAME_HEIGHT = 64;
    private static final int FRAME_WIDTH = 64;
    private static final int FRAME_DURATION = 30;

    private int currentHoveredIcon = -1; // -1 = ninguno, 0 = agriculture, 1 = decoration, 2 = production
    private long hoverStartTime = -1;

    private enum PageState { MAIN_MENU, CATEGORY_LIST, ENTRY_PAGES }

    private PageState currentState = PageState.MAIN_MENU;
    private CatalogData.Category selectedCategory;
    private CatalogData.Entry selectedEntry;
    private int currentPageIndex = 0;
    private int bookX, bookY;

    public CatalogScreen() {
        super(Text.empty());
    }

    @Override
    protected void init() {
        super.init();
        updateBookPosition();
    }

    private void updateBookPosition() {
        this.bookX = (this.width - BOOK_WIDTH) / 2;
        this.bookY = (this.height - BOOK_HEIGHT) / 2;
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        super.renderInGameBackground(context);
        renderBookBackground(context);

        PageUtils leftPage = PageUtils.createLeftPage(context, textRenderer, bookX, bookY);
        PageUtils rightPage = PageUtils.createRightPage(context, textRenderer, bookX, bookY);

        switch (currentState) {
            case MAIN_MENU -> renderMainMenu(leftPage, rightPage, mouseX, mouseY);
            case CATEGORY_LIST -> renderCategoryList(leftPage, rightPage, mouseX, mouseY);
            case ENTRY_PAGES -> renderEntryPages(leftPage, rightPage);
        }

        renderNavigationButtons(context, mouseX, mouseY);
    }

    private void renderBookBackground(DrawContext context) {
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        context.drawTexture(BACKGROUND, bookX, bookY, 0, 0, BOOK_WIDTH, BOOK_HEIGHT, TEXTURE_SIZE, TEXTURE_SIZE);
    }

    private void renderNavigationButtons(DrawContext context, int mouseX, int mouseY) {
        switch (currentState) {
            case CATEGORY_LIST -> renderMainMenuButton(context, mouseX, mouseY);
            case ENTRY_PAGES -> renderEntryNavigationButtons(context, mouseX, mouseY);
        }
    }

    private void renderMainMenuButton(DrawContext context, int mouseX, int mouseY) {
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

    private void renderEntryNavigationButtons(DrawContext context, int mouseX, int mouseY) {
        if (selectedEntry == null) return;

        List<CatalogData.Page> pages = selectedEntry.getPages();

        // Prev button
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

        // Next button
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

        // Back to category button
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

        // Menu button
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

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) return false;

        switch (currentState) {
            case MAIN_MENU -> { return handleMainMenuClick(mouseX, mouseY); }
            case CATEGORY_LIST -> { return handleCategoryListClick(mouseX, mouseY); }
            case ENTRY_PAGES -> { return handleEntryPagesClick(mouseX, mouseY); }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean handleMainMenuClick(double mouseX, double mouseY) {
        int rightPageX = bookX + PageUtils.RIGHT_PAGE_X;
        int rightPageY = bookY + PageUtils.RIGHT_PAGE_Y;

        int[] iconXs = {43, 43, 43};
        int[] iconYs = {35, 73, 110};
        int iconSize = 32;
        List<CatalogData.Category> categories = CatalogData.CATEGORIES;

        for (int i = 0; i < categories.size(); i++) {
            int x = iconXs[i];
            int y = iconYs[i];

            if (isMouseOver(mouseX, mouseY, rightPageX + x, rightPageY + y, iconSize, iconSize)) {
                CatalogData.Category category = categories.get(i);
                if (selectedCategory != category || currentState != PageState.CATEGORY_LIST) {
                    selectedCategory = category;
                    currentState = PageState.CATEGORY_LIST;
                    playPageTurnSound();
                }
                return true;
            }
        }
        return false;
    }

    private boolean handleCategoryListClick(double mouseX, double mouseY) {
        if (selectedCategory == null) return false;

        // Entry
        if (handleEntryClicks(mouseX, mouseY)) return true;

        // Menu
        if (isMouseOver(mouseX, mouseY, bookX + MAIN_MENU_X, bookY + MAIN_MENU_Y,
                MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT)) {
            currentState = PageState.MAIN_MENU;
            selectedCategory = null;
            playPageTurnSound();
            return true;
        }

        return false;
    }

    private boolean handleEntryClicks(double mouseX, double mouseY) {
        List<CatalogData.Entry> entries = selectedCategory.getEntries();
        int startY = 30, spacing = 17, startYr = 10;
        int entryWidth = PageUtils.PAGE_WIDTH;
        int entryHeight = 16;

        int leftIndex = 0;
        int rightIndex = 0;

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

            if (isMouseOver(mouseX, mouseY, pageX, pageY, entryWidth, entryHeight)) {
                if (selectedEntry != entry || currentState != PageState.ENTRY_PAGES) {
                    selectedEntry = entry;
                    currentPageIndex = 0;
                    currentState = PageState.ENTRY_PAGES;
                    playPageTurnSound();
                }
                return true;
            }
        }
        return false;
    }

    private boolean handleEntryPagesClick(double mouseX, double mouseY) {
        if (selectedEntry == null) return false;

        List<CatalogData.Page> pages = selectedEntry.getPages();

        // Prev page
        if (isMouseOver(mouseX, mouseY, bookX + PREV_BUTTON_X, bookY + PREV_BUTTON_Y,
                PREV_BUTTON_WIDTH, PREV_BUTTON_HEIGHT) && currentPageIndex > 0) {
            currentPageIndex--;
            playPageTurnSound();
            return true;
        }

        // Next page
        if (isMouseOver(mouseX, mouseY, bookX + NEXT_BUTTON_X, bookY + NEXT_BUTTON_Y,
                NEXT_BUTTON_WIDTH, NEXT_BUTTON_HEIGHT) && currentPageIndex < pages.size() - 1) {
            currentPageIndex++;
            playPageTurnSound();
            return true;
        }

        // Back to category
        if (isMouseOver(mouseX, mouseY, bookX + BACK_CATEGORY_X, bookY + BACK_CATEGORY_Y,
                BACK_CATEGORY_WIDTH, BACK_CATEGORY_HEIGHT)) {
            currentState = PageState.CATEGORY_LIST;
            selectedEntry = null;
            playPageTurnSound();
            return true;
        }

        // Menu
        if (isMouseOver(mouseX, mouseY, bookX + MAIN_MENU_X, bookY + MAIN_MENU_Y,
                MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT)) {
            currentState = PageState.MAIN_MENU;
            selectedEntry = null;
            selectedCategory = null;
            playPageTurnSound();
            return true;
        }

        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }

        switch (keyCode) {
            case GLFW.GLFW_KEY_LEFT -> {
                if (currentState == PageState.ENTRY_PAGES && currentPageIndex > 0) {
                    currentPageIndex--;
                    playPageTurnSound();
                    return true;
                }
            }
            case GLFW.GLFW_KEY_RIGHT -> {
                if (currentState == PageState.ENTRY_PAGES && selectedEntry != null &&
                        currentPageIndex < selectedEntry.getPages().size() - 1) {
                    currentPageIndex++;
                    playPageTurnSound();
                    return true;
                }
            }
            case GLFW.GLFW_KEY_UP -> {
                if (currentState == PageState.ENTRY_PAGES) {
                    currentState = PageState.CATEGORY_LIST;
                    selectedEntry = null;
                    playPageTurnSound();
                    return true;
                }
            }
            case GLFW.GLFW_KEY_DOWN -> {
                if (currentState == PageState.ENTRY_PAGES || currentState == PageState.CATEGORY_LIST) {
                    currentState = PageState.MAIN_MENU;
                    selectedEntry = null;
                    selectedCategory = null;
                    playPageTurnSound();
                    return true;
                }
            }
            case GLFW.GLFW_KEY_TAB -> {
                if (currentState == PageState.MAIN_MENU || currentState == PageState.CATEGORY_LIST) {
                    return navigateCategories(modifiers == GLFW.GLFW_MOD_SHIFT);
                }
            }

        }
        return false;
    }
    private boolean navigateCategories(boolean reverse) {
        List<CatalogData.Category> categories = CatalogData.CATEGORIES;
        if (categories.isEmpty()) return false;

        if (selectedCategory == null) {
            selectedCategory = categories.getFirst();
            currentState = PageState.CATEGORY_LIST;
            selectedEntry = null;
            playPageTurnSound();
            return true;
        }

        int currentIndex = categories.indexOf(selectedCategory);

        int newIndex;
        if (reverse) {
            newIndex = currentIndex <= 0 ? categories.size() - 1 : currentIndex - 1;
        } else {
            newIndex = currentIndex >= categories.size() - 1 ? 0 : currentIndex + 1;
        }

        selectedCategory = categories.get(newIndex);
        selectedEntry = null;
        playPageTurnSound();
        return true;
    }

    private void playPageTurnSound() {
        if (this.client != null && this.client.getSoundManager() != null) {
            this.client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F));
        }
    }

    private boolean isMouseOver(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    private void renderMainMenu(PageUtils leftPage, PageUtils rightPage, int mouseX, int mouseY) {
        leftPage.drawIcon(MENU_TITLE, 0, 11, 120, 20);
        leftPage.drawParagraph(Text.translatable("text.resprouted.main"), 40, COLOR);
        rightPage.drawIcon(INDEX_TITLE, 15, 11, 91, 20);

        int[] iconXs = {43, 43, 43};
        int[] iconYs = {35, 73, 110};
        int displaySize = 32;

        List<CatalogData.Category> categories = CatalogData.CATEGORIES;
        int rightPageX = bookX + PageUtils.RIGHT_PAGE_X;
        int rightPageY = bookY + PageUtils.RIGHT_PAGE_Y;

        // Detect hover
        int hoveredIcon = -1;
        for (int i = 0; i < categories.size(); i++) {
            int x = iconXs[i];
            int y = iconYs[i];
            if (isMouseOver(mouseX, mouseY, rightPageX + x, rightPageY + y, displaySize, displaySize)) {
                hoveredIcon = i;
                break;
            }
        }

        // Update hover state & anim
        if (hoveredIcon != currentHoveredIcon) {
            currentHoveredIcon = hoveredIcon;
            if (hoveredIcon != -1) {
                hoverStartTime = System.currentTimeMillis();
            }
        }

        // Render icons
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
                    // Calculate frametime just once until last frame
                    long elapsed = System.currentTimeMillis() - hoverStartTime;
                    int frame = (int) (elapsed / FRAME_DURATION);

                    // State in last frame
                    if (frame >= ANIMATION_FRAMES - 1) {
                        frame = ANIMATION_FRAMES - 1;
                    }

                    renderAnimatedIcon(rightPage, iconTexture, x, y, displaySize, displaySize, frame);
                } else {

                    renderAnimatedIcon(rightPage, iconTexture, x, y, displaySize, displaySize, 0);
                }

                // Tooltip
                if (i == hoveredIcon) {
                    rightPage.getContext().drawTooltip(textRenderer,
                            Text.translatable("text.category.resprouted." + category.getName()), mouseX, mouseY);
                }
            }
        }
    }

    private void renderAnimatedIcon(PageUtils page, Identifier texture, int x, int y, int width, int height, int frame) {
        DrawContext context = page.getContext();
        int baseX = page.getBaseX();
        int baseY = page.getBaseY();

        // Draw specific frame
        context.drawTexture(texture,
                baseX + x, baseY + y,
                width, height,
                0, frame * FRAME_HEIGHT,
                FRAME_WIDTH, FRAME_HEIGHT,
                FRAME_WIDTH, FRAME_HEIGHT * ANIMATION_FRAMES);
    }


    private void renderCategoryList(PageUtils leftPage, PageUtils rightPage, int mouseX, int mouseY) {
        if (selectedCategory == null) return;

        leftPage.drawTitle(Text.translatable("text.category.resprouted." + selectedCategory.getName()).formatted(Formatting.BOLD), 10, COLOR);
        leftPage.drawIcon(SEPARATOR, 12, 22, SEP_WIDTH, SEP_HEIGHT);

        List<CatalogData.Entry> entries = selectedCategory.getEntries();
        int startY = 30, spacing = 17, startYr = 10;
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
    }
    private void drawEntryHoverBackground(PageUtils page, int y, int width, int height) {
        int x = 8;
        int backgroundColor = 0x80E2DDCC;

        DrawContext context = page.getContext();
        context.fill(page.getBaseX() + x, page.getBaseY() + y, page.getBaseX() + x + width, page.getBaseY() + y + height, backgroundColor);
    }

    private void renderEntryPages(PageUtils leftPage, PageUtils rightPage) {
        if (selectedEntry == null) return;

        List<CatalogData.Page> pages = selectedEntry.getPages();
        if (pages.isEmpty() || currentPageIndex >= pages.size()) return;

        CatalogData.Page page = pages.get(currentPageIndex);

        leftPage.drawTitle((selectedEntry.getName()), 10, COLOR);
        leftPage.drawIcon(SEPARATOR, 12, 22, SEP_WIDTH, SEP_HEIGHT);

        if (page instanceof CatalogData.TextPage textPage) {
            leftPage.drawParagraph(textPage.getLeftText(), 40, COLOR);

            if (textPage.getRightText() != null) {
                rightPage.drawParagraph(textPage.getRightText(), 10, COLOR);
            }
        }
    }

    private void drawButtonWithTooltip(DrawContext context, Identifier texture, int x, int y, int width, int height, Text tooltip, int mouseX, int mouseY) {
        context.drawTexture(texture, x, y, 0, 0, width, height, width, height);

        if (isMouseOver(mouseX, mouseY, x, y, width, height)) {
            context.drawTooltip(textRenderer, tooltip, mouseX, mouseY);
        }
    }
}

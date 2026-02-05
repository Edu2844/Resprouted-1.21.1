package net.edu.resprouted.book.pages;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.book.CatalogData;
import net.edu.resprouted.book.CatalogScreen;
import net.edu.resprouted.book.abstracts.BaseCatalogPage;
import net.edu.resprouted.util.misc.PageUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class CategoryListPage extends BaseCatalogPage {
    private static final Identifier PREV_BUTTON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/previous_page.png");
    private static final Identifier NEXT_BUTTON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/next_page.png");

    private static final int ENTRY_START_Y_LEFT = 30;
    private static final int ENTRY_START_Y_RIGHT = 10;
    private static final int ENTRY_SPACING = 17;
    private static final int ENTRY_WIDTH = PageUtils.PAGE_WIDTH;
    private static final int ENTRY_HEIGHT = 16;

    private static final int MAX_ENTRIES_LEFT = 7;
    private static final int MAX_ENTRIES_RIGHT = 8;

    private int currentCategoryPageIndex = 0;
    private List<PageLayout> cachedLayouts = new ArrayList<>();

    public CategoryListPage(CatalogScreen screen) {
        super(screen);
    }

    @Override
    public void init() {
        super.init();

        int bookX = screen.getBookX();
        int bookY = screen.getBookY();

        currentCategoryPageIndex = 0;

        // Previous page button
        widgets.add(createNavigationButton(PREV_BUTTON, bookX + 10, bookY + 148, 13, 15,
                Text.translatable("text.resprouted.previous_page"), button -> {
                    if (currentCategoryPageIndex > 0) {
                        currentCategoryPageIndex--;
                        screen.playPageTurnSound();
                    }
                }
        ));

        // Next page button
        widgets.add(createNavigationButton(NEXT_BUTTON, bookX + 268, bookY + 148, 13, 15,
                Text.translatable("text.resprouted.next_page"), button -> {
                    if (currentCategoryPageIndex < cachedLayouts.size() - 1) {
                        currentCategoryPageIndex++;
                        screen.playPageTurnSound();
                    }
                }
        ));

        // Menu button
        widgets.add(createMenuButton(bookX, bookY));
    }

    @Override
    public void render(PageUtils leftPage, PageUtils rightPage, int mouseX, int mouseY) {
        CatalogData.Category selectedCategory = screen.getSelectedCategory();
        if (selectedCategory == null) return;

        // Title and separator
        leftPage.drawTitle(Text.translatable("text.category.resprouted." + selectedCategory.getName()).formatted(Formatting.BOLD), 10, COLOR);
        leftPage.drawIcon(SEPARATOR, 12, 22, SEP_WIDTH, SEP_HEIGHT);

        // Calculate layouts (will reorganize if entries changed due to config)
        cachedLayouts = calculatePageLayouts(selectedCategory.getEntries());

        // Get current page layout
        if (currentCategoryPageIndex >= cachedLayouts.size()) {
            currentCategoryPageIndex = Math.max(0, cachedLayouts.size() - 1);
        }

        if (!cachedLayouts.isEmpty()) {
            PageLayout currentLayout = cachedLayouts.get(currentCategoryPageIndex);
            renderPageLayout(leftPage, rightPage, currentLayout, mouseX, mouseY);
        }

        updateButtonVisibility();
    }

    @Override
    public boolean handleClick(double mouseX, double mouseY) {
        if (cachedLayouts.isEmpty() || currentCategoryPageIndex >= cachedLayouts.size()) {
            return false;
        }

        PageLayout currentLayout = cachedLayouts.get(currentCategoryPageIndex);
        return handleLayoutClicks(currentLayout, mouseX, mouseY);
    }

    /**
     * Calculate automatic page layouts
     */
    private List<PageLayout> calculatePageLayouts(List<CatalogData.Entry> allEntries) {
        List<PageLayout> layouts = new ArrayList<>();
        PageLayout currentPage = new PageLayout();

        for (CatalogData.Entry entry : allEntries) {
            // Try to add to left first
            if (currentPage.leftEntries.size() < MAX_ENTRIES_LEFT) {
                currentPage.leftEntries.add(entry);
            }
            // If left is full, add to right
            else if (currentPage.rightEntries.size() < MAX_ENTRIES_RIGHT) {
                currentPage.rightEntries.add(entry);
            }
            // If both are full, create new page
            else {
                layouts.add(currentPage);
                currentPage = new PageLayout();
                currentPage.leftEntries.add(entry);
            }
        }

        // Add last page if it has content
        if (!currentPage.isEmpty()) {
            layouts.add(currentPage);
        }

        return layouts;
    }

    /**
     * Render a single page layout
     */
    private void renderPageLayout(PageUtils leftPage, PageUtils rightPage, PageLayout layout, int mouseX, int mouseY) {
        // Render left entries
        for (int i = 0; i < layout.leftEntries.size(); i++) {
            CatalogData.Entry entry = layout.leftEntries.get(i);
            int y = ENTRY_START_Y_LEFT + i * ENTRY_SPACING;

            if (leftPage.isMouseOverEntry(mouseX, mouseY, y, ENTRY_WIDTH, ENTRY_HEIGHT)) {
                drawEntryHoverBackground(leftPage, y, ENTRY_WIDTH, ENTRY_HEIGHT);
            }

            leftPage.drawItemStackIcon(entry.getIcon(), 10, y);
            leftPage.drawText(entry.getName(), 30, y + 4, COLOR);
        }

        // Render right entries
        for (int i = 0; i < layout.rightEntries.size(); i++) {
            CatalogData.Entry entry = layout.rightEntries.get(i);
            int y = ENTRY_START_Y_RIGHT + i * ENTRY_SPACING;

            if (rightPage.isMouseOverEntry(mouseX, mouseY, y, ENTRY_WIDTH, ENTRY_HEIGHT)) {
                drawEntryHoverBackground(rightPage, y, ENTRY_WIDTH, ENTRY_HEIGHT);
            }

            rightPage.drawItemStackIcon(entry.getIcon(), 10, y);
            rightPage.drawText(entry.getName(), 30, y + 4, COLOR);
        }
    }

    /**
     * Handle clicks on current page layout
     */
    private boolean handleLayoutClicks(PageLayout layout, double mouseX, double mouseY) {
        int bookX = screen.getBookX();
        int bookY = screen.getBookY();

        // Check left entries
        for (int i = 0; i < layout.leftEntries.size(); i++) {
            CatalogData.Entry entry = layout.leftEntries.get(i);
            int y = ENTRY_START_Y_LEFT + i * ENTRY_SPACING;
            int pageX = bookX + PageUtils.LEFT_PAGE_X + 8;
            int pageY = bookY + PageUtils.LEFT_PAGE_Y + y;

            if (screen.isMouseOver(mouseX, mouseY, pageX, pageY, ENTRY_WIDTH, ENTRY_HEIGHT)) {
                selectEntry(entry);
                return true;
            }
        }

        // Check right entries
        for (int i = 0; i < layout.rightEntries.size(); i++) {
            CatalogData.Entry entry = layout.rightEntries.get(i);
            int y = ENTRY_START_Y_RIGHT + i * ENTRY_SPACING;
            int pageX = bookX + PageUtils.RIGHT_PAGE_X + 8;
            int pageY = bookY + PageUtils.RIGHT_PAGE_Y + y;

            if (screen.isMouseOver(mouseX, mouseY, pageX, pageY, ENTRY_WIDTH, ENTRY_HEIGHT)) {
                selectEntry(entry);
                return true;
            }
        }

        return false;
    }

    private void selectEntry(CatalogData.Entry entry) {
        if (screen.getSelectedEntry() != entry || screen.getCurrentState() != CatalogScreen.PageState.ENTRY_PAGES) {
            screen.setSelectedEntry(entry);
            screen.setCurrentPageIndex(0);
            screen.setCurrentState(CatalogScreen.PageState.ENTRY_PAGES);
            screen.playPageTurnSound();
        }
    }

    private void updateButtonVisibility() {
        if (widgets.size() >= 3) {
            widgets.get(0).visible = currentCategoryPageIndex > 0;
            widgets.get(1).visible = currentCategoryPageIndex < cachedLayouts.size() - 1;
            widgets.get(2).visible = true;
        }
    }

    public void resetPage() {
        currentCategoryPageIndex = 0;
        cachedLayouts.clear();
    }

    /**
     * Internal class to represent a page layout
     */
    private static class PageLayout {
        List<CatalogData.Entry> leftEntries = new ArrayList<>();
        List<CatalogData.Entry> rightEntries = new ArrayList<>();

        boolean isEmpty() {
            return leftEntries.isEmpty() && rightEntries.isEmpty();
        }
    }
}
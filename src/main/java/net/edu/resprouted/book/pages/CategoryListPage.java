package net.edu.resprouted.book.pages;

import net.edu.resprouted.book.CatalogData;
import net.edu.resprouted.book.CatalogScreen;
import net.edu.resprouted.book.abstracts.BaseCatalogPage;
import net.edu.resprouted.util.PageUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

@Environment(EnvType.CLIENT)
public class CategoryListPage extends BaseCatalogPage {
    private static final int ENTRY_START_Y_LEFT = 30;
    private static final int ENTRY_START_Y_RIGHT = 10;
    private static final int ENTRY_SPACING = 17;
    private static final int ENTRY_WIDTH = PageUtils.PAGE_WIDTH;
    private static final int ENTRY_HEIGHT = 16;

    public CategoryListPage(CatalogScreen screen) {
        super(screen);
    }

    @Override
    public void init() {
        super.init();

        int bookX = screen.getBookX();
        int bookY = screen.getBookY();

        //Add menu button
        widgets.add(createMenuButton(bookX, bookY));
    }

    @Override
    public void render(PageUtils leftPage, PageUtils rightPage, int mouseX, int mouseY) {
        CatalogData.Category selectedCategory = screen.getSelectedCategory();
        if (selectedCategory == null) return;

        //Title and separator
        leftPage.drawTitle(Text.translatable("text.category.resprouted." + selectedCategory.getName()).formatted(Formatting.BOLD), 10, COLOR);
        leftPage.drawIcon(SEPARATOR, 12, 22, SEP_WIDTH, SEP_HEIGHT);

        //Render entries
        renderEntries(leftPage, rightPage, selectedCategory.getEntries(), mouseX, mouseY);
    }

    @Override
    public boolean handleClick(double mouseX, double mouseY) {
        CatalogData.Category selectedCategory = screen.getSelectedCategory();
        if (selectedCategory == null) return false;

        return handleEntryClicks(selectedCategory.getEntries(), mouseX, mouseY);
    }

    private void renderEntries(PageUtils leftPage, PageUtils rightPage,
                               List<CatalogData.Entry> entries, int mouseX, int mouseY) {
        int leftIndex = 0;
        int rightIndex = 0;

        for (CatalogData.Entry entry : entries) {
            PageUtils target;
            int y;

            if (entry.getSide() == CatalogData.Entry.Side.LEFT) {
                target = leftPage;
                y = ENTRY_START_Y_LEFT + leftIndex * ENTRY_SPACING;
                leftIndex++;
            } else {
                target = rightPage;
                y = ENTRY_START_Y_RIGHT + rightIndex * ENTRY_SPACING;
                rightIndex++;
            }

            //Hover effect
            if (target.isMouseOverEntry(mouseX, mouseY, y, ENTRY_WIDTH, ENTRY_HEIGHT)) {
                drawEntryHoverBackground(target, y, ENTRY_WIDTH, ENTRY_HEIGHT);
            }

            //Draw entry
            target.drawItemStackIcon(entry.getIcon(), 10, y);
            target.drawText(entry.getName(), 30, y + 4, COLOR);
        }
    }

    private boolean handleEntryClicks(List<CatalogData.Entry> entries, double mouseX, double mouseY) {
        int leftIndex = 0;
        int rightIndex = 0;
        int bookX = screen.getBookX();
        int bookY = screen.getBookY();

        for (CatalogData.Entry entry : entries) {
            int y;
            int pageX, pageY;

            if (entry.getSide() == CatalogData.Entry.Side.LEFT) {
                y = ENTRY_START_Y_LEFT + leftIndex * ENTRY_SPACING;
                leftIndex++;
                pageX = bookX + PageUtils.LEFT_PAGE_X + 8;
                pageY = bookY + PageUtils.LEFT_PAGE_Y + y;
            } else {
                y = ENTRY_START_Y_RIGHT + rightIndex * ENTRY_SPACING;
                rightIndex++;
                pageX = bookX + PageUtils.RIGHT_PAGE_X + 8;
                pageY = bookY + PageUtils.RIGHT_PAGE_Y + y;
            }

            if (screen.isMouseOver(mouseX, mouseY, pageX, pageY, ENTRY_WIDTH, ENTRY_HEIGHT)) {
                if (screen.getSelectedEntry() != entry ||
                        screen.getCurrentState() != CatalogScreen.PageState.ENTRY_PAGES) {
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
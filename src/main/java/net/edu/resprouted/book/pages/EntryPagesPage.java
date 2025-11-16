package net.edu.resprouted.book.pages;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.book.CatalogData;
import net.edu.resprouted.book.CatalogScreen;
import net.edu.resprouted.book.abstracts.BaseCatalogPage;
import net.edu.resprouted.util.PageUtils;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;


public class EntryPagesPage extends BaseCatalogPage {
    private static final Identifier CATEGORY_ICON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/category_back.png");
    private static final Identifier PREV_BUTTON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/previous_page.png");
    private static final Identifier NEXT_BUTTON = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/next_page.png");

    public EntryPagesPage(CatalogScreen screen) {
        super(screen);
    }

    @Override
    public void init() {
        super.init();

        int bookX = screen.getBookX();
        int bookY = screen.getBookY();

        //Previous page button
        widgets.add(createNavigationButton(
                PREV_BUTTON,
                bookX + 10, bookY + 148,
                13, 15,
                Text.translatable("text.resprouted.previous_page"),
                button -> {
                    int current = screen.getCurrentPageIndex();
                    if (current > 0) {
                        screen.setCurrentPageIndex(current - 1);
                        screen.playPageTurnSound();
                    }
                }
        ));

        //Next page button
        widgets.add(createNavigationButton(
                NEXT_BUTTON,
                bookX + 268, bookY + 148,
                13, 15,
                Text.translatable("text.resprouted.next_page"),
                button -> {
                    CatalogData.Entry entry = screen.getSelectedEntry();
                    int current = screen.getCurrentPageIndex();
                    if (entry != null && current < entry.getPages().size() - 1) {
                        screen.setCurrentPageIndex(current + 1);
                        screen.playPageTurnSound();
                    }
                }
        ));

        //Back to category button
        widgets.add(createNavigationButton(
                CATEGORY_ICON,
                bookX + 9, bookY + 5,
                13, 15,
                Text.translatable("text.resprouted.back_category"),
                button -> {
                    screen.setCurrentState(CatalogScreen.PageState.CATEGORY_LIST);
                    screen.setSelectedEntry(null);
                    screen.playPageTurnSound();
                }
        ));

        //Menu button
        widgets.add(createMenuButton(bookX, bookY));
    }

    @Override
    public void render(PageUtils leftPage, PageUtils rightPage, int mouseX, int mouseY) {
        CatalogData.Entry selectedEntry = screen.getSelectedEntry();
        if (selectedEntry == null) return;

        List<CatalogData.Page> pages = selectedEntry.getPages();
        int currentPageIndex = screen.getCurrentPageIndex();
        if (pages.isEmpty() || currentPageIndex >= pages.size()) return;

        CatalogData.Page page = pages.get(currentPageIndex);

        leftPage.drawTitle(selectedEntry.getName(), 10, COLOR);
        leftPage.drawIcon(SEPARATOR, 12, 22, SEP_WIDTH, SEP_HEIGHT);

        if (page instanceof CatalogData.TextPage textPage) {
            leftPage.drawParagraph(textPage.getLeftText(), 30, COLOR);

            if (textPage.getRightText() != null) {
                rightPage.drawParagraph(textPage.getRightText(), 10, COLOR);
            }
        }
        updateButtonVisibility();
    }

    @Override
    public boolean handleClick(double mouseX, double mouseY) {
        return false;
    }

    private void updateButtonVisibility() {
        CatalogData.Entry selectedEntry = screen.getSelectedEntry();
        int currentPageIndex = screen.getCurrentPageIndex();

        if (selectedEntry != null && widgets.size() >= 4) {
            // Prev button (index 0)
            widgets.get(0).visible = currentPageIndex > 0;

            // Next button (index 1)
            widgets.get(1).visible = currentPageIndex < selectedEntry.getPages().size() - 1;

            //Back button and menu button always visible
            widgets.get(2).visible = true;
            widgets.get(3).visible = true;
        }
    }
}
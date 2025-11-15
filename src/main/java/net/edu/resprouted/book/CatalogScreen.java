package net.edu.resprouted.book;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.book.CatalogData.*;
import net.edu.resprouted.util.PageUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.List;

@Environment(EnvType.CLIENT)
public class CatalogScreen extends Screen {
    public static final Identifier BACKGROUND = Identifier.of(Resprouted.MOD_ID, "textures/gui/catalog/book.png");
    public static final int BOOK_WIDTH = 293;
    public static final int BOOK_HEIGHT = 184;
    public static final int TEXTURE_SIZE = 512;

    public enum PageState { MAIN_MENU, CATEGORY_LIST, ENTRY_PAGES }

    private PageState currentState = PageState.MAIN_MENU;
    private Category selectedCategory;
    private Entry selectedEntry;
    private int currentPageIndex = 0;
    private int bookX, bookY;

    private MainMenuPage mainMenuPage;
    private CategoryListPage categoryListPage;
    private EntryPagesPage entryPagesPage;

    public CatalogScreen() {
        super(Text.empty());
    }

    @Override
    protected void init() {
        super.init();
        this.bookX = (this.width - BOOK_WIDTH) / 2;
        this.bookY = (this.height - BOOK_HEIGHT) / 2;

        this.mainMenuPage = new MainMenuPage(this);
        this.categoryListPage = new CategoryListPage(this);
        this.entryPagesPage = new EntryPagesPage(this);
    }

    @Override
    public void close() {
        super.close();

        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            Hand[] hands = {Hand.MAIN_HAND, Hand.OFF_HAND};
            for (Hand hand : hands) {
                ItemStack stack = player.getStackInHand(hand);
                if (stack.isOf(ModItems.CATALOG)) {
                    stack.remove(DataComponentTypes.CUSTOM_MODEL_DATA);
                }
            }
        }
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        super.renderInGameBackground(context);
        context.drawTexture(BACKGROUND, bookX, bookY, 0, 0, BOOK_WIDTH, BOOK_HEIGHT, TEXTURE_SIZE, TEXTURE_SIZE);

        PageUtils leftPage = PageUtils.createLeftPage(context, textRenderer, bookX, bookY);
        PageUtils rightPage = PageUtils.createRightPage(context, textRenderer, bookX, bookY);

        switch (currentState) {
            case MAIN_MENU -> mainMenuPage.render(leftPage, rightPage, mouseX, mouseY);
            case CATEGORY_LIST -> categoryListPage.render(leftPage, rightPage, mouseX, mouseY);
            case ENTRY_PAGES -> entryPagesPage.render(leftPage, rightPage, mouseX, mouseY);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) return false;

        switch (currentState) {
            case MAIN_MENU -> {
                return mainMenuPage.handleClick(mouseX, mouseY);
            }
            case CATEGORY_LIST -> {
                return categoryListPage.handleClick(mouseX, mouseY);
            }
            case ENTRY_PAGES -> {
                return entryPagesPage.handleClick(mouseX, mouseY);
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
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

    @Override
    public boolean shouldPause() {
        return false;
    }

    private boolean navigateCategories(boolean reverse) {
        List<Category> categories = CatalogData.CATEGORIES;
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

    public void playPageTurnSound() {
        if (this.client != null && this.client.getSoundManager() != null) {
            this.client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F));
        }
    }

    public boolean isMouseOver(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public TextRenderer getTextRenderer() {
        return this.textRenderer;
    }

    public PageState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(PageState state) {
        this.currentState = state;
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category category) {
        this.selectedCategory = category;
    }

    public Entry getSelectedEntry() {
        return selectedEntry;
    }

    public void setSelectedEntry(Entry entry) {
        this.selectedEntry = entry;
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public void setCurrentPageIndex(int index) {
        this.currentPageIndex = index;
    }

    public int getBookX() {
        return bookX;
    }

    public int getBookY() {
        return bookY;
    }
}
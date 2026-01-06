package net.edu.resprouted.book.widget;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CatalogButtonWidget extends ButtonWidget {
    private final Identifier normalTexture;
    private final Identifier hoverTexture;
    private final int textureWidth;
    private final int textureHeight;
    private final int hoverWidth;
    private final int hoverHeight;
    private final boolean hasHoverVariant;
    private final Text tooltipText;

    private CatalogButtonWidget(Builder builder) {
        super(builder.x, builder.y, builder.width, builder.height, ScreenTexts.EMPTY, builder.onPress, DEFAULT_NARRATION_SUPPLIER);
        this.normalTexture = builder.normalTexture;
        this.hoverTexture = builder.hoverTexture;
        this.textureWidth = builder.textureWidth;
        this.textureHeight = builder.textureHeight;
        this.hoverWidth = builder.hoverWidth;
        this.hoverHeight = builder.hoverHeight;
        this.hasHoverVariant = builder.hoverTexture != null;
        this.tooltipText = builder.message;
    }

    @Override
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        Identifier texture = normalTexture;
        int width = textureWidth;
        int height = textureHeight;

        if (hasHoverVariant && this.isSelected()) {
            texture = hoverTexture;
            width = hoverWidth;
            height = hoverHeight;
        }

        context.drawTexture(texture, this.getX(), this.getY(), 0, 0, width, height, width, height);

        // Render tooltip
        if (this.isHovered() && tooltipText != null && !tooltipText.equals(ScreenTexts.EMPTY)) {
            context.drawTooltip(MinecraftClient.getInstance().textRenderer, tooltipText, mouseX, mouseY);
        }
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        soundManager.play(PositionedSoundInstance.master(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F));
    }

    public static Builder builder(Identifier texture, PressAction onPress) {
        return new Builder(texture, onPress);
    }

    public static class Builder {
        private final Identifier normalTexture;
        private final PressAction onPress;
        private Identifier hoverTexture;
        private int x;
        private int y;
        private int width;
        private int height;
        private int textureWidth;
        private int textureHeight;
        private int hoverWidth;
        private int hoverHeight;
        private Text message = ScreenTexts.EMPTY;

        public Builder(Identifier normalTexture, PressAction onPress) {
            this.normalTexture = normalTexture;
            this.onPress = onPress;
        }

        public Builder position(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder size(int width, int height) {
            this.width = width;
            this.height = height;
            this.textureWidth = width;
            this.textureHeight = height;
            this.hoverWidth = width;
            this.hoverHeight = height;
            return this;
        }

        public Builder textureSize(int width, int height) {
            this.textureWidth = width;
            this.textureHeight = height;
            return this;
        }

        public Builder hoverTexture(Identifier texture, int width, int height) {
            this.hoverTexture = texture;
            this.hoverWidth = width;
            this.hoverHeight = height;
            return this;
        }

        public Builder tooltip(Text message) {
            this.message = message;
            return this;
        }

        public CatalogButtonWidget build() {
            return new CatalogButtonWidget(this);
        }
    }
}
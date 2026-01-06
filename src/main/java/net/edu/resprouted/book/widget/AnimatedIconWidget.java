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
public class AnimatedIconWidget extends ButtonWidget {
    private final Identifier texture;
    private final int frameCount;
    private final int frameWidth;
    private final int frameHeight;
    private final int frameDuration;
    private final int displaySize;
    private final Text tooltipText;

    private long animationStartTime = -1;
    private boolean wasHovered = false;

    private AnimatedIconWidget(Builder builder) {
        super(builder.x, builder.y, builder.displaySize, builder.displaySize, ScreenTexts.EMPTY, builder.onPress, DEFAULT_NARRATION_SUPPLIER);
        this.texture = builder.texture;
        this.frameCount = builder.frameCount;
        this.frameWidth = builder.frameWidth;
        this.frameHeight = builder.frameHeight;
        this.frameDuration = builder.frameDuration;
        this.displaySize = builder.displaySize;
        this.tooltipText = builder.message;
    }

    @Override
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        boolean currentlyHovered = this.isHovered();

        if (currentlyHovered && !wasHovered) {
            animationStartTime = System.currentTimeMillis();
        }

        wasHovered = currentlyHovered;

        int frame = 0;
        if (currentlyHovered && animationStartTime != -1) {
            long elapsed = System.currentTimeMillis() - animationStartTime;
            frame = (int) (elapsed / frameDuration);
            if (frame >= frameCount - 1) {
                frame = frameCount - 1;
            }
        }

        context.drawTexture(
                texture,
                this.getX(), this.getY(),
                displaySize, displaySize,
                0, frame * frameHeight,
                frameWidth, frameHeight,
                frameWidth, frameHeight * frameCount
        );

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
        private final Identifier texture;
        private final PressAction onPress;
        private int x;
        private int y;
        private int frameCount = 8;
        private int frameWidth = 64;
        private int frameHeight = 64;
        private int frameDuration = 25;
        private int displaySize = 32;
        private Text message = ScreenTexts.EMPTY;

        public Builder(Identifier texture, PressAction onPress) {
            this.texture = texture;
            this.onPress = onPress;
        }

        public Builder position(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder animation(int frames, int frameWidth, int frameHeight, int duration) {
            this.frameCount = frames;
            this.frameWidth = frameWidth;
            this.frameHeight = frameHeight;
            this.frameDuration = duration;
            return this;
        }

        public Builder displaySize(int size) {
            this.displaySize = size;
            return this;
        }

        public Builder tooltip(Text message) {
            this.message = message;
            return this;
        }

        public AnimatedIconWidget build() {
            return new AnimatedIconWidget(this);
        }
    }
}
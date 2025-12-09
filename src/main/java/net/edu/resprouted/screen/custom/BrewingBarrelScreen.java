package net.edu.resprouted.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.screen.widget.FluidWidget;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BrewingBarrelScreen extends HandledScreen<BrewingBarrelScreenHandler> {
    private static final Identifier GUI_TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/gui/brewing_barrel/brewing_barrel.png");
    private static final Identifier ARROW_TEXTURE_1 = Identifier.of(Resprouted.MOD_ID, "textures/gui/sprites/brewing_progress_1.png");
    private static final Identifier ARROW_TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/gui/sprites/brewing_progress_2.png");
    private static final Identifier BUBBLES_TEXTURE = Identifier.ofVanilla("container/brewing_stand/bubbles");
    private static final int[] BUBBLE_PROGRESS = new int[]{0, 6, 11, 16, 20, 24, 29};

    public BrewingBarrelScreen(BrewingBarrelScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();

        addDrawable(FluidWidget.builder(handler.getBlockEntity().getInputTank())
                .bounds(this.x + 62, this.y + 27, 16, 32)
                .posSupplier(() -> handler.getBlockEntity().getPos())
                .build());

        addDrawable(FluidWidget.builder(handler.getBlockEntity().getOutputTank())
                .bounds(this.x + 116, this.y + 27, 16, 32)
                .posSupplier(() -> handler.getBlockEntity().getPos())
                .build());

        addDrawable(FluidWidget.builder(handler.getBlockEntity().getAuxiliaryTank())
                .bounds(this.x + 26, this.y + 35, 16, 16)
                .posSupplier(() -> handler.getBlockEntity().getPos())
                .build());
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgress(context, x, y);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        context.drawText(this.textRenderer, this.title, this.titleX, this.titleY, 4210752, false);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    private void renderProgress(DrawContext context, int x, int y) {
        int m = this.handler.getBrewingTime();
        if (m > 0) {
            context.drawTexture(ARROW_TEXTURE,
                    x + 85, y + 35, 0, 0, handler.getScaledArrowProgress(), 16, 24, 16);

            context.drawTexture(ARROW_TEXTURE_1,
                    x + 45, y + 37, 0, 0, handler.getScaledAuxArrowProgress(), 10, 13, 10);

            int n = BUBBLE_PROGRESS[m / 3 % 7];
            if (n > 0) {
                context.drawGuiTexture(BUBBLES_TEXTURE, 12, 29, 0, 29 - n, x + 138, y + 28 + (29 - n), 12, n);
            }
        }
    }
}

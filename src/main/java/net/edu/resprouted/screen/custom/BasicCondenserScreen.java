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
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class BasicCondenserScreen extends HandledScreen<BasicCondenserScreenHandler> {
    private static final Identifier GUI_TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/gui/condenser/condenser.png");
    private static final Identifier ARROW_TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/gui/sprites/condenser_progress.png");
    private static final Identifier LIT_TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/gui/sprites/lit_progress.png");

    public BasicCondenserScreen(BasicCondenserScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);

    }

    @Override
    protected void init() {
        super.init();

        addDrawable(FluidWidget.builder(this.handler.getBlockEntity().getFluidStorage())
                .bounds(this.x + 133, this.y + 27, 16, 32)
                .posSupplier(() -> this.handler.getBlockEntity().getPos())
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

        renderProgressArrow(context, x, y);
        renderProgressLit(context, x, y);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(ARROW_TEXTURE, x + 44, y + 29, 0, 0, handler.getScaledArrowProgress(), 27, 50, 28);
        }
    }

    private void renderProgressLit(DrawContext context, int x, int y) {
        if (handler.isBurning()) {
            int height = MathHelper.ceil(handler.getFuelProgress() * 14);
            context.drawTexture(LIT_TEXTURE, x + 67, y + 46 + (14 - height), 0, 14 - height, 14, height, 14, 14);
        }
    }
}

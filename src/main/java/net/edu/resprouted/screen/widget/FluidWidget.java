package net.edu.resprouted.screen.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.edu.resprouted.util.FluidStack;
import net.edu.resprouted.util.ScreenUtils;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.texture.Sprite;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FluidWidget implements Drawable, Widget {
    private final SingleFluidStorage fluidStorage;
    private final Supplier<BlockPos> posSupplier;

    private final int width, height;
    private int x, y;

    public FluidWidget(SingleFluidStorage fluidStorage, int x, int y, int width, int height, Supplier<BlockPos> posSupplier) {
        this.fluidStorage = fluidStorage;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.posSupplier = posSupplier;
    }
    public static Builder builder(SingleFluidStorage fluidStorage) {
        return new Builder(fluidStorage);
    }
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        long fluidAmount = this.fluidStorage.getAmount();
        if(fluidAmount <= 0)
            return;

        Fluid fluid = this.fluidStorage.variant.getFluid();
        long fluidCapacity = this.fluidStorage.getCapacity();
        int fluidHeight = Math.round(((float) fluidAmount / fluidCapacity) * this.height);

        FluidRenderHandler fluidRenderHandler = FluidRenderHandlerRegistry.INSTANCE.get(fluid);
        if(fluidRenderHandler == null)
            return;

        BlockPos pos = this.posSupplier.get();
        FluidState fluidState = fluid.getDefaultState();
        World world = MinecraftClient.getInstance().world;
        if(world == null)
            return;

        Sprite sprite = fluidRenderHandler.getFluidSprites(world, pos, fluidState)[0];

        int tintColor = fluidRenderHandler.getFluidColor(world, pos, fluidState);
        float red = (tintColor >> 16 & 0xFF) / 255f;
        float green = (tintColor >> 8 & 0xFF) / 255f;
        float blue = (tintColor & 0xFF) / 255f;
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        ScreenUtils.renderTiledSprite(context, sprite, this.x, this.y + this.height - fluidHeight, this.width, fluidHeight, red, green, blue, 1.0f);

        if(isPointWithinBounds(this.x, this.y, this.width, this.height, mouseX, mouseY)) {
            drawTooltip(context, mouseX, mouseY);
        }
    }
    protected void drawTooltip(DrawContext context, int mouseX, int mouseY) {
        Fluid fluid = this.fluidStorage.variant.getFluid();
        long fluidAmount = this.fluidStorage.getAmount();
        long fluidCapacity = this.fluidStorage.getCapacity();

        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        if(fluid != null && fluidAmount > 0) {
            List<Text> texts = List.of(
                    Text.translatable(fluid.getDefaultState().getBlockState().getBlock().getTranslationKey()),
                    Text.literal("%s / %s mB".formatted(getMb(fluidAmount), getMb(fluidCapacity)))
            );
            context.drawTooltip(textRenderer, texts, mouseX, mouseY);
        }
    }
    private static long getMb(long amount) {
        return (long) (float) FluidStack.convertDropletsToMb(amount);
    }
    private static boolean isPointWithinBounds(int x, int y, int width, int height, int pointX, int pointY) {
        return pointX >= x && pointX <= x + width &&
                pointY >= y && pointY <= y + height;
    }
    @Override
    public void setX(int x) {
        this.x = x;
    }
    @Override
    public void setY(int y) {
        this.y = y;
    }
    @Override
    public int getX() {
        return this.x;
    }
    @Override
    public int getY() {
        return this.y;
    }
    @Override
    public int getWidth() {
        return this.width;
    }
    @Override
    public int getHeight() {
        return this.height;
    }
    @Override
    public void forEachChild(Consumer<ClickableWidget> consumer) {}

    public static class Builder {
        private final SingleFluidStorage fluidStorage;
        private Supplier<BlockPos> posSupplier = () -> null;
        private int x, y;
        private int width, height;

        public Builder(SingleFluidStorage fluidStorage) {
            this.fluidStorage = fluidStorage;
        }
        public Builder bounds(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            return this;
        }
        public Builder posSupplier(Supplier<BlockPos> posSupplier) {
            this.posSupplier = posSupplier;
            return this;
        }
        public FluidWidget build() {
            return new FluidWidget(this.fluidStorage, this.x, this.y, this.width, this.height, this.posSupplier);
        }
    }
}

package net.edu.resprouted.compat.rei.category;

import com.mojang.blaze3d.systems.RenderSystem;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.compat.rei.display.DryingBasinDisplay;
import net.edu.resprouted.util.fluid.FluidUtils;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class DryingBasinCategory implements DisplayCategory<DryingBasinDisplay> {
    public static final Identifier TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/gui/recipe/drying_basin_recipe.png");
    public static final CategoryIdentifier<DryingBasinDisplay> ID = CategoryIdentifier.of(Resprouted.MOD_ID, "plugin/drying");

    @Override
    public CategoryIdentifier<? extends DryingBasinDisplay> getCategoryIdentifier() {
        return ID;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("recipe.resprouted.drying");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.DRYING_BASIN);
    }

    @Override
    public int getDisplayHeight() {
        return 85;
    }

    @Override
    public List<Widget> setupDisplay(DryingBasinDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 57, bounds.getCenterY() - 26);
        List<Widget> widgets = new LinkedList<>();

        // Background
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createTexturedWidget(TEXTURE, startPoint.x, startPoint.y, 0, 0, 113, 63));

        // Fluid Slot
        int fluidX = startPoint.x + 36;
        int fluidY = startPoint.y + 5;

        FluidVariant fluidVariant = display.getFluid();
        Sprite sprite = FluidVariantRendering.getSprite(display.getFluid());
        if (sprite != null) {
            widgets.add(Widgets.createDrawableWidget((helper, mouseX, mouseY, delta) -> {

                int color = FluidVariantRendering.getColor(display.getFluid());
                RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
                RenderSystem.setShaderTexture(0, sprite.getAtlasId());
                RenderSystem.setShaderColor(
                        ((color >> 16) & 0xFF) / 255f,
                        ((color >> 8) & 0xFF) / 255f,
                        (color & 0xFF) / 255f,
                        ((color >> 24) & 0xFF) / 255f
                );
                helper.drawSprite(fluidX, fluidY, 0, 16, 16, sprite);
                RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
            }));

            // Fluid Tooltip
            widgets.add(Widgets.createTooltip(new Rectangle(fluidX, fluidY, 16, 16),
                    List.of(fluidVariant
                            .getFluid()
                            .getDefaultState()
                            .getBlockState()
                            .getBlock()
                            .getName(), Text.literal(FluidUtils.convertDropletsToMb(display.getAmount()) + "/6000 mB").formatted(Formatting.GRAY))));

            // Arrow
            widgets.add(Widgets.createArrow(new Point(startPoint.x + 53, startPoint.y + 28)).animationDurationTicks(500));
        }

        // Time Icon
        int timeIconX = startPoint.x + 70;
        int timeIconY = startPoint.y + 17;
        widgets.add(Widgets.createTexturedWidget(TEXTURE, timeIconX, timeIconY, 135, 0, 9, 12));

        // Time Icon Tooltip
        String timeText = formatTime(display.getCraftTime());
        widgets.add(Widgets.createTooltip(
                new Rectangle(timeIconX, timeIconY, 9, 12),
                Text.translatable("recipe.resprouted.drying.time", timeText)
        ));

        // Output Slot
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 82, startPoint.y + 28))
                .entries(display.getOutputEntries().getFirst())
                .disableBackground()
                .markOutput());


        return widgets;
    }

    private String formatTime(int ticks) {
        double seconds = ticks / 20.0;

        if (seconds < 60) {
            // Less than 1 minute = show seconds
            if (seconds == (int) seconds) {
                return String.format("%ds", (int) seconds);
            } else {
                return String.format("%.1fs", seconds);
            }
        } else {
            // 1 minute or more = show minutes and seconds
            int minutes = (int) (seconds / 60);
            int remainingSeconds = (int) (seconds % 60);
            if (remainingSeconds == 0) {
                return String.format("%dm", minutes);
            } else {
                return String.format("%dm %ds", minutes, remainingSeconds);
            }
        }
    }
}

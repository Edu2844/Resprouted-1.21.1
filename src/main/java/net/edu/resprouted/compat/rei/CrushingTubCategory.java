package net.edu.resprouted.compat.rei;

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
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.fluid.Fluid;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import java.util.LinkedList;
import java.util.List;

public class CrushingTubCategory implements DisplayCategory<CrushingTubDisplay> {
    public static final Identifier TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/gui/rei/crushing_tub_gui.png");
    public static final CategoryIdentifier<CrushingTubDisplay> ID = CategoryIdentifier.of(Resprouted.MOD_ID, "crushing_tub");

    @Override
    public CategoryIdentifier<? extends CrushingTubDisplay> getCategoryIdentifier() {
        return ID;
    }
    @Override
    public Text getTitle() {
        return Text.translatable("block.resprouted.crushing_tub");
    }
    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.CRUSHING_TUB);
    }
    @Override
    public int getDisplayHeight() {
        return 60;
    }
    @Override
    public List<Widget> setupDisplay(CrushingTubDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 52, bounds.getCenterY() - 22);
        List<Widget> widgets = new LinkedList<>();

        //Fondo
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createTexturedWidget(TEXTURE, startPoint.x, startPoint.y,0, 0, 130, 57));

        //Slot input (sin fondo)
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 19, startPoint.y + 7))
                .entries(display.getInputEntries().getFirst())
                .disableBackground()
                .markInput());

        //Slot output (si existe)
        if (!display.getOutputEntries().isEmpty()) {
            boolean hasValidFluid = display.getFluid() != null &&
                    FluidVariantRendering.getSprite(display.getFluid()) != null;

            int slotX = startPoint.x + 82;
            int slotY = hasValidFluid ? startPoint.y + 5 : startPoint.y + 15;

            widgets.add(Widgets.createSlot(new Point(slotX, slotY))
                    .entries(display.getOutputEntries().getFirst())

                    .markOutput());
        }
        if (display.getFluid() != null) {
            FluidVariant fluidVariant = display.getFluid();
            Fluid fluid = fluidVariant.getFluid();
            long amount = display.getAmount();
            String amountText = amount + " mB";

            Sprite sprite = FluidVariantRendering.getSprite(fluidVariant);
            if (sprite != null && sprite.getAtlasId() != null) {
                // Determinar posición basada en si hay output item
                boolean hasOutputItem = !display.getOutputEntries().isEmpty();
                int fluidX = startPoint.x + 82;
                int fluidY = hasOutputItem ? startPoint.y + 25 : startPoint.y + 15;
                int tooltipY = hasOutputItem ? startPoint.y + 25 : startPoint.y + 15;

                // Renderizado del fluido
                widgets.add(Widgets.createDrawableWidget((helper, mouseX, mouseY, delta) -> {
                    helper.drawTexture(Identifier.of("textures/gui/sprites/container/slot.png"),
                            fluidX - 1, fluidY - 1, 0, 0, 18, 18, 18, 18);
                    int color = FluidVariantRendering.getColor(fluidVariant);

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

                // Tooltip
                widgets.add(Widgets.createTooltip(
                        new Rectangle(fluidX, tooltipY, 16, 16),
                        List.of(
                                fluid.getDefaultState().getBlockState().getBlock().getName(),
                                Text.literal(amountText).formatted(Formatting.GRAY)
                        )
                ));
            }
        }
        return widgets;
    }
}

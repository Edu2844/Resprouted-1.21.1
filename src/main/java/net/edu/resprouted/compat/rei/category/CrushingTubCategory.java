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
import net.edu.resprouted.compat.rei.display.CrushingTubDisplay;
import net.edu.resprouted.util.FluidUtils;
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
    public static final Identifier TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/gui/recipe/crushing_tub_recipe.png");
    public static final CategoryIdentifier<CrushingTubDisplay> ID = CategoryIdentifier.of(Resprouted.MOD_ID, "plugin/crushing");

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
    public int getDisplayWidth(CrushingTubDisplay display) {
        return 110;
    }
    @Override
    public List<Widget> setupDisplay(CrushingTubDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 52, bounds.getCenterY() - 22);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createRecipeBase(bounds));

        widgets.add(Widgets.createTexturedWidget(TEXTURE, startPoint.x, startPoint.y,0, 0, 130, 57));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 19, startPoint.y + 7))
                .entries(display.getInputEntries().getFirst())
                .disableBackground()
                .markInput());


        if (!display.getOutputEntries().isEmpty()) {
            boolean hasValidFluid = display.getFluid() != null && FluidVariantRendering.getSprite(display.getFluid()) != null;

            int slotX = startPoint.x + 82;
            int slotY = hasValidFluid ? startPoint.y + 5 : startPoint.y + 15;

            widgets.add(Widgets.createSlot(new Point(slotX, slotY)).entries(display.getOutputEntries().getFirst()).markOutput());
        }

        if (display.getFluid() != null) {

            Sprite sprite = FluidVariantRendering.getSprite(display.getFluid());
            if (sprite != null) {
                boolean hasOutputItem = !display.getOutputEntries().isEmpty();
                int fluidX = startPoint.x + 82;
                int fluidY = hasOutputItem ? startPoint.y + 25 : startPoint.y + 15;
                int tooltipY = hasOutputItem ? startPoint.y + 25 : startPoint.y + 15;

                widgets.add(Widgets.createSlotBackground(new Point(fluidX, fluidY)));

                //Fluid
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

                //Fluid Tooltip
                widgets.add(Widgets.createTooltip(
                        new Rectangle(fluidX, tooltipY, 16, 16),
                        List.of(display.getFluid().getFluid()
                                .getDefaultState()
                                .getBlockState()
                                .getBlock()
                                .getName(), Text.literal(FluidUtils.convertDropletsToMb(display.getAmount()) + "/8000 mB").formatted(Formatting.GRAY))
                ));
            }
        }

        return widgets;
    }
}

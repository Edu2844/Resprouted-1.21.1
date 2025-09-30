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
import net.edu.resprouted.compat.rei.display.EvaporatingBasinDisplay;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class EvaporatingBasinCategory implements DisplayCategory<EvaporatingBasinDisplay> {
    public static final Identifier TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/gui/recipe/evaporating_basin_recipe.png");
    public static final CategoryIdentifier<EvaporatingBasinDisplay> ID = CategoryIdentifier.of(Resprouted.MOD_ID, "plugin/evaporating");

    @Override
    public CategoryIdentifier<? extends EvaporatingBasinDisplay> getCategoryIdentifier() {
        return ID;
    }
    @Override
    public Text getTitle() {
        return Text.translatable("block.resprouted.evaporating_basin");
    }
    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.EVAPORATING_BASIN);
    }
    @Override
    public int getDisplayHeight() {
        return 75;
    }
    @Override
    public int getDisplayWidth(EvaporatingBasinDisplay display) {
        return 110;
    }
    @Override
    public List<Widget> setupDisplay(EvaporatingBasinDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 52, bounds.getCenterY() - 22);
        List<Widget> widgets = new LinkedList<>();

        //Background
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createTexturedWidget(TEXTURE, startPoint.x, startPoint.y, 0, 0, 113, 63));

        //Fluid Slot
        int fluidX = startPoint.x + 36;
        int fluidY = startPoint.y + 5;

        FluidVariant fluidVariant = display.getFluid();
        Sprite sprite = FluidVariantRendering.getSprite(display.getFluid());
        if (sprite != null) {
            widgets.add(Widgets.createSlotBackground(new Point(fluidX, fluidY)));
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
            widgets.add(Widgets.createTooltip(new Rectangle(fluidX, fluidY, 16, 16),
                    List.of(fluidVariant
                            .getFluid()
                            .getDefaultState()
                            .getBlockState()
                            .getBlock()
                            .getName(), Text.literal(display.getAmount() + "/6000 mB").formatted(Formatting.GRAY))));

            //Arrow
            widgets.add(Widgets.createArrow(new Point(startPoint.x + 51, startPoint.y + 28)).animationDurationTicks(500));
        }

        //Output Slot
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 80, startPoint.y + 25))
                .entries(display.getOutputEntries().getFirst())
                .markOutput());


        return widgets;
    }
}

package net.edu.resprouted.compat.rei.category;

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
import net.edu.resprouted.compat.rei.display.BrewingBarrelDisplay;
import net.edu.resprouted.util.RenderUtils;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.texture.Sprite;
import net.minecraft.fluid.Fluid;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class BrewingBarrelCategory implements DisplayCategory<BrewingBarrelDisplay> {
    public static final Identifier TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/gui/recipe/brewing_barrel_recipe.png");
    public static final CategoryIdentifier<BrewingBarrelDisplay> ID = CategoryIdentifier.of(Resprouted.MOD_ID, "plugin/brewing_barrel");

    @Override
    public CategoryIdentifier<? extends BrewingBarrelDisplay> getCategoryIdentifier() {
        return ID;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("recipe.resprouted.brewing");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.BREWING_BARREL);
    }

    @Override
    public int getDisplayHeight() {
        return 65;
    }

    @Override
    public List<Widget> setupDisplay(BrewingBarrelDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 57, bounds.getCenterY() - 20);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createTexturedWidget(TEXTURE, startPoint.x, startPoint.y, 0, 0, 114, 40));

        // Input tank
        widgets.add(createFluidSlot(display.getInputFluid(), startPoint.x + 40, startPoint.y + 4,16,32));

        // Aux tank
        widgets.add(createFluidSlot(display.getOutputFluid(), startPoint.x + 4, startPoint.y + 12,16,16));

        // Arrow
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 63, startPoint.y + 11)).animationDurationTicks(500));

        // Output tank
        widgets.add(createFluidSlot(display.getOutputFluid(), startPoint.x + 94, startPoint.y + 4,16,32));

        widgets.add(Widgets.createTooltip(
                new Rectangle(startPoint.x + 8, startPoint.y + 31, 7, 7),
                List.of(Text.translatable("recipe.resprouted.brewing_barrel_tooltip"))
        ));
        return widgets;
    }

    @SuppressWarnings("all")
    private Widget createFluidSlot(Fluid fluid, int x, int y, int width, int height) {
        FluidVariant variant = FluidVariant.of(fluid);

        Widget fluidWidget = Widgets.createDrawableWidget((graphics, mouseX, mouseY, delta) -> {
            Sprite sprite = FluidVariantRendering.getSprite(variant);
            if (sprite != null) {
                int color = FluidVariantRendering.getColor(variant);
                float red = ((color >> 16) & 0xFF) / 255f;
                float green = ((color >> 8) & 0xFF) / 255f;
                float blue = (color & 0xFF) / 255f;
                float alpha = ((color >> 24) & 0xFF) / 255f;

                RenderUtils.renderTiledSprite(graphics, sprite, x, y, width, height, red, green, blue, alpha);
            }
        });
        Widget tooltipWidget = Widgets.createTooltip(
                new Rectangle(x, y, width, height),
                FluidVariantRendering.getTooltip(variant)
        );
        return Widgets.concat(List.of(fluidWidget, tooltipWidget));
    }
}

package net.edu.resprouted.compat.rei.category;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.custom.decorative.UrnBlock;
import net.edu.resprouted.compat.rei.display.UrnColoringDisplay;
import net.minecraft.text.Text;

import java.util.LinkedList;
import java.util.List;

public class UrnColoringCategory implements DisplayCategory<UrnColoringDisplay> {
    public static final CategoryIdentifier<UrnColoringDisplay> ID = CategoryIdentifier.of(Resprouted.MOD_ID, "plugin/urn_coloring");

    @Override
    public CategoryIdentifier<? extends UrnColoringDisplay> getCategoryIdentifier() {
        return ID;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("recipe.resprouted.urn_coloring");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(UrnBlock.get(null));
    }

    @Override
    public List<Widget> setupDisplay(UrnColoringDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 58, bounds.getCenterY() - 27);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createRecipeBase(bounds));

        List<EntryIngredient> inputEntries = display.getInputEntries();

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 1, startPoint.y + 1)).entries(inputEntries.get(0)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 19, startPoint.y + 1)).entries(inputEntries.get(1)).markInput());

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 37, startPoint.y + 1)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 1, startPoint.y + 19)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 19, startPoint.y + 19)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 37, startPoint.y + 19)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 1, startPoint.y + 37)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 19, startPoint.y + 37)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 37, startPoint.y + 37)));

        widgets.add(Widgets.createArrow(new Point(startPoint.x + 60, startPoint.y + 18)).disableAnimation());

        if (!display.getOutputEntries().isEmpty()) {
            widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 95, startPoint.y + 19)));
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 95, startPoint.y + 19))
                    .entries(display.getOutputEntries().getFirst())
                    .disableBackground()
                    .markOutput());
        }

        return widgets;
    }
}

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
import net.edu.resprouted.block.custom.decorative.JarBlock;
import net.edu.resprouted.compat.rei.display.JarColoringDisplay;
import net.minecraft.text.Text;

import java.util.LinkedList;
import java.util.List;


public class JarColoringCategory implements DisplayCategory<JarColoringDisplay> {
    public static final CategoryIdentifier<JarColoringDisplay> ID = CategoryIdentifier.of(Resprouted.MOD_ID, "plugin/jar_coloring");

    @Override
    public CategoryIdentifier<? extends JarColoringDisplay> getCategoryIdentifier() {
        return ID;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("recipe.resprouted.jar_coloring");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(JarBlock.get(null));
    }

    @Override
    public List<Widget> setupDisplay(JarColoringDisplay display, Rectangle bounds) {
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

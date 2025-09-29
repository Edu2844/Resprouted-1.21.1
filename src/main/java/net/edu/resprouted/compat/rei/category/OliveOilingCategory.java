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
import net.edu.resprouted.compat.rei.display.OliveOilingDisplay;
import net.edu.resprouted.item.ModItems;
import net.minecraft.text.Text;

import java.util.LinkedList;
import java.util.List;

public class OliveOilingCategory implements DisplayCategory<OliveOilingDisplay> {
    public static final CategoryIdentifier<OliveOilingDisplay> ID = CategoryIdentifier.of(Resprouted.MOD_ID, "plugin/oiling");

    @Override
    public CategoryIdentifier<? extends OliveOilingDisplay> getCategoryIdentifier() {
        return ID;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("rei.resprouted.oiling");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModItems.OLIVE_OIL_BOTTLE); // Icono más representativo
    }


    @Override
    public List<Widget> setupDisplay(OliveOilingDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 58, bounds.getCenterY() - 27);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createRecipeBase(bounds));

        List<EntryIngredient> inputEntries = display.getInputEntries();
        if (inputEntries.size() >= 2) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 1, startPoint.y + 10))
                    .entries(inputEntries.get(0))
                    .markInput());

            widgets.add(Widgets.createSlot(new Point(startPoint.x + 19, startPoint.y + 10))
                    .entries(inputEntries.get(1))
                    .markInput());
        }

        widgets.add(Widgets.createArrow(new Point(startPoint.x + 45, startPoint.y + 9))
                .animationDurationTicks(20));

        if (!display.getOutputEntries().isEmpty()) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 80, startPoint.y + 9))
                    .entries(display.getOutputEntries().getFirst())
                    .disableBackground()
                    .markOutput());
        }

        widgets.add(Widgets.createTooltip(
                new Rectangle(bounds.x, bounds.y + bounds.height - 10, bounds.width, 10),
                List.of(Text.translatable("rei.resprouted.oiling_tooltip"))
        ));

        return widgets;
    }
}
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
import net.edu.resprouted.compat.rei.display.VantaOilingDisplay;
import net.edu.resprouted.item.ModItems;
import net.minecraft.text.Text;


import java.util.LinkedList;
import java.util.List;

public class VantaOilingCategory implements DisplayCategory<VantaOilingDisplay> {
    public static final CategoryIdentifier<VantaOilingDisplay> ID = CategoryIdentifier.of(Resprouted.MOD_ID, "plugin/vanta_oiling");

    @Override
    public CategoryIdentifier<? extends VantaOilingDisplay> getCategoryIdentifier() {
        return ID;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("rei.resprouted.vanta_oiling");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModItems.VANTA_OIL_BOTTLE);
    }

    @Override
    public List<Widget> setupDisplay(VantaOilingDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 58, bounds.getCenterY() - 27);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createRecipeBase(bounds));

        List<EntryIngredient> inputEntries = display.getInputEntries();
        int numPotions = display.getNumPotions();

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 1, startPoint.y + 1))
                .entries(inputEntries.get(0)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 19, startPoint.y + 1))
                .entries(inputEntries.get(1)).markInput());

        Point[] potionSlots = {
                new Point(startPoint.x + 37, startPoint.y + 1),   // Slot 2
                new Point(startPoint.x + 1, startPoint.y + 19),   // Slot 3
                new Point(startPoint.x + 19, startPoint.y + 19),  // Slot 4
                new Point(startPoint.x + 37, startPoint.y + 19),  // Slot 5
                new Point(startPoint.x + 1, startPoint.y + 37),   // Slot 6
                new Point(startPoint.x + 19, startPoint.y + 37),  // Slot 7
                new Point(startPoint.x + 37, startPoint.y + 37)   // Slot 8
        };

        for (int i = 0; i < potionSlots.length; i++) {
            int slotIndex = i + 2;
            if (numPotions >= i + 1) {
                widgets.add(Widgets.createSlot(potionSlots[i])
                        .entries(inputEntries.get(slotIndex)).markInput());
            } else {
                widgets.add(Widgets.createSlot(potionSlots[i]));
            }
        }

        widgets.add(Widgets.createArrow(new Point(startPoint.x + 60, startPoint.y + 18)).disableAnimation());

        if (!display.getOutputEntries().isEmpty()) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 95, startPoint.y + 19))
                    .entries(display.getOutputEntries().getFirst())
                    .disableBackground().markOutput());
            widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 95, startPoint.y + 19)));
        }

        return widgets;
    }
}

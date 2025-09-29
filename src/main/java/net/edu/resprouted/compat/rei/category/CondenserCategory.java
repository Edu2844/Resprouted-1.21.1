package net.edu.resprouted.compat.rei.category;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.BurningFire;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.compat.rei.display.CondenserDisplay;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


import java.util.LinkedList;
import java.util.List;

public class CondenserCategory implements DisplayCategory<CondenserDisplay> {
    public static final Identifier TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/gui/recipe/condenser_recipe.png");
    public static final Identifier ARROW = Identifier.of(Resprouted.MOD_ID, "textures/gui/sprites/condenser_progress.png");
    public static final CategoryIdentifier<CondenserDisplay> ID = CategoryIdentifier.of(Resprouted.MOD_ID, "plugin/condenser");

    @Override
    public CategoryIdentifier<? extends CondenserDisplay> getCategoryIdentifier() {
        return ID;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("block.resprouted.condenser");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.CONDENSER);
    }

    @Override
    public int getDisplayHeight() {
        return 75;
    }

    @Override
    public List<Widget> setupDisplay(CondenserDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 65, bounds.getCenterY() - 31);
        List<Widget> widgets = new LinkedList<>();


        widgets.add(Widgets.createRecipeBase(bounds));

        widgets.add(Widgets.createTexturedWidget(TEXTURE, startPoint.x, startPoint.y, 0, 0, 130, 79));

        widgets.add(Widgets.createDrawableWidget((graphics, mouseX, mouseY, delta) -> {
            assert MinecraftClient.getInstance().world != null;
            long currentTime = MinecraftClient.getInstance().world.getTime();
            int ticksPassed = (int) (currentTime % 210);
            int arrowAnimationWidth = (ticksPassed * 51) / 210;

            graphics.drawTexture(ARROW, startPoint.x + 21, startPoint.y + 25, 0, 0, arrowAnimationWidth, 28, 50, 28);
        }));

        widgets.add(Widgets.createTooltip(
                new Rectangle(startPoint.x + 110, startPoint.y + 24, 16, 32),
                Text.translatable("block.minecraft.water"),
                Text.literal("125/8000 mB")
        ));

        BurningFire fire = Widgets.createBurningFire(new Point(startPoint.x + 44, startPoint.y + 45));
        fire.animationDurationMS(42000);
        widgets.add(fire);

        if (display.getInputEntries().size() >= 2) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 4, startPoint.y + 20))
                    .entries(display.getInputEntries().get(0))
                    .disableBackground()
                    .markInput());

            widgets.add(Widgets.createSlot(new Point(startPoint.x + 4, startPoint.y + 44))
                    .entries(display.getInputEntries().get(1))
                    .disableBackground()
                    .markInput());
        }

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 82, startPoint.y + 4))
                .entry(EntryStacks.of(Items.GLASS_BOTTLE))
                .disableBackground()
                .markInput());

        if (!display.getOutputEntries().isEmpty()) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 82, startPoint.y + 32))
                    .entries(display.getOutputEntries().getFirst())
                    .disableBackground()
                    .markOutput());
        }


        return widgets;
    }

}

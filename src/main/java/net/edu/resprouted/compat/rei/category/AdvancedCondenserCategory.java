package net.edu.resprouted.compat.rei.category;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.BurningFire;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.compat.rei.display.AdvancedCondenserDisplay;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class AdvancedCondenserCategory implements DisplayCategory<AdvancedCondenserDisplay> {

    public static final Identifier TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/gui/recipe/advanced_condenser_recipe.png");
    public static final Identifier ARROW = Identifier.of(Resprouted.MOD_ID, "textures/gui/sprites/advanced_condenser_progress.png");
    public static final CategoryIdentifier<AdvancedCondenserDisplay> ID = CategoryIdentifier.of(Resprouted.MOD_ID, "plugin/advanced_alchemy");

    @Override
    public CategoryIdentifier<? extends AdvancedCondenserDisplay> getCategoryIdentifier() {
        return ID;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("recipe.resprouted.advanced_alchemy");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.ADVANCED_CONDENSER);
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }

    @Override
    public List<Widget> setupDisplay(AdvancedCondenserDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 65, bounds.getCenterY() - 39);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createTexturedWidget(TEXTURE, startPoint.x, startPoint.y, 0, 0, 130, 76));

        widgets.add(Widgets.createDrawableWidget((graphics, mouseX, mouseY, delta) -> {
            assert MinecraftClient.getInstance().world != null;
            long currentTime = MinecraftClient.getInstance().world.getTime();
            int ticksPassed = (int) (currentTime % 210);
            int arrowAnimationWidth = (ticksPassed * 51) / 210;
            graphics.drawTexture(ARROW, startPoint.x + 21, startPoint.y + 13, 0, 0, arrowAnimationWidth, 53, 50, 53);
        }));

        BurningFire fire = Widgets.createBurningFire(new Point(startPoint.x + 44, startPoint.y + 43));
        fire.animationDurationTicks(400);
        widgets.add(fire);

        widgets.add(Widgets.createTooltip(new Rectangle(startPoint.x + 110, startPoint.y + 24, 16, 32),
                Text.translatable("block.minecraft.water"),
                Text.literal("125/8000 mB").formatted(Formatting.GRAY)
        ));

        List<EntryIngredient> inputs = display.getInputEntries();
        int ingredientCount = inputs.size();

        if (display.getModifier().isPresent()) {
            ingredientCount--;
        }

        if (ingredientCount >= 1) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 4, startPoint.y + 8)).entries(inputs.getFirst()).disableBackground().markInput());
        }

        if (ingredientCount >= 2) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 4, startPoint.y + 32)).entries(inputs.get(1)).disableBackground().markInput());
        }

        if (ingredientCount >= 3) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 4, startPoint.y + 56)).entries(inputs.get(2)).disableBackground().markInput());
        }

        if (display.getModifier().isPresent()) {

            int modifierIndex = inputs.size() - 1;
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 43, startPoint.y + 4))
                    .entries(inputs.get(modifierIndex))
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
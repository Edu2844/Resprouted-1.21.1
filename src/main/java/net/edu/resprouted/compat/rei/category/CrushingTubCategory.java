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
import net.edu.resprouted.recipe.helper.RecipeOutput;
import net.edu.resprouted.util.FluidUtils;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.texture.Sprite;
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
        return Text.translatable("recipe.resprouted.crushing");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.CRUSHING_TUB);
    }

    @Override
    public int getDisplayHeight() {
        return 119;
    }

    @Override
    public List<Widget> setupDisplay(CrushingTubDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 56, bounds.getCenterY() - 59);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createTexturedWidget(TEXTURE, startPoint.x, startPoint.y, 0, 0, 113, 119));

        // Input slot
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 22, startPoint.y + 41))
                .entries(display.getInputEntries().getFirst())
                .disableBackground()
                .markInput());

        List<RecipeOutput> outputs = display.getOutputs();
        boolean hasFluid = display.getFluid() != null && FluidVariantRendering.getSprite(display.getFluid()) != null;

        int totalOutputs = outputs.size() + (hasFluid ? 1 : 0);

        if (totalOutputs == 0) {
            return widgets;
        }

        int startY = switch (totalOutputs) {
            case 2 -> 40;
            case 3 -> 31;
            case 4 -> 20;
            case 5 -> 11;
            default -> 50;
        };

        int currentSlot = 0;
        int slotSpacing = 21;
        int outputX = 88;

        // Fluid output (always first if present)
        if (hasFluid) {
            Point fluidPoint = new Point(startPoint.x + outputX, startPoint.y + startY);

            // Slot background
            widgets.add(Widgets.createDrawableWidget((helper, mouseX, mouseY, delta) -> helper.drawTexture(TEXTURE, fluidPoint.x - 1, fluidPoint.y - 1, 113, 0, 18, 18)));

            Sprite sprite = FluidVariantRendering.getSprite(display.getFluid());
            if (sprite != null) {
                int color = FluidVariantRendering.getColor(display.getFluid());

                // Fluid render
                widgets.add(Widgets.createDrawableWidget((helper, mouseX, mouseY, delta) -> {
                    RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
                    RenderSystem.setShaderTexture(0, sprite.getAtlasId());
                    RenderSystem.setShaderColor(
                            ((color >> 16) & 0xFF) / 255f,
                            ((color >> 8) & 0xFF) / 255f,
                            (color & 0xFF) / 255f,
                            ((color >> 24) & 0xFF) / 255f
                    );
                    helper.drawSprite(fluidPoint.x, fluidPoint.y, 0, 16, 16, sprite);
                    RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
                }));

                // Fluid tooltip
                widgets.add(Widgets.createTooltip(
                        new Rectangle(fluidPoint.x, fluidPoint.y, 16, 16),
                        display.getFluid().getFluid().getDefaultState().getBlockState().getBlock().getName(),
                        Text.literal(FluidUtils.convertDropletsToMb(display.getAmount()) + "/8000 mB").formatted(Formatting.GRAY)
                ));
            }

            currentSlot++;
        }

        // Item outputs
        for (RecipeOutput output : outputs) {
            if (!output.stack().isEmpty()) {
                int itemY = startY + (currentSlot * slotSpacing);
                Point itemPoint = new Point(startPoint.x + outputX, startPoint.y + itemY);

                boolean isChance = output.chance() < 1.0f;

                // Slot background
                int slotTextureY = isChance ? 18 : 0;
                widgets.add(Widgets.createDrawableWidget((helper, mouseX, mouseY, delta) -> helper.drawTexture(TEXTURE, itemPoint.x - 1, itemPoint.y - 1, 113, slotTextureY, 18, 18)));

                var entryStack = EntryStacks.of(output.stack());
                if (isChance) {
                    float chance = output.chance() * 100;
                    String formattedChance = chance % 1 == 0 ? String.format("%d%%", (int) chance) : String.format("%.1f%%", chance);
                    entryStack = entryStack.tooltip(Text.translatable("recipe.resprouted.crushing.chance", formattedChance).formatted(Formatting.GOLD));
                }
                // Item slot
                widgets.add(Widgets.createSlot(itemPoint).entries(List.of(entryStack)).disableBackground().markOutput());
                currentSlot++;
            }
        }

        return widgets;
    }
}
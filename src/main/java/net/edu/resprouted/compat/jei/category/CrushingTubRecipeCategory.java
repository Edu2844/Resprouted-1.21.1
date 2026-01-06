package net.edu.resprouted.compat.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.compat.jei.JEIPlugin;
import net.edu.resprouted.recipe.custom.CrushingTubRecipe;
import net.edu.resprouted.recipe.helper.RecipeOutput;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("removal")
public class CrushingTubRecipeCategory implements IRecipeCategory<CrushingTubRecipe> {
    private static final IDrawable BASIC_SLOT;
    private static final IDrawable CHANCE_SLOT;
    private final IDrawable background;
    private final IDrawable icon;

    static {
        Identifier texture = Identifier.of(Resprouted.MOD_ID, "textures/gui/recipe/crushing_tub_recipe.png");

        BASIC_SLOT = new IDrawable() {
            @Override
            public int getWidth() {
                return 18;
            }

            @Override
            public int getHeight() {
                return 18;
            }

            @Override
            public void draw(DrawContext graphics, int xOffset, int yOffset) {
                graphics.drawTexture(texture, xOffset, yOffset, 113, 0, 18, 18, 256, 256);
            }
        };

        CHANCE_SLOT = new IDrawable() {
            @Override
            public int getWidth() {
                return 18;
            }

            @Override
            public int getHeight() {
                return 18;
            }

            @Override
            public void draw(DrawContext graphics, int xOffset, int yOffset) {
                graphics.drawTexture(texture, xOffset, yOffset, 113, 18, 18, 18, 256, 256);
            }
        };
    }

    public CrushingTubRecipeCategory(IGuiHelper helper) {
        Identifier texture = Identifier.of(Resprouted.MOD_ID, "textures/gui/recipe/crushing_tub_recipe.png");
        this.background = helper.createDrawable(texture, 0, 0, 113, 119);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.CRUSHING_TUB));
    }

    @NotNull
    @Override
    public RecipeType<CrushingTubRecipe> getRecipeType() {
        return JEIPlugin.CRUSHING_TUB;
    }

    @NotNull
    @Override
    public Text getTitle() {
        return Text.translatable("recipe.resprouted.crushing");
    }

    @Override
    public int getWidth() {
        return 113;
    }

    @Override
    public int getHeight() {
        return 119;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CrushingTubRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 22, 41).addIngredients(recipe.inputItem());

        List<RecipeOutput> outputs = recipe.outputItems();
        boolean hasFluid = recipe.fluidOutput() != null && !recipe.fluidOutput().isBlank();

        int totalOutputs = outputs.size() + (hasFluid ? 1 : 0);

        if (totalOutputs == 0) {
            return;
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

        if (hasFluid) {
            builder.addSlot(RecipeIngredientRole.OUTPUT, outputX, startY)
                    .setBackground(BASIC_SLOT, -1, -1)
                    .setFluidRenderer(8000, true, 16, 16)
                    .addFluidStack(recipe.fluidOutput().getFluid(), recipe.fluidAmount());
            currentSlot++;
        }

        for (RecipeOutput output : outputs) {
            if (!output.stack().isEmpty()) {
                int itemY = startY + (currentSlot * slotSpacing);
                IRecipeSlotBuilder slot = builder.addSlot(RecipeIngredientRole.OUTPUT, outputX, itemY)
                        .setBackground(output.chance() < 1.0f ? CHANCE_SLOT : BASIC_SLOT, -1, -1)
                        .addItemStack(output.stack());

                if (output.chance() < 1.0f) {
                    slot.addTooltipCallback((slotView, tooltip) -> {
                        float chance = output.chance() * 100;
                        String formattedChance = chance % 1 == 0 ? String.format("%d%%", (int) chance) : String.format("%.1f%%", chance);
                        tooltip.add(Text.translatable("recipe.resprouted.crushing.chance", formattedChance).formatted(Formatting.GOLD));
                    });
                }

                currentSlot++;
            }
        }
    }

    @Override
    public void draw(CrushingTubRecipe recipe, IRecipeSlotsView recipeSlotsView, DrawContext guiGraphics, double mouseX, double mouseY) {
        background.draw(guiGraphics,0,0);
    }
}
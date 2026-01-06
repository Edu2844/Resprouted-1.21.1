package net.edu.resprouted.compat.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.recipe.custom.DryingBasinRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import net.minecraft.client.gui.DrawContext;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.edu.resprouted.compat.jei.JEIPlugin;

import java.util.List;

@SuppressWarnings("removal")
public class DryingBasinRecipeCategory implements IRecipeCategory<DryingBasinRecipe> {
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrow;
    private final IDrawable timeIcon;

    public DryingBasinRecipeCategory(IGuiHelper helper) {
        Identifier texture = Identifier.of(Resprouted.MOD_ID, "textures/gui/recipe/drying_basin_recipe.png");
        background = helper.createDrawable(texture, 0, 0, 113, 63);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.DRYING_BASIN));
        arrow = helper.drawableBuilder(texture, 113, 0, 22, 15).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
        timeIcon = helper.createDrawable(texture, 135, 0, 9, 12);
    }

    @Override
    public @NotNull RecipeType<DryingBasinRecipe> getRecipeType() {
        return JEIPlugin.DRYING_BASIN;
    }

    @Override
    public @NotNull Text getTitle() {
        return Text.translatable("recipe.resprouted.drying");
    }

    @Override
    public int getWidth() {
        return 113;
    }

    @Override
    public int getHeight() {
        return 63;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DryingBasinRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 36 , 5)
                .setFluidRenderer(6000, true, 16, 16)
                .addFluidStack(recipe.fluidInput().getFluid(), recipe.fluidCost());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 82, 28)
                .addItemStack(recipe.output());
    }

    @Override
    public void draw(DryingBasinRecipe recipe, IRecipeSlotsView recipeSlotsView, DrawContext guiGraphics, double mouseX, double mouseY) {
        background.draw(guiGraphics, 0, 0);
        arrow.draw(guiGraphics, 54, 28);
        timeIcon.draw(guiGraphics, 70, 17);
    }

    @Override
    public @NotNull List<Text> getTooltipStrings(DryingBasinRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        if (mouseX >= 70 && mouseX <= 79 && mouseY >= 17 && mouseY <= 29) {
            int ticks = recipe.craftTime();
            String timeText = formatTime(ticks);
            return List.of(Text.translatable("recipe.resprouted.drying.time", timeText));
        }
        return List.of();
    }

    private String formatTime(int ticks) {
        double seconds = ticks / 20.0;

        if (seconds < 60) {
            // Less than 1 minute = show seconds
            if (seconds == (int) seconds) {
                return String.format("%ds", (int) seconds);
            } else {
                return String.format("%.1fs", seconds);
            }
        } else {
            // 1 minute or more = show minutes and seconds
            int minutes = (int) (seconds / 60);
            int remainingSeconds = (int) (seconds % 60);
            if (remainingSeconds == 0) {
                return String.format("%dm", minutes);
            } else {
                return String.format("%dm %ds", minutes, remainingSeconds);
            }
        }
    }
}
package net.edu.resprouted.compat.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.recipe.custom.EvaporatingBasinRecipe;
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

public class EvaporatingBasinRecipeCategory implements IRecipeCategory<EvaporatingBasinRecipe> {
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrow;
    private final int offsetX = 0;
    private final int offsetY = 0;

    public EvaporatingBasinRecipeCategory(IGuiHelper helper) {
        Identifier texture = Identifier.of(Resprouted.MOD_ID, "textures/gui/recipe/evaporating_basin_recipe.png");
        background = helper.createDrawable(texture, 0, 0, 113, 63);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.EVAPORATING_BASIN));
        arrow = helper.drawableBuilder(texture, 113, 0, 22, 15)
                .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }


    @Override
    public @NotNull RecipeType<EvaporatingBasinRecipe> getRecipeType() {
        return JEIPlugin.EVAPORATING_BASIN;
    }

    @Override
    public @NotNull Text getTitle() {
        return Text.translatable("block.resprouted.evaporating_basin");
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
    public void setRecipe(IRecipeLayoutBuilder builder, EvaporatingBasinRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 36 + offsetX, 5 + offsetY)
                .setFluidRenderer(6000, true, 16, 16)
                .addFluidStack(recipe.fluidInput().getFluid(), recipe.fluidCost());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 82 + offsetX, 28 + offsetY)
                .addItemStack(recipe.output());
    }

    @Override
    public void draw(EvaporatingBasinRecipe recipe, IRecipeSlotsView recipeSlotsView, DrawContext guiGraphics, double mouseX, double mouseY) {
        background.draw(guiGraphics, offsetX, offsetY);
        arrow.draw(guiGraphics, 50, 28);
    }
}
package net.edu.resprouted.compat.jei.category;

import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.edu.resprouted.recipe.custom.BrewingBarrelRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.compat.jei.JEIPlugin;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("removal")
public class BrewingBarrelRecipeCategory implements IRecipeCategory<BrewingBarrelRecipe> {
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrow;

    public BrewingBarrelRecipeCategory(IGuiHelper helper) {
        Identifier texture = Identifier.of(Resprouted.MOD_ID, "textures/gui/recipe/brewing_barrel_recipe.png");

        this.background = helper.createDrawable(texture, 0, 0, 114, 40);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.BREWING_BARREL));

        this.arrow = helper.drawableBuilder(texture, 114, 0, 22, 16)
                .buildAnimated(500, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @NotNull
    @Override
    public  RecipeType<BrewingBarrelRecipe> getRecipeType() {
        return JEIPlugin.BREWING_BARREL;
    }

    @NotNull
    @Override
    public Text getTitle() {
        return Text.translatable("recipe.resprouted.brewing");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, BrewingBarrelRecipe recipe, IFocusGroup focuses) {
        // Input tank
        builder.addSlot(RecipeIngredientRole.INPUT, 40, 4)
                .setFluidRenderer(8000, true, 16, 32)
                .addFluidStack(recipe.inputFluid(), 648000);

        // Aux tank
        builder.addSlot(RecipeIngredientRole.INPUT, 4, 12)
                .setFluidRenderer(1000, true, 16, 16)
                .addFluidStack(recipe.outputFluid(), 81000);

        // Output tank
        builder.addSlot(RecipeIngredientRole.OUTPUT, 94, 4)
                .setFluidRenderer(8000, true, 16, 32)
                .addFluidStack(recipe.outputFluid(), 648000);
    }

    @Override
    public void draw(BrewingBarrelRecipe recipe, IRecipeSlotsView recipeSlotsView, DrawContext guiGraphics, double mouseX, double mouseY) {
        background.draw(guiGraphics, 0, 0);
        arrow.draw(guiGraphics, 64, 11);
    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, BrewingBarrelRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        if (mouseX >= 8 && mouseX <= 15 && mouseY >= 31 && mouseY <= 38) {
            tooltip.add(Text.translatable("rei.resprouted.brewing_barrel_tooltip"));
        }
    }
}
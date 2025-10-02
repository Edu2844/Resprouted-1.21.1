package net.edu.resprouted.compat.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
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
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class CrushingTubRecipeCategory implements IRecipeCategory<CrushingTubRecipe> {
    private final IDrawable background;
    private final IDrawable icon;
    private final IGuiHelper helper;
    private final int offsetX = 5;
    private final int offsetY = 7;

    public CrushingTubRecipeCategory(IGuiHelper helper) {
        this.helper = helper;
        Identifier texture = Identifier.of(Resprouted.MOD_ID, "textures/gui/recipe/crushing_tub_recipe.png");
        this.background = helper.createDrawable(texture, 0, 0, 110, 60);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.CRUSHING_TUB));
    }

    @Override
    public @NotNull RecipeType<CrushingTubRecipe> getRecipeType() {
        return JEIPlugin.CRUSHING_TUB;
    }

    @Override
    public @NotNull Text getTitle() {
        return Text.translatable("block.resprouted.crushing_tub");
    }

    @Override
    public int getWidth() {
        return 110;
    }

    @Override
    public int getHeight() {
        return 60;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CrushingTubRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 20 + offsetX, 8 + offsetY)
                .addIngredients(recipe.inputItem());

        if (recipe.outputItem() != null && !recipe.outputItem().isEmpty()) {
            boolean hasFluid = recipe.fluidOutput() != null && !recipe.fluidOutput().isBlank();
            int outputY = hasFluid ? 6 : 16;

            builder.addSlot(RecipeIngredientRole.OUTPUT, 83 + offsetX, outputY + offsetY)
                    .setBackground(helper.getSlotDrawable(), -1, -1)
                    .addItemStack(recipe.outputItem());
        }

        if (recipe.fluidOutput() != null && !recipe.fluidOutput().isBlank()) {
            boolean hasOutputItem = recipe.outputItem() != null && !recipe.outputItem().isEmpty();
            int fluidY = hasOutputItem ? 26 : 16;

            builder.addSlot(RecipeIngredientRole.OUTPUT, 83 + offsetX, fluidY + offsetY)
                    .setBackground(helper.getSlotDrawable(), -1, -1)
                    .setFluidRenderer(8000, true, 16, 16)
                    .addFluidStack(recipe.fluidOutput().getFluid(), recipe.fluidAmount());
        }
    }

    @Override
    public void draw(CrushingTubRecipe recipe, IRecipeSlotsView recipeSlotsView, DrawContext guiGraphics, double mouseX, double mouseY) {
        background.draw(guiGraphics, offsetX, offsetY);
    }
}

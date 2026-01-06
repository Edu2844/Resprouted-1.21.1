package net.edu.resprouted.compat.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.compat.jei.JEIPlugin;
import net.edu.resprouted.recipe.custom.AdvancedCondenserRecipe;
import net.edu.resprouted.util.ElixirUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("removal")
public class AdvancedCondenserRecipeCategory implements IRecipeCategory<AdvancedCondenserRecipe> {
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrow;
    private final IDrawable fire;

    public AdvancedCondenserRecipeCategory(IGuiHelper helper) {
        Identifier texture = Identifier.of(Resprouted.MOD_ID, "textures/gui/recipe/advanced_condenser_recipe.png");

        background = helper.createDrawable(texture, 0, 0, 130, 76);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ADVANCED_CONDENSER));
        arrow = helper.drawableBuilder(texture, 130, 14, 50, 54).buildAnimated(210, IDrawableAnimated.StartDirection.LEFT, false);
        fire = helper.drawableBuilder(texture, 130, 0, 14, 14).buildAnimated(400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @NotNull
    @Override
    public RecipeType<AdvancedCondenserRecipe> getRecipeType() {
        return JEIPlugin.ADVANCED_CONDENSER;
    }

    @NotNull
    @Override
    public Text getTitle() {
        return Text.translatable("recipe.resprouted.advanced_alchemy");
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
    public void setRecipe(IRecipeLayoutBuilder builder, AdvancedCondenserRecipe recipe, IFocusGroup focuses) {
        int ingredientCount = recipe.ingredients().size();


        if (ingredientCount >= 1) {
            builder.addSlot(RecipeIngredientRole.INPUT, 4, 8)
                    .addIngredients(recipe.ingredients().getFirst());
        }
        if (ingredientCount >= 2) {
            builder.addSlot(RecipeIngredientRole.INPUT, 4, 32)
                    .addIngredients(recipe.ingredients().get(1));
        }
        if (ingredientCount >= 3) {
            builder.addSlot(RecipeIngredientRole.INPUT, 4, 56)
                    .addIngredients(recipe.ingredients().get(2));
        }

        if (recipe.modifier().isPresent()) {
            builder.addSlot(RecipeIngredientRole.INPUT, 43, 4)
                    .addIngredients(recipe.modifier().get());
        }

        builder.addSlot(RecipeIngredientRole.INPUT, 82, 4)
                .addItemStack(new ItemStack(Items.GLASS_BOTTLE));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 82, 32)
                .addItemStack(ElixirUtils.createElixir(recipe.effect(), recipe.duration(), recipe.amplifier()));
    }

    @Override
    public void draw(AdvancedCondenserRecipe recipe, IRecipeSlotsView recipeSlotsView, DrawContext guiGraphics, double mouseX, double mouseY) {
        background.draw(guiGraphics, 0, 0);
        arrow.draw(guiGraphics, 21, 13);
        fire.draw(guiGraphics, 44, 43);
    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, AdvancedCondenserRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        if (mouseX >= 110 && mouseX <= 126 && mouseY >= 24 && mouseY <= 56) {
            tooltip.add(Text.translatable("block.minecraft.water"));
            tooltip.add(Text.literal("125/8000 mB").formatted(Formatting.GRAY));
        }
    }
}
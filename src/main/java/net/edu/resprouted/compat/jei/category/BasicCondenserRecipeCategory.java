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
import net.edu.resprouted.recipe.custom.BasicCondenserRecipe;
import net.edu.resprouted.util.ElixirUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("removal")
public class BasicCondenserRecipeCategory implements IRecipeCategory<BasicCondenserRecipe> {
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrow;
    private final IDrawable fire;

    public BasicCondenserRecipeCategory(IGuiHelper helper) {
        Identifier texture = Identifier.of(Resprouted.MOD_ID, "textures/gui/recipe/condenser_recipe.png");

        this.background = helper.createDrawable(texture, 0, 0, 130, 64);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.BASIC_CONDENSER));

        this.arrow = helper.drawableBuilder(texture, 130, 14, 50, 28)
                .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);

        this.fire = helper.drawableBuilder(texture, 130, 0, 14, 14)
                .buildAnimated(800, IDrawableAnimated.StartDirection.TOP, true);
    }

    @NotNull
    @Override
    public RecipeType<BasicCondenserRecipe> getRecipeType() {
        return JEIPlugin.CONDENSER;
    }

    @NotNull
    @Override
    public Text getTitle() {
        return Text.translatable("recipe.resprouted.basic_alchemy");
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
    public void setRecipe(IRecipeLayoutBuilder builder, BasicCondenserRecipe recipe, IFocusGroup focuses) {
        if (!recipe.ingredients().isEmpty()) {
            builder.addSlot(RecipeIngredientRole.INPUT, 4, 20)
                    .addIngredients(recipe.ingredients().getFirst());
        }

        if (recipe.ingredients().size() >= 2) {
            builder.addSlot(RecipeIngredientRole.INPUT, 4, 44)
                    .addIngredients(recipe.ingredients().get(1));
        }

        builder.addSlot(RecipeIngredientRole.INPUT, 82, 4)
                .addItemStack(new ItemStack(Items.GLASS_BOTTLE));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 82, 32)
                .addItemStack(ElixirUtils.createElixir(recipe.effect(), recipe.duration(), recipe.amplifier()));
    }

    @Override
    public void draw(BasicCondenserRecipe recipe, IRecipeSlotsView recipeSlotsView, DrawContext guiGraphics, double mouseX, double mouseY) {
        background.draw(guiGraphics, 0, 0);
        arrow.draw(guiGraphics, 21, 25);
        fire.draw(guiGraphics, 44, 45);
    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, BasicCondenserRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        if (mouseX >= 110 && mouseX <= 126 && mouseY >= 24 && mouseY <= 56) {
            tooltip.add(Text.translatable("block.minecraft.water"));
            tooltip.add(Text.literal("125/8000 mB").formatted(Formatting.GRAY));
        }
    }
}
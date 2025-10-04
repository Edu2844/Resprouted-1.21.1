package net.edu.resprouted.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.compat.jei.category.*;
import net.edu.resprouted.compat.jei.recipes.ResproutedJEIRecipes;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.recipe.custom.*;
import net.edu.resprouted.screen.custom.AdvancedCondenserScreen;
import net.edu.resprouted.screen.custom.BasicCondenserScreen;
import net.edu.resprouted.screen.custom.BrewingBarrelScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("removal")
public class JEIPlugin implements IModPlugin {

    private static final Identifier ID = Identifier.of(Resprouted.MOD_ID, "jei_plugin");

    public static final RecipeType<CrushingTubRecipe> CRUSHING_TUB =
            RecipeType.create(Resprouted.MOD_ID, "crushing_tub", CrushingTubRecipe.class);

    public static final RecipeType<EvaporatingBasinRecipe> EVAPORATING_BASIN =
            RecipeType.create(Resprouted.MOD_ID, "evaporating_basin", EvaporatingBasinRecipe.class);

    public static final RecipeType<CondenserRecipe> CONDENSER =
            RecipeType.create(Resprouted.MOD_ID, "condenser", CondenserRecipe.class);

    public static final RecipeType<AdvancedCondenserRecipe> ADVANCED_CONDENSER =
            RecipeType.create(Resprouted.MOD_ID, "advanced_condenser", AdvancedCondenserRecipe.class);

    public static final RecipeType<BrewingBarrelRecipe> BREWING_BARREL =
            RecipeType.create(Resprouted.MOD_ID, "brewing_barrel", BrewingBarrelRecipe.class);


    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(CRUSHING_TUB, ResproutedJEIRecipes.getCrushingTubRecipes());
        registration.addRecipes(EVAPORATING_BASIN, ResproutedJEIRecipes.getEvaporatingBasinRecipes());
        registration.addRecipes(CONDENSER, ResproutedJEIRecipes.getCondenserRecipes());
        registration.addRecipes(ADVANCED_CONDENSER, ResproutedJEIRecipes.getAdvancedCondenserRecipes());
        registration.addRecipes(BREWING_BARREL, ResproutedJEIRecipes.getBrewingBarrelRecipes());

    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper helper = registry.getJeiHelpers().getGuiHelper();

        registry.addRecipeCategories(new CrushingTubRecipeCategory(helper));
        registry.addRecipeCategories(new EvaporatingBasinRecipeCategory(helper));
        registry.addRecipeCategories(new CondenserRecipeCategory(helper));
        registry.addRecipeCategories(new AdvancedCondenserRecipeCategory(helper));
        registry.addRecipeCategories(new BrewingBarrelRecipeCategory(helper));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(BasicCondenserScreen.class, 63, 40, 29, 5, CONDENSER);
        registration.addRecipeClickArea(AdvancedCondenserScreen.class, 63, 40, 29, 5, ADVANCED_CONDENSER);
        registration.addRecipeClickArea(BrewingBarrelScreen.class, 85, 41, 23, 3, BREWING_BARREL);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.CRUSHING_TUB), CRUSHING_TUB);
        registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.EVAPORATING_BASIN), EVAPORATING_BASIN);
        registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.CONDENSER), CONDENSER);
        registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ADVANCED_CONDENSER), ADVANCED_CONDENSER);
        registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.BREWING_BARREL), BREWING_BARREL);
    }

    @Override
    public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
        registration.getCraftingCategory().addExtension(OilingRecipe.class, JEIVanillaExtensions.createOilingExtension());

    }

    @Override
    public @NotNull Identifier getPluginUid() {
        return ID;
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registry) {
        registry.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, ModItems.ELIXIR_BOTTLE, (itemStack, context) -> itemStack.getComponents().toString());

    }
}
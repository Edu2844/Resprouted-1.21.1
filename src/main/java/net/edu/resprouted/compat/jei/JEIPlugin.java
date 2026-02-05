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

    public static final RecipeType<CrushingTubRecipe> CRUSHING_TUB = RecipeType.create(Resprouted.MOD_ID, "crushing", CrushingTubRecipe.class);
    public static final RecipeType<DryingBasinRecipe> DRYING_BASIN = RecipeType.create(Resprouted.MOD_ID, "drying", DryingBasinRecipe.class);
    public static final RecipeType<BasicCondenserRecipe> CONDENSER = RecipeType.create(Resprouted.MOD_ID, "basic_alchemy", BasicCondenserRecipe.class);
    public static final RecipeType<AdvancedCondenserRecipe> ADVANCED_CONDENSER = RecipeType.create(Resprouted.MOD_ID, "advanced_alchemy", AdvancedCondenserRecipe.class);
    public static final RecipeType<BrewingBarrelRecipe> BREWING_BARREL = RecipeType.create(Resprouted.MOD_ID, "brewing", BrewingBarrelRecipe.class);

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(CRUSHING_TUB, ResproutedJEIRecipes.getCrushingRecipes());
        registration.addRecipes(DRYING_BASIN, ResproutedJEIRecipes.getDryingRecipes());
        registration.addRecipes(CONDENSER, ResproutedJEIRecipes.getBasicAlchemyRecipes());
        registration.addRecipes(ADVANCED_CONDENSER, ResproutedJEIRecipes.getAdvancedAlchemyRecipes());
        registration.addRecipes(BREWING_BARREL, ResproutedJEIRecipes.getBrewingRecipes());
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper helper = registry.getJeiHelpers().getGuiHelper();

        registry.addRecipeCategories(new CrushingTubRecipeCategory(helper));
        registry.addRecipeCategories(new DryingBasinRecipeCategory(helper));
        registry.addRecipeCategories(new BasicCondenserRecipeCategory(helper));
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
        registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.DRYING_BASIN), DRYING_BASIN);
        registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.BASIC_CONDENSER), CONDENSER);
        registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ADVANCED_CONDENSER), ADVANCED_CONDENSER);
        registration.addRecipeCatalyst(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.BREWING_BARREL), BREWING_BARREL);
    }

    @Override
    public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
        registration.getCraftingCategory().addExtension(OliveOilingRecipe.class, JEIVanillaExtensions.createOilingExtension());
        registration.getCraftingCategory().addExtension(UrnColoringRecipe.class, JEIVanillaExtensions.createUrnColoringExtension());
        registration.getCraftingCategory().addExtension(JarColoringRecipe.class, JEIVanillaExtensions.createJarColoringExtension());
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
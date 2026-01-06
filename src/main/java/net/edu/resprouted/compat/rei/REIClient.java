package net.edu.resprouted.compat.rei;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.entry.EntryRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.entry.comparison.ItemComparatorRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.compat.rei.category.*;
import net.edu.resprouted.compat.rei.display.*;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.recipe.custom.*;
import net.edu.resprouted.screen.custom.AdvancedCondenserScreen;
import net.edu.resprouted.screen.custom.BasicCondenserScreen;
import net.edu.resprouted.screen.custom.BrewingBarrelScreen;
import net.edu.resprouted.util.ElixirUtils;
import net.edu.resprouted.util.RecipeUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.List;

public class REIClient implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CrushingTubCategory());
        registry.add(new DryingBasinCategory());
        registry.add(new BasicCondenserCategory());
        registry.add(new AdvancedCondenserCategory());
        registry.add(new BrewingBarrelCategory());
        registry.add(new OliveOilingCategory());
        registry.add(new VantaOilingCategory());

        registry.addWorkstations(BasicCondenserCategory.ID, EntryStacks.of(ModBlocks.BASIC_CONDENSER));
        registry.addWorkstations(AdvancedCondenserCategory.ID, EntryStacks.of(ModBlocks.ADVANCED_CONDENSER));
        registry.addWorkstations(BrewingBarrelCategory.ID, EntryStacks.of(ModBlocks.BREWING_BARREL));
        registry.addWorkstations(OliveOilingCategory.ID, EntryStacks.of(Items.CRAFTING_TABLE));
        registry.addWorkstations(VantaOilingCategory.ID, EntryStacks.of(Items.CRAFTING_TABLE));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(CrushingTubRecipe.class, ModRecipes.CRUSHING_TYPE, CrushingTubDisplay::new);
        registry.registerRecipeFiller(DryingBasinRecipe.class, ModRecipes.DRYING_TYPE, DryingBasinDisplay::new);
        registry.registerRecipeFiller(BasicCondenserRecipe.class, ModRecipes.CONDENSER_TYPE, BasicCondenserDisplay::new);
        registry.registerRecipeFiller(AdvancedCondenserRecipe.class, ModRecipes.ADVANCED_CONDENSER_TYPE, AdvancedCondenserDisplay::new);
        registerOliveOilingRecipes(registry);
        registerVantaOilingRecipes(registry);
        BrewingBarrelRecipe.RECIPES.forEach(recipe -> registry.add(new BrewingBarrelDisplay(recipe)));
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerContainerClickArea(
                new Rectangle(60, 38, 30, 8),
                BasicCondenserScreen.class,
                BasicCondenserCategory.ID
        );

        registry.registerContainerClickArea(
                new Rectangle(60, 38, 30, 8),
                AdvancedCondenserScreen.class,
                AdvancedCondenserCategory.ID
        );

        registry.registerContainerClickArea(
                new Rectangle(85, 39, 21, 7),
                BrewingBarrelScreen.class,
                BrewingBarrelCategory.ID
        );
    }

    @Override
    public void registerEntries(EntryRegistry registry) {
        if (ModItems.ELIXIR_BOTTLE != null) {
            List<ItemStack> elixirs = ElixirUtils.getElixirs();
            for (ItemStack elixir : elixirs) {
                registry.addEntry(EntryStacks.of(elixir));
            }
        }
    }

    @Override
    public void registerItemComparators(ItemComparatorRegistry registry) {
        registry.registerComponents(ModItems.ELIXIR_BOTTLE);
    }

    private void registerOliveOilingRecipes(DisplayRegistry registry) {
        ClientWorld world = MinecraftClient.getInstance().world;
        if (world == null) return;

        RecipeManager recipeManager = world.getRecipeManager();

        boolean hasOliveOilingRecipe = false;
        for (RecipeEntry<?> entry : recipeManager.values()) {
            if (entry.value() instanceof OliveOilingRecipe) {
                hasOliveOilingRecipe = true;
                break;
            }
        }

        if (!hasOliveOilingRecipe) return;

        List<Item> validFoods = Registries.ITEM.stream().filter(item -> RecipeUtils.isValidFood(new ItemStack(item))).toList();

        for (Item food : validFoods) {
            registry.add(new OliveOilingDisplay(food));
        }
    }

    private void registerVantaOilingRecipes(DisplayRegistry registry) {
        for (int numPotions = 1; numPotions <= 7; numPotions++) {

            for (RegistryEntry<Potion> potionEntry : Registries.POTION.getIndexedEntries()) {
                Potion potion = potionEntry.value();
                if (potion.getEffects().size() == 1) {
                    registry.add(new VantaOilingDisplay(numPotions, potionEntry));
                }
            }

            if (ModItems.ELIXIR_BOTTLE != null) {
                List<ItemStack> elixirs = ElixirUtils.getElixirs();
                for (ItemStack elixir : elixirs) {
                    StatusEffectInstance effect = VantaOilingRecipe.getIngredientEffect(elixir);
                    if (effect != null) {
                        registry.add(new VantaOilingDisplay(numPotions, elixir));
                    }
                }
            }
        }
    }
}
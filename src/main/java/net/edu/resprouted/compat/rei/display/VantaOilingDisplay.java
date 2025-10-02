package net.edu.resprouted.compat.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.edu.resprouted.compat.rei.category.VantaOilingCategory;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.recipe.custom.VantaOilingRecipe;
import net.edu.resprouted.util.RecipeUtils;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.ArrayList;
import java.util.List;

public class VantaOilingDisplay extends BasicDisplay {
    private final int numPotions;

    public VantaOilingDisplay(int numPotions, RegistryEntry<Potion> potionEntry) {
        super(getInputsForPotion(numPotions, potionEntry), getOutputsForPotion(numPotions, potionEntry));
        this.numPotions = numPotions;
    }

    public VantaOilingDisplay(int numPotions, ItemStack elixir) {
        super(getInputsForElixir(numPotions, elixir), getOutputsForElixir(numPotions, elixir));
        this.numPotions = numPotions;
    }

    private static List<EntryIngredient> getInputsForPotion(int numPotions, RegistryEntry<Potion> potionEntry) {
        List<EntryIngredient> inputs = new ArrayList<>();

        List<ItemStack> weapons = new ArrayList<>();
        for (Item item : Registries.ITEM) {
            ItemStack stack = new ItemStack(item);
            if (RecipeUtils.isVantaOilableWeapon(stack)) {
                weapons.add(stack);
            }
        }
        inputs.add(EntryIngredients.ofItemStacks(weapons));

        inputs.add(EntryIngredients.of(ModItems.VANTA_OIL_BOTTLE));

        List<EntryStack<?>> potionStacks = new ArrayList<>();
        potionStacks.add(EntryStacks.of(createPotionStack(Items.POTION, potionEntry)));
        potionStacks.add(EntryStacks.of(createPotionStack(Items.SPLASH_POTION, potionEntry)));
        potionStacks.add(EntryStacks.of(createPotionStack(Items.LINGERING_POTION, potionEntry)));

        for (int i = 0; i < numPotions; i++) {
            inputs.add(EntryIngredient.of(potionStacks));
        }

        return inputs;
    }

    private static List<EntryIngredient> getInputsForElixir(int numPotions, ItemStack elixir) {
        List<EntryIngredient> inputs = new ArrayList<>();

        List<ItemStack> weapons = new ArrayList<>();
        for (Item item : Registries.ITEM) {
            ItemStack stack = new ItemStack(item);
            if (RecipeUtils.isVantaOilableWeapon(stack)) {
                weapons.add(stack);
            }
        }
        inputs.add(EntryIngredients.ofItemStacks(weapons));

        inputs.add(EntryIngredients.of(ModItems.VANTA_OIL_BOTTLE));

        for (int i = 0; i < numPotions; i++) {
            inputs.add(EntryIngredient.of(EntryStacks.of(elixir)));
        }

        return inputs;
    }

    private static List<EntryIngredient> getOutputsForPotion(int numPotions, RegistryEntry<Potion> potionEntry) {
        List<EntryStack<?>> outputs = new ArrayList<>();
        Potion potion = potionEntry.value();

        if (potion.getEffects().size() == 1) {
            StatusEffectInstance effect = potion.getEffects().getFirst();

            for (Item item : Registries.ITEM) {
                ItemStack stack = new ItemStack(item);
                if (RecipeUtils.isVantaOilableWeapon(stack)) {
                    ItemStack oiled = stack.copy();
                    oiled.setCount(1);

                    int totalDuration = effect.getDuration() * numPotions;
                    StatusEffectInstance vantaEffect = new StatusEffectInstance(
                            effect.getEffectType(),
                            totalDuration,
                            effect.getAmplifier()
                    );

                    oiled.set(ModDataComponentTypes.VANTA_OIL_EFFECT, vantaEffect);
                    outputs.add(EntryStacks.of(oiled));
                }
            }
        }

        return List.of(EntryIngredient.of(outputs));
    }

    private static List<EntryIngredient> getOutputsForElixir(int numPotions, ItemStack elixir) {
        List<EntryStack<?>> outputs = new ArrayList<>();
        StatusEffectInstance effect = VantaOilingRecipe.getIngredientEffect(elixir);

        if (effect != null) {
            for (Item item : Registries.ITEM) {
                ItemStack stack = new ItemStack(item);
                if (RecipeUtils.isVantaOilableWeapon(stack)) {
                    ItemStack oiled = stack.copy();
                    oiled.setCount(1);

                    int totalDuration = effect.getDuration() * numPotions;
                    StatusEffectInstance vantaEffect = new StatusEffectInstance(
                            effect.getEffectType(),
                            totalDuration,
                            effect.getAmplifier()
                    );

                    oiled.set(ModDataComponentTypes.VANTA_OIL_EFFECT, vantaEffect);
                    outputs.add(EntryStacks.of(oiled));
                }
            }
        }

        return List.of(EntryIngredient.of(outputs));
    }

    private static ItemStack createPotionStack(Item basePotionItem, RegistryEntry<Potion> potionEntry) {
        ItemStack stack = new ItemStack(basePotionItem);
        stack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(potionEntry));
        return stack;
    }

    public int getNumPotions() {
        return numPotions;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return VantaOilingCategory.ID;
    }
}
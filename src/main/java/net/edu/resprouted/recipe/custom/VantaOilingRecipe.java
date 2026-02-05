package net.edu.resprouted.recipe.custom;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.util.recipe.RecipeUtils;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

public class VantaOilingRecipe extends SpecialCraftingRecipe {
    private final CraftingRecipeCategory category;

    public VantaOilingRecipe(CraftingRecipeCategory category) {
        super(category);
        this.category = category;
    }

    public CraftingRecipeCategory getCategory() {
        return category;
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        if (!Resprouted.COMMON_CONFIG.general.isEnableVantaOiling()) {
            return false;
        }

        if (world.isClient())
            return false;

        ItemStack weaponStack = ItemStack.EMPTY;
        ItemStack vantaoilStack = ItemStack.EMPTY;
        RegistryEntry<StatusEffect> Effect = null;
        StatusEffectInstance weaponEffect = null;

        for (int i = 0; i < input.getSize(); i++) {
            ItemStack tempStack = input.getStackInSlot(i);
            if (tempStack.isEmpty())
                continue;

            if (RecipeUtils.isVantaOilBottle(tempStack)) {
                if (!vantaoilStack.isEmpty())
                    return false;
                vantaoilStack = tempStack;
                continue;
            }

            StatusEffectInstance tempEffect = getIngredientEffect(tempStack);
            if (tempEffect != null) {
                if (Effect != null) {
                    if (!Effect.equals(tempEffect.getEffectType()))
                        return false;
                } else {
                    Effect = tempEffect.getEffectType();
                    if (weaponEffect != null) {
                        if ((weaponEffect.getEffectType() != null) && !weaponEffect.getEffectType().equals(Effect)) return false;
                    }
                }
                continue;
            }

            if (RecipeUtils.isVantaOilableWeapon(tempStack)) {
                if (!weaponStack.isEmpty()) return false;
                weaponStack = tempStack;
                weaponEffect = RecipeUtils.getVantaOilEffect(weaponStack);
                if ((weaponEffect != null) && (Effect != null)) {
                    if ((weaponEffect.getEffectType() != null) && !weaponEffect.getEffectType().equals(Effect)) return false;
                }
                continue;
            }
            return false;
        }
        return (!weaponStack.isEmpty() && !vantaoilStack.isEmpty() && (Effect != null));
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        ItemStack weaponStack = ItemStack.EMPTY;
        ItemStack vantaoilStack = ItemStack.EMPTY;
        RegistryEntry<StatusEffect> ingEffect = null;
        int amplifier = 0;
        int duration = 0;
        StatusEffectInstance weaponEffect = null;

        for (int i = 0; i < input.getSize(); i++) {
            ItemStack tempStack = input.getStackInSlot(i);
            if (tempStack.isEmpty()) continue;

            if (RecipeUtils.isVantaOilBottle(tempStack)) {
                if (!vantaoilStack.isEmpty()) return ItemStack.EMPTY;
                vantaoilStack = tempStack;
                continue;
            }
            StatusEffectInstance tempEffect = getIngredientEffect(tempStack);
            if (tempEffect != null) {
                if (ingEffect != null) {
                    if ((tempEffect.getAmplifier() >= 0) && (tempEffect.getAmplifier() < amplifier)) {
                        amplifier = tempEffect.getAmplifier();
                    }
                    duration += isInstantEffect(tempEffect.getEffectType()) ? 1 : Math.max(tempEffect.getDuration(), 1);
                    if (!ingEffect.equals(tempEffect.getEffectType())) return ItemStack.EMPTY;
                } else {
                    ingEffect = tempEffect.getEffectType();
                    amplifier = tempEffect.getAmplifier();
                    duration = isInstantEffect(ingEffect) ? 1 : Math.max(tempEffect.getDuration(), 1);
                    if (weaponEffect != null) {
                        if ((weaponEffect.getEffectType() != null) && !weaponEffect.getEffectType().equals(ingEffect)) {
                            return ItemStack.EMPTY;
                        }
                    }
                }
                continue;
            }

            String itemId = Registries.ITEM.getId(tempStack.getItem()).toString();
            if (RecipeUtils.isVantaOilableWeapon(tempStack) && !Resprouted.COMMON_CONFIG.general.getVantaOilBlackList().contains(itemId)) {
                if (!weaponStack.isEmpty()) return ItemStack.EMPTY;
                weaponStack = tempStack;
                weaponEffect = RecipeUtils.getVantaOilEffect(weaponStack);
                if ((weaponEffect != null) && (ingEffect != null)) {
                    if ((weaponEffect.getEffectType() != null) && !weaponEffect.getEffectType().equals(ingEffect)) {
                        return ItemStack.EMPTY;
                    }
                }
                continue;
            }
            return ItemStack.EMPTY;
        }

        if (weaponStack.isEmpty() || vantaoilStack.isEmpty() || (ingEffect == null)) return ItemStack.EMPTY;

        if (weaponEffect != null) {
            duration += Math.max(weaponEffect.getDuration(), 0);
            if ((weaponEffect.getAmplifier() >= 0) && (weaponEffect.getAmplifier() < amplifier)) {
                amplifier = weaponEffect.getAmplifier();
            }
        }

        ItemStack returnStack = weaponStack.copy();
        returnStack.setCount(1);
        RecipeUtils.setVantaOilEffect(returnStack, new StatusEffectInstance(ingEffect, duration, amplifier));

        return returnStack;
    }

    private boolean isInstantEffect(RegistryEntry<StatusEffect> effect) {
        return effect.value().isInstant();
    }

    public static StatusEffectInstance getIngredientEffect(ItemStack stack) {
        final Item item = stack.getItem();

        PotionContentsComponent potionContents = stack.get(DataComponentTypes.POTION_CONTENTS);
        if (potionContents == null) {
            return null;
        }
        if (item == Items.POTION || item == Items.SPLASH_POTION || item == Items.LINGERING_POTION || item == ModItems.ELIXIR_BOTTLE) {

            if (!potionContents.customEffects().isEmpty()) {
                if (potionContents.customEffects().size() == 1) {
                    return potionContents.customEffects().getFirst();
                }
                return null;
            }
            Optional<RegistryEntry<Potion>> potionEntry = potionContents.potion();
            if (potionEntry.isPresent()) {
                Potion potion = potionEntry.get().value();
                List<StatusEffectInstance> effects = potion.getEffects();
                if (effects.size() == 1) {
                    return effects.getFirst();
                }
            }
        }
        return null;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingRecipeInput input) {
        DefaultedList<ItemStack> remainder = DefaultedList.ofSize(input.getSize(), ItemStack.EMPTY);

        for (int i = 0; i < input.getSize(); i++) {
            ItemStack stack = input.getStackInSlot(i);
            if (RecipeUtils.isVantaOilBottle(stack)) {
                remainder.set(i, new ItemStack(Items.GLASS_BOTTLE));
                break;
            }
        }
        return remainder;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 3;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.VANTA_OIL_SERIALIZER;
    }
}

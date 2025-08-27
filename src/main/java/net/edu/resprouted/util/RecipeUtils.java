package net.edu.resprouted.util;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.item.ModItems;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class RecipeUtils {
    public static final int VANTA_OIL_EFFECT_DURATION = 15 * 20;
    public static final int VANTA_OIL_EFFECT_MAX_DURATION = VANTA_OIL_EFFECT_DURATION + (5 * 20);


    //Vanta Oiling
    public static boolean isVantaOilBottle(ItemStack stack) {
        return stack.isOf(ModItems.VANTA_OIL_BOTTLE);
    }
    public static boolean isVantaOilableWeapon(ItemStack stack) {
        String itemId = Registries.ITEM.getId(stack.getItem()).toString();
        Item item = stack.getItem();
        return (item instanceof AxeItem || item instanceof SwordItem || Resprouted.CONFIG.getVantaOilWhiteList().contains(itemId));
    }
    public static StatusEffectInstance getVantaOilEffect(ItemStack stack) {
        if (stack.contains(ModDataComponentTypes.VANTA_OIL_EFFECT)) {
            return stack.get(ModDataComponentTypes.VANTA_OIL_EFFECT);
        }
        return null;
    }
    public static void setVantaOilEffect(ItemStack stack, StatusEffectInstance effect) {
        stack.set(ModDataComponentTypes.VANTA_OIL_EFFECT, effect);
    }
    public static int getRemainingVantaUses(StatusEffectInstance effect) {
        if (effect == null) return 0;

        int duration = effect.getDuration();
        if (duration <= 0) return 0;

        if (effect.getEffectType().value().isInstant())
            return duration;

        int hits = duration / VANTA_OIL_EFFECT_DURATION;
        int remainderDuration = duration % VANTA_OIL_EFFECT_DURATION;

        if ((remainderDuration > (VANTA_OIL_EFFECT_MAX_DURATION - VANTA_OIL_EFFECT_DURATION)) || (hits == 0))
            hits++;

        return hits;
    }
    public static int getNextVantaHitDuration(int totalDuration) {
        return (totalDuration > VANTA_OIL_EFFECT_MAX_DURATION) ? VANTA_OIL_EFFECT_DURATION : totalDuration;
    }

    public static List<Item> getValidFoods() {
        List<Item> validFoods = new ArrayList<>();

        validFoods.add(Items.BEEF);
        validFoods.add(Items.COOKED_BEEF);
        validFoods.add(Items.PORKCHOP);
        validFoods.add(Items.COOKED_PORKCHOP);
        validFoods.add(Items.MUTTON);
        validFoods.add(Items.COOKED_MUTTON);
        validFoods.add(Items.CHICKEN);
        validFoods.add(Items.COOKED_CHICKEN);
        validFoods.add(Items.RABBIT);
        validFoods.add(Items.COOKED_RABBIT);
        validFoods.add(Items.COD);
        validFoods.add(Items.COOKED_COD);
        validFoods.add(Items.SALMON);
        validFoods.add(Items.COOKED_SALMON);
        validFoods.add(Items.TROPICAL_FISH);
        validFoods.add(Items.BREAD);
        validFoods.add(Items.POTATO);
        validFoods.add(Items.BAKED_POTATO);
        validFoods.add(Items.CARROT);
        validFoods.add(Items.GOLDEN_CARROT);
        validFoods.add(Items.BEETROOT);
        validFoods.add(Items.SWEET_BERRIES);
        validFoods.add(Items.GLOW_BERRIES);
        validFoods.add(Items.APPLE);
        validFoods.add(Items.GOLDEN_APPLE);
        validFoods.add(ModItems.TOMATO);
        validFoods.add(ModItems.CHILI_PEPPER);
        validFoods.add(ModItems.GRAPES);
        validFoods.add(ModItems.IRON_BERRIES);
        validFoods.add(ModItems.OLIVES);

        for (String itemId : Resprouted.CONFIG.getOliveOilFoodWhiteList()) {
            Identifier id = Identifier.tryParse(itemId);
            if (id != null) {
                Item item = Registries.ITEM.get(id);
                if (item != Items.AIR) {
                    validFoods.add(item);
                }
            }
        }
        return validFoods;
    }
    public static boolean isValidFood(ItemStack stack) {
        String itemId = Registries.ITEM.getId(stack.getItem()).toString();
        return getValidFoods().contains(stack.getItem()) && !Resprouted.CONFIG.getOliveOilFoodBlackList().contains(itemId);
    }
}


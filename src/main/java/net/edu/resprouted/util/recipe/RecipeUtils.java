package net.edu.resprouted.util.recipe;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.util.misc.ModTags;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;

public class RecipeUtils {
    public static final int VANTA_OIL_EFFECT_DURATION = 15 * 20;
    public static final int VANTA_OIL_EFFECT_MAX_DURATION = VANTA_OIL_EFFECT_DURATION + (5 * 20);

    public static boolean isVantaOilBottle(ItemStack stack) {
        return stack.isOf(ModItems.VANTA_OIL_BOTTLE);
    }

    public static boolean isVantaOilableWeapon(ItemStack stack) {
        String itemId = Registries.ITEM.getId(stack.getItem()).toString();
        Item item = stack.getItem();
        return (item instanceof AxeItem || item instanceof SwordItem || Resprouted.COMMON_CONFIG.general.getVantaOilWhiteList().contains(itemId));
    }

    public static StatusEffectInstance getVantaOilEffect(ItemStack stack) {
        if (stack.contains(ModDataComponentTypes.VANTA_OILED)) {
            return stack.get(ModDataComponentTypes.VANTA_OILED);
        }
        return null;
    }

    public static void setVantaOilEffect(ItemStack stack, StatusEffectInstance effect) {
        stack.set(ModDataComponentTypes.VANTA_OILED, effect);
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

    public static boolean isValidFood(ItemStack stack) {
        String itemId = Registries.ITEM.getId(stack.getItem()).toString();

        if (Resprouted.COMMON_CONFIG.food.getOilableFoodBlackList().contains(itemId)) {
            return false;
        }
        if (stack.isIn(ModTags.Items.CAN_BE_OILED)) {
            return true;
        }
        for (String configItemId : Resprouted.COMMON_CONFIG.food.getOilableFoodWhiteList()) {
            if (itemId.equals(configItemId)) {
                return true;
            }
        }
        return false;
    }
}


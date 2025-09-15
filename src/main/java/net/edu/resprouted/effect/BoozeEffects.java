package net.edu.resprouted.effect;

import net.edu.resprouted.item.custom.BoozeBottleItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class BoozeEffects {

    public static void applyAleEffects(BoozeBottleItem.BoozeConsumptionContext context) {
        PlayerEntity player = context.player();
        float quality = context.quality();

        if (quality >= 0.5F) {
            player.getHungerManager().add(2, 4F * quality);
            int duration = 1200 + ((int) (10800 * quality));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, duration));
        } else {
            int duration = (int) (6000 * (1 - quality));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, duration));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, duration));
        }
    }
}

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
    public static void applyIronWineEffects(BoozeBottleItem.BoozeConsumptionContext context) {
        PlayerEntity player = context.player();
        float quality = context.quality();

        if (quality >= 0.5F) {
            player.getHungerManager().add(1, 2F * quality);

            float absorption = 10F * (Math.max((quality - 0.5F) * 2F, 0F));
            float currentAbsorption = player.getAbsorptionAmount();
            player.setAbsorptionAmount(Math.min(currentAbsorption + absorption, 20F));

        } else {
            int duration = (int) (6000 * Math.max(1 - quality, 0.25));

            float damage = 10F * (Math.max(Math.abs(quality - 0.5F) + 0.1F, 0.25F));
            player.damage(player.getDamageSources().magic(), damage);

            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, duration));
        }
    }
}

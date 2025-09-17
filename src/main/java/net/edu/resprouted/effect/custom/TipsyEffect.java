package net.edu.resprouted.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class TipsyEffect extends StatusEffect {
    public TipsyEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return (amplifier >= 1) && ((duration % 100) == 0);
    }
    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient() && amplifier >= 1) {
            if (amplifier >= 2) {
                entity.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.NAUSEA, 400, 0, false, false, false
                ));
                entity.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.SLOWNESS, 400, 0, false, false, false
                ));

                if (amplifier >= 3) {
                    entity.addStatusEffect(new StatusEffectInstance(
                            StatusEffects.BLINDNESS, 400, 0, false, false, false
                    ));
                    entity.addStatusEffect(new StatusEffectInstance(
                            StatusEffects.NAUSEA, 400, 1, false, false, false  // ← Náuseas nivel 2
                    ));
                }
            }
        }
        return true;
    }
}

package net.edu.resprouted.mixin.effect;

import net.edu.resprouted.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityEffectsMixin {
    @ModifyVariable(
            method = "damage",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 0
    )
    private float modifyDamageAmount(float amount, DamageSource source) {
        LivingEntity entity = (LivingEntity) (Object) this;

        //FULLMETAL
        if (entity.hasStatusEffect(ModEffects.FULL_METAL) &&
                !source.isOf(DamageTypes.OUT_OF_WORLD) &&
                !source.isSourceCreativePlayer()) {
            return 0;
        }

        //FULL
        if (source.isOf(DamageTypes.STARVE) && entity.hasStatusEffect(ModEffects.FULL)) {
            StatusEffectInstance effect = entity.getStatusEffect(ModEffects.FULL);
            if (effect != null) {
                return Math.max(amount - (effect.getAmplifier() + 1), 0);
            }
        }

        //MAGIC_RESISTANCE
        if ((source.isOf(DamageTypes.MAGIC) || source.isOf(DamageTypes.INDIRECT_MAGIC)) &&
                entity.hasStatusEffect(ModEffects.MAGIC_RESISTANCE)) {
            StatusEffectInstance effect = entity.getStatusEffect(ModEffects.MAGIC_RESISTANCE);
            if (effect != null) {
                return amount / (2.0f * (effect.getAmplifier() + 1));
            }
        }

        //WITHER_WARD
        if (source.isOf(DamageTypes.WITHER) && entity.hasStatusEffect(ModEffects.WITHER_WARD)) {
            StatusEffectInstance effect = entity.getStatusEffect(ModEffects.WITHER_WARD);
            if (effect != null) {
                return amount / (2.0f * (effect.getAmplifier() + 1));
            }
        }

        return amount;
    }
}
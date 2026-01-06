package net.edu.resprouted.mixin.gameplay;

import net.edu.resprouted.effect.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyVariable(method = "damage", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private float modifyDamageAmount(float amount, DamageSource source) {
        LivingEntity entity = (LivingEntity) (Object) this;

        // Full Metal
        if (entity.hasStatusEffect(ModEffects.FULL_METAL) && !source.isOf(DamageTypes.OUT_OF_WORLD) && !source.isSourceCreativePlayer()) {
            return 0;
        }
        // Full Stomach
        if (source.isOf(DamageTypes.STARVE) && entity.hasStatusEffect(ModEffects.FULL_STOMACH)) {
            StatusEffectInstance effect = entity.getStatusEffect(ModEffects.FULL_STOMACH);

            if (effect != null) {
                return Math.max(amount - (effect.getAmplifier() + 1), 0);
            }
        }
        // Magic Resistance
        if ((source.isOf(DamageTypes.MAGIC) || source.isOf(DamageTypes.INDIRECT_MAGIC)) && entity.hasStatusEffect(ModEffects.MAGIC_RESISTANCE)) {
            StatusEffectInstance effect = entity.getStatusEffect(ModEffects.MAGIC_RESISTANCE);

            if (effect != null) {
                return amount / (2.0f * (effect.getAmplifier() + 1));
            }
        }
        // Wither Ward
        if (source.isOf(DamageTypes.WITHER) && entity.hasStatusEffect(ModEffects.WITHER_WARD)) {
            StatusEffectInstance effect = entity.getStatusEffect(ModEffects.WITHER_WARD);

            if (effect != null) {
                return amount / (2.0f * (effect.getAmplifier() + 1));
            }
        }
        return amount;
    }

    @Inject(method = "jump", at = @At("TAIL"))
    private void onJump(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity.hasStatusEffect(ModEffects.FULL_METAL)) {
            entity.setVelocity(entity.getVelocity().x * 0, 0, entity.getVelocity().z * 0);
        }
    }

    @Inject(method = "handleFallDamage", at = @At("HEAD"))
    private void onFall(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity.hasStatusEffect(ModEffects.FULL_METAL)
                && fallDistance >= 0.6F
                && !entity.isTouchingWater()
                && !entity.isInLava()) {
            entity.playSound(SoundEvents.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
        }
    }
}

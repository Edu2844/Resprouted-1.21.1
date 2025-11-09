package net.edu.resprouted.mixin.gameplay;

import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.util.RecipeUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyVariable(
            method = "damage",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 0
    )

    private float modifyDamageAmount(float amount, DamageSource source) {
        LivingEntity entity = (LivingEntity) (Object) this;

        //Full Metal Effect
        if (entity.hasStatusEffect(ModEffects.FULL_METAL) &&
                !source.isOf(DamageTypes.OUT_OF_WORLD) &&
                !source.isSourceCreativePlayer()) {
            return 0;
        }

        //Full Stomach Effect
        if (source.isOf(DamageTypes.STARVE) && entity.hasStatusEffect(ModEffects.FULL_STOMACH)) {
            StatusEffectInstance effect = entity.getStatusEffect(ModEffects.FULL_STOMACH);
            if (effect != null) {
                return Math.max(amount - (effect.getAmplifier() + 1), 0);
            }
        }

        //Magic Resistance Effect
        if ((source.isOf(DamageTypes.MAGIC) || source.isOf(DamageTypes.INDIRECT_MAGIC)) &&
                entity.hasStatusEffect(ModEffects.MAGIC_RESISTANCE)) {
            StatusEffectInstance effect = entity.getStatusEffect(ModEffects.MAGIC_RESISTANCE);
            if (effect != null) {
                return amount / (2.0f * (effect.getAmplifier() + 1));
            }
        }

        //Wither Ward Effect
        if (source.isOf(DamageTypes.WITHER) && entity.hasStatusEffect(ModEffects.WITHER_WARD)) {
            StatusEffectInstance effect = entity.getStatusEffect(ModEffects.WITHER_WARD);
            if (effect != null) {
                return amount / (2.0f * (effect.getAmplifier() + 1));
            }
        }

        return amount;
    }

    @Inject(method = "damage", at = @At("HEAD"))
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!this.getWorld().isClient() && source.getAttacker() instanceof PlayerEntity player) {
            ItemStack weapon = player.getMainHandStack();

            if (weapon.contains(ModDataComponentTypes.VANTA_OILED)) {
                StatusEffectInstance currentEffect = weapon.get(ModDataComponentTypes.VANTA_OILED);

                if (currentEffect != null) {
                    LivingEntity target = (LivingEntity) (Object) this;

                    if (!target.canTakeDamage() || target.isInvulnerableTo(source)) {
                        return;
                    }

                    int totalDuration = currentEffect.getDuration();
                    int amplifier = currentEffect.getAmplifier();
                    RegistryEntry<StatusEffect> effectType = currentEffect.getEffectType();

                    if (totalDuration < 1 || amplifier < 0) {
                        weapon.remove(ModDataComponentTypes.VANTA_OILED);
                        return;
                    }

                    boolean isInstant = effectType.value().isInstant();

                    int hitDuration = isInstant ? 1 : RecipeUtils.getNextVantaHitDuration(totalDuration);
                    int effectDuration = hitDuration;

                    StatusEffectInstance activeEffect = target.getStatusEffect(effectType);
                    if (activeEffect != null) {
                        int activeDur = activeEffect.getDuration();
                        if (activeDur <= 0) {
                            target.removeStatusEffect(effectType);
                        } else if (activeEffect.getAmplifier() == amplifier) {
                            effectDuration += activeDur;
                        } else if (activeEffect.getAmplifier() > amplifier) {
                            effectDuration = 0;
                        }
                    }

                    if (effectDuration > 0) {
                        target.addStatusEffect(new StatusEffectInstance(
                                effectType,
                                effectDuration,
                                amplifier,
                                currentEffect.isAmbient(),
                                currentEffect.shouldShowParticles(),
                                currentEffect.shouldShowIcon()
                        ), player);
                    }

                    int newDuration = totalDuration - hitDuration;

                    if (newDuration <= 0) {
                        weapon.remove(ModDataComponentTypes.VANTA_OILED);
                    } else {
                        weapon.set(ModDataComponentTypes.VANTA_OILED,
                                new StatusEffectInstance(
                                        effectType,
                                        newDuration,
                                        amplifier,
                                        currentEffect.isAmbient(),
                                        currentEffect.shouldShowParticles(),
                                        currentEffect.shouldShowIcon()));
                    }
                }
            }
        }
    }
}

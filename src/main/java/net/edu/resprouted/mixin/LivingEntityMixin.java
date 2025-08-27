package net.edu.resprouted.mixin;

import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.util.RecipeUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }
    @Inject(method = "damage", at = @At("HEAD"))
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!this.getWorld().isClient() && source.getAttacker() instanceof PlayerEntity player) {
            ItemStack weapon = player.getMainHandStack();

            if (weapon.contains(ModDataComponentTypes.VANTA_OIL_EFFECT)) {
                StatusEffectInstance currentEffect = weapon.get(ModDataComponentTypes.VANTA_OIL_EFFECT);

                if (currentEffect != null) {
                    LivingEntity target = (LivingEntity) (Object) this;
                    target.addStatusEffect(new StatusEffectInstance(
                            currentEffect.getEffectType(),
                            currentEffect.getDuration(),
                            currentEffect.getAmplifier()
                    ), player);

                    int newDuration = currentEffect.getDuration();
                    if (currentEffect.getEffectType().value().isInstant()) {
                        newDuration--;
                    } else {
                        newDuration -= RecipeUtils.getNextVantaHitDuration(currentEffect.getDuration());
                    }
                    if (newDuration <= 0) {
                        weapon.remove(ModDataComponentTypes.VANTA_OIL_EFFECT);
                    } else {
                        weapon.set(ModDataComponentTypes.VANTA_OIL_EFFECT,
                                new StatusEffectInstance(
                                        currentEffect.getEffectType(),
                                        newDuration,
                                        currentEffect.getAmplifier(),
                                        currentEffect.isAmbient(),
                                        currentEffect.shouldShowParticles(),
                                        currentEffect.shouldShowIcon()));
                    }
                }
            }
        }
    }
}

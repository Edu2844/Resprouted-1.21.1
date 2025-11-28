package net.edu.resprouted.mixin.gameplay;

import net.edu.resprouted.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;


@Mixin(MilkBucketItem.class)
public abstract class MilkBucketItemMixin {

    @Unique
    private final Map<LivingEntity, Map<RegistryEntry<StatusEffect>, StatusEffectInstance>> savedEffects = new WeakHashMap<>();

    @Inject(method = "finishUsing", at = @At("HEAD"))
    private void beforeFinishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (!world.isClient && user.hasStatusEffect(ModEffects.TIPSY)) {
            StatusEffectInstance tipsyEffect = user.getStatusEffect(ModEffects.TIPSY);

            if (tipsyEffect != null && tipsyEffect.getAmplifier() >= 1) {
                Map<RegistryEntry<StatusEffect>, StatusEffectInstance> toSave = new HashMap<>();

                toSave.put(tipsyEffect.getEffectType(), new StatusEffectInstance(tipsyEffect));

                StatusEffectInstance nausea = user.getStatusEffect(StatusEffects.NAUSEA);
                if (nausea != null) {
                    toSave.put(nausea.getEffectType(), new StatusEffectInstance(nausea));
                }

                StatusEffectInstance slowness = user.getStatusEffect(StatusEffects.SLOWNESS);
                if (slowness != null) {
                    assert nausea != null;
                    toSave.put(slowness.getEffectType(), new StatusEffectInstance(nausea));
                }

                StatusEffectInstance blindness = user.getStatusEffect(StatusEffects.BLINDNESS);
                if (blindness != null) {
                    toSave.put(blindness.getEffectType(), new StatusEffectInstance(blindness));
                }

                savedEffects.put(user, toSave);
            }
        }
    }

    @Inject(method = "finishUsing", at = @At("RETURN"))
    private void afterFinishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (!world.isClient && savedEffects.containsKey(user)) {
            Map<RegistryEntry<StatusEffect>, StatusEffectInstance> effects = savedEffects.remove(user);

            for (StatusEffectInstance effect : effects.values()) {
                user.addStatusEffect(effect);
            }
        }
    }
}
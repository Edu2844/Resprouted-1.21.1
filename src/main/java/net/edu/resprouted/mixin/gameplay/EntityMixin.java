package net.edu.resprouted.mixin.gameplay;

import net.edu.resprouted.effect.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "startRiding(Lnet/minecraft/entity/Entity;Z)Z", at = @At("HEAD"), cancellable = true)
    private void onStartRiding(Entity vehicle, boolean force, CallbackInfoReturnable<Boolean> cir) {
        Entity rider = (Entity) (Object) this;

        if (rider instanceof LivingEntity livingRider) {
            if (livingRider.hasStatusEffect(ModEffects.FULL_METAL)) {
                cir.setReturnValue(false);
            }
        }
    }
}

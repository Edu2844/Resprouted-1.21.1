package net.edu.resprouted.mixin.gameplay;

import net.edu.resprouted.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(MilkBucketItem.class)
public abstract class MilkBucketItemMixin {

    @Inject(method = "finishUsing", at = @At("HEAD"), cancellable = true)
    private void onFinishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (user.hasStatusEffect(ModEffects.TIPSY)) {
            StatusEffectInstance tipsyEffect = user.getStatusEffect(ModEffects.TIPSY);

            if (tipsyEffect != null && tipsyEffect.getAmplifier() >= 2) {
                if (user instanceof PlayerEntity player) {
                    cir.setReturnValue(ItemUsage.exchangeStack(stack, player, new ItemStack(Items.BUCKET)));
                } else {
                    cir.setReturnValue(stack);
                }
                cir.cancel();
            }
        }
    }
}

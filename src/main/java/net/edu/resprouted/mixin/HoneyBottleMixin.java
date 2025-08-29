package net.edu.resprouted.mixin;

import net.edu.resprouted.Resprouted;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.item.HoneyBottleItem.class)
public class HoneyBottleMixin {

    @Inject(method = "finishUsing", at = @At("HEAD"))
    private void onFinishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (!world.isClient() && stack.getItem() == Items.HONEY_BOTTLE && Resprouted.CONFIG.EnableHoneyBottleEffect) {
            user.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.REGENERATION,
                    80,
                    0,
                    false,
                    false,
                    true
            ));
        }
    }
}
package net.edu.resprouted.mixin.gameplay;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.util.TextUtils;
import net.minecraft.entity.LivingEntity;
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
        if (!world.isClient() && stack.getItem() == Items.HONEY_BOTTLE && Resprouted.COMMON_CONFIG.general.isEnableHoneyBottleRegenerationEffect()) {
            user.addStatusEffect(TextUtils.getHoneyBottleEffect());
        }
    }
}
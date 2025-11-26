package net.edu.resprouted.mixin.misc;

import net.edu.resprouted.advancement.criterion.ModCriteria;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin {
    @Shadow @Final
    public PlayerEntity player;

    @Inject(method = "insertStack(ILnet/minecraft/item/ItemStack;)Z", at = @At("RETURN"))
    private void afterInsertStack(int slot, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue() && this.player instanceof ServerPlayerEntity serverPlayer) {
            ModCriteria.FLUID_QUALITY_INVENTORY.trigger(serverPlayer, (PlayerInventory)(Object)this, stack);
        }
    }

    @Inject(method = "setStack", at = @At("TAIL"))
    private void afterSetStack(int slot, ItemStack stack, CallbackInfo ci) {
        if (this.player instanceof ServerPlayerEntity serverPlayer) {
            ModCriteria.FLUID_QUALITY_INVENTORY.trigger(serverPlayer, (PlayerInventory)(Object)this, stack);
        }
    }
}
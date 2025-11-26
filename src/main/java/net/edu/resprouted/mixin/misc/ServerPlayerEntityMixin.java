package net.edu.resprouted.mixin.misc;

import net.edu.resprouted.advancement.criterion.ModCriteria;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Inject(method = "onScreenHandlerOpened", at = @At("TAIL"))
    private void onScreenHandlerOpened(ScreenHandler screenHandler, CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;
        ModCriteria.FLUID_QUALITY_INVENTORY.trigger(player, player.getInventory(), ItemStack.EMPTY);
    }
}
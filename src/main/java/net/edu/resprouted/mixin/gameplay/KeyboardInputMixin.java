package net.edu.resprouted.mixin.gameplay;

import net.edu.resprouted.effect.ModEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    private void onInputUpdate(boolean slowDown, float slowDownFactor, CallbackInfo ci) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (player != null && player.hasStatusEffect(ModEffects.FULL_METAL)) {
            KeyboardInput input = (KeyboardInput) (Object) this;
            input.jumping = false;
            input.movementForward = 0.0F;
            input.movementSideways = 0.0F;
        }
    }
}

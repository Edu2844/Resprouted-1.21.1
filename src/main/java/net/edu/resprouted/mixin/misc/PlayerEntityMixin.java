package net.edu.resprouted.mixin.misc;

import net.edu.resprouted.Resprouted;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "getDisplayName", at = @At("RETURN"), cancellable = true)
    private void modifyDisplayName(CallbackInfoReturnable<Text> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (player.getUuid().equals(Resprouted.EDU_UUID)) {
            cir.setReturnValue(Text.literal("RoofMan").formatted(Formatting.RESET)
            );
        }
        if (player.getUuid().equals(Resprouted.NUMEROS_UUID)) {
            cir.setReturnValue(Text.literal("TheOneWhoKnocks").formatted(Formatting.BLUE)
            );
        }
        if (player.getUuid().equals(Resprouted.IDOLS_UUID)) {
            cir.setReturnValue(Text.literal("SixSeven").formatted(Formatting.AQUA)
            );
        }
    }
}
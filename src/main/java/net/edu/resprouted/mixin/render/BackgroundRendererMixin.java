package net.edu.resprouted.mixin.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.edu.resprouted.util.ModTags;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
    @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
    private static void onApplyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        if (camera.getFocusedEntity().isSubmergedIn(ModTags.Fluids.HONEY)) {
            RenderSystem.setShaderFogColor(0.9f, 0.7f, 0.1f);
            RenderSystem.setShaderFogStart(0.25f);
            RenderSystem.setShaderFogEnd(1.0f);
            ci.cancel();
        }
    }
}

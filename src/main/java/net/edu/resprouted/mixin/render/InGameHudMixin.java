package net.edu.resprouted.mixin.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.effect.ModEffects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
@Environment(EnvType.CLIENT)
public class InGameHudMixin {
    @Inject(method = "renderMiscOverlays", at = @At("TAIL"))
    private void renderFullMetalOverlay(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        if (!client.player.hasStatusEffect(ModEffects.FULL_METAL)) return;
        if (!client.options.getPerspective().isFirstPerson()) return;

        int screenWidth = context.getScaledWindowWidth();
        int screenHeight = context.getScaledWindowHeight();

        int textureWidth = 16;
        int textureHeight = 16;

        float scale = Math.max((float)screenWidth / textureWidth, (float)screenHeight / textureHeight);
        int scaledWidth = (int)(textureWidth * scale);
        int scaledHeight = (int)(textureHeight * scale);

        int x = (screenWidth - scaledWidth) / 2;
        int y = (screenHeight - scaledHeight) / 2;

        Identifier OVERLAY_TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/misc/full_metal_overlay.png");

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        context.setShaderColor(1.0F, 1.0F, 1.0F, 0.99F);

        context.drawTexture(OVERLAY_TEXTURE, x, y, -90, 0, 0, scaledWidth, scaledHeight, scaledWidth, scaledHeight);

        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}

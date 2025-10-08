package net.edu.resprouted.effect.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.effect.ModEffects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class FullMetalHudOverlay implements HudRenderCallback {
    private static final Identifier OVERLAY_TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/misc/full_metal_overlay.png");

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        if (!client.player.hasStatusEffect(ModEffects.FULL_METAL)) return;

        int screenWidth = drawContext.getScaledWindowWidth();
        int screenHeight = drawContext.getScaledWindowHeight();

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 0.99f);

        int textureWidth = 16;
        int textureHeight = 16;

        float scale = Math.max((float)screenWidth / textureWidth, (float)screenHeight / textureHeight);
        int scaledWidth = (int)(textureWidth * scale);
        int scaledHeight = (int)(textureHeight * scale);

        int x = (screenWidth - scaledWidth) / 2;
        int y = (screenHeight - scaledHeight) / 2;

        drawContext.drawTexture(OVERLAY_TEXTURE, x, y, 0, 0, scaledWidth, scaledHeight, scaledWidth, scaledHeight);

        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
    }
}


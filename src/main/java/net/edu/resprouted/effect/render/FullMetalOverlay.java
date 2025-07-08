package net.edu.resprouted.effect.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.effect.ModEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class FullMetalOverlay {
    private static final Identifier OVERLAY_TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/misc/full_metal_overlay.png");

    public static void render(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null || !client.player.hasStatusEffect(ModEffects.FULL_METAL)) {
            return;
        }
        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();
        float aspectRatio = (float) width / height;

        context.getMatrices().push();
        RenderSystem.enableBlend();
        context.setShaderColor(1.0F, 1.0F, 1.0F, 0.97F);

        int regionHeight = 8;
        float regionWidth = regionHeight * aspectRatio;

        if (regionWidth > 16.0f) {
            regionWidth = 16.0f;
            regionHeight = (int) (regionWidth / aspectRatio);
        }

        float u = (16.0f - regionWidth) / 2.0f;
        float v = (16.0f - regionHeight) / 2.0f;

        context.drawTexture(
                OVERLAY_TEXTURE,
                0, 0,
                width, height,
                u, v,
                (int) regionWidth, regionHeight,
                16, 16
        );
        context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        context.getMatrices().pop();
    }
}

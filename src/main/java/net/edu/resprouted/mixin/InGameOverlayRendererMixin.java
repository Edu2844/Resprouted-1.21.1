package net.edu.resprouted.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.edu.resprouted.util.ModTags;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public abstract class InGameOverlayRendererMixin {
    @Unique
    private static final Identifier HONEY_OVERLAY = Identifier.of("resprouted:textures/misc/honey_overlay.png");

    @Inject(method = "renderOverlays", at = @At("HEAD"), cancellable = true)
    private static void onRenderOverlays(MinecraftClient client, MatrixStack matrices, CallbackInfo ci) {
        if (client.player != null && client.player.isSubmergedIn(ModTags.Fluids.HONEY)) {
            renderHoneyOverlay(client, matrices);
            ci.cancel();
        }
    }
    @Unique
    private static void renderHoneyOverlay(MinecraftClient client, MatrixStack matrices) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, HONEY_OVERLAY);

        BlockPos blockPos = BlockPos.ofFloored(client.player.getX(), client.player.getEyeY(), client.player.getZ());
        float brightness = LightmapTextureManager.getBrightness(
                client.player.getWorld().getDimension(),
                client.player.getWorld().getLightLevel(blockPos)
        );

        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(brightness, brightness, brightness, 0.1F);

        float yawOffset = -client.player.getYaw() / 64.0F;
        float pitchOffset = client.player.getPitch() / 64.0F;
        Matrix4f matrix = matrices.peek().getPositionMatrix();

        BufferBuilder buffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        buffer.vertex(matrix, -1.0F, -1.0F, -0.5F).texture(4.0F + yawOffset, 4.0F + pitchOffset);
        buffer.vertex(matrix, 1.0F, -1.0F, -0.5F).texture(0.0F + yawOffset, 4.0F + pitchOffset);
        buffer.vertex(matrix, 1.0F, 1.0F, -0.5F).texture(0.0F + yawOffset, 0.0F + pitchOffset);
        buffer.vertex(matrix, -1.0F, 1.0F, -0.5F).texture(4.0F + yawOffset, 0.0F + pitchOffset);
        BufferRenderer.drawWithGlobalProgram(buffer.end());

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
    }
}


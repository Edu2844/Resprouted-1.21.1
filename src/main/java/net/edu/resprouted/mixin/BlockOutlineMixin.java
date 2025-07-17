package net.edu.resprouted.mixin;

import net.edu.resprouted.block.interfaces.IAdvancedRotationPlacement;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class BlockOutlineMixin {
    @Inject(method = "drawBlockOutline", at = @At("HEAD"))
    private void onDrawBlockOutline(MatrixStack matrices, VertexConsumer vertexConsumer, Entity entity, double cameraX, double cameraY, double cameraZ, BlockPos pos, BlockState state, CallbackInfo ci) {
        if (!(entity instanceof PlayerEntity player)) return;

        ItemStack stack = player.getMainHandStack();
        if (!(stack.getItem() instanceof BlockItem blockItem)) return;
        if (!(blockItem.getBlock() instanceof IAdvancedRotationPlacement)) return;
        World world = player.getWorld();
        boolean isSolid = state.isFullCube(world, pos);
        if (!isSolid) return;
        renderCustomXOutline(matrices, vertexConsumer, cameraX, cameraY, cameraZ, pos);
    }
    @Unique
    private void renderCustomXOutline(MatrixStack matrices, VertexConsumer vertexConsumer, double cameraX, double cameraY, double cameraZ, BlockPos pos) {
        matrices.push();
        matrices.translate(-cameraX, -cameraY, -cameraZ);

        double minX = pos.getX();
        double minY = pos.getY();
        double minZ = pos.getZ();
        double maxX = pos.getX() + 1;
        double maxY = pos.getY() + 1;
        double maxZ = pos.getZ() + 1;

        //Color
        float r = 0.0f;
        float g = 0.0f;
        float b = 0.0f;
        float a = 0.8f;

        //Cara superior
        drawLine(matrices, vertexConsumer, minX, maxY, minZ, maxX, maxY, maxZ, r, g, b, a);
        drawLine(matrices, vertexConsumer, minX, maxY, maxZ, maxX, maxY, minZ, r, g, b, a);

        //Cara inferior
        drawLine(matrices, vertexConsumer, minX, minY, minZ, maxX, minY, maxZ, r, g, b, a);
        drawLine(matrices, vertexConsumer, minX, minY, maxZ, maxX, minY, minZ, r, g, b, a);

        matrices.pop();
    }
    @Unique
    private void drawLine(MatrixStack matrices, VertexConsumer vertexConsumer, double x1, double y1, double z1, double x2, double y2, double z2, float r, float g, float b, float a) {
        MatrixStack.Entry entry = matrices.peek();
        Matrix4f matrix = entry.getPositionMatrix();
        float nx = 0f;
        float ny = 1f;
        float nz = 0f;
        vertexConsumer.vertex(matrix, (float)x1, (float)y1, (float)z1).color(r, g, b, a).normal(entry, nx, ny, nz);
        vertexConsumer.vertex(matrix, (float)x2, (float)y2, (float)z2).color(r, g, b, a).normal(entry, nx, ny, nz);
    }
}

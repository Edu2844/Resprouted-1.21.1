package net.edu.resprouted.mixin;

import net.edu.resprouted.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderLayers.class)
public abstract class LeavesCutoutMixin {
    @Inject(method = "getBlockLayer", at = @At("HEAD"), cancellable = true)
    private static void forceCutoutForCustomLeaves(BlockState state, CallbackInfoReturnable<RenderLayer> cir) {
        Block block = state.getBlock();

        if (block == ModBlocks.APPLE_LEAVES) {
            cir.setReturnValue(RenderLayer.getCutout());
        }
    }
}

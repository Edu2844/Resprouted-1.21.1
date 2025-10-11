package net.edu.resprouted.mixin.render;

import net.edu.resprouted.block.custom.agriculture.AppleLeavesBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderLayers.class)
public abstract class RenderLayersMixin {

    @Inject(method = "getBlockLayer", at = @At("HEAD"), cancellable = true)
    private static void forceCutoutForFruitingLeaves(BlockState state, CallbackInfoReturnable<RenderLayer> cir) {
        Block block = state.getBlock();

        if (block instanceof AppleLeavesBlock) {
            cir.setReturnValue(RenderLayer.getCutout());
        }
    }
}

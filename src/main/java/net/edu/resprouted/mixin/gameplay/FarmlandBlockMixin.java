package net.edu.resprouted.mixin.gameplay;

import net.edu.resprouted.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FarmlandBlock.class)
public class FarmlandBlockMixin {
    @Inject(method = "hasCrop", at = @At("HEAD"), cancellable = true)
    private static void modifyHasCrop(BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockState blockState = world.getBlockState(pos.up());
        if (blockState.isIn(ModTags.Blocks.PRESERVES_FARMLAND)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
    private void modifyCanPlaceAt(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockState blockState = world.getBlockState(pos.up());
        if (blockState.isIn(ModTags.Blocks.PRESERVES_FARMLAND)) {
            cir.setReturnValue(true);
        }
    }
}
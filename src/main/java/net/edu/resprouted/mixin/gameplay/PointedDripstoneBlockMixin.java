package net.edu.resprouted.mixin.gameplay;

import net.edu.resprouted.block.custom.decorative.LiquidBarrelBlock;
import net.edu.resprouted.mixin.util.AbstractCauldronBlockAccessor;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PointedDripstoneBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

@Mixin(PointedDripstoneBlock.class)
public class PointedDripstoneBlockMixin {
    @Inject(method = "getCauldronPos", at = @At("HEAD"), cancellable = true)
    private static void getCauldronPosIncludingBarrel(World world, BlockPos pos, Fluid fluid, CallbackInfoReturnable<BlockPos> cir) {
        Predicate<BlockState> predicate = state -> {
            Block block = state.getBlock();
            // Check for vanilla cauldrons
            if (block instanceof AbstractCauldronBlock cauldron) {
                return ((AbstractCauldronBlockAccessor) cauldron).invokeCanBeFilledByDripstone(fluid);
            }
            // Check for liquid barrel
            return block instanceof LiquidBarrelBlock && fluid == Fluids.WATER;
        };

        BiPredicate<BlockPos, BlockState> biPredicate = (posx, state) -> canDripThrough(world, posx, state);
        BlockPos result = searchInDirection(world, pos, Direction.DOWN.getDirection(), biPredicate, predicate, 11).orElse(null);
        cir.setReturnValue(result);
    }

    @Shadow
    private static boolean canDripThrough(BlockView world, BlockPos pos, BlockState state) {
        throw new AssertionError();
    }

    @Shadow
    private static Optional<BlockPos> searchInDirection(WorldAccess world, BlockPos pos, Direction.AxisDirection direction, BiPredicate<BlockPos, BlockState> continuePredicate, Predicate<BlockState> stopPredicate, int range) {
        throw new AssertionError();
    }
}
package net.edu.resprouted.mixin;

import net.edu.resprouted.util.BottleInteractions;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlassBottleItem.class)
public class GlassBottleItemMixin {
    @Redirect(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/GlassBottleItem;raycast(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/world/RaycastContext$FluidHandling;)Lnet/minecraft/util/hit/BlockHitResult;"
            )
    )
    private BlockHitResult resprouted$redirectRaycast(World world, PlayerEntity user, RaycastContext.FluidHandling fluidHandling) {
        Vec3d cameraPos = user.getCameraPosVec(1.0F);
        Vec3d rotation = user.getRotationVec(1.0F);
        Vec3d reach = cameraPos.add(rotation.multiply(5.0D));
        BlockHitResult hit = world.raycast(new RaycastContext(cameraPos, reach, RaycastContext.ShapeType.OUTLINE, fluidHandling, user));

        if (hit.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = hit.getBlockPos();
            BlockState state = world.getBlockState(pos);

            if (BottleInteractions.getFluidData(state.getBlock()) != null) {
                return BlockHitResult.createMissed(hit.getPos(), hit.getSide(), pos);
            }
        }

        return hit;
    }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void resprouted$injectUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack itemStack = user.getStackInHand(hand);

        Vec3d cameraPos = user.getCameraPosVec(0.0F);
        Vec3d rotation = user.getRotationVec(0.0F);
        Vec3d reach = cameraPos.add(rotation.multiply(5.0D));
        BlockHitResult hit = world.raycast(new RaycastContext(cameraPos, reach,
                RaycastContext.ShapeType.OUTLINE,
                RaycastContext.FluidHandling.SOURCE_ONLY,
                user));

        if (hit.getType() != HitResult.Type.BLOCK) return;

        BlockPos pos = hit.getBlockPos();
        BlockState state = world.getBlockState(pos);
        BottleInteractions.FluidBottleData data = BottleInteractions.getFluidData(state.getBlock());

        if (data != null) {
            if (!world.isClient) {
                ItemStack filled = new ItemStack(data.bottleItem());

                user.incrementStat(Stats.USED.getOrCreateStat(Items.GLASS_BOTTLE));
                if (!user.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }

                if (!user.getInventory().insertStack(filled)) {
                    user.dropItem(filled, false);
                }

                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.PLAYERS, 1.0F, 1.0F);
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }

            cir.setReturnValue(TypedActionResult.success(itemStack, world.isClient));
            cir.cancel();
        }
    }
}
//No se que diablos hice, pero evita que aparezcan botellas de agua
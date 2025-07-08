package net.edu.resprouted.item.custom;


import net.edu.resprouted.entity.custom.ThrownTomatoEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TomatoItem extends Item {
    public TomatoItem(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (user.isSneaking()) {
            user.playSound(SoundEvents.ENTITY_SNOWBALL_THROW, 0.5f, 0.5f);

            if (!world.isClient()) {
                ThrownTomatoEntity tomato = new ThrownTomatoEntity(world, user);
                Vec3d pos = user.getEyePos().add(user.getRotationVec(1.0F).multiply(1.5));
                tomato.setPosition(pos);
                tomato.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 1.0f, 1.0f);
                tomato.setItem(stack.copyWithCount(1));
                world.spawnEntity(tomato);
            }
            if (!user.getAbilities().creativeMode) {
                stack.decrement(1);
            }
            return TypedActionResult.success(stack, world.isClient());
        }
        return super.use(world, user, hand);
    }
}
package net.edu.resprouted.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import java.util.UUID;

public class FullMetalEffect extends StatusEffect {
    private static final UUID MOVEMENT_SPEED_MODIFIER_ID = UUID.fromString("0CA3AAC1-2CD1-4552-AC7E-DD4984B8D7A3");
    private static final UUID ATTACK_SPEED_MODIFIER_ID = UUID.fromString("1809F109-92FE-410A-B78C-3BB9D0C4CC9E");
    private static final UUID KNOCKBACK_RESISTANCE_MODIFIER_ID = UUID.fromString("639E0E9E-0B0D-474C-86FA-626901789BAC");
    private static final UUID JUMP_STRENGTH_MODIFIER_ID = UUID.fromString("A5B4C3D2-1E0F-4A9B-8C7D-6E5F40392817");

    public FullMetalEffect(StatusEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(
                EntityAttributes.GENERIC_MOVEMENT_SPEED,
                Identifier.of(MOVEMENT_SPEED_MODIFIER_ID.toString()), -1.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(
                EntityAttributes.GENERIC_ATTACK_SPEED,
                Identifier.of(ATTACK_SPEED_MODIFIER_ID.toString()), -0.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(
                EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
                Identifier.of(KNOCKBACK_RESISTANCE_MODIFIER_ID.toString()), 9001.0, EntityAttributeModifier.Operation.ADD_VALUE);
        this.addAttributeModifier(
                EntityAttributes.GENERIC_JUMP_STRENGTH,
                Identifier.of(JUMP_STRENGTH_MODIFIER_ID.toString()), -1.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }
    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
     if (!(entity instanceof PlayerEntity player)) return false;
        if (player.isFallFlying()) {
            player.stopFallFlying();
            player.setVelocity(entity.getVelocity().add(0, -10, 0));
        }
        if (player.isTouchingWaterOrRain() || player.isInLava()) {
        Vec3d vel = player.getVelocity();
        player.setVelocity(new Vec3d(vel.x * 0, -10, vel.z * 0));
        player.setSwimming(false);
        }
        player.setJumping(false);
        player.setSprinting(false);
        return true;
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
    @Override
    public void playApplySound(LivingEntity entity, int amplifier) {
        entity.getWorld().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, 1.0f, 1.0f);
    }
}


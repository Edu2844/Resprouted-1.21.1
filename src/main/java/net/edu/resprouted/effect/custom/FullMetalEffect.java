package net.edu.resprouted.effect.custom;

import net.edu.resprouted.Resprouted;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class FullMetalEffect extends StatusEffect {
    private static final Identifier MOVEMENT_SPEED_MODIFIER_ID = Identifier.of(Resprouted.MOD_ID, "full_metal_movement_speed");
    private static final Identifier ATTACK_SPEED_MODIFIER_ID = Identifier.of(Resprouted.MOD_ID, "full_metal_attack_speed");
    private static final Identifier KNOCKBACK_RESISTANCE_MODIFIER_ID = Identifier.of(Resprouted.MOD_ID, "full_metal_knockback_resistance");
    private static final Identifier JUMP_STRENGTH_MODIFIER_ID = Identifier.of(Resprouted.MOD_ID, "full_metal_jump_strength");
    private static final Identifier GRAVITY_MODIFIER_ID = Identifier.of(Resprouted.MOD_ID, "full_metal_gravity");
    private static final Identifier STEP_HEIGHT_MODIFIER_ID = Identifier.of(Resprouted.MOD_ID, "full_metal_step_height");
    private static final Identifier WATER_MOVEMENT_MODIFIER_ID = Identifier.of(Resprouted.MOD_ID, "full_metal_water_movement");

    public FullMetalEffect(StatusEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                MOVEMENT_SPEED_MODIFIER_ID, -1.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
        );
        this.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED,
                ATTACK_SPEED_MODIFIER_ID, -0.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
        );
        this.addAttributeModifier(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
                KNOCKBACK_RESISTANCE_MODIFIER_ID, 1.0, EntityAttributeModifier.Operation.ADD_VALUE
        );
        this.addAttributeModifier(EntityAttributes.GENERIC_JUMP_STRENGTH,
                JUMP_STRENGTH_MODIFIER_ID, -1.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
        );
        this.addAttributeModifier(
                EntityAttributes.GENERIC_GRAVITY,
                GRAVITY_MODIFIER_ID, 1.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
        );
        this.addAttributeModifier(
                EntityAttributes.GENERIC_STEP_HEIGHT,
                STEP_HEIGHT_MODIFIER_ID, -1.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
        );
        this.addAttributeModifier(EntityAttributes.GENERIC_WATER_MOVEMENT_EFFICIENCY,
                WATER_MOVEMENT_MODIFIER_ID, -1.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
        );
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!(entity instanceof PlayerEntity player)) {
            return true;
        }

        if (player.isFallFlying()) {
            player.stopFallFlying();
            player.setVelocity(player.getVelocity().multiply(1.0, 0.0, 1.0).add(0, -0.5, 0));
        }

        if (player.isTouchingWaterOrRain() || player.isInLava()) {
            Vec3d vel = player.getVelocity();
            player.setVelocity(vel.multiply(0.0, 1.0, 0.0).add(0, -0.3, 0));
            player.setSwimming(false);
        }

        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}


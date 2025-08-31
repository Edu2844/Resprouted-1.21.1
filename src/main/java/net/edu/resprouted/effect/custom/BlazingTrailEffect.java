package net.edu.resprouted.effect.custom;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlazingTrailEffect extends StatusEffect {
    public BlazingTrailEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return (duration % 10) == 0;
    }
    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient() && entity.isOnGround()) {
            World world = entity.getWorld();
            BlockPos pos = entity.getBlockPos();

            if (world.getBlockState(pos).isAir() && world.getBlockState(pos.down()).isSolidBlock(world, pos.down())) {

                world.setBlockState(pos, Blocks.FIRE.getDefaultState());
            }
        }
        return true;
    }
}

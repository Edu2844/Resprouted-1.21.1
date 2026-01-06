package net.edu.resprouted.block.custom.agriculture;

import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;

public class CustomCakeBlock extends CakeBlock {
    private final RegistryEntry<StatusEffect> statusEffect;
    private final int effectDuration;
    private final int effectAmplifier;

    public CustomCakeBlock(Settings settings) {
        this(settings, null, 0, 0);
    }

    public CustomCakeBlock(Settings settings, RegistryEntry<StatusEffect> statusEffect, int effectDuration, int effectAmplifier) {
        super(settings);
        this.statusEffect = statusEffect;
        this.effectDuration = effectDuration;
        this.effectAmplifier = effectAmplifier;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            if (customTryEat(world, pos, state, player).isAccepted()) {
                return ActionResult.SUCCESS;
            }
            if (player.getStackInHand(Hand.MAIN_HAND).isEmpty()) {
                return ActionResult.CONSUME;
            }
        }

        return customTryEat(world, pos, state, player);
    }

    protected ActionResult customTryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        }

        player.incrementStat(Stats.EAT_CAKE_SLICE);
        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.BLOCKS, 1.0F, 1.0F);
        player.getHungerManager().add(2, 0.1F);

        if (statusEffect != null && !world.isClient()) {
            player.addStatusEffect(new StatusEffectInstance(
                    statusEffect,
                    effectDuration,
                    effectAmplifier
            ));
        }


        if (world instanceof World serverWorld) {
            BlockState blockState = world.getBlockState(pos);

            for (int i = 0; i < 8; i++) {
                double offsetX = (serverWorld.random.nextDouble() - 0.5) * 0.5;
                double offsetY = serverWorld.random.nextDouble() * 0.5;
                double offsetZ = (serverWorld.random.nextDouble() - 0.5) * 0.5;

                serverWorld.addParticle(
                        new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState),
                        pos.getX() + 0.5 + offsetX,
                        pos.getY() + 0.5 + offsetY,
                        pos.getZ() + 0.5 + offsetZ,
                        offsetX * 0.1,
                        offsetY * 0.1 + 0.05,
                        offsetZ * 0.1
                );
            }
        }

        int bites = state.get(BITES);
        world.emitGameEvent(player, GameEvent.EAT, pos);

        if (bites < MAX_BITES) {
            world.setBlockState(pos, state.with(BITES, bites + 1), Block.NOTIFY_ALL);
        } else {
            world.removeBlock(pos, false);
            world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        }

        return ActionResult.SUCCESS;
    }
}
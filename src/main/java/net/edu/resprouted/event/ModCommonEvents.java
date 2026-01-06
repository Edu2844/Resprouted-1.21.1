package net.edu.resprouted.event;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.item.custom.StakeCropSeedItem;
import net.edu.resprouted.registry.GrassSeedRegistry;
import net.edu.resprouted.util.RecipeUtils;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ModCommonEvents {
    public static void registerEvents() {
        Resprouted.LOGGER.info("Registering Events for " + Resprouted.MOD_ID);
        ServerLivingEntityEvents.ALLOW_DEATH.register(ModCommonEvents::onAllowDeath);
        UseItemCallback.EVENT.register(ModCommonEvents::onWaterBottleUse);
        AttackEntityCallback.EVENT.register(ModCommonEvents::onAttackEntity);
        EntitySleepEvents.ALLOW_SLEEPING.register(ModCommonEvents::onPlayerSleep);
        PlayerBlockBreakEvents.AFTER.register(ModCommonEvents::onBlockBreak);
    }

    /**
     * Note to my future self: We didn't use LootTableEvents because they require a game restart
     * or /reload command to apply config changes. We use PlayerBlockBreakEvents instead
     * to allow real-time config updates without interrupting gameplay. You can change it if you want.
     */
    private static void onBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        if (world.isClient()) return;
        if (!Resprouted.COMMON_CONFIG.world.isEnableSeedDrops() || player.isCreative()) return;

        if (state.isOf(Blocks.SHORT_GRASS) || state.isOf(Blocks.TALL_GRASS)) {
            float seedChance = Resprouted.COMMON_CONFIG.world.getSeedsDropRate() / 100.0f;

            for (StakeCropSeedItem seed : GrassSeedRegistry.getRegisteredSeeds()) {
                if (world.getRandom().nextFloat() < seedChance) {
                    dropItem(world, pos, new ItemStack(seed));
                }
            }
        }

        if (state.isOf(Blocks.VINE)) {
            boolean shouldDrop = false;

            if (!Resprouted.COMMON_CONFIG.world.isGrapeDropNeedsTool()) {
                shouldDrop = true;
            } else {
                ItemStack stack = player.getMainHandStack();

                if (!stack.isEmpty()) {
                    Identifier itemId = Registries.ITEM.getId(stack.getItem());
                    String itemName = itemId.toString();

                    if (Resprouted.COMMON_CONFIG.world.getGrapeSeedsToolWhitelist().contains(itemName)) {
                        shouldDrop = true;
                    }
                }
            }

            float seedChance = Resprouted.COMMON_CONFIG.world.getSeedsDropRate() / 100.0f;

            if (shouldDrop && world.getRandom().nextFloat() < seedChance) {
                dropItem(world, pos, new ItemStack(ModBlocks.GRAPE_STEM));
            }
        }
    }

    private static void dropItem(World world, BlockPos pos, ItemStack stack) {
        ItemEntity itemEntity = new ItemEntity(
                world,
                pos.getX() + 0.5,
                pos.getY() + 0.5,
                pos.getZ() + 0.5,
                stack
        );
        itemEntity.setToDefaultPickupDelay();
        world.spawnEntity(itemEntity);
    }

    @Nullable
    private static PlayerEntity.SleepFailureReason onPlayerSleep(PlayerEntity player, BlockPos sleepingPos) {
        if (player.hasStatusEffect(ModEffects.FULL_METAL)) {
            return PlayerEntity.SleepFailureReason.OTHER_PROBLEM;
        }
        return null;
    }

    @SuppressWarnings("SameReturnValue")
    private static ActionResult onAttackEntity(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (world.isClient() || !(entity instanceof LivingEntity target)) {
            return ActionResult.PASS;
        }

        ItemStack weapon = player.getStackInHand(hand);

        if (weapon.contains(ModDataComponentTypes.VANTA_OILED)) {
            StatusEffectInstance currentEffect = weapon.get(ModDataComponentTypes.VANTA_OILED);

            if (currentEffect != null) {
                if (!target.canTakeDamage()) {
                    return ActionResult.PASS;
                }

                int totalDuration = currentEffect.getDuration();
                int amplifier = currentEffect.getAmplifier();
                RegistryEntry<StatusEffect> effectType = currentEffect.getEffectType();

                if (totalDuration < 1 || amplifier < 0) {
                    weapon.remove(ModDataComponentTypes.VANTA_OILED);
                    return ActionResult.PASS;
                }

                boolean isInstant = effectType.value().isInstant();
                int hitDuration = isInstant ? 1 : RecipeUtils.getNextVantaHitDuration(totalDuration);
                int effectDuration = hitDuration;

                StatusEffectInstance activeEffect = target.getStatusEffect(effectType);

                if (activeEffect != null) {
                    int activeDur = activeEffect.getDuration();

                    if (activeDur <= 0) {
                        target.removeStatusEffect(effectType);
                    } else if (activeEffect.getAmplifier() == amplifier) {
                        effectDuration += activeDur;
                    } else if (activeEffect.getAmplifier() > amplifier) {
                        effectDuration = 0;
                    }
                }

                if (effectDuration > 0) {
                    if (isInstant) {
                        effectType.value().applyInstantEffect(player, player, target, amplifier, 1.0);
                    } else {
                        target.addStatusEffect(new StatusEffectInstance(
                                effectType,
                                effectDuration,
                                amplifier,
                                currentEffect.isAmbient(),
                                currentEffect.shouldShowParticles(),
                                currentEffect.shouldShowIcon()
                        ), player);
                    }
                }

                int newDuration = totalDuration - hitDuration;

                StatusEffectInstance newVantaEffect = null;
                if (newDuration > 0) {
                    newVantaEffect = new StatusEffectInstance(
                            effectType,
                            newDuration,
                            amplifier,
                            currentEffect.isAmbient(),
                            currentEffect.shouldShowParticles(),
                            currentEffect.shouldShowIcon());
                }

                if (weapon.getCount() > 1) {
                    ItemStack remainingStack = weapon.split(weapon.getCount() - 1);
                    weapon.set(ModDataComponentTypes.VANTA_OILED, newVantaEffect);

                    if (!player.getInventory().insertStack(remainingStack)) {
                        player.dropItem(remainingStack, false);
                    }
                } else {
                    if (newVantaEffect == null) {
                        weapon.remove(ModDataComponentTypes.VANTA_OILED);
                    } else {
                        weapon.set(ModDataComponentTypes.VANTA_OILED, newVantaEffect);
                    }
                }
            }
        }
        return ActionResult.PASS;
    }


    private static TypedActionResult<ItemStack> onWaterBottleUse(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.getItem() == Items.POTION) {
            PotionContentsComponent potionContents = stack.get(DataComponentTypes.POTION_CONTENTS);
            if (potionContents != null && potionContents.matches(Potions.WATER)) {

                StatusEffectInstance effect = player.getStatusEffect(ModEffects.TIPSY);
                if (effect != null && !world.isClient()) {
                    Random random = player.getRandom();
                    int duration = effect.getDuration();
                    int amplifier = effect.getAmplifier();

                    if (random.nextFloat() < 0.1F) {
                        amplifier--;
                    } else {
                        duration -= (random.nextInt(800) + 200);
                    }
                    player.removeStatusEffect(ModEffects.TIPSY);

                    if (amplifier >= 0 && duration > 0) {
                        player.addStatusEffect(new StatusEffectInstance(
                                ModEffects.TIPSY,
                                duration,
                                amplifier,
                                false,
                                false
                        ));
                    }
                }
            }
        }
        return TypedActionResult.pass(stack);
    }

    private static boolean onAllowDeath(LivingEntity entity, DamageSource source, float damageAmount) {
        if (source.isSourceCreativePlayer()) return true;
        if (!(entity instanceof ServerPlayerEntity player)) return true;

        StatusEffectInstance effect = player.getStatusEffect(ModEffects.UNDYING);
        if (effect == null) return true;

        player.removeStatusEffect(ModEffects.UNDYING);

        player.setHealth(1.0f);
        player.clearStatusEffects();
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
        player.getWorld().sendEntityStatus(player, EntityStatuses.USE_TOTEM_OF_UNDYING);

        if (effect.getAmplifier() > 0) {
            player.addStatusEffect(new StatusEffectInstance(
                    ModEffects.UNDYING,
                    effect.getDuration(),
                    effect.getAmplifier() - 1,
                    effect.isAmbient(),
                    effect.shouldShowParticles(),
                    effect.shouldShowIcon()
            ));
        }
        return false;
    }
}

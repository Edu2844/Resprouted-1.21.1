package net.edu.resprouted.effect;

import net.edu.resprouted.item.custom.BoozeBottleItem.*;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BoozeEffects {

    //Ale
    public static void applyAleEffects(BoozeConsumptionContext context) {
        PlayerEntity player = context.player();
        float quality = context.quality();

        if (quality >= 0.5F) {
            player.getHungerManager().add(2, 4F * quality);
            int duration = 1200 + ((int) (10800 * quality));
            player.addStatusEffect(new StatusEffectInstance(ModEffects.FULL_STOMACH, duration));
        } else {
            int duration = (int) (6000 * (1 - quality));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, duration));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, duration));
        }
    }

    //Iron Wine
    public static void applyIronWineEffects(BoozeConsumptionContext context) {
        PlayerEntity player = context.player();
        World world = context.world();
        float quality = context.quality();

        if (world.isClient) return;

        if (quality >= 0.5F) {
            player.getHungerManager().add(1, 2F * quality);

            float a = 10F * (Math.max((quality - 0.5F) * 2F, 0F));
            float currentAbs = player.getAbsorptionAmount();
            float newAbs = Math.min(currentAbs + a, 20F);

            EntityAttributeInstance maxAb = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_ABSORPTION);
            if (maxAb != null && maxAb.getBaseValue() < newAbs) {
                maxAb.setBaseValue(newAbs);
            }

            player.setAbsorptionAmount(newAbs);

        } else {
            int duration = (int) (6000 * Math.max(1 - quality, 0.25));
            float damage = 10F * (Math.max(Math.abs(quality - 0.5F) + 0.1F, 0.25F));
            player.damage(player.getDamageSources().magic(), damage);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, duration));
        }
    }

    //Cider
    public static void applyCiderEffects(BoozeConsumptionContext context) {
        PlayerEntity player = context.player();
        float quality = context.quality();

        if (quality >= 0.5F) {
            player.getHungerManager().add(1, 2F * quality);

            int duration = 1200 + ((int) (10800 * (Math.max(Math.abs((quality - 0.5F) * 2F), 0F))));
            player.addStatusEffect(new StatusEffectInstance(ModEffects.MAGIC_RESISTANCE, duration));

        } else {
            int poisonDuration = (int) (1200 * Math.max(1 - quality, 0.25F));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, poisonDuration));

            int nauseaDuration = (int) (6000 * Math.max(1 - quality, 0.25F));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, nauseaDuration));
        }
    }

    //Mead
    public static void applyMeadEffects(BoozeConsumptionContext context) {
        PlayerEntity player = context.player();
        float quality = context.quality();

        if (quality >= 0.5F) {
            player.getHungerManager().add(1, 2F * quality);

            int duration = 1200 + ((int) (6000 * (Math.max(Math.abs((quality - 0.5F) * 2F), 0F))));
            player.addStatusEffect(new StatusEffectInstance(ModEffects.WITHER_WARD, duration));

        } else {
            int witherDuration = (int) (800 * Math.max(1 - quality, 0.25));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, witherDuration));

            int nauseaDuration = (int) (6000 * Math.max(1 - quality, 0.25));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, nauseaDuration));
        }
    }

    //Sweet Berry Wine
    public static void applySweetBerryWineEffects(BoozeConsumptionContext context) {
        PlayerEntity player = context.player();
        float quality = context.quality();

        if (quality >= 0.5F) {

            player.getHungerManager().add(1, 2F * quality);

            for (StatusEffectInstance effect : player.getStatusEffects()) {
                if (effect.getEffectType().value().getCategory() == StatusEffectCategory.BENEFICIAL &&
                        effect.getAmplifier() < 2) {player.addStatusEffect(
                                new StatusEffectInstance(
                                        effect.getEffectType(),
                                        effect.getDuration(),
                                        effect.getAmplifier() + 1,
                                        effect.isAmbient(),
                                        effect.shouldShowParticles(),
                                        effect.shouldShowIcon()
                    ));
                }
            }

        } else {
            for (StatusEffectInstance effect : player.getStatusEffects()) {
                if (effect.getEffectType().value().getCategory() == StatusEffectCategory.BENEFICIAL) {
                    if (effect.getAmplifier() > 0) {

                        player.removeStatusEffect(effect.getEffectType());
                        player.addStatusEffect(new StatusEffectInstance(
                                effect.getEffectType(),
                                effect.getDuration(),
                                effect.getAmplifier() - 1,
                                effect.isAmbient(),
                                effect.shouldShowParticles(),
                                effect.shouldShowIcon()
                        ));
                    } else if (effect.getAmplifier() == 0) {
                        player.removeStatusEffect(effect.getEffectType());
                    }
                }
            }

            int nauseaDuration = (int) (6000 * Math.max(1 - quality, 0.25F));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, nauseaDuration));
        }
    }

    //Wine
    public static void applyWineEffects(BoozeConsumptionContext context) {
        PlayerEntity player = context.player();
        float quality = context.quality();

        if (quality >= 0.5F) {

            player.getHungerManager().add(1, 2F * quality);

            int durationIncrease = 600 + ((int) (2400 * ((quality - 0.5F) * 2F)));

            for (StatusEffectInstance effect : player.getStatusEffects()) {
                if (effect.getEffectType().value().getCategory() == StatusEffectCategory.BENEFICIAL &&
                        effect.getDuration() < 12000) {

                    int newDuration = Math.min(effect.getDuration() + durationIncrease, 12000);
                    player.addStatusEffect(new StatusEffectInstance(
                            effect.getEffectType(),
                            newDuration,
                            effect.getAmplifier(),
                            effect.isAmbient(),
                            effect.shouldShowParticles(),
                            effect.shouldShowIcon()
                    ));
                }
            }

        } else {

            int durationDecrease = (int) (2400 * (Math.abs(quality - 0.6)));

            for (StatusEffectInstance effect : player.getStatusEffects()) {
                if (effect.getEffectType().value().getCategory() == StatusEffectCategory.BENEFICIAL) {
                    int newDuration = effect.getDuration() - durationDecrease;

                    player.removeStatusEffect(effect.getEffectType());
                    if (newDuration > 0) {
                        player.addStatusEffect(new StatusEffectInstance(
                                effect.getEffectType(),
                                newDuration,
                                effect.getAmplifier(),
                                effect.isAmbient(),
                                effect.shouldShowParticles(),
                                effect.shouldShowIcon()
                        ));
                    }
                }
            }

            int nauseaDuration = (int) (6000 * Math.max(1 - quality, 0));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, nauseaDuration));
        }
    }

    //Ambrosia
    public static void applyAmbrosiaEffects(BoozeConsumptionContext context) {
        PlayerEntity player = context.player();
        float quality = context.quality();

        if (quality >= 0.5F) {
            player.getHungerManager().add(2, 2F * quality);

            int duration;
            if (quality > 0.99F) {
                duration = StatusEffectInstance.INFINITE;
                player.removeStatusEffect(ModEffects.UNDYING);
            } else {
                duration = (20 * 300) + ((int) (18000 * (Math.max(Math.abs((quality - 0.5F) * 2F), 0.00F))));
            }
            player.addStatusEffect(new StatusEffectInstance(ModEffects.UNDYING, duration, 0, false, false));

        } else {
            player.damage(player.getDamageSources().magic(), Float.MAX_VALUE);
        }
    }

    //Glow Berry Wine
    public static void applyGlowBerryWineEffects(BoozeConsumptionContext context) {
        PlayerEntity player = context.player();
        float quality = context.quality();

        if (quality >= 0.5F) {
            player.getHungerManager().add(1, 2F * quality);

            int baseDuration = 1200;
            int bonusDuration = (int) (4800 * quality);

            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.NIGHT_VISION,
                    baseDuration + bonusDuration,
                    0, false, false, true
            ));

            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.GLOWING,
                    baseDuration + bonusDuration,
                    0, false, false, true
            ));

            if (quality >= 0.8F) {

                int resistanceDuration = (int) (3600 * (quality - 0.8F) * 5);
                player.addStatusEffect(new StatusEffectInstance(
                        ModEffects.WITHER_WARD,
                        resistanceDuration,
                        0, false, false, true
                ));
            }

        }

        else {
            player.getHungerManager().add(1, 0.5F * quality);

            int negativeDuration = (int) (6000 * (1 - quality));

            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.BLINDNESS,
                    negativeDuration,
                    0, false, true, true
            ));

            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.NAUSEA,
                    negativeDuration / 2,
                    0, false, true, true
            ));

            if (quality <= 0.2F) {

                player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.DARKNESS,
                        negativeDuration / 2,
                        0, false, true, true
                ));
            }
        }
    }

    //Rum
    public static void applyRumEffects(BoozeConsumptionContext context) {
        PlayerEntity player = context.player();
        float quality = context.quality();
        boolean hasStrongTipsy = false;

        StatusEffectInstance tipsyEffect = player.getStatusEffect(ModEffects.TIPSY);
        if (tipsyEffect != null && tipsyEffect.getAmplifier() >= 2) {
            hasStrongTipsy = true;
        }

        if (quality >= 0.75F) {
            player.getHungerManager().add(2, 3.0F * quality);

            if (!hasStrongTipsy) {
                List<RegistryEntry<StatusEffect>> effectsToRemove = new ArrayList<>();
                for (StatusEffectInstance effect : player.getStatusEffects()) {
                    if (effect.getEffectType().value().getCategory() == StatusEffectCategory.HARMFUL) {
                        effectsToRemove.add(effect.getEffectType());
                    }
                }
                for (RegistryEntry<StatusEffect> effect : effectsToRemove) {
                    player.removeStatusEffect(effect);
                }
            }

        } else if (quality >= 0.5F) {
            player.getHungerManager().add(1, 1.5F * quality);

            if (!hasStrongTipsy) {
                List<RegistryEntry<StatusEffect>> harmfulEffects = new ArrayList<>();
                for (StatusEffectInstance effect : player.getStatusEffects()) {
                    if (effect.getEffectType().value().getCategory() == StatusEffectCategory.HARMFUL) {
                        harmfulEffects.add(effect.getEffectType());
                    }
                }
                if (!harmfulEffects.isEmpty()) {
                    RegistryEntry<StatusEffect> randomEffect = harmfulEffects.get(
                            player.getRandom().nextInt(harmfulEffects.size())
                    );
                    player.removeStatusEffect(randomEffect);
                }
            }

        } else if (quality >= 0.2F) {
            int duration = (int) (6000 * (1 - quality));
            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.WEAKNESS,
                    duration,
                    0,
                    false,
                    false,
                    true
            ));
            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.MINING_FATIGUE,
                    duration / 2,
                    0,
                    false,
                    false,
                    true
            ));

        } else {
            List<RegistryEntry<StatusEffect>> positiveEffectsToRemove = new ArrayList<>();
            for (StatusEffectInstance effect : player.getStatusEffects()) {
                if (effect.getEffectType().value().getCategory() == StatusEffectCategory.BENEFICIAL) {
                    positiveEffectsToRemove.add(effect.getEffectType());
                }
            }
            for (RegistryEntry<StatusEffect> effect : positiveEffectsToRemove) {
                player.removeStatusEffect(effect);
            }
            int duration = (int) (8000 * (1 - quality));
            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.WEAKNESS,
                    duration,
                    1,
                    false,
                    false,
                    true
            ));
            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.MINING_FATIGUE,
                    duration,
                    1,
                    false,
                    false,
                    true
            ));
        }
    }
}



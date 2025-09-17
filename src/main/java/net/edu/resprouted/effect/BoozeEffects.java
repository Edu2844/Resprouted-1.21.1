package net.edu.resprouted.effect;

import net.edu.resprouted.item.custom.BoozeBottleItem;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class BoozeEffects {

    public static void applyAleEffects(BoozeBottleItem.BoozeConsumptionContext context) {
        PlayerEntity player = context.player();
        float quality = context.quality();

        if (quality >= 0.5F) {
            player.getHungerManager().add(2, 4F * quality);
            int duration = 1200 + ((int) (10800 * quality));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, duration));
        } else {
            int duration = (int) (6000 * (1 - quality));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, duration));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, duration));
        }
    }

    public static void applyIronWineEffects(BoozeBottleItem.BoozeConsumptionContext context) {
        PlayerEntity player = context.player();
        float quality = context.quality();

        if (quality >= 0.5F) {
            player.getHungerManager().add(1, 2F * quality);

            float absorption = 10F * (Math.max((quality - 0.5F) * 2F, 0F));
            float currentAbsorption = player.getAbsorptionAmount();
            player.setAbsorptionAmount(Math.min(currentAbsorption + absorption, 20F));

        } else {
            int duration = (int) (6000 * Math.max(1 - quality, 0.25));

            float damage = 10F * (Math.max(Math.abs(quality - 0.5F) + 0.1F, 0.25F));
            player.damage(player.getDamageSources().magic(), damage);

            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, duration));
        }
    }

    public static void applyCiderEffects(BoozeBottleItem.BoozeConsumptionContext context) {
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

    public static void applyMeadEffects(BoozeBottleItem.BoozeConsumptionContext context) {
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

    public static void applySweetBerryWineEffects(BoozeBottleItem.BoozeConsumptionContext context) {
        PlayerEntity player = context.player();
        float quality = context.quality();

        if (quality >= 0.5F) {

            player.getHungerManager().add(1, 2F * quality);

            for (StatusEffectInstance effect : player.getStatusEffects()) {
                if (effect.getEffectType().value().getCategory() == StatusEffectCategory.BENEFICIAL &&
                        effect.getAmplifier() < 2) {
                    player.addStatusEffect(new StatusEffectInstance(
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

    public static void applyWineEffects(BoozeBottleItem.BoozeConsumptionContext context) {
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

                    if (newDuration > 0) {
                        player.removeStatusEffect(effect.getEffectType());
                        player.addStatusEffect(new StatusEffectInstance(
                                effect.getEffectType(),
                                newDuration,
                                effect.getAmplifier(),
                                effect.isAmbient(),
                                effect.shouldShowParticles(),
                                effect.shouldShowIcon()
                        ));
                    } else {
                        player.removeStatusEffect(effect.getEffectType());
                    }
                }
            }

            int nauseaDuration = (int) (6000 * Math.max(1 - quality, 0));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, nauseaDuration));
        }
    }
    public static void applyAmbrosiaEffects(BoozeBottleItem.BoozeConsumptionContext context) {
        PlayerEntity player = context.player();
        float quality = context.quality();

        if (quality >= 0.5F) {
            player.getHungerManager().add(2, 2F * quality);

            int duration;
            if (quality > 0.99F) {
                duration = Integer.MAX_VALUE;
                player.removeStatusEffect(ModEffects.UNDYING);
            } else {
                duration = (20 * 300) + ((int) (18000 * (Math.max(Math.abs((quality - 0.5F) * 2F), 0.00F))));
            }
            player.addStatusEffect(new StatusEffectInstance(ModEffects.UNDYING, duration, 0, false, false));

        } else {
            player.damage(player.getDamageSources().magic(), Float.MAX_VALUE);
        }
    }
}



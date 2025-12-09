package net.edu.resprouted.networking;

import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.networking.payload.EntityEffectSyncPayload;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;
import java.util.Set;

public class EffectSyncHandler {
    private static int tickCounter = 0;

    private static final Set<RegistryEntry<StatusEffect>> VISUAL_EFFECTS = Set.of(
            ModEffects.IRON_SKIN,
            ModEffects.FULL_METAL
    );

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            tickCounter++;

            if (tickCounter >= 20) {
                tickCounter = 0;

                for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {

                    player.getServerWorld().getEntitiesByClass(LivingEntity.class,
                                    player.getBoundingBox().expand(64),
                                    entity -> entity != player && hasVisualEffects(entity)).forEach(entity -> {

                                List<StatusEffectInstance> visualEffects = entity.getStatusEffects().stream()
                                        .filter(effect -> VISUAL_EFFECTS.contains(effect.getEffectType()))
                                        .toList();

                                if (!visualEffects.isEmpty()) {
                                    ServerPlayNetworking.send(player,
                                            new EntityEffectSyncPayload(entity.getId(), visualEffects)
                                    );
                                }
                            });
                }
            }
        });
    }

    private static boolean hasVisualEffects(LivingEntity entity) {
        return entity.getStatusEffects().stream().anyMatch(effect -> VISUAL_EFFECTS.contains(effect.getEffectType()));
    }
}
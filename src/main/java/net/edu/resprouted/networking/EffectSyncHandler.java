package net.edu.resprouted.networking;

import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.networking.payload.EntityEffectSyncPayload;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.*;
import java.util.stream.Collectors;

public class EffectSyncHandler {
    private static int tickCounter = 0;
    private static final Map<Integer, Set<RegistryEntry<StatusEffect>>> previousEffects = new HashMap<>();

    public static final Set<RegistryEntry<StatusEffect>> VISUAL_EFFECTS = Set.of(
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
                            entity -> entity != player
                    ).forEach(entity -> syncEntityEffects(player, entity));
                }

                cleanupStaleEntries(server);
            }
        });
    }

    private static void syncEntityEffects(ServerPlayerEntity player, LivingEntity entity) {
        Set<RegistryEntry<StatusEffect>> currentEffects = entity.getStatusEffects().stream()
                .map(StatusEffectInstance::getEffectType)
                .filter(VISUAL_EFFECTS::contains)
                .collect(Collectors.toSet());

        Set<RegistryEntry<StatusEffect>> previous = previousEffects.getOrDefault(
                entity.getId(),
                Collections.emptySet()
        );

        if (!currentEffects.equals(previous)) {
            List<StatusEffectInstance> visualEffects = entity.getStatusEffects().stream()
                    .filter(effect -> VISUAL_EFFECTS.contains(effect.getEffectType()))
                    .toList();

            ServerPlayNetworking.send(player,
                    new EntityEffectSyncPayload(entity.getId(), visualEffects)
            );

            if (currentEffects.isEmpty()) {
                previousEffects.remove(entity.getId());
            } else {
                previousEffects.put(entity.getId(), new HashSet<>(currentEffects));
            }
        }
    }

    private static void cleanupStaleEntries(MinecraftServer server) {
        previousEffects.keySet().removeIf(entityId -> {
            for (ServerWorld world : server.getWorlds()) {
                if (world.getEntityById(entityId) != null) {
                    return false;
                }
            }
            return true;
        });
    }
}
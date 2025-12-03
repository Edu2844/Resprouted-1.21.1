package net.edu.resprouted.networking;

import net.edu.resprouted.networking.payload.EntityEffectSyncPayload;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class EffectSyncHandler {
    private static int tickCounter = 0;

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            tickCounter++;

            if (tickCounter >= 20) {
                tickCounter = 0;

                for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                    player.getServerWorld().getEntitiesByClass(LivingEntity.class, player.getBoundingBox().expand(64),
                            entity -> !(entity instanceof ServerPlayerEntity)).forEach(entity -> ServerPlayNetworking.send(player,
                            new EntityEffectSyncPayload(entity.getId(), entity.getStatusEffects().stream().toList())
                    ));
                }
            }
        });
    }
}
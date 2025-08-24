package net.edu.resprouted.networking;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.networking.payload.FirePowerAttackPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class ModMessages {
    public static final Identifier FIRE_POWER_ATTACK = Identifier.of(Resprouted.MOD_ID, "fire_power_attack");

    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(FirePowerAttackPayload.ID, (payload, context) -> {
            ServerPlayerEntity player = context.player();
            MinecraftServer server = player.getServer();
            assert server != null;

            if (player.getItemCooldownManager().isCoolingDown(Items.SNOWBALL)) {
                return;
            }
            server.execute(() -> {

                if (player.hasStatusEffect(ModEffects.FIRE_POWER)) {Vec3d lookVec = player.getRotationVec(1.0F);

                    FireballEntity fireball = new FireballEntity(player.getWorld(), player, new Vec3d(lookVec.x, lookVec.y, lookVec.z), 1);
                    fireball.setPosition(player.getX() + lookVec.x, player.getEyeY() + lookVec.y, player.getZ() + lookVec.z
                    );
                    player.getWorld().spawnEntity(fireball);
                    player.getItemCooldownManager().set(Items.SNOWBALL, 20);
                }
            });
        });
    }
    public static void registerPayloads(){
        PayloadTypeRegistry.playC2S().register(FirePowerAttackPayload.ID, FirePowerAttackPayload.CODEC);

    }
}

package net.edu.resprouted.networking.payload;

import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.networking.ModMessages;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Items;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

public record FirePowerAttackPayload() implements CustomPayload {
    public static final CustomPayload.Id<FirePowerAttackPayload> ID = new CustomPayload.Id<>(ModMessages.FIRE_POWER_ATTACK);
    public static final PacketCodec<RegistryByteBuf, FirePowerAttackPayload> CODEC = PacketCodec.unit(new FirePowerAttackPayload());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    @SuppressWarnings("unused")
    public static void handle(FirePowerAttackPayload payload, ServerPlayNetworking.Context context) {
        ServerPlayerEntity player = context.player();
        MinecraftServer server = player.getServer();

        if (server == null || player.getItemCooldownManager().isCoolingDown(Items.SNOWBALL)) {
            return;
        }

        server.execute(() -> {
            if (player.hasStatusEffect(ModEffects.FIRE_POWER)) {
                Vec3d lookVec = player.getRotationVec(1.0F);

                FireballEntity fireball = new FireballEntity(player.getWorld(), player, new Vec3d(lookVec.x, lookVec.y, lookVec.z), 1);
                fireball.setPosition(player.getX() + lookVec.x, player.getEyeY() + lookVec.y, player.getZ() + lookVec.z);
                player.getWorld().spawnEntity(fireball);
                player.getItemCooldownManager().set(Items.SNOWBALL, 20);
            }
        });
    }
}
package net.edu.resprouted.networking.payload;


import net.edu.resprouted.networking.ModMessages;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record FirePowerAttackPayload() implements CustomPayload {
    public static final CustomPayload.Id<FirePowerAttackPayload> ID = new CustomPayload.Id<>(ModMessages.FIRE_POWER_ATTACK);
    public static final PacketCodec<RegistryByteBuf, FirePowerAttackPayload> CODEC = PacketCodec.unit(new FirePowerAttackPayload());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
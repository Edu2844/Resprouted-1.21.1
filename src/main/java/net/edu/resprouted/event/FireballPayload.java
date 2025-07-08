package net.edu.resprouted.event;


import net.edu.resprouted.Resprouted;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record FireballPayload() implements CustomPayload {
    public static final CustomPayload.Id<FireballPayload> ID = new CustomPayload.Id<>(Identifier.of(Resprouted.MOD_ID, "fireball"));
    public static final PacketCodec<RegistryByteBuf, FireballPayload> CODEC = PacketCodec.unit(new FireballPayload());


    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
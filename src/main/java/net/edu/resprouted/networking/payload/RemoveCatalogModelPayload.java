package net.edu.resprouted.networking.payload;

import net.edu.resprouted.networking.ModMessages;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record RemoveCatalogModelPayload() implements CustomPayload {
    public static final CustomPayload.Id<RemoveCatalogModelPayload> ID = new CustomPayload.Id<>(ModMessages.REMOVE_CATALOG_MODEL);
    public static final PacketCodec<RegistryByteBuf, RemoveCatalogModelPayload> CODEC = PacketCodec.unit(new RemoveCatalogModelPayload());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
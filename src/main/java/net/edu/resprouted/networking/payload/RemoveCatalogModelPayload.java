package net.edu.resprouted.networking.payload;

import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.networking.ModMessages;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;

public record RemoveCatalogModelPayload() implements CustomPayload {
    public static final CustomPayload.Id<RemoveCatalogModelPayload> ID = new CustomPayload.Id<>(ModMessages.REMOVE_CATALOG_MODEL);
    public static final PacketCodec<RegistryByteBuf, RemoveCatalogModelPayload> CODEC = PacketCodec.unit(new RemoveCatalogModelPayload());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    @SuppressWarnings("unused")
    public static void handle(RemoveCatalogModelPayload payload, ServerPlayNetworking.Context context) {
        ServerPlayerEntity player = context.player();
        MinecraftServer server = player.getServer();

        if (server == null) {
            return;
        }

        server.execute(() -> {
            for (Hand hand : Hand.values()) {
                ItemStack stack = player.getStackInHand(hand);
                if (stack.isOf(ModItems.CATALOG)) {
                    stack.remove(DataComponentTypes.CUSTOM_MODEL_DATA);
                }
            }
        });
    }
}
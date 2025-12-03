package net.edu.resprouted.networking.payload;

import net.edu.resprouted.networking.ModMessages;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

import java.util.ArrayList;
import java.util.List;

public record EntityEffectSyncPayload(int entityId, List<StatusEffectInstance> effects) implements CustomPayload {

    public static final Id<EntityEffectSyncPayload> ID = new Id<>(ModMessages.ENTITY_EFFECT_SYNC);

    public static final PacketCodec<RegistryByteBuf, EntityEffectSyncPayload> CODEC = new PacketCodec<>() {
        @Override
        public EntityEffectSyncPayload decode(RegistryByteBuf buf) {
            int entityId = buf.readVarInt();
            int size = buf.readVarInt();
            List<StatusEffectInstance> effects = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                effects.add(StatusEffectInstance.PACKET_CODEC.decode(buf));
            }
            return new EntityEffectSyncPayload(entityId, effects);
        }

        @Override
        public void encode(RegistryByteBuf buf, EntityEffectSyncPayload payload) {
            buf.writeVarInt(payload.entityId);
            buf.writeVarInt(payload.effects.size());
            for (StatusEffectInstance effect : payload.effects) {
                StatusEffectInstance.PACKET_CODEC.encode(buf, effect);
            }
        }
    };

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
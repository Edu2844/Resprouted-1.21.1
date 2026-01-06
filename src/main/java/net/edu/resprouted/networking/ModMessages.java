package net.edu.resprouted.networking;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.networking.payload.FirePowerAttackPayload;
import net.edu.resprouted.networking.payload.RemoveCatalogModelPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier FIRE_POWER_ATTACK = Identifier.of(Resprouted.MOD_ID, "fire_power_attack");
    public static final Identifier REMOVE_CATALOG_MODEL = Identifier.of(Resprouted.MOD_ID, "remove_catalog_model");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(FirePowerAttackPayload.ID, FirePowerAttackPayload::handle);
        ServerPlayNetworking.registerGlobalReceiver(RemoveCatalogModelPayload.ID, RemoveCatalogModelPayload::handle);
    }

    public static void registerPayloads() {
        PayloadTypeRegistry.playC2S().register(FirePowerAttackPayload.ID, FirePowerAttackPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RemoveCatalogModelPayload.ID, RemoveCatalogModelPayload.CODEC);
    }
}

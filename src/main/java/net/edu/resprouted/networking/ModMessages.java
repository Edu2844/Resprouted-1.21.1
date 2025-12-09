package net.edu.resprouted.networking;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.networking.payload.EntityEffectSyncPayload;
import net.edu.resprouted.networking.payload.FirePowerAttackPayload;
import net.edu.resprouted.networking.payload.RemoveCatalogModelPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;

public class ModMessages {
    public static final Identifier FIRE_POWER_ATTACK = Identifier.of(Resprouted.MOD_ID, "fire_power_attack");
    public static final Identifier ENTITY_EFFECT_SYNC = Identifier.of(Resprouted.MOD_ID, "entity_effect_sync");
    public static final Identifier REMOVE_CATALOG_MODEL = Identifier.of(Resprouted.MOD_ID, "remove_catalog_model");

    public static void registerC2SPackets(){

        //Fire Power Attack
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

        //Remove Catalog Custom Model Data
        ServerPlayNetworking.registerGlobalReceiver(RemoveCatalogModelPayload.ID, (payload, context) -> {
            ServerPlayerEntity player = context.player();
            MinecraftServer server = player.getServer();
            assert server != null;

            server.execute(() -> {
                for (Hand hand : Hand.values()) {
                    ItemStack stack = player.getStackInHand(hand);
                    if (stack.isOf(ModItems.CATALOG)) {
                        stack.remove(DataComponentTypes.CUSTOM_MODEL_DATA);
                    }
                }
            });
        });
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(EntityEffectSyncPayload.ID, (payload, context) -> context.client().execute(() -> {
            Entity entity = Objects.requireNonNull(context.client().world).getEntityById(payload.entityId());
            if (entity instanceof LivingEntity living) {
                living.getStatusEffects().clear();
                payload.effects().forEach(effect -> living.setStatusEffect(effect, null));
            }
        }));
    }

    public static void registerPayloads(){
        PayloadTypeRegistry.playC2S().register(FirePowerAttackPayload.ID, FirePowerAttackPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(EntityEffectSyncPayload.ID, EntityEffectSyncPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(RemoveCatalogModelPayload.ID, RemoveCatalogModelPayload.CODEC);

    }
}

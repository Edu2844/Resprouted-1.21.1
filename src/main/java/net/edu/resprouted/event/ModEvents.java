package net.edu.resprouted.event;


import net.edu.resprouted.Resprouted;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.util.TextUtils;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;


import java.util.List;

public class ModEvents {

    public static void registerModEvents() {
        Resprouted.LOGGER.info("Registering Events for " + Resprouted.MOD_ID);

        ItemTooltipCallback.EVENT.register(ModEvents::onItemTooltip);
        ItemTooltipCallback.EVENT.register(ModEvents::onWeaponTooltip);
        ItemTooltipCallback.EVENT.register(ModEvents::onHoneyTooltip);
        ServerLivingEntityEvents.ALLOW_DEATH.register(ModEvents::onAllowDeath);

    }

    // ========== TOOLTIP EVENTS ==========

    private static void onItemTooltip(ItemStack stack, Item.TooltipContext context, TooltipType type, List<Text> tooltip) {
        if (stack.get(ModDataComponentTypes.OILED) != null && Boolean.TRUE.equals(stack.get(ModDataComponentTypes.OILED))) {
            Text oiledText = Text.translatable("tooltip.resprouted.olive_oil").formatted(Formatting.DARK_GREEN);
            if (!tooltip.isEmpty()) {
                tooltip.add(1, oiledText);
            } else {
                tooltip.add(oiledText);
            }
        }
    }

    private static void onWeaponTooltip(ItemStack stack, Item.TooltipContext context, TooltipType type, List<Text> tooltip){
            TextUtils.addVantaOilEffectTooltip(stack, context, tooltip);
    }

    private static void onHoneyTooltip(ItemStack stack, Item.TooltipContext context, TooltipType type, List<Text> tooltip) {
        if (Resprouted.CONFIG.BottleEffectTooltips && Resprouted.CONFIG.EnableHoneyBottleEffect){
            TextUtils.addHoneyEffectTooltip(stack, context, tooltip);
        }
    }

    // ========== ENTITY EVENTS ==========

    private static boolean onAllowDeath(LivingEntity entity, DamageSource source, float damageAmount) {
        if (source.isSourceCreativePlayer()) return true;
        if (!(entity instanceof ServerPlayerEntity player)) return true;

        StatusEffectInstance effect = player.getStatusEffect(ModEffects.UNDYING);
        if (effect == null) return true;

        player.removeStatusEffect(ModEffects.UNDYING);

        player.setHealth(1.0f);
        player.clearStatusEffects();
        player.addStatusEffect(new StatusEffectInstance(
                StatusEffects.REGENERATION, 900, 1, false, false, true));
        player.addStatusEffect(new StatusEffectInstance(
                StatusEffects.ABSORPTION, 100, 1, false, false, true));

        player.getWorld().sendEntityStatus(player, (byte) 35);

        if (effect.getAmplifier() > 0) {
            player.addStatusEffect(new StatusEffectInstance(
                    ModEffects.UNDYING,
                    effect.getDuration(),
                    effect.getAmplifier() - 1,
                    effect.isAmbient(),
                    effect.shouldShowParticles(),
                    effect.shouldShowIcon()
            ));
        }

        return false;
    }
}

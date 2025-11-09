package net.edu.resprouted.event;


import net.edu.resprouted.Resprouted;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.util.TextUtils;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.potion.Potions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;


import java.util.List;

public class ModEvents {

    public static void registerModEvents() {
        Resprouted.LOGGER.info("Registering Events for " + Resprouted.MOD_ID);

        ServerLivingEntityEvents.ALLOW_DEATH.register(ModEvents::onAllowDeath);
        ItemTooltipCallback.EVENT.register(ModEvents::onItemTooltip);
        ItemTooltipCallback.EVENT.register(ModEvents::onWeaponTooltip);
        ItemTooltipCallback.EVENT.register(ModEvents::onHoneyBottleTooltip);
        UseItemCallback.EVENT.register(ModEvents::onWaterBottleUse);

    }

    // ========== TOOLTIP EVENTS ==========

    private static void onItemTooltip(ItemStack stack, Item.TooltipContext context, TooltipType type, List<Text> tooltip) {
        if (stack.get(ModDataComponentTypes.OLIVE_OILED) != null && Boolean.TRUE.equals(stack.get(ModDataComponentTypes.OLIVE_OILED))) {
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

    private static void onHoneyBottleTooltip(ItemStack stack, Item.TooltipContext context, TooltipType type, List<Text> tooltip) {
        if (Resprouted.CONFIG.isBottleEffectTooltipsEnabled() && Resprouted.CONFIG.isHoneyBottleEffectEnabled()){
            TextUtils.addHoneyEffectTooltip(stack, context, tooltip);
        }
    }

    // ========== ITEM USE EVENTS ==========

    private static TypedActionResult<ItemStack> onWaterBottleUse(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.getItem() == Items.POTION) {
            PotionContentsComponent potionContents = stack.get(DataComponentTypes.POTION_CONTENTS);
            if (potionContents != null && potionContents.matches(Potions.WATER)) {

                StatusEffectInstance effect = player.getStatusEffect(ModEffects.TIPSY);
                if (effect != null && !world.isClient()) {
                    Random random = player.getRandom();
                    int duration = effect.getDuration();
                    int amplifier = effect.getAmplifier();

                    if (random.nextFloat() < 0.1F) {
                        amplifier--;
                    } else {
                        duration -= (random.nextInt(800) + 200);
                    }

                    player.removeStatusEffect(ModEffects.TIPSY);

                    if (amplifier >= 0 && duration > 0) {
                        player.addStatusEffect(new StatusEffectInstance(
                                ModEffects.TIPSY,
                                duration,
                                amplifier,
                                false,
                                false
                        ));
                    }
                }
            }
        }

        return TypedActionResult.pass(stack);
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

package net.edu.resprouted.util;

import net.edu.resprouted.component.ModDataComponentTypes;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class TextUtils {
    private static final StatusEffectInstance HONEY_BOTTLE_EFFECT = new StatusEffectInstance(
            StatusEffects.REGENERATION, 80, 0, false, false, true
    );

    // Custom Food Tooltip
    public static void addFoodEffectTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip) {
        FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);
        if (foodComponent == null) {
            return;
        }

        if (foodComponent.effects().isEmpty()) {
            return;
        }

        for (FoodComponent.StatusEffectEntry entry : foodComponent.effects()) {
            StatusEffectInstance effect = entry.effect();

            MutableText text = Text.translatable(effect.getTranslationKey());

            if (effect.getAmplifier() > 0) {
                text = Text.translatable("potion.withAmplifier", text, Text.translatable("potion.potency." + effect.getAmplifier()));
            }

            if (!effect.isDurationBelow(20)) {
                text = Text.translatable("potion.withDuration", text, StatusEffectUtil.getDurationText(effect, 1.0F, context.getUpdateTickRate()));
            }
            text.formatted(effect.getEffectType().value().getCategory().getFormatting());
            tooltip.add(text);
        }
    }

    // Vanta Oiled Weapon Tooltip
    public static void addVantaOilEffectTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip) {
        if (!stack.contains(ModDataComponentTypes.VANTA_OILED)) {
            return;
        }

        StatusEffectInstance vantaEffect = stack.get(ModDataComponentTypes.VANTA_OILED);
        if (vantaEffect == null) {
            return;
        }
        Text vantaText = Text.translatable("tooltip.resprouted.vanta_oiled").formatted(Formatting.DARK_PURPLE);
        tooltip.add(vantaText);

        MutableText effectName = Text.translatable(vantaEffect.getTranslationKey());

        if (vantaEffect.getAmplifier() > 0) {
            effectName = Text.translatable("potion.withAmplifier", effectName, Text.translatable("potion.potency." + vantaEffect.getAmplifier()));
        }

        if (!vantaEffect.getEffectType().value().isInstant()) {
            int duration = RecipeUtils.getNextVantaHitDuration(vantaEffect.getDuration());
            effectName = Text.translatable("potion.withDuration", effectName,
                    StatusEffectUtil.getDurationText(new StatusEffectInstance
                            (vantaEffect.getEffectType(), duration, vantaEffect.getAmplifier()), 1.0F, context.getUpdateTickRate()));
        }
        effectName.formatted(vantaEffect.getEffectType().value().getCategory().getFormatting());
        tooltip.add(effectName);

        int uses = RecipeUtils.getRemainingVantaUses(vantaEffect);
        Text usesText = Text.translatable((uses == 1) ? "tooltip.resprouted.vanta_oil_use" : "tooltip.resprouted.vanta_oil_uses", uses).formatted(Formatting.GRAY);
        tooltip.add(usesText);
    }

    // Honey Bottle Tooltip
    public static void addHoneyEffectTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip) {
        if (stack.getItem() != Items.HONEY_BOTTLE) {
            return;
        }

        MutableText text = Text.translatable(HONEY_BOTTLE_EFFECT.getTranslationKey());

        if (!HONEY_BOTTLE_EFFECT.isDurationBelow(20)) {
            text = Text.translatable("potion.withDuration", text,
                    StatusEffectUtil.getDurationText(HONEY_BOTTLE_EFFECT, 1.0F, context.getUpdateTickRate()));
        }

        text.formatted(HONEY_BOTTLE_EFFECT.getEffectType().value().getCategory().getFormatting());
        tooltip.add(1, text);
    }

    public static StatusEffectInstance getHoneyBottleEffect() {
        return HONEY_BOTTLE_EFFECT;
    }

    // Booze Quality Text
    public static Text addQualityText(float quality) {
        int qualityPercent = (int) (quality * 100);

        String qualityName;
        Formatting nameColor;

        if (quality >= 0.9f) {
            qualityName = "divine";
            nameColor = Formatting.GOLD;
        } else if (quality >= 0.8f) {
            qualityName = "exquisite";
            nameColor = Formatting.YELLOW;
        } else if (quality >= 0.7f) {
            qualityName = "flavorful";
            nameColor = Formatting.GREEN;
        } else if (quality >= 0.6f) {
            qualityName = "fine";
            nameColor = Formatting.DARK_PURPLE;
        } else if (quality >= 0.5f) {
            qualityName = "decent";
            nameColor = Formatting.AQUA;
        } else if (quality >= 0.4f) {
            qualityName = "average";
            nameColor = Formatting.BLUE;
        } else if (quality >= 0.3f) {
            qualityName = "mediocre";
            nameColor = Formatting.YELLOW;
        } else if (quality >= 0.2f) {
            qualityName = "poor";
            nameColor = Formatting.RED;
        } else if (quality >= 0.1f) {
            qualityName = "awful";
            nameColor = Formatting.DARK_RED;
        } else {
            qualityName = "sewage";
            nameColor = Formatting.DARK_GRAY;
        }

        Text qualityNameText = Text.translatable("tooltip.resprouted.quality." + qualityName).formatted(nameColor);
        return Text.translatable("tooltip.resprouted.quality_format", qualityNameText, qualityPercent).formatted(Formatting.GRAY);
    }
}



package net.edu.resprouted.util;

import net.edu.resprouted.component.ModDataComponentTypes;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class TextUtils {

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
                text = Text.translatable("potion.withAmplifier", text,
                        Text.translatable("potion.potency." + effect.getAmplifier()));
            }
            if (!effect.isDurationBelow(20)) {
                text = Text.translatable("potion.withDuration", text,
                        StatusEffectUtil.getDurationText(effect, 1.0F, context.getUpdateTickRate()));
            }
            text.formatted(effect.getEffectType().value().getCategory().getFormatting());
            tooltip.add(text);
        }
    }
    public static void addVantaOilEffectTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip) {
        if (!stack.contains(ModDataComponentTypes.VANTA_OIL_EFFECT)) {
            return;
        }
        StatusEffectInstance vantaEffect = stack.get(ModDataComponentTypes.VANTA_OIL_EFFECT);
        if (vantaEffect == null) {
            return;
        }
        Text vantaText = Text.translatable("tooltip.resprouted.vanta_oiled").formatted(Formatting.DARK_PURPLE);
        tooltip.add(vantaText);

        MutableText effectName = Text.translatable(vantaEffect.getTranslationKey());

        if (vantaEffect.getAmplifier() > 0) {
            effectName = Text.translatable("potion.withAmplifier", effectName,
                    Text.translatable("potion.potency." + vantaEffect.getAmplifier()));
        }
        if (!vantaEffect.getEffectType().value().isInstant()) {
            int duration = RecipeUtils.getNextVantaHitDuration(vantaEffect.getDuration());
            effectName = Text.translatable("potion.withDuration", effectName,
                    StatusEffectUtil.getDurationText(new StatusEffectInstance(vantaEffect.getEffectType(), duration, vantaEffect.getAmplifier()), 1.0F, context.getUpdateTickRate()));
        }
        effectName.formatted(vantaEffect.getEffectType().value().getCategory().getFormatting());
        tooltip.add(effectName);

        int uses = RecipeUtils.getRemainingVantaUses(vantaEffect);
        Text usesText = Text.translatable((uses == 1) ? "tooltip.resprouted.vanta_oil_use" : "tooltip.resprouted.vanta_oil_uses", uses)
                .formatted(Formatting.GRAY);
        tooltip.add(usesText);
    }
}



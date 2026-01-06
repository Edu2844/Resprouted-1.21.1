package net.edu.resprouted.event;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.ResproutedClient;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.util.TextUtils;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class ModClientEvents {
    public static void registerClientEvents() {
        Resprouted.LOGGER.info("Registering Events for " + Resprouted.MOD_ID);
        ItemTooltipCallback.EVENT.register(ModClientEvents::onOliveOilTooltip);
        ItemTooltipCallback.EVENT.register(ModClientEvents::onVantaOilTooltip);
        ItemTooltipCallback.EVENT.register(ModClientEvents::onHoneyBottleTooltip);
    }

    private static void onOliveOilTooltip(ItemStack stack, Item.TooltipContext context, TooltipType type, List<Text> tooltip) {
        if (stack.get(ModDataComponentTypes.OLIVE_OILED) != null && Boolean.TRUE.equals(stack.get(ModDataComponentTypes.OLIVE_OILED))) {
            Text oiledText = Text.translatable("tooltip.resprouted.olive_oil").formatted(Formatting.DARK_GREEN);

            if (!tooltip.isEmpty()) {
                tooltip.add(1, oiledText);
            } else {
                tooltip.add(oiledText);
            }
        }
    }

    private static void onVantaOilTooltip(ItemStack stack, Item.TooltipContext context, TooltipType type, List<Text> tooltip){
        TextUtils.addVantaOilEffectTooltip(stack, context, tooltip);
    }

    private static void onHoneyBottleTooltip(ItemStack stack, Item.TooltipContext context, TooltipType type, List<Text> tooltip) {
        if (ResproutedClient.CLIENT_CONFIG.isBottleEffectTooltipsEnabled() && Resprouted.COMMON_CONFIG.general.isEnableHoneyBottleRegenerationEffect()){
            TextUtils.addHoneyEffectTooltip(stack, context, tooltip);
        }
    }
}

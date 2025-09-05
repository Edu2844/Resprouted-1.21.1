package net.edu.resprouted.event;


import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.util.RopeDispenser;
import net.edu.resprouted.util.TextUtils;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;


import java.util.List;

public class ModEvents {

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

    public static void registerModEvents() {
        Resprouted.LOGGER.info("Registering Events for " + Resprouted.MOD_ID);
        ItemTooltipCallback.EVENT.register(ModEvents::onItemTooltip);
        ItemTooltipCallback.EVENT.register(ModEvents::onWeaponTooltip);
        ItemTooltipCallback.EVENT.register(ModEvents::onHoneyTooltip);
        DispenserBlock.registerBehavior((ModBlocks.ROPE.asItem()), new RopeDispenser());

    }
}

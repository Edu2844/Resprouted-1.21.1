package net.edu.resprouted.item.custom;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.util.TextUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class FoodItem extends Item {
    public FoodItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        if (Resprouted.CONFIG.isFoodEffectTooltipsEnabled()) {
            TextUtils.addFoodEffectTooltip(stack, context, tooltip);
        }
    }
}

package net.edu.resprouted.item.custom;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.util.TextUtils;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.List;

public class DrinkableBottleItem extends Item {

    public DrinkableBottleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);

        if (foodComponent != null) {
            ItemStack result = user.eatFood(world, stack, foodComponent);

            if (user instanceof PlayerEntity player && !player.getAbilities().creativeMode) {
                ItemStack emptyBottle = new ItemStack(Items.GLASS_BOTTLE);
                if (!player.getInventory().insertStack(emptyBottle)) {
                    player.dropItem(emptyBottle, false);
                }
            }
            return result;
        }
        return stack;
    }
    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 32;
    }
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        if (Resprouted.CONFIG.isBottleEffectTooltipsEnabled()) {
            TextUtils.addFoodEffectTooltip(stack, context, tooltip);
        }
    }
}

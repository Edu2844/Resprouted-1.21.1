package net.edu.resprouted.item.custom;

import net.edu.resprouted.ResproutedClient;
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
import net.minecraft.world.event.GameEvent;

import java.util.List;

public class DrinkableBottleItem extends Item {
    public DrinkableBottleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        super.finishUsing(stack, world, user);
        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;
        FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);

        if (foodComponent != null) {
            ItemStack result = user.eatFood(world, stack, foodComponent);

            if (playerEntity != null && !playerEntity.isInCreativeMode()) {
                ItemStack glassBottle = new ItemStack(Items.GLASS_BOTTLE);

                if (stack.isEmpty()) {
                    return glassBottle;
                }

                if (!playerEntity.getInventory().insertStack(glassBottle)) {
                    playerEntity.dropItem(glassBottle, false);
                }
            }
            return result;
        }

        user.emitGameEvent(GameEvent.DRINK);
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
    public SoundEvent getDrinkSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        if (ResproutedClient.CLIENT_CONFIG.isBottleEffectTooltipsEnabled()) {
            TextUtils.addFoodEffectTooltip(stack, context, tooltip);
        }
    }
}

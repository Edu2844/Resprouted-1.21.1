package net.edu.resprouted.item.custom;

import dev.architectury.fluid.FluidStack;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.util.FluidUtils;
import net.edu.resprouted.util.TextUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BoozeBottleItem extends Item {
    private final Fluid fluidType;
    private static final Map<Fluid, BoozeBottleItem> FLUID_TO_BOTTLE = new HashMap<>();
    private final Consumer<BoozeConsumptionContext> effectConsumer;
    protected float inebriationChance = 0.5f;

    public BoozeBottleItem(Fluid fluidType, Settings settings, java.util.function.Consumer<BoozeConsumptionContext> effectConsumer) {
        super(settings);
        this.fluidType = fluidType;
        this.effectConsumer = effectConsumer;
        FLUID_TO_BOTTLE.put(fluidType, this);
    }

    public BoozeBottleItem(Fluid fluidType, Settings settings) {
        this(fluidType, settings, context -> {});
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        super.finishUsing(stack, world, user);
        if (!world.isClient() && user instanceof PlayerEntity player) {
            float quality = getQuality(stack);

            effectConsumer.accept(new BoozeConsumptionContext(world, player, quality));

            inebriate(world, player, quality);


            stack.decrement(1);
            if (stack.isEmpty()) return new ItemStack(Items.GLASS_BOTTLE);
            player.getInventory().insertStack(new ItemStack(Items.GLASS_BOTTLE));
        }
        return stack;
    }


    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }
    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity entity) {
        return 32;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        float quality = getQuality(stack);
        tooltip.add(TextUtils.addQualityText(quality));
    }

    public static float getQuality(ItemStack stack) {
        Float quality = stack.get(ModDataComponentTypes.FLUID_QUALITY);
        return quality != null ? quality : 0f;
    }

    public static void setQuality(ItemStack stack, float quality) {
        stack.set(ModDataComponentTypes.FLUID_QUALITY, Math.max(0f, Math.min(1f, quality)));
    }

    public static ItemStack fromFluidStack(FluidStack fluidStack) {
        Fluid fluid = fluidStack.getFluid();
        BoozeBottleItem bottleItem = FLUID_TO_BOTTLE.get(fluid);

        if (bottleItem != null) {
            float quality = FluidUtils.getQuality(fluidStack);
            ItemStack bottle = new ItemStack(bottleItem);
            setQuality(bottle, quality);
            return bottle;
        }

        return ItemStack.EMPTY;
    }

    public FluidStack toFluidStack(ItemStack bottle) {
        float quality = getQuality(bottle);
        return FluidUtils.withQuality(fluidType, 250, quality);
    }

    public static BoozeBottleItem getBottleForFluid(Fluid fluid) {
        return FLUID_TO_BOTTLE.get(fluid);
    }

    public static Fluid getFluidFromBottle(ItemStack bottleStack) {
        Item item = bottleStack.getItem();

        for (Fluid fluid : Registries.FLUID) {
            BoozeBottleItem bottleItem = BoozeBottleItem.getBottleForFluid(fluid);
            if (bottleItem == item) {
                return fluid;
            }
        }

        return Fluids.EMPTY;
    }

    public BoozeBottleItem setInebriationChance(float chance) {
        this.inebriationChance = chance;
        return this;
    }

    protected void inebriate(World world, PlayerEntity player, float quality) {
        int duration = (quality >= 0.5f)
                ? ((int) (12000 * (Math.max(1 - Math.abs(quality - 0.75F), 0.5F))))
                : ((int) (12000 * (Math.max(1 - (quality * 0.5F), 0.5F))));

        float inebriationChanceMod = Math.max(Math.min(1 - Math.abs(0.67F * (quality - 0.75F)), 1), 0);

        StatusEffectInstance tipsyEffect = player.getStatusEffect(ModEffects.TIPSY);

        if (world.random.nextFloat() < this.inebriationChance * inebriationChanceMod) {
            if (tipsyEffect == null) {
                player.addStatusEffect(new StatusEffectInstance(
                        ModEffects.TIPSY, duration, 0, false, false
                ));
            } else if (tipsyEffect.getAmplifier() < 3) {
                player.addStatusEffect(new StatusEffectInstance(
                        ModEffects.TIPSY,
                        Math.max(duration, tipsyEffect.getDuration()),
                        tipsyEffect.getAmplifier() + 1,
                        false,
                        false
                ));
            }
        }
    }

    public record BoozeConsumptionContext(World world, PlayerEntity player, float quality) {}
}
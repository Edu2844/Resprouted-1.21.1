package net.edu.resprouted.item;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.effect.BoozeEffects;
import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.entity.custom.ThrownTomatoEntity;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.custom.*;
import net.edu.resprouted.registry.ResproutedBoatTypes;
import net.edu.resprouted.util.TextUtils;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
public class ModItems {

    // =================================================
    // ||                   OLIVE                     ||
    // =================================================
    public static final Item OLIVE_SIGN = registerItem("olive_sign", new SignItem(new Item.Settings().maxCount(16), ModBlocks.OLIVE_SIGN, ModBlocks.OLIVE_WALL_SIGN));
    public static final Item OLIVE_HANGING_SIGN = registerItem("olive_hanging_sign", new HangingSignItem(ModBlocks.OLIVE_HANGING_SIGN , ModBlocks.OLIVE_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));

    public static final Item OLIVE_BOAT = registerItem("olive_boat",
            new BoatItem(false, ResproutedBoatTypes.OLIVE, new Item.Settings().maxCount(1)));

    public static final Item OLIVE_CHEST_BOAT = registerItem("olive_chest_boat",
            new BoatItem(true, ResproutedBoatTypes.OLIVE, new Item.Settings().maxCount(1)));

    // =================================================
    // ||                  IRONWOOD                   ||
    // =================================================
    public static final Item IRONWOOD_SIGN = registerItem("ironwood_sign", new SignItem(new Item.Settings().maxCount(16), ModBlocks.IRONWOOD_SIGN, ModBlocks.IRONWOOD_WALL_SIGN));
    public static final Item IRONWOOD_HANGING_SIGN = registerItem("ironwood_hanging_sign", new HangingSignItem(ModBlocks.IRONWOOD_HANGING_SIGN , ModBlocks.IRONWOOD_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));

    public static final Item IRONWOOD_BOAT = registerItem("ironwood_boat",
            new BoatItem(false, ResproutedBoatTypes.IRONWOOD, new Item.Settings().maxCount(1)));

    public static final Item IRONWOOD_CHEST_BOAT = registerItem("ironwood_chest_boat",
            new BoatItem(true, ResproutedBoatTypes.IRONWOOD, new Item.Settings().maxCount(1)));

    // =================================================
    // ||                   HERBS                     ||
    // =================================================
    public static final Item ALOE_VERA = registerItem("aloe_vera", new AliasedBlockItem(ModBlocks.ALOE_VERA_BLOCK, new Item.Settings()));
    public static final Item BLOOD_ORCHID = registerItem("blood_orchid", new AliasedBlockItem(ModBlocks.BLOOD_ORCHID_BLOCK ,new Item.Settings()));
    public static final Item CHAMOMILE = registerItem("chamomile", new AliasedBlockItem(ModBlocks.CHAMOMILE_BLOCK ,new Item.Settings()));
    public static final Item CLOUDSBLUFF = registerItem("cloudsbluff", new AliasedBlockItem(ModBlocks.CLOUDSBLUFF_BLOCK, new Item.Settings().food(ModFoodComponents.CLOUDSBLUFF)));
    public static final Item COHOSH = registerItem("cohosh", new AliasedBlockItem(ModBlocks.COHOSH_BLOCK ,new Item.Settings()));
    public static final Item CORE_ROOT = registerItem("core_root", new AliasedBlockItem(ModBlocks.CORE_ROOT ,new Item.Settings().food(ModFoodComponents.ROOT)));
    public static final Item DEATHSTALK_MUSHROOM = registerItem("deathstalk_mushroom", new AliasedBlockItem(ModBlocks.DEATHSTALK_MUSHROOM, new Item.Settings()));
    public static final Item GINSENG = registerItem("ginseng", new AliasedBlockItem(ModBlocks.GINSENG_BLOCK ,new Item.Settings().food(ModFoodComponents.ROOT)));
    public static final Item HORSETAIL = registerItem("horsetail", new AliasedBlockItem(ModBlocks.HORSETAIL_BLOCK, new  Item.Settings()));
    public static final Item MARSH_MALLOW = registerItem("marsh_mallow", new AliasedBlockItem(ModBlocks.MARSHMALLOW_BLOCK ,new Item.Settings().food(ModFoodComponents.ROOT)));
    public static final Item MOONCAP_MUSHROOM = registerItem("mooncap_mushroom", new AliasedBlockItem(ModBlocks.MOONCAP_MUSHROOM, new Item.Settings()));
    public static final Item VANTA_LILY = registerItem("vanta_lily", new AliasedBlockItem(ModBlocks.VANTA_LILY_BLOCK ,new Item.Settings()));
    public static final Item WIND_THISTLE = registerItem("wind_thistle", new AliasedBlockItem(ModBlocks.WIND_THISTLE_BLOCK ,new Item.Settings()));

    // =================================================
    // ||                AGRICULTURE                  ||
    // =================================================
    public static final Item APPLE_SEEDS = registerItem("apple_seeds", new AliasedBlockItem(ModBlocks.APPLE_TREE,new Item.Settings()));
    public static final Item BLUE_BERRIES = registerItem("blue_berries", new AliasedBlockItem(ModBlocks.BLUE_BERRY_BUSH, new Item.Settings().food(ModFoodComponents.BLUE_BERRIES)));
    public static final Item CATALOG = registerItem("catalog", new CatalogItem(new Item.Settings()));
    public static final Item COPPER_NUGGET = registerItem("copper_nugget", new Item(new Item.Settings()));
    public static final Item CHILI_PEPPER_SEEDS = registerItem("chili_pepper_seeds", new Item(new Item.Settings()));
    public static final Item GOLDEN_DUST = registerItem("golden_dust", new Item(new Item.Settings()));
    public static final Item IRON_DUST = registerItem("iron_dust", new Item(new Item.Settings()));
    public static final Item GRAPE_SEEDS = registerItem("grape_seeds", new AliasedBlockItem(ModBlocks.GRAPE_STEM,new Item.Settings()));
    public static final Item GRAPES = registerItem("grapes", new Item(new Item.Settings().food(ModFoodComponents.GRAPES)));
    public static final Item TINY_IRON_DUST = registerItem("tiny_iron_dust", new Item(new Item.Settings()));
    public static final Item TINY_GOLDEN_DUST = registerItem("tiny_golden_dust", new Item(new Item.Settings()));
    public static final Item TINY_GLOWSTONE_DUST = registerItem("tiny_glowstone_dust", new Item(new Item.Settings()));
    public static final Item TOMATO_SEEDS = registerItem("tomato_seeds", new Item(new Item.Settings()));

    public static final Item TOMATO = registerItem("tomato", new Item(new Item.Settings().food(ModFoodComponents.TOMATO).maxCount(64)){
        @Override
        public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
            ItemStack stack = user.getStackInHand(hand);
            if (user.isSneaking()) {
                user.playSound(SoundEvents.ENTITY_SNOWBALL_THROW, 0.5f, 0.5f);

                if (!world.isClient()) {
                    ThrownTomatoEntity tomato = new ThrownTomatoEntity(world, user);
                    Vec3d pos = user.getEyePos().add(user.getRotationVec(1.0F).multiply(1.5));
                    tomato.setPosition(pos);
                    tomato.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 1.0f, 1.0f);
                    tomato.setItem(stack.copyWithCount(1));
                    world.spawnEntity(tomato);
                }

                if (!user.getAbilities().creativeMode) {
                    stack.decrement(1);
                }

                return TypedActionResult.success(stack, world.isClient());
            }

            return super.use(world, user, hand);
        }

    });

    public static final Item IRON_BERRIES = registerItem("iron_berries", new Item(new Item.Settings().food(ModFoodComponents.IRON_BERRIES)){
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            super.appendTooltip(stack, context, tooltip, type);
            addFoodEffectTooltip(stack, context, tooltip);
        }
    });

    public static final Item OLIVES = registerItem("olives", new Item(new Item.Settings().food(ModFoodComponents.OLIVES)){
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            super.appendTooltip(stack, context, tooltip, type);
            addFoodEffectTooltip(stack, context, tooltip);
        }
    });

    public static final Item CHILI_PEPPER = registerItem("chili_pepper", new Item(new Item.Settings().food(ModFoodComponents.CHILLI_PEPPER)){
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            super.appendTooltip(stack, context, tooltip, type);
            addFoodEffectTooltip(stack, context, tooltip);
        }
        @Override
        public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
            if (!world.isClient()) {
                Random random = world.getRandom();
                if (random.nextInt(24) == 0) {
                    user.damage(world.getDamageSources().onFire(), 1.0F);
                }
            }
            return super.finishUsing(stack, world, user);
        }
    });

    public static final Item GHOST_PEPPER = registerItem("ghost_pepper", new Item(new Item.Settings().food(ModFoodComponents.GHOST_PEPPER).rarity(Rarity.RARE)){
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.resprouted.ghost_pepper.tooltip"));
            super.appendTooltip(stack, context, tooltip, type);
            addFoodEffectTooltip(stack, context, tooltip);
        }
    });

    // =================================================
    // ||                   BUCKETS                   ||
    // =================================================
    public static final Item HONEY_BUCKET = registerItem("honey_bucket", new BucketItem(ModFluids.HONEY_STILL,new Item.Settings().maxCount(1)){
        @Override
        protected void playEmptyingSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos) {
            world.playSound(player, pos, SoundEvents.BLOCK_HONEY_BLOCK_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.emitGameEvent(player, GameEvent.FLUID_PLACE, pos);
        }
    });

    public static final Item APPLE_JUICE_BUCKET = registerItem("apple_juice_bucket", new BucketItem(ModFluids.APPLE_JUICE_STILL,new Item
            .Settings()
            .maxCount(1)));
    public static final Item GOLDEN_APPLE_JUICE_BUCKET = registerItem("golden_apple_juice_bucket", new BucketItem(ModFluids.GOLDEN_APPLE_JUICE_STILL,new Item
            .Settings()
            .maxCount(1)));
    public static final Item GRAPE_JUICE_BUCKET = registerItem("grape_juice_bucket", new BucketItem(ModFluids.GRAPE_JUICE_STILL,new Item
            .Settings()
            .maxCount(1)));
    public static final Item SWEET_BERRY_JUICE_BUCKET = registerItem("sweet_berry_juice_bucket", new BucketItem(ModFluids.SWEET_BERRY_JUICE_STILL,new Item
            .Settings()
            .maxCount(1)));
    public static final Item OLIVE_OIL_BUCKET = registerItem("olive_oil_bucket", new BucketItem(ModFluids.OLIVE_OIL_STILL,new Item
            .Settings()
            .maxCount(1)));
    public static final Item VANTA_OIL_BUCKET = registerItem("vanta_oil_bucket", new BucketItem(ModFluids.VANTA_OIL_STILL,new Item
            .Settings()
            .maxCount(1)));
    public static final Item GLOW_BERRY_JUICE_BUCKET = registerItem("glow_berry_juice_bucket", new BucketItem(ModFluids.GLOW_BERRY_JUICE_STILL,new Item
            .Settings()
            .maxCount(1)));
    public static final Item IRON_BERRY_JUICE_BUCKET = registerItem("iron_berry_juice_bucket", new BucketItem(ModFluids.IRON_BERRY_JUICE_STILL,new Item
            .Settings()
            .maxCount(1)));

    public static final Item SUGAR_CANE_JUICE_BUCKET = registerItem("sugar_cane_juice_bucket", new BucketItem(ModFluids.SUGAR_CANE_JUICE_STILL,new Item
            .Settings()
            .maxCount(1)));

    public static final Item ALE_WORT_BUCKET = registerItem("ale_wort_bucket", new BucketItem(ModFluids.ALE_WORT_STILL,new Item
            .Settings()
            .maxCount(1)));

    // =================================================
    // ||                   BOTTLES                   ||
    // =================================================
    public static final Item APPLE_JUICE_BOTTLE = registerItem("apple_juice_bottle",
            new DrinkableBottleItem(new Item.Settings().food(ModFoodComponents.APPLE_JUICE).maxCount(16)));

    public static final Item GOLDEN_APPLE_JUICE_BOTTLE = registerItem("golden_apple_juice_bottle",
            new DrinkableBottleItem(new Item.Settings().food(FoodComponents.GOLDEN_APPLE).maxCount(16).rarity(Rarity.EPIC)));

    public static final Item GRAPE_JUICE_BOTTLE = registerItem("grape_juice_bottle",
            new DrinkableBottleItem(new Item.Settings().food(ModFoodComponents.GRAPE_JUICE).maxCount(16)));

    public static final Item SWEET_BERRY_JUICE_BOTTLE = registerItem("sweet_berry_juice_bottle",
            new DrinkableBottleItem(new Item.Settings().food(ModFoodComponents.SWEET_BERRY_JUICE).maxCount(16)));

    public static final Item OLIVE_OIL_BOTTLE = registerItem("olive_oil_bottle",
            new DrinkableBottleItem(new Item.Settings().food(ModFoodComponents.OLIVE_OIL).maxCount(16)));

    public static final Item VANTA_OIL_BOTTLE = registerItem("vanta_oil_bottle",
            new DrinkableBottleItem(new Item.Settings().food(ModFoodComponents.VANTA_OIL).maxCount(16)));

    public static final Item GLOW_BERRY_JUICE_BOTTLE = registerItem("glow_berry_juice_bottle",
            new DrinkableBottleItem(new Item.Settings().food(ModFoodComponents.GLOW_BERRY_JUICE).maxCount(16)));

    public static final Item IRON_BERRY_JUICE_BOTTLE = registerItem("iron_berry_juice_bottle",
            new DrinkableBottleItem(new Item.Settings().food(ModFoodComponents.IRON_BERRY_JUICE).maxCount(16)));

    public static final Item SUGAR_CANE_JUICE_BOTTLE = registerItem("sugar_cane_juice_bottle",
            new DrinkableBottleItem(new Item.Settings().food(ModFoodComponents.REGULAR_JUICE).maxCount(16)));

    public static final Item ALE_WORT_BOTTLE = registerItem("ale_wort_bottle",
            new DrinkableBottleItem(new Item.Settings().food(ModFoodComponents.ALE_WORT).maxCount(16)));

    //Booze bottles
    public static final Item ALE_BOTTLE = registerItem("ale_bottle",
            new BoozeBottleItem(ModFluids.ALE_STILL, new Item.Settings().maxCount(64), BoozeEffects::applyAleEffects)
                    .setInebriationChance(0.5f));

    public static final Item IRON_WINE_BOTTLE = registerItem("iron_wine_bottle",
            new BoozeBottleItem(ModFluids.IRON_WINE_STILL, new Item.Settings().maxCount(64), BoozeEffects::applyIronWineEffects)
                    .setInebriationChance(0.5f));

    public static final Item CIDER_BOTTLE = registerItem("cider_bottle",
            new BoozeBottleItem(ModFluids.CIDER_STILL, new Item.Settings().maxCount(64), BoozeEffects::applyCiderEffects)
                    .setInebriationChance(0.5f));

    public static final Item MEAD_BOTTLE = registerItem("mead_bottle",
            new BoozeBottleItem(ModFluids.MEAD_STILL, new Item.Settings().maxCount(64), BoozeEffects::applyMeadEffects)
                    .setInebriationChance(0.5f));

    public static final Item SWEET_BERRY_WINE_BOTTLE = registerItem("sweet_berry_wine_bottle",
            new BoozeBottleItem(ModFluids.SWEET_BERRY_WINE_STILL, new Item.Settings().maxCount(64), BoozeEffects::applySweetBerryWineEffects)
                    .setInebriationChance(0.85f));

    public static final Item GLOW_BERRY_WINE_BOTTLE = registerItem("glow_berry_wine_bottle",
            new BoozeBottleItem(ModFluids.GLOW_BERRY_WINE_STILL, new Item.Settings().maxCount(64), BoozeEffects::applyGlowBerryWineEffects)
                    .setInebriationChance(0.5f));

    public static final Item WINE_BOTTLE = registerItem("wine_bottle",
            new BoozeBottleItem(ModFluids.WINE_STILL, new Item.Settings().maxCount(64), BoozeEffects::applyWineEffects)
                    .setInebriationChance(0.5f));

    public static final Item RUM_BOTTLE = registerItem("rum_bottle",
            new BoozeBottleItem(ModFluids.RUM_STILL, new Item.Settings().maxCount(64), BoozeEffects::applyRumEffects)
                    .setInebriationChance(0.6f));

    public static final Item AMBROSIA_BOTTLE = registerItem("ambrosia_bottle",
            new BoozeBottleItem(ModFluids.AMBROSIA_STILL, new Item.Settings().maxCount(64).rarity(Rarity.EPIC), BoozeEffects::applyAmbrosiaEffects) {
                @Override
                protected void inebriate(World world, PlayerEntity player, float quality) {
                    int duration = (quality >= 0.5f)
                            ? ((int) (12000 * (Math.max(1 - Math.abs(quality - 0.75F), 0.5F))))
                            : ((int) (12000 * (Math.max(1 - (quality * 0.5F), 0.5F))));

                    StatusEffectInstance tipsyEffect = player.getStatusEffect(ModEffects.TIPSY);

                    if (world.random.nextFloat() < this.inebriationChance) {
                        if (tipsyEffect == null) {
                            int amp = (quality > 0.99F) ? 1 : 0;
                            player.addStatusEffect(new StatusEffectInstance(
                                    ModEffects.TIPSY, duration, amp, false, false
                            ));
                        } else if (tipsyEffect.getAmplifier() < 3) {
                            int newAmp = Math.min(tipsyEffect.getAmplifier() + ((quality >0.99F) ? 2 :1), 3);
                            player.addStatusEffect(new StatusEffectInstance(
                                    ModEffects.TIPSY,
                                    Math.max(duration, tipsyEffect.getDuration()),
                                    newAmp,
                                    false,
                                    false
                            ));
                        }
                    }
                }
            }.setInebriationChance(1.0f));


    // =================================================
    // ||                    FOOD                     ||
    // =================================================
    public static final Item LAMB_STEW = registerItem("lamb_stew", new Item(new Item.Settings().food(FoodComponents.RABBIT_STEW)));
    public static final Item HONEY_GLAZED_CARROTS = registerItem("honey_glazed_carrots", new Item(new Item.Settings().food(FoodComponents.RABBIT_STEW)));

    public static Item IRON_BERRY_CAKE_SLICE;

    // =================================================
    // ||                   ALCHEMY                   ||
    // =================================================
    public static final Item ELIXIR_BOTTLE = registerItem("elixir_bottle",
            new ElixirBottle(new Item.Settings()
                    .maxCount(16)
                    .component(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(Potions.WATER))
            ));
    public static final Item ELIXIR_ICON = registerItem("elixir_icon", new Item(new Item.Settings()));



    static {
        ResproutedBoatTypes.addBoatTypeItems(ResproutedBoatTypes.IRONWOOD, IRONWOOD_BOAT, IRONWOOD_CHEST_BOAT);
        ResproutedBoatTypes.addBoatTypeItems(ResproutedBoatTypes.OLIVE, OLIVE_BOAT, OLIVE_CHEST_BOAT);
    }


    private static void addFoodEffectTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip) {
        if (Resprouted.CONFIG.isFoodEffectTooltipsEnabled()) {
            TextUtils.addFoodEffectTooltip(stack, context, tooltip);
        }
    }

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Resprouted.MOD_ID, name), item);
    }

    public static void registerCompatItem() {
        if (Resprouted.isModLoaded(Resprouted.FARMERS_DELIGHT_MOD_ID)) {
            IRON_BERRY_CAKE_SLICE = registerItem("iron_berry_cake_slice", new Item(new Item.Settings().food(ModFoodComponents.IRON_BERRIES)));

        }
    }

    public static void registerModItems() {
        registerCompatItem();
        Resprouted.LOGGER.info("Registering Items for " +  Resprouted.MOD_ID);
    }
}

package net.edu.resprouted.item;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.entity.ModEntities;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.custom.*;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

import static net.edu.resprouted.block.ModBlocks.LIQUID_BARREL;

public class ModItems {

    // =================================================
    // ||                   OLIVE                     ||
    // =================================================
    public static final Item OLIVE_SIGN = registerItem("olive_sign",
            new SignItem(new Item.Settings().maxCount(16), ModBlocks.OLIVE_SIGN, ModBlocks.OLIVE_WALL_SIGN));
    public static final Item OLIVE_HANGING_SIGN = registerItem("olive_hanging_sign",
            new HangingSignItem(ModBlocks.OLIVE_HANGING_SIGN , ModBlocks.OLIVE_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));

    public static final Item OLIVE_BOAT = TerraformBoatItemHelper.registerBoatItem(ModEntities.OLIVE_BOAT, ModEntities.OLIVE_BOAT_KEY, false);
    public static final Item OLIVE_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModEntities.OLIVE_CHEST_BOAT, ModEntities.OLIVE_BOAT_KEY, true);
    // =================================================
    // ||                  IRONWOOD                   ||
    // =================================================
    public static final Item IRONWOOD_SIGN = registerItem("ironwood_sign",
            new SignItem(new Item.Settings().maxCount(16), ModBlocks.IRONWOOD_SIGN, ModBlocks.IRONWOOD_WALL_SIGN));
    public static final Item IRONWOOD_HANGING_SIGN = registerItem("ironwood_hanging_sign",
            new HangingSignItem(ModBlocks.IRONWOOD_HANGING_SIGN , ModBlocks.IRONWOOD_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));
    public static final Item IRONWOOD_BOAT = TerraformBoatItemHelper.registerBoatItem(ModEntities.IRONWOOD_BOAT, ModEntities.IRONWOOD_BOAT_KEY, false);
    public static final Item IRONWOOD_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModEntities.IRONWOOD_CHEST_BOAT, ModEntities.IRONWOOD_BOAT_KEY, true);

    // =================================================
    // ||                   HERBS                     ||
    // =================================================
    public static final Item ALOE_VERA = registerItem("aloe_vera", new AliasedBlockItem(ModBlocks.ALOE_VERA_BLOCK, new Item.Settings()));
    public static final Item CLOUDSBLUFF = registerItem("cloudsbluff", new AliasedBlockItem(ModBlocks.CLOUDSBLUFF_BLOCK, new Item.Settings().food(ModFoodComponents.CLOUDSBLUFF)));
    public static final Item COHOSH = registerItem("cohosh", new AliasedBlockItem(ModBlocks.COHOSH_BLOCK ,new Item.Settings()));
    public static final Item BLOOD_ORCHID = registerItem("blood_orchid", new AliasedBlockItem(ModBlocks.BLOOD_ORCHID_BLOCK ,new Item.Settings()));
    public static final Item CHAMOMILE = registerItem("chamomile", new AliasedBlockItem(ModBlocks.CHAMOMILE_BLOCK ,new Item.Settings()));
    public static final Item CORE_ROOT = registerItem("core_root", new AliasedBlockItem(ModBlocks.CORE_ROOT ,new Item.Settings().food(ModFoodComponents.ROOT)));
    public static final Item GINSENG = registerItem("ginseng", new AliasedBlockItem(ModBlocks.GINSENG_BLOCK ,new Item.Settings().food(ModFoodComponents.ROOT)));
    public static final Item HORSETAIL = registerItem("horsetail", new AliasedBlockItem(ModBlocks.HORSETAIL_BLOCK, new  Item.Settings()));
    public static final Item MARSH_MALLOW = registerItem("marsh_mallow", new AliasedBlockItem(ModBlocks.MARSHMALLOW_BLOCK ,new Item.Settings().food(ModFoodComponents.ROOT)));
    public static final Item VANTA_LILY = registerItem("vanta_lily", new AliasedBlockItem(ModBlocks.VANTA_LILY_BLOCK ,new Item.Settings()));
    public static final Item WIND_THISTLE = registerItem("wind_thistle", new AliasedBlockItem(ModBlocks.WIND_THISTLE_BLOCK ,new Item.Settings()));
    public static final Item MOONCAP_MUSHROOM = registerItem("mooncap_mushroom", new AliasedBlockItem(ModBlocks.MOONCAP_MUSHROOM, new Item.Settings()));
    public static final Item DEATHSTALK_MUSHROOM = registerItem("deathstalk_mushroom", new AliasedBlockItem(ModBlocks.DEATHSTALK_MUSHROOM, new Item.Settings()));

    // =================================================
    // ||                AGRICULTURE                  ||
    // =================================================
    public static final Item APPLE_SEEDS = registerItem("apple_seeds", new AliasedBlockItem(ModBlocks.APPLE_TREE,new Item.Settings()));
    public static final Item BLUE_BERRIES = registerItem("blue_berries", new AliasedBlockItem(ModBlocks.BLUE_BERRY_BUSH, new Item.Settings().food(ModFoodComponents.BLUE_BERRIES)));
    public static final Item CATALOG = registerItem("catalog", new Item(new Item.Settings()));
    public static final Item COPPER_NUGGET = registerItem("copper_nugget", new Item(new Item.Settings()));
    public static final Item CHILI_PEPPER = registerItem("chili_pepper", new FoodItem(new Item.Settings().food(ModFoodComponents.CHILLI_PEPPER)));
    public static final Item CHILI_PEPPER_SEEDS = registerItem("chili_pepper_seeds", new Item(new Item.Settings()));
    public static final Item GOLDEN_DUST = registerItem("golden_dust", new Item(new Item.Settings()));
    public static final Item IRON_DUST = registerItem("iron_dust", new Item(new Item.Settings()));
    public static final Item GRAPE_SEEDS = registerItem("grape_seeds", new AliasedBlockItem(ModBlocks.GRAPE_STEM,new Item.Settings()));
    public static final Item GRAPES = registerItem("grapes", new Item(new Item.Settings().food(ModFoodComponents.GRAPES)));
    public static final Item IRON_BERRIES = registerItem("iron_berries", new FoodItem(new Item.Settings().food(ModFoodComponents.IRON_BERRIES)));
    public static final Item OLIVES = registerItem("olives", new FoodItem(new Item.Settings().food(ModFoodComponents.OLIVES)));
    public static final Item TINY_IRON_DUST = registerItem("tiny_iron_dust", new Item(new Item.Settings()));
    public static final Item TINY_GOLDEN_DUST = registerItem("tiny_golden_dust", new Item(new Item.Settings()));
    public static final Item TINY_GLOWSTONE_DUST = registerItem("tiny_glowstone_dust", new Item(new Item.Settings()));
    public static final Item TOMATO = registerItem("tomato", new TomatoItem(new Item.Settings().food(ModFoodComponents.TOMATO).maxCount(64)));
    public static final Item TOMATO_SEEDS = registerItem("tomato_seeds", new Item(new Item.Settings()));
    public static final Item GHOST_PEPPER = registerItem("ghost_pepper", new FoodItem(new Item.Settings().food(ModFoodComponents.GHOST_PEPPER)){
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.resprouted.ghost_pepper.tooltip"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });
    public static final Item LIQUID_BARREL_ITEM = registerItem("liquid_barrel", new LiquidBarrelItem(LIQUID_BARREL, new Item.Settings()));

    // =================================================
    // ||                 FLUID BUCKETS               ||
    // =================================================
    public static final Item HONEY_BUCKET = registerItem("honey_bucket", new CustomBucketItem(ModFluids.HONEY_STILL,new Item
            .Settings()
            .maxCount(1)));
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

    public static final Item ALE_WORT_BUCKET = registerItem("ale_wort_bucket", new BucketItem(ModFluids.ALE_WORT_STILL,new Item
            .Settings()
            .maxCount(1)));

    // =================================================
    // ||                   BOTTLES                   ||
    // =================================================
    public static final Item APPLE_JUICE_BOTTLE = registerItem("apple_juice_bottle",
            new DrinkableBottleItem(new Item.Settings().food(ModFoodComponents.REGULAR_JUICE).maxCount(16)));

    public static final Item GOLDEN_APPLE_JUICE_BOTTLE = registerItem("golden_apple_juice_bottle",
            new DrinkableBottleItem(new Item.Settings().food(ModFoodComponents.GOLDEN_APPLE_JUICE).maxCount(16)));

    public static final Item GRAPE_JUICE_BOTTLE = registerItem("grape_juice_bottle",
            new DrinkableBottleItem(new Item.Settings().food(ModFoodComponents.REGULAR_JUICE).maxCount(16)));

    public static final Item SWEET_BERRY_JUICE_BOTTLE = registerItem("sweet_berry_juice_bottle",
            new DrinkableBottleItem(new Item.Settings().food(ModFoodComponents.REGULAR_JUICE).maxCount(16)));

    public static final Item OLIVE_OIL_BOTTLE = registerItem("olive_oil_bottle",
            new DrinkableBottleItem(new Item.Settings().food(ModFoodComponents.OLIVE_OIL).maxCount(16)));

    public static final Item VANTA_OIL_BOTTLE = registerItem("vanta_oil_bottle",
            new DrinkableBottleItem(new Item.Settings().food(ModFoodComponents.VANTA_OIL).maxCount(16)));

    public static final Item GLOW_BERRY_JUICE_BOTTLE = registerItem("glow_berry_juice_bottle",
            new DrinkableBottleItem(new Item.Settings().food(ModFoodComponents.GLOW_BERRY_JUICE).maxCount(16)));

    public static final Item IRON_BERRY_JUICE_BOTTLE = registerItem("iron_berry_juice_bottle",
            new DrinkableBottleItem(new Item.Settings().food(ModFoodComponents.IRON_BERRIES).maxCount(16)));

    public static final Item ALE_WORT_BOTTLE = registerItem("ale_wort_bottle",
            new DrinkableBottleItem(new Item.Settings().food(ModFoodComponents.ALE_WORT).maxCount(16)));

    // =================================================
    // ||                    FOOD                     ||
    // =================================================
    public static final Item LAMB_STEW = registerItem("lamb_stew", new Item(new Item.Settings().food(FoodComponents.RABBIT_STEW)));
    public static final Item HONEY_GLAZED_CARROTS = registerItem("honey_glazed_carrots", new Item(new Item.Settings().food(FoodComponents.RABBIT_STEW)));

    // =================================================
    // ||                   ALCHEMY                   ||
    // =================================================
    public static final Item ELIXIR_BOTTLE = registerItem("elixir_bottle", new ElixirBottle(new Item.Settings().maxCount(16)));
    public static final Item ELIXIR_ICON = registerItem("elixir_icon", new Item(new Item.Settings()));


    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Resprouted.MOD_ID, name), item);
    }
    public static void registerModItems() {
        Resprouted.LOGGER.info("Registering Items for " +  Resprouted.MOD_ID);
    }
}

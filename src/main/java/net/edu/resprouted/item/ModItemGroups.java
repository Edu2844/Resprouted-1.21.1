package net.edu.resprouted.item;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.item.custom.BoozeBottleItem;
import net.edu.resprouted.util.ElixirUtils;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static  final ItemGroup AGRICULTURE_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Resprouted.MOD_ID,"agriculture_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.OLIVES))
                    .displayName(Text.translatable("itemgroup.resprouted.agriculture"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.CATALOG);
                        entries.add(ModBlocks.FERTILE_SOIL);
                        entries.add(ModBlocks.OLIVE_LOG);
                        entries.add(ModBlocks.OLIVE_WOOD);
                        entries.add(ModBlocks.IRONWOOD_LOG);
                        entries.add(ModBlocks.IRONWOOD_WOOD);
                        entries.add(ModBlocks.STRIPPED_OLIVE_LOG);
                        entries.add(ModBlocks.STRIPPED_OLIVE_WOOD);
                        entries.add(ModBlocks.STRIPPED_IRONWOOD_LOG);
                        entries.add(ModBlocks.STRIPPED_IRONWOOD_WOOD);
                        entries.add(ModBlocks.OLIVE_LEAVES);
                        entries.add(ModBlocks.IRONWOOD_LEAVES);
                        entries.add(ModBlocks.APPLE_LEAVES);
                        entries.add(ModBlocks.APPLE_SAPLING);
                        entries.add(ModBlocks.OLIVE_SAPLING);
                        entries.add(ModBlocks.IRONWOOD_SAPLING);
                        entries.add(ModBlocks.CRUSHING_TUB);
                        entries.add(ModBlocks.LIQUID_BARREL);
                        entries.add(ModBlocks.EVAPORATING_BASIN);
                        entries.add(ModBlocks.STAKE);
                        entries.add(ModBlocks.ROPE);


                        entries.add(ModItems.CHILI_PEPPER_SEEDS);
                        entries.add(ModItems.TOMATO_SEEDS);
                        entries.add(ModItems.GRAPE_SEEDS);
                        entries.add(ModItems.APPLE_SEEDS);
                        entries.add(ModItems.OLIVES);
                        entries.add(ModItems.IRON_BERRIES);
                        entries.add(ModItems.BLUE_BERRIES);
                        entries.add(ModItems.GRAPES);
                        entries.add(ModItems.TOMATO);
                        entries.add(ModItems.CHILI_PEPPER);
                        entries.add(ModItems.GHOST_PEPPER);
                        entries.add(ModItems.GOLDEN_DUST);
                        entries.add(ModItems.TINY_GOLDEN_DUST);
                        entries.add(ModItems.IRON_DUST);
                        entries.add(ModItems.TINY_IRON_DUST);
                        entries.add(ModItems.TINY_GLOWSTONE_DUST);
                        entries.add(ModItems.COPPER_NUGGET);

                        entries.add(ModItems.APPLE_JUICE_BOTTLE);
                        entries.add(ModItems.GOLDEN_APPLE_JUICE_BOTTLE);
                        entries.add(ModItems.GRAPE_JUICE_BOTTLE);
                        entries.add(ModItems.SWEET_BERRY_JUICE_BOTTLE);
                        entries.add(ModItems.OLIVE_OIL_BOTTLE);
                        entries.add(ModItems.VANTA_OIL_BOTTLE);
                        entries.add(ModItems.GLOW_BERRY_JUICE_BOTTLE);
                        entries.add(ModItems.IRON_BERRY_JUICE_BOTTLE);
                        entries.add(ModItems.SUGAR_CANE_JUICE_BOTTLE);
                        entries.add(ModItems.ALE_WORT_BOTTLE);

                        entries.add(ModItems.HONEY_BUCKET);
                        entries.add(ModItems.APPLE_JUICE_BUCKET);
                        entries.add(ModItems.GOLDEN_APPLE_JUICE_BUCKET);
                        entries.add(ModItems.GRAPE_JUICE_BUCKET);
                        entries.add(ModItems.SWEET_BERRY_JUICE_BUCKET);
                        entries.add(ModItems.OLIVE_OIL_BUCKET);
                        entries.add(ModItems.VANTA_OIL_BUCKET);
                        entries.add(ModItems.GLOW_BERRY_JUICE_BUCKET);
                        entries.add(ModItems.IRON_BERRY_JUICE_BUCKET);
                        entries.add(ModItems.SUGAR_CANE_JUICE_BUCKET);
                        entries.add(ModItems.ALE_WORT_BUCKET);

                        entries.add(ModItems.LAMB_STEW);
                        entries.add(ModItems.HONEY_GLAZED_CARROTS);
                        entries.add(ModBlocks.IRON_BERRY_CAKE);

                        entries.add(ModBlocks.BREWING_BARREL);

                        ItemStack IronWineBottleWithQuality = new ItemStack(ModItems.IRON_WINE_BOTTLE);
                        BoozeBottleItem.setQuality(IronWineBottleWithQuality, 1.0f);
                        entries.add(IronWineBottleWithQuality);

                        ItemStack aleBottleWithQuality = new ItemStack(ModItems.ALE_BOTTLE);
                        BoozeBottleItem.setQuality(aleBottleWithQuality, 0.75f);
                        entries.add(aleBottleWithQuality);

                        ItemStack CiderBottleWithQuality = new ItemStack(ModItems.CIDER_BOTTLE);
                        BoozeBottleItem.setQuality(CiderBottleWithQuality, 0.75f);
                        entries.add(CiderBottleWithQuality);

                        ItemStack MeadBottleWithQuality = new ItemStack(ModItems.MEAD_BOTTLE);
                        BoozeBottleItem.setQuality(MeadBottleWithQuality, 0.75f);
                        entries.add(MeadBottleWithQuality);

                        ItemStack AmbrosiaBottleWithQuality = new ItemStack(ModItems.AMBROSIA_BOTTLE);
                        BoozeBottleItem.setQuality(AmbrosiaBottleWithQuality, 0.75f);
                        entries.add(AmbrosiaBottleWithQuality);

                        ItemStack GlowBerryBottleWithQuality = new ItemStack(ModItems.GLOW_BERRY_WINE_BOTTLE);
                        BoozeBottleItem.setQuality(GlowBerryBottleWithQuality, 0.75f);
                        entries.add(GlowBerryBottleWithQuality);

                        ItemStack RumBottleWithQuality = new ItemStack(ModItems.RUM_BOTTLE);
                        BoozeBottleItem.setQuality(RumBottleWithQuality, 0.75f);
                        entries.add(RumBottleWithQuality);

                        ItemStack SweetBerryWineBottleWithQuality = new ItemStack(ModItems.SWEET_BERRY_WINE_BOTTLE);
                        BoozeBottleItem.setQuality(SweetBerryWineBottleWithQuality, 0.75f);
                        entries.add(SweetBerryWineBottleWithQuality);

                        ItemStack WineBottleWithQuality = new ItemStack(ModItems.WINE_BOTTLE);
                        BoozeBottleItem.setQuality(WineBottleWithQuality, 0.75f);
                        entries.add(WineBottleWithQuality);





                    }).build());
    public static  final ItemGroup DECORATION_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Resprouted.MOD_ID,"decoration_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.POLISHED_SLATE))
                    .displayName(Text.translatable("itemgroup.resprouted.decoration"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.SLATE);
                        entries.add(ModBlocks.COBBLED_SLATE);
                        entries.add(ModBlocks.COBBLED_SLATE_SLAB);
                        entries.add(ModBlocks.COBBLED_SLATE_STAIRS);
                        entries.add(ModBlocks.COBBLED_SLATE_WALL);
                        entries.add(ModBlocks.POLISHED_SLATE);
                        entries.add(ModBlocks.POLISHED_SLATE_SLAB);
                        entries.add(ModBlocks.POLISHED_SLATE_STAIRS);
                        entries.add(ModBlocks.POLISHED_SLATE_WALL);
                        entries.add(ModBlocks.CHISELED_SLATE);
                        entries.add(ModBlocks.SLATE_BRICKS);
                        entries.add(ModBlocks.SLATE_BRICK_SLAB);
                        entries.add(ModBlocks.SLATE_BRICK_STAIRS);
                        entries.add(ModBlocks.SLATE_BRICK_WALL);
                        entries.add(ModBlocks.SLATE_ROOFS);
                        entries.add(ModBlocks.SLATE_ROOF_SLAB);
                        entries.add(ModBlocks.SLATE_ROOF_STAIRS);
                        entries.add(ModBlocks.SLATE_PILLAR);

                        entries.add(ModBlocks.POLISHED_STONE);
                        entries.add(ModBlocks.POLISHED_STONE_SLAB);
                        entries.add(ModBlocks.POLISHED_STONE_STAIRS);
                        entries.add(ModBlocks.POLISHED_STONE_WALL);
                        entries.add(ModBlocks.STONE_PILLAR);

                        entries.add(ModBlocks.ANDESITE_BRICKS);
                        entries.add(ModBlocks.ANDESITE_BRICK_SLAB);
                        entries.add(ModBlocks.ANDESITE_BRICK_STAIRS);
                        entries.add(ModBlocks.ANDESITE_BRICK_WALL);
                        entries.add(ModBlocks.CRACKED_ANDESITE_BRICKS);
                        entries.add(ModBlocks.CHISELED_ANDESITE);
                        entries.add(ModBlocks.ANDESITE_PILLAR);

                        entries.add(ModBlocks.DIORITE_BRICKS);
                        entries.add(ModBlocks.DIORITE_BRICK_SLAB);
                        entries.add(ModBlocks.DIORITE_BRICK_STAIRS);
                        entries.add(ModBlocks.DIORITE_BRICK_WALL);
                        entries.add(ModBlocks.CRACKED_DIORITE_BRICKS);
                        entries.add(ModBlocks.CHISELED_DIORITE);
                        entries.add(ModBlocks.DIORITE_PILLAR);

                        entries.add(ModBlocks.GRANITE_BRICKS);
                        entries.add(ModBlocks.GRANITE_BRICK_SLAB);
                        entries.add(ModBlocks.GRANITE_BRICK_STAIRS);
                        entries.add(ModBlocks.GRANITE_BRICK_WALL);
                        entries.add(ModBlocks.CRACKED_GRANITE_BRICKS);
                        entries.add(ModBlocks.CHISELED_GRANITE);
                        entries.add(ModBlocks.GRANITE_PILLAR);

                        entries.add(ModBlocks.SANDSTONE_BRICKS);
                        entries.add(ModBlocks.SANDSTONE_BRICK_SLAB);
                        entries.add(ModBlocks.SANDSTONE_BRICK_STAIRS);
                        entries.add(ModBlocks.SANDSTONE_BRICK_WALL);
                        entries.add(ModBlocks.SANDSTONE_PILLAR);

                        entries.add(ModBlocks.RED_SANDSTONE_BRICKS);
                        entries.add(ModBlocks.RED_SANDSTONE_BRICK_SLAB);
                        entries.add(ModBlocks.RED_SANDSTONE_BRICK_STAIRS);
                        entries.add(ModBlocks.RED_SANDSTONE_BRICK_WALL);
                        entries.add(ModBlocks.RED_SANDSTONE_PILLAR);

                        entries.add(ModBlocks.WROUGHT_IRON_BLOCK);
                        entries.add(ModBlocks.CUT_WROUGHT_IRON_BLOCK);
                        entries.add(ModBlocks.CUT_WROUGHT_IRON_SLAB);
                        entries.add(ModBlocks.CUT_WROUGHT_IRON_STAIRS);
                        entries.add(ModBlocks.WROUGHT_IRON_BULB);
                        entries.add(ModBlocks.WROUGHT_IRON_BARS);

                        entries.add(ModBlocks.CLAY_WALL);
                        entries.add(ModBlocks.CLAY_WALL_CROSS);
                        entries.add(ModBlocks.CLAY_WALL_DIAGONAL);

                        entries.add(ModBlocks.OAK_CABINET_BLOCK);
                        entries.add(ModBlocks.SPRUCE_CABINET_BLOCK);
                        entries.add(ModBlocks.BIRCH_CABINET_BLOCK);
                        entries.add(ModBlocks.JUNGLE_CABINET_BLOCK);
                        entries.add(ModBlocks.ACACIA_CABINET_BLOCK);
                        entries.add(ModBlocks.DARK_OAK_CABINET_BLOCK);
                        entries.add(ModBlocks.MANGROVE_CABINET_BLOCK);
                        entries.add(ModBlocks.CHERRY_CABINET_BLOCK);
                        entries.add(ModBlocks.CRIMSON_CABINET_BLOCK);
                        entries.add(ModBlocks.WARPED_CABINET_BLOCK);
                        entries.add(ModBlocks.IRONWOOD_CABINET_BLOCK);
                        entries.add(ModBlocks.OLIVE_CABINET_BLOCK);

                        entries.add(ModBlocks.OAK_CHAIR);
                        entries.add(ModBlocks.DARK_OAK_CHAIR);
                        entries.add(ModBlocks.BIRCH_CHAIR);
                        entries.add(ModBlocks.SPRUCE_CHAIR);
                        entries.add(ModBlocks.MANGROVE_CHAIR);
                        entries.add(ModBlocks.CHERRY_CHAIR);
                        entries.add(ModBlocks.ACACIA_CHAIR);
                        entries.add(ModBlocks.JUNGLE_CHAIR);
                        entries.add(ModBlocks.BAMBOO_CHAIR);
                        entries.add(ModBlocks.CRIMSON_CHAIR);
                        entries.add(ModBlocks.WARPED_CHAIR);
                        entries.add(ModBlocks.IRONWOOD_CHAIR);
                        entries.add(ModBlocks.OLIVE_CHAIR);

                        entries.add(ModBlocks.OAK_STOOL);
                        entries.add(ModBlocks.DARK_OAK_STOOL);
                        entries.add(ModBlocks.BIRCH_STOOL);
                        entries.add(ModBlocks.SPRUCE_STOOL);
                        entries.add(ModBlocks.MANGROVE_STOOL);
                        entries.add(ModBlocks.CHERRY_STOOL);
                        entries.add(ModBlocks.ACACIA_STOOL);
                        entries.add(ModBlocks.JUNGLE_STOOL);
                        entries.add(ModBlocks.BAMBOO_STOOL);
                        entries.add(ModBlocks.CRIMSON_STOOL);
                        entries.add(ModBlocks.WARPED_STOOL);
                        entries.add(ModBlocks.IRONWOOD_STOOL);
                        entries.add(ModBlocks.OLIVE_STOOL);

                        entries.add(ModBlocks.OAK_TABLE);
                        entries.add(ModBlocks.DARK_OAK_TABLE);
                        entries.add(ModBlocks.BIRCH_TABLE);
                        entries.add(ModBlocks.SPRUCE_TABLE);
                        entries.add(ModBlocks.MANGROVE_TABLE);
                        entries.add(ModBlocks.CHERRY_TABLE);
                        entries.add(ModBlocks.ACACIA_TABLE);
                        entries.add(ModBlocks.JUNGLE_TABLE);
                        entries.add(ModBlocks.BAMBOO_TABLE);
                        entries.add(ModBlocks.CRIMSON_TABLE);
                        entries.add(ModBlocks.WARPED_TABLE);
                        entries.add(ModBlocks.IRONWOOD_TABLE);
                        entries.add(ModBlocks.OLIVE_TABLE);

                        entries.add(ModBlocks.IRONWOOD_PLANKS);
                        entries.add(ModBlocks.IRONWOOD_STAIRS);
                        entries.add(ModBlocks.IRONWOOD_SLAB);
                        entries.add(ModBlocks.IRONWOOD_FENCE);
                        entries.add(ModBlocks.IRONWOOD_FENCE_GATE);
                        entries.add(ModBlocks.IRONWOOD_DOOR);
                        entries.add(ModBlocks.IRONWOOD_TRAPDOOR);
                        entries.add(ModBlocks.IRONWOOD_PRESSURE_PLATE);
                        entries.add(ModBlocks.IRONWOOD_BUTTON);
                        entries.add(ModItems.IRONWOOD_SIGN);
                        entries.add(ModItems.IRONWOOD_HANGING_SIGN);
                        entries.add(ModItems.IRONWOOD_BOAT);
                        entries.add(ModItems.IRONWOOD_CHEST_BOAT);

                        entries.add(ModBlocks.OLIVE_PLANKS);
                        entries.add(ModBlocks.OLIVE_STAIRS);
                        entries.add(ModBlocks.OLIVE_SLAB);
                        entries.add(ModBlocks.OLIVE_FENCE);
                        entries.add(ModBlocks.OLIVE_FENCE_GATE);
                        entries.add(ModBlocks.OLIVE_DOOR);
                        entries.add(ModBlocks.OLIVE_TRAPDOOR);
                        entries.add(ModBlocks.OLIVE_PRESSURE_PLATE);
                        entries.add(ModBlocks.OLIVE_BUTTON);
                        entries.add(ModItems.OLIVE_SIGN);
                        entries.add(ModItems.OLIVE_HANGING_SIGN);
                        entries.add(ModItems.OLIVE_BOAT);
                        entries.add(ModItems.OLIVE_CHEST_BOAT);

                        entries.add(ModBlocks.BLACK_PAINTED_PLANKS);
                        entries.add(ModBlocks.BLUE_PAINTED_PLANKS);
                        entries.add(ModBlocks.BROWN_PAINTED_PLANKS);
                        entries.add(ModBlocks.CYAN_PAINTED_PLANKS);
                        entries.add(ModBlocks.GRAY_PAINTED_PLANKS);
                        entries.add(ModBlocks.GREEN_PAINTED_PLANKS);
                        entries.add(ModBlocks.LIGHT_BLUE_PAINTED_PLANKS);
                        entries.add(ModBlocks.LIME_PAINTED_PLANKS);
                        entries.add(ModBlocks.MAGENTA_PAINTED_PLANKS);
                        entries.add(ModBlocks.ORANGE_PAINTED_PLANKS);
                        entries.add(ModBlocks.PINK_PAINTED_PLANKS);
                        entries.add(ModBlocks.PURPLE_PAINTED_PLANKS);
                        entries.add(ModBlocks.RED_PAINTED_PLANKS);
                        entries.add(ModBlocks.LIGHT_GRAY_PAINTED_PLANKS);
                        entries.add(ModBlocks.WHITE_PAINTED_PLANKS);
                        entries.add(ModBlocks.YELLOW_PAINTED_PLANKS);
                        entries.add(ModBlocks.GARGOYLE);
                        entries.add(ModBlocks.GOLDEN_CHAIN);
                        entries.add(ModBlocks.COPPER_CHAIN);
                        entries.add(ModBlocks.EXPOSED_COPPER_CHAIN);
                        entries.add(ModBlocks.WEATHERED_COPPER_CHAIN);
                        entries.add(ModBlocks.OXIDIZED_COPPER_CHAIN);
                        entries.add(ModBlocks.WAXED_COPPER_CHAIN);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN);
                        entries.add(ModBlocks.GOLDEN_LANTERN);
                        entries.add(ModBlocks.GOLDEN_SOUL_LANTERN);
                        entries.add(ModBlocks.COPPER_LANTERN);
                        entries.add(ModBlocks.EXPOSED_COPPER_LANTERN);
                        entries.add(ModBlocks.WEATHERED_COPPER_LANTERN);
                        entries.add(ModBlocks.OXIDIZED_COPPER_LANTERN);
                        entries.add(ModBlocks.WAXED_COPPER_LANTERN);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_LANTERN);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_LANTERN);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN);
                        entries.add(ModBlocks.COPPER_SOUL_LANTERN);
                        entries.add(ModBlocks.EXPOSED_COPPER_SOUL_LANTERN);
                        entries.add(ModBlocks.WEATHERED_COPPER_SOUL_LANTERN);
                        entries.add(ModBlocks.OXIDIZED_COPPER_SOUL_LANTERN);
                        entries.add(ModBlocks.WAXED_COPPER_SOUL_LANTERN);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_SOUL_LANTERN);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_SOUL_LANTERN);
                        entries.add(ModBlocks.CHANDELIER);
                        entries.add(ModBlocks.GOLDEN_CHANDELIER);
                        entries.add(ModBlocks.COPPER_CHANDELIER);
                        entries.add(ModBlocks.EXPOSED_COPPER_CHANDELIER);
                        entries.add(ModBlocks.WEATHERED_COPPER_CHANDELIER);
                        entries.add(ModBlocks.OXIDIZED_COPPER_CHANDELIER);
                        entries.add(ModBlocks.WAXED_COPPER_CHANDELIER);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_CHANDELIER);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_CHANDELIER);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_CHANDELIER);

                        entries.add(ModBlocks.IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.WHITE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.LIGHT_GRAY_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.GRAY_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.BLACK_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.BROWN_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.RED_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.ORANGE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.YELLOW_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.GREEN_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.LIME_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.CYAN_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.LIGHT_BLUE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.BLUE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.PURPLE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.MAGENTA_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.PINK_IRON_CANDLE_HOLDER);

                        entries.add(ModBlocks.GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.WHITE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.LIGHT_GRAY_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.GRAY_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.BLACK_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.BROWN_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.RED_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.ORANGE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.YELLOW_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.GREEN_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.LIME_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.CYAN_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.LIGHT_BLUE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.BLUE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.PURPLE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.MAGENTA_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.PINK_GOLDEN_CANDLE_HOLDER);

                        entries.add(ModBlocks.COPPER_CANDLE_HOLDER);
                        entries.add(ModBlocks.COPPER_WHITE_CANDLE_HOLDER);
                        entries.add(ModBlocks.COPPER_LIGHT_GRAY_CANDLE_HOLDER);
                        entries.add(ModBlocks.COPPER_GRAY_CANDLE_HOLDER);
                        entries.add(ModBlocks.COPPER_BLACK_CANDLE_HOLDER);
                        entries.add(ModBlocks.COPPER_BROWN_CANDLE_HOLDER);
                        entries.add(ModBlocks.COPPER_RED_CANDLE_HOLDER);
                        entries.add(ModBlocks.COPPER_ORANGE_CANDLE_HOLDER);
                        entries.add(ModBlocks.COPPER_YELLOW_CANDLE_HOLDER);
                        entries.add(ModBlocks.COPPER_LIME_CANDLE_HOLDER);
                        entries.add(ModBlocks.COPPER_GREEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.COPPER_CYAN_CANDLE_HOLDER);
                        entries.add(ModBlocks.COPPER_LIGHT_BLUE_CANDLE_HOLDER);
                        entries.add(ModBlocks.COPPER_BLUE_CANDLE_HOLDER);
                        entries.add(ModBlocks.COPPER_PURPLE_CANDLE_HOLDER);
                        entries.add(ModBlocks.COPPER_MAGENTA_CANDLE_HOLDER);
                        entries.add(ModBlocks.COPPER_PINK_CANDLE_HOLDER);

                        entries.add(ModBlocks.EXPOSED_COPPER_CANDLE_HOLDER);
                        entries.add(ModBlocks.EXPOSED_COPPER_WHITE_CANDLE_HOLDER);
                        entries.add(ModBlocks.EXPOSED_COPPER_LIGHT_GRAY_CANDLE_HOLDER);
                        entries.add(ModBlocks.EXPOSED_COPPER_GRAY_CANDLE_HOLDER);
                        entries.add(ModBlocks.EXPOSED_COPPER_BLACK_CANDLE_HOLDER);
                        entries.add(ModBlocks.EXPOSED_COPPER_BROWN_CANDLE_HOLDER);
                        entries.add(ModBlocks.EXPOSED_COPPER_RED_CANDLE_HOLDER);
                        entries.add(ModBlocks.EXPOSED_COPPER_ORANGE_CANDLE_HOLDER);
                        entries.add(ModBlocks.EXPOSED_COPPER_YELLOW_CANDLE_HOLDER);
                        entries.add(ModBlocks.EXPOSED_COPPER_LIME_CANDLE_HOLDER);
                        entries.add(ModBlocks.EXPOSED_COPPER_GREEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.EXPOSED_COPPER_CYAN_CANDLE_HOLDER);
                        entries.add(ModBlocks.EXPOSED_COPPER_LIGHT_BLUE_CANDLE_HOLDER);
                        entries.add(ModBlocks.EXPOSED_COPPER_BLUE_CANDLE_HOLDER);
                        entries.add(ModBlocks.EXPOSED_COPPER_PURPLE_CANDLE_HOLDER);
                        entries.add(ModBlocks.EXPOSED_COPPER_MAGENTA_CANDLE_HOLDER);
                        entries.add(ModBlocks.EXPOSED_COPPER_PINK_CANDLE_HOLDER);

                        entries.add(ModBlocks.WEATHERED_COPPER_CANDLE_HOLDER);
                        entries.add(ModBlocks.WEATHERED_COPPER_WHITE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WEATHERED_COPPER_LIGHT_GRAY_CANDLE_HOLDER);
                        entries.add(ModBlocks.WEATHERED_COPPER_GRAY_CANDLE_HOLDER);
                        entries.add(ModBlocks.WEATHERED_COPPER_BLACK_CANDLE_HOLDER);
                        entries.add(ModBlocks.WEATHERED_COPPER_BROWN_CANDLE_HOLDER);
                        entries.add(ModBlocks.WEATHERED_COPPER_RED_CANDLE_HOLDER);
                        entries.add(ModBlocks.WEATHERED_COPPER_ORANGE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WEATHERED_COPPER_YELLOW_CANDLE_HOLDER);
                        entries.add(ModBlocks.WEATHERED_COPPER_LIME_CANDLE_HOLDER);
                        entries.add(ModBlocks.WEATHERED_COPPER_GREEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.WEATHERED_COPPER_CYAN_CANDLE_HOLDER);
                        entries.add(ModBlocks.WEATHERED_COPPER_LIGHT_BLUE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WEATHERED_COPPER_BLUE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WEATHERED_COPPER_PURPLE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WEATHERED_COPPER_MAGENTA_CANDLE_HOLDER);
                        entries.add(ModBlocks.WEATHERED_COPPER_PINK_CANDLE_HOLDER);

                        entries.add(ModBlocks.OXIDIZED_COPPER_CANDLE_HOLDER);
                        entries.add(ModBlocks.OXIDIZED_COPPER_WHITE_CANDLE_HOLDER);
                        entries.add(ModBlocks.OXIDIZED_COPPER_LIGHT_GRAY_CANDLE_HOLDER);
                        entries.add(ModBlocks.OXIDIZED_COPPER_GRAY_CANDLE_HOLDER);
                        entries.add(ModBlocks.OXIDIZED_COPPER_BLACK_CANDLE_HOLDER);
                        entries.add(ModBlocks.OXIDIZED_COPPER_BROWN_CANDLE_HOLDER);
                        entries.add(ModBlocks.OXIDIZED_COPPER_RED_CANDLE_HOLDER);
                        entries.add(ModBlocks.OXIDIZED_COPPER_ORANGE_CANDLE_HOLDER);
                        entries.add(ModBlocks.OXIDIZED_COPPER_YELLOW_CANDLE_HOLDER);
                        entries.add(ModBlocks.OXIDIZED_COPPER_LIME_CANDLE_HOLDER);
                        entries.add(ModBlocks.OXIDIZED_COPPER_GREEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.OXIDIZED_COPPER_CYAN_CANDLE_HOLDER);
                        entries.add(ModBlocks.OXIDIZED_COPPER_LIGHT_BLUE_CANDLE_HOLDER);
                        entries.add(ModBlocks.OXIDIZED_COPPER_BLUE_CANDLE_HOLDER);
                        entries.add(ModBlocks.OXIDIZED_COPPER_PURPLE_CANDLE_HOLDER);
                        entries.add(ModBlocks.OXIDIZED_COPPER_MAGENTA_CANDLE_HOLDER);
                        entries.add(ModBlocks.OXIDIZED_COPPER_PINK_CANDLE_HOLDER);

                        entries.add(ModBlocks.DOUBLE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.WHITE_DOUBLE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.LIGHT_GRAY_DOUBLE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.GRAY_DOUBLE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.BLACK_DOUBLE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.BROWN_DOUBLE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.RED_DOUBLE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.ORANGE_DOUBLE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.YELLOW_DOUBLE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.GREEN_DOUBLE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.LIME_DOUBLE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.CYAN_DOUBLE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.LIGHT_BLUE_DOUBLE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.BLUE_DOUBLE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.PURPLE_DOUBLE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.MAGENTA_DOUBLE_IRON_CANDLE_HOLDER);
                        entries.add(ModBlocks.PINK_DOUBLE_IRON_CANDLE_HOLDER);

                        entries.add(ModBlocks.DOUBLE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.WHITE_DOUBLE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.LIGHT_GRAY_DOUBLE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.GRAY_DOUBLE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.BLACK_DOUBLE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.BROWN_DOUBLE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.RED_DOUBLE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.ORANGE_DOUBLE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.YELLOW_DOUBLE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.GREEN_DOUBLE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.LIME_DOUBLE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.CYAN_DOUBLE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.LIGHT_BLUE_DOUBLE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.BLUE_DOUBLE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.PURPLE_DOUBLE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.MAGENTA_DOUBLE_GOLDEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.PINK_DOUBLE_GOLDEN_CANDLE_HOLDER);

                        /*entries.add(ModBlocks.WAXED_COPPER_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_COPPER_WHITE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_COPPER_LIGHT_GRAY_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_COPPER_GRAY_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_COPPER_BLACK_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_COPPER_BROWN_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_COPPER_RED_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_COPPER_ORANGE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_COPPER_YELLOW_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_COPPER_LIME_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_COPPER_GREEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_COPPER_CYAN_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_COPPER_LIGHT_BLUE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_COPPER_BLUE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_COPPER_PURPLE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_COPPER_MAGENTA_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_COPPER_PINK_CANDLE_HOLDER);

                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_WHITE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_LIGHT_GRAY_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_GRAY_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_BLACK_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_BROWN_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_RED_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_ORANGE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_YELLOW_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_LIME_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_GREEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_CYAN_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_LIGHT_BLUE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_BLUE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_PURPLE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_MAGENTA_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_PINK_CANDLE_HOLDER);

                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_WHITE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_LIGHT_GRAY_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_GRAY_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_BLACK_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_BROWN_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_RED_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_ORANGE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_YELLOW_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_LIME_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_GREEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_CYAN_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_LIGHT_BLUE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_BLUE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_PURPLE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_MAGENTA_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_PINK_CANDLE_HOLDER);

                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_WHITE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_LIGHT_GRAY_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_GRAY_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_BLACK_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_BROWN_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_RED_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_ORANGE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_YELLOW_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_LIME_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_GREEN_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_CYAN_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_LIGHT_BLUE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_BLUE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_PURPLE_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_MAGENTA_CANDLE_HOLDER);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_PINK_CANDLE_HOLDER);*/

                    }).build());
    public static  final ItemGroup ALCHEMY_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Resprouted.MOD_ID,"alchemy_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.ELIXIR_ICON))
                    .displayName(Text.translatable("itemgroup.resprouted.alchemy"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.ALOE_VERA);
                        entries.add(ModItems.BLOOD_ORCHID);
                        entries.add(ModItems.CHAMOMILE);
                        entries.add(ModItems.CLOUDSBLUFF);
                        entries.add(ModItems.COHOSH);
                        entries.add(ModItems.HORSETAIL);
                        entries.add(ModItems.VANTA_LILY);
                        entries.add(ModItems.WIND_THISTLE);
                        entries.add(ModItems.CORE_ROOT);
                        entries.add(ModItems.GINSENG);
                        entries.add(ModItems.MARSH_MALLOW);
                        entries.add(ModItems.MOONCAP_MUSHROOM);
                        entries.add(ModItems.DEATHSTALK_MUSHROOM);
                        entries.add(ModBlocks.CONDENSER);
                        entries.add(ModBlocks.RETORT);
                        entries.add(ModBlocks.ADVANCED_CONDENSER);
                        entries.add(ModBlocks.ADVANCED_RETORT);
                        entries.addAll(ElixirUtils.getElixirs());
                    }).build());

    public static void registerItemGroup(){
        Resprouted.LOGGER.info("Registering item groups for " + Resprouted.MOD_ID);
    }
}

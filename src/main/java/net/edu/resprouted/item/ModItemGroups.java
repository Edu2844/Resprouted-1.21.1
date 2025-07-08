package net.edu.resprouted.item;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.util.ElixirUtil;
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
                        entries.add(ModItems.GLOW_BERRY_JUICE_BOTTLE);
                        entries.add(ModItems.IRON_BERRY_JUICE_BOTTLE);

                        entries.add(ModItems.HONEY_BUCKET);
                        entries.add(ModItems.APPLE_JUICE_BUCKET);
                        entries.add(ModItems.GOLDEN_APPLE_JUICE_BUCKET);
                        entries.add(ModItems.GRAPE_JUICE_BUCKET);
                        entries.add(ModItems.SWEET_BERRY_JUICE_BUCKET);
                        entries.add(ModItems.OLIVE_OIL_BUCKET);
                        entries.add(ModItems.GLOW_BERRY_JUICE_BUCKET);
                        entries.add(ModItems.IRON_BERRY_JUICE_BUCKET);

                        entries.add(ModItems.LAMB_STEW);
                        entries.add(ModItems.HONEY_GLAZED_CARROTS);
                        entries.add(ModBlocks.BLUE_BERRY_CAKE);


                    }).build());
    public static  final ItemGroup DECORATION_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Resprouted.MOD_ID,"decoration_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.POLISHED_SLATE))
                    .displayName(Text.translatable("itemgroup.resprouted.decoration"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.SLATE);
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
                        entries.add(ModBlocks.STONE_PILLAR);

                        entries.add(ModBlocks.ANDESITE_BRICKS);
                        entries.add(ModBlocks.CRACKED_ANDESITE_BRICKS);
                        entries.add(ModBlocks.CHISELED_ANDESITE);
                        entries.add(ModBlocks.ANDESITE_PILLAR);

                        entries.add(ModBlocks.DIORITE_BRICKS);
                        entries.add(ModBlocks.CRACKED_DIORITE_BRICKS);
                        entries.add(ModBlocks.CHISELED_DIORITE);
                        entries.add(ModBlocks.DIORITE_PILLAR);

                        entries.add(ModBlocks.GRANITE_BRICKS);
                        entries.add(ModBlocks.CRACKED_GRANITE_BRICKS);
                        entries.add(ModBlocks.CHISELED_GRANITE);
                        entries.add(ModBlocks.GRANITE_PILLAR);

                        entries.add(ModBlocks.POLISHED_SANDSTONE);
                        entries.add(ModBlocks.SANDSTONE_BRICKS);
                        entries.add(ModBlocks.SANDSTONE_PILLAR);

                        entries.add(ModBlocks.POLISHED_RED_SANDSTONE);
                        entries.add(ModBlocks.RED_SANDSTONE_BRICKS);
                        entries.add(ModBlocks.RED_SANDSTONE_PILLAR);

                        entries.add(ModBlocks.WROUGHT_IRON_BLOCK);
                        entries.add(ModBlocks.CUT_WROUGHT_IRON_BLOCK);

                        entries.add(ModBlocks.CLAY_WALL);
                        entries.add(ModBlocks.CLAY_WALL_CROSS);
                        entries.add(ModBlocks.CLAY_WALL_DIAGONAL);

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

                        entries.add(ModBlocks.OLIVE_PLANKS);
                        entries.add(ModBlocks.OLIVE_STAIRS);
                        entries.add(ModBlocks.OLIVE_SLAB);
                        entries.add(ModBlocks.OLIVE_FENCE);
                        entries.add(ModBlocks.OLIVE_FENCE_GATE);
                        entries.add(ModBlocks.OLIVE_DOOR);
                        entries.add(ModBlocks.OLIVE_TRAPDOOR);
                        entries.add(ModBlocks.OLIVE_PRESSURE_PLATE);
                        entries.add(ModBlocks.OLIVE_BUTTON);

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
                        entries.addAll(ElixirUtil.getElixirs());
                    }).build());

    public static void registerItemGroup(){
        Resprouted.LOGGER.info("Registering item groups for " + Resprouted.MOD_ID);
    }
}

package net.edu.resprouted.datagen;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.IRONWOOD_LOG.asItem(),
                        ModBlocks.IRONWOOD_WOOD.asItem(),
                        ModBlocks.STRIPPED_IRONWOOD_LOG.asItem(),
                        ModBlocks.OLIVE_LOG.asItem(),
                        ModBlocks.OLIVE_WOOD.asItem(),
                        ModBlocks.STRIPPED_OLIVE_LOG.asItem()
                );

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.IRONWOOD_PLANKS.asItem(),
                        ModBlocks.OLIVE_PLANKS.asItem(),
                        ModBlocks.BLACK_PAINTED_PLANKS.asItem(),
                        ModBlocks.BLUE_PAINTED_PLANKS.asItem(),
                        ModBlocks.BROWN_PAINTED_PLANKS.asItem(),
                        ModBlocks.CYAN_PAINTED_PLANKS.asItem(),
                        ModBlocks.GRAY_PAINTED_PLANKS.asItem(),
                        ModBlocks.GREEN_PAINTED_PLANKS.asItem(),
                        ModBlocks.LIGHT_BLUE_PAINTED_PLANKS.asItem(),
                        ModBlocks.LIME_PAINTED_PLANKS.asItem(),
                        ModBlocks.MAGENTA_PAINTED_PLANKS.asItem(),
                        ModBlocks.ORANGE_PAINTED_PLANKS.asItem(),
                        ModBlocks.PINK_PAINTED_PLANKS.asItem(),
                        ModBlocks.PURPLE_PAINTED_PLANKS.asItem(),
                        ModBlocks.RED_PAINTED_PLANKS.asItem(),
                        ModBlocks.LIGHT_GRAY_PAINTED_PLANKS.asItem(),
                        ModBlocks.WHITE_PAINTED_PLANKS.asItem(),
                        ModBlocks.YELLOW_PAINTED_PLANKS.asItem()
                );

        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                .add(ModBlocks.IRONWOOD_DOOR.asItem(),
                        ModBlocks.OLIVE_DOOR.asItem()

                );

        getOrCreateTagBuilder(ItemTags.SIGNS)
                .add(ModBlocks.IRONWOOD_SIGN.asItem(),
                        ModBlocks.OLIVE_SIGN.asItem()

                );

        getOrCreateTagBuilder(ItemTags.HANGING_SIGNS)
                .add(ModBlocks.IRONWOOD_HANGING_SIGN.asItem(),
                        ModBlocks.OLIVE_HANGING_SIGN.asItem()

                );

        getOrCreateTagBuilder(ItemTags.STONE_TOOL_MATERIALS)
                .add(ModBlocks.COBBLED_SLATE.asItem());

        getOrCreateTagBuilder(ModTags.Items.TOMATO)
                .add(ModItems.TOMATO);

        getOrCreateTagBuilder(ModTags.Items.TALLOW)
                .add(ModItems.TALLOW);

        getOrCreateTagBuilder(ModTags.Items.GRAPES)
                .add(ModItems.GRAPES);

        getOrCreateTagBuilder(ModTags.Items.OLIVES)
                .add(ModItems.OLIVES);

        getOrCreateTagBuilder(ModTags.Items.PEPPERS)
                .add(ModItems.CHILI_PEPPER,
                        ModItems.GHOST_PEPPER);

        getOrCreateTagBuilder(ModTags.Items.CHILI_PEPPER)
                .add(ModItems.CHILI_PEPPER);

        getOrCreateTagBuilder(ModTags.Items.GOLDEN_DUST)
                .add(ModItems.GOLDEN_DUST,
                        ModItems.TINY_GOLDEN_DUST);

        getOrCreateTagBuilder(ModTags.Items.IRON_DUST)
                .add(ModItems.IRON_DUST,
                        ModItems.TINY_IRON_DUST);

        getOrCreateTagBuilder(ModTags.Items.COPPER_NUGGET)
                .add(ModItems.COPPER_NUGGET);

        getOrCreateTagBuilder(ModTags.Items.DUSTS)
                .add(ModItems.IRON_DUST,
                        ModItems.GOLDEN_DUST,
                        ModItems.TINY_IRON_DUST,
                        ModItems.TINY_GOLDEN_DUST,
                        ModItems.TINY_GLOWSTONE_DUST
                );

        getOrCreateTagBuilder(ModTags.Items.CROPS)
                .add(ModItems.TOMATO,
                        ModItems.CHILI_PEPPER,
                        ModItems.GRAPES
                );

        getOrCreateTagBuilder(ModTags.Items.GLASS_BOTTLES)
                .add(ModItems.ALE_WORT_BOTTLE,
                        ModItems.GRAPE_JUICE_BOTTLE,
                        ModItems.APPLE_JUICE_BOTTLE,
                        ModItems.GOLDEN_APPLE_JUICE_BOTTLE,
                        ModItems.GLOW_BERRY_JUICE_BOTTLE,
                        ModItems.IRON_BERRY_JUICE_BOTTLE,
                        ModItems.SWEET_BERRY_JUICE_BOTTLE,
                        ModItems.VANTA_OIL_BOTTLE,
                        ModItems.OLIVE_OIL_BOTTLE,
                        ModItems.SUGAR_CANE_JUICE_BOTTLE
                );

        getOrCreateTagBuilder(ModTags.Items.CAN_BE_OILED)
                .add(Items.BEEF,
                        Items.RABBIT_STEW,
                        Items.MUSHROOM_STEW,
                        Items.SUSPICIOUS_STEW,
                        Items.COOKED_BEEF,
                        Items.PORKCHOP,
                        Items.COOKED_PORKCHOP,
                        Items.MUTTON,
                        Items.COOKED_MUTTON,
                        Items.CHICKEN,
                        Items.COOKED_CHICKEN,
                        Items.RABBIT,
                        Items.COOKED_RABBIT,
                        Items.COD,
                        Items.COOKED_COD,
                        Items.SALMON,
                        Items.COOKED_SALMON,
                        Items.TROPICAL_FISH,
                        Items.BREAD,
                        Items.POTATO,
                        Items.BAKED_POTATO,
                        Items.CARROT,
                        Items.GOLDEN_CARROT,
                        Items.BEETROOT,
                        Items.SWEET_BERRIES,
                        Items.GLOW_BERRIES,
                        Items.APPLE,
                        Items.GOLDEN_APPLE,
                        ModItems.TOMATO,
                        ModItems.CHILI_PEPPER,
                        ModItems.GRAPES,
                        ModItems.IRON_BERRIES,
                        ModItems.OLIVES
                );

        getOrCreateTagBuilder(ItemTags.CHICKEN_FOOD)
                .add(ModItems.TOMATO_SEEDS,
                        ModItems.CHILI_PEPPER_SEEDS,
                        ModItems.GRAPE_SEEDS
                );

        getOrCreateTagBuilder(ModTags.Items.ALCHEMY_MODIFIERS)
                .add(ModItems.HORSETAIL,
                        ModItems.MARSH_MALLOW
                );

        getOrCreateTagBuilder(ModTags.Items.BOOZE_BOTTLES)
                .add(ModItems.ALE_BOTTLE,
                        ModItems.IRON_WINE_BOTTLE,
                        ModItems.WINE_BOTTLE,
                        ModItems.SWEET_BERRY_WINE_BOTTLE,
                        ModItems.GLOW_BERRY_WINE_BOTTLE,
                        ModItems.CIDER_BOTTLE,
                        ModItems.AMBROSIA_BOTTLE,
                        ModItems.RUM_BOTTLE,
                        ModItems.MEAD_BOTTLE
                );

    }
}

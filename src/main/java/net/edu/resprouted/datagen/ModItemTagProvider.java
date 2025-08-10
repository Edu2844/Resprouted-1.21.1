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
                .add(ModBlocks.IRONWOOD_LOG.asItem())
                .add(ModBlocks.IRONWOOD_WOOD.asItem())
                .add(ModBlocks.STRIPPED_IRONWOOD_LOG.asItem())
                .add(ModBlocks.OLIVE_LOG.asItem())
                .add(ModBlocks.OLIVE_WOOD.asItem())
                .add(ModBlocks.STRIPPED_OLIVE_LOG.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.IRONWOOD_PLANKS.asItem())
                .add(ModBlocks.OLIVE_PLANKS.asItem())
                .add(ModBlocks.BLACK_PAINTED_PLANKS.asItem())
                .add(ModBlocks.BLUE_PAINTED_PLANKS.asItem())
                .add(ModBlocks.BROWN_PAINTED_PLANKS.asItem())
                .add(ModBlocks.CYAN_PAINTED_PLANKS.asItem())
                .add(ModBlocks.GRAY_PAINTED_PLANKS.asItem())
                .add(ModBlocks.GREEN_PAINTED_PLANKS.asItem())
                .add(ModBlocks.LIGHT_BLUE_PAINTED_PLANKS.asItem())
                .add(ModBlocks.LIME_PAINTED_PLANKS.asItem())
                .add(ModBlocks.MAGENTA_PAINTED_PLANKS.asItem())
                .add(ModBlocks.ORANGE_PAINTED_PLANKS.asItem())
                .add(ModBlocks.PINK_PAINTED_PLANKS.asItem())
                .add(ModBlocks.PURPLE_PAINTED_PLANKS.asItem())
                .add(ModBlocks.RED_PAINTED_PLANKS.asItem())
                .add(ModBlocks.LIGHT_GRAY_PAINTED_PLANKS.asItem())
                .add(ModBlocks.WHITE_PAINTED_PLANKS.asItem())
                .add(ModBlocks.YELLOW_PAINTED_PLANKS.asItem())
        ;
        getOrCreateTagBuilder(ModTags.Items.TOMATO)
                .add(ModItems.TOMATO);

        getOrCreateTagBuilder(ModTags.Items.TOMATO_SEEDS)
                .add(ModItems.TOMATO_SEEDS);

        getOrCreateTagBuilder(ModTags.Items.IRON_BERRIES)
                .add(ModItems.IRON_BERRIES);

        getOrCreateTagBuilder(ModTags.Items.GRAPES)
                .add(ModItems.GRAPES);

        getOrCreateTagBuilder(ModTags.Items.GRAPE_SEEDS)
                .add(ModItems.GRAPE_SEEDS);

        getOrCreateTagBuilder(ModTags.Items.OLIVES)
                .add(ModItems.OLIVES);

        getOrCreateTagBuilder(ModTags.Items.PEPPERS)
                .add(ModItems.CHILI_PEPPER)
                .add(ModItems.GHOST_PEPPER)
        ;

        getOrCreateTagBuilder(ModTags.Items.CHILI_PEPPER)
                .add(ModItems.CHILI_PEPPER);

        getOrCreateTagBuilder(ModTags.Items.GHOST_PEPPER)
                .add(ModItems.GHOST_PEPPER);

        getOrCreateTagBuilder(ModTags.Items.CHILI_PEPPER_SEEDS)
                .add(ModItems.CHILI_PEPPER_SEEDS);

        getOrCreateTagBuilder(ModTags.Items.CHILI_PEPPER_SEEDS)
                .add(ModItems.CHILI_PEPPER_SEEDS);

        getOrCreateTagBuilder(ModTags.Items.GOLDEN_DUST)
                .add(ModItems.GOLDEN_DUST);

        getOrCreateTagBuilder(ModTags.Items.IRON_DUST)
                .add(ModItems.IRON_DUST);
        getOrCreateTagBuilder(ModTags.Items.COPPER_NUGGET)
                .add(ModItems.COPPER_NUGGET)
        ;
        getOrCreateTagBuilder(ModTags.Items.DUSTS)
                .add(ModItems.IRON_DUST)
                .add(ModItems.GOLDEN_DUST)
                .add(ModItems.TINY_IRON_DUST)
                .add(ModItems.TINY_GOLDEN_DUST)
                .add(ModItems.TINY_GLOWSTONE_DUST)
        ;
        getOrCreateTagBuilder(ModTags.Items.CROPS)
                .add(ModItems.TOMATO)
                .add(ModItems.CHILI_PEPPER)
                .add(ModItems.GRAPES)
        ;
        getOrCreateTagBuilder(ModTags.Items.CAN_BE_OILED)
                .add(ModItems.TOMATO)
                .add(Items.COOKED_PORKCHOP)
        ;
        getOrCreateTagBuilder(ModTags.Items.GLASS_BOTTLES)
                .add(ModItems.GRAPE_JUICE_BOTTLE)
                .add(ModItems.APPLE_JUICE_BOTTLE)
                .add(ModItems.GOLDEN_APPLE_JUICE_BOTTLE)
                .add(ModItems.GLOW_BERRY_JUICE_BOTTLE)
                .add(ModItems.IRON_BERRY_JUICE_BOTTLE)
                .add(ModItems.SWEET_BERRY_JUICE_BOTTLE)
                .add(ModItems.SWEET_BERRY_JUICE_BOTTLE)
                .add(ModItems.OLIVE_OIL_BOTTLE)
        ;
        getOrCreateTagBuilder(ModTags.Items.IGNITERS)
                .add(Items.FLINT_AND_STEEL)
                .add(Items.FIRE_CHARGE)
        ;
        getOrCreateTagBuilder(ModTags.Items.FUELS)
                .add(Items.CHARCOAL)
        ;
    }
}

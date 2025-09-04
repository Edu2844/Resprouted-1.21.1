package net.edu.resprouted.util;

import net.edu.resprouted.Resprouted;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> FERTILE_SOILS = createTag("fertile_soils");
        public static final TagKey<Block> ALLOWS_GROWTH = createTag("allows_growth");
        public static final TagKey<Block> EVAPORATING_BOOSTERS = createTag("evaporating_boosters");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Resprouted.MOD_ID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> TOMATO = createTag("tomato");
        public static final TagKey<Item> TOMATO_SEEDS = createTag("tomato_seeds");
        public static final TagKey<Item> IRON_BERRIES = createTag("iron_berries");
        public static final TagKey<Item> GRAPES = createTag("grapes");
        public static final TagKey<Item> GRAPE_SEEDS = createTag("grape_seeds");
        public static final TagKey<Item> OLIVES = createTag("olives");
        public static final TagKey<Item> PEPPERS = createTag("peppers");
        public static final TagKey<Item> CHILI_PEPPER = createTag("chili_pepper");
        public static final TagKey<Item> CHILI_PEPPER_SEEDS = createTag("chili_pepper_seeds");
        public static final TagKey<Item> GHOST_PEPPER = createTag("ghost_pepper");
        public static final TagKey<Item> GOLDEN_DUST = createTag("golden_dust");
        public static final TagKey<Item> IRON_DUST = createTag("iron_dust");
        public static final TagKey<Item> COPPER_NUGGET = createTag("copper_nugget");
        public static final TagKey<Item> DUSTS = createTag("dusts");
        public static final TagKey<Item> CROPS = createTag("crops");
        public static final TagKey<Item> GLASS_BOTTLES = createTag("glass_bottles");
        public static final TagKey<Item> IGNITERS = createTag("igniters");
        public static final TagKey<Item> FUELS = createTag("fuels");
        public static final TagKey<Item> CAN_BE_OILED = createTag("can_be_oiled");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Resprouted.MOD_ID, name));
        }
    }
    public static class Fluids {
        public static final TagKey<Fluid> HONEY = createTag("honey");
        public static final TagKey<Fluid> IRON_BERRY_JUICE = createTag("iron_berry_juice");
        public static final TagKey<Fluid> SWEET_BERRY_JUICE = createTag("sweet_berry_juice");
        public static final TagKey<Fluid> GLOW_BERRY_JUICE = createTag("glow_berry_juice");
        public static final TagKey<Fluid> GRAPE_JUICE = createTag("grape_juice");
        public static final TagKey<Fluid> APPLE_JUICE = createTag("apple_juice");
        public static final TagKey<Fluid> GOLDEN_APPLE_JUICE = createTag("golden_apple_juice");
        public static final TagKey<Fluid> OLIVE_OIL = createTag("olive_oil");
        public static final List<TagKey<Fluid>> ALL_FLUID_TAGS = List.of(
                HONEY,
                IRON_BERRY_JUICE,
                SWEET_BERRY_JUICE,
                GLOW_BERRY_JUICE,
                GRAPE_JUICE,
                APPLE_JUICE,
                GOLDEN_APPLE_JUICE,
                OLIVE_OIL
        );
        private static TagKey<Fluid> createTag(String name) {
            return TagKey.of(RegistryKeys.FLUID, Identifier.of(Resprouted.MOD_ID, name));
        }
    }
}

package net.edu.resprouted.util;

import net.edu.resprouted.Resprouted;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> PRESERVES_FARMLAND = createTag("preserves_farmland");
        public static final TagKey<Block> FERTILE_SOILS = createTag("fertile_soils");
        public static final TagKey<Block> ALLOWS_HERBS_GROWTH = createTag("allows_herbs_growth");
        public static final TagKey<Block> DRYING_BOOSTERS = createTag("drying_boosters");
        public static final TagKey<Block> DIFFERENT_TUB_RENDER = createTag("different_tub_render");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Resprouted.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TOMATO = createTag("tomato");
        public static final TagKey<Item> GRAPES = createTag("grapes");
        public static final TagKey<Item> OLIVES = createTag("olives");
        public static final TagKey<Item> PEPPERS = createTag("peppers");
        public static final TagKey<Item> CHILI_PEPPER = createTag("chili_pepper");
        public static final TagKey<Item> GOLDEN_DUST = createTag("golden_dust");
        public static final TagKey<Item> IRON_DUST = createTag("iron_dust");
        public static final TagKey<Item> COPPER_NUGGET = createTag("copper_nugget");
        public static final TagKey<Item> DUSTS = createTag("dusts");
        public static final TagKey<Item> CROPS = createTag("crops");
        public static final TagKey<Item> GLASS_BOTTLES = createTag("glass_bottles");
        public static final TagKey<Item> TALLOW = createTag("tallow");
        public static final TagKey<Item> CAN_BE_OILED = createTag("can_be_oiled");
        public static final TagKey<Item> ALCHEMY_MODIFIERS = createTag("alchemy_modifiers");
        public static final TagKey<Item> BOOZE_BOTTLES = createTag("booze_bottles");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Resprouted.MOD_ID, name));
        }
    }

    public static class Fluids {
        public static final TagKey<Fluid> ALE = createTag("ale");
        public static final TagKey<Fluid> ALE_WORT = createTag("ale_wort");
        public static final TagKey<Fluid> AMBROSIA = createTag("ambrosia");
        public static final TagKey<Fluid> APPLE_JUICE = createTag("apple_juice");
        public static final TagKey<Fluid> CIDER = createTag("cider");
        public static final TagKey<Fluid> GLOW_BERRY_JUICE = createTag("glow_berry_juice");
        public static final TagKey<Fluid> GLOW_BERRY_WINE = createTag("glow_berry_wine");
        public static final TagKey<Fluid> GOLDEN_APPLE_JUICE = createTag("golden_apple_juice");
        public static final TagKey<Fluid> GRAPE_JUICE = createTag("grape_juice");
        public static final TagKey<Fluid> HONEY = createTag("honey");
        public static final TagKey<Fluid> IRON_BERRY_JUICE = createTag("iron_berry_juice");
        public static final TagKey<Fluid> IRON_WINE = createTag("iron_wine");
        public static final TagKey<Fluid> MEAD = createTag("mead");
        public static final TagKey<Fluid> OLIVE_OIL = createTag("olive_oil");
        public static final TagKey<Fluid> RUM = createTag("rum");
        public static final TagKey<Fluid> SUGAR_CANE_JUICE = createTag("sugar_cane_juice");
        public static final TagKey<Fluid> SWEET_BERRY_JUICE = createTag("sweet_berry_juice");
        public static final TagKey<Fluid> SWEET_BERRY_WINE = createTag("sweet_berry_wine");
        public static final TagKey<Fluid> VANTA_OIL = createTag("vanta_oil");
        public static final TagKey<Fluid> WINE = createTag("wine");

        private static TagKey<Fluid> createTag(String name) {
            return TagKey.of(RegistryKeys.FLUID, Identifier.of(Resprouted.MOD_ID, name));
        }
    }
}

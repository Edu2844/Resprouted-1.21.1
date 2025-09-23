package net.edu.resprouted.util;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.HashMap;
import java.util.Map;

public class BottleInteractions {
    public static final Map<Block, FluidBottleData> FLUID_BOTTLE_MAP = new HashMap<>();

    public static void registerFluidInteractions() {
        FLUID_BOTTLE_MAP.put(ModBlocks.HONEY_FLUID_BLOCK, new FluidBottleData(Items.HONEY_BOTTLE));

        FLUID_BOTTLE_MAP.put(ModBlocks.APPLE_JUICE_FLUID_BLOCK, new FluidBottleData(ModItems.APPLE_JUICE_BOTTLE));

        FLUID_BOTTLE_MAP.put(ModBlocks.GOLDEN_APPLE_JUICE_FLUID_BLOCK, new FluidBottleData(ModItems.GOLDEN_APPLE_JUICE_BOTTLE));

        FLUID_BOTTLE_MAP.put(ModBlocks.GRAPE_JUICE_FLUID_BLOCK, new FluidBottleData(ModItems.GRAPE_JUICE_BOTTLE));

        FLUID_BOTTLE_MAP.put(ModBlocks.SWEET_BERRY_JUICE_FLUID_BLOCK, new FluidBottleData(ModItems.SWEET_BERRY_JUICE_BOTTLE));

        FLUID_BOTTLE_MAP.put(ModBlocks.OLIVE_OIL_FLUID_BLOCK, new FluidBottleData(ModItems.OLIVE_OIL_BOTTLE));

        FLUID_BOTTLE_MAP.put(ModBlocks.VANTA_OIL_FLUID_BLOCK, new FluidBottleData(ModItems.VANTA_OIL_BOTTLE));

        FLUID_BOTTLE_MAP.put(ModBlocks.GLOW_BERRY_JUICE_FLUID_BLOCK, new FluidBottleData(ModItems.GLOW_BERRY_JUICE_BOTTLE));

        FLUID_BOTTLE_MAP.put(ModBlocks.IRON_BERRY_JUICE_FLUID_BLOCK, new FluidBottleData(ModItems.IRON_BERRY_JUICE_BOTTLE));

        FLUID_BOTTLE_MAP.put(ModBlocks.SUGAR_CANE_JUICE_FLUID_BLOCK, new FluidBottleData(ModItems.SUGAR_CANE_JUICE_BOTTLE));

        FLUID_BOTTLE_MAP.put(ModBlocks.ALE_WORT_FLUID_BLOCK, new FluidBottleData(ModItems.ALE_WORT_BOTTLE));
    }
    public static FluidBottleData getFluidData(Block fluidBlock) {
        return FLUID_BOTTLE_MAP.get(fluidBlock);
    }
    public record FluidBottleData(Item bottleItem) {}
}
//Pero la quería tanto...
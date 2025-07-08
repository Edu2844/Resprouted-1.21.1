package net.edu.resprouted.world.tree;


import net.edu.resprouted.Resprouted;
import net.edu.resprouted.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator IRONWOOD = new SaplingGenerator(Resprouted.MOD_ID + ":ironwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.IRONWOOD_KEY), Optional.empty());
    public static final SaplingGenerator OLIVE = new SaplingGenerator(Resprouted.MOD_ID + ":olive",
            Optional.empty(), Optional.of(ModConfiguredFeatures.OLIVE_KEY), Optional.empty());
    public static final SaplingGenerator APPLE = new SaplingGenerator(Resprouted.MOD_ID + ":apple",
            Optional.empty(), Optional.of(ModConfiguredFeatures.APPLE_KEY), Optional.empty());
}

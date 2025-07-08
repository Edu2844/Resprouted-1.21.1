package net.edu.resprouted.fluid;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.fluid.custom.*;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFluids {
    public static final FlowableFluid HONEY_STILL = register("honey_still", new HoneyFluid.Still());
    public static final FlowableFluid HONEY_FLOWING = register("honey_flowing", new HoneyFluid.Flowing());

    public static final FlowableFluid APPLE_JUICE_STILL = register("apple_juice_still", new AppleJuice.Still());
    public static final FlowableFluid APPLE_JUICE_FLOWING = register("apple_juice_flowing", new AppleJuice.Flowing());

    public static final FlowableFluid GOLDEN_APPLE_JUICE_STILL = register("golden_apple_juice_still", new GoldenAppleJuice.Still());
    public static final FlowableFluid GOLDEN_APPLE_JUICE_FLOWING = register("golden_apple_juice_flowing", new GoldenAppleJuice.Flowing());

    public static final FlowableFluid GRAPE_JUICE_STILL = register("grape_juice_still", new GrapeJuice.Still());
    public static final FlowableFluid GRAPE_JUICE_FLOWING = register("grape_juice_flowing", new GrapeJuice.Flowing());

    public static final FlowableFluid SWEET_BERRY_JUICE_STILL = register("sweet_berry_juice_still", new SweetBerryJuice.Still());
    public static final FlowableFluid SWEET_BERRY_JUICE_FLOWING = register("sweet_berry_juice_flowing", new SweetBerryJuice.Flowing());

    public static final FlowableFluid OLIVE_OIL_STILL = register("olive_oil_still", new OliveOil.Still());
    public static final FlowableFluid OLIVE_OIL_FLOWING = register("olive_oil_flowing", new OliveOil.Flowing());

    public static final FlowableFluid GLOW_BERRY_JUICE_STILL = register("glow_berry_juice_still", new GlowBerryJuice.Still());
    public static final FlowableFluid GLOW_BERRY_JUICE_FLOWING = register("glow_berry_juice_flowing", new GlowBerryJuice.Flowing());

    public static final FlowableFluid IRON_BERRY_JUICE_STILL = register("iron_berry_juice_still", new IronBerryJuice.Still());
    public static final FlowableFluid IRON_BERRY_JUICE_FLOWING = register("iron_berry_juice_flowing", new IronBerryJuice.Flowing());

    private static FlowableFluid register(String name, FlowableFluid flowableFluid) {
        return Registry.register(Registries.FLUID, Identifier.of(Resprouted.MOD_ID, name), flowableFluid);
    }
    public static void registerModFluids(){
        Resprouted.LOGGER.info("Registering Mod Fluids for " + Resprouted.MOD_ID);
    }
}

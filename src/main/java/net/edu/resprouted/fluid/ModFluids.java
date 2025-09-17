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

    public static final FlowableFluid VANTA_OIL_STILL = register("vanta_oil_still", new VantaOil.Still());
    public static final FlowableFluid VANTA_OIL_FLOWING = register("vanta_oil_flowing", new VantaOil.Flowing());

    public static final FlowableFluid GLOW_BERRY_JUICE_STILL = register("glow_berry_juice_still", new GlowBerryJuice.Still());
    public static final FlowableFluid GLOW_BERRY_JUICE_FLOWING = register("glow_berry_juice_flowing", new GlowBerryJuice.Flowing());

    public static final FlowableFluid IRON_BERRY_JUICE_STILL = register("iron_berry_juice_still", new IronBerryJuice.Still());
    public static final FlowableFluid IRON_BERRY_JUICE_FLOWING = register("iron_berry_juice_flowing", new IronBerryJuice.Flowing());

    public static final FlowableFluid ALE_WORT_STILL = register("ale_wort_still", new AleWort.Still());
    public static final FlowableFluid ALE_WORT_FLOWING = register("ale_wort_flowing", new AleWort.Flowing());

    public static final FlowableFluid ALE_STILL = register("ale_still", new Ale.Still());
    public static final FlowableFluid ALE_FLOWING = register("ale_flowing", new Ale.Flowing());

    public static final FlowableFluid IRON_WINE_STILL = register("iron_wine_still", new IronWine.Still());
    public static final FlowableFluid IRON_WINE_FLOWING = register("iron_wine_flowing", new IronWine.Flowing());

    public static final FlowableFluid CIDER_STILL = register("cider_still", new Cider.Still());
    public static final FlowableFluid CIDER_FLOWING = register("cider_flowing", new Cider.Flowing());

    public static final FlowableFluid MEAD_STILL = register("mead_still", new Mead.Still());
    public static final FlowableFluid MEAD_FLOWING = register("mead_flowing", new Mead.Flowing());

    public static final FlowableFluid WINE_STILL = register("wine_still", new Wine.Still());
    public static final FlowableFluid WINE_FLOWING = register("wine_flowing", new Wine.Flowing());

    public static final FlowableFluid SWEET_BERRY_WINE_STILL = register("sweet_berry_wine_still", new SweetBerryWine.Still());
    public static final FlowableFluid SWEET_BERRY_WINE_FLOWING = register("sweet_berry_wine_flowing", new SweetBerryWine.Flowing());

    public static final FlowableFluid AMBROSIA_STILL = register("ambrosia_still", new Ambrosia.Still());
    public static final FlowableFluid AMBROSIA_FLOWING = register("ambrosia_flowing", new Ambrosia.Flowing());

    private static FlowableFluid register(String name, FlowableFluid flowableFluid) {
        return Registry.register(Registries.FLUID, Identifier.of(Resprouted.MOD_ID, name), flowableFluid);
    }
    public static void registerModFluids(){
        Resprouted.LOGGER.info("Registering Fluids for " + Resprouted.MOD_ID);
    }
}

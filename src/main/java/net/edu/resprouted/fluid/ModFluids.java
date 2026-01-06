package net.edu.resprouted.fluid;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.fluid.custom.*;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFluids {
    public static final FlowableFluid HONEY = register("honey", new HoneyFluid.Still());
    public static final FlowableFluid HONEY_FLOWING = register("honey_flowing", new HoneyFluid.Flowing());

    public static final FlowableFluid APPLE_JUICE = register("apple_juice", new AppleJuice.Still());
    public static final FlowableFluid APPLE_JUICE_FLOWING = register("apple_juice_flowing", new AppleJuice.Flowing());

    public static final FlowableFluid GOLDEN_APPLE_JUICE = register("golden_apple_juice", new GoldenAppleJuice.Still());
    public static final FlowableFluid GOLDEN_APPLE_JUICE_FLOWING = register("golden_apple_juice_flowing", new GoldenAppleJuice.Flowing());

    public static final FlowableFluid GRAPE_JUICE = register("grape_juice", new GrapeJuice.Still());
    public static final FlowableFluid GRAPE_JUICE_FLOWING = register("grape_juice_flowing", new GrapeJuice.Flowing());

    public static final FlowableFluid SWEET_BERRY_JUICE = register("sweet_berry_juice", new SweetBerryJuice.Still());
    public static final FlowableFluid SWEET_BERRY_JUICE_FLOWING = register("sweet_berry_juice_flowing", new SweetBerryJuice.Flowing());

    public static final FlowableFluid OLIVE_OIL = register("olive_oil", new OliveOil.Still());
    public static final FlowableFluid OLIVE_OIL_FLOWING = register("olive_oil_flowing", new OliveOil.Flowing());

    public static final FlowableFluid VANTA_OIL = register("vanta_oil", new VantaOil.Still());
    public static final FlowableFluid VANTA_OIL_FLOWING = register("vanta_oil_flowing", new VantaOil.Flowing());

    public static final FlowableFluid GLOW_BERRY_JUICE = register("glow_berry_juice", new GlowBerryJuice.Still());
    public static final FlowableFluid GLOW_BERRY_JUICE_FLOWING = register("glow_berry_juice_flowing", new GlowBerryJuice.Flowing());

    public static final FlowableFluid IRON_BERRY_JUICE = register("iron_berry_juice", new IronBerryJuice.Still());
    public static final FlowableFluid IRON_BERRY_JUICE_FLOWING = register("iron_berry_juice_flowing", new IronBerryJuice.Flowing());

    public static final FlowableFluid SUGAR_CANE_JUICE = register("sugar_cane_juice", new SugarCaneJuice.Still());
    public static final FlowableFluid SUGAR_CANE_JUICE_FLOWING = register("sugar_cane_juice_flowing", new SugarCaneJuice.Flowing());

    public static final FlowableFluid ALE_WORT = register("ale_wort", new AleWort.Still());
    public static final FlowableFluid ALE_WORT_FLOWING = register("ale_wort_flowing", new AleWort.Flowing());

    public static final FlowableFluid ALE = register("ale", new Ale.Still());
    public static final FlowableFluid ALE_FLOWING = register("ale_flowing", new Ale.Flowing());

    public static final FlowableFluid IRON_WINE = register("iron_wine", new IronWine.Still());
    public static final FlowableFluid IRON_WINE_FLOWING = register("iron_wine_flowing", new IronWine.Flowing());

    public static final FlowableFluid CIDER = register("cider", new Cider.Still());
    public static final FlowableFluid CIDER_FLOWING = register("cider_flowing", new Cider.Flowing());

    public static final FlowableFluid MEAD = register("mead", new Mead.Still());
    public static final FlowableFluid MEAD_FLOWING = register("mead_flowing", new Mead.Flowing());

    public static final FlowableFluid WINE = register("wine", new Wine.Still());
    public static final FlowableFluid WINE_FLOWING = register("wine_flowing", new Wine.Flowing());

    public static final FlowableFluid SWEET_BERRY_WINE = register("sweet_berry_wine", new SweetBerryWine.Still());
    public static final FlowableFluid SWEET_BERRY_WINE_FLOWING = register("sweet_berry_wine_flowing", new SweetBerryWine.Flowing());

    public static final FlowableFluid GLOW_BERRY_WINE = register("glow_berry_wine", new GlowBerryWine.Still());
    public static final FlowableFluid GLOW_BERRY_WINE_FLOWING = register("glow_berry_wine_flowing", new GlowBerryWine.Flowing());

    public static final FlowableFluid AMBROSIA = register("ambrosia", new Ambrosia.Still());
    public static final FlowableFluid AMBROSIA_FLOWING = register("ambrosia_flowing", new Ambrosia.Flowing());

    public static final FlowableFluid RUM = register("rum", new Rum.Still());
    public static final FlowableFluid RUM_FLOWING = register("rum_flowing", new Rum.Flowing());

    public static final FlowableFluid POTION = register("potion", new PotionFluid.Still());
    public static final FlowableFluid POTION_FLOWING = register("potion_flowing", new PotionFluid.Flowing());

    private static FlowableFluid register(String name, FlowableFluid flowableFluid) {
        return Registry.register(Registries.FLUID, Identifier.of(Resprouted.MOD_ID, name), flowableFluid);
    }

    public static void registerModFluids(){
        Resprouted.LOGGER.info("Registering Fluids for " + Resprouted.MOD_ID);
    }
}

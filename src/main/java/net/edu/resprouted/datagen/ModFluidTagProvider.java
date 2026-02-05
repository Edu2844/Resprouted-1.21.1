package net.edu.resprouted.datagen;

import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.util.misc.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModFluidTagProvider extends FabricTagProvider.FluidTagProvider {
    public ModFluidTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Fluids.ALE)
                .add(ModFluids.ALE, ModFluids.ALE_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.ALE_WORT)
                .add(ModFluids.ALE_WORT, ModFluids.ALE_WORT_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.AMBROSIA)
                .add(ModFluids.AMBROSIA, ModFluids.AMBROSIA_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.CIDER)
                .add(ModFluids.CIDER,ModFluids.CIDER_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.HONEY)
                .add(ModFluids.HONEY, ModFluids.HONEY_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.IRON_BERRY_JUICE)
                .add(ModFluids.IRON_BERRY_JUICE, ModFluids.IRON_BERRY_JUICE_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.IRON_WINE)
                .add(ModFluids.IRON_WINE, ModFluids.IRON_WINE_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.MEAD)
                .add(ModFluids.MEAD, ModFluids.MEAD_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.RUM)
                .add(ModFluids.RUM, ModFluids.RUM_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.SWEET_BERRY_JUICE)
                .add(ModFluids.SWEET_BERRY_JUICE, ModFluids.SWEET_BERRY_JUICE_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.SWEET_BERRY_WINE)
                .add(ModFluids.SWEET_BERRY_WINE, ModFluids.SWEET_BERRY_WINE_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.WINE)
                .add(ModFluids.WINE, ModFluids.WINE_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.GLOW_BERRY_JUICE)
                .add(ModFluids.GLOW_BERRY_JUICE, ModFluids.GLOW_BERRY_JUICE_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.GLOW_BERRY_WINE)
                .add(ModFluids.GLOW_BERRY_WINE, ModFluids.GLOW_BERRY_WINE_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.GRAPE_JUICE)
                .add(ModFluids.GRAPE_JUICE, ModFluids.GRAPE_JUICE_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.SUGAR_CANE_JUICE)
                .add(ModFluids.SUGAR_CANE_JUICE, ModFluids.SUGAR_CANE_JUICE_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.APPLE_JUICE)
                .add(ModFluids.APPLE_JUICE, ModFluids.APPLE_JUICE_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.GOLDEN_APPLE_JUICE)
                .add(ModFluids.GOLDEN_APPLE_JUICE, ModFluids.GOLDEN_APPLE_JUICE_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.OLIVE_OIL)
                .add(ModFluids.OLIVE_OIL, ModFluids.OLIVE_OIL_FLOWING);

        getOrCreateTagBuilder(ModTags.Fluids.VANTA_OIL)
                .add(ModFluids.VANTA_OIL, ModFluids.VANTA_OIL_FLOWING);
    }
}

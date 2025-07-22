package net.edu.resprouted.datagen;

import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.util.ModTags;
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
        getOrCreateTagBuilder(ModTags.Fluids.HONEY)
                .add(ModFluids.HONEY_STILL)
                .add(ModFluids.HONEY_FLOWING)
        ;
        getOrCreateTagBuilder(ModTags.Fluids.IRON_BERRY_JUICE)
                .add(ModFluids.IRON_BERRY_JUICE_STILL)
                .add(ModFluids.IRON_BERRY_JUICE_FLOWING)
        ;
        getOrCreateTagBuilder(ModTags.Fluids.SWEET_BERRY_JUICE)
                .add(ModFluids.SWEET_BERRY_JUICE_STILL)
                .add(ModFluids.SWEET_BERRY_JUICE_FLOWING)
        ;
        getOrCreateTagBuilder(ModTags.Fluids.GLOW_BERRY_JUICE)
                .add(ModFluids.GLOW_BERRY_JUICE_STILL)
                .add(ModFluids.GLOW_BERRY_JUICE_FLOWING)
        ;
        getOrCreateTagBuilder(ModTags.Fluids.GRAPE_JUICE)
                .add(ModFluids.GRAPE_JUICE_STILL)
                .add(ModFluids.GRAPE_JUICE_FLOWING)
        ;
        getOrCreateTagBuilder(ModTags.Fluids.APPLE_JUICE)
                .add(ModFluids.APPLE_JUICE_STILL)
                .add(ModFluids.APPLE_JUICE_FLOWING)
        ;
        getOrCreateTagBuilder(ModTags.Fluids.GOLDEN_APPLE_JUICE)
                .add(ModFluids.GOLDEN_APPLE_JUICE_STILL)
                .add(ModFluids.GOLDEN_APPLE_JUICE_FLOWING)
        ;
        getOrCreateTagBuilder(ModTags.Fluids.OLIVE_OIL)
                .add(ModFluids.OLIVE_OIL_STILL)
                .add(ModFluids.OLIVE_OIL_FLOWING)
        ;
    }
}

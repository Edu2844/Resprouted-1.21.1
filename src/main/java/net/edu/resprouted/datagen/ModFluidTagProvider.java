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
    }
}

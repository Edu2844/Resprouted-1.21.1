package net.edu.resprouted.fluid;

import net.edu.resprouted.Resprouted;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributeHandler;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.potion.Potion;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ModFluidAttributes {
    public static void registerFluidAttributes() {
        register();
        Resprouted.LOGGER.info("Registering Fluid Attributes for " + Resprouted.MOD_ID);
    }

    public static void register() {
        // HONEY
        FluidVariantAttributes.register(ModFluids.HONEY, new FluidVariantAttributeHandler() {
            @Override
            public int getViscosity(FluidVariant variant, @Nullable World world) {
                return 10000;
            }
        });
        FluidVariantAttributes.register(ModFluids.HONEY_FLOWING, new FluidVariantAttributeHandler() {
            @Override
            public int getViscosity(FluidVariant variant, @Nullable World world) {
                return 10000;
            }
        });

        // OLIVE OIL
        FluidVariantAttributes.register(ModFluids.OLIVE_OIL, new FluidVariantAttributeHandler() {
            @Override
            public int getViscosity(FluidVariant variant, @Nullable World world) {
                return 3000;
            }

        });
        FluidVariantAttributes.register(ModFluids.OLIVE_OIL_FLOWING, new FluidVariantAttributeHandler() {
            @Override
            public int getViscosity(FluidVariant variant, @Nullable World world) {
                return 3000;
            }

        });

        // VANTA OIL
        FluidVariantAttributes.register(ModFluids.VANTA_OIL, new FluidVariantAttributeHandler() {
            @Override
            public int getViscosity(FluidVariant variant, @Nullable World world) {
                return 5000;
            }

            @Override
            public int getLuminance(FluidVariant variant) {
                return 0;
            }
        });
        FluidVariantAttributes.register(ModFluids.VANTA_OIL_FLOWING, new FluidVariantAttributeHandler() {
            @Override
            public int getViscosity(FluidVariant variant, @Nullable World world) {
                return 5000;
            }

            @Override
            public int getLuminance(FluidVariant variant) {
                return 0;
            }
        });

        // GLOW BERRY JUICE
        FluidVariantAttributes.register(ModFluids.GLOW_BERRY_JUICE, new FluidVariantAttributeHandler() {
            @Override
            public int getLuminance(FluidVariant variant) {
                return 8;
            }
        });
        FluidVariantAttributes.register(ModFluids.GLOW_BERRY_JUICE_FLOWING, new FluidVariantAttributeHandler() {
            @Override
            public int getLuminance(FluidVariant variant) {
                return 8;
            }
        });


        // POTION
        FluidVariantAttributes.register(ModFluids.POTION, new FluidVariantAttributeHandler() {
            @Override
            public Text getName(FluidVariant variant) {
                if (variant == null || variant.isBlank()) {
                    return Text.translatable("block.resprouted.potion");
                }

                ComponentMap components = variant.getComponentMap();
                PotionContentsComponent potionContents = components.get(DataComponentTypes.POTION_CONTENTS);

                if (potionContents != null && potionContents.potion().isPresent()) {
                    String translationKey = Potion.finishTranslationKey(
                            potionContents.potion(),
                            "item.minecraft.potion.effect."
                    );
                    return Text.translatable(translationKey);
                }

                return Text.translatable("block.resprouted.potion");
            }
        });
        FluidVariantAttributes.register(ModFluids.POTION_FLOWING, new FluidVariantAttributeHandler() {
            @Override
            public Text getName(FluidVariant variant) {
                if (variant == null || variant.isBlank()) {
                    return Text.translatable("block.resprouted.potion");
                }

                ComponentMap components = variant.getComponentMap();
                PotionContentsComponent potionContents = components.get(DataComponentTypes.POTION_CONTENTS);

                if (potionContents != null && potionContents.potion().isPresent()) {
                    String translationKey = Potion.finishTranslationKey(
                            potionContents.potion(),
                            "item.minecraft.potion.effect."
                    );
                    return Text.translatable(translationKey);
                }

                return Text.translatable("block.resprouted.potion");
            }
        });
    }
}

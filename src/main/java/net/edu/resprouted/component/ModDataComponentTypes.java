package net.edu.resprouted.component;

import com.mojang.serialization.Codec;
import net.edu.resprouted.Resprouted;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {

    public static final ComponentType<Boolean> OILED =
            register("oiled", builder -> builder.codec(Codec.BOOL));

    public static final ComponentType<StatusEffectInstance> VANTA_OIL_EFFECT =
            register("vanta_oil_effect", builder -> builder.codec(StatusEffectInstance.CODEC));

    public static final ComponentType<Float> FLUID_QUALITY =
            register("fluid_quality", builder -> builder.codec(Codec.FLOAT));



    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE,
                Identifier.of(Resprouted.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }
    public static void registerDataComponentTypes(){
        Resprouted.LOGGER.info("Registering Data Component Types for " + Resprouted.MOD_ID);
    }
}

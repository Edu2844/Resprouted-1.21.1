package net.edu.resprouted.fluid.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public record FluidItemMapping(FluidVariant fluid, ItemStack fullItem, ItemStack emptyItem, long amount, Set<Identifier> preservedComponents, DirectionMode direction, Optional<Identifier> customSound) {

    public enum DirectionMode implements StringIdentifiable {
        INSERT("insert"),
        EXTRACT("extract");

        private final String name;

        DirectionMode(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return name;
        }

        public boolean allowsInsert() {
            return this == INSERT;
        }

        public boolean allowsExtract() {
            return this == EXTRACT;
        }

        public static final Codec<DirectionMode> CODEC = StringIdentifiable.createCodec(DirectionMode::values);
    }

    public FluidItemMapping {
        if (preservedComponents == null) {
            preservedComponents = Set.of();
        }
        if (customSound.isEmpty()) {
            customSound = Optional.empty();
        }
    }

    public static final MapCodec<FluidItemMapping> MAP_CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            FluidVariant.CODEC.fieldOf("fluid_variant").forGetter(FluidItemMapping::fluid),
            ItemStack.CODEC.fieldOf("item").forGetter(FluidItemMapping::fullItem),
            ItemStack.CODEC.fieldOf("empty_item").forGetter(FluidItemMapping::emptyItem),
            Codec.LONG.fieldOf("amount").xmap(mb -> mb * 81, droplets -> droplets / 81).forGetter(FluidItemMapping::amount),
            Identifier.CODEC.listOf().optionalFieldOf("preserved_components", List.of())
                    .xmap(Set::copyOf, List::copyOf)
                    .forGetter(FluidItemMapping::preservedComponents),
            DirectionMode.CODEC.fieldOf("direction").forGetter(FluidItemMapping::direction),
            Identifier.CODEC.optionalFieldOf("sound").forGetter(FluidItemMapping::customSound)
    ).apply(inst, FluidItemMapping::new));

    public static final Codec<FluidItemMapping> CODEC = MAP_CODEC.codec();
}
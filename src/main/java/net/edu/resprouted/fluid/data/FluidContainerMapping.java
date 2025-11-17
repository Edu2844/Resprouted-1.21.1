package net.edu.resprouted.fluid.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;

import java.util.List;
import java.util.Set;

public record FluidContainerMapping(FluidVariant fluid, ItemStack fullItem, ItemStack emptyItem, long amount, DirectionMode direction, Set<Identifier> preservedComponents) {
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
    public FluidContainerMapping {
        if (preservedComponents == null) {
            preservedComponents = Set.of();
        }
    }
    public static final MapCodec<FluidContainerMapping> MAP_CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            FluidVariant.CODEC.fieldOf("fluid_variant").forGetter(FluidContainerMapping::fluid),
            ItemStack.CODEC.fieldOf("item").forGetter(FluidContainerMapping::fullItem),
            ItemStack.CODEC.fieldOf("empty_item").forGetter(FluidContainerMapping::emptyItem),
            Codec.LONG.fieldOf("amount").xmap(mb -> mb * 81, droplets -> droplets / 81).forGetter(FluidContainerMapping::amount),
            DirectionMode.CODEC.fieldOf("direction").forGetter(FluidContainerMapping::direction),
            Identifier.CODEC.listOf().optionalFieldOf("preserved_components", List.of())
                    .xmap(Set::copyOf, List::copyOf)
                    .forGetter(FluidContainerMapping::preservedComponents)
    ).apply(inst, FluidContainerMapping::new));

    public static final Codec<FluidContainerMapping> CODEC = MAP_CODEC.codec();
}

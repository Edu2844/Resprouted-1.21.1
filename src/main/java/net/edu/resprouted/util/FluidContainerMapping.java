package net.edu.resprouted.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringIdentifiable;

public record FluidContainerMapping(FluidVariant fluid, ItemStack fullItem, ItemStack emptyItem, long amount, DirectionMode direction) {
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
    public static final MapCodec<FluidContainerMapping> MAP_CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            FluidVariant.CODEC.fieldOf("fluid_variant").forGetter(FluidContainerMapping::fluid),
            ItemStack.CODEC.fieldOf("item").forGetter(FluidContainerMapping::fullItem),
            ItemStack.CODEC.fieldOf("empty_item").forGetter(FluidContainerMapping::emptyItem),
            Codec.LONG.fieldOf("amount").forGetter(FluidContainerMapping::amount),
            DirectionMode.CODEC.fieldOf("direction").forGetter(FluidContainerMapping::direction)
    ).apply(inst, FluidContainerMapping::new));
    public static final Codec<FluidContainerMapping> CODEC = MAP_CODEC.codec();
}

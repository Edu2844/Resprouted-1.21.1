package net.edu.resprouted.recipe.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.edu.resprouted.recipe.Input.EvaporatingBasinRecipeInput;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.util.FluidUtils;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

public record EvaporatingBasinRecipe(FluidVariant fluidInput, long fluidCost, ItemStack output) implements Recipe<EvaporatingBasinRecipeInput> {

    @Override
    public boolean matches(EvaporatingBasinRecipeInput in, World world) {
        boolean sameFluid = fluidInput.getFluid() == in.fluid().getFluid();
        return !world.isClient && sameFluid;
    }

    @Override public ItemStack craft(EvaporatingBasinRecipeInput in, RegistryWrapper.WrapperLookup l) {
        return output.copy();
    }

    @Override public boolean fits(int w, int h) {
        return true;
    }

    @Override public ItemStack getResult(RegistryWrapper.WrapperLookup l) {
        return output;
    }

    @Override public RecipeSerializer<?> getSerializer() {
        return ModRecipes.EV_BASIN_SERIALIZER;
    }

    @Override public RecipeType<?> getType() {
        return ModRecipes.EV_BASIN_TYPE;
    }

    public static class Serializer implements RecipeSerializer<EvaporatingBasinRecipe> {

        public static final MapCodec<EvaporatingBasinRecipe> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
                FluidVariant.CODEC.fieldOf("fluid_variant").forGetter(EvaporatingBasinRecipe::fluidInput),
                Codec.LONG.fieldOf("amount").xmap(FluidUtils::convertMbToDroplets, droplets -> droplets).forGetter(EvaporatingBasinRecipe::fluidCost),
                ItemStack.CODEC.fieldOf("output_item").forGetter(EvaporatingBasinRecipe::output)).apply(i, EvaporatingBasinRecipe::new));

        public static final PacketCodec<RegistryByteBuf, EvaporatingBasinRecipe> STREAM = PacketCodec.ofStatic(
                (buf, r) -> {FluidVariant.PACKET_CODEC.encode(buf, r.fluidInput());
                    buf.writeVarLong(r.fluidCost());
                    ItemStack.PACKET_CODEC.encode(buf, r.output());
                    },
                buf -> new EvaporatingBasinRecipe(FluidVariant.PACKET_CODEC.decode(buf),
                        buf.readVarLong(),
                        ItemStack.PACKET_CODEC.decode(buf))
        );

        @Override public MapCodec<EvaporatingBasinRecipe> codec() {
            return CODEC;
        }

        @Override public PacketCodec<RegistryByteBuf, EvaporatingBasinRecipe> packetCodec() {
            return STREAM;
        }
    }
}

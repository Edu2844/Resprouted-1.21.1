package net.edu.resprouted.recipe.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.edu.resprouted.recipe.Input.DryingBasinRecipeInput;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.util.fluid.FluidUtils;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

public record DryingBasinRecipe(FluidVariant fluidInput, long fluidCost, ItemStack output, int craftTime) implements Recipe<DryingBasinRecipeInput> {
    @Override
    public boolean matches(DryingBasinRecipeInput in, World world) {
        if (world.isClient) return false;

        boolean sameFluid = fluidInput.getFluid() == in.fluid().getFluid();
        boolean enoughFluid = in.amount() >= fluidCost;

        // Check if output slot is empty or can stack with the result
        ItemStack storedStack = in.itemStack();
        return sameFluid && enoughFluid && (storedStack.isEmpty() || (ItemStack.areItemsAndComponentsEqual(storedStack, output)
                && storedStack.getCount() + output.getCount() <= storedStack.getMaxCount()));
    }

    @Override
    public ItemStack craft(DryingBasinRecipeInput in, RegistryWrapper.WrapperLookup l) {
        return output.copy();
    }

    @Override
    public boolean fits(int w, int h) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup l) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.DRYING_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.DRYING_TYPE;
    }

    public static class Serializer implements RecipeSerializer<DryingBasinRecipe> {

        public static final MapCodec<DryingBasinRecipe> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
                FluidVariant.CODEC.fieldOf("fluid_variant").forGetter(DryingBasinRecipe::fluidInput),
                Codec.LONG.fieldOf("amount").xmap(FluidUtils::convertMbToDroplets, droplets -> droplets).forGetter(DryingBasinRecipe::fluidCost),
                ItemStack.CODEC.fieldOf("output_item").forGetter(DryingBasinRecipe::output),
                Codec.INT.fieldOf("evaporatingtime").forGetter(DryingBasinRecipe::craftTime)
        ).apply(i, DryingBasinRecipe::new));

        public static final PacketCodec<RegistryByteBuf, DryingBasinRecipe> STREAM = PacketCodec.ofStatic(
                (buf, r) -> {
                    FluidVariant.PACKET_CODEC.encode(buf, r.fluidInput());
                    buf.writeVarLong(r.fluidCost());
                    ItemStack.PACKET_CODEC.encode(buf, r.output());
                    buf.writeVarInt(r.craftTime());
                },
                buf -> new DryingBasinRecipe(
                        FluidVariant.PACKET_CODEC.decode(buf),
                        buf.readVarLong(),
                        ItemStack.PACKET_CODEC.decode(buf),
                        buf.readVarInt()
                )
        );

        @Override
        public MapCodec<DryingBasinRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, DryingBasinRecipe> packetCodec() {
            return STREAM;
        }
    }
}
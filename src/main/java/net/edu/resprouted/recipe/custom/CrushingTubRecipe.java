package net.edu.resprouted.recipe.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.edu.resprouted.recipe.ModRecipes;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public record CrushingTubRecipe(Ingredient inputItem, @Nullable ItemStack outputItem, FluidVariant fluidOutput, long fluidAmount) implements Recipe<CrushingTubRecipeInput> {
    @Override
    public boolean matches(CrushingTubRecipeInput input, World world) {
        if (world.isClient()) return false;
        return inputItem.test(input.getStackInSlot(0));
    }
    @Override
    public ItemStack craft(CrushingTubRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return outputItem == null ? ItemStack.EMPTY : outputItem.copy();
    }
    @Override
    public boolean fits(int width, int height) {
        return true;
    }
    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return outputItem == null ? ItemStack.EMPTY : outputItem;
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CRUSHING_TUB_SERIALIZER;
    }
    @Override
    public RecipeType<?> getType() {
        return ModRecipes.CRUSHING_TUB_TYPE;
    }

    public static class CrushingTubRecipeSerializer implements RecipeSerializer<CrushingTubRecipe> {

        public static final MapCodec<CrushingTubRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("input_item").forGetter(CrushingTubRecipe::inputItem),

                ItemStack.CODEC.optionalFieldOf("output_item", ItemStack.EMPTY).forGetter(r -> r.outputItem == null ? ItemStack.EMPTY : r.outputItem),
                FluidVariant.CODEC.optionalFieldOf("fluid_variant", FluidVariant.blank()).forGetter(CrushingTubRecipe::fluidOutput),
                Codec.LONG.optionalFieldOf("amount", 0L).forGetter(CrushingTubRecipe::fluidAmount)
        ).apply(inst, (inputItem, outputItem, fluidOutput, amount) -> {

            return new CrushingTubRecipe(inputItem, outputItem == ItemStack.EMPTY ? null : outputItem, fluidOutput, amount);
        }));
        public static final PacketCodec<RegistryByteBuf, CrushingTubRecipe> STREAM_CODEC = PacketCodec.ofStatic(
                (buf, recipe) -> {
                    Ingredient.PACKET_CODEC.encode(buf, recipe.inputItem());

                    buf.writeBoolean(recipe.outputItem() != null && !recipe.outputItem().isEmpty());
                    if (recipe.outputItem() != null && !recipe.outputItem().isEmpty()) {
                        ItemStack.PACKET_CODEC.encode(buf, recipe.outputItem());
                    }
                    FluidVariant.PACKET_CODEC.encode(buf, recipe.fluidOutput());
                    buf.writeVarLong(recipe.fluidAmount());
                },
                buf -> {
                    Ingredient input = Ingredient.PACKET_CODEC.decode(buf);
                    boolean hasOutputItem = buf.readBoolean();
                    ItemStack output = hasOutputItem ? ItemStack.PACKET_CODEC.decode(buf) : null;
                    FluidVariant fluid = FluidVariant.PACKET_CODEC.decode(buf);
                    long amount = buf.readVarLong();
                    return new CrushingTubRecipe(input, output, fluid, amount);
                }
        );
        @Override
        public MapCodec<CrushingTubRecipe> codec() {
            return CODEC;
        }
        @Override
        public PacketCodec<RegistryByteBuf, CrushingTubRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}

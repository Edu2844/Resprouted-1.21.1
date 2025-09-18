package net.edu.resprouted.recipe.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.edu.resprouted.block.custom.alchemy.BasicCondenserBlock;
import net.edu.resprouted.block.entity.custom.BasicCondenserBE;
import net.edu.resprouted.recipe.Input.CondenserRecipeInput;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.util.ElixirUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

import java.util.List;

public record CondenserRecipe(List<Ingredient> ingredients, RegistryEntry<StatusEffect> effect, int duration, int amplifier) implements Recipe<CondenserRecipeInput> {

    @Override
    public boolean matches(CondenserRecipeInput input, World world) {
        if (world.isClient()) return false;
        if (ingredients.size() != 2) return false;

        boolean matchesIngredients = (ingredients.get(0).test(input.inputA()) && ingredients.get(1).test(input.inputB())) ||
                (ingredients.get(0).test(input.inputB()) && ingredients.get(1).test(input.inputA()));

        boolean hasFuelOrBurning = false;
        boolean hasRetorts = false;
        boolean hasFluid = false;
        boolean hasBottle = input.bottle().isOf(Items.GLASS_BOTTLE);

        if (world.getBlockEntity(input.pos()) instanceof BasicCondenserBE be) {
            hasFuelOrBurning = be.isBurning() || !input.fuel().isEmpty();
            hasFluid = be.hasFluid();

            BlockState blockState = world.getBlockState(input.pos());
            if (blockState.getBlock() instanceof BasicCondenserBlock condenserBlock) {
                hasRetorts = condenserBlock.hasRequiredRetorts(world, input.pos(), blockState);
            }
        }
        return matchesIngredients && hasFuelOrBurning && hasBottle && hasFluid && hasRetorts;
    }
    @Override
    public ItemStack craft(CondenserRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return ElixirUtils.createElixir(effect, duration, amplifier);
    }
    @Override
    public boolean fits(int width, int height) {
        return true;
    }
    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup lookup) {
        return ElixirUtils.createElixir(effect, duration, amplifier);
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CONDENSER_SERIALIZER;
    }
    @Override
    public RecipeType<?> getType() {
        return ModRecipes.CONDENSER_TYPE;
    }
    public static class Serializer implements RecipeSerializer<CondenserRecipe> {

        public static final MapCodec<CondenserRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients").forGetter(CondenserRecipe::ingredients),
                StatusEffect.ENTRY_CODEC.fieldOf("effect").forGetter(CondenserRecipe::effect),
                Codec.INT.optionalFieldOf("duration",0).forGetter(CondenserRecipe::duration),
                Codec.INT.optionalFieldOf("amplifier",0).forGetter(CondenserRecipe::amplifier)
        ).apply(inst, CondenserRecipe::new));

        public static final PacketCodec<RegistryByteBuf, CondenserRecipe> STREAM_CODEC = PacketCodec.tuple(
                Ingredient.PACKET_CODEC.collect(PacketCodecs.toList()), CondenserRecipe::ingredients,
                PacketCodecs.registryEntry(RegistryKeys.STATUS_EFFECT), CondenserRecipe::effect,
                PacketCodecs.VAR_INT, CondenserRecipe::duration,
                PacketCodecs.VAR_INT, CondenserRecipe::amplifier,
                CondenserRecipe::new
        );
        @Override public MapCodec<CondenserRecipe> codec() {
            return CODEC;
        }
        @Override public PacketCodec<RegistryByteBuf, CondenserRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}

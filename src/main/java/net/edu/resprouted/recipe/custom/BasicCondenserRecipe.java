package net.edu.resprouted.recipe.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.edu.resprouted.block.custom.alchemy.AdvancedCondenserBlock;
import net.edu.resprouted.block.custom.alchemy.BasicCondenserBlock;
import net.edu.resprouted.block.entity.custom.alchemy.AdvancedCondenserBlockEntity;
import net.edu.resprouted.block.entity.custom.alchemy.BasicCondenserBlockEntity;
import net.edu.resprouted.recipe.Input.BasicCondenserRecipeInput;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.util.misc.ElixirUtils;
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

import java.util.ArrayList;
import java.util.List;

public record BasicCondenserRecipe(List<Ingredient> ingredients, RegistryEntry<StatusEffect> effect, int duration, int amplifier) implements Recipe<BasicCondenserRecipeInput> {

    @Override
    public boolean matches(BasicCondenserRecipeInput input, World world) {
        if (world.isClient()) return false;
        if (ingredients.isEmpty() || ingredients.size() > 2) return false;

        List<ItemStack> inputs = new ArrayList<>();
        if (!input.inputA().isEmpty()) inputs.add(input.inputA());
        if (!input.inputB().isEmpty()) inputs.add(input.inputB());

        if (inputs.size() != ingredients.size()) return false;

        List<Ingredient> ingredientsCopy = new ArrayList<>(ingredients);
        for (ItemStack stack : inputs) {
            boolean matched = ingredientsCopy.removeIf(ing -> ing.test(stack));
            if (!matched) return false;
        }

        boolean hasFuelOrBurning = false;
        boolean hasRetorts = false;
        boolean hasFluid = false;
        boolean hasBottle = input.bottle().isOf(Items.GLASS_BOTTLE);

        if (world.getBlockEntity(input.pos()) instanceof BasicCondenserBlockEntity be) {
            hasFuelOrBurning = be.isBurning() || !input.fuel().isEmpty();
            hasFluid = be.hasFluid();

            BlockState blockState = world.getBlockState(input.pos());
            if (blockState.getBlock() instanceof BasicCondenserBlock condenserBlock) {
                hasRetorts = condenserBlock.hasRetorts(world, input.pos(), blockState);
            }
        }

        else if (world.getBlockEntity(input.pos()) instanceof AdvancedCondenserBlockEntity be) {
            hasFuelOrBurning = be.isBurning() || !input.fuel().isEmpty();
            hasFluid = be.hasFluid();

            BlockState blockState = world.getBlockState(input.pos());
            if (blockState.getBlock() instanceof AdvancedCondenserBlock condenserBlock) {
                hasRetorts = condenserBlock.hasRetorts(world, input.pos(), blockState);
            }
        }

        return hasFuelOrBurning && hasBottle && hasFluid && hasRetorts;
    }

    @Override
    public ItemStack craft(BasicCondenserRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
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

    public static class Serializer implements RecipeSerializer<BasicCondenserRecipe> {

        public static final MapCodec<BasicCondenserRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients").forGetter(BasicCondenserRecipe::ingredients),
                StatusEffect.ENTRY_CODEC.fieldOf("effect").forGetter(BasicCondenserRecipe::effect),
                Codec.INT.optionalFieldOf("duration",0).forGetter(BasicCondenserRecipe::duration),
                Codec.INT.optionalFieldOf("amplifier",0).forGetter(BasicCondenserRecipe::amplifier)
        ).apply(inst, BasicCondenserRecipe::new));

        public static final PacketCodec<RegistryByteBuf, BasicCondenserRecipe> STREAM_CODEC = PacketCodec.tuple(
                Ingredient.PACKET_CODEC.collect(PacketCodecs.toList()), BasicCondenserRecipe::ingredients,
                PacketCodecs.registryEntry(RegistryKeys.STATUS_EFFECT), BasicCondenserRecipe::effect,
                PacketCodecs.VAR_INT, BasicCondenserRecipe::duration,
                PacketCodecs.VAR_INT, BasicCondenserRecipe::amplifier,
                BasicCondenserRecipe::new
        );
        @Override public MapCodec<BasicCondenserRecipe> codec() {
            return CODEC;
        }

        @Override public PacketCodec<RegistryByteBuf, BasicCondenserRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}

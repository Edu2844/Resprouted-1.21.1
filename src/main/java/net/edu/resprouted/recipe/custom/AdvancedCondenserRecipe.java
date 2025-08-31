package net.edu.resprouted.recipe.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.edu.resprouted.block.custom.alchemy.AdvancedCondenserBlock;
import net.edu.resprouted.block.entity.custom.AdvancedCondenserBlockEntity;
import net.edu.resprouted.recipe.Input.AdvancedCondenserRecipeInput;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record AdvancedCondenserRecipe(List<Ingredient> ingredients, Optional<Ingredient> modifier, RegistryEntry<StatusEffect> effect, int duration, int amplifier) implements Recipe<AdvancedCondenserRecipeInput> {
    @Override
    public boolean matches(AdvancedCondenserRecipeInput input, World world) {
        if (world.isClient()) return false;

        if (ingredients.size() < 2 || ingredients.size() > 3) return false;

        List<ItemStack> actualInputs = new ArrayList<>();
        if (!input.inputA().isEmpty()) actualInputs.add(input.inputA());
        if (!input.inputB().isEmpty()) actualInputs.add(input.inputB());
        if (!input.inputC().isEmpty()) actualInputs.add(input.inputC());

        if (ingredients.size() != actualInputs.size()) return false;

        List<Ingredient> ingredientsCopy = new ArrayList<>(ingredients);
        for (ItemStack stack : actualInputs) {
            boolean matched = ingredientsCopy.removeIf(ing -> ing.test(stack));
            if (!matched) return false;
        }
        boolean hasFuelOrBurning = false;
        boolean hasRetorts = false;
        if (world.getBlockEntity(input.pos()) instanceof AdvancedCondenserBlockEntity be) {
            hasFuelOrBurning = be.isBurning() || !input.fuel().isEmpty();
            BlockState blockState = world.getBlockState(input.pos());
            if (blockState.getBlock() instanceof AdvancedCondenserBlock advcondenser) {
                hasRetorts = advcondenser.hasRequiredRetorts(world, input.pos(), blockState);
            }
        }
        boolean hasBottle = input.bottle().isOf(Items.GLASS_BOTTLE);

        boolean hasFluid = false;
        if (world.getBlockEntity(input.pos()) instanceof AdvancedCondenserBlockEntity be) {
            hasFluid = be.hasFluid();
        }
        if (modifier.isPresent()) {
            if (!modifier.get().test(input.modifier())) {
                return false;
            }
        } else {
            if (!input.modifier().isEmpty()) {
                return false;
            }
        }
        return hasFuelOrBurning && hasBottle && hasFluid && hasRetorts;
    }
    @Override
    public ItemStack craft(AdvancedCondenserRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return ElixirUtils.createElixir(effect, duration, amplifier);
    }
    @Override
    public boolean fits(int width, int height) {
        return true;
    }
    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return ElixirUtils.createElixir(effect, duration, amplifier);
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.ADVANCED_CONDENSER_SERIALIZER;
    }
    @Override
    public RecipeType<?> getType() {
        return ModRecipes.ADVANCED_CONDENSER_TYPE;
    }
    public static class Serializer implements RecipeSerializer<AdvancedCondenserRecipe> {

        public static final MapCodec<AdvancedCondenserRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients").forGetter(AdvancedCondenserRecipe::ingredients),
                Ingredient.DISALLOW_EMPTY_CODEC.optionalFieldOf("modifier").forGetter(AdvancedCondenserRecipe::modifier),
                StatusEffect.ENTRY_CODEC.fieldOf("effect").forGetter(AdvancedCondenserRecipe::effect),
                Codec.INT.fieldOf("duration").forGetter(AdvancedCondenserRecipe::duration),
                Codec.INT.fieldOf("amplifier").forGetter(AdvancedCondenserRecipe::amplifier)
        ).apply(inst, AdvancedCondenserRecipe::new));

        public static final PacketCodec<RegistryByteBuf, AdvancedCondenserRecipe> STREAM_CODEC = PacketCodec.tuple(
                Ingredient.PACKET_CODEC.collect(PacketCodecs.toList()), AdvancedCondenserRecipe::ingredients,
                PacketCodecs.optional(Ingredient.PACKET_CODEC), AdvancedCondenserRecipe::modifier,
                PacketCodecs.registryEntry(RegistryKeys.STATUS_EFFECT), AdvancedCondenserRecipe::effect,
                PacketCodecs.VAR_INT, AdvancedCondenserRecipe::duration,
                PacketCodecs.VAR_INT, AdvancedCondenserRecipe::amplifier,
                AdvancedCondenserRecipe::new
        );
        @Override
        public MapCodec<AdvancedCondenserRecipe> codec() {
            return CODEC;
        }
        @Override
        public PacketCodec<RegistryByteBuf, AdvancedCondenserRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}

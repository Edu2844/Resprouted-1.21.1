package net.edu.resprouted.recipe.custom;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.edu.resprouted.recipe.Input.CrushingTubRecipeInput;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.recipe.helper.RecipeOutput;
import net.edu.resprouted.util.FluidUtils;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record CrushingTubRecipe(Ingredient inputItem, List<RecipeOutput> outputItems, FluidVariant fluidOutput, long fluidAmount, @Nullable Identifier crushSoundId) implements Recipe<CrushingTubRecipeInput> {
    @Override
    public boolean matches(CrushingTubRecipeInput input, World world) {
        if (world.isClient()) return false;
        return inputItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(CrushingTubRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return outputItems.isEmpty() ? ItemStack.EMPTY : outputItems.getFirst().stack().copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return outputItems.isEmpty() ? ItemStack.EMPTY : outputItems.getFirst().stack();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CRUSHING_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.CRUSHING_TYPE;
    }

    public SoundEvent getCrushSound() {
        if (crushSoundId != null) {
            return Registries.SOUND_EVENT.get(crushSoundId);
        }
        return SoundEvents.BLOCK_SLIME_BLOCK_FALL;
    }

    public CrushingTubRecipe {
        if (outputItems.size() > 4) {
            throw new IllegalArgumentException("Cannot have more than 4 output items");
        }
    }

    public static class CrushingTubRecipeSerializer implements RecipeSerializer<CrushingTubRecipe> {

        private static final Codec<List<RecipeOutput>> OUTPUT_CODEC = Codec.either(RecipeOutput.CODEC, RecipeOutput.CODEC.listOf(1, 4)).
                xmap(either -> either.map(List::of, list -> list),
                        list -> list.size() == 1 ? Either.left(list.getFirst()) : Either.right(list));

        public static final MapCodec<CrushingTubRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("input_item").forGetter(CrushingTubRecipe::inputItem),
                OUTPUT_CODEC.optionalFieldOf("output_item", List.of()).forGetter(CrushingTubRecipe::outputItems),
                FluidVariant.CODEC.optionalFieldOf("fluid_variant", FluidVariant.blank()).forGetter(CrushingTubRecipe::fluidOutput),
                Codec.LONG.optionalFieldOf("amount", 0L).xmap(FluidUtils::convertMbToDroplets, droplets -> droplets).forGetter(CrushingTubRecipe::fluidAmount),
                Identifier.CODEC.optionalFieldOf("crush_sound").forGetter(r -> Optional.ofNullable(r.crushSoundId))
        ).apply(inst, (input, outputs, fluid, amount, soundId) ->
                new CrushingTubRecipe(input, outputs, fluid, amount, soundId.orElse(null))));

        public static final PacketCodec<RegistryByteBuf, CrushingTubRecipe> STREAM_CODEC = PacketCodec.ofStatic(
                (buf, recipe) -> {
                    Ingredient.PACKET_CODEC.encode(buf, recipe.inputItem());

                    buf.writeVarInt(recipe.outputItems().size());
                    for (RecipeOutput output : recipe.outputItems()) {
                        RecipeOutput.PACKET_CODEC.encode(buf, output);
                    }

                    FluidVariant.PACKET_CODEC.encode(buf, recipe.fluidOutput());
                    buf.writeVarLong(recipe.fluidAmount());

                    buf.writeBoolean(recipe.crushSoundId() != null);
                    if (recipe.crushSoundId() != null) {
                        buf.writeIdentifier(recipe.crushSoundId());
                    }
                },
                buf -> {
                    Ingredient in = Ingredient.PACKET_CODEC.decode(buf);

                    int outputCount = buf.readVarInt();
                    List<RecipeOutput> outputs = new ArrayList<>(outputCount);
                    for (int i = 0; i < outputCount; i++) {
                        outputs.add(RecipeOutput.PACKET_CODEC.decode(buf));
                    }

                    FluidVariant fluid = FluidVariant.PACKET_CODEC.decode(buf);
                    long amt = buf.readVarLong();

                    boolean hasCrushSound = buf.readBoolean();
                    Identifier crushSound = hasCrushSound ? buf.readIdentifier() : null;

                    return new CrushingTubRecipe(in, outputs, fluid, amt, crushSound);
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
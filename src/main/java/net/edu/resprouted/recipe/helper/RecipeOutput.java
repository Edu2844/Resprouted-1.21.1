package net.edu.resprouted.recipe.helper;

import com.mojang.serialization.Codec;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.datafixers.util.Pair;

public record RecipeOutput(ItemStack stack, float chance) {

    public static final Codec<RecipeOutput> CODEC = new Codec<>() {
        @Override
        public <T> DataResult<Pair<RecipeOutput, T>> decode(DynamicOps<T> ops, T input) {
            return ItemStack.CODEC.decode(ops, input).flatMap(pair -> {
                ItemStack stack = pair.getFirst();
                DataResult<Float> chanceResult = ops.get(input, "chance")
                        .flatMap(chanceValue -> Codec.FLOAT.decode(ops, chanceValue).map(Pair::getFirst));

                float chance = chanceResult.result().orElse(1.0f);
                return DataResult.success(Pair.of(new RecipeOutput(stack, chance), pair.getSecond()));
            });
        }

        @Override
        public <T> DataResult<T> encode(RecipeOutput output, DynamicOps<T> ops, T prefix) {
            return ItemStack.CODEC.encode(output.stack, ops, prefix).flatMap(encoded -> ops.mergeToMap(encoded, ops.createString("chance"), ops.createFloat(output.chance)));
        }
    };

    public static final PacketCodec<RegistryByteBuf, RecipeOutput> PACKET_CODEC = PacketCodec.tuple(
            ItemStack.PACKET_CODEC, RecipeOutput::stack,
            PacketCodecs.FLOAT, RecipeOutput::chance,
            RecipeOutput::new
    );
}
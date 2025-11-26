package net.edu.resprouted.advancement.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Optional;

public class FluidQualityInventoryCriterion extends AbstractCriterion<FluidQualityInventoryCriterion.Conditions> {

    @Override
    public Codec<Conditions> getConditionsCodec() {
        return Conditions.CODEC;
    }

    public void trigger(ServerPlayerEntity player, PlayerInventory inventory, ItemStack stack) {
        this.trigger(player, conditions -> conditions.matches(inventory, stack));
    }

    public record Conditions(
            Optional<LootContextPredicate> player,
            NumberRange.DoubleRange quality,
            Optional<RegistryEntryList<Item>> items
    ) implements AbstractCriterion.Conditions {

        public static final Codec<Conditions> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        EntityPredicate.LOOT_CONTEXT_PREDICATE_CODEC.optionalFieldOf("player").forGetter(Conditions::player),
                        NumberRange.DoubleRange.CODEC.optionalFieldOf("quality", NumberRange.DoubleRange.ANY).forGetter(Conditions::quality),
                        RegistryCodecs.entryList(RegistryKeys.ITEM).optionalFieldOf("items").forGetter(Conditions::items)
                ).apply(instance, Conditions::new)
        );

        public boolean matches(PlayerInventory inventory, ItemStack changedStack) {
            if (testStack(changedStack)) {
                return true;
            }

            for (int i = 0; i < inventory.size(); i++) {
                if (testStack(inventory.getStack(i))) {
                    return true;
                }
            }
            return false;
        }

        private boolean testStack(ItemStack stack) {
            if (stack.isEmpty()) {
                return false;
            }

            if (items.isPresent() && !stack.isIn(items.get())) {
                return false;
            }

            Float quality = stack.get(ModDataComponentTypes.FLUID_QUALITY);
            return quality != null && this.quality.test(quality.doubleValue());
        }
    }
}
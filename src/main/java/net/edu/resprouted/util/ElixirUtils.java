package net.edu.resprouted.util;

import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potions;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ElixirUtils {
    public static List<ItemStack> getElixirs() {
        List<ItemStack> elixirs = new ArrayList<>();
        elixirs.add(createElixir(StatusEffects.INSTANT_HEALTH, 0, 0));
        elixirs.add(createElixir(StatusEffects.INSTANT_HEALTH, 0, 1));
        elixirs.add(createElixir(StatusEffects.REGENERATION, 900, 0));
        elixirs.add(createElixir(StatusEffects.REGENERATION, 1800, 0));
        elixirs.add(createElixir(StatusEffects.REGENERATION, 440, 1));
        elixirs.add(createElixir(StatusEffects.WITHER, 900, 0));
        elixirs.add(createElixir(StatusEffects.WITHER, 1800, 0));
        elixirs.add(createElixir(StatusEffects.WITHER, 440, 1));
        elixirs.add(createElixir(StatusEffects.NIGHT_VISION, 3600, 0));
        elixirs.add(createElixir(StatusEffects.NIGHT_VISION, 9600, 0));
        elixirs.add(createElixir(StatusEffects.SPEED, 3600, 0));
        elixirs.add(createElixir(StatusEffects.SPEED, 9600, 0));
        elixirs.add(createElixir(StatusEffects.SPEED, 1800, 1));
        elixirs.add(createElixir(StatusEffects.FIRE_RESISTANCE, 3600, 0));
        elixirs.add(createElixir(StatusEffects.FIRE_RESISTANCE, 9600, 0));
        elixirs.add(createElixir(StatusEffects.HEALTH_BOOST, 3600, 0));
        elixirs.add(createElixir(StatusEffects.HEALTH_BOOST, 9600, 0));
        elixirs.add(createElixir(StatusEffects.HEALTH_BOOST, 1800, 1));
        elixirs.add(createElixir(StatusEffects.HASTE, 3600, 0));
        elixirs.add(createElixir(StatusEffects.HASTE, 9600, 0));
        elixirs.add(createElixir(StatusEffects.HASTE, 1800, 1));
        elixirs.add(createElixir(StatusEffects.STRENGTH, 3600, 0));
        elixirs.add(createElixir(StatusEffects.STRENGTH, 9600, 0));
        elixirs.add(createElixir(StatusEffects.STRENGTH, 1800, 1));
        elixirs.add(createElixir(ModEffects.IRON_SKIN, 3600, 0));
        elixirs.add(createElixir(ModEffects.IRON_SKIN, 9600, 0));
        elixirs.add(createElixir(ModEffects.IRON_SKIN, 1800, 1));
        elixirs.add(createElixir(StatusEffects.SLOW_FALLING, 3600, 0));
        elixirs.add(createElixir(StatusEffects.SLOW_FALLING, 9600, 0));
        elixirs.add(createElixir(ModEffects.BLAZING_TRAIL, 3600, 0));
        elixirs.add(createElixir(ModEffects.BLAZING_TRAIL, 9600, 0));

        return elixirs;
    }
    public static ItemStack createElixir(RegistryEntry<StatusEffect> effect, int duration, int amplifier) {
        ItemStack stack = new ItemStack(ModItems.ELIXIR_BOTTLE);
        stack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(Optional.of(Potions.WATER), Optional.empty(), List.of(new StatusEffectInstance(effect, duration, amplifier))));
        return stack;
    }
}

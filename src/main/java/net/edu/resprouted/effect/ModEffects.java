package net.edu.resprouted.effect;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.effect.custom.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> FIRE_POWER = registerStatusEffect("fire_power",
            new FirePowerEffect(StatusEffectCategory.BENEFICIAL, 0xFF4500));

    public static final RegistryEntry<StatusEffect> FULL_METAL = registerStatusEffect("full_metal",
            new FullMetalEffect(StatusEffectCategory.HARMFUL, 0x919191));

    public static final RegistryEntry<StatusEffect> IRON_SKIN = registerStatusEffect("iron_skin",
            new IronSkinEffect(StatusEffectCategory.BENEFICIAL, 0xFFFFDD));

    public static final RegistryEntry<StatusEffect> BLAZING_TRAIL = registerStatusEffect("blazing_trail",
            new BlazingTrailEffect(StatusEffectCategory.BENEFICIAL, 0xFB6800));

    public static final RegistryEntry<StatusEffect> TIPSY = registerStatusEffect("tipsy",
            new TipsyEffect(StatusEffectCategory.HARMFUL, 0x789C62));


    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Resprouted.MOD_ID, name), statusEffect);
    }
    public static void registerEffects() {
        Resprouted.LOGGER.info("Registering Effects for " + Resprouted.MOD_ID);
    }
}

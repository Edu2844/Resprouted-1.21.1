package net.edu.resprouted.effect.custom;

import net.edu.resprouted.Resprouted;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;

public class IronSkinEffect extends StatusEffect {
    private static final Identifier ARMOR_UUID = Identifier.of(Resprouted.MOD_ID, "armor");
    private static final Identifier TOUGHNESS_UUID = Identifier.of(Resprouted.MOD_ID,"toughness");

    public IronSkinEffect(StatusEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, ARMOR_UUID, 3.0, EntityAttributeModifier.Operation.ADD_VALUE);
        this.addAttributeModifier(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, TOUGHNESS_UUID, 2.0, EntityAttributeModifier.Operation.ADD_VALUE);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}


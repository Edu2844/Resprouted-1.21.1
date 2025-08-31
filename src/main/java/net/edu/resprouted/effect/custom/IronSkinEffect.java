package net.edu.resprouted.effect.custom;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import java.util.UUID;

public class IronSkinEffect extends StatusEffect {
    private static final UUID ARMOR_UUID = UUID.fromString("efc23a0c-71e1-4a0a-aabc-f58f5d8a4000");
    private static final UUID TOUGHNESS_UUID = UUID.fromString("874b2af4-7a9b-4e07-b351-40c6a0a40001");

    public IronSkinEffect(StatusEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, Identifier.of(ARMOR_UUID.toString()), 3.0, EntityAttributeModifier.Operation.ADD_VALUE);
        this.addAttributeModifier(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, Identifier.of(TOUGHNESS_UUID.toString()), 2.0, EntityAttributeModifier.Operation.ADD_VALUE);
    }
}


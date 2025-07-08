package net.edu.resprouted.entity;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.entity.custom.ChairEntity;
import net.edu.resprouted.entity.custom.ThrownTomatoEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<ChairEntity> CHAIR = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Resprouted.MOD_ID, "chair_entity"),
            EntityType.Builder.create(ChairEntity::new, SpawnGroup.MISC).dimensions(0.5f, 0.5f).build());
    public static final EntityType<ThrownTomatoEntity> THROWN_TOMATO = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Resprouted.MOD_ID, "thrown_tomato"),
            EntityType.Builder.<ThrownTomatoEntity>create(ThrownTomatoEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25f, 0.25f)
                    .build());

    public static void registerModEntities() {
        Resprouted.LOGGER.info("Registering Mod Entities for " + Resprouted.MOD_ID);
    }
}

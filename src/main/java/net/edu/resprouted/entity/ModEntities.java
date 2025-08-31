package net.edu.resprouted.entity;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.entity.custom.ChairEntity;
import net.edu.resprouted.entity.custom.StoolEntity;
import net.edu.resprouted.entity.custom.ThrownTomatoEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<ChairEntity> CHAIR = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Resprouted.MOD_ID, "chair_entity"),
            EntityType.Builder.create(ChairEntity::new, SpawnGroup.MISC).dimensions(0.5f, 0.5f).build());

    public static final EntityType<StoolEntity> STOOL = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Resprouted.MOD_ID, "stool_entity"),
            EntityType.Builder.create(StoolEntity::new, SpawnGroup.MISC).dimensions(0.5f, 0.6f).build());

    public static final EntityType<ThrownTomatoEntity> THROWN_TOMATO = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Resprouted.MOD_ID, "thrown_tomato"),
            EntityType.Builder.<ThrownTomatoEntity>create(ThrownTomatoEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25f, 0.25f)
                    .build());

    public static final Identifier OLIVE_BOAT = Identifier.of(Resprouted.MOD_ID, "olive_boat");
    public static final Identifier OLIVE_CHEST_BOAT = Identifier.of(Resprouted.MOD_ID, "olive_chest_boat");

    public static final Identifier IRONWOOD_BOAT = Identifier.of(Resprouted.MOD_ID, "ironwood_boat");
    public static final Identifier IRONWOOD_CHEST_BOAT = Identifier.of(Resprouted.MOD_ID, "ironwood_chest_boat");

    public static final RegistryKey<TerraformBoatType> OLIVE_BOAT_KEY = TerraformBoatTypeRegistry.createKey(OLIVE_BOAT);
    public static final RegistryKey<TerraformBoatType> IRONWOOD_BOAT_KEY = TerraformBoatTypeRegistry.createKey(IRONWOOD_BOAT);

    public static void registerModEntities() {
        Resprouted.LOGGER.info("Registering Entities for " + Resprouted.MOD_ID);
    }
}

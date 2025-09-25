package net.edu.resprouted.block;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.entity.custom.*;
import net.edu.resprouted.util.CabinetRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.edu.resprouted.block.ModBlocks.*;

public class ModBlockEntities {
    public static final BlockEntityType<CrushingTubBE> CRUSHING_TUB_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Resprouted.MOD_ID, "crushing_tub_be"),
                    BlockEntityType.Builder.create(CrushingTubBE::new, ModBlocks.CRUSHING_TUB).build(null));

    public static final BlockEntityType<LiquidBarrelBE> LIQUID_BARREL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Resprouted.MOD_ID, "liquid_barrel_be"),
                    BlockEntityType.Builder.create(LiquidBarrelBE::new, ModBlocks.LIQUID_BARREL).build(null));

    public static final BlockEntityType<EvaporatingBasinBE> EVAPORATING_BASIN_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Resprouted.MOD_ID, "evaporating_basin_be"),
                    BlockEntityType.Builder.create(EvaporatingBasinBE::new, ModBlocks.EVAPORATING_BASIN).build(null));

    public static final BlockEntityType<CabinetBE> CABINET_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Resprouted.MOD_ID, "cabinet_be"),
                    BlockEntityType.Builder.create(CabinetBE::new, CabinetRegistry.getAllCabinetBlocks()).build(null));

    public static final BlockEntityType<BasicCondenserBE> CONDENSER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Resprouted.MOD_ID, "condenser_be"),
                    BlockEntityType.Builder.create(BasicCondenserBE::new, ModBlocks.CONDENSER).build(null));

    public static final BlockEntityType<AdvancedCondenserBE> ADVANCED_CONDENSER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Resprouted.MOD_ID, "advanced_condenser_be"),
                    BlockEntityType.Builder.create(AdvancedCondenserBE::new, ModBlocks.ADVANCED_CONDENSER).build(null));

    public static final BlockEntityType<BrewingBarrelBE> BREWING_BARREL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Resprouted.MOD_ID, "brewing_barrel_be"),
                    BlockEntityType.Builder.create(BrewingBarrelBE::new, ModBlocks.BREWING_BARREL).build(null));


    public static final BlockEntityType<ModSignBlockEntity> MOD_SIGN_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(Resprouted.MOD_ID, "mod_sign_entity"),
            BlockEntityType.Builder.create(ModSignBlockEntity::new, OLIVE_SIGN, OLIVE_WALL_SIGN, IRONWOOD_SIGN, IRONWOOD_WALL_SIGN).build()
    );

    public static final BlockEntityType<ModHangingSignBlockEntity> MOD_HANGING_SIGN_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(Resprouted.MOD_ID, "mod_hanging_sign_entity"),
            BlockEntityType.Builder.create(ModHangingSignBlockEntity::new, OLIVE_HANGING_SIGN, OLIVE_WALL_HANGING_SIGN, IRONWOOD_HANGING_SIGN, IRONWOOD_WALL_HANGING_SIGN).build()
    );


    public static void registerModBlockEntities() {
        Resprouted.LOGGER.info("Registering Block Entities for " + Resprouted.MOD_ID);
    }
}

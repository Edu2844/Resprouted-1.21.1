package net.edu.resprouted.block;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.entity.custom.CabinetBlockEntity;
import net.edu.resprouted.block.entity.custom.CrushingTubBlockEntity;
import net.edu.resprouted.block.entity.custom.EvaporatingBasinBlockEntity;
import net.edu.resprouted.block.entity.custom.LiquidBarrelBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<CrushingTubBlockEntity> CRUSHING_TUB_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Resprouted.MOD_ID, "crushing_tub_be"),
                    BlockEntityType.Builder.create(CrushingTubBlockEntity::new, ModBlocks.CRUSHING_TUB).build(null));

    public static final BlockEntityType<LiquidBarrelBlockEntity> LIQUID_BARREL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Resprouted.MOD_ID, "liquid_barrel_be"),
                    BlockEntityType.Builder.create(LiquidBarrelBlockEntity::new, ModBlocks.LIQUID_BARREL).build(null));

    public static final BlockEntityType<EvaporatingBasinBlockEntity> EVAPORATING_BASIN_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Resprouted.MOD_ID, "evaporating_basin_be"),
                    BlockEntityType.Builder.create(EvaporatingBasinBlockEntity::new, ModBlocks.EVAPORATING_BASIN).build(null));

    public static final BlockEntityType<CabinetBlockEntity> CABINET_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Resprouted.MOD_ID, "cabinet_be"),
                    BlockEntityType.Builder.create(CabinetBlockEntity::new, ModBlocks.CABINET_BLOCK).build(null));

    public static void registerModBlockEntities() {
        Resprouted.LOGGER.info("Registering Mod Block Entities for " + Resprouted.MOD_ID);
    }
}

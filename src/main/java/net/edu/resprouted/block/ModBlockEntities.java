package net.edu.resprouted.block;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.entity.custom.agriculture.BrewingBarrelBlockEntity;
import net.edu.resprouted.block.entity.custom.agriculture.CrushingTubBlockEntity;
import net.edu.resprouted.block.entity.custom.agriculture.DryingBasinBlockEntity;
import net.edu.resprouted.block.entity.custom.alchemy.AdvancedCondenserBlockEntity;
import net.edu.resprouted.block.entity.custom.alchemy.BasicCondenserBlockEntity;
import net.edu.resprouted.block.entity.custom.decorative.CabinetBlockEntity;
import net.edu.resprouted.block.entity.custom.decorative.JarBlockEntity;
import net.edu.resprouted.block.entity.custom.decorative.LiquidBarrelBlockEntity;
import net.edu.resprouted.block.entity.custom.decorative.UrnBlockEntity;
import net.edu.resprouted.util.misc.CabinetRegistry;
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

    public static final BlockEntityType<DryingBasinBlockEntity> DRYING_BASIN_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Resprouted.MOD_ID, "drying_basin_be"),
                    BlockEntityType.Builder.create(DryingBasinBlockEntity::new, ModBlocks.DRYING_BASIN).build(null));

    public static final BlockEntityType<CabinetBlockEntity> CABINET_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Resprouted.MOD_ID, "cabinet_be"),
                    BlockEntityType.Builder.create(CabinetBlockEntity::new, CabinetRegistry.getRegisteredCabinets()).build(null));

    public static final BlockEntityType<BasicCondenserBlockEntity> CONDENSER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Resprouted.MOD_ID, "condenser_be"),
                    BlockEntityType.Builder.create(BasicCondenserBlockEntity::new, ModBlocks.BASIC_CONDENSER).build(null));

    public static final BlockEntityType<AdvancedCondenserBlockEntity> ADVANCED_CONDENSER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Resprouted.MOD_ID, "advanced_condenser_be"),
                    BlockEntityType.Builder.create(AdvancedCondenserBlockEntity::new, ModBlocks.ADVANCED_CONDENSER).build(null));

    public static final BlockEntityType<BrewingBarrelBlockEntity> BREWING_BARREL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Resprouted.MOD_ID, "brewing_barrel_be"),
                    BlockEntityType.Builder.create(BrewingBarrelBlockEntity::new, ModBlocks.BREWING_BARREL).build(null));

    public static final BlockEntityType<JarBlockEntity> JAR_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Resprouted.MOD_ID, "jar_be"),
            BlockEntityType.Builder.create(
                    JarBlockEntity::new,
                    ModBlocks.JAR,
                    ModBlocks.BLACK_JAR,
                    ModBlocks.BLUE_JAR,
                    ModBlocks.BROWN_JAR,
                    ModBlocks.CYAN_JAR,
                    ModBlocks.GRAY_JAR,
                    ModBlocks.GREEN_JAR,
                    ModBlocks.LIGHT_BLUE_JAR,
                    ModBlocks.LIGHT_GRAY_JAR,
                    ModBlocks.LIME_JAR,
                    ModBlocks.MAGENTA_JAR,
                    ModBlocks.ORANGE_JAR,
                    ModBlocks.PINK_JAR,
                    ModBlocks.PURPLE_JAR,
                    ModBlocks.RED_JAR,
                    ModBlocks.WHITE_JAR,
                    ModBlocks.YELLOW_JAR
            ).build(null)
    );

    public static final BlockEntityType<UrnBlockEntity> URN_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Resprouted.MOD_ID, "urn_be"),
                    BlockEntityType.Builder.create(
                            UrnBlockEntity::new,
                            ModBlocks.URN,
                            ModBlocks.BLACK_URN,
                            ModBlocks.BLUE_URN,
                            ModBlocks.BROWN_URN,
                            ModBlocks.CYAN_URN,
                            ModBlocks.GRAY_URN,
                            ModBlocks.GREEN_URN,
                            ModBlocks.LIGHT_BLUE_URN,
                            ModBlocks.LIGHT_GRAY_URN,
                            ModBlocks.LIME_URN,
                            ModBlocks.MAGENTA_URN,
                            ModBlocks.ORANGE_URN,
                            ModBlocks.PINK_URN,
                            ModBlocks.PURPLE_URN,
                            ModBlocks.RED_URN,
                            ModBlocks.WHITE_URN,
                            ModBlocks.YELLOW_URN
                    ).build(null)
            );

    public static void registerModBlockEntities() {
        Resprouted.LOGGER.info("Registering Block Entities for " + Resprouted.MOD_ID);
    }
}

package net.edu.resprouted.datagen;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.custom.decorative.CabinetBlock;
import net.edu.resprouted.block.enums.CabinetType;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

import java.util.Optional;

@SuppressWarnings("deprecation")
public class ModCabinetTemplateModels {
    public static final TextureKey CABINET = TextureKey.of("cabinet");
    public static final TextureKey FRONT = TextureKey.of("front");
    public static final TextureKey SIDE = TextureKey.of("side");

    public static final Model TEMPLATE_CABINET = new Model(
            Optional.of(Identifier.of("minecraft", "block/orientable")), Optional.empty(), FRONT, SIDE, TextureKey.TOP
    );
    public static final Model TEMPLATE_CABINET_BOTTOM = new Model(
            Optional.of(Identifier.of(Resprouted.MOD_ID, "block/template_cabinet_bottom")),
            Optional.empty(),
            CABINET, TextureKey.PARTICLE
    );
    public static final Model TEMPLATE_CABINET_BOTTOM_LEFT = new Model(
            Optional.of(Identifier.of(Resprouted.MOD_ID, "block/template_cabinet_bottom_left")),
            Optional.empty(),
            CABINET, TextureKey.PARTICLE
    );
    public static final Model TEMPLATE_CABINET_BOTTOM_OPEN = new Model(
            Optional.of(Identifier.of(Resprouted.MOD_ID, "block/template_cabinet_bottom_open")),
            Optional.empty(),
            CABINET, TextureKey.PARTICLE
    );
    public static final Model TEMPLATE_CABINET_BOTTOM_OPEN_LEFT = new Model(
            Optional.of(Identifier.of(Resprouted.MOD_ID, "block/template_cabinet_bottom_open_left")),
            Optional.empty(),
            CABINET, TextureKey.PARTICLE
    );
    public static final Model TEMPLATE_CABINET_TOP = new Model(
            Optional.of(Identifier.of(Resprouted.MOD_ID, "block/template_cabinet_top")),
            Optional.empty(),
            CABINET, TextureKey.PARTICLE
    );
    public static final Model TEMPLATE_CABINET_TOP_LEFT = new Model(
            Optional.of(Identifier.of(Resprouted.MOD_ID, "block/template_cabinet_top_left")),
            Optional.empty(),
            CABINET, TextureKey.PARTICLE
    );
    public static final Model TEMPLATE_CABINET_TOP_OPEN = new Model(
            Optional.of(Identifier.of(Resprouted.MOD_ID, "block/template_cabinet_top_open")),
            Optional.empty(),
            CABINET, TextureKey.PARTICLE
    );
    public static final Model TEMPLATE_CABINET_TOP_OPEN_LEFT = new Model(
            Optional.of(Identifier.of(Resprouted.MOD_ID, "block/template_cabinet_top_open_left")),
            Optional.empty(),
            CABINET, TextureKey.PARTICLE
    );

    // =================================================
    // ||          CABINET MODEL GENERATOR            ||
    // =================================================
    public static void registerCabinetModels(BlockStateModelGenerator blockStateModelGenerator, Block cabinetBlock, String woodType) {
        String blockName = getBlockName(cabinetBlock);
        CabinetModels models = generateCabinetModels(blockStateModelGenerator, woodType, blockName);
        createCabinetBlockState(blockStateModelGenerator, cabinetBlock, models);
        registerCabinetItemModel(blockStateModelGenerator, cabinetBlock, models.singleClosed);
    }

    private static class CabinetModels {
        // Single cabinet models
        Identifier singleClosed;
        Identifier singleClosedLeft;
        Identifier singleOpen;
        Identifier singleOpenLeft;

        // Top cabinet models
        Identifier topClosed;
        Identifier topClosedLeft;
        Identifier topOpen;
        Identifier topOpenLeft;

        // Bottom cabinet models
        Identifier bottomClosed;
        Identifier bottomClosedLeft;
        Identifier bottomOpen;
        Identifier bottomOpenLeft;
    }

    private static CabinetModels generateCabinetModels(BlockStateModelGenerator generator, String woodType, String blockName) {
        CabinetModels models = new CabinetModels();
        String basePath = "block/" + blockName;

        // Single closed (right hinge)
        TextureMap singleClosedTextures = new TextureMap()
                .put(FRONT, Identifier.of(Resprouted.MOD_ID, "block/" + woodType + "_cabinet_front"))
                .put(SIDE, Identifier.of(Resprouted.MOD_ID, "block/" + woodType + "_cabinet_side"))
                .put(TextureKey.TOP, Identifier.of(Resprouted.MOD_ID, "block/" + woodType + "_cabinet_side"));

        models.singleClosed = TEMPLATE_CABINET.upload(
                Identifier.of(Resprouted.MOD_ID, basePath),
                singleClosedTextures,
                generator.modelCollector
        );

        // Single closed left (left hinge)
        TextureMap singleClosedLeftTextures = new TextureMap()
                .put(FRONT, Identifier.of(Resprouted.MOD_ID, "block/" + woodType + "_cabinet_front_left"))
                .put(SIDE, Identifier.of(Resprouted.MOD_ID, "block/" + woodType + "_cabinet_side"))
                .put(TextureKey.TOP, Identifier.of(Resprouted.MOD_ID, "block/" + woodType + "_cabinet_side"));

        models.singleClosedLeft = TEMPLATE_CABINET.upload(
                Identifier.of(Resprouted.MOD_ID, basePath + "_left"),
                singleClosedLeftTextures,
                generator.modelCollector
        );

        // Single open (right hinge)
        TextureMap singleOpenTextures = new TextureMap()
                .put(FRONT, Identifier.of(Resprouted.MOD_ID, "block/" + woodType + "_cabinet_front_open"))
                .put(SIDE, Identifier.of(Resprouted.MOD_ID, "block/" + woodType + "_cabinet_side"))
                .put(TextureKey.TOP, Identifier.of(Resprouted.MOD_ID, "block/" + woodType + "_cabinet_side"));

        models.singleOpen = TEMPLATE_CABINET.upload(
                Identifier.of(Resprouted.MOD_ID, basePath + "_open"),
                singleOpenTextures,
                generator.modelCollector
        );

        // Single open left (left hinge)
        TextureMap singleOpenLeftTextures = new TextureMap()
                .put(FRONT, Identifier.of(Resprouted.MOD_ID, "block/" + woodType + "_cabinet_front_open_left"))
                .put(SIDE, Identifier.of(Resprouted.MOD_ID, "block/" + woodType + "_cabinet_side"))
                .put(TextureKey.TOP, Identifier.of(Resprouted.MOD_ID, "block/" + woodType + "_cabinet_side"));

        models.singleOpenLeft = TEMPLATE_CABINET.upload(
                Identifier.of(Resprouted.MOD_ID, basePath + "_open_left"),
                singleOpenLeftTextures,
                generator.modelCollector
        );

        // ===== TOP CABINET MODELS =====
        TextureMap topTextures = new TextureMap()
                .put(CABINET, Identifier.of(Resprouted.MOD_ID, "block/" + woodType + "_double_cabinet"))
                .put(TextureKey.PARTICLE, Identifier.of(Resprouted.MOD_ID, "block/" + woodType + "_cabinet_side"));

        models.topClosed = TEMPLATE_CABINET_TOP.upload(
                Identifier.of(Resprouted.MOD_ID, basePath + "_top"),
                topTextures,
                generator.modelCollector
        );

        models.topClosedLeft = TEMPLATE_CABINET_TOP_LEFT.upload(
                Identifier.of(Resprouted.MOD_ID, basePath + "_top_left"),
                topTextures,
                generator.modelCollector
        );

        models.topOpen = TEMPLATE_CABINET_TOP_OPEN.upload(
                Identifier.of(Resprouted.MOD_ID, basePath + "_top_open"),
                topTextures,
                generator.modelCollector
        );

        models.topOpenLeft = TEMPLATE_CABINET_TOP_OPEN_LEFT.upload(
                Identifier.of(Resprouted.MOD_ID, basePath + "_top_open_left"),
                topTextures,
                generator.modelCollector
        );

        // ===== BOTTOM CABINET MODELS =====
        TextureMap bottomTextures = new TextureMap()
                .put(CABINET, Identifier.of(Resprouted.MOD_ID, "block/" + woodType + "_double_cabinet"))
                .put(TextureKey.PARTICLE, Identifier.of(Resprouted.MOD_ID, "block/" + woodType + "_cabinet_side"));

        models.bottomClosed = TEMPLATE_CABINET_BOTTOM.upload(
                Identifier.of(Resprouted.MOD_ID, basePath + "_bottom"),
                bottomTextures,
                generator.modelCollector
        );

        models.bottomClosedLeft = TEMPLATE_CABINET_BOTTOM_LEFT.upload(
                Identifier.of(Resprouted.MOD_ID, basePath + "_bottom_left"),
                bottomTextures,
                generator.modelCollector
        );

        models.bottomOpen = TEMPLATE_CABINET_BOTTOM_OPEN.upload(
                Identifier.of(Resprouted.MOD_ID, basePath + "_bottom_open"),
                bottomTextures,
                generator.modelCollector
        );

        models.bottomOpenLeft = TEMPLATE_CABINET_BOTTOM_OPEN_LEFT.upload(
                Identifier.of(Resprouted.MOD_ID, basePath + "_bottom_open_left"),
                bottomTextures,
                generator.modelCollector
        );

        return models;
    }


    private static void createCabinetBlockState(BlockStateModelGenerator generator, Block cabinet, CabinetModels models) {
        generator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(cabinet)
                        .coordinate(BlockStateVariantMap.create(
                                Properties.HORIZONTAL_FACING,
                                CabinetBlock.CABINET_TYPE,
                                CabinetBlock.OPEN,
                                CabinetBlock.HINGE
                        ).register((facing, cabinetType, open, hinge) -> {
                            BlockStateVariant variant = BlockStateVariant.create();
                            Identifier model = getModelForState(models, cabinetType, open, hinge);
                            variant.put(VariantSettings.MODEL, model);

                            VariantSettings.Rotation rotation = switch (facing) {
                                case EAST -> VariantSettings.Rotation.R90;
                                case SOUTH -> VariantSettings.Rotation.R180;
                                case WEST -> VariantSettings.Rotation.R270;
                                default -> VariantSettings.Rotation.R0;
                            };
                            variant.put(VariantSettings.Y, rotation);
                            return variant;
                        }))
        );
    }


    private static Identifier getModelForState(CabinetModels models, CabinetType cabinetType, boolean open, net.minecraft.block.enums.DoorHinge hinge) {
        boolean isLeft = hinge == net.minecraft.block.enums.DoorHinge.LEFT;

        return switch (cabinetType) {
            case SINGLE -> {
                if (open) {
                    yield isLeft ? models.singleOpenLeft : models.singleOpen;
                } else {
                    yield isLeft ? models.singleClosedLeft : models.singleClosed;
                }
            }
            case TOP -> {
                if (open) {
                    yield isLeft ? models.topOpenLeft : models.topOpen;
                } else {
                    yield isLeft ? models.topClosedLeft : models.topClosed;
                }
            }
            case BOTTOM -> {
                if (open) {
                    yield isLeft ? models.bottomOpenLeft : models.bottomOpen;
                } else {
                    yield isLeft ? models.bottomClosedLeft : models.bottomClosed;
                }
            }
        };
    }


    private static void registerCabinetItemModel(BlockStateModelGenerator generator, Block cabinet, Identifier baseModel) {
        generator.registerParentedItemModel(cabinet, baseModel);
    }

    private static String getBlockName(Block block) {
        Identifier blockId = block.getRegistryEntry().registryKey().getValue();
        return blockId.getPath();
    }
}
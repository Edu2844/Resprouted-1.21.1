package net.edu.resprouted.datagen;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.custom.decorative.CandleHolderBlock;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Optional;

@SuppressWarnings("deprecation")
public class ModCandleHolderTemplateModels {
    public static final TextureKey HOLDER = TextureKey.of("holder");
    public static final Model TEMPLATE_CANDLE_HOLDER = new Model(
            Optional.of(Identifier.of(Resprouted.MOD_ID, "block/template_candle_holder")),
            Optional.empty(), HOLDER, TextureKey.CANDLE
    );
    public static final Model TEMPLATE_WALL_CANDLE_HOLDER = new Model(
            Optional.of(Identifier.of(Resprouted.MOD_ID, "block/template_wall_candle_holder")),
            Optional.empty(),
            HOLDER, TextureKey.CANDLE
    );
    public static final Model TEMPLATE_DOUBLE_CANDLE_HOLDER = new Model(
            Optional.of(Identifier.of(Resprouted.MOD_ID, "block/template_double_candle_holder")),
            Optional.empty(),
            HOLDER, TextureKey.CANDLE
    );
    public static final Model TEMPLATE_WALL_DOUBLE_CANDLE_HOLDER = new Model(
            Optional.of(Identifier.of(Resprouted.MOD_ID, "block/template_wall_double_candle_holder")),
            Optional.empty(),
            HOLDER, TextureKey.CANDLE
    );


    // =================================================
    // ||               CANDLE HOLDER                 ||
    // =================================================

    public static void registerCandleHolderModels(BlockStateModelGenerator blockStateModelGenerator, Block candleHolder, String holderTexture, String candleColor, String folderName) {

        TextureMap litTextures = createTextures(holderTexture, candleColor, true);
        TextureMap unlitTextures = createTextures(holderTexture, candleColor, false);

        String blockName = getBlockName(candleHolder);

        Identifier floorUnlitModel = TEMPLATE_CANDLE_HOLDER.upload(
                Identifier.of(Resprouted.MOD_ID, "block/" + folderName + "/" + blockName),
                unlitTextures,
                blockStateModelGenerator.modelCollector
        );
        Identifier floorLitModel = TEMPLATE_CANDLE_HOLDER.upload(
                Identifier.of(Resprouted.MOD_ID, "block/" + folderName + "/" + blockName + "_lit"),
                litTextures,
                blockStateModelGenerator.modelCollector
        );
        Identifier wallUnlitModel = TEMPLATE_WALL_CANDLE_HOLDER.upload(
                Identifier.of(Resprouted.MOD_ID, "block/" + folderName + "/wall_" + blockName),
                unlitTextures,
                blockStateModelGenerator.modelCollector
        );
        Identifier wallLitModel = TEMPLATE_WALL_CANDLE_HOLDER.upload(
                Identifier.of(Resprouted.MOD_ID, "block/" + folderName + "/wall_" + blockName + "_lit"),
                litTextures,
                blockStateModelGenerator.modelCollector
        );

        createBlockState(blockStateModelGenerator, candleHolder, floorUnlitModel, floorLitModel, wallUnlitModel, wallLitModel);
        registerCandleHolderItemModel(blockStateModelGenerator, candleHolder, holderTexture, candleColor);
    }

    private static TextureMap createTextures(String holderTexture, String candleColor, boolean lit) {
        String candleSuffix = lit ? "_lit" : "";

        if (candleColor.isEmpty()) {
            return new TextureMap()
                    .put(HOLDER, Identifier.of(Resprouted.MOD_ID, "block/" + holderTexture))
                    .put(TextureKey.CANDLE, Identifier.of("minecraft", "block/candle" + candleSuffix));
        } else {
            return new TextureMap()
                    .put(HOLDER, Identifier.of(Resprouted.MOD_ID, "block/" + holderTexture))
                    .put(TextureKey.CANDLE, Identifier.of("minecraft", "block/" + candleColor + "_candle" + candleSuffix));
        }
    }

    private static void createBlockState(BlockStateModelGenerator blockStateModelGenerator, Block candleHolder, Identifier floorUnlitModel, Identifier floorLitModel, Identifier wallUnlitModel, Identifier wallLitModel) {

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(candleHolder)
                .coordinate(BlockStateVariantMap.create(CandleHolderBlock.FACING, Properties.LIT)
                        .register((facing, lit) -> {
                            BlockStateVariant variant = BlockStateVariant.create();

                            if (facing == Direction.UP) {
                                variant.put(VariantSettings.MODEL, lit ? floorLitModel : floorUnlitModel);
                            } else {
                                variant.put(VariantSettings.MODEL, lit ? wallLitModel : wallUnlitModel);

                                switch (facing) {
                                    case NORTH -> variant.put(VariantSettings.Y, VariantSettings.Rotation.R0);
                                    case EAST -> variant.put(VariantSettings.Y, VariantSettings.Rotation.R90);
                                    case SOUTH -> variant.put(VariantSettings.Y, VariantSettings.Rotation.R180);
                                    case WEST -> variant.put(VariantSettings.Y, VariantSettings.Rotation.R270);
                                    default -> {}
                                }
                            }
                            return variant;
                        }))
        );
    }

    private static void registerCandleHolderItemModel(BlockStateModelGenerator blockStateModelGenerator, Block candleHolder, String holderTexture, String candleColor) {
        Model itemModel = new Model(Optional.of(Identifier.of("minecraft", "item/generated")), Optional.empty(), TextureKey.LAYER0, TextureKey.LAYER1);

        TextureMap itemTextures;
        if (candleColor.isEmpty()) {
            itemTextures = new TextureMap()
                    .put(TextureKey.LAYER1, Identifier.of(Resprouted.MOD_ID, "item/candle_holder/candle"))
                    .put(TextureKey.LAYER0, Identifier.of(Resprouted.MOD_ID, "item/candle_holder/" + holderTexture + "_layer"));
        } else {
            itemTextures = new TextureMap()
                    .put(TextureKey.LAYER1, Identifier.of(Resprouted.MOD_ID, "item/candle_holder/" + candleColor + "_candle"))
                    .put(TextureKey.LAYER0, Identifier.of(Resprouted.MOD_ID, "item/candle_holder/" + holderTexture + "_layer"));
        }
        itemModel.upload(ModelIds.getItemModelId(candleHolder.asItem()), itemTextures, blockStateModelGenerator.modelCollector);
    }


    // =================================================
    // ||             DOUBLE CANDLE HOLDERS           ||
    // =================================================
    public static void registerDoubleCandleHolderModels(BlockStateModelGenerator blockStateModelGenerator, Block doubleCandleHolder, String holderTexture, String candleColor, String folderName) {
        TextureMap litTextures = createTextures(holderTexture, candleColor, true);
        TextureMap unlitTextures = createTextures(holderTexture, candleColor, false);

        String blockName = getBlockName(doubleCandleHolder);

        Identifier floorUnlitModel = TEMPLATE_DOUBLE_CANDLE_HOLDER.upload(
                Identifier.of(Resprouted.MOD_ID, "block/" + folderName + "/" + blockName),
                unlitTextures,
                blockStateModelGenerator.modelCollector
        );
        Identifier floorLitModel = TEMPLATE_DOUBLE_CANDLE_HOLDER.upload(
                Identifier.of(Resprouted.MOD_ID, "block/" + folderName + "/" + blockName + "_lit"),
                litTextures,
                blockStateModelGenerator.modelCollector
        );
        Identifier wallUnlitModel = TEMPLATE_WALL_DOUBLE_CANDLE_HOLDER.upload(
                Identifier.of(Resprouted.MOD_ID, "block/" + folderName + "/wall_" + blockName),
                unlitTextures,
                blockStateModelGenerator.modelCollector
        );
        Identifier wallLitModel = TEMPLATE_WALL_DOUBLE_CANDLE_HOLDER.upload(
                Identifier.of(Resprouted.MOD_ID, "block/" + folderName + "/wall_" + blockName + "_lit"),
                litTextures,
                blockStateModelGenerator.modelCollector
        );
        createDoubleBlockState(blockStateModelGenerator, doubleCandleHolder, floorUnlitModel, floorLitModel, wallUnlitModel, wallLitModel);
        registerDoubleCandleHolderItemModel(blockStateModelGenerator, doubleCandleHolder, holderTexture, candleColor);
    }

    private static void createDoubleBlockState(BlockStateModelGenerator blockStateModelGenerator, Block doubleCandleHolder, Identifier floorUnlitModel, Identifier floorLitModel, Identifier wallUnlitModel, Identifier wallLitModel) {
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(doubleCandleHolder)
                .coordinate(BlockStateVariantMap.create(CandleHolderBlock.FACING, Properties.LIT, Properties.AXIS)
                        .register((facing, lit, axis) -> {
                            BlockStateVariant variant = BlockStateVariant.create();

                            if (facing == Direction.UP) {
                                variant.put(VariantSettings.MODEL, lit ? floorLitModel : floorUnlitModel);

                                if (axis == Direction.Axis.X) {
                                    variant.put(VariantSettings.Y, VariantSettings.Rotation.R90);
                                } else {
                                    variant.put(VariantSettings.Y, VariantSettings.Rotation.R0);
                                }

                            } else {
                                variant.put(VariantSettings.MODEL, lit ? wallLitModel : wallUnlitModel);

                                switch (facing) {
                                    case NORTH -> variant.put(VariantSettings.Y, VariantSettings.Rotation.R0);
                                    case EAST -> variant.put(VariantSettings.Y, VariantSettings.Rotation.R90);
                                    case SOUTH -> variant.put(VariantSettings.Y, VariantSettings.Rotation.R180);
                                    case WEST -> variant.put(VariantSettings.Y, VariantSettings.Rotation.R270);
                                    default -> {}
                                }
                            }
                            return variant;
                        }))
        );
    }
    private static void registerDoubleCandleHolderItemModel(BlockStateModelGenerator blockStateModelGenerator, Block doubleCandleHolder, String holderTexture, String candleColor) {
        Model itemModel = new Model(Optional.of(Identifier.of("minecraft", "item/generated")), Optional.empty(), TextureKey.LAYER0, TextureKey.LAYER1);

        TextureMap itemTextures;
        if (candleColor.isEmpty()) {
            itemTextures = new TextureMap()
                    .put(TextureKey.LAYER1, Identifier.of(Resprouted.MOD_ID, "item/candle_holder/double_candle"))
                    .put(TextureKey.LAYER0, Identifier.of(Resprouted.MOD_ID, "item/candle_holder/" + holderTexture + "_layer"));
        } else {
            itemTextures = new TextureMap()
                    .put(TextureKey.LAYER1, Identifier.of(Resprouted.MOD_ID, "item/candle_holder/" + candleColor + "_double_candle"))
                    .put(TextureKey.LAYER0, Identifier.of(Resprouted.MOD_ID, "item/candle_holder/" + holderTexture + "_layer"));
        }
        itemModel.upload(ModelIds.getItemModelId(doubleCandleHolder.asItem()), itemTextures, blockStateModelGenerator.modelCollector);
    }


    // =================================================
    // ||            METHODS PER MATERIAL             ||
    // =================================================

    public static void registerCopperCandleHolderModels(BlockStateModelGenerator blockStateModelGenerator, Block candleHolder, String holderTexture, String candleColor) {
        registerCandleHolderModels(blockStateModelGenerator, candleHolder, holderTexture, candleColor, "copper_candle_holder");
    }

    public static void registerGoldenCandleHolderModels(BlockStateModelGenerator blockStateModelGenerator, Block candleHolder, String holderTexture, String candleColor) {
        registerCandleHolderModels(blockStateModelGenerator, candleHolder, holderTexture, candleColor, "golden_candle_holder");
    }

    public static void registerIronCandleHolderModels(BlockStateModelGenerator blockStateModelGenerator, Block candleHolder, String holderTexture, String candleColor) {
        registerCandleHolderModels(blockStateModelGenerator, candleHolder, holderTexture, candleColor, "iron_candle_holder");
    }

    public static void registerDoubleCopperCandleHolderModels(BlockStateModelGenerator blockStateModelGenerator, Block doubleCandleHolder, String holderTexture, String candleColor) {
        registerDoubleCandleHolderModels(blockStateModelGenerator, doubleCandleHolder, holderTexture, candleColor, "copper_candle_holder");
    }

    public static void registerDoubleGoldenCandleHolderModels(BlockStateModelGenerator blockStateModelGenerator, Block doubleCandleHolder, String holderTexture, String candleColor) {
        registerDoubleCandleHolderModels(blockStateModelGenerator, doubleCandleHolder, holderTexture, candleColor, "golden_candle_holder");
    }

    public static void registerDoubleIronCandleHolderModels(BlockStateModelGenerator blockStateModelGenerator, Block doubleCandleHolder, String holderTexture, String candleColor) {
        registerDoubleCandleHolderModels(blockStateModelGenerator, doubleCandleHolder, holderTexture, candleColor, "iron_candle_holder");
    }

    private static String getBlockName(Block block) {
        Identifier blockId = block.getRegistryEntry().registryKey().getValue();
        return blockId.getPath();
    }
}
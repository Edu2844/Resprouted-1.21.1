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
public class ModTemplateModels {
    public static final TextureKey HOLDER = TextureKey.of("holder");

    public static final Model TEMPLATE_CANDLE_HOLDER = new Model(
            Optional.of(Identifier.of(Resprouted.MOD_ID, "block/candle/template_candle_holder")), Optional.empty(),
            HOLDER, TextureKey.CANDLE
    );

    public static final Model TEMPLATE_WALL_CANDLE_HOLDER = new Model(
            Optional.of(Identifier.of(Resprouted.MOD_ID, "block/candle/template_wall_candle_holder")),
            Optional.empty(),
            HOLDER, TextureKey.CANDLE
    );

    public static void registerCopperCandleHolderModels(BlockStateModelGenerator blockStateModelGenerator, Block candleHolder, String holderTexture, String candleColor) {

        TextureMap litTextures;
        TextureMap unlitTextures;

        if (candleColor.isEmpty()) {
            litTextures = new TextureMap()
                    .put(HOLDER, Identifier.of("resprouted", "block/" + holderTexture))
                    .put(TextureKey.CANDLE, Identifier.of("minecraft", "block/candle_lit"));

            unlitTextures = new TextureMap()
                    .put(HOLDER, Identifier.of("resprouted", "block/" + holderTexture))
                    .put(TextureKey.CANDLE, Identifier.of("minecraft", "block/candle"));
        } else {
            litTextures = new TextureMap()
                    .put(HOLDER, Identifier.of("resprouted", "block/" + holderTexture))
                    .put(TextureKey.CANDLE, Identifier.of("minecraft", "block/" + candleColor + "_candle_lit"));

            unlitTextures = new TextureMap()
                    .put(HOLDER, Identifier.of("resprouted", "block/" + holderTexture))
                    .put(TextureKey.CANDLE, Identifier.of("minecraft", "block/" + candleColor + "_candle"));
        }

        Identifier floorUnlitModel = TEMPLATE_CANDLE_HOLDER.upload(candleHolder, unlitTextures, blockStateModelGenerator.modelCollector);
        Identifier floorLitModel = TEMPLATE_CANDLE_HOLDER.upload(candleHolder, "_lit", litTextures, blockStateModelGenerator.modelCollector);
        Identifier wallUnlitModel = TEMPLATE_WALL_CANDLE_HOLDER.upload(Identifier.of(Resprouted.MOD_ID, "block/wall_" + getBlockName(candleHolder)), unlitTextures, blockStateModelGenerator.modelCollector);
        Identifier wallLitModel = TEMPLATE_WALL_CANDLE_HOLDER.upload(Identifier.of(Resprouted.MOD_ID, "block/wall_" + getBlockName(candleHolder) + "_lit"), litTextures, blockStateModelGenerator.modelCollector);

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

        registerCandleHolderItemModel(blockStateModelGenerator, candleHolder, holderTexture, candleColor);
    }

    private static void registerCandleHolderItemModel(BlockStateModelGenerator blockStateModelGenerator, Block candleHolder, String holderTexture, String candleColor) {

        Model itemModel = new Model(
                Optional.of(Identifier.of("minecraft", "item/generated")),
                Optional.empty(),
                TextureKey.LAYER0, TextureKey.LAYER1
        );

        TextureMap itemTextures;
        if (candleColor.isEmpty()) {
            itemTextures = new TextureMap()
                    .put(TextureKey.LAYER0, Identifier.of("minecraft", "item/candle"))
                    .put(TextureKey.LAYER1, Identifier.of("resprouted", "item/layer/" + holderTexture + "_layer"));
        } else {
            itemTextures = new TextureMap()
                    .put(TextureKey.LAYER0, Identifier.of("minecraft", "item/" + candleColor + "_candle"))
                    .put(TextureKey.LAYER1, Identifier.of("resprouted", "item/layer/" + holderTexture + "_layer"));
        }

        itemModel.upload(ModelIds.getItemModelId(candleHolder.asItem()), itemTextures, blockStateModelGenerator.modelCollector);
    }

    private static String getBlockName(Block block) {
        Identifier blockId = block.getRegistryEntry().registryKey().getValue();
        return blockId.getPath();
    }
}
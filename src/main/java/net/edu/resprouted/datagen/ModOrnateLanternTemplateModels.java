package net.edu.resprouted.datagen;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.custom.decorative.OrnateLanternBlock;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Optional;

public class ModOrnateLanternTemplateModels {
    public static final TextureKey LANTERN = TextureKey.of("lantern");
    public static final Model TEMPLATE_ORNATE_LANTERN = block("template_ornate_lantern", LANTERN);
    public static final Model TEMPLATE_ORNATE_HANGING_LANTERN = block("template_hanging_ornate_lantern", LANTERN);
    public static final Model TEMPLATE_ORNATE_WALL_LANTERN = block("template_wall_ornate_lantern", LANTERN);

    public static void registerOrnateLantern(Block lantern, BlockStateModelGenerator generator) {
        Identifier blockId = Registries.BLOCK.getId(lantern);
        String blockName = blockId.getPath();

        TextureMap textureMap = new TextureMap().put(LANTERN, TextureMap.getId(lantern));
        Identifier standingModel = TEMPLATE_ORNATE_LANTERN.upload(lantern, textureMap, generator.modelCollector);
        Identifier hangingModel = TEMPLATE_ORNATE_HANGING_LANTERN.upload(lantern, "_hanging", textureMap, generator.modelCollector);
        Identifier wallModelId = Identifier.of(blockId.getNamespace(), "block/wall_" + blockName);
        Identifier wallModel = TEMPLATE_ORNATE_WALL_LANTERN.upload(wallModelId, textureMap, generator.modelCollector);
        generator.registerItemModel(lantern.asItem());

        generator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(lantern)
                        .coordinate(BlockStateVariantMap.create(Properties.HANGING, OrnateLanternBlock.WALL, Properties.HORIZONTAL_FACING)
                                .register(false, false, Direction.NORTH, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, standingModel))
                                .register(false, false, Direction.SOUTH, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, standingModel))
                                .register(false, false, Direction.EAST, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, standingModel))
                                .register(false, false, Direction.WEST, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, standingModel))

                                .register(true, false, Direction.NORTH, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, hangingModel))
                                .register(true, false, Direction.SOUTH, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, hangingModel))
                                .register(true, false, Direction.EAST, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, hangingModel))
                                .register(true, false, Direction.WEST, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, hangingModel))

                                .register(false, true, Direction.NORTH, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, wallModel))

                                .register(false, true, Direction.SOUTH, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, wallModel)
                                        .put(VariantSettings.Y, VariantSettings.Rotation.R180))

                                .register(false, true, Direction.EAST, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, wallModel)
                                        .put(VariantSettings.Y, VariantSettings.Rotation.R90))

                                .register(false, true, Direction.WEST, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, wallModel)
                                        .put(VariantSettings.Y, VariantSettings.Rotation.R270))

                                .register(true, true, Direction.NORTH, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, wallModel))
                                .register(true, true, Direction.SOUTH, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, wallModel)
                                        .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                                .register(true, true, Direction.EAST, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, wallModel)
                                        .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                                .register(true, true, Direction.WEST, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, wallModel)
                                        .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        )
        );
    }

    private static Model block(String parent, TextureKey... requiredTextures) {
        return new Model(Optional.of(Identifier.of(Resprouted.MOD_ID, "block/" + parent)), Optional.empty(), requiredTextures);
    }
}
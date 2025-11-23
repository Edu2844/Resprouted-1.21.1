package net.edu.resprouted.datagen;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    public void generate(RecipeExporter exporter) {
        //CHAIRS
        Map<ItemConvertible, ItemConvertible> Chairs = Map.ofEntries(
                Map.entry(Blocks.OAK_PLANKS, ModBlocks.OAK_CHAIR),
                Map.entry(Blocks.DARK_OAK_PLANKS, ModBlocks.DARK_OAK_CHAIR),
                Map.entry(Blocks.MANGROVE_PLANKS, ModBlocks.MANGROVE_CHAIR),
                Map.entry(Blocks.CHERRY_PLANKS, ModBlocks.CHERRY_CHAIR),
                Map.entry(Blocks.BIRCH_PLANKS, ModBlocks.BIRCH_CHAIR),
                Map.entry(Blocks.SPRUCE_PLANKS, ModBlocks.SPRUCE_CHAIR),
                Map.entry(Blocks.ACACIA_PLANKS, ModBlocks.ACACIA_CHAIR),
                Map.entry(Blocks.JUNGLE_PLANKS, ModBlocks.JUNGLE_CHAIR),
                Map.entry(Blocks.BAMBOO_PLANKS, ModBlocks.BAMBOO_CHAIR),
                Map.entry(Blocks.CRIMSON_PLANKS, ModBlocks.CRIMSON_CHAIR),
                Map.entry(Blocks.WARPED_PLANKS, ModBlocks.WARPED_CHAIR),
                Map.entry(ModBlocks.IRONWOOD_PLANKS, ModBlocks.IRONWOOD_CHAIR),
                Map.entry(ModBlocks.OLIVE_PLANKS, ModBlocks.OLIVE_CHAIR)
        );
        Chairs.forEach((planks, chair) ->
                ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, chair, 4)
                        .pattern("P  ")
                        .pattern("PPP")
                        .pattern("S S")
                        .input('P', planks)
                        .input('S', Items.STICK)
                        .criterion("has_planks", conditionsFromItem(planks))
                        .offerTo(exporter));

        //TABLES
        Map<ItemConvertible, ItemConvertible> Tables = Map.ofEntries(
                Map.entry(Blocks.OAK_PLANKS, ModBlocks.OAK_TABLE),
                Map.entry(Blocks.DARK_OAK_PLANKS, ModBlocks.DARK_OAK_TABLE),
                Map.entry(Blocks.MANGROVE_PLANKS, ModBlocks.MANGROVE_TABLE),
                Map.entry(Blocks.CHERRY_PLANKS, ModBlocks.CHERRY_TABLE),
                Map.entry(Blocks.BIRCH_PLANKS, ModBlocks.BIRCH_TABLE),
                Map.entry(Blocks.SPRUCE_PLANKS, ModBlocks.SPRUCE_TABLE),
                Map.entry(Blocks.ACACIA_PLANKS, ModBlocks.ACACIA_TABLE),
                Map.entry(Blocks.JUNGLE_PLANKS, ModBlocks.JUNGLE_TABLE),
                Map.entry(Blocks.BAMBOO_PLANKS, ModBlocks.BAMBOO_TABLE),
                Map.entry(Blocks.CRIMSON_PLANKS, ModBlocks.CRIMSON_TABLE),
                Map.entry(Blocks.WARPED_PLANKS, ModBlocks.WARPED_TABLE),
                Map.entry(ModBlocks.IRONWOOD_PLANKS, ModBlocks.IRONWOOD_TABLE),
                Map.entry(ModBlocks.OLIVE_PLANKS, ModBlocks.OLIVE_TABLE)
        );
        Tables.forEach((planks, table) ->
                ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, table, 2)
                        .pattern("   ")
                        .pattern("PPP")
                        .pattern("S S")
                        .input('P', planks)
                        .input('S', Items.STICK)
                        .criterion("has_planks", conditionsFromItem(planks))
                        .offerTo(exporter));

        //STOOLS
        Map<ItemConvertible, ItemConvertible> Stools = Map.ofEntries(
                Map.entry(Blocks.OAK_SLAB, ModBlocks.OAK_STOOL),
                Map.entry(Blocks.DARK_OAK_SLAB, ModBlocks.DARK_OAK_STOOL),
                Map.entry(Blocks.MANGROVE_SLAB, ModBlocks.MANGROVE_STOOL),
                Map.entry(Blocks.CHERRY_SLAB, ModBlocks.CHERRY_STOOL),
                Map.entry(Blocks.BIRCH_SLAB, ModBlocks.BIRCH_STOOL),
                Map.entry(Blocks.SPRUCE_SLAB, ModBlocks.SPRUCE_STOOL),
                Map.entry(Blocks.ACACIA_SLAB, ModBlocks.ACACIA_STOOL),
                Map.entry(Blocks.JUNGLE_SLAB, ModBlocks.JUNGLE_STOOL),
                Map.entry(Blocks.BAMBOO_SLAB, ModBlocks.BAMBOO_STOOL),
                Map.entry(Blocks.CRIMSON_SLAB, ModBlocks.CRIMSON_STOOL),
                Map.entry(Blocks.WARPED_SLAB, ModBlocks.WARPED_STOOL),
                Map.entry(ModBlocks.IRONWOOD_SLAB, ModBlocks.IRONWOOD_STOOL),
                Map.entry(ModBlocks.OLIVE_SLAB, ModBlocks.OLIVE_STOOL)
        );
        Stools.forEach((slab, stool) ->
                ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, stool, 4)
                        .pattern("   ")
                        .pattern("PPP")
                        .pattern("S S")
                        .input('P', slab)
                        .input('S', Items.STICK)
                        .criterion("has_slab", conditionsFromItem(slab))
                        .offerTo(exporter));

        //IRON CANDLE HOLDERS
        Map<ItemConvertible, ItemConvertible> Iron_holder = Map.ofEntries(
                Map.entry(Blocks.BLACK_CANDLE, ModBlocks.BLACK_IRON_CANDLE_HOLDER),
                Map.entry(Blocks.BLUE_CANDLE, ModBlocks.BLUE_IRON_CANDLE_HOLDER),
                Map.entry(Blocks.BROWN_CANDLE, ModBlocks.BROWN_IRON_CANDLE_HOLDER),
                Map.entry(Blocks.CANDLE, ModBlocks.IRON_CANDLE_HOLDER),
                Map.entry(Blocks.CYAN_CANDLE, ModBlocks.CYAN_IRON_CANDLE_HOLDER),
                Map.entry(Blocks.GRAY_CANDLE, ModBlocks.GRAY_IRON_CANDLE_HOLDER),
                Map.entry(Blocks.GREEN_CANDLE, ModBlocks.GREEN_IRON_CANDLE_HOLDER),
                Map.entry(Blocks.LIGHT_BLUE_CANDLE, ModBlocks.LIGHT_BLUE_IRON_CANDLE_HOLDER),
                Map.entry(Blocks.LIGHT_GRAY_CANDLE, ModBlocks.LIGHT_GRAY_IRON_CANDLE_HOLDER),
                Map.entry(Blocks.LIME_CANDLE, ModBlocks.LIME_IRON_CANDLE_HOLDER),
                Map.entry(Blocks.MAGENTA_CANDLE, ModBlocks.MAGENTA_IRON_CANDLE_HOLDER),
                Map.entry(Blocks.ORANGE_CANDLE, ModBlocks.ORANGE_IRON_CANDLE_HOLDER),
                Map.entry(Blocks.PINK_CANDLE, ModBlocks.PINK_IRON_CANDLE_HOLDER),
                Map.entry(Blocks.PURPLE_CANDLE, ModBlocks.PURPLE_IRON_CANDLE_HOLDER),
                Map.entry(Blocks.RED_CANDLE, ModBlocks.RED_IRON_CANDLE_HOLDER),
                Map.entry(Blocks.WHITE_CANDLE, ModBlocks.WHITE_IRON_CANDLE_HOLDER),
                Map.entry(Blocks.YELLOW_CANDLE, ModBlocks.YELLOW_IRON_CANDLE_HOLDER)
        );
        Iron_holder.forEach((candle, iron_candle_holder) ->
                ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, iron_candle_holder, 4)
                        .pattern(" C ")
                        .pattern(" N ")
                        .pattern(" I ")
                        .input('C', candle)
                        .input('N', Items.IRON_NUGGET)
                        .input('I', Items.IRON_INGOT)
                        .criterion("has_candle", conditionsFromItem(candle))
                        .offerTo(exporter));

        //GOLDEN CANDLE HOLDERS
        Map<ItemConvertible, ItemConvertible> Golden_holder = Map.ofEntries(
                Map.entry(Blocks.BLACK_CANDLE, ModBlocks.BLACK_GOLDEN_CANDLE_HOLDER),
                Map.entry(Blocks.BLUE_CANDLE, ModBlocks.BLUE_GOLDEN_CANDLE_HOLDER),
                Map.entry(Blocks.BROWN_CANDLE, ModBlocks.BROWN_GOLDEN_CANDLE_HOLDER),
                Map.entry(Blocks.CANDLE, ModBlocks.GOLDEN_CANDLE_HOLDER),
                Map.entry(Blocks.CYAN_CANDLE, ModBlocks.CYAN_GOLDEN_CANDLE_HOLDER),
                Map.entry(Blocks.GRAY_CANDLE, ModBlocks.GRAY_GOLDEN_CANDLE_HOLDER),
                Map.entry(Blocks.GREEN_CANDLE, ModBlocks.GREEN_GOLDEN_CANDLE_HOLDER),
                Map.entry(Blocks.LIGHT_BLUE_CANDLE, ModBlocks.LIGHT_BLUE_GOLDEN_CANDLE_HOLDER),
                Map.entry(Blocks.LIGHT_GRAY_CANDLE, ModBlocks.LIGHT_GRAY_GOLDEN_CANDLE_HOLDER),
                Map.entry(Blocks.LIME_CANDLE, ModBlocks.LIME_GOLDEN_CANDLE_HOLDER),
                Map.entry(Blocks.MAGENTA_CANDLE, ModBlocks.MAGENTA_GOLDEN_CANDLE_HOLDER),
                Map.entry(Blocks.ORANGE_CANDLE, ModBlocks.ORANGE_GOLDEN_CANDLE_HOLDER),
                Map.entry(Blocks.PINK_CANDLE, ModBlocks.PINK_GOLDEN_CANDLE_HOLDER),
                Map.entry(Blocks.PURPLE_CANDLE, ModBlocks.PURPLE_GOLDEN_CANDLE_HOLDER),
                Map.entry(Blocks.RED_CANDLE, ModBlocks.RED_GOLDEN_CANDLE_HOLDER),
                Map.entry(Blocks.WHITE_CANDLE, ModBlocks.WHITE_GOLDEN_CANDLE_HOLDER),
                Map.entry(Blocks.YELLOW_CANDLE, ModBlocks.YELLOW_GOLDEN_CANDLE_HOLDER)
        );
        Golden_holder.forEach((candle1, golden_candle_holder) ->
                ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, golden_candle_holder, 4)
                        .pattern(" C ")
                        .pattern(" N ")
                        .pattern(" I ")
                        .input('C', candle1)
                        .input('N', Items.GOLD_NUGGET)
                        .input('I', Items.GOLD_INGOT)
                        .criterion("has_candle", conditionsFromItem(candle1))
                        .offerTo(exporter));


        //GOLDEN CANDLE HOLDERS
        Map<ItemConvertible, ItemConvertible> Copper_holder = Map.ofEntries(
                Map.entry(Blocks.BLACK_CANDLE, ModBlocks.BLACK_COPPER_CANDLE_HOLDER),
                Map.entry(Blocks.BLUE_CANDLE, ModBlocks.BLUE_COPPER_CANDLE_HOLDER),
                Map.entry(Blocks.BROWN_CANDLE, ModBlocks.BROWN_COPPER_CANDLE_HOLDER),
                Map.entry(Blocks.CANDLE, ModBlocks.COPPER_CANDLE_HOLDER),
                Map.entry(Blocks.CYAN_CANDLE, ModBlocks.CYAN_COPPER_CANDLE_HOLDER),
                Map.entry(Blocks.GRAY_CANDLE, ModBlocks.GRAY_COPPER_CANDLE_HOLDER),
                Map.entry(Blocks.GREEN_CANDLE, ModBlocks.GREEN_COPPER_CANDLE_HOLDER),
                Map.entry(Blocks.LIGHT_BLUE_CANDLE, ModBlocks.LIGHT_BLUE_COPPER_CANDLE_HOLDER),
                Map.entry(Blocks.LIGHT_GRAY_CANDLE, ModBlocks.LIGHT_GRAY_COPPER_CANDLE_HOLDER),
                Map.entry(Blocks.LIME_CANDLE, ModBlocks.LIME_COPPER_CANDLE_HOLDER),
                Map.entry(Blocks.MAGENTA_CANDLE, ModBlocks.MAGENTA_COPPER_CANDLE_HOLDER),
                Map.entry(Blocks.ORANGE_CANDLE, ModBlocks.ORANGE_COPPER_CANDLE_HOLDER),
                Map.entry(Blocks.PINK_CANDLE, ModBlocks.PINK_COPPER_CANDLE_HOLDER),
                Map.entry(Blocks.PURPLE_CANDLE, ModBlocks.PURPLE_COPPER_CANDLE_HOLDER),
                Map.entry(Blocks.RED_CANDLE, ModBlocks.RED_COPPER_CANDLE_HOLDER),
                Map.entry(Blocks.WHITE_CANDLE, ModBlocks.WHITE_COPPER_CANDLE_HOLDER),
                Map.entry(Blocks.YELLOW_CANDLE, ModBlocks.YELLOW_COPPER_CANDLE_HOLDER)
        );
        Copper_holder.forEach((candle2, copper_candle_holder) ->
                ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, copper_candle_holder, 4)
                        .pattern(" C ")
                        .pattern(" N ")
                        .pattern(" I ")
                        .input('C', candle2)
                        .input('N', ModItems.COPPER_NUGGET)
                        .input('I', Items.COPPER_INGOT)
                        .criterion("has_candle", conditionsFromItem(candle2))
                        .offerTo(exporter));
    }
}

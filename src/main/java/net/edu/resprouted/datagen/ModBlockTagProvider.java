package net.edu.resprouted.datagen;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                //SLATE
                .add(ModBlocks.SLATE)
                .add(ModBlocks.POLISHED_SLATE)
                .add(ModBlocks.POLISHED_SLATE_SLAB)
                .add(ModBlocks.POLISHED_SLATE_STAIRS)
                .add(ModBlocks.POLISHED_SLATE_WALL)
                .add(ModBlocks.CHISELED_SLATE)
                .add(ModBlocks.SLATE_BRICKS)
                .add(ModBlocks.SLATE_BRICK_SLAB)
                .add(ModBlocks.SLATE_BRICK_STAIRS)
                .add(ModBlocks.SLATE_BRICK_WALL)
                .add(ModBlocks.SLATE_ROOFS)
                .add(ModBlocks.SLATE_ROOF_SLAB)
                .add(ModBlocks.SLATE_ROOF_STAIRS)
                .add(ModBlocks.SLATE_PILLAR)

                //ANDESITE
                .add(ModBlocks.ANDESITE_BRICKS)
                .add(ModBlocks.CRACKED_ANDESITE_BRICKS)
                .add(ModBlocks.CHISELED_ANDESITE)
                .add(ModBlocks.ANDESITE_PILLAR)

                //DIORITE
                .add(ModBlocks.DIORITE_BRICKS)
                .add(ModBlocks.CRACKED_DIORITE_BRICKS)
                .add(ModBlocks.CHISELED_DIORITE)
                .add(ModBlocks.DIORITE_PILLAR)

                //GRANITE
                .add(ModBlocks.GRANITE_BRICKS)
                .add(ModBlocks.CRACKED_GRANITE_BRICKS)
                .add(ModBlocks.CHISELED_GRANITE)
                .add(ModBlocks.GRANITE_PILLAR)

                //STONE
                .add(ModBlocks.POLISHED_STONE)
                .add(ModBlocks.STONE_PILLAR)

                //SANDSTONE
                .add(ModBlocks.POLISHED_SANDSTONE)
                .add(ModBlocks.SANDSTONE_BRICKS)
                .add(ModBlocks.SANDSTONE_PILLAR)

                //RED SANDSTONE
                .add(ModBlocks.POLISHED_RED_SANDSTONE)
                .add(ModBlocks.RED_SANDSTONE_BRICKS)
                .add(ModBlocks.RED_SANDSTONE_PILLAR)

                //WROUGHT IRON
                .add(ModBlocks.WROUGHT_IRON_BLOCK)
                .add(ModBlocks.CUT_WROUGHT_IRON_BLOCK)

                //CHAINS
                .add(ModBlocks.GOLDEN_CHAIN)
                .add(ModBlocks.COPPER_CHAIN)
                .add(ModBlocks.EXPOSED_COPPER_CHAIN)
                .add(ModBlocks.WEATHERED_COPPER_CHAIN)
                .add(ModBlocks.OXIDIZED_COPPER_CHAIN)
                .add(ModBlocks.WAXED_COPPER_CHAIN)
                .add(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN)
                .add(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN)
                .add(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN)

                //CHANDELIER
                .add(ModBlocks.CHANDELIER)
                .add(ModBlocks.GOLDEN_CHANDELIER)
                .add(ModBlocks.COPPER_CHANDELIER)
                .add(ModBlocks.EXPOSED_COPPER_CHANDELIER)
                .add(ModBlocks.WEATHERED_COPPER_CHANDELIER)
                .add(ModBlocks.OXIDIZED_COPPER_CHANDELIER)
                .add(ModBlocks.WAXED_COPPER_CHANDELIER)
                .add(ModBlocks.WAXED_EXPOSED_COPPER_CHANDELIER)
                .add(ModBlocks.WAXED_WEATHERED_COPPER_CHANDELIER)
                .add(ModBlocks.WAXED_OXIDIZED_COPPER_CHANDELIER)

                //LANTERNS
                .add(ModBlocks.GOLDEN_LANTERN)
                .add(ModBlocks.GOLDEN_SOUL_LANTERN)
                .add(ModBlocks.COPPER_LANTERN)
                .add(ModBlocks.EXPOSED_COPPER_LANTERN)
                .add(ModBlocks.WEATHERED_COPPER_LANTERN)
                .add(ModBlocks.OXIDIZED_COPPER_LANTERN)
                .add(ModBlocks.WAXED_COPPER_LANTERN)
                .add(ModBlocks.WAXED_EXPOSED_COPPER_LANTERN)
                .add(ModBlocks.WAXED_WEATHERED_COPPER_LANTERN)
                .add(ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN)

                .add(ModBlocks.COPPER_SOUL_LANTERN)
                .add(ModBlocks.EXPOSED_COPPER_SOUL_LANTERN)
                .add(ModBlocks.WEATHERED_COPPER_SOUL_LANTERN)
                .add(ModBlocks.OXIDIZED_COPPER_SOUL_LANTERN)
                .add(ModBlocks.WAXED_COPPER_SOUL_LANTERN)
                .add(ModBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN)
                .add(ModBlocks.WAXED_WEATHERED_COPPER_SOUL_LANTERN)
                .add(ModBlocks.WAXED_OXIDIZED_COPPER_SOUL_LANTERN)

                .add(ModBlocks.EVAPORATING_BASIN)

                //ALCHEMY
                .add(ModBlocks.CONDENSER)
                .add(ModBlocks.RETORT)

                .add(ModBlocks.IRON_CANDLE_HOLDER)

        ;
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                //IRONWOOD
                .add(ModBlocks.IRONWOOD_LOG)
                .add(ModBlocks.STRIPPED_IRONWOOD_LOG)
                .add(ModBlocks.IRONWOOD_PLANKS)
                .add(ModBlocks.IRONWOOD_FENCE)
                .add(ModBlocks.IRONWOOD_FENCE_GATE)
                .add(ModBlocks.IRONWOOD_STAIRS)
                .add(ModBlocks.IRONWOOD_SLAB)
                .add(ModBlocks.IRONWOOD_DOOR)
                .add(ModBlocks.IRONWOOD_TRAPDOOR)
                .add(ModBlocks.IRONWOOD_PRESSURE_PLATE)
                .add(ModBlocks.IRONWOOD_BUTTON)
                .add(ModBlocks.IRONWOOD_SIGN)
                .add(ModBlocks.IRONWOOD_WALL_SIGN)
                .add(ModBlocks.IRONWOOD_HANGING_SIGN)
                .add(ModBlocks.IRONWOOD_WALL_HANGING_SIGN)

                //OLIVE
                .add(ModBlocks.OLIVE_LOG)
                .add(ModBlocks.STRIPPED_OLIVE_LOG)
                .add(ModBlocks.OLIVE_PLANKS)
                .add(ModBlocks.OLIVE_FENCE)
                .add(ModBlocks.OLIVE_FENCE_GATE)
                .add(ModBlocks.OLIVE_STAIRS)
                .add(ModBlocks.OLIVE_SLAB)
                .add(ModBlocks.OLIVE_DOOR)
                .add(ModBlocks.OLIVE_TRAPDOOR)
                .add(ModBlocks.OLIVE_PRESSURE_PLATE)
                .add(ModBlocks.OLIVE_BUTTON)
                .add(ModBlocks.OLIVE_HANGING_SIGN)
                .add(ModBlocks.OLIVE_WALL_HANGING_SIGN)
                .add(ModBlocks.OLIVE_SIGN)
                .add(ModBlocks.OLIVE_WALL_SIGN)

                //CHAIRS
                .add(ModBlocks.OAK_CHAIR)
                .add(ModBlocks.DARK_OAK_CHAIR)
                .add(ModBlocks.BIRCH_CHAIR)
                .add(ModBlocks.SPRUCE_CHAIR)
                .add(ModBlocks.ACACIA_CHAIR)
                .add(ModBlocks.JUNGLE_CHAIR)
                .add(ModBlocks.BAMBOO_CHAIR)
                .add(ModBlocks.CRIMSON_CHAIR)
                .add(ModBlocks.WARPED_CHAIR)
                .add(ModBlocks.IRONWOOD_CHAIR)
                .add(ModBlocks.OLIVE_CHAIR)

                //STOOLS
                .add(ModBlocks.OAK_STOOL)

                //TABLE
                .add(ModBlocks.OAK_TABLE)
                .add(ModBlocks.DARK_OAK_TABLE)
                .add(ModBlocks.MANGROVE_TABLE)
                .add(ModBlocks.BIRCH_TABLE)
                .add(ModBlocks.SPRUCE_TABLE)
                .add(ModBlocks.ACACIA_TABLE)
                .add(ModBlocks.JUNGLE_TABLE)
                .add(ModBlocks.BAMBOO_TABLE)
                .add(ModBlocks.CRIMSON_TABLE)
                .add(ModBlocks.WARPED_TABLE)
                .add(ModBlocks.IRONWOOD_TABLE)
                .add(ModBlocks.OLIVE_TABLE)

                //PAINTED PLANKS
                .add(ModBlocks.BLACK_PAINTED_PLANKS)
                .add(ModBlocks.BLUE_PAINTED_PLANKS)
                .add(ModBlocks.BROWN_PAINTED_PLANKS)
                .add(ModBlocks.CYAN_PAINTED_PLANKS)
                .add(ModBlocks.GRAY_PAINTED_PLANKS)
                .add(ModBlocks.GREEN_PAINTED_PLANKS)
                .add(ModBlocks.LIGHT_BLUE_PAINTED_PLANKS)
                .add(ModBlocks.LIME_PAINTED_PLANKS)
                .add(ModBlocks.MAGENTA_PAINTED_PLANKS)
                .add(ModBlocks.ORANGE_PAINTED_PLANKS)
                .add(ModBlocks.PINK_PAINTED_PLANKS)
                .add(ModBlocks.PURPLE_PAINTED_PLANKS)
                .add(ModBlocks.RED_PAINTED_PLANKS)
                .add(ModBlocks.LIGHT_GRAY_PAINTED_PLANKS)
                .add(ModBlocks.WHITE_PAINTED_PLANKS)
                .add(ModBlocks.YELLOW_PAINTED_PLANKS)

                //AGRICULTURE
                .add(ModBlocks.STAKE)
                .add(ModBlocks.CRUSHING_TUB)
                .add(ModBlocks.LIQUID_BARREL)

        ;
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.FERTILE_SOIL)
                .add(ModBlocks.CLAY_WALL)
                .add(ModBlocks.CLAY_WALL_CROSS)
                .add(ModBlocks.CLAY_WALL_DIAGONAL)
        ;
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                //IRONWOOD
                .add(ModBlocks.IRONWOOD_LOG)
                .add(ModBlocks.IRONWOOD_PLANKS)
                .add(ModBlocks.STRIPPED_IRONWOOD_LOG)
                //OLIVE
                .add(ModBlocks.OLIVE_LOG)
                .add(ModBlocks.OLIVE_WOOD)
                .add(ModBlocks.STRIPPED_OLIVE_LOG)
        ;
        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.IRONWOOD_FENCE)
                .add(ModBlocks.OLIVE_FENCE);

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.IRONWOOD_FENCE_GATE)
                .add(ModBlocks.OLIVE_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.POLISHED_SLATE_WALL)
                .add(ModBlocks.SLATE_BRICK_WALL)
        ;
        getOrCreateTagBuilder(BlockTags.CLIMBABLE)
                .add(ModBlocks.ROPE)
        ;
        getOrCreateTagBuilder(BlockTags.BAMBOO_PLANTABLE_ON)
                .add(ModBlocks.FERTILE_SOIL)
        ;
        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.IRONWOOD_LEAVES)
                .add(ModBlocks.OLIVE_LEAVES)
                .add(ModBlocks.APPLE_LEAVES)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.FERTILE_SOILS)
                .add(ModBlocks.FERTILE_SOIL)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.ALLOWS_GROWTH)
                .add(Blocks.DIRT)
                .add(Blocks.GRASS_BLOCK)
                .add(Blocks.PODZOL)
                .add(Blocks.FARMLAND)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.EVAPORATING_BOOSTERS)
                .add(Blocks.MAGMA_BLOCK)
                .add(Blocks.FIRE)
        ;

    }
}

package net.edu.resprouted.block;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.custom.agriculture.*;
import net.edu.resprouted.block.custom.agriculture.CustomCakeBlock;
import net.edu.resprouted.block.custom.alchemy.AdvancedCondenserBlock;
import net.edu.resprouted.block.custom.alchemy.AdvancedRetortBlock;
import net.edu.resprouted.block.custom.alchemy.BasicCondenserBlock;
import net.edu.resprouted.block.custom.alchemy.RetortBlock;
import net.edu.resprouted.block.custom.decorative.*;
import net.edu.resprouted.block.interfaces.LuminousFluidStorage;
import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.registry.ResproutedBlockSetTypes;
import net.edu.resprouted.registry.ResproutedWoodTypes;
import net.edu.resprouted.util.misc.CabinetRegistry;
import net.edu.resprouted.world.tree.ModSaplingGenerators;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.edu.resprouted.block.custom.alchemy.BasicCondenserBlock.LIT;

public class ModBlocks {
    // =================================================
    // ||                   SLATE                     ||
    // =================================================
    public static final Block SLATE = registerBlock("slate",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block COBBLED_SLATE = registerBlock("cobbled_slate",
            new Block(AbstractBlock.Settings.copy(Blocks.COBBLESTONE).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block COBBLED_SLATE_WALL = registerBlock("cobbled_slate_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(2f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block COBBLED_SLATE_STAIRS = registerBlock("cobbled_slate_stairs",
            new StairsBlock(ModBlocks.COBBLED_SLATE.getDefaultState(),AbstractBlock.Settings.copy(Blocks.COBBLESTONE_STAIRS).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block COBBLED_SLATE_SLAB = registerBlock("cobbled_slate_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(2f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block POLISHED_SLATE = registerBlock("polished_slate",
            new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE).sounds(BlockSoundGroup.POLISHED_DEEPSLATE)));
    public static final Block POLISHED_SLATE_WALL = registerBlock("polished_slate_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(2f).requiresTool().sounds(BlockSoundGroup.POLISHED_DEEPSLATE)));
    public static final Block POLISHED_SLATE_STAIRS = registerBlock("polished_slate_stairs",
            new StairsBlock(ModBlocks.POLISHED_SLATE.getDefaultState(),AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE).sounds(BlockSoundGroup.POLISHED_DEEPSLATE)));
    public static final Block POLISHED_SLATE_SLAB = registerBlock("polished_slate_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(2f).requiresTool().sounds(BlockSoundGroup.POLISHED_DEEPSLATE)));
    public static final Block SLATE_BRICKS = registerBlock("slate_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
    public static final Block SLATE_BRICK_WALL = registerBlock("slate_brick_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
    public static final Block SLATE_BRICK_STAIRS = registerBlock("slate_brick_stairs",
            new StairsBlock(ModBlocks.SLATE_BRICKS.getDefaultState(),AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
    public static final Block SLATE_BRICK_SLAB = registerBlock("slate_brick_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
    public static final Block SLATE_ROOFS = registerBlock("slate_roofs",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
    public static final Block SLATE_ROOF_STAIRS = registerBlock("slate_roof_stairs",
            new StairsBlock(ModBlocks.SLATE_ROOFS.getDefaultState(),AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
    public static final Block SLATE_ROOF_SLAB = registerBlock("slate_roof_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
    public static final Block CHISELED_SLATE = registerBlock("chiseled_slate",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
    public static final Block SLATE_PILLAR = registerBlock("slate_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));

    // =================================================
    // ||                 ANDESITE                    ||
    // =================================================
    public static final Block ANDESITE_BRICKS = registerVanillaVariationBlock("andesite_bricks",
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block ANDESITE_BRICK_WALL = registerVanillaVariationWall("andesite_brick_wall",
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block ANDESITE_BRICK_STAIRS = registerVanillaVariationStairs("andesite_brick_stairs",
            ANDESITE_BRICKS, Blocks.STONE_BRICKS);
    public static final Block ANDESITE_BRICK_SLAB = registerVanillaVariationSlab("andesite_brick_slab",
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block CRACKED_ANDESITE_BRICKS = registerVanillaVariationBlock("cracked_andesite_bricks",
            AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS));
    public static final Block CHISELED_ANDESITE = registerVanillaVariationBlock("chiseled_andesite_bricks",
            AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS));
    public static final Block ANDESITE_PILLAR = registerVanillaVariationPillar("andesite_pillar",
            AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS));

    // =================================================
    // ||                  DIORITE                    ||
    // =================================================
    public static final Block DIORITE_BRICKS = registerVanillaVariationBlock("diorite_bricks",
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block DIORITE_BRICK_WALL = registerVanillaVariationWall("diorite_brick_wall",
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block DIORITE_BRICK_STAIRS = registerVanillaVariationStairs("diorite_brick_stairs",
            DIORITE_BRICKS, Blocks.STONE_BRICKS);
    public static final Block DIORITE_BRICK_SLAB = registerVanillaVariationSlab("diorite_brick_slab",
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block CRACKED_DIORITE_BRICKS = registerVanillaVariationBlock("cracked_diorite_bricks",
            AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS));
    public static final Block CHISELED_DIORITE = registerVanillaVariationBlock("chiseled_diorite_bricks",
            AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS));
    public static final Block DIORITE_PILLAR = registerVanillaVariationPillar("diorite_pillar",
            AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS));

    // =================================================
    // ||                  GRANITE                    ||
    // =================================================
    public static final Block GRANITE_BRICKS = registerVanillaVariationBlock("granite_bricks",
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block GRANITE_BRICK_WALL = registerVanillaVariationWall("granite_brick_wall",
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block GRANITE_BRICK_STAIRS = registerVanillaVariationStairs("granite_brick_stairs",
            GRANITE_BRICKS, Blocks.STONE_BRICKS);
    public static final Block GRANITE_BRICK_SLAB = registerVanillaVariationSlab("granite_brick_slab",
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block CRACKED_GRANITE_BRICKS = registerVanillaVariationBlock("cracked_granite_bricks",
            AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS));
    public static final Block CHISELED_GRANITE = registerVanillaVariationBlock("chiseled_granite_bricks",
            AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS));
    public static final Block GRANITE_PILLAR = registerVanillaVariationPillar("granite_pillar",
            AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS));

    // =================================================
    // ||                   STONE                     ||
    // =================================================
    public static final Block POLISHED_STONE = registerVanillaVariationBlock("polished_stone",
            AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE));
    public static final Block POLISHED_STONE_WALL = registerVanillaVariationWall("polished_stone_wall",
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block POLISHED_STONE_STAIRS = registerVanillaVariationStairs("polished_stone_stairs",
            POLISHED_STONE, Blocks.POLISHED_ANDESITE);
    public static final Block POLISHED_STONE_SLAB = registerVanillaVariationSlab("polished_stone_slab",
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block STONE_PILLAR = registerVanillaVariationPillar("stone_pillar",
            AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS));

    // =================================================
    // ||                SANDSTONE                    ||
    // =================================================
    public static final Block SANDSTONE_BRICKS = registerVanillaVariationBlock("sandstone_bricks",
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block SANDSTONE_BRICK_WALL = registerVanillaVariationWall("sandstone_brick_wall",
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block SANDSTONE_BRICK_STAIRS = registerVanillaVariationStairs("sandstone_brick_stairs",
            SANDSTONE_BRICKS, Blocks.STONE_BRICKS);
    public static final Block SANDSTONE_BRICK_SLAB = registerVanillaVariationSlab("sandstone_brick_slab",
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block SANDSTONE_PILLAR = registerVanillaVariationPillar("sandstone_pillar",
            AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS));

    // =================================================
    // ||                RED SANDSTONE                ||
    // =================================================
    public static final Block RED_SANDSTONE_BRICKS = registerVanillaVariationBlock("red_sandstone_bricks",
            AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE));
    public static final Block RED_SANDSTONE_BRICK_WALL = registerVanillaVariationWall("red_sandstone_brick_wall",
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block RED_SANDSTONE_BRICK_STAIRS = registerVanillaVariationStairs("red_sandstone_brick_stairs",
            RED_SANDSTONE_BRICKS, Blocks.STONE_BRICKS);
    public static final Block RED_SANDSTONE_BRICK_SLAB = registerVanillaVariationSlab("red_sandstone_brick_slab",
            AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
    public static final Block RED_SANDSTONE_PILLAR = registerVanillaVariationPillar("red_sandstone_pillar",
            AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS));

    // =================================================
    // ||                 WROUGHT IRON                ||
    // =================================================
    public static final Block WROUGHT_IRON_BLOCK = registerBlock("wrought_iron_block", new Block(AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK)));
    public static final Block CUT_WROUGHT_IRON_BLOCK = registerBlock("cut_wrought_iron_block", new Block(AbstractBlock.Settings.copy(Blocks.CUT_COPPER)));
    public static final Block CUT_WROUGHT_IRON_STAIRS = registerBlock("cut_wrought_iron_stairs", new StairsBlock(ModBlocks.CUT_WROUGHT_IRON_BLOCK.getDefaultState(),AbstractBlock.Settings.copy(Blocks.CUT_COPPER_STAIRS)));
    public static final Block CUT_WROUGHT_IRON_SLAB = registerBlock("cut_wrought_iron_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CUT_COPPER_SLAB)));
    public static final Block WROUGHT_IRON_BULB = registerBlock("wrought_iron_bulb", new BulbBlock(AbstractBlock.Settings.copy(Blocks.COPPER_BULB)));
    public static final Block WROUGHT_IRON_BARS = registerBlock("wrought_iron_bars", new PaneBlock(AbstractBlock.Settings.copy(Blocks.IRON_BARS)));

    // =================================================
    // ||                  CLAY WALL                  ||
    // =================================================
    public static final Block CLAY_WALL = registerClayWall("clay_wall", false);
    public static final Block CLAY_WALL_CROSS = registerClayWall("clay_wall_cross", false);
    public static final Block CLAY_WALL_DIAGONAL = registerClayWall("clay_wall_diagonal", true);

    // =================================================
    // ||                  IRONWOOD                   ||
    // =================================================
    public static final Block IRONWOOD_LEAVES = registerBlock("ironwood_leaves", new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block IRONWOOD_LOG = registerBlock("ironwood_log", new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block IRONWOOD_WOOD = registerBlock("ironwood_wood", new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_IRONWOOD_LOG = registerBlock("stripped_ironwood_log", new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block STRIPPED_IRONWOOD_WOOD = registerBlock("stripped_ironwood_wood", new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block IRONWOOD_PLANKS = registerBlock("ironwood_planks", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block IRONWOOD_SAPLING = registerBlock("ironwood_sapling", new SaplingBlock(ModSaplingGenerators.IRONWOOD, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
    public static final Block IRONWOOD_STAIRS = registerBlock("ironwood_stairs", new StairsBlock(ModBlocks.IRONWOOD_PLANKS.getDefaultState(), AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block IRONWOOD_SLAB = registerBlock("ironwood_slab", new SlabBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block IRONWOOD_BUTTON = registerBlock("ironwood_button", new ButtonBlock(ResproutedBlockSetTypes.IRONWOOD, 30, AbstractBlock.Settings.create().strength(2f).requiresTool().noCollision()));
    public static final Block IRONWOOD_PRESSURE_PLATE = registerBlock("ironwood_pressure_plate", new PressurePlateBlock(ResproutedBlockSetTypes.IRONWOOD, AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block IRONWOOD_FENCE = registerBlock("ironwood_fence", new FenceBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block IRONWOOD_FENCE_GATE = registerBlock("ironwood_fence_gate", new FenceGateBlock(ResproutedWoodTypes.IRONWOOD, AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block IRONWOOD_DOOR = registerBlock("ironwood_door", new DoorBlock(ResproutedBlockSetTypes.IRONWOOD, AbstractBlock.Settings.create().strength(2f).requiresTool().nonOpaque()));
    public static final Block IRONWOOD_TRAPDOOR = registerBlock("ironwood_trapdoor", new TrapdoorBlock(ResproutedBlockSetTypes.IRONWOOD, AbstractBlock.Settings.create().strength(2f).requiresTool().nonOpaque()));
    public static final Block IRONWOOD_SIGN = registerBlockWithoutBlockItem("ironwood_sign", new SignBlock(ResproutedWoodTypes.IRONWOOD, AbstractBlock.Settings.copy(Blocks.OAK_SIGN).strength(1.0f, 5.0f).mapColor(MapColor.TERRACOTTA_WHITE)));
    public static final Block IRONWOOD_WALL_SIGN = registerBlockWithoutBlockItem("ironwood_wall_sign", new WallSignBlock(ResproutedWoodTypes.IRONWOOD, AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN).strength(1.0f, 5.0f).mapColor(MapColor.TERRACOTTA_WHITE).dropsLike(ModBlocks.IRONWOOD_SIGN)));
    public static final Block IRONWOOD_HANGING_SIGN = registerBlockWithoutBlockItem("ironwood_hanging_sign", new HangingSignBlock(ResproutedWoodTypes.IRONWOOD, AbstractBlock.Settings.copy(Blocks.OAK_HANGING_SIGN).strength(1.0f, 5.0f).mapColor(MapColor.TERRACOTTA_WHITE)));
    public static final Block IRONWOOD_WALL_HANGING_SIGN = registerBlockWithoutBlockItem("ironwood_wall_hanging_sign", new WallHangingSignBlock(ResproutedWoodTypes.IRONWOOD, AbstractBlock.Settings.copy(Blocks.OAK_WALL_HANGING_SIGN).strength(1.0f, 5.0f).mapColor(MapColor.TERRACOTTA_WHITE).dropsLike(ModBlocks.IRONWOOD_HANGING_SIGN)));

    // =================================================
    // ||                    OLIVE                    ||
    // =================================================
    public static final Block OLIVE_LEAVES = registerBlock("olive_leaves", new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block OLIVE_LOG = registerBlock("olive_log", new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block STRIPPED_OLIVE_WOOD = registerBlock("stripped_olive_wood", new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block OLIVE_WOOD = registerBlock("olive_wood", new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_OLIVE_LOG = registerBlock("stripped_olive_log", new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block OLIVE_PLANKS = registerBlock("olive_planks", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block OLIVE_SAPLING = registerBlock("olive_sapling", new SaplingBlock(ModSaplingGenerators.OLIVE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
    public static final Block OLIVE_STAIRS = registerBlock("olive_stairs", new StairsBlock(ModBlocks.OLIVE_PLANKS.getDefaultState(), AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block OLIVE_SLAB = registerBlock("olive_slab", new SlabBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block OLIVE_BUTTON = registerBlock("olive_button", new ButtonBlock(ResproutedBlockSetTypes.OLIVE, 30, AbstractBlock.Settings.create().strength(2f).requiresTool().noCollision()));
    public static final Block OLIVE_PRESSURE_PLATE = registerBlock("olive_pressure_plate", new PressurePlateBlock(ResproutedBlockSetTypes.OLIVE, AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block OLIVE_FENCE = registerBlock("olive_fence", new FenceBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block OLIVE_FENCE_GATE = registerBlock("olive_fence_gate", new FenceGateBlock(ResproutedWoodTypes.OLIVE, AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block OLIVE_DOOR = registerBlock("olive_door", new DoorBlock(ResproutedBlockSetTypes.OLIVE, AbstractBlock.Settings.create().strength(2f).requiresTool().nonOpaque()));
    public static final Block OLIVE_TRAPDOOR = registerBlock("olive_trapdoor", new TrapdoorBlock(ResproutedBlockSetTypes.OLIVE, AbstractBlock.Settings.create().strength(2f).requiresTool().nonOpaque()));
    public static final Block OLIVE_SIGN = registerBlockWithoutBlockItem("olive_sign", new SignBlock(ResproutedWoodTypes.OLIVE, AbstractBlock.Settings.copy(Blocks.OAK_SIGN).strength(1.0f, 5.0f).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block OLIVE_WALL_SIGN = registerBlockWithoutBlockItem("olive_wall_sign", new WallSignBlock(ResproutedWoodTypes.OLIVE, AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN).strength(1.0f, 5.0f).mapColor(MapColor.TERRACOTTA_ORANGE).dropsLike(ModBlocks.OLIVE_SIGN)));
    public static final Block OLIVE_HANGING_SIGN = registerBlockWithoutBlockItem("olive_hanging_sign", new HangingSignBlock(ResproutedWoodTypes.OLIVE, AbstractBlock.Settings.copy(Blocks.OAK_HANGING_SIGN).strength(1.0f, 5.0f).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block OLIVE_WALL_HANGING_SIGN = registerBlockWithoutBlockItem("olive_wall_hanging_sign", new WallHangingSignBlock(ResproutedWoodTypes.OLIVE, AbstractBlock.Settings.copy(Blocks.OAK_WALL_HANGING_SIGN).strength(1.0f, 5.0f).mapColor(MapColor.TERRACOTTA_ORANGE).dropsLike(ModBlocks.OLIVE_HANGING_SIGN)));

    // =================================================
    // ||               PAINTED PLANKS                ||
    // =================================================
    public static final Block BLACK_PAINTED_PLANKS = registerPaintedPlank("black_painted_planks", MapColor.BLACK);
    public static final Block BLUE_PAINTED_PLANKS = registerPaintedPlank("blue_painted_planks", MapColor.BLUE);
    public static final Block BROWN_PAINTED_PLANKS = registerPaintedPlank("brown_painted_planks", MapColor.BROWN);
    public static final Block CYAN_PAINTED_PLANKS = registerPaintedPlank("cyan_painted_planks", MapColor.CYAN);
    public static final Block GRAY_PAINTED_PLANKS = registerPaintedPlank("gray_painted_planks", MapColor.GRAY);
    public static final Block GREEN_PAINTED_PLANKS = registerPaintedPlank("green_painted_planks", MapColor.GREEN);
    public static final Block LIGHT_BLUE_PAINTED_PLANKS = registerPaintedPlank("light_blue_painted_planks", MapColor.LIGHT_BLUE);
    public static final Block LIME_PAINTED_PLANKS = registerPaintedPlank("lime_painted_planks", MapColor.LIME);
    public static final Block MAGENTA_PAINTED_PLANKS = registerPaintedPlank("magenta_painted_planks", MapColor.MAGENTA);
    public static final Block ORANGE_PAINTED_PLANKS = registerPaintedPlank("orange_painted_planks", MapColor.ORANGE);
    public static final Block PINK_PAINTED_PLANKS = registerPaintedPlank("pink_painted_planks", MapColor.PINK);
    public static final Block PURPLE_PAINTED_PLANKS = registerPaintedPlank("purple_painted_planks", MapColor.PURPLE);
    public static final Block RED_PAINTED_PLANKS = registerPaintedPlank("red_painted_planks", MapColor.RED);
    public static final Block LIGHT_GRAY_PAINTED_PLANKS = registerPaintedPlank("light_gray_painted_planks", MapColor.LIGHT_GRAY);
    public static final Block WHITE_PAINTED_PLANKS = registerPaintedPlank("white_painted_planks", MapColor.WHITE);
    public static final Block YELLOW_PAINTED_PLANKS = registerPaintedPlank("yellow_painted_planks", MapColor.YELLOW);

    // =================================================
    // ||                  CHAIRS                     ||
    // =================================================
    public static final Block ACACIA_CHAIR = registerChair("acacia_chair", Blocks.ACACIA_PLANKS);
    public static final Block CHERRY_CHAIR = registerChair("cherry_chair", Blocks.CHERRY_PLANKS);
    public static final Block BAMBOO_CHAIR = registerChair("bamboo_chair", Blocks.BAMBOO_PLANKS);
    public static final Block CRIMSON_CHAIR = registerChair("crimson_chair", Blocks.CRIMSON_PLANKS);
    public static final Block OAK_CHAIR = registerChair("oak_chair", Blocks.OAK_PLANKS);
    public static final Block BIRCH_CHAIR = registerChair("birch_chair", Blocks.BIRCH_PLANKS);
    public static final Block SPRUCE_CHAIR = registerChair("spruce_chair", Blocks.SPRUCE_PLANKS);
    public static final Block JUNGLE_CHAIR = registerChair("jungle_chair", Blocks.JUNGLE_PLANKS);
    public static final Block WARPED_CHAIR = registerChair("warped_chair", Blocks.WARPED_PLANKS);
    public static final Block DARK_OAK_CHAIR = registerChair("dark_oak_chair", Blocks.DARK_OAK_PLANKS);
    public static final Block MANGROVE_CHAIR = registerChair("mangrove_chair", Blocks.MANGROVE_PLANKS);
    public static final Block IRONWOOD_CHAIR = registerChair("ironwood_chair", Blocks.OAK_PLANKS);
    public static final Block OLIVE_CHAIR = registerChair("olive_chair", Blocks.OAK_PLANKS);

    // =================================================
    // ||                  STOOLS                     ||
    // =================================================
    public static final Block OAK_STOOL = registerStool("oak_stool", Blocks.OAK_PLANKS);
    public static final Block ACACIA_STOOL = registerStool("acacia_stool", Blocks.ACACIA_PLANKS);
    public static final Block CHERRY_STOOL = registerStool("cherry_stool", Blocks.CHERRY_PLANKS);
    public static final Block BAMBOO_STOOL = registerStool("bamboo_stool", Blocks.BAMBOO_PLANKS);
    public static final Block CRIMSON_STOOL = registerStool("crimson_stool", Blocks.CRIMSON_PLANKS);
    public static final Block BIRCH_STOOL = registerStool("birch_stool", Blocks.BIRCH_PLANKS);
    public static final Block SPRUCE_STOOL = registerStool("spruce_stool", Blocks.SPRUCE_PLANKS);
    public static final Block JUNGLE_STOOL = registerStool("jungle_stool", Blocks.JUNGLE_PLANKS);
    public static final Block WARPED_STOOL = registerStool("warped_stool", Blocks.WARPED_PLANKS);
    public static final Block DARK_OAK_STOOL = registerStool("dark_oak_stool", Blocks.DARK_OAK_PLANKS);
    public static final Block MANGROVE_STOOL = registerStool("mangrove_stool", Blocks.MANGROVE_PLANKS);
    public static final Block IRONWOOD_STOOL = registerStool("ironwood_stool", Blocks.OAK_PLANKS);
    public static final Block OLIVE_STOOL = registerStool("olive_stool", Blocks.OAK_PLANKS);

    // =================================================
    // ||                  TABLES                     ||
    // =================================================
    public static final Block OAK_TABLE = registerTable("oak_table", Blocks.OAK_PLANKS);
    public static final Block DARK_OAK_TABLE = registerTable("dark_oak_table", Blocks.DARK_OAK_PLANKS);
    public static final Block MANGROVE_TABLE = registerTable("mangrove_table", Blocks.MANGROVE_PLANKS);
    public static final Block CHERRY_TABLE = registerTable("cherry_table", Blocks.CHERRY_PLANKS);
    public static final Block BIRCH_TABLE = registerTable("birch_table", Blocks.BIRCH_PLANKS);
    public static final Block SPRUCE_TABLE = registerTable("spruce_table", Blocks.SPRUCE_PLANKS);
    public static final Block ACACIA_TABLE = registerTable("acacia_table", Blocks.ACACIA_PLANKS);
    public static final Block JUNGLE_TABLE = registerTable("jungle_table", Blocks.JUNGLE_PLANKS);
    public static final Block BAMBOO_TABLE = registerTable("bamboo_table", Blocks.BAMBOO_PLANKS);
    public static final Block CRIMSON_TABLE = registerTable("crimson_table", Blocks.CRIMSON_PLANKS);
    public static final Block WARPED_TABLE = registerTable("warped_table", Blocks.WARPED_PLANKS);
    public static final Block IRONWOOD_TABLE = registerTable("ironwood_table", Blocks.OAK_PLANKS);
    public static final Block OLIVE_TABLE = registerTable("olive_table", Blocks.OAK_PLANKS);

    // =================================================
    // ||                 CABINETS                    ||
    // =================================================
    public static final Block ACACIA_CABINET = registerCabinetBlock("acacia_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_PLANKS).nonOpaque()));
    public static final Block CHERRY_CABINET = registerCabinetBlock("cherry_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_PLANKS).nonOpaque()));
    public static final Block OAK_CABINET = registerCabinetBlock("oak_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block DARK_OAK_CABINET = registerCabinetBlock("dark_oak_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.DARK_OAK_PLANKS).nonOpaque()));
    public static final Block BIRCH_CABINET = registerCabinetBlock("birch_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_PLANKS).nonOpaque()));
    public static final Block SPRUCE_CABINET = registerCabinetBlock("spruce_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).nonOpaque()));
    public static final Block MANGROVE_CABINET = registerCabinetBlock("mangrove_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_PLANKS).nonOpaque()));
    public static final Block JUNGLE_CABINET = registerCabinetBlock("jungle_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_PLANKS).nonOpaque()));
    public static final Block CRIMSON_CABINET = registerCabinetBlock("crimson_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.CRIMSON_PLANKS).nonOpaque()));
    public static final Block WARPED_CABINET = registerCabinetBlock("warped_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.WARPED_PLANKS).nonOpaque()));
    public static final Block IRONWOOD_CABINET = registerCabinetBlock("ironwood_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(ModBlocks.IRONWOOD_PLANKS).nonOpaque()));
    public static final Block OLIVE_CABINET = registerCabinetBlock("olive_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(ModBlocks.OLIVE_PLANKS).nonOpaque()));

    // =================================================
    // ||                  CHAINS                     ||
    // =================================================
    public static final Block GOLDEN_CHAIN = registerBlock("golden_chain", new CustomChainBlock(AbstractBlock.Settings.copy(Blocks.CHAIN)));
    public static final Block COPPER_CHAIN = registerBlock("copper_chain",
            new CopperChainBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(Blocks.CHAIN)));
    public static final Block EXPOSED_COPPER_CHAIN = registerBlock("exposed_copper_chain",
            new CopperChainBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(Blocks.CHAIN)));
    public static final Block WEATHERED_COPPER_CHAIN = registerBlock("weathered_copper_chain",
            new CopperChainBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(Blocks.CHAIN)));
    public static final Block OXIDIZED_COPPER_CHAIN = registerBlock("oxidized_copper_chain",
            new CopperChainBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(Blocks.CHAIN)));
    // Waxed
    public static final Block WAXED_COPPER_CHAIN = registerBlock("waxed_copper_chain",
            new CustomChainBlock(AbstractBlock.Settings.copy(Blocks.CHAIN)));
    public static final Block WAXED_EXPOSED_COPPER_CHAIN = registerBlock("waxed_exposed_copper_chain",
            new CustomChainBlock(AbstractBlock.Settings.copy(Blocks.CHAIN)));
    public static final Block WAXED_OXIDIZED_COPPER_CHAIN = registerBlock("waxed_oxidized_copper_chain",
            new CustomChainBlock(AbstractBlock.Settings.copy(Blocks.CHAIN)));
    public static final Block WAXED_WEATHERED_COPPER_CHAIN = registerBlock("waxed_weathered_copper_chain",
            new CustomChainBlock(AbstractBlock.Settings.copy(Blocks.CHAIN)));

    // =================================================
    // ||                   LANTERNS                  ||
    // =================================================
    //Iron
    public static final Block ORNATE_LANTERN = registerBlock("ornate_lantern",
            new OrnateLanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block ORNATE_SOUL_LANTERN = registerBlock("ornate_soul_lantern",
            new OrnateLanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    // Golden
    public static final Block ORNATE_GOLD_LANTERN = registerBlock("ornate_gold_lantern",
            new OrnateLanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block ORNATE_GOLD_SOUL_LANTERN = registerBlock("ornate_gold_soul_lantern",
            new OrnateLanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    // Copper
    public static final Block ORNATE_COPPER_LANTERN = registerBlock("ornate_copper_lantern",
            new OrnateCopperLanternBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block EXPOSED_ORNATE_COPPER_LANTERN = registerBlock("exposed_ornate_copper_lantern",
            new OrnateCopperLanternBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block WEATHERED_ORNATE_COPPER_LANTERN = registerBlock("weathered_ornate_copper_lantern",
            new OrnateCopperLanternBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block OXIDIZED_ORNATE_COPPER_LANTERN = registerBlock("oxidized_ornate_copper_lantern",
            new OrnateCopperLanternBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block ORNATE_COPPER_SOUL_LANTERN = registerBlock("ornate_copper_soul_lantern",
            new OrnateCopperLanternBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block EXPOSED_ORNATE_COPPER_SOUL_LANTERN = registerBlock("exposed_ornate_copper_soul_lantern",
            new OrnateCopperLanternBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block WEATHERED_ORNATE_COPPER_SOUL_LANTERN = registerBlock("weathered_ornate_copper_soul_lantern",
            new OrnateCopperLanternBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block OXIDIZED_ORNATE_COPPER_SOUL_LANTERN = registerBlock("oxidized_ornate_copper_soul_lantern",
            new OrnateCopperLanternBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(Blocks.LANTERN)));
    // Waxed
    public static final Block WAXED_ORNATE_COPPER_LANTERN = registerBlock("waxed_ornate_copper_lantern",
            new OrnateLanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block WAXED_EXPOSED_ORNATE_COPPER_LANTERN = registerBlock("waxed_exposed_ornate_copper_lantern",
            new OrnateLanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block WAXED_OXIDIZED_ORNATE_COPPER_LANTERN = registerBlock("waxed_oxidized_ornate_copper_lantern",
            new OrnateLanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block WAXED_WEATHERED_ORNATE_COPPER_LANTERN = registerBlock("waxed_weathered_ornate_copper_lantern",
            new OrnateLanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block WAXED_ORNATE_COPPER_SOUL_LANTERN = registerBlock("waxed_ornate_copper_soul_lantern",
            new OrnateLanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block WAXED_EXPOSED_ORNATE_COPPER_SOUL_LANTERN = registerBlock("waxed_exposed_ornate_copper_soul_lantern",
            new OrnateLanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block WAXED_OXIDIZED_ORNATE_COPPER_SOUL_LANTERN = registerBlock("waxed_oxidized_ornate_copper_soul_lantern",
            new OrnateLanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block WAXED_WEATHERED_ORNATE_COPPER_SOUL_LANTERN = registerBlock("waxed_weathered_ornate_copper_soul_lantern",
            new OrnateLanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));

    // =================================================
    // ||                 CHANDELIER                  ||
    // =================================================
    // Iron
    public static final Block CHANDELIER = registerBlock("chandelier",
            new ChandelierBlock(AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));
    //Golden
    public static final Block GOLDEN_CHANDELIER = registerBlock("golden_chandelier",
            new ChandelierBlock(AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));
    // Copper
    public static final Block COPPER_CHANDELIER = registerBlock("copper_chandelier",
            new CopperChandelierBlock(Oxidizable.OxidationLevel.UNAFFECTED,AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));
    public static final Block EXPOSED_COPPER_CHANDELIER = registerBlock("exposed_copper_chandelier",
            new CopperChandelierBlock(Oxidizable.OxidationLevel.EXPOSED,AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));
    public static final Block WEATHERED_COPPER_CHANDELIER = registerBlock("weathered_copper_chandelier",
            new CopperChandelierBlock(Oxidizable.OxidationLevel.WEATHERED,AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));
    public static final Block OXIDIZED_COPPER_CHANDELIER = registerBlock("oxidized_copper_chandelier",
            new CopperChandelierBlock(Oxidizable.OxidationLevel.OXIDIZED,AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));
    //Waxed
    public static final Block WAXED_COPPER_CHANDELIER = registerBlock("waxed_copper_chandelier",
            new ChandelierBlock(AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));
    public static final Block WAXED_EXPOSED_COPPER_CHANDELIER = registerBlock("waxed_exposed_copper_chandelier",
            new ChandelierBlock(AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));
    public static final Block WAXED_WEATHERED_COPPER_CHANDELIER = registerBlock("waxed_weathered_copper_chandelier",
            new ChandelierBlock(AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));
    public static final Block WAXED_OXIDIZED_COPPER_CHANDELIER = registerBlock("waxed_oxidized_copper_chandelier",
            new ChandelierBlock(AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));

    // =================================================
    // ||                CANDLE HOLDER                ||
    // =================================================
    // Iron
    public static final Block IRON_CANDLE_HOLDER = registerBlock("iron_candle_holder", new CandleHolderBlock());
    public static final Block WHITE_IRON_CANDLE_HOLDER = registerBlock("white_iron_candle_holder", new CandleHolderBlock());
    public static final Block LIGHT_GRAY_IRON_CANDLE_HOLDER = registerBlock("light_gray_iron_candle_holder", new CandleHolderBlock());
    public static final Block GRAY_IRON_CANDLE_HOLDER = registerBlock("gray_iron_candle_holder", new CandleHolderBlock());
    public static final Block BLACK_IRON_CANDLE_HOLDER = registerBlock("black_iron_candle_holder", new CandleHolderBlock());
    public static final Block BROWN_IRON_CANDLE_HOLDER = registerBlock("brown_iron_candle_holder", new CandleHolderBlock());
    public static final Block RED_IRON_CANDLE_HOLDER = registerBlock("red_iron_candle_holder", new CandleHolderBlock());
    public static final Block ORANGE_IRON_CANDLE_HOLDER = registerBlock("orange_iron_candle_holder", new CandleHolderBlock());
    public static final Block YELLOW_IRON_CANDLE_HOLDER = registerBlock("yellow_iron_candle_holder", new CandleHolderBlock());
    public static final Block LIME_IRON_CANDLE_HOLDER = registerBlock("lime_iron_candle_holder", new CandleHolderBlock());
    public static final Block CYAN_IRON_CANDLE_HOLDER = registerBlock("cyan_iron_candle_holder", new CandleHolderBlock());
    public static final Block LIGHT_BLUE_IRON_CANDLE_HOLDER = registerBlock("light_blue_iron_candle_holder", new CandleHolderBlock());
    public static final Block BLUE_IRON_CANDLE_HOLDER = registerBlock("blue_iron_candle_holder", new CandleHolderBlock());
    public static final Block PURPLE_IRON_CANDLE_HOLDER = registerBlock("purple_iron_candle_holder", new CandleHolderBlock());
    public static final Block MAGENTA_IRON_CANDLE_HOLDER = registerBlock("magenta_iron_candle_holder", new CandleHolderBlock());
    public static final Block PINK_IRON_CANDLE_HOLDER = registerBlock("pink_iron_candle_holder", new CandleHolderBlock());
    public static final Block GREEN_IRON_CANDLE_HOLDER = registerBlock("green_iron_candle_holder", new CandleHolderBlock());

    // Golden
    public static final Block GOLDEN_CANDLE_HOLDER = registerBlock("golden_candle_holder", new CandleHolderBlock());
    public static final Block WHITE_GOLDEN_CANDLE_HOLDER = registerBlock("white_golden_candle_holder", new CandleHolderBlock());
    public static final Block LIGHT_GRAY_GOLDEN_CANDLE_HOLDER = registerBlock("light_gray_golden_candle_holder", new CandleHolderBlock());
    public static final Block GRAY_GOLDEN_CANDLE_HOLDER = registerBlock("gray_golden_candle_holder", new CandleHolderBlock());
    public static final Block BLACK_GOLDEN_CANDLE_HOLDER = registerBlock("black_golden_candle_holder", new CandleHolderBlock());
    public static final Block BROWN_GOLDEN_CANDLE_HOLDER = registerBlock("brown_golden_candle_holder", new CandleHolderBlock());
    public static final Block RED_GOLDEN_CANDLE_HOLDER = registerBlock("red_golden_candle_holder", new CandleHolderBlock());
    public static final Block ORANGE_GOLDEN_CANDLE_HOLDER = registerBlock("orange_golden_candle_holder", new CandleHolderBlock());
    public static final Block YELLOW_GOLDEN_CANDLE_HOLDER = registerBlock("yellow_golden_candle_holder", new CandleHolderBlock());
    public static final Block LIME_GOLDEN_CANDLE_HOLDER = registerBlock("lime_golden_candle_holder", new CandleHolderBlock());
    public static final Block CYAN_GOLDEN_CANDLE_HOLDER = registerBlock("cyan_golden_candle_holder", new CandleHolderBlock());
    public static final Block LIGHT_BLUE_GOLDEN_CANDLE_HOLDER = registerBlock("light_blue_golden_candle_holder", new CandleHolderBlock());
    public static final Block BLUE_GOLDEN_CANDLE_HOLDER = registerBlock("blue_golden_candle_holder", new CandleHolderBlock());
    public static final Block PURPLE_GOLDEN_CANDLE_HOLDER = registerBlock("purple_golden_candle_holder", new CandleHolderBlock());
    public static final Block MAGENTA_GOLDEN_CANDLE_HOLDER = registerBlock("magenta_golden_candle_holder", new CandleHolderBlock());
    public static final Block PINK_GOLDEN_CANDLE_HOLDER = registerBlock("pink_golden_candle_holder", new CandleHolderBlock());
    public static final Block GREEN_GOLDEN_CANDLE_HOLDER = registerBlock("green_golden_candle_holder", new CandleHolderBlock());

    // Copper
    public static final Block COPPER_CANDLE_HOLDER = registerBlock("copper_candle_holder",
            new CopperCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_COPPER_CANDLE_HOLDER = registerBlock("exposed_copper_candle_holder",
            new CopperCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_COPPER_CANDLE_HOLDER = registerBlock("weathered_copper_candle_holder",
            new CopperCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_COPPER_CANDLE_HOLDER = registerBlock("oxidized_copper_candle_holder",
            new CopperCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    // Waxed
    public static final Block WAXED_COPPER_CANDLE_HOLDER = registerBlock("waxed_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_EXPOSED_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_WEATHERED_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_OXIDIZED_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_copper_candle_holder", new CandleHolderBlock());

    // Black
    public static final Block BLACK_COPPER_CANDLE_HOLDER = registerBlock("black_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_BLACK_COPPER_CANDLE_HOLDER = registerBlock("exposed_black_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_BLACK_COPPER_CANDLE_HOLDER = registerBlock("weathered_black_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_BLACK_COPPER_CANDLE_HOLDER = registerBlock("oxidized_black_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_BLACK_COPPER_CANDLE_HOLDER = registerBlock("waxed_black_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_EXPOSED_BLACK_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_black_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_WEATHERED_BLACK_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_black_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_OXIDIZED_BLACK_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_black_copper_candle_holder", new CandleHolderBlock());

    // Blue
    public static final Block BLUE_COPPER_CANDLE_HOLDER = registerBlock("blue_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_BLUE_COPPER_CANDLE_HOLDER = registerBlock("exposed_blue_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_BLUE_COPPER_CANDLE_HOLDER = registerBlock("weathered_blue_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_BLUE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_blue_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_BLUE_COPPER_CANDLE_HOLDER = registerBlock("waxed_blue_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_EXPOSED_BLUE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_blue_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_WEATHERED_BLUE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_blue_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_OXIDIZED_BLUE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_blue_copper_candle_holder", new CandleHolderBlock());

    // Brown
    public static final Block BROWN_COPPER_CANDLE_HOLDER = registerBlock("brown_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_BROWN_COPPER_CANDLE_HOLDER = registerBlock("exposed_brown_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_BROWN_COPPER_CANDLE_HOLDER = registerBlock("weathered_brown_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_BROWN_COPPER_CANDLE_HOLDER = registerBlock("oxidized_brown_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_BROWN_COPPER_CANDLE_HOLDER = registerBlock("waxed_brown_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_EXPOSED_BROWN_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_brown_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_WEATHERED_BROWN_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_brown_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_OXIDIZED_BROWN_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_brown_copper_candle_holder", new CandleHolderBlock());

    // Cyan
    public static final Block CYAN_COPPER_CANDLE_HOLDER = registerBlock("cyan_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_CYAN_COPPER_CANDLE_HOLDER = registerBlock("exposed_cyan_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_CYAN_COPPER_CANDLE_HOLDER = registerBlock("weathered_cyan_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_CYAN_COPPER_CANDLE_HOLDER = registerBlock("oxidized_cyan_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_CYAN_COPPER_CANDLE_HOLDER = registerBlock("waxed_cyan_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_EXPOSED_CYAN_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_cyan_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_WEATHERED_CYAN_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_cyan_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_OXIDIZED_CYAN_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_cyan_copper_candle_holder", new CandleHolderBlock());

    // Gray
    public static final Block GRAY_COPPER_CANDLE_HOLDER = registerBlock("gray_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_GRAY_COPPER_CANDLE_HOLDER = registerBlock("exposed_gray_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_GRAY_COPPER_CANDLE_HOLDER = registerBlock("weathered_gray_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_GRAY_COPPER_CANDLE_HOLDER = registerBlock("oxidized_gray_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_GRAY_COPPER_CANDLE_HOLDER = registerBlock("waxed_gray_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_EXPOSED_GRAY_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_gray_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_WEATHERED_GRAY_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_gray_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_OXIDIZED_GRAY_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_gray_copper_candle_holder", new CandleHolderBlock());

    // Green
    public static final Block GREEN_COPPER_CANDLE_HOLDER = registerBlock("green_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_GREEN_COPPER_CANDLE_HOLDER = registerBlock("exposed_green_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_GREEN_COPPER_CANDLE_HOLDER = registerBlock("weathered_green_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_GREEN_COPPER_CANDLE_HOLDER = registerBlock("oxidized_green_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_GREEN_COPPER_CANDLE_HOLDER = registerBlock("waxed_green_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_EXPOSED_GREEN_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_green_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_WEATHERED_GREEN_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_green_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_OXIDIZED_GREEN_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_green_copper_candle_holder", new CandleHolderBlock());

    // Light blue
    public static final Block LIGHT_BLUE_COPPER_CANDLE_HOLDER = registerBlock("light_blue_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_LIGHT_BLUE_COPPER_CANDLE_HOLDER = registerBlock("exposed_light_blue_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_LIGHT_BLUE_COPPER_CANDLE_HOLDER = registerBlock("weathered_light_blue_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_LIGHT_BLUE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_light_blue_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_LIGHT_BLUE_COPPER_CANDLE_HOLDER = registerBlock("waxed_light_blue_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_EXPOSED_LIGHT_BLUE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_light_blue_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_WEATHERED_LIGHT_BLUE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_light_blue_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_OXIDIZED_LIGHT_BLUE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_light_blue_copper_candle_holder", new CandleHolderBlock());

    // Light gray
    public static final Block LIGHT_GRAY_COPPER_CANDLE_HOLDER = registerBlock("light_gray_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_LIGHT_GRAY_COPPER_CANDLE_HOLDER = registerBlock("exposed_light_gray_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_LIGHT_GRAY_COPPER_CANDLE_HOLDER = registerBlock("weathered_light_gray_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_LIGHT_GRAY_COPPER_CANDLE_HOLDER = registerBlock("oxidized_light_gray_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_LIGHT_GRAY_COPPER_CANDLE_HOLDER = registerBlock("waxed_light_gray_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_EXPOSED_LIGHT_GRAY_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_light_gray_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_WEATHERED_LIGHT_GRAY_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_light_gray_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_OXIDIZED_LIGHT_GRAY_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_light_gray_copper_candle_holder", new CandleHolderBlock());

    // Lime
    public static final Block LIME_COPPER_CANDLE_HOLDER = registerBlock("lime_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_LIME_COPPER_CANDLE_HOLDER = registerBlock("exposed_lime_copper_candle_holder",new CopperCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_LIME_COPPER_CANDLE_HOLDER = registerBlock("weathered_lime_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_LIME_COPPER_CANDLE_HOLDER = registerBlock("oxidized_lime_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_LIME_COPPER_CANDLE_HOLDER = registerBlock("waxed_lime_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_EXPOSED_LIME_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_lime_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_WEATHERED_LIME_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_lime_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_OXIDIZED_LIME_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_lime_copper_candle_holder", new CandleHolderBlock());

    // Magenta
    public static final Block MAGENTA_COPPER_CANDLE_HOLDER = registerBlock("magenta_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_MAGENTA_COPPER_CANDLE_HOLDER = registerBlock("exposed_magenta_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_MAGENTA_COPPER_CANDLE_HOLDER = registerBlock("weathered_magenta_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_MAGENTA_COPPER_CANDLE_HOLDER = registerBlock("oxidized_magenta_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_MAGENTA_COPPER_CANDLE_HOLDER = registerBlock("waxed_magenta_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_EXPOSED_MAGENTA_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_magenta_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_WEATHERED_MAGENTA_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_magenta_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_OXIDIZED_MAGENTA_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_magenta_copper_candle_holder", new CandleHolderBlock());

    // Orange
    public static final Block ORANGE_COPPER_CANDLE_HOLDER = registerBlock("orange_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_ORANGE_COPPER_CANDLE_HOLDER = registerBlock("exposed_orange_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_ORANGE_COPPER_CANDLE_HOLDER = registerBlock("weathered_orange_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_ORANGE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_orange_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_ORANGE_COPPER_CANDLE_HOLDER = registerBlock("waxed_orange_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_EXPOSED_ORANGE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_orange_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_WEATHERED_ORANGE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_orange_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_OXIDIZED_ORANGE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_orange_copper_candle_holder", new CandleHolderBlock());

    // Pink
    public static final Block PINK_COPPER_CANDLE_HOLDER = registerBlock("pink_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_PINK_COPPER_CANDLE_HOLDER = registerBlock("exposed_pink_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_PINK_COPPER_CANDLE_HOLDER = registerBlock("weathered_pink_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_PINK_COPPER_CANDLE_HOLDER = registerBlock("oxidized_pink_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_PINK_COPPER_CANDLE_HOLDER = registerBlock("waxed_pink_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_EXPOSED_PINK_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_pink_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_WEATHERED_PINK_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_pink_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_OXIDIZED_PINK_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_pink_copper_candle_holder", new CandleHolderBlock());

    // Purple
    public static final Block PURPLE_COPPER_CANDLE_HOLDER = registerBlock("purple_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_PURPLE_COPPER_CANDLE_HOLDER = registerBlock("exposed_purple_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_PURPLE_COPPER_CANDLE_HOLDER = registerBlock("weathered_purple_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_PURPLE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_purple_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_PURPLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_purple_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_EXPOSED_PURPLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_purple_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_WEATHERED_PURPLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_purple_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_OXIDIZED_PURPLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_purple_copper_candle_holder", new CandleHolderBlock());

    // Red
    public static final Block RED_COPPER_CANDLE_HOLDER = registerBlock("red_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_RED_COPPER_CANDLE_HOLDER = registerBlock("exposed_red_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_RED_COPPER_CANDLE_HOLDER = registerBlock("weathered_red_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_RED_COPPER_CANDLE_HOLDER = registerBlock("oxidized_red_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_RED_COPPER_CANDLE_HOLDER = registerBlock("waxed_red_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_EXPOSED_RED_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_red_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_WEATHERED_RED_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_red_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_OXIDIZED_RED_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_red_copper_candle_holder", new CandleHolderBlock());

    // White
    public static final Block WHITE_COPPER_CANDLE_HOLDER = registerBlock("white_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_WHITE_COPPER_CANDLE_HOLDER = registerBlock("exposed_white_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_WHITE_COPPER_CANDLE_HOLDER = registerBlock("weathered_white_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_WHITE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_white_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_WHITE_COPPER_CANDLE_HOLDER = registerBlock("waxed_white_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_EXPOSED_WHITE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_white_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_WEATHERED_WHITE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_white_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_OXIDIZED_WHITE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_white_copper_candle_holder", new CandleHolderBlock());

    // Yellow
    public static final Block YELLOW_COPPER_CANDLE_HOLDER = registerBlock("yellow_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_YELLOW_COPPER_CANDLE_HOLDER = registerBlock("exposed_yellow_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_YELLOW_COPPER_CANDLE_HOLDER = registerBlock("weathered_yellow_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_YELLOW_COPPER_CANDLE_HOLDER = registerBlock("oxidized_yellow_copper_candle_holder", new CopperCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_YELLOW_COPPER_CANDLE_HOLDER = registerBlock("waxed_yellow_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_EXPOSED_YELLOW_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_yellow_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_WEATHERED_YELLOW_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_yellow_copper_candle_holder", new CandleHolderBlock());
    public static final Block WAXED_OXIDIZED_YELLOW_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_yellow_copper_candle_holder", new CandleHolderBlock());


    // =================================================
    // ||             DOUBLE CANDLE HOLDER            ||
    // =================================================
    // Iron
    public static final Block DOUBLE_IRON_CANDLE_HOLDER = registerBlock("double_iron_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WHITE_DOUBLE_IRON_CANDLE_HOLDER = registerBlock("white_double_iron_candle_holder", new DoubleCandleHolderBlock());
    public static final Block LIGHT_GRAY_DOUBLE_IRON_CANDLE_HOLDER = registerBlock("light_gray_double_iron_candle_holder", new DoubleCandleHolderBlock());
    public static final Block GRAY_DOUBLE_IRON_CANDLE_HOLDER = registerBlock("gray_double_iron_candle_holder", new DoubleCandleHolderBlock());
    public static final Block BLACK_DOUBLE_IRON_CANDLE_HOLDER = registerBlock("black_double_iron_candle_holder", new DoubleCandleHolderBlock());
    public static final Block BROWN_DOUBLE_IRON_CANDLE_HOLDER = registerBlock("brown_double_iron_candle_holder", new DoubleCandleHolderBlock());
    public static final Block RED_DOUBLE_IRON_CANDLE_HOLDER = registerBlock("red_double_iron_candle_holder", new DoubleCandleHolderBlock());
    public static final Block ORANGE_DOUBLE_IRON_CANDLE_HOLDER = registerBlock("orange_double_iron_candle_holder", new DoubleCandleHolderBlock());
    public static final Block YELLOW_DOUBLE_IRON_CANDLE_HOLDER = registerBlock("yellow_double_iron_candle_holder", new DoubleCandleHolderBlock());
    public static final Block LIME_DOUBLE_IRON_CANDLE_HOLDER = registerBlock("lime_double_iron_candle_holder", new DoubleCandleHolderBlock());
    public static final Block CYAN_DOUBLE_IRON_CANDLE_HOLDER = registerBlock("cyan_double_iron_candle_holder", new DoubleCandleHolderBlock());
    public static final Block LIGHT_BLUE_DOUBLE_IRON_CANDLE_HOLDER = registerBlock("light_blue_double_iron_candle_holder", new DoubleCandleHolderBlock());
    public static final Block BLUE_DOUBLE_IRON_CANDLE_HOLDER = registerBlock("blue_double_iron_candle_holder", new DoubleCandleHolderBlock());
    public static final Block PURPLE_DOUBLE_IRON_CANDLE_HOLDER = registerBlock("purple_double_iron_candle_holder", new DoubleCandleHolderBlock());
    public static final Block MAGENTA_DOUBLE_IRON_CANDLE_HOLDER = registerBlock("magenta_double_iron_candle_holder", new DoubleCandleHolderBlock());
    public static final Block PINK_DOUBLE_IRON_CANDLE_HOLDER = registerBlock("pink_double_iron_candle_holder", new DoubleCandleHolderBlock());
    public static final Block GREEN_DOUBLE_IRON_CANDLE_HOLDER = registerBlock("green_double_iron_candle_holder", new DoubleCandleHolderBlock());

    // Golden
    public static final Block DOUBLE_GOLDEN_CANDLE_HOLDER = registerBlock("double_golden_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WHITE_DOUBLE_GOLDEN_CANDLE_HOLDER = registerBlock("white_double_golden_candle_holder", new DoubleCandleHolderBlock());
    public static final Block LIGHT_GRAY_DOUBLE_GOLDEN_CANDLE_HOLDER = registerBlock("light_gray_double_golden_candle_holder", new DoubleCandleHolderBlock());
    public static final Block GRAY_DOUBLE_GOLDEN_CANDLE_HOLDER = registerBlock("gray_double_golden_candle_holder", new DoubleCandleHolderBlock());
    public static final Block BLACK_DOUBLE_GOLDEN_CANDLE_HOLDER = registerBlock("black_double_golden_candle_holder", new DoubleCandleHolderBlock());
    public static final Block BROWN_DOUBLE_GOLDEN_CANDLE_HOLDER = registerBlock("brown_double_golden_candle_holder", new DoubleCandleHolderBlock());
    public static final Block RED_DOUBLE_GOLDEN_CANDLE_HOLDER = registerBlock("red_double_golden_candle_holder", new DoubleCandleHolderBlock());
    public static final Block ORANGE_DOUBLE_GOLDEN_CANDLE_HOLDER = registerBlock("orange_double_golden_candle_holder", new DoubleCandleHolderBlock());
    public static final Block YELLOW_DOUBLE_GOLDEN_CANDLE_HOLDER = registerBlock("yellow_double_golden_candle_holder", new DoubleCandleHolderBlock());
    public static final Block LIME_DOUBLE_GOLDEN_CANDLE_HOLDER = registerBlock("lime_double_golden_candle_holder", new DoubleCandleHolderBlock());
    public static final Block CYAN_DOUBLE_GOLDEN_CANDLE_HOLDER = registerBlock("cyan_double_golden_candle_holder", new DoubleCandleHolderBlock());
    public static final Block LIGHT_BLUE_DOUBLE_GOLDEN_CANDLE_HOLDER = registerBlock("light_blue_double_golden_candle_holder", new DoubleCandleHolderBlock());
    public static final Block BLUE_DOUBLE_GOLDEN_CANDLE_HOLDER = registerBlock("blue_double_golden_candle_holder", new DoubleCandleHolderBlock());
    public static final Block PURPLE_DOUBLE_GOLDEN_CANDLE_HOLDER = registerBlock("purple_double_golden_candle_holder", new DoubleCandleHolderBlock());
    public static final Block MAGENTA_DOUBLE_GOLDEN_CANDLE_HOLDER = registerBlock("magenta_double_golden_candle_holder", new DoubleCandleHolderBlock());
    public static final Block PINK_DOUBLE_GOLDEN_CANDLE_HOLDER = registerBlock("pink_double_golden_candle_holder", new DoubleCandleHolderBlock());
    public static final Block GREEN_DOUBLE_GOLDEN_CANDLE_HOLDER = registerBlock("green_double_golden_candle_holder", new DoubleCandleHolderBlock());

    // Copper
    public static final Block DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("exposed_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("weathered_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_EXPOSED_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_WEATHERED_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_OXIDIZED_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_double_copper_candle_holder", new DoubleCandleHolderBlock());

    // Black
    public static final Block BLACK_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("black_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("exposed_black_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("weathered_black_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_black_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_black_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_EXPOSED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_black_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_WEATHERED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_black_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_OXIDIZED_BLACK_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_black_double_copper_candle_holder", new DoubleCandleHolderBlock());

    // Blue
    public static final Block BLUE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("blue_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("exposed_blue_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("weathered_blue_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_blue_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_blue_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_EXPOSED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_blue_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_WEATHERED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_blue_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_OXIDIZED_BLUE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_blue_double_copper_candle_holder", new DoubleCandleHolderBlock());

    // Brown
    public static final Block BROWN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("brown_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("exposed_brown_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("weathered_brown_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_brown_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_brown_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_EXPOSED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_brown_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_WEATHERED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_brown_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_OXIDIZED_BROWN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_brown_double_copper_candle_holder", new DoubleCandleHolderBlock());

    // Cyan
    public static final Block CYAN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("cyan_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("exposed_cyan_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("weathered_cyan_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_cyan_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_cyan_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_EXPOSED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_cyan_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_WEATHERED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_cyan_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_OXIDIZED_CYAN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_cyan_double_copper_candle_holder", new DoubleCandleHolderBlock());

    // Gray
    public static final Block GRAY_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("gray_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("exposed_gray_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("weathered_gray_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_gray_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_gray_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_EXPOSED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_gray_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_WEATHERED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_gray_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_OXIDIZED_GRAY_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_gray_double_copper_candle_holder", new DoubleCandleHolderBlock());

    // Green
    public static final Block GREEN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("green_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("exposed_green_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("weathered_green_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_green_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_green_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_EXPOSED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_green_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_WEATHERED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_green_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_OXIDIZED_GREEN_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_green_double_copper_candle_holder", new DoubleCandleHolderBlock());

    // Light blue
    public static final Block LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("light_blue_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("exposed_light_blue_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("weathered_light_blue_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_light_blue_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_light_blue_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_EXPOSED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_light_blue_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_WEATHERED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_light_blue_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_OXIDIZED_LIGHT_BLUE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_light_blue_double_copper_candle_holder", new DoubleCandleHolderBlock());

    // Light gray
    public static final Block LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("light_gray_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("exposed_light_gray_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("weathered_light_gray_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_light_gray_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_light_gray_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_EXPOSED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_light_gray_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_WEATHERED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_light_gray_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_OXIDIZED_LIGHT_GRAY_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_light_gray_double_copper_candle_holder", new DoubleCandleHolderBlock());

    // Lime
    public static final Block LIME_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("lime_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("exposed_lime_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("weathered_lime_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_LIME_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_lime_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_LIME_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_lime_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_EXPOSED_LIME_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_lime_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_WEATHERED_LIME_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_lime_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_OXIDIZED_LIME_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_lime_double_copper_candle_holder", new DoubleCandleHolderBlock());

    // Magenta
    public static final Block MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("magenta_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("exposed_magenta_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("weathered_magenta_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_magenta_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_magenta_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_EXPOSED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_magenta_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_WEATHERED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_magenta_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_OXIDIZED_MAGENTA_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_magenta_double_copper_candle_holder", new DoubleCandleHolderBlock());

    // Orange
    public static final Block ORANGE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("orange_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("exposed_orange_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("weathered_orange_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_orange_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_orange_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_EXPOSED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_orange_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_WEATHERED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_orange_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_OXIDIZED_ORANGE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_orange_double_copper_candle_holder", new DoubleCandleHolderBlock());

    // Pink
    public static final Block PINK_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("pink_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("exposed_pink_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("weathered_pink_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_PINK_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_pink_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_PINK_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_pink_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_EXPOSED_PINK_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_pink_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_WEATHERED_PINK_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_pink_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_OXIDIZED_PINK_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_pink_double_copper_candle_holder", new DoubleCandleHolderBlock());

    // Purple
    public static final Block PURPLE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("purple_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("exposed_purple_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("weathered_purple_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_purple_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_purple_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_EXPOSED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_purple_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_WEATHERED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_purple_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_OXIDIZED_PURPLE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_purple_double_copper_candle_holder", new DoubleCandleHolderBlock());

    // Red
    public static final Block RED_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("red_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("exposed_red_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("weathered_red_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_RED_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_red_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_RED_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_red_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_EXPOSED_RED_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_red_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_WEATHERED_RED_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_red_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_OXIDIZED_RED_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_red_double_copper_candle_holder", new DoubleCandleHolderBlock());

    // White
    public static final Block WHITE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("white_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("exposed_white_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("weathered_white_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_white_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_white_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_EXPOSED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_white_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_WEATHERED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_white_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_OXIDIZED_WHITE_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_white_double_copper_candle_holder", new DoubleCandleHolderBlock());

    // Yellow
    public static final Block YELLOW_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("yellow_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block EXPOSED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("exposed_yellow_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WEATHERED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("weathered_yellow_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block OXIDIZED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("oxidized_yellow_double_copper_candle_holder", new CopperDoubleCandleHolderBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_yellow_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_EXPOSED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_exposed_yellow_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_WEATHERED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_weathered_yellow_double_copper_candle_holder", new DoubleCandleHolderBlock());
    public static final Block WAXED_OXIDIZED_YELLOW_DOUBLE_COPPER_CANDLE_HOLDER = registerBlock("waxed_oxidized_yellow_double_copper_candle_holder", new DoubleCandleHolderBlock());

    // =================================================
    // ||                AGRICULTURE                  ||
    // =================================================
    public static final Block ROPE = registerBlock("rope", new RopeBlock(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block GRAPE_LEAVES = registerBlockWithoutBlockItem("grape_leaves", new GrapeLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block GRAPE_STEM = registerBlockWithoutBlockItem("grape_stem", new GrapeStemBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)));
    public static final Block FERTILE_SOIL = registerBlock("fertile_soil", new FertileSoilBlock(AbstractBlock.Settings.copy(Blocks.FARMLAND)));
    public static final Block STAKE = registerBlock("stake", new StakeBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block TOMATO_CROP = registerBlockWithoutBlockItem("tomato_crop", new CropStakeBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), 2, 4));
    public static final Block CHILI_CROP = registerBlockWithoutBlockItem("chili_crop",
            new CropStakeBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), 1, 4) {
                @Override
                protected @NotNull Item getCrop() {
                    return ModItems.CHILI_PEPPER;
                }

                @Override
                protected @NotNull Item getSeed() {
                    return ModItems.CHILI_PEPPER_SEEDS;
                }

                @Override
                protected void giveCrops(PlayerEntity player, Random random) {
                    super.giveCrops(player, random);
                    if (random.nextFloat() < 0.03f) {
                        player.giveItemStack(new ItemStack(ModItems.GHOST_PEPPER));
                    }
                }
            }
    );

    public static final Block APPLE_TREE = registerBlockWithoutBlockItem("apple_tree", new AppleTreeBlock(AbstractBlock.Settings.create().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY).mapColor(MapColor.DARK_GREEN)));
    public static final Block APPLE_LEAVES = registerBlock("apple_leaves", new FruitLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).nonOpaque()));
    public static final Block APPLE_SAPLING = registerBlock("apple_sapling", new SaplingBlock(ModSaplingGenerators.APPLE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).nonOpaque().solidBlock(Blocks::never).suffocates(Blocks::never)));
    public static final Block CRUSHING_TUB = registerBlock("crushing_tub", new CrushingTubBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().luminance(state -> state.get(LuminousFluidStorage.LIGHT_LEVEL))));
    public static final Block DRYING_BASIN = registerBlock("drying_basin", new DryingBasinBlock(AbstractBlock.Settings.copy(Blocks.TERRACOTTA).nonOpaque().sounds(BlockSoundGroup.DECORATED_POT).luminance(state -> state.get(LuminousFluidStorage.LIGHT_LEVEL))));
    public static final Block LIQUID_BARREL = registerBlock("liquid_barrel" , new LiquidBarrelBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().luminance(state -> state.get(LuminousFluidStorage.LIGHT_LEVEL))));
    public static final Block BREWING_BARREL = registerBlock("brewing_barrel" , new BrewingBarrelBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block IRON_BERRY_CAKE = registerBlock("iron_berry_cake", new CustomCakeBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ModEffects.FULL_METAL, 200, 0));

    // =================================================
    // ||                 FLUID BLOCKS                ||
    // =================================================
    public static final Block HONEY = registerBlockWithoutBlockItem("honey",
            new FluidBlock(ModFluids.HONEY,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().dropsNothing().nonOpaque())){
                @Override
                public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
                    super.onEntityCollision(state, world, pos, entity);

                    if (entity instanceof LivingEntity) {
                        Vec3d velocity = entity.getVelocity();
                        entity.setVelocity(velocity.x * 0.4, velocity.y * 0.4, velocity.z * 0.4);
                        entity.velocityModified = true;
                    }
                }
            }
    );

    public static final Block OLIVE_OIL = registerBlockWithoutBlockItem("olive_oil",
            new FluidBlock(ModFluids.OLIVE_OIL,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().dropsNothing().nonOpaque())));

    public static final Block VANTA_OIL = registerBlockWithoutBlockItem("vanta_oil",
            new FluidBlock(ModFluids.VANTA_OIL,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().dropsNothing().nonOpaque())));

    public static final Block APPLE_JUICE = registerBlockWithoutBlockItem("apple_juice", new
            FluidBlock(ModFluids.APPLE_JUICE,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block GOLDEN_APPLE_JUICE = registerBlockWithoutBlockItem("golden_apple_juice", new
            FluidBlock(ModFluids.GOLDEN_APPLE_JUICE,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block GRAPE_JUICE = registerBlockWithoutBlockItem("grape_juice", new
            FluidBlock(ModFluids.GRAPE_JUICE,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block SWEET_BERRY_JUICE = registerBlockWithoutBlockItem("sweet_berry_juice", new
            FluidBlock(ModFluids.SWEET_BERRY_JUICE,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block GLOW_BERRY_JUICE = registerBlockWithoutBlockItem("glow_berry_juice", new
            FluidBlock(ModFluids.GLOW_BERRY_JUICE,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing().luminance(state -> 8))));

    public static final Block IRON_BERRY_JUICE = registerBlockWithoutBlockItem("iron_berry_juice", new
            FluidBlock(ModFluids.IRON_BERRY_JUICE,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block SUGAR_CANE_JUICE = registerBlockWithoutBlockItem("sugar_cane_juice", new
            FluidBlock(ModFluids.SUGAR_CANE_JUICE,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block ALE_WORT = registerBlockWithoutBlockItem("ale_wort", new
            FluidBlock(ModFluids.ALE_WORT,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block ALE = registerBlockWithoutBlockItem("ale", new
            FluidBlock(ModFluids.ALE,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block IRON_WINE = registerBlockWithoutBlockItem("iron_wine", new
            FluidBlock(ModFluids.IRON_WINE,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block CIDER = registerBlockWithoutBlockItem("cider", new
            FluidBlock(ModFluids.CIDER,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block MEAD = registerBlockWithoutBlockItem("mead", new
            FluidBlock(ModFluids.MEAD,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block WINE = registerBlockWithoutBlockItem("wine", new
            FluidBlock(ModFluids.WINE,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block SWEET_BERRY_WINE = registerBlockWithoutBlockItem("sweet_berry_wine", new
            FluidBlock(ModFluids.SWEET_BERRY_WINE,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block GLOW_BERRY_WINE = registerBlockWithoutBlockItem("glow_berry_wine", new
            FluidBlock(ModFluids.GLOW_BERRY_WINE,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing().luminance(state -> 10))));

    public static final Block AMBROSIA = registerBlockWithoutBlockItem("ambrosia", new
            FluidBlock(ModFluids.AMBROSIA,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block RUM = registerBlockWithoutBlockItem("rum", new
            FluidBlock(ModFluids.RUM,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block POTION = registerBlockWithoutBlockItem("potion", new
            FluidBlock(ModFluids.POTION,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())){

    });

    // =================================================
    // ||                   HERBS                     ||
    // =================================================
    public static final Block ALOE_VERA = registerBlockWithoutBlockItem("aloe_vera_block", new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.ALOE_VERA));
    public static final Block BLOOD_ORCHID = registerBlockWithoutBlockItem("blood_orchid_block", new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.BLOOD_ORCHID));
    public static final Block CHAMOMILE = registerBlockWithoutBlockItem("chamomile_block", new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.CHAMOMILE));
    public static final Block CLOUDSBLUFF = registerBlockWithoutBlockItem("cloudsbluff_block", new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.CLOUDSBLUFF));
    public static final Block COHOSH = registerBlockWithoutBlockItem("cohosh_block", new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.COHOSH));
    public static final Block CORE_ROOT = registerBlockWithoutBlockItem("core_root", new CustomMushroomBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.CORE_ROOT));
    public static final Block DEATHSTALK_MUSHROOM = registerBlockWithoutBlockItem("deathstalk_mushroom", CustomMushroomBlock.NetherMushroom(AbstractBlock.Settings.copy(Blocks.WHEAT).luminance(state -> 8), () -> ModItems.DEATHSTALK_MUSHROOM));
    public static final Block GINSENG = registerBlockWithoutBlockItem("ginseng_block", new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.GINSENG));
    public static final Block HORSETAIL = registerBlockWithoutBlockItem("horsetail_block", new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.HORSETAIL));
    public static final Block MARSHMALLOW = registerBlockWithoutBlockItem("marshmallow_block", new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.MARSH_MALLOW));
    public static final Block MOONCAP_MUSHROOM = registerBlockWithoutBlockItem("mooncap_mushroom", new CustomMushroomBlock(AbstractBlock.Settings.copy(Blocks.WHEAT).luminance(state -> 15), () -> ModItems.MOONCAP_MUSHROOM));
    public static final Block VANTA_LILY = registerBlockWithoutBlockItem("vanta_lily_block", new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.VANTA_LILY));
    public static final Block WIND_THISTLE = registerBlockWithoutBlockItem("wind_thistle_block", new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.WIND_THISTLE));

    // =================================================
    // ||                   POTTED                    ||
    // =================================================
    public static final Block POTTED_OLIVE_SAPLING = registerBlock("potted_olive_sapling", Blocks.createFlowerPotBlock(OLIVE_SAPLING));
    public static final Block POTTED_IRONWOOD_SAPLING = registerBlock("potted_ironwood_sapling", Blocks.createFlowerPotBlock(IRONWOOD_SAPLING));
    public static final Block POTTED_ALOE_VERA = registerBlock("potted_aloe_vera", Blocks.createFlowerPotBlock(ALOE_VERA));
    public static final Block POTTED_BLOOD_ORCHID = registerBlock("potted_blood_orchid", Blocks.createFlowerPotBlock(BLOOD_ORCHID));
    public static final Block POTTED_CHAMOMILE = registerBlock("potted_chamomile", Blocks.createFlowerPotBlock(CHAMOMILE));
    public static final Block POTTED_CLOUDSBLUFF = registerBlock("potted_cloudsbluff", Blocks.createFlowerPotBlock(CLOUDSBLUFF));
    public static final Block POTTED_COHOSH = registerBlock("potted_cohosh", Blocks.createFlowerPotBlock(COHOSH));
    public static final Block POTTED_CORE_ROOT = registerBlock("potted_core_root", Blocks.createFlowerPotBlock(CORE_ROOT));
    public static final Block POTTED_DEATHSTALK_MUSHROOM = registerBlock("potted_deathstalk_mushroom", Blocks.createFlowerPotBlock(DEATHSTALK_MUSHROOM));
    public static final Block POTTED_GINSENG = registerBlock("potted_ginseng", Blocks.createFlowerPotBlock(GINSENG));
    public static final Block POTTED_HORSETAIL = registerBlock("potted_horsetail", Blocks.createFlowerPotBlock(HORSETAIL));
    public static final Block POTTED_MARSHMALLOW = registerBlock("potted_marshmallow", Blocks.createFlowerPotBlock(MARSHMALLOW));
    public static final Block POTTED_MOONCAP_MUSHROOM = registerBlock("potted_mooncap_mushroom", Blocks.createFlowerPotBlock(MOONCAP_MUSHROOM));
    public static final Block POTTED_VANTA_LILY = registerBlock("potted_vanta_lily", Blocks.createFlowerPotBlock(VANTA_LILY));
    public static final Block POTTED_WIND_THISTLE = registerBlock("potted_wind_thistle", Blocks.createFlowerPotBlock(WIND_THISTLE));

    // =================================================
    // ||                   BUSHES                    ||
    // =================================================
    public static final Block TEST_BERRY_BUSH = registerBlockWithoutBlockItem("test_berry_bush", new CustomBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)));

    // =================================================
    // ||                  ALCHEMY                    ||
    // =================================================
    public static final Block BASIC_CONDENSER = registerBlock("condenser", new BasicCondenserBlock(AbstractBlock.Settings.copy(Blocks.BRICKS).luminance(state -> state.get(LIT) ? 15 : 0).nonOpaque()));
    public static final Block ADVANCED_CONDENSER = registerBlock("advanced_condenser", new AdvancedCondenserBlock(AbstractBlock.Settings.copy(Blocks.NETHER_BRICKS).luminance(state -> state.get(AdvancedCondenserBlock.LIT) ? 15 : 0).nonOpaque()));
    public static final Block BASIC_RETORT = registerBlock("retort", new RetortBlock(AbstractBlock.Settings.copy(Blocks.BRICKS).nonOpaque()));
    public static final Block ADVANCED_RETORT = registerBlock("advanced_retort", new AdvancedRetortBlock(AbstractBlock.Settings.copy(Blocks.NETHER_BRICKS).nonOpaque()));

    // =================================================
    // ||                 DECORATIVE                  ||
    // =================================================
    public static final Block GARGOYLE = registerBlock("gargoyle", new GargoyleBlock(AbstractBlock.Settings.copy(Blocks.STONE).nonOpaque()));
    public static final Block JAR = registerBlock("jar", createJarBlock(null, MapColor.TERRACOTTA_RED));
    //Jar
    public static final Block WHITE_JAR = registerBlock("white_jar", createJarBlock(DyeColor.WHITE, MapColor.WHITE));
    public static final Block ORANGE_JAR = registerBlock("orange_jar", createJarBlock(DyeColor.ORANGE, MapColor.ORANGE));
    public static final Block MAGENTA_JAR = registerBlock("magenta_jar", createJarBlock(DyeColor.MAGENTA, MapColor.MAGENTA));
    public static final Block LIGHT_BLUE_JAR = registerBlock("light_blue_jar", createJarBlock(DyeColor.LIGHT_BLUE, MapColor.LIGHT_BLUE));
    public static final Block YELLOW_JAR = registerBlock("yellow_jar", createJarBlock(DyeColor.YELLOW, MapColor.YELLOW));
    public static final Block LIME_JAR = registerBlock("lime_jar", createJarBlock(DyeColor.LIME, MapColor.LIME));
    public static final Block PINK_JAR = registerBlock("pink_jar", createJarBlock(DyeColor.PINK, MapColor.PINK));
    public static final Block GRAY_JAR = registerBlock("gray_jar", createJarBlock(DyeColor.GRAY, MapColor.GRAY));
    public static final Block LIGHT_GRAY_JAR = registerBlock("light_gray_jar", createJarBlock(DyeColor.LIGHT_GRAY, MapColor.LIGHT_GRAY));
    public static final Block CYAN_JAR = registerBlock("cyan_jar", createJarBlock(DyeColor.CYAN, MapColor.CYAN));
    public static final Block PURPLE_JAR = registerBlock("purple_jar", createJarBlock(DyeColor.PURPLE, MapColor.TERRACOTTA_PURPLE));
    public static final Block BLUE_JAR = registerBlock("blue_jar", createJarBlock(DyeColor.BLUE, MapColor.BLUE));
    public static final Block BROWN_JAR = registerBlock("brown_jar", createJarBlock(DyeColor.BROWN, MapColor.BROWN));
    public static final Block GREEN_JAR = registerBlock("green_jar", createJarBlock(DyeColor.GREEN, MapColor.GREEN));
    public static final Block RED_JAR = registerBlock("red_jar", createJarBlock(DyeColor.RED, MapColor.RED));
    public static final Block BLACK_JAR = registerBlock("black_jar", createJarBlock(DyeColor.BLACK, MapColor.BLACK));
    //Urn
    public static final Block URN = registerBlock("urn", createPotBlock(null, MapColor.TERRACOTTA_RED));
    public static final Block WHITE_URN = registerBlock("white_urn", createPotBlock(DyeColor.WHITE, MapColor.WHITE));
    public static final Block ORANGE_URN = registerBlock("orange_urn", createPotBlock(DyeColor.ORANGE, MapColor.ORANGE));
    public static final Block MAGENTA_URN = registerBlock("magenta_urn", createPotBlock(DyeColor.MAGENTA, MapColor.MAGENTA));
    public static final Block LIGHT_BLUE_URN = registerBlock("light_blue_urn", createPotBlock(DyeColor.LIGHT_BLUE, MapColor.LIGHT_BLUE));
    public static final Block YELLOW_URN = registerBlock("yellow_urn", createPotBlock(DyeColor.YELLOW, MapColor.YELLOW));
    public static final Block LIME_URN = registerBlock("lime_urn", createPotBlock(DyeColor.LIME, MapColor.LIME));
    public static final Block PINK_URN = registerBlock("pink_urn", createPotBlock(DyeColor.PINK, MapColor.PINK));
    public static final Block GRAY_URN = registerBlock("gray_urn", createPotBlock(DyeColor.GRAY, MapColor.GRAY));
    public static final Block LIGHT_GRAY_URN = registerBlock("light_gray_urn", createPotBlock(DyeColor.LIGHT_GRAY, MapColor.LIGHT_GRAY));
    public static final Block CYAN_URN = registerBlock("cyan_urn", createPotBlock(DyeColor.CYAN, MapColor.CYAN));
    public static final Block PURPLE_URN = registerBlock("purple_urn", createPotBlock(DyeColor.PURPLE, MapColor.TERRACOTTA_PURPLE));
    public static final Block BLUE_URN = registerBlock("blue_urn", createPotBlock(DyeColor.BLUE, MapColor.BLUE));
    public static final Block BROWN_URN = registerBlock("brown_urn", createPotBlock(DyeColor.BROWN, MapColor.BROWN));
    public static final Block GREEN_URN = registerBlock("green_urn", createPotBlock(DyeColor.GREEN, MapColor.GREEN));
    public static final Block RED_URN = registerBlock("red_urn", createPotBlock(DyeColor.RED, MapColor.RED));
    public static final Block BLACK_URN = registerBlock("black_urn", createPotBlock(DyeColor.BLACK, MapColor.BLACK));

    // =================================================
    // ||                UNFIRED BLOCKS               ||
    // =================================================

    public static final Block UNFIRED_DRYING_BASIN = registerBlock("unfired_drying_basin",
            new UnfiredBlock(AbstractBlock.Settings.copy(Blocks.CLAY), ModBlocks.DRYING_BASIN) {
                @Override
                protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
                    return DryingBasinBlock.SHAPE;
                }
            });

    public static final Block UNFIRED_JAR = registerBlock("unfired_jar",
            new UnfiredBlock(AbstractBlock.Settings.copy(Blocks.CLAY), ModBlocks.JAR) {
                @Override
                protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
                    return JarBlock.CLOSED_SHAPE;
                }
            });

    public static final Block UNFIRED_URN = registerBlock("unfired_urn",
            new UnfiredBlock(AbstractBlock.Settings.copy(Blocks.CLAY), ModBlocks.URN) {
                @Override
                protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
                    return UrnBlock.SHAPE;
                }
            });

    private static Block createJarBlock(@Nullable DyeColor color, MapColor mapColor) {
        return new JarBlock(AbstractBlock.Settings.create()
                .mapColor(mapColor)
                .solid()
                .strength(2.0F)
                .dynamicBounds()
                .nonOpaque()
                .luminance(state -> state.get(LuminousFluidStorage.LIGHT_LEVEL))
                .sounds(BlockSoundGroup.DECORATED_POT)
                .pistonBehavior(PistonBehavior.DESTROY),
                color
        );
    }

    private static Block createPotBlock(@Nullable DyeColor color, MapColor mapColor) {
        return new UrnBlock(AbstractBlock.Settings.create()
                .mapColor(mapColor)
                .solid()
                .strength(2.0F)
                .dynamicBounds()
                .nonOpaque()
                .sounds(BlockSoundGroup.DECORATED_POT)
                .pistonBehavior(PistonBehavior.DESTROY),
                color
        );
    }

    private static Block registerPaintedPlank(String name, MapColor color) {
        if (Resprouted.COMMON_CONFIG.getGeneral().isEnablePaintedPlanks()) {
            return registerBlock(name, new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor(color)));
        }
        return null;
    }

    private static Block registerChair(String name, Block baseBlock) {
        if (Resprouted.COMMON_CONFIG.getGeneral().isEnableChairs()) {
            return registerBlock(name, new ChairBlock(AbstractBlock.Settings.copy(baseBlock).nonOpaque()));
        }
        return null;
    }

    private static Block registerStool(String name, Block baseBlock) {
        if (Resprouted.COMMON_CONFIG.getGeneral().isEnableStools()) {
            return registerBlock(name, new StoolBlock(AbstractBlock.Settings.copy(baseBlock).nonOpaque()));
        }
        return null;
    }

    private static Block registerTable(String name, Block baseBlock) {
        if (Resprouted.COMMON_CONFIG.getGeneral().isEnableTables()) {
            return registerBlock(name, new TableBlock(AbstractBlock.Settings.copy(baseBlock).nonOpaque().solid()));
        }
        return null;
    }

    private static Block registerClayWall(String name, boolean isDiagonal) {
        if (Resprouted.COMMON_CONFIG.getGeneral().isEnableClayWalls()) {
            if (isDiagonal) {
                return registerBlock(name, new ClayWallDiagBlock(AbstractBlock.Settings.copy(Blocks.CLAY)));
            } else {
                return registerBlock(name, new Block(AbstractBlock.Settings.copy(Blocks.CLAY)));
            }
        }
        return null;
    }

    private static Block registerVanillaVariationBlock(String name, AbstractBlock.Settings settings) {
        if (!Resprouted.COMMON_CONFIG.getGeneral().isEnableVanillaBlockVariations()) {
            return null;
        }
        return registerBlock(name, new Block(settings));
    }

    private static Block registerVanillaVariationWall(String name, AbstractBlock.Settings settings) {
        if (!Resprouted.COMMON_CONFIG.getGeneral().isEnableVanillaBlockVariations()) {
            return null;
        }
        return registerBlock(name, new WallBlock(settings));
    }

    private static Block registerVanillaVariationSlab(String name, AbstractBlock.Settings settings) {
        if (!Resprouted.COMMON_CONFIG.getGeneral().isEnableVanillaBlockVariations()) {
            return null;
        }
        return registerBlock(name, new SlabBlock(settings));
    }

    private static Block registerVanillaVariationPillar(String name, AbstractBlock.Settings settings) {
        if (!Resprouted.COMMON_CONFIG.getGeneral().isEnableVanillaBlockVariations()) {
            return null;
        }
        return registerBlock(name, new PillarBlock(settings));
    }

    private static Block registerVanillaVariationStairs(String name, Block baseBlock, Block copySettingsFrom) {
        if (!Resprouted.COMMON_CONFIG.getGeneral().isEnableVanillaBlockVariations() || baseBlock == null) {
            return null;
        }
        return registerBlock(name, new StairsBlock(baseBlock.getDefaultState(), AbstractBlock.Settings.copy(copySettingsFrom)));
    }

    private static Block registerCabinetBlock(String name, Block block) {
        CabinetRegistry.registerCabinet(block);
        return registerBlock(name, block);
    }

    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(Resprouted.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Resprouted.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block){
        Registry.register(Registries.ITEM, Identifier.of(Resprouted.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks(){
        Resprouted.LOGGER.info("Registering Blocks for " + Resprouted.MOD_ID);
    }
}

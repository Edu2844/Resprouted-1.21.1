package net.edu.resprouted.block;

import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.custom.agriculture.*;
import net.edu.resprouted.block.custom.alchemy.AdvancedCondenserBlock;
import net.edu.resprouted.block.custom.alchemy.AdvancedRetortBlock;
import net.edu.resprouted.block.custom.alchemy.CondenserBlock;
import net.edu.resprouted.block.custom.alchemy.RetortBlock;
import net.edu.resprouted.block.custom.decorative.*;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.registry.ModCabinetRegistry;
import net.edu.resprouted.world.tree.ModSaplingGenerators;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import static net.edu.resprouted.block.custom.alchemy.CondenserBlock.LIT;

public class ModBlocks {
    // =================================================
    // ||                   SLATE                     ||
    // =================================================
    public static final Block SLATE = registerBlock("slate",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE)));

    public static final Block POLISHED_SLATE = registerBlock("polished_slate",
            new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE)));
    public static final Block POLISHED_SLATE_WALL = registerBlock("polished_slate_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block POLISHED_SLATE_STAIRS = registerBlock("polished_slate_stairs",
            new StairsBlock(ModBlocks.POLISHED_SLATE.getDefaultState(),AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE)));
    public static final Block POLISHED_SLATE_SLAB = registerBlock("polished_slate_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()));

    public static final Block SLATE_BRICKS = registerBlock("slate_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
    public static final Block SLATE_BRICK_WALL = registerBlock("slate_brick_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
    public static final Block SLATE_BRICK_STAIRS = registerBlock("slate_brick_stairs",
            new StairsBlock(ModBlocks.SLATE_BRICKS.getDefaultState(),AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
    public static final Block SLATE_BRICK_SLAB = registerBlock("slate_brick_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));

    public static final Block SLATE_ROOFS = registerBlock("slate_roofs",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
    public static final Block SLATE_ROOF_STAIRS = registerBlock("slate_roof_stairs",
            new StairsBlock(ModBlocks.SLATE_ROOFS.getDefaultState(),AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
    public static final Block SLATE_ROOF_SLAB = registerBlock("slate_roof_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));

    public static final Block CHISELED_SLATE = registerBlock("chiseled_slate",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));

    public static final Block SLATE_PILLAR = registerBlock("slate_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));

    // =================================================
    // ||                 ANDESITE                    ||
    // =================================================
    public static final Block ANDESITE_BRICKS = registerBlock("andesite_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
    public static final Block CRACKED_ANDESITE_BRICKS = registerBlock("cracked_andesite_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS)));
    public static final Block CHISELED_ANDESITE = registerBlock("chiseled_andesite",
            new Block(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS)));
    public static final Block ANDESITE_PILLAR = registerBlock("andesite_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS)));

    // =================================================
    // ||                  DIORITE                    ||
    // =================================================
    public static final Block DIORITE_BRICKS = registerBlock("diorite_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
    public static final Block CRACKED_DIORITE_BRICKS = registerBlock("cracked_diorite_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS)));
    public static final Block CHISELED_DIORITE = registerBlock("chiseled_diorite",
            new Block(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS)));
    public static final Block DIORITE_PILLAR = registerBlock("diorite_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS)));

    // =================================================
    // ||                  GRANITE                    ||
    // =================================================
    public static final Block GRANITE_BRICKS = registerBlock("granite_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
    public static final Block CRACKED_GRANITE_BRICKS = registerBlock("cracked_granite_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS)));
    public static final Block CHISELED_GRANITE = registerBlock("chiseled_granite",
            new Block(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS)));
    public static final Block GRANITE_PILLAR = registerBlock("granite_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS)));

    // =================================================
    // ||                   STONE                     ||
    // =================================================
    public static final Block POLISHED_STONE = registerBlock("polished_stone",
            new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE)));
    public static final Block STONE_PILLAR = registerBlock("stone_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS)));

    // =================================================
    // ||                SANDSTONE                    ||
    // =================================================
    public static final Block POLISHED_SANDSTONE = registerBlock("polished_sandstone",
            new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE)));
    public static final Block SANDSTONE_BRICKS = registerBlock("sandstone_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
    public static final Block SANDSTONE_PILLAR = registerBlock("sandstone_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS)));

    // =================================================
    // ||                RED SANDSTONE                ||
    // =================================================
    public static final Block POLISHED_RED_SANDSTONE = registerBlock("polished_red_sandstone",
            new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE)));
    public static final Block RED_SANDSTONE_BRICKS = registerBlock("red_sandstone_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE)));
    public static final Block RED_SANDSTONE_PILLAR = registerBlock("red_sandstone_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS)));

    // =================================================
    // ||                WROUGHT IRON                ||
    // =================================================
    public static final Block WROUGHT_IRON_BLOCK = registerBlock("wrought_iron_block",
            new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
    public static final Block CUT_WROUGHT_IRON_BLOCK = registerBlock("cut_wrought_iron_block",
            new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));

    // =================================================
    // ||                   CLAY WALL                 ||
    // =================================================
    public static final Block CLAY_WALL = registerBlock("clay_wall",
            new Block(AbstractBlock.Settings.copy(Blocks.CLAY)));
    public static final Block CLAY_WALL_CROSS = registerBlock("clay_wall_cross",
            new Block(AbstractBlock.Settings.copy(Blocks.CLAY)));
    public static final Block CLAY_WALL_DIAGONAL = registerBlock("clay_wall_diagonal",
            new ClayWallDiagBlock(AbstractBlock.Settings.copy(Blocks.CLAY)));

    // =================================================
    // ||                  IRONWOOD                   ||
    // =================================================
    public static final Identifier IRONWOOD_SIGN_TEXTURE = Identifier.of(Resprouted.MOD_ID, "entity/signs/ironwood");
    public static final Identifier IRONWOOD_HANGING_SIGN_TEXTURE = Identifier.of(Resprouted.MOD_ID, "entity/signs/hanging/ironwood");
    public static final Identifier IRONWOOD_HANGING_SIGN_GUI_TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/gui/hanging_signs/ironwood");

    public static final Block IRONWOOD_LEAVES = registerBlock("ironwood_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block IRONWOOD_LOG = registerBlock("ironwood_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block IRONWOOD_WOOD = registerBlock("ironwood_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_IRONWOOD_LOG = registerBlock("stripped_ironwood_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block STRIPPED_IRONWOOD_WOOD = registerBlock("stripped_ironwood_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block IRONWOOD_PLANKS = registerBlock("ironwood_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block IRONWOOD_SAPLING = registerBlock("ironwood_sapling",
            new SaplingBlock(ModSaplingGenerators.IRONWOOD, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
    public static final Block IRONWOOD_STAIRS = registerBlock("ironwood_stairs",
            new StairsBlock(ModBlocks.IRONWOOD_PLANKS.getDefaultState(), AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block IRONWOOD_SLAB = registerBlock("ironwood_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block IRONWOOD_BUTTON = registerBlock("ironwood_button",
            new ButtonBlock(BlockSetType.ACACIA, 2, AbstractBlock.Settings.create().strength(2f).requiresTool().noCollision()));
    public static final Block IRONWOOD_PRESSURE_PLATE = registerBlock("ironwood_pressure_plate",
            new PressurePlateBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block IRONWOOD_FENCE = registerBlock("ironwood_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block IRONWOOD_FENCE_GATE = registerBlock("ironwood_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block IRONWOOD_DOOR = registerBlock("ironwood_door",
            new DoorBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(2f).requiresTool().nonOpaque()));
    public static final Block IRONWOOD_TRAPDOOR = registerBlock("ironwood_trapdoor",
            new TrapdoorBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(2f).requiresTool().nonOpaque()));

    public static final Block IRONWOOD_SIGN = registerBlock("ironwood_sign",
            new TerraformSignBlock(IRONWOOD_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_SIGN)));
    public static final Block IRONWOOD_WALL_SIGN = Registry.register(Registries.BLOCK, Identifier.of(Resprouted.MOD_ID, "ironwood_wall_sign"),
            new TerraformWallSignBlock(IRONWOOD_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN).dropsLike(ModBlocks.IRONWOOD_SIGN)));
    public static final Block IRONWOOD_HANGING_SIGN = registerBlock("ironwood_hanging_sign",
            new TerraformHangingSignBlock(IRONWOOD_HANGING_SIGN_TEXTURE,IRONWOOD_HANGING_SIGN_GUI_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_HANGING_SIGN)));
    public static final Block IRONWOOD_WALL_HANGING_SIGN = registerBlock("ironwood_wall_hanging_sign",
            new TerraformWallHangingSignBlock(IRONWOOD_HANGING_SIGN_TEXTURE,IRONWOOD_HANGING_SIGN_GUI_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_WALL_HANGING_SIGN).dropsLike(ModBlocks.IRONWOOD_HANGING_SIGN)));

    // =================================================
    // ||                    OLIVE                    ||
    // =================================================
    public static final Identifier OLIVE_SIGN_TEXTURE = Identifier.of(Resprouted.MOD_ID, "entity/signs/olive");
    public static final Identifier OLIVE_HANGING_SIGN_TEXTURE = Identifier.of(Resprouted.MOD_ID, "entity/signs/hanging/olive");
    public static final Identifier OLIVE_HANGING_SIGN_GUI_TEXTURE = Identifier.of(Resprouted.MOD_ID, "textures/gui/hanging_signs/olive");

    public static final Block OLIVE_LEAVES = registerBlock("olive_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block OLIVE_LOG = registerBlock("olive_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block STRIPPED_OLIVE_WOOD = registerBlock("stripped_olive_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block OLIVE_WOOD = registerBlock("olive_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_OLIVE_LOG = registerBlock("stripped_olive_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block OLIVE_PLANKS = registerBlock("olive_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block OLIVE_SAPLING = registerBlock("olive_sapling",
            new SaplingBlock(ModSaplingGenerators.OLIVE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
    public static final Block OLIVE_STAIRS = registerBlock("olive_stairs",
            new StairsBlock(ModBlocks.OLIVE_PLANKS.getDefaultState(), AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block OLIVE_SLAB = registerBlock("olive_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block OLIVE_BUTTON = registerBlock("olive_button",
            new ButtonBlock(BlockSetType.ACACIA, 2, AbstractBlock.Settings.create().strength(2f).requiresTool().noCollision()));
    public static final Block OLIVE_PRESSURE_PLATE = registerBlock("olive_pressure_plate",
            new PressurePlateBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block OLIVE_FENCE = registerBlock("olive_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block OLIVE_FENCE_GATE = registerBlock("olive_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(2f).requiresTool()));
    public static final Block OLIVE_DOOR = registerBlock("olive_door",
            new DoorBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(2f).requiresTool().nonOpaque()));
    public static final Block OLIVE_TRAPDOOR = registerBlock("olive_trapdoor",
            new TrapdoorBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(2f).requiresTool().nonOpaque()));
    public static final Block OLIVE_SIGN = registerBlock("olive_sign",
            new TerraformSignBlock(OLIVE_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_SIGN)));
    public static final Block OLIVE_WALL_SIGN = Registry.register(Registries.BLOCK, Identifier.of(Resprouted.MOD_ID, "olive_wall_sign"),
            new TerraformWallSignBlock(OLIVE_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN).dropsLike(ModBlocks.OLIVE_SIGN)));
    public static final Block OLIVE_HANGING_SIGN = registerBlock("olive_hanging_sign",
            new TerraformHangingSignBlock(OLIVE_HANGING_SIGN_TEXTURE,OLIVE_HANGING_SIGN_GUI_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_HANGING_SIGN)));
    public static final Block OLIVE_WALL_HANGING_SIGN = registerBlock("olive_wall_hanging_sign",
            new TerraformWallHangingSignBlock(OLIVE_HANGING_SIGN_TEXTURE,OLIVE_HANGING_SIGN_GUI_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_WALL_HANGING_SIGN).dropsLike(ModBlocks.OLIVE_HANGING_SIGN)));


    // =================================================
    // ||               PAINTED PLANKS                ||
    // =================================================
    public static final Block BLACK_PAINTED_PLANKS = registerBlock("black_painted_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block BLUE_PAINTED_PLANKS = registerBlock("blue_painted_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block BROWN_PAINTED_PLANKS = registerBlock("brown_painted_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block CYAN_PAINTED_PLANKS = registerBlock("cyan_painted_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block GRAY_PAINTED_PLANKS = registerBlock("gray_painted_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block GREEN_PAINTED_PLANKS = registerBlock("green_painted_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block LIGHT_BLUE_PAINTED_PLANKS = registerBlock("light_blue_painted_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block LIME_PAINTED_PLANKS = registerBlock("lime_painted_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block MAGENTA_PAINTED_PLANKS = registerBlock("magenta_painted_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block ORANGE_PAINTED_PLANKS = registerBlock("orange_painted_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block PINK_PAINTED_PLANKS = registerBlock("pink_painted_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block PURPLE_PAINTED_PLANKS = registerBlock("purple_painted_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block RED_PAINTED_PLANKS = registerBlock("red_painted_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block LIGHT_GRAY_PAINTED_PLANKS = registerBlock("light_gray_painted_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block WHITE_PAINTED_PLANKS = registerBlock("white_painted_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block YELLOW_PAINTED_PLANKS = registerBlock("yellow_painted_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));

    // =================================================
    // ||                  CHAIRS                     ||
    // =================================================
    public static final Block ACACIA_CHAIR = registerBlock("acacia_chair",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block CHERRY_CHAIR = registerBlock("cherry_chair",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block BAMBOO_CHAIR = registerBlock("bamboo_chair",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block CRIMSON_CHAIR = registerBlock("crimson_chair",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block OAK_CHAIR = registerBlock("oak_chair",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block BIRCH_CHAIR = registerBlock("birch_chair",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block SPRUCE_CHAIR = registerBlock("spruce_chair",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block JUNGLE_CHAIR = registerBlock("jungle_chair",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block WARPED_CHAIR = registerBlock("warped_chair",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block DARK_OAK_CHAIR = registerBlock("dark_oak_chair",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block MANGROVE_CHAIR = registerBlock("mangrove_chair",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block IRONWOOD_CHAIR = registerBlock("ironwood_chair",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block OLIVE_CHAIR = registerBlock("olive_chair",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    // =================================================
    // ||                  STOOLS                     ||
    // =================================================
    public static final Block OAK_STOOL = registerBlock("oak_stool",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block ACACIA_STOOL = registerBlock("acacia_stool",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block CHERRY_STOOL = registerBlock("cherry_stool",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block BAMBOO_STOOL = registerBlock("bamboo_stool",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block CRIMSON_STOOL = registerBlock("crimson_stool",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block BIRCH_STOOL = registerBlock("birch_stool",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block SPRUCE_STOOL = registerBlock("spruce_stool",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block JUNGLE_STOOL = registerBlock("jungle_stool",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block WARPED_STOOL = registerBlock("warped_stool",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block DARK_OAK_STOOL = registerBlock("dark_oak_stool",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block MANGROVE_STOOL = registerBlock("mangrove_stool",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block IRONWOOD_STOOL = registerBlock("ironwood_stool",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block OLIVE_STOOL = registerBlock("olive_stool",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    // =================================================
    // ||                  TABLES                     ||
    // =================================================
    public static final Block OAK_TABLE = registerBlock("oak_table",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().solid()));
    public static final Block DARK_OAK_TABLE = registerBlock("dark_oak_table",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().solid()));
    public static final Block MANGROVE_TABLE = registerBlock("mangrove_table",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().solid()));
    public static final Block CHERRY_TABLE = registerBlock("cherry_table",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().solid()));
    public static final Block BIRCH_TABLE = registerBlock("birch_table",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().solid()));
    public static final Block SPRUCE_TABLE = registerBlock("spruce_table",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().solid()));
    public static final Block ACACIA_TABLE = registerBlock("acacia_table",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().solid()));
    public static final Block JUNGLE_TABLE = registerBlock("jungle_table",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().solid()));
    public static final Block BAMBOO_TABLE = registerBlock("bamboo_table",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().solid()));
    public static final Block CRIMSON_TABLE = registerBlock("crimson_table",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().solid()));
    public static final Block WARPED_TABLE = registerBlock("warped_table",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().solid()));
    public static final Block IRONWOOD_TABLE = registerBlock("ironwood_table",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().solid()));
    public static final Block OLIVE_TABLE = registerBlock("olive_table", new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().solid()));

    // =================================================
    // ||                 CABINETS                    ||
    // =================================================

    public static final Block ACACIA_CABINET_BLOCK = registerCabinetBlock("acacia_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_PLANKS).nonOpaque()));

    public static final Block CHERRY_CABINET_BLOCK = registerCabinetBlock("cherry_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_PLANKS).nonOpaque()));

    public static final Block OAK_CABINET_BLOCK = registerCabinetBlock("oak_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block DARK_OAK_CABINET_BLOCK = registerCabinetBlock("dark_oak_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.DARK_OAK_PLANKS).nonOpaque()));

    public static final Block BIRCH_CABINET_BLOCK = registerCabinetBlock("birch_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_PLANKS).nonOpaque()));

    public static final Block SPRUCE_CABINET_BLOCK = registerCabinetBlock("spruce_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).nonOpaque()));

    public static final Block MANGROVE_CABINET_BLOCK = registerCabinetBlock("mangrove_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_PLANKS).nonOpaque()));

    public static final Block JUNGLE_CABINET_BLOCK = registerCabinetBlock("jungle_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_PLANKS).nonOpaque()));

    public static final Block CRIMSON_CABINET_BLOCK = registerCabinetBlock("crimson_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.CRIMSON_PLANKS).nonOpaque()));

    public static final Block WARPED_CABINET_BLOCK = registerCabinetBlock("warped_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.WARPED_PLANKS).nonOpaque()));

    public static final Block IRONWOOD_CABINET_BLOCK = registerCabinetBlock("ironwood_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(ModBlocks.IRONWOOD_PLANKS).nonOpaque()));

    public static final Block OLIVE_CABINET_BLOCK = registerCabinetBlock("olive_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(ModBlocks.OLIVE_PLANKS).nonOpaque()));

    // =================================================
    // ||                  CHAINS                     ||
    // =================================================
    public static final Block GOLDEN_CHAIN = registerBlock("golden_chain",
            new ChainBlock(AbstractBlock.Settings.copy(Blocks.CHAIN)));
    public static final Block COPPER_CHAIN = registerBlock("copper_chain",
            new CopperChainBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(Blocks.CHAIN)));
    public static final Block EXPOSED_COPPER_CHAIN = registerBlock("exposed_copper_chain",
            new CopperChainBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(Blocks.CHAIN)));
    public static final Block WEATHERED_COPPER_CHAIN = registerBlock("weathered_copper_chain",
            new CopperChainBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(Blocks.CHAIN)));
    public static final Block OXIDIZED_COPPER_CHAIN = registerBlock("oxidized_copper_chain",
            new CopperChainBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(Blocks.CHAIN)));
    //WAXED
    public static final Block WAXED_COPPER_CHAIN = registerBlock("waxed_copper_chain", new ChainBlock(AbstractBlock.Settings.copy(Blocks.CHAIN)));
    public static final Block WAXED_EXPOSED_COPPER_CHAIN = registerBlock("waxed_exposed_copper_chain", new ChainBlock(AbstractBlock.Settings.copy(Blocks.CHAIN)));
    public static final Block WAXED_OXIDIZED_COPPER_CHAIN = registerBlock("waxed_oxidized_copper_chain", new ChainBlock(AbstractBlock.Settings.copy(Blocks.CHAIN)));
    public static final Block WAXED_WEATHERED_COPPER_CHAIN = registerBlock("waxed_weathered_copper_chain", new ChainBlock(AbstractBlock.Settings.copy(Blocks.CHAIN)));

    // =================================================
    // ||                   LANTERNS                  ||
    // =================================================
    //GOLDEN
    public static final Block GOLDEN_LANTERN = registerBlock("golden_lantern",
            new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block GOLDEN_SOUL_LANTERN = registerBlock("golden_soul_lantern",
            new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    //COPPER
    public static final Block COPPER_LANTERN = registerBlock("copper_lantern",
            new CopperLanternBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block EXPOSED_COPPER_LANTERN = registerBlock("exposed_copper_lantern",
            new CopperLanternBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block WEATHERED_COPPER_LANTERN = registerBlock("weathered_copper_lantern",
            new CopperLanternBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block OXIDIZED_COPPER_LANTERN = registerBlock("oxidized_copper_lantern",
            new CopperLanternBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(Blocks.LANTERN)));

    public static final Block COPPER_SOUL_LANTERN = registerBlock("copper_soul_lantern",
            new CopperLanternBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block EXPOSED_COPPER_SOUL_LANTERN = registerBlock("exposed_copper_soul_lantern",
            new CopperLanternBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block WEATHERED_COPPER_SOUL_LANTERN = registerBlock("weathered_copper_soul_lantern",
            new CopperLanternBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block OXIDIZED_COPPER_SOUL_LANTERN = registerBlock("oxidized_copper_soul_lantern",
            new CopperLanternBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(Blocks.LANTERN)));
    //WAXED
    public static final Block WAXED_COPPER_LANTERN = registerBlock("waxed_copper_lantern", new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block WAXED_EXPOSED_COPPER_LANTERN = registerBlock("waxed_exposed_copper_lantern", new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block WAXED_OXIDIZED_COPPER_LANTERN = registerBlock("waxed_oxidized_copper_lantern", new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block WAXED_WEATHERED_COPPER_LANTERN = registerBlock("waxed_weathered_copper_lantern", new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));

    public static final Block WAXED_COPPER_SOUL_LANTERN = registerBlock("waxed_copper_soul_lantern", new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block WAXED_EXPOSED_COPPER_SOUL_LANTERN = registerBlock("waxed_exposed_copper_soul_lantern", new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block WAXED_OXIDIZED_COPPER_SOUL_LANTERN = registerBlock("waxed_oxidized_copper_soul_lantern", new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block WAXED_WEATHERED_COPPER_SOUL_LANTERN = registerBlock("waxed_weathered_copper_soul_lantern", new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));

    // =================================================
    // ||                 CHANDELIER                  ||
    // =================================================
    public static final Block CHANDELIER = registerBlock("chandelier", new ChandelierBlock(AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));
    public static final Block GOLDEN_CHANDELIER = registerBlock("golden_chandelier", new ChandelierBlock(AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));

    public static final Block COPPER_CHANDELIER = registerBlock("copper_chandelier", new CopperChandelierBlock(Oxidizable.OxidationLevel.UNAFFECTED,AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));
    public static final Block EXPOSED_COPPER_CHANDELIER = registerBlock("exposed_copper_chandelier", new CopperChandelierBlock(Oxidizable.OxidationLevel.EXPOSED,AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));
    public static final Block WEATHERED_COPPER_CHANDELIER = registerBlock("weathered_copper_chandelier", new CopperChandelierBlock(Oxidizable.OxidationLevel.WEATHERED,AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));
    public static final Block OXIDIZED_COPPER_CHANDELIER = registerBlock("oxidized_copper_chandelier", new CopperChandelierBlock(Oxidizable.OxidationLevel.OXIDIZED,AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));

    public static final Block WAXED_COPPER_CHANDELIER = registerBlock("waxed_copper_chandelier", new ChandelierBlock(AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));
    public static final Block WAXED_EXPOSED_COPPER_CHANDELIER = registerBlock("waxed_exposed_copper_chandelier", new ChandelierBlock(AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));
    public static final Block WAXED_WEATHERED_COPPER_CHANDELIER = registerBlock("waxed_weathered_copper_chandelier", new ChandelierBlock(AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));
    public static final Block WAXED_OXIDIZED_COPPER_CHANDELIER = registerBlock("waxed_oxidized_copper_chandelier", new ChandelierBlock(AbstractBlock.Settings.copy(Blocks.ANVIL).nonOpaque().solid()));

    // =================================================
    // ||                CANDLE HOLDER                ||
    // =================================================
    //Iron
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
    //Gold
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

    // =================================================
    // ||                AGRICULTURE                  ||
    // =================================================
    public static final Block ROPE = registerBlock("rope", new RopeBlock(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block GRAPE_LEAVES = registerBlockWithoutBlockItem("grape_leaves", new GrapeLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block GRAPE_STEM = registerBlockWithoutBlockItem("grape_stem", new GrapeStemBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)));
    public static final Block FERTILE_SOIL = registerBlock("fertile_soil", new FertileSoilBlock(AbstractBlock.Settings.copy(Blocks.FARMLAND)));
    public static final Block STAKE = registerBlock("stake", new StakeBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block TOMATO_CROP = registerBlockWithoutBlockItem("tomato_crop", new TomatoCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)));
    public static final Block CHILI_CROP = registerBlockWithoutBlockItem("chili_crop", new ChiliCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)));
    public static final Block APPLE_TREE = registerBlockWithoutBlockItem("apple_tree", new AppleTreeBlock(AbstractBlock.Settings.create().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY).mapColor(MapColor.DARK_GREEN)));
    public static final Block APPLE_LEAVES = registerBlock("apple_leaves", new FruitingLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).nonOpaque()));
    public static final Block APPLE_SAPLING = registerBlock("apple_sapling", new SaplingBlock(ModSaplingGenerators.APPLE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).nonOpaque().solidBlock(Blocks::never).suffocates(Blocks::never)));
    public static final Block CRUSHING_TUB = registerBlock("crushing_tub", new CrushingTubBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block EVAPORATING_BASIN = registerBlock("evaporating_basin", new EvaporatingBasinBlock(AbstractBlock.Settings.copy(Blocks.TERRACOTTA).nonOpaque()));
    public static final Block LIQUID_BARREL = registerBlock("liquid_barrel" , new LiquidBarrelBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block BLUE_BERRY_CAKE = registerBlock("blue_berry_cake", new CustomCakeBlock(AbstractBlock.Settings.copy(Blocks.CAKE)));

    // =================================================
    // ||                 FLUID BLOCKS                ||
    // =================================================
    public static final Block HONEY_FLUID_BLOCK = registerBlockWithoutBlockItem("honey_fluid_block", new
            HoneyFluidBlock(ModFluids.HONEY_STILL,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().dropsNothing().nonOpaque())));

    public static final Block APPLE_JUICE_FLUID_BLOCK = registerBlockWithoutBlockItem("apple_juice_fluid_block", new
            FluidBlock(ModFluids.APPLE_JUICE_STILL,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block GOLDEN_APPLE_JUICE_FLUID_BLOCK = registerBlockWithoutBlockItem("golden_apple_juice_fluid_block", new
            FluidBlock(ModFluids.GOLDEN_APPLE_JUICE_STILL,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block GRAPE_JUICE_FLUID_BLOCK = registerBlockWithoutBlockItem("grape_juice_fluid_block", new
            FluidBlock(ModFluids.GRAPE_JUICE_STILL,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block SWEET_BERRY_JUICE_FLUID_BLOCK = registerBlockWithoutBlockItem("sweet_berry_juice_fluid_block", new
            FluidBlock(ModFluids.SWEET_BERRY_JUICE_STILL,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block OLIVE_OIL_FLUID_BLOCK = registerBlockWithoutBlockItem("olive_oil_fluid_block", new
            FluidBlock(ModFluids.OLIVE_OIL_STILL,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block VANTA_OIL_FLUID_BLOCK = registerBlockWithoutBlockItem("vanta_oil_fluid_block", new
            FluidBlock(ModFluids.VANTA_OIL_STILL,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block GLOW_BERRY_JUICE_FLUID_BLOCK = registerBlockWithoutBlockItem("glow_berry_juice_fluid_block", new
            FluidBlock(ModFluids.GLOW_BERRY_JUICE_STILL,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block IRON_BERRY_JUICE_FLUID_BLOCK = registerBlockWithoutBlockItem("iron_berry_juice_fluid_block", new
            FluidBlock(ModFluids.IRON_BERRY_JUICE_STILL,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    public static final Block ALE_WORT_FLUID_BLOCK = registerBlockWithoutBlockItem("ale_wort_fluid_block", new
            FluidBlock(ModFluids.ALE_WORT_STILL,(AbstractBlock.Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing())));

    // =================================================
    // ||                   HERBS                     ||
    // =================================================
    public static final Block ALOE_VERA_BLOCK = registerBlockWithoutBlockItem("aloe_vera_block",
            new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.ALOE_VERA));
    public static final Block HORSETAIL_BLOCK = registerBlockWithoutBlockItem("horsetail_block",
            new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.HORSETAIL));
    public static final Block COHOSH_BLOCK = registerBlockWithoutBlockItem("cohosh_block",
            new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.COHOSH));
    public static final Block CHAMOMILE_BLOCK = registerBlockWithoutBlockItem("chamomile_block",
            new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.CHAMOMILE));
    public static final Block CLOUDSBLUFF_BLOCK = registerBlockWithoutBlockItem("cloudsbluff_block",
            new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.CLOUDSBLUFF));
    public static final Block BLOOD_ORCHID_BLOCK = registerBlockWithoutBlockItem("blood_orchid_block",
            new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.BLOOD_ORCHID));
    public static final Block GINSENG_BLOCK = registerBlockWithoutBlockItem("ginseng_block",
            new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.GINSENG));
    public static final Block MARSHMALLOW_BLOCK = registerBlockWithoutBlockItem("marshmallow_block",
            new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.MARSH_MALLOW));
    public static final Block VANTA_LILY_BLOCK = registerBlockWithoutBlockItem("vanta_lily_block",
            new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.VANTA_LILY));
    public static final Block WIND_THISTLE_BLOCK = registerBlockWithoutBlockItem("wind_thistle_block",
            new HerbBlock(AbstractBlock.Settings.copy(Blocks.WHEAT), () -> ModItems.WIND_THISTLE));
    public static final Block MOONCAP_MUSHROOM = registerBlockWithoutBlockItem("mooncap_mushroom",
            new CustomMushroomBlock(AbstractBlock.Settings.copy(Blocks.WHEAT).luminance(state -> 3), () -> ModItems.MOONCAP_MUSHROOM));
    public static final Block DEATHSTALK_MUSHROOM = registerBlockWithoutBlockItem("deathstalk_mushroom",
            CustomMushroomBlock.NetherMushroom(AbstractBlock.Settings.copy(Blocks.WHEAT).luminance(state -> 3), () -> ModItems.DEATHSTALK_MUSHROOM));
    public static final Block CORE_ROOT = registerBlockWithoutBlockItem("core_root",
            new CustomMushroomBlock(AbstractBlock.Settings.copy(Blocks.WHEAT).luminance(state -> 3), () -> ModItems.CORE_ROOT));

    // =================================================
    // ||                   BUSHES                    ||
    // =================================================
    public static final Block BLUE_BERRY_BUSH = registerBlockWithoutBlockItem("blue_berry_bush",
            new BlueBerrieBush(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)));

    // =================================================
    // ||                  ALCHEMY                    ||
    // =================================================
    public static final Block CONDENSER = registerBlock("condenser", new CondenserBlock(AbstractBlock.Settings.copy(Blocks.BRICKS).luminance(state -> state.get(LIT) ? 15 : 0).nonOpaque()));
    public static final Block ADVANCED_CONDENSER = registerBlock("advanced_condenser", new AdvancedCondenserBlock(AbstractBlock.Settings.copy(Blocks.NETHER_BRICKS).luminance(state -> state.get(AdvancedCondenserBlock.LIT) ? 15 : 0).nonOpaque()));
    public static final Block RETORT = registerBlock("retort", new RetortBlock(AbstractBlock.Settings.copy(Blocks.BRICKS).nonOpaque()));
    public static final Block ADVANCED_RETORT = registerBlock("advanced_retort", new AdvancedRetortBlock(AbstractBlock.Settings.copy(Blocks.NETHER_BRICKS).nonOpaque()));

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
    private static Block registerCabinetBlock(String name, Block block) {
        ModCabinetRegistry.registerCabinet(block);
        return registerBlock(name, block);
    }
    public static void registerModBlocks(){
        Resprouted.LOGGER.info("Registering Blocks for " + Resprouted.MOD_ID);
    }
}

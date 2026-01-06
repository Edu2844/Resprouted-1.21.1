package net.edu.resprouted;

import net.edu.resprouted.advancement.criterion.ModCriteria;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.book.CatalogData;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.entity.ModEntities;
import net.edu.resprouted.fluid.ModFluidAttributes;
import net.edu.resprouted.mixin.util.BlockEntityTypeAccessor;
import net.edu.resprouted.networking.ModMessages;
import net.edu.resprouted.registry.ResproutedResourcePacks;
import net.edu.resprouted.registry.CabinetRegistry;
import net.edu.resprouted.screen.ModScreenHandlers;
import net.edu.resprouted.resource.reload.FluidItemLoader;
import net.edu.resprouted.event.ModCommonEvents;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.ModItemGroups;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.registry.ResproutedCommonRegistry;
import net.edu.resprouted.util.RopeDispenser;
import net.edu.resprouted.world.ModFoliagePlacerTypes;
import net.edu.resprouted.world.ModTrunkPlacerTypes;
import net.edu.resprouted.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.resource.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Resprouted implements ModInitializer {
	public static final String MOD_ID = "resprouted";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static ResproutedCommonConfiguration COMMON_CONFIG = new ResproutedCommonConfiguration();

	//Player id's
	public static final UUID NUMEROS_UUID = UUID.fromString("ac2a8bac-654d-4b24-b455-e99678d78de1");
	public static final UUID EDU_UUID = UUID.fromString("a0f68e03-3194-4ddf-a057-9bd2ef6f53b1");
	public static final UUID IDOLS_UUID = UUID.fromString("1a29732f-a1c7-420d-bdb7-d85d985b8ce8");

	// Other mod id's
	public static final String EATING_ANIMATIONS_MOD_ID = "eatinganimationid";
	public static final String FARMERS_DELIGHT_MOD_ID = "farmersdelight";
	public static final String AMENDMENTS_MOD_ID = "amendments";

	public static boolean isModLoaded(String modId) {
		return FabricLoader.getInstance().isModLoaded(modId);
	}

	@Override
	public void onInitialize() {
		Resprouted.COMMON_CONFIG = ResproutedCommonConfiguration.load();
		ResproutedResourcePacks.registerBuiltinResourcePacks();
		CatalogData.init();
		ModEffects.registerEffects();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerModBlockEntities();
		ModFluids.registerModFluids();
		ModFluidAttributes.registerFluidAttributes();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroup();
		ModEntities.registerModEntities();
		ModWorldGeneration.generateModWorldGen();
		ModFoliagePlacerTypes.registerModFoliagePlacers();
		ModTrunkPlacerTypes.registerModTrunkPlacers();
		ModRecipes.registerRecipes();
		ModCommonEvents.registerEvents();
		ResproutedCommonRegistry.registerModStuffs();
		CabinetRegistry.getAllCabinetBlocks();
		ModMessages.registerPayloads();
		ModMessages.registerC2SPackets();
		ModScreenHandlers.registerScreenHandlers();
		ModDataComponentTypes.registerDataComponentTypes();
		ModCriteria.registerCriteria();
		DispenserBlock.registerBehavior((ModBlocks.ROPE.asItem()), new RopeDispenser());
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new FluidItemLoader());

		Set<Block> vanillaSigns = ((BlockEntityTypeAccessor) BlockEntityType.SIGN).getBlocks();
		Set<Block> newSigns = new HashSet<>(vanillaSigns);
		newSigns.add(ModBlocks.OLIVE_SIGN);
		newSigns.add(ModBlocks.OLIVE_WALL_SIGN);
		newSigns.add(ModBlocks.IRONWOOD_SIGN);
		newSigns.add(ModBlocks.IRONWOOD_WALL_SIGN);
		((BlockEntityTypeAccessor) BlockEntityType.SIGN).setBlocks(newSigns);

		Set<Block> vanillaHanging = ((BlockEntityTypeAccessor) BlockEntityType.HANGING_SIGN).getBlocks();
		Set<Block> newHanging = new HashSet<>(vanillaHanging);
		newHanging.add(ModBlocks.OLIVE_HANGING_SIGN);
		newHanging.add(ModBlocks.OLIVE_WALL_HANGING_SIGN);
		newHanging.add(ModBlocks.IRONWOOD_HANGING_SIGN);
		newHanging.add(ModBlocks.IRONWOOD_WALL_HANGING_SIGN);
		((BlockEntityTypeAccessor) BlockEntityType.HANGING_SIGN).setBlocks(newHanging);

	}
}
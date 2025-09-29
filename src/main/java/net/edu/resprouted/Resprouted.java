package net.edu.resprouted;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.book.CatalogData;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.entity.ModEntities;
import net.edu.resprouted.mixin.helper.BlockEntityTypeAccessor;
import net.edu.resprouted.networking.ModMessages;
import net.edu.resprouted.registry.ResproutedResourcePacks;
import net.edu.resprouted.util.CabinetRegistry;
import net.edu.resprouted.screen.ModScreenHandlers;
import net.edu.resprouted.resource.reload.FluidContainerLoader;
import net.edu.resprouted.event.ModEvents;
import net.edu.resprouted.util.BottleInteractions;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.ModItemGroups;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.registry.ResproutedRegistry;
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

public class Resprouted implements ModInitializer {
	public static final String MOD_ID = "resprouted";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static ResproutedConfiguration CONFIG = new ResproutedConfiguration();

	//Other mods id's
	public static final String EATING_ANIMATIONS_MOD_ID = "eatinganimationid";
	public static final String FARMERS_DELIGHT_MOD_ID = "farmersdelight";
	public static final String AMENDMENTS_MOD_ID = "amendments";
	public static final String BOTANY_POTS_MOD_ID = "botanypots";

	public static boolean isModLoaded(String modId) {
		return FabricLoader.getInstance().isModLoaded(modId);
	}


	@Override
	public void onInitialize() {
		Resprouted.CONFIG = ResproutedConfiguration.load();
		ResproutedResourcePacks.registerBuiltinResourcePacks();
		CatalogData.init();
		ModEffects.registerEffects();
		ModFluids.registerModFluids();
		BottleInteractions.registerFluidInteractions();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerModBlockEntities();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroup();
		ModEntities.registerModEntities();
		ModWorldGeneration.generateModWorldGen();
		ModFoliagePlacerTypes.registerModFoliagePlacers();
		ModTrunkPlacerTypes.registerModTrunkPlacers();
		ModRecipes.registerRecipes();
		ModEvents.registerModEvents();
		ResproutedRegistry.RegisterModStuffs();
		CabinetRegistry.getAllCabinetBlocks();
		ModMessages.registerPayloads();
		ModMessages.registerC2SPackets();
		ModScreenHandlers.registerScreenHandlers();
		ModDataComponentTypes.registerDataComponentTypes();
		DispenserBlock.registerBehavior((ModBlocks.ROPE.asItem()), new RopeDispenser());
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new FluidContainerLoader());

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
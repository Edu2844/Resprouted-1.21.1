package net.edu.resprouted;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.entity.ModEntities;
import net.edu.resprouted.networking.ModMessages;
import net.edu.resprouted.registry.ResproutedCabinetRegistry;
import net.edu.resprouted.screen.ModScreenHandlers;
import net.edu.resprouted.resource.reload.FluidContainerLoader;
import net.edu.resprouted.event.ModEvents;
import net.edu.resprouted.util.BottleInteractions;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.ModItemGroups;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.registry.ModRegistry;
import net.edu.resprouted.world.ModFoliagePlacerTypes;
import net.edu.resprouted.world.ModTrunkPlacerTypes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Resprouted implements ModInitializer {
	public static final String MOD_ID = "resprouted";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static ResproutedConfiguration CONFIG;

	@Override
	public void onInitialize() {
		ModEffects.registerEffects();
		ModFluids.registerModFluids();
		BottleInteractions.registerFluidInteractions();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerModBlockEntities();
		ModItemGroups.registerItemGroup();
		ModEntities.registerModEntities();
		ModFoliagePlacerTypes.registerModFoliagePlacers();
		ModTrunkPlacerTypes.registerModTrunkPlacers();
		ModRecipes.registerRecipes();
		ModEvents.registerModEvents();
		ModRegistry.RegisterModStuffs();
		ResproutedCabinetRegistry.getAllCabinetBlocks();
		ModMessages.registerPayloads();
		ModMessages.registerC2SPackets();
		ModScreenHandlers.registerScreenHandlers();
		ModDataComponentTypes.registerDataComponentTypes();
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new FluidContainerLoader());
		CONFIG = ResproutedConfiguration.load();
		System.out.println("Resprouted config loaded:");
	}
}
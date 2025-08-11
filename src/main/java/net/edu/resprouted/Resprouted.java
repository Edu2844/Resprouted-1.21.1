package net.edu.resprouted;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.entity.ModEntities;
import net.edu.resprouted.networking.ModMessages;
import net.edu.resprouted.registry.ModCabinetRegistry;
import net.edu.resprouted.screen.ModScreenHandlers;
import net.edu.resprouted.util.FluidContainerLoader;
import net.edu.resprouted.event.ModEvents;
import net.edu.resprouted.util.BottleInteractionsUtils;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.ModItemGroups;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.registry.ModStakeCropRegistry;
import net.edu.resprouted.registry.ModRegistry;
import net.edu.resprouted.world.ModFoliagePlacerTypes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Resprouted implements ModInitializer {
	public static final String MOD_ID = "resprouted";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		ModFluids.registerModFluids();
		BottleInteractionsUtils.registerFluidInteractions();
		ModEffects.registerEffects();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerModBlockEntities();
		ModItemGroups.registerItemGroup();
		ModEntities.registerModEntities();
		ModFoliagePlacerTypes.registerModFoliagePlacers();
		ModRecipes.registerRecipes();
		ModEvents.registerModEvents();
		ModRegistry.RegisterModStuffs();
		ModStakeCropRegistry.registerStakeCropsSeeds();
		ModCabinetRegistry.getAllCabinetBlocks();
		ModMessages.registerPayloads();
		ModMessages.registerC2SPackets();
		ModScreenHandlers.registerScreenHandlers();
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new FluidContainerLoader());


		/*
		FluidStorage.SIDED.registerForBlockEntity(
				(blockEntity, direction) -> blockEntity.fluidStorage, ModBlockEntities.CRUSHING_TUB_BE);
		FluidStorage.SIDED.registerForBlockEntity(
				(blockEntity, direction) -> blockEntity.liquidbarrel, ModBlockEntities.LIQUID_BARREL_BE);
		FluidStorage.SIDED.registerForBlockEntity(
				(blockEntity, direction) -> blockEntity.basin, ModBlockEntities.EVAPORATING_BASIN_BE);*/
	}

}
package net.edu.resprouted;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.entity.ModEntities;
import net.edu.resprouted.networking.ModMessages;
import net.edu.resprouted.util.FluidContainerLoader;
import net.edu.resprouted.event.ModEvents;
import net.edu.resprouted.util.BottleInteractionsUtil;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.ModItemGroups;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.registry.ModStakeCropsRegistries;
import net.edu.resprouted.registry.ModRegistries;
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
		BottleInteractionsUtil.registerFluidInteractions();
		ModEffects.registerEffects();
		ModItemGroups.registerItemGroup();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerModBlockEntities();
		ModEntities.registerModEntities();
		ModFoliagePlacerTypes.registerModFoliagePlacers();
		ModRecipes.registerRecipes();
		ModEvents.registerModEvents();
		ModRegistries.RegisterModStuffs();
		ModStakeCropsRegistries.registerStakeCropsSeeds();
		ModMessages.registerPayloads();
		ModMessages.registerC2SPackets();
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
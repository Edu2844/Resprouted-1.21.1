package net.edu.resprouted;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.entity.ModEntities;
import net.edu.resprouted.event.FireballPayload;
import net.edu.resprouted.event.ModEvents;
import net.edu.resprouted.fluid.ModFluidInteractions;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.ModItemGroups;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.util.ModStakeCrops;
import net.edu.resprouted.world.ModFoliagePlacerTypes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Resprouted implements ModInitializer {
	public static final String MOD_ID = "resprouted";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModFluids.registerModFluids();
		ModFluidInteractions.registerFluidInteractions();
		ModEffects.registerEffects();
		ModItemGroups.registerItemGroup();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerModBlockEntities();
		ModEntities.registerModEntities();
		ModFoliagePlacerTypes.registerModFoliagePlacers();
		ModRecipes.registerRecipes();
		ModEvents.registerModEvents();
		ModStakeCrops.registerStakeCropsSeeds();
		// =================================================
		// ||                  STRIPPABLE                 ||
		// =================================================
		StrippableBlockRegistry.register(ModBlocks.IRONWOOD_LOG, ModBlocks.STRIPPED_IRONWOOD_LOG);
		StrippableBlockRegistry.register(ModBlocks.IRONWOOD_WOOD, ModBlocks.STRIPPED_IRONWOOD_WOOD);
		StrippableBlockRegistry.register(ModBlocks.OLIVE_LOG, ModBlocks.STRIPPED_OLIVE_LOG);
		StrippableBlockRegistry.register(ModBlocks.OLIVE_WOOD, ModBlocks.STRIPPED_OLIVE_WOOD);

		// =================================================
		// ||               FLAMMABLE BLOCKS              ||
		// =================================================
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.IRONWOOD_LOG,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.IRONWOOD_WOOD,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_IRONWOOD_LOG,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_IRONWOOD_WOOD,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.IRONWOOD_PLANKS,5,20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.IRONWOOD_LEAVES,30,60);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.OLIVE_LOG,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.OLIVE_WOOD,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_OLIVE_LOG,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_OLIVE_WOOD,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.OLIVE_PLANKS,5,20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.OLIVE_LEAVES,30,60);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.APPLE_LEAVES,30,60);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.CRUSHING_TUB,5,20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.LIQUID_BARREL,5,20);

		// =================================================
		// ||              OXIDIZABLE BLOCKS              ||
		// =================================================
		//CHAINs
		OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.COPPER_CHAIN, ModBlocks.EXPOSED_COPPER_CHAIN);
		OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_CHAIN, ModBlocks.WEATHERED_COPPER_CHAIN);
		OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_CHAIN, ModBlocks.OXIDIZED_COPPER_CHAIN);

		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.COPPER_CHAIN, ModBlocks.WAXED_COPPER_CHAIN);
		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_CHAIN, ModBlocks.WAXED_EXPOSED_COPPER_CHAIN);
		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_CHAIN, ModBlocks.WAXED_EXPOSED_COPPER_CHAIN);
		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_CHAIN, ModBlocks.WAXED_WEATHERED_COPPER_CHAIN);
		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_CHAIN, ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN);

		//LANTERNS
		OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.COPPER_LANTERN, ModBlocks.EXPOSED_COPPER_LANTERN);
		OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_LANTERN, ModBlocks.WEATHERED_COPPER_LANTERN);
		OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_LANTERN, ModBlocks.OXIDIZED_COPPER_LANTERN);

		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.COPPER_LANTERN, ModBlocks.WAXED_COPPER_LANTERN);
		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_LANTERN, ModBlocks.WAXED_EXPOSED_COPPER_LANTERN);
		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_LANTERN, ModBlocks.WAXED_EXPOSED_COPPER_LANTERN);
		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_LANTERN, ModBlocks.WAXED_WEATHERED_COPPER_LANTERN);
		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_LANTERN, ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN);

		OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.COPPER_SOUL_LANTERN, ModBlocks.EXPOSED_COPPER_SOUL_LANTERN);
		OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_SOUL_LANTERN, ModBlocks.WEATHERED_COPPER_SOUL_LANTERN);
		OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_SOUL_LANTERN, ModBlocks.OXIDIZED_COPPER_SOUL_LANTERN);

		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.COPPER_SOUL_LANTERN, ModBlocks.WAXED_COPPER_SOUL_LANTERN);
		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_SOUL_LANTERN, ModBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN);
		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_SOUL_LANTERN, ModBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN);
		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_SOUL_LANTERN, ModBlocks.WAXED_WEATHERED_COPPER_SOUL_LANTERN);
		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_SOUL_LANTERN, ModBlocks.WAXED_OXIDIZED_COPPER_SOUL_LANTERN);

		//CHANDELIER
		OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.COPPER_CHANDELIER, ModBlocks.EXPOSED_COPPER_CHANDELIER);
		OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_CHANDELIER, ModBlocks.WEATHERED_COPPER_CHANDELIER);
		OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_CHANDELIER, ModBlocks.OXIDIZED_COPPER_CHANDELIER);

		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.COPPER_CHANDELIER, ModBlocks.WAXED_COPPER_CHANDELIER);
		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_CHANDELIER, ModBlocks.WAXED_EXPOSED_COPPER_CHANDELIER);
		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_CHANDELIER, ModBlocks.WAXED_EXPOSED_COPPER_CHANDELIER);
		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_CHANDELIER, ModBlocks.WAXED_WEATHERED_COPPER_CHANDELIER);
		OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_CHANDELIER, ModBlocks.WAXED_OXIDIZED_COPPER_CHANDELIER);

		// =================================================
		// ||               COMPOSTABLE ITEMS             ||
		// =================================================
		CompostingChanceRegistry.INSTANCE.add(ModItems.ALOE_VERA, 0.5F);
		CompostingChanceRegistry.INSTANCE.add(ModItems.HORSETAIL, 0.5F);
		CompostingChanceRegistry.INSTANCE.add(ModItems.COHOSH, 0.5F);
		CompostingChanceRegistry.INSTANCE.add(ModItems.CHAMOMILE, 0.5F);
		CompostingChanceRegistry.INSTANCE.add(ModItems.CLOUDSBLUFF, 0.5F);
		CompostingChanceRegistry.INSTANCE.add(ModItems.BLOOD_ORCHID, 0.5F);
		CompostingChanceRegistry.INSTANCE.add(ModItems.VANTA_LILY, 0.5F);
		CompostingChanceRegistry.INSTANCE.add(ModItems.MARSH_MALLOW, 0.5F);
		CompostingChanceRegistry.INSTANCE.add(ModItems.CORE_ROOT, 0.5F);
		CompostingChanceRegistry.INSTANCE.add(ModItems.MOONCAP_MUSHROOM, 0.5F);
		CompostingChanceRegistry.INSTANCE.add(ModItems.DEATHSTALK_MUSHROOM, 0.5F);
		CompostingChanceRegistry.INSTANCE.add(ModItems.TOMATO, 0.7F);
		CompostingChanceRegistry.INSTANCE.add(ModItems.TOMATO_SEEDS, 0.7F);
		CompostingChanceRegistry.INSTANCE.add(ModItems.CHILI_PEPPER, 0.7F);
		CompostingChanceRegistry.INSTANCE.add(ModItems.CHILI_PEPPER_SEEDS, 0.7F);
		CompostingChanceRegistry.INSTANCE.add(ModItems.GRAPES, 0.7F);
		CompostingChanceRegistry.INSTANCE.add(ModItems.GRAPE_SEEDS, 0.7F);

		PayloadTypeRegistry.playC2S().register(FireballPayload.ID, FireballPayload.CODEC);

		ServerPlayNetworking.registerGlobalReceiver(FireballPayload.ID, (payload, context) -> {
			ServerPlayerEntity player = context.player();
			MinecraftServer server = player.getServer();
			assert server != null;

			if (player.getItemCooldownManager().isCoolingDown(Items.SNOWBALL)) {
				return;
			}
			server.execute(() -> {

				if (player.hasStatusEffect(ModEffects.FIRE_POWER)) {
					Vec3d lookVec = player.getRotationVec(1.0F);

					FireballEntity fireball = new FireballEntity(player.getWorld(), player,
							new Vec3d(lookVec.x, lookVec.y, lookVec.z), 1);
					fireball.setPosition(
							player.getX() + lookVec.x,
							player.getEyeY() + lookVec.y,
							player.getZ() + lookVec.z
					);
					player.getWorld().spawnEntity(fireball);
					player.getItemCooldownManager().set(Items.SNOWBALL, 20);
				}
			});
		});
		// =================================================
		// ||                FLUID STORAGE                ||
		// =================================================
		FluidStorage.SIDED.registerForBlockEntity(
				(blockEntity, direction) -> blockEntity.fluidStorage, ModBlockEntities.CRUSHING_TUB_BE);
		FluidStorage.SIDED.registerForBlockEntity(
				(blockEntity, direction) -> blockEntity.liquidbarrel, ModBlockEntities.LIQUID_BARREL_BE);
		FluidStorage.SIDED.registerForBlockEntity(
				(blockEntity, direction) -> blockEntity.basin, ModBlockEntities.EVAPORATING_BASIN_BE);
	}

}
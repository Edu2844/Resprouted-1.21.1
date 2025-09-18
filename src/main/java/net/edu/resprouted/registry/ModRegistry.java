package net.edu.resprouted.registry;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.entity.ModEntities;
import net.edu.resprouted.item.ModItems;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.registry.Registry;

import static net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry.registerOxidizableBlockPair;
import static net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry.registerWaxableBlockPair;
import static net.fabricmc.fabric.api.registry.StrippableBlockRegistry.register;

public class ModRegistry {
    public static void RegisterModStuffs(){
        registerStrippables();
        registerFlammables();
        registerOxidizablesAndWaxables();
        registerCompostables();
        registerFluidStorages();
        registerBoats();
    }
    public static void registerStrippables() {
        register(ModBlocks.IRONWOOD_LOG, ModBlocks.STRIPPED_IRONWOOD_LOG);
        register(ModBlocks.IRONWOOD_WOOD, ModBlocks.STRIPPED_IRONWOOD_WOOD);
        register(ModBlocks.OLIVE_LOG, ModBlocks.STRIPPED_OLIVE_LOG);
        register(ModBlocks.OLIVE_WOOD, ModBlocks.STRIPPED_OLIVE_WOOD);
    }
    public static void registerFlammables() {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        registry.add(ModBlocks.IRONWOOD_LOG,5,5);
        registry.add(ModBlocks.IRONWOOD_WOOD,5,5);
        registry.add(ModBlocks.STRIPPED_IRONWOOD_LOG,5,5);
        registry.add(ModBlocks.STRIPPED_IRONWOOD_WOOD,5,5);
        registry.add(ModBlocks.IRONWOOD_PLANKS,5,20);
        registry.add(ModBlocks.IRONWOOD_LEAVES,30,60);
        registry.add(ModBlocks.OLIVE_LOG,5,5);
        registry.add(ModBlocks.OLIVE_WOOD,5,5);
        registry.add(ModBlocks.STRIPPED_OLIVE_LOG,5,5);
        registry.add(ModBlocks.STRIPPED_OLIVE_WOOD,5,5);
        registry.add(ModBlocks.OLIVE_PLANKS,5,20);
        registry.add(ModBlocks.OLIVE_LEAVES,30,60);
        registry.add(ModBlocks.APPLE_LEAVES,30,60);
        registry.add(ModBlocks.TOMATO_CROP,30,60);
        registry.add(ModBlocks.CHILI_CROP,30,60);
        registry.add(ModBlocks.CRUSHING_TUB,5,20);
        registry.add(ModBlocks.LIQUID_BARREL,5,20);
    }
    public static void registerOxidizablesAndWaxables() {
        //Chain
        registerOxidizableBlockPair(ModBlocks.COPPER_CHAIN, ModBlocks.EXPOSED_COPPER_CHAIN);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_CHAIN, ModBlocks.WEATHERED_COPPER_CHAIN);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_CHAIN, ModBlocks.OXIDIZED_COPPER_CHAIN);

        registerWaxableBlockPair(ModBlocks.COPPER_CHAIN, ModBlocks.WAXED_COPPER_CHAIN);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_CHAIN, ModBlocks.WAXED_EXPOSED_COPPER_CHAIN);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_CHAIN, ModBlocks.WAXED_EXPOSED_COPPER_CHAIN);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_CHAIN, ModBlocks.WAXED_WEATHERED_COPPER_CHAIN);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_CHAIN, ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN);

        //Lantern
        registerOxidizableBlockPair(ModBlocks.COPPER_LANTERN, ModBlocks.EXPOSED_COPPER_LANTERN);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_LANTERN, ModBlocks.WEATHERED_COPPER_LANTERN);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_LANTERN, ModBlocks.OXIDIZED_COPPER_LANTERN);

        registerWaxableBlockPair(ModBlocks.COPPER_LANTERN, ModBlocks.WAXED_COPPER_LANTERN);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_LANTERN, ModBlocks.WAXED_EXPOSED_COPPER_LANTERN);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_LANTERN, ModBlocks.WAXED_EXPOSED_COPPER_LANTERN);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_LANTERN, ModBlocks.WAXED_WEATHERED_COPPER_LANTERN);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_LANTERN, ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN);

        registerOxidizableBlockPair(ModBlocks.COPPER_SOUL_LANTERN, ModBlocks.EXPOSED_COPPER_SOUL_LANTERN);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_SOUL_LANTERN, ModBlocks.WEATHERED_COPPER_SOUL_LANTERN);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_SOUL_LANTERN, ModBlocks.OXIDIZED_COPPER_SOUL_LANTERN);

        registerWaxableBlockPair(ModBlocks.COPPER_SOUL_LANTERN, ModBlocks.WAXED_COPPER_SOUL_LANTERN);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_SOUL_LANTERN, ModBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_SOUL_LANTERN, ModBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_SOUL_LANTERN, ModBlocks.WAXED_WEATHERED_COPPER_SOUL_LANTERN);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_SOUL_LANTERN, ModBlocks.WAXED_OXIDIZED_COPPER_SOUL_LANTERN);

        //Chandelier
        registerOxidizableBlockPair(ModBlocks.COPPER_CHANDELIER, ModBlocks.EXPOSED_COPPER_CHANDELIER);
        registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_CHANDELIER, ModBlocks.WEATHERED_COPPER_CHANDELIER);
        registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_CHANDELIER, ModBlocks.OXIDIZED_COPPER_CHANDELIER);

        registerWaxableBlockPair(ModBlocks.COPPER_CHANDELIER, ModBlocks.WAXED_COPPER_CHANDELIER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_CHANDELIER, ModBlocks.WAXED_EXPOSED_COPPER_CHANDELIER);
        registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_CHANDELIER, ModBlocks.WAXED_EXPOSED_COPPER_CHANDELIER);
        registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_CHANDELIER, ModBlocks.WAXED_WEATHERED_COPPER_CHANDELIER);
        registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_CHANDELIER, ModBlocks.WAXED_OXIDIZED_COPPER_CHANDELIER);

    }
    public static void registerCompostables() {
        CompostingChanceRegistry registry = CompostingChanceRegistry.INSTANCE;
        registry.add(ModItems.ALOE_VERA, 0.5F);
        registry.add(ModItems.HORSETAIL, 0.5F);
        registry.add(ModItems.COHOSH, 0.5F);
        registry.add(ModItems.CHAMOMILE, 0.5F);
        registry.add(ModItems.CLOUDSBLUFF, 0.5F);
        registry.add(ModItems.BLOOD_ORCHID, 0.5F);
        registry.add(ModItems.VANTA_LILY, 0.5F);
        registry.add(ModItems.MARSH_MALLOW, 0.5F);
        registry.add(ModItems.CORE_ROOT, 0.5F);
        registry.add(ModItems.MOONCAP_MUSHROOM, 0.5F);
        registry.add(ModItems.DEATHSTALK_MUSHROOM, 0.5F);
        registry.add(ModItems.IRON_BERRIES, 0.5F);
        registry.add(ModItems.TOMATO, 0.5F);
        registry.add(ModItems.TOMATO_SEEDS, 0.5F);
        registry.add(ModItems.CHILI_PEPPER, 0.5F);
        registry.add(ModItems.CHILI_PEPPER_SEEDS, 0.5F);
        registry.add(ModItems.GRAPES, 0.5F);
        registry.add(ModItems.GRAPE_SEEDS, 0.5F);
        registry.add(ModItems.OLIVES, 0.5F);
    }
    public static void registerFluidStorages() {
        FluidStorage.SIDED.registerForBlockEntity((crushingTubBE, direction2) -> crushingTubBE.getFluidTankProvider(), ModBlockEntities.CRUSHING_TUB_BE);
        FluidStorage.SIDED.registerForBlockEntity((liquidBarrelBE, direction1) -> liquidBarrelBE.getFluidTankProvider(), ModBlockEntities.LIQUID_BARREL_BE);
        FluidStorage.SIDED.registerForBlockEntity((evaporatingBasinBE, direction) -> evaporatingBasinBE.getFluidTankProvider(), ModBlockEntities.EVAPORATING_BASIN_BE);
        FluidStorage.SIDED.registerForBlockEntity((condenserBE, direction1) -> condenserBE.getFluidTankProvider(), ModBlockEntities.CONDENSER_BE);
        FluidStorage.SIDED.registerForBlockEntity((advancedCondenserBE, direction) -> advancedCondenserBE.getFluidTankProvider(), ModBlockEntities.ADVANCED_CONDENSER_BE);
    }
    public static void registerBoats(){
        TerraformBoatType OliveBoat = new TerraformBoatType.Builder()
                .item(ModItems.OLIVE_BOAT)
                .chestItem(ModItems.OLIVE_CHEST_BOAT)
                .planks(ModBlocks.OLIVE_PLANKS.asItem())
                .build();
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, ModEntities.OLIVE_BOAT_KEY, OliveBoat);

        TerraformBoatType IronwoodBoat = new TerraformBoatType.Builder()
                .item(ModItems.IRONWOOD_BOAT)
                .chestItem(ModItems.IRONWOOD_CHEST_BOAT)
                .planks(ModBlocks.IRONWOOD_PLANKS.asItem())
                .build();
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, ModEntities.IRONWOOD_BOAT_KEY, IronwoodBoat);
    }
}

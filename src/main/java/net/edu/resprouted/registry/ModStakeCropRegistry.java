package net.edu.resprouted.registry;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.custom.agriculture.StakeCropBlock;
import net.edu.resprouted.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class ModStakeCropRegistry {
    private static final Map<Item, Supplier<BlockState>> REGISTRY = new HashMap<>();
    public static void register(Item seedItem, Supplier<BlockState> cropStateSupplier) {
        REGISTRY.put(seedItem, cropStateSupplier);
    }
    public static Optional<BlockState> getCropForSeed(Item seedItem) {
        Supplier<BlockState> supplier = REGISTRY.get(seedItem);
        return supplier != null ? Optional.of(supplier.get()) : Optional.empty();
    }
    public static void registerStakeCropsSeeds() {
        ModStakeCropRegistry.register(ModItems.TOMATO_SEEDS, () -> ModBlocks.TOMATO_CROP.getDefaultState().with(StakeCropBlock.AGE, 0));
        ModStakeCropRegistry.register(ModItems.CHILI_PEPPER_SEEDS, () -> ModBlocks.CHILI_CROP.getDefaultState().with(StakeCropBlock.AGE, 0));
    }
}

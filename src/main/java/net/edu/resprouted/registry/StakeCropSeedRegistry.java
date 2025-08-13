package net.edu.resprouted.registry;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class StakeCropSeedRegistry {
    private static final Map<Item, Supplier<BlockState>> REGISTRY = new HashMap<>();
    public static void register(Item seedItem, Supplier<BlockState> cropStateSupplier) {
        REGISTRY.put(seedItem, cropStateSupplier);
    }
    public static Optional<BlockState> getCropForSeed(Item seedItem) {
        Supplier<BlockState> supplier = REGISTRY.get(seedItem);
        return supplier != null ? Optional.of(supplier.get()) : Optional.empty();
    }
}

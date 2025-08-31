package net.edu.resprouted.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class HarvestUtils {
    private final List<DropEntry> drops = new ArrayList<>();
    private Random random;

    public static HarvestUtils create(Random random) {
        HarvestUtils util = new HarvestUtils();
        util.random = random;
        return util;
    }

    public HarvestUtils add(Item item, int count, float chance) {
        drops.add(new DropEntry(item, () -> count, chance));
        return this;
    }

    public HarvestUtils add(Item item, int count) {
        return add(item, count, 1.0f);
    }

    public HarvestUtils add(Item item) {
        return add(item, 1, 1.0f);
    }

    public HarvestUtils add(Item item, int minCount, int maxCount, float chance) {
        drops.add(new DropEntry(item, () ->
                minCount + random.nextInt(maxCount - minCount + 1), chance));
        return this;
    }

    public HarvestUtils add(Item item, int minCount, int maxCount) {
        return add(item, minCount, maxCount, 1.0f);
    }

    public HarvestUtils add(Item item, float chance) {
        return add(item, 1, chance);
    }

    public List<ItemStack> generate() {
        List<ItemStack> result = new ArrayList<>();

        for (DropEntry entry : drops) {
            if (random.nextFloat() < entry.chance) {
                result.add(new ItemStack(entry.item, entry.countSupplier.get()));
            }
        }

        return result;
    }

    private record DropEntry(Item item, Supplier<Integer> countSupplier, float chance) {

    }
}
package net.edu.resprouted.item.custom;

import net.edu.resprouted.util.misc.GrassSeedsRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class StakeCropSeedItem extends Item {
    private final Block cropBlock;

    public StakeCropSeedItem(Block cropBlock, Settings settings) {
        super(settings);
        this.cropBlock = cropBlock;

        GrassSeedsRegistry.registerSeed(this);
    }

    public Block getCropBlock() {
        return cropBlock;
    }
}
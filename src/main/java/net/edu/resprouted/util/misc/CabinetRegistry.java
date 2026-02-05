package net.edu.resprouted.util.misc;

import net.edu.resprouted.block.custom.decorative.CabinetBlock;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class CabinetRegistry {
    private static final List<Block> REGISTERED_CABINETS = new ArrayList<>();

    public static void registerCabinet(Block block) {
        if (block instanceof CabinetBlock) {
            REGISTERED_CABINETS.add(block);
        } else {
            throw new IllegalArgumentException("The block must be an instance of CabinetBlock");
        }
    }

    public static Block[] getRegisteredCabinets() {
        return REGISTERED_CABINETS.toArray(new Block[0]);
    }
}

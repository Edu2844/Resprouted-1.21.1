package net.edu.resprouted.registry;

import net.minecraft.block.BlockSetType;

public class ResproutedBlockSetTypes {
    public static final BlockSetType IRONWOOD = ResproutedBlockSetTypes.register(new BlockSetType("ironwood"));
    public static final BlockSetType OLIVE = ResproutedBlockSetTypes.register(new BlockSetType("olive"));

    private static BlockSetType register(BlockSetType blockSetType) {
        return blockSetType;
    }
}

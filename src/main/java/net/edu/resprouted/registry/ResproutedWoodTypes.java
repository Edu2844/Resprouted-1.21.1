package net.edu.resprouted.registry;

import net.edu.resprouted.Resprouted;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.WoodType;
import net.minecraft.util.Identifier;

public class ResproutedWoodTypes {
    public static final WoodType IRONWOOD = new WoodTypeBuilder().register(Identifier.of(Resprouted.MOD_ID, "ironwood"), ResproutedBlockSetTypes.IRONWOOD);
    public static final WoodType OLIVE = new WoodTypeBuilder().register(Identifier.of(Resprouted.MOD_ID, "olive"), ResproutedBlockSetTypes.OLIVE);

    public static String getName(WoodType woodType) {
        String fullName = woodType.name();
        return fullName.contains(":") ? fullName.split(":")[1] : fullName;
    }
}
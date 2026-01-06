package net.edu.resprouted.compat.jade.component;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.custom.agriculture.GrapeLeavesBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

import static net.edu.resprouted.block.custom.agriculture.GrapeLeavesBlock.DIST;

public class GrapeLeavesComponentProvider implements IBlockComponentProvider {
    public static final GrapeLeavesComponentProvider INSTANCE = new GrapeLeavesComponentProvider();

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        BlockState state = accessor.getBlockState();
        Block block = state.getBlock();

        if (block instanceof GrapeLeavesBlock && (state.get(DIST) > 0) ) {
            int currentAge = state.get(GrapeLeavesBlock.AGE);
            int maxAge = GrapeLeavesBlock.getMaxAge();
            float growthValue = (float) currentAge / maxAge;

            FruitingLeavesComponentProvider.addMaturityTooltip(tooltip, growthValue);
        }
    }

    @Override
    public Identifier getUid() {
        return Identifier.of(Resprouted.MOD_ID, "grape_leaves_mature");
    }
}

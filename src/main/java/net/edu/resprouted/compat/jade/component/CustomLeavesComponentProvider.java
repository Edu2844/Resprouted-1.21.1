package net.edu.resprouted.compat.jade.component;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.custom.agriculture.CustomLeavesBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.theme.IThemeHelper;

public class CustomLeavesComponentProvider implements IBlockComponentProvider {
    public static final CustomLeavesComponentProvider INSTANCE = new CustomLeavesComponentProvider();

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        BlockState state = accessor.getBlockState();
        Block block = state.getBlock();

        if (block instanceof CustomLeavesBlock customLeavesBlock) {
            if (CustomLeavesBlock.isExposedToAir(accessor.getLevel(), accessor.getPosition())) {
                int currentAge = state.get(CustomLeavesBlock.AGE);
                int maxAge = customLeavesBlock.MaxAge();
                float growthValue = (float) currentAge / maxAge;

                addMaturityTooltip(tooltip, growthValue);
            }
        }
    }
    private static void addMaturityTooltip(ITooltip tooltip, float growthValue) {
        MutableText component;

        if (growthValue < 1.0F) {
            component = IThemeHelper.get().info(String.format("%.0f%%", growthValue * 100.0F));
        } else {
            component = IThemeHelper.get().success(Text.translatable("tooltip.jade.crop_mature"));
        }
        tooltip.add(Text.translatable("tooltip.resprouted.leaves.growth", component));
    }
    @Override
    public Identifier getUid() {
        return Identifier.of(Resprouted.MOD_ID, "custom_leaves_progress");
    }
}

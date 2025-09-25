package net.edu.resprouted.block.custom.decorative;

import net.edu.resprouted.block.entity.custom.ModSignBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.SignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class ModStandingSignBlock extends SignBlock {
    public ModStandingSignBlock(WoodType woodType, Settings settings) {
        super(woodType, settings);
    }
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ModSignBlockEntity(pos, state);
    }
}

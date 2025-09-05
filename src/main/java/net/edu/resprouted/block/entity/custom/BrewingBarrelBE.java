package net.edu.resprouted.block.entity.custom;

import net.edu.resprouted.block.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class BrewingBarrelBE extends BlockEntity {

    public BrewingBarrelBE (BlockPos pos, BlockState state) {
        super(ModBlockEntities.BREWING_BARREL_BE, pos, state);
    }
}

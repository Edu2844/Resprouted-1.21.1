package net.edu.resprouted.block.entity.custom;

import net.edu.resprouted.block.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class CondenserBlockEntity extends BlockEntity {

    public CondenserBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CONDENSER_BE, pos, state);
    }
}

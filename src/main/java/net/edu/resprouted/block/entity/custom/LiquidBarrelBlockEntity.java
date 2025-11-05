package net.edu.resprouted.block.entity.custom;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.abstracts.AbstractFluidStorageBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class LiquidBarrelBlockEntity extends AbstractFluidStorageBlockEntity {
    public LiquidBarrelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LIQUID_BARREL_BE, pos, state, FluidConstants.BUCKET * 16);
    }
}

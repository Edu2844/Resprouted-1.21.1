package net.edu.resprouted.block.entity.custom;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.abstracts.AnimatedFluidStorageBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LiquidBarrelBlockEntity extends AnimatedFluidStorageBlockEntity {

    public LiquidBarrelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LIQUID_BARREL_BE, pos, state, FluidConstants.BUCKET * 16);
    }

    @SuppressWarnings("unused")
    public static void tick(World world, BlockPos pos, BlockState state, LiquidBarrelBlockEntity entity) {
        entity.tickFluidAnimation();
    }
}

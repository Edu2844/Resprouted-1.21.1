package net.edu.resprouted.block.custom.agriculture;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HoneyFluidBlock extends FluidBlock {
    public HoneyFluidBlock(FlowableFluid fluid, Settings settings) {
        super(fluid, settings);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);

        if (entity instanceof LivingEntity) {
            Vec3d velocity = entity.getVelocity();
            entity.setVelocity(velocity.x * 0.4, velocity.y, velocity.z * 0.4);
            entity.velocityModified = true;
        }
    }
}

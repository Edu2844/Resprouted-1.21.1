package net.edu.resprouted.block.abstracts;

import net.edu.resprouted.util.SmoothFloat;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;


public abstract class AbstractAnimatedFluidStorageBlockEntity extends AbstractFluidStorageBlockEntity {
    private final SmoothFloat fluidAnimation = new SmoothFloat();
    private long lastFluidAmount = -1;
    private boolean initialized = false;

    public AbstractAnimatedFluidStorageBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, long capacity) {
        super(type, pos, state, capacity);
    }

    @Override
    public void markDirty() {
        super.markDirty();

        if (world != null && world.isClient) {
            long currentAmount = getFluidStorage().getAmount();

            if (currentAmount != lastFluidAmount) {
                float targetHeight = calculateTargetHeight();
                fluidAnimation.setTarget(targetHeight);
                lastFluidAmount = currentAmount;
            }
        }
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);

        if (world != null && world.isClient) {
            long currentAmount = getFluidStorage().getAmount();
            float targetHeight = calculateTargetHeight();

            if (!initialized) {
                fluidAnimation.set(targetHeight);
                lastFluidAmount = currentAmount;
                initialized = true;
            } else if (currentAmount != lastFluidAmount) {
                //Amount changed
                fluidAnimation.setTarget(targetHeight);
                lastFluidAmount = currentAmount;
            }
        }
    }

    protected float calculateTargetHeight() {
        long currentAmount = getFluidStorage().getAmount();
        long capacity = getFluidStorage().getCapacity();
        return MathHelper.clamp((float) currentAmount / capacity, 0, 1);
    }

    public void tickFluidAnimation() {
        if (world != null && world.isClient) {
            fluidAnimation.tick();
        }
    }

    public float getFluidHeight(float tickDelta) {
        return fluidAnimation.get(tickDelta);
    }
}




package net.edu.resprouted.block.abstracts.entity;

import net.edu.resprouted.util.block.SmoothFloat;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

/**
 * Base class for fluid storage block entities with smooth fill animations
 */
public abstract class AbstractAnimatedSingleFluidStorageBlockEntity extends AbstractSingleFluidStorageBlockEntity {
    private final SmoothFloat fluidAnimation = new SmoothFloat();
    private long lastFluidAmount = -1; // Track changes to trigger animations
    private boolean initialized = false; // Prevent animating on first load

    public AbstractAnimatedSingleFluidStorageBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, long capacity) {
        super(type, pos, state, capacity);
    }

    @Override
    public void markDirty() {
        super.markDirty();

        // Client side: detect fluid amount changes and start animation
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
                // First load: set directly without animating
                fluidAnimation.set(targetHeight);
                lastFluidAmount = currentAmount;
                initialized = true;
            } else if (currentAmount != lastFluidAmount) {
                // Subsequent changes: animate to new level
                fluidAnimation.setTarget(targetHeight);
                lastFluidAmount = currentAmount;
            }
        }
    }

    /**
     * Converts fluid amount to fill height (0.0 = empty, 1.0 = full)
     */
    protected float calculateTargetHeight() {
        long currentAmount = getFluidStorage().getAmount();
        long capacity = getFluidStorage().getCapacity();
        return MathHelper.clamp((float) currentAmount / capacity, 0, 1);
    }

    /**
     * Called every tick to update the fluid animation
     * It's called from the block's ticker on client side
     */
    public void tickFluidAnimation() {
        if (world != null && world.isClient) {
            fluidAnimation.tick();
        }
    }

    /**
     * Gets the current interpolated fluid height for rendering
     */
    public float getFluidHeight(float tickDelta) {
        return fluidAnimation.get(tickDelta);
    }
}




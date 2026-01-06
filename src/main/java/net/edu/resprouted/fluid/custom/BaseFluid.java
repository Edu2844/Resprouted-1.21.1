package net.edu.resprouted.fluid.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.Optional;

public abstract class BaseFluid extends FlowableFluid {
    /**
     * @return whether the given fluid an instance of this fluid
     */
    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    }

    /**
     * @return whether the fluid is infinite (which means can be infinitely created like water). In vanilla, it depends on the game rule.
     */
    @Override
    protected boolean isInfinite(World world) {
        return false;
    }

    /**
     * Perform actions when the fluid flows into a replaceable block. Water drops
     * the block's loot table. Lava plays the "block.lava.extinguish" sound.
     */
    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropStacks(state, world, pos, blockEntity);
    }

    /**
     * Lava returns true if it's FluidState is above a certain height and the
     * Fluid is Water.
     *
     * @return whether the given Fluid can flow into this FluidState
     */
    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return false;
    }

    /**
     * Water returns 1. Lava returns 2 in the Overworld and 1 in the Nether.
     */
    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 1;
    }

    /**
     * Water returns 5. Lava returns 30 in the Overworld and 10 in the Nether.
     */
    @Override
    public int getTickRate(WorldView world) {
        return 5;
    }

    /**
     * Water and Lava both return 100.0F.
     */
    @Override
    protected float getBlastResistance() {
        return 100.0F;
    }

    /**
     * Determines the maximum horizontal distance this fluid can flow from its source.
     * Water returns 4 (flows 4 blocks). Lava returns 4 in ultrawarm dimensions (Nether)
     * and 2 in normal dimensions (Overworld).
     *
     * @return the maximum number of blocks this fluid can flow horizontally
     */
    @Override
    protected int getMaxFlowDistance(WorldView world) {
        return 4;
    }

    /**
     * @return the item used to pick up and place this fluid (the bucket item)
     */
    @Override
    public Item getBucketItem() {
        return Items.AIR;
    }

    /**
     * @return the sound event played when filling a bucket with this fluid
     */
    @Override
    public Optional<SoundEvent> getBucketFillSound() {
        return Optional.of(SoundEvents.ITEM_BUCKET_FILL);
    }

    /**
     * @return whether this fluid state is still (not flowing)
     */
    @Override
    public boolean isStill(FluidState state) {
        return false;
    }

    /**
     * Gets the level value (0-8) from the fluid state.
     * Level 8 represents a full source block, lower values represent flowing fluid.
     *
     * @return the level of the fluid state
     */
    @Override
    public int getLevel(FluidState fluidState) {
        return fluidState.get(LEVEL);
    }
}

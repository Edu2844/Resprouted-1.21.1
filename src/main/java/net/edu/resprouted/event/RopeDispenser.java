package net.edu.resprouted.event;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.custom.agriculture.RopeBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class RopeDispenser implements DispenserBehavior {
    private final ItemDispenserBehavior defaultBehavior = new ItemDispenserBehavior();

    @Override
    public ItemStack dispense(BlockPointer pointer, ItemStack stack) {
        ServerWorld world = pointer.world();
        BlockState dispenserState = pointer.state();
        Direction facing = dispenserState.get(DispenserBlock.FACING);
        BlockPos frontPos = pointer.pos().offset(facing);

        if (world.getBlockState(frontPos).getBlock() instanceof RopeBlock) {
            if (addRope(world, frontPos)) {
                ItemStack result = stack.copy();
                result.decrement(1);
                return result;
            }
        }

        else if (world.isAir(frontPos) && world.getBlockState(frontPos.offset(facing)).getBlock() instanceof RopeBlock) {
            BlockPos ropePos = frontPos.offset(facing);
            if (addRope(world, ropePos)) {
                ItemStack result = stack.copy();
                result.decrement(1);
                return result;
            }
        }
        return defaultBehavior.dispense(pointer, stack);
    }

    private boolean addRope(ServerWorld world, BlockPos pos) {
        int yOffset = 1;

        for (; yOffset < 64 && world.getBlockState(pos.down(yOffset)).getBlock() instanceof RopeBlock; yOffset++) {
            BlockState ropeState = world.getBlockState(pos.down(yOffset));
            if (ropeState.get(RopeBlock.AXIS) != Direction.Axis.Y) {
                return false;
            }
        }
        BlockPos targetPos = pos.down(yOffset);

        if (world.getBlockState(targetPos).isAir() && ((RopeBlock) ModBlocks.ROPE).canPlaceAt(ModBlocks.ROPE.getDefaultState(), world, targetPos)) {

            BlockState newRopeState = ModBlocks.ROPE.getDefaultState()
                    .with(RopeBlock.AXIS, Direction.Axis.Y)
                    .with(RopeBlock.WATERLOGGED, false)
                    .with(RopeBlock.HAS_KNOT, false);

            if (world.setBlockState(targetPos, newRopeState, Block.NOTIFY_ALL)) {

                world.playSound(null, targetPos, SoundEvents.BLOCK_WOOL_PLACE, SoundCategory.BLOCKS,
                        1.0F, 0.8F + world.random.nextFloat() * 0.4F);

                return true;
            }
        }
        return false;
    }
}
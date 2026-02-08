package net.edu.resprouted.block.custom.decorative;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class UnfiredBlock extends Block {
    private final Block firedBlock;
    private static final int FIRING_TIME = 400; // 20 seg

    public UnfiredBlock(Settings settings, Block firedBlock) {
        super(settings);
        this.firedBlock = firedBlock;
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (shouldFire(world, pos)) {
            world.setBlockState(pos, firedBlock.getDefaultState(), Block.NOTIFY_ALL);
            world.playSound(null, pos, SoundEvents.BLOCK_DECORATED_POT_BREAK, SoundCategory.BLOCKS, 0.5f, 2.6f);
        }
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!world.isClient && shouldFire((ServerWorld)world, pos)) {
            world.scheduleBlockTick(pos, this, FIRING_TIME);
        }
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient) {
            if (shouldFire((ServerWorld)world, pos)) {
                if (!world.getBlockTickScheduler().isQueued(pos, this)) {
                    world.scheduleBlockTick(pos, this, FIRING_TIME);
                }
            }
        }
    }

    private boolean shouldFire(ServerWorld world, BlockPos pos) {
        BlockPos belowPos = pos.down();
        BlockState belowState = world.getBlockState(belowPos);

        if (world.getDimension().ultrawarm()) {
            return true;
        }

        boolean isOnFire = belowState.isIn(BlockTags.FIRE);
        boolean isOnLitCampfire = belowState.isIn(BlockTags.CAMPFIRES) && belowState.get(CampfireBlock.LIT);

        return isOnLitCampfire || isOnFire;
    }
}
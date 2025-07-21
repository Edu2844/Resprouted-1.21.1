package net.edu.resprouted.block.custom.agriculture;

import net.edu.resprouted.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class AppleLeavesBlock extends CustomLeavesBlock implements Fertilizable {
    private static final int MAX_AGE = 3;
    private static final int GROW_CHANCE = 25;
    public static final IntProperty APPLE_STAGE = IntProperty.of("apple_stage", 0, 3);

    public AppleLeavesBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(APPLE_STAGE, 0)
                .with(PERSISTENT, false)
                .with(DISTANCE, 1));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(APPLE_STAGE);
    }
    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (world.isClient) return;
        if (world.getBaseLightLevel(pos, 0) >= 9 && isExposedToAir(world, pos)) {
            int currentStage = state.get(APPLE_STAGE);
            if (currentStage < MAX_AGE && random.nextInt(GROW_CHANCE) == 0) {
                world.setBlockState(pos, state.with(APPLE_STAGE, currentStage + 1), Block.NOTIFY_LISTENERS);
            }
        }
    }
    private boolean isExposedToAir(WorldView world, BlockPos pos) {
        return world.isAir(pos.down()) ||   // ABAJO - BOTTOM
                world.isAir(pos.north()) || // NORTE - NORTH
                world.isAir(pos.south()) || // SUR - SOUTH
                world.isAir(pos.east()) ||  // ESTE - EAST
                world.isAir(pos.west());    // OESTE - WEST
    }
    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return state.get(APPLE_STAGE) < MAX_AGE && isExposedToAir(world, pos);
    }
    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }
    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if (isExposedToAir(world, pos)) {
            int currentStage = state.get(APPLE_STAGE);
            int nextStage = Math.min(currentStage + 1, MAX_AGE);
            world.setBlockState(pos, state.with(APPLE_STAGE, nextStage), Block.NOTIFY_LISTENERS);
        }
    }
    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.isOf(ModBlocks.APPLE_LEAVES) && state.get(AppleLeavesBlock.APPLE_STAGE) == 3) {
            player.giveItemStack(new ItemStack(Items.APPLE, 1));

            world.setBlockState(pos, state.with(AppleLeavesBlock.APPLE_STAGE, 0), Block.NOTIFY_ALL);
            world.playSound(null, pos, SoundEvents.BLOCK_CAVE_VINES_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.FAIL;
    }
}

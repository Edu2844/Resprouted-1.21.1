package net.edu.resprouted.block.custom.agriculture;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
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

public class FruitingLeavesBlock extends LeavesBlock implements Fertilizable {
    private static final int GROW_CHANCE = 25;
    public static final IntProperty AGE = IntProperty.of("age", 0, 3);

    public FruitingLeavesBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0).with(PERSISTENT, false).with(DISTANCE, 1));
    }

    // ========= PROPIEDADES Y ESTADO ========
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(AGE);
    }
    public int MaxAge() {
        return 3;
    }
    @Override
    public boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) != MaxAge();
    }
    public static boolean isExposedToAir(WorldView world, BlockPos pos) {
        return world.isAir(pos.down()) || world.isAir(pos.north()) || world.isAir(pos.south()) || world.isAir(pos.east()) || world.isAir(pos.west());
    }
    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return state.get(AGE) < MaxAge() && isExposedToAir(world, pos);
    }
    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }
    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if (isExposedToAir(world, pos)) {
            int currentStage = state.get(AGE);
            int nextStage = Math.min(currentStage + 1, MaxAge());
            world.setBlockState(pos, state.with(AGE, nextStage), Block.NOTIFY_LISTENERS);
        }
    }
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (world.isClient) return;
        if (world.getBaseLightLevel(pos, 0) >= 9 && isExposedToAir(world, pos)) {
            int currentStage = state.get(AGE);
            if (currentStage < MaxAge() && random.nextInt(GROW_CHANCE) == 0) {
                world.setBlockState(pos, state.with(AGE, currentStage + 1), Block.NOTIFY_LISTENERS);
            }
        }
    }

    // ========= INTERACCIÓN =========
    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(FruitingLeavesBlock.AGE) == MaxAge()) {
            player.giveItemStack(new ItemStack(getHarvestResult(), Count()));

            world.setBlockState(pos, state.with(FruitingLeavesBlock.AGE, 0), Block.NOTIFY_ALL);
            world.playSound(null, pos, SoundEvents.BLOCK_CAVE_VINES_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 1.0F + world.random.nextFloat() * 0.4F);
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.FAIL;
    }
    public ItemConvertible getHarvestResult() {
        return Items.APPLE;
    }
    protected int Count() {
        return 1;
    }
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            if (!world.isClient) {
                if (state.get(AGE) == MaxAge()) {
                    dropMatureItem(world, pos);
                }
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }
    protected void dropMatureItem(World world, BlockPos pos) {
        dropStack(world, pos, new ItemStack(Items.APPLE, 1));
    }
}

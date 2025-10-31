package net.edu.resprouted.block.custom.agriculture;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
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


public class AppleLeavesBlock extends LeavesBlock implements Fertilizable {
    private static final int GROW_CHANCE = 25;
    public static final int MAX_AGE = 3;
    public static final IntProperty AGE = IntProperty.of("age", 0, 3);

    public AppleLeavesBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0).with(PERSISTENT, false).with(DISTANCE, 1));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(AGE);
    }

    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) != getMaxAge() || state.get(DISTANCE) == 7 && !(Boolean)state.get(PERSISTENT);
    }

    public static boolean isExposedToAir(WorldView world, BlockPos pos) {
        return world.isAir(pos.down()) || world.isAir(pos.north()) || world.isAir(pos.south()) || world.isAir(pos.east()) || world.isAir(pos.west());
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return state.get(AGE) < getMaxAge() && isExposedToAir(world, pos);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if (isExposedToAir(world, pos)) {
            int i = state.get(AGE);
            int j = Math.min(i + 1, getMaxAge());

            world.setBlockState(pos, state.with(AGE, j), Block.NOTIFY_LISTENERS);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (world.isClient)
            return;

        if (world.getBaseLightLevel(pos, 0) >= 9 && isExposedToAir(world, pos)) {
            int k = state.get(AGE);

            if (k < getMaxAge() && random.nextInt(GROW_CHANCE) == 0) {
                world.setBlockState(pos, state.with(AGE, k + 1), Block.NOTIFY_LISTENERS);
            }
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(AppleLeavesBlock.AGE) == getMaxAge()) {
            player.giveItemStack(new ItemStack(getHarvest()));
            world.setBlockState(pos, state.with(AppleLeavesBlock.AGE, 0), Block.NOTIFY_ALL);

            world.playSound(null, pos, SoundEvents.BLOCK_CAVE_VINES_PICK_BERRIES,
                    SoundCategory.BLOCKS, 1.0F, 1.0F);

            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.FAIL;
    }

    public Item getHarvest() {
        return Items.APPLE;
    }
}

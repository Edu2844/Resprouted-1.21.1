package net.edu.resprouted.block.custom.agriculture;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
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
import org.jetbrains.annotations.Nullable;

public class FruitLeavesBlock extends LeavesBlock implements Fertilizable {
    private static final int GROW_CHANCE = 25;
    private static final int MIN_LIGHT_LEVEL = 9;
    public static final int MAX_AGE = 3;
    public static final IntProperty AGE = IntProperty.of("age", 0, 3);

    public FruitLeavesBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(AGE, 0)
                .with(PERSISTENT, false)
                .with(DISTANCE, 1));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(AGE);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return !isMature(state) || state.get(DISTANCE) == 7 && !state.get(PERSISTENT);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return !isMature(state) && isExposedToAir(world, pos);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return !isMature(state);
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if (isExposedToAir(world, pos)) {
            int newAge = Math.min(state.get(AGE) + 1, getMaxAge());
            world.setBlockState(pos, state.with(AGE, newAge), Block.NOTIFY_LISTENERS);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (world.isClient) return;

        if (world.getBaseLightLevel(pos, 0) >= MIN_LIGHT_LEVEL
                && isExposedToAir(world, pos)
                && !isMature(state)
                && random.nextInt(GROW_CHANCE) == 0) {
            int newAge = Math.min(state.get(AGE) + 1, getMaxAge());
            world.setBlockState(pos, state.with(AGE, newAge), Block.NOTIFY_LISTENERS);
        }
    }

    public static boolean isExposedToAir(WorldView world, BlockPos pos) {
        return world.isAir(pos.down()) || world.isAir(pos.north()) || world.isAir(pos.south()) || world.isAir(pos.east()) || world.isAir(pos.west());
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (isMature(state)) {
            harvestFruit(world, pos, state, player, true);
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.FAIL;
    }

    protected void harvestFruit(World world, BlockPos pos, BlockState state, @Nullable PlayerEntity player, boolean giveToPlayer) {
        if (giveToPlayer && player != null) {
            player.giveItemStack(new ItemStack(getHarvest()));

        } else {
            Block.dropStack(world, pos, new ItemStack(getHarvest()));
        }
        world.setBlockState(pos, state.with(AGE, 0), Block.NOTIFY_ALL);
        world.playSound(null, pos, SoundEvents.BLOCK_CAVE_VINES_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

    protected Item getHarvest() {
        return Items.APPLE;
    }

    @Override
    protected void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        super.onProjectileHit(world, state, hit, projectile);
        if (!world.isClient && isMature(state)) {
            harvestFruit(world, hit.getBlockPos(), state, null, false);
        }
    }

    protected boolean isMature(BlockState state) {
        return state.get(AGE) == getMaxAge();
    }

    public int getMaxAge() {
        return MAX_AGE;
    }
}
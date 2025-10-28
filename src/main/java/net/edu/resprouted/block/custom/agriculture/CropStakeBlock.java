package net.edu.resprouted.block.custom.agriculture;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.util.ModTags;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
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
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Optional;

public class CropStakeBlock extends CropBlock {
    public static final int MAX_AGE = 7;
    public static final IntProperty AGE = IntProperty.of("age", 0, 7);
    protected static final VoxelShape CROP_SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);
    protected static final VoxelShape STAKE_SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);

    private final int maxVerticalGrowth;
    private final int resetAge;

    public CropStakeBlock(Settings settings, int maxVerticalGrowth, int resetAge) {
        super(settings);
        this.maxVerticalGrowth = maxVerticalGrowth;
        this.resetAge = resetAge;
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    protected Item getCrop() {
        return null;
    }

    protected Item getSeed() {
        return null;
    }

    protected void giveCrops(PlayerEntity player, Random random) {
        if (getCrop() != null) {
            player.giveItemStack(new ItemStack(getCrop()));
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return getSeed();
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(ModTags.Blocks.FERTILE_SOILS) || floor.isOf(Blocks.FARMLAND);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState below = world.getBlockState(pos.down());
        return below.isIn(ModTags.Blocks.FERTILE_SOILS) || below.getBlock() instanceof CropStakeBlock || below.isOf(Blocks.FARMLAND);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int age = getAge(state);

        // Regular
        if (age < getMaxAge() && world.getBaseLightLevel(pos, 0) >= 9) {
            if (random.nextInt(5) == 0) {
                world.setBlockState(pos, state.with(AGE, age + 1));
            }
        }

        // Vertical
        if (age >= 4 && pos.getY() < world.getTopY()) {
            BlockPos above = pos.up();
            BlockState aboveState = world.getBlockState(above);

            if (aboveState.isOf(ModBlocks.STAKE) && !aboveState.get(StakeBlock.HAS_ROPE)) {
                int height = countBelow(world, pos);
                if (height < getMaxVerticalGrowth()) {
                    world.setBlockState(above, state.with(AGE, 4));
                }
            }
        }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        super.onStateReplaced(state, world, pos, newState, moved);
        if (!state.isOf(newState.getBlock())) {
            if (!world.isClient) {
                world.setBlockState(pos, ModBlocks.STAKE.getDefaultState(), Block.NOTIFY_ALL);
            }
        }
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        if (!world.isClient && !player.isCreative()) {
            Block.dropStacks(state, world, pos, null, player, player.getMainHandStack());
        }
        return state;
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (getAge(state) >= getMaxAge()) {
            if (!world.isClient) {
                harvestBlock(world, pos, state, player);
                harvestVertical(world, pos, player, true);
                harvestVertical(world, pos, player, false);

                world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.FAIL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return CROP_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return STAKE_SHAPE;
    }

    private void harvestVertical(World world, BlockPos origin, PlayerEntity player, boolean upwards) {
        BlockPos.Mutable currentPos = origin.mutableCopy();
        int direction = upwards ? 1 : -1;

        while (true) {
            currentPos.setY(currentPos.getY() + direction);
            BlockState currentState = world.getBlockState(currentPos);

            if (!currentState.isOf(this)) {
                break;
            }
            if (getAge(currentState) >= getMaxAge()) {
                harvestBlock(world, currentPos, currentState, player);
            }
        }
    }

    private void harvestBlock(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        giveCrops(player, world.random);
        world.setBlockState(pos, state.with(getAgeProperty(), getResetAge()), Block.NOTIFY_ALL);
    }

    protected int countBelow(World world, BlockPos pos) {
        int count = 0;
        for (int i = 1; i <= getMaxVerticalGrowth(); i++) {
            BlockState s = world.getBlockState(pos.down(i));
            if (s.getBlock() instanceof CropStakeBlock) count++;
            else break;
        }
        return count;
    }

    protected int getMaxVerticalGrowth() {
        return maxVerticalGrowth;
    }

    protected int getResetAge() {
        return resetAge;
    }

    public static Optional<BlockState> getCropForSeed(Item seedItem) {
        for (Block block : Registries.BLOCK) {
            if (block instanceof CropStakeBlock stakeCrop) {
                Item seed = stakeCrop.getSeed();
                if (seed != null && seed == seedItem) {
                    return Optional.of(block.getDefaultState());
                }
            }
        }
        return Optional.empty();
    }
}
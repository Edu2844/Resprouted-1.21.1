package net.edu.resprouted.block.custom.agriculture;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.util.ModTags;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
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
import java.util.Collections;
import java.util.List;

public class StakeCropBlock extends CropBlock {
    public static final int MAX_AGE = 7;
    public static final IntProperty AGE = IntProperty.of("age", 0, 7);
    protected static final VoxelShape CROP_SHAPE = Block.createCuboidShape(2.0F, 0.0F, 2.0F, 14.0F, 16.0F, 14.0F);
    protected static final VoxelShape STAKE_SHAPE = Block.createCuboidShape(6.0F, 0.0F, 6.0F, 10.0F, 16.0F, 10.0F);

    public StakeCropBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return CROP_SHAPE;
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return STAKE_SHAPE;
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
        return null;
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(ModTags.Blocks.FERTILE_SOILS);
    }
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState below = world.getBlockState(pos.down());
        return below.isIn(ModTags.Blocks.FERTILE_SOILS) || below.getBlock() instanceof StakeCropBlock;
    }
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int age = getAge(state);

        //Crecimiento normal
        if (age < getMaxAge() && world.getBaseLightLevel(pos, 0) >= 9) {
            if (random.nextInt(5) == 0) {
                world.setBlockState(pos, state.with(AGE, age + 1));
            }
        }
        //Crecimiento vertical a través de stake
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
    protected int countBelow(World world, BlockPos pos) {
        int count = 0;
        for (int i = 1; i <= getMaxVerticalGrowth(); i++) {
            BlockState s = world.getBlockState(pos.down(i));
            if (s.getBlock() instanceof StakeCropBlock) count++;
            else break;
        }
        return count;
    }
    protected int getMaxVerticalGrowth() {
        return 2; //Este + 2 = 3 de alto
    }
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            if (!world.isClient) {
                if (getAge(state) >= getMaxAge()) {
                    dropMatureItem(world, pos, state);
                }
                world.setBlockState(pos, ModBlocks.STAKE.getDefaultState(), Block.NOTIFY_ALL);
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }
    protected void dropMatureItem(World world, BlockPos pos, BlockState state) {
        //dropStack(world, pos, new ItemStack((item), (count));
    }
    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }
    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (getAge(state) >= getMaxAge()) {
            if (!world.isClient) {
                for (ItemStack drop : getHarvestResult(world.getRandom())) {
                    player.giveItemStack(drop);
                }
                //Resetear el estado de la planta
                world.setBlockState(pos, state.with(getAgeProperty(), getResetAge()), Block.NOTIFY_ALL);
                world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES,
                        SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.FAIL;
    }
    protected List<ItemStack> getHarvestResult(Random random) {
        return Collections.emptyList();
    }
    protected int getResetAge() {
        return 4;
    }
}


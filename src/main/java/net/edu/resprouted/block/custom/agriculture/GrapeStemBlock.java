package net.edu.resprouted.block.custom.agriculture;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.util.ModTags;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class GrapeStemBlock extends CropBlock{
    public static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;
    public static final IntProperty AGE = IntProperty.of("age", 0, 3);
    public static final int MAX_AGE = 3;
    private static final VoxelShape[] SHAPE_TO_AGE = new VoxelShape[]{
            Block.createCuboidShape(7.0F, 0.0F, 7.0F, 9.0F, 3.0F, 9.0F),
            Block.createCuboidShape(6.0F, 0.0F, 6.0F, 10.0F, 9.0F, 10.0F),
            Block.createCuboidShape(6.0F, 0.0F, 6.0F, 10.0F, 15.0F, 10.0F),
            Block.createCuboidShape(6.0F, 0.0F, 6.0F, 10.0F, 16.0F, 10.0F)
    };
    public GrapeStemBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0).with(AXIS, Direction.Axis.Y));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE).add(AXIS);
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
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(Blocks.FARMLAND)|| floor.isIn(ModTags.Blocks.FERTILE_SOILS);
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.GRAPE_SEEDS;
    }
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE_TO_AGE[this.getAge(state)];
    }
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getLightLevel(pos.up()) >= 9) {
            int currentAge = state.get(AGE);

            if (currentAge < this.getMaxAge()) {
                float growthChance = getGrowthChance();

                if (random.nextInt((int)(25.0F / growthChance) + 1) == 0) {
                    world.setBlockState(pos, state.with(AGE, currentAge + 1), Block.NOTIFY_LISTENERS);
                }
            } else {
                //Revisa que exista una cuerda arriba
                BlockState aboveState = world.getBlockState(pos.up());
                if (aboveState.isOf(ModBlocks.ROPE) && aboveState.get(RopeBlock.AXIS) != Direction.Axis.Y) {
                    float growthChance = getGrowthChance();

                    if (random.nextInt((int)(25.0F / growthChance) + 1) == 0) {
                        Direction.Axis axis = aboveState.get(RopeBlock.AXIS);
                        world.setBlockState(pos.up(),
                                ModBlocks.GRAPE_LEAVES.getDefaultState()
                                        .with(GrapeLeavesBlock.AXIS, axis)
                                        .with(GrapeLeavesBlock.DIST, 0),
                                Block.NOTIFY_ALL);
                    }
                }
            }
        }
    }
    protected static float getGrowthChance() {
        return 7.0F;
    }
    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return state.get(AGE) < getMaxAge() ||
                (state.get(AGE) == getMaxAge()
                        && world.getBlockState(pos.up()).isOf(ModBlocks.ROPE)
                        && world.getBlockState(pos.up()).get(RopeBlock.AXIS) != Direction.Axis.Y);
    }
    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int currentAge = state.get(AGE);
        int maxAge = getMaxAge();

        if (currentAge < maxAge) {
            int newAge = currentAge + getBonemealAgeIncrease();
            if (newAge > maxAge) {
                newAge = maxAge;
            }
            world.setBlockState(pos, state.with(AGE, newAge), Block.NOTIFY_LISTENERS);
        } else {
            BlockState aboveState = world.getBlockState(pos.up());
            if (aboveState.isOf(ModBlocks.ROPE)) {
                Direction.Axis axis = aboveState.get(RopeBlock.AXIS);
                world.setBlockState(pos.up(),
                        ModBlocks.GRAPE_LEAVES.getDefaultState().with(GrapeLeavesBlock.AXIS, axis), Block.NOTIFY_ALL);
            }
        }
    }
    protected int getBonemealAgeIncrease() {
        return Random.create().nextBetween(2, 5);
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState();
    }
    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }
    @Override
    public BlockSoundGroup getSoundGroup(BlockState state) {
        if (state.get(GrapeStemBlock.AGE) == 3) {
            return BlockSoundGroup.WOOD;
        }
        return super.getSoundGroup(state);
    }
}

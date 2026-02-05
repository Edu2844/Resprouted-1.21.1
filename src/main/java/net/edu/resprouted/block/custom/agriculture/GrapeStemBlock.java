package net.edu.resprouted.block.custom.agriculture;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.util.misc.ModTags;
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
            Block.createCuboidShape(7.0, 0.0, 7.0, 9.0, 3.0, 9.0),
            Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 9.0, 10.0),
            Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 15.0, 10.0),
            Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 16.0, 10.0)
    };
    public GrapeStemBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(AGE, 0)
                .with(AXIS, Direction.Axis.Y));
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
            int i = state.get(AGE);

            if (i < this.getMaxAge()) {

                if (random.nextInt((int)(25.0F / getGrowthChance()) + 1) == 0) {
                    world.setBlockState(pos, state.with(AGE, i + 1), Block.NOTIFY_LISTENERS);
                }

            } else {
                // Check if rope exists
                BlockState j = world.getBlockState(pos.up());
                if (j.isOf(ModBlocks.ROPE) && j.get(RopeBlock.AXIS) != Direction.Axis.Y) {

                    if (random.nextInt((int)(25.0F / getGrowthChance()) + 1) == 0) {

                        world.setBlockState(pos.up(),
                                ModBlocks.GRAPE_LEAVES.getDefaultState()
                                        .with(GrapeLeavesBlock.AXIS, j.get(RopeBlock.AXIS))
                                        .with(GrapeLeavesBlock.DIST, 0),
                                Block.NOTIFY_ALL);
                    }
                }
            }
        }
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
        int k = state.get(AGE);
        int l = getMaxAge();

        if (k < l) {
            int m = k + getBonemealAgeIncrease();

            if (m > l) {
                m = l;
            }
            world.setBlockState(pos, state.with(AGE, m), Block.NOTIFY_LISTENERS);

        } else {
            BlockState n = world.getBlockState(pos.up());

            if (n.isOf(ModBlocks.ROPE)) {
                world.setBlockState(pos.up(), ModBlocks.GRAPE_LEAVES.getDefaultState().with(GrapeLeavesBlock.AXIS, n.get(RopeBlock.AXIS)), Block.NOTIFY_ALL);
            }
        }
    }

    protected int getBonemealAgeIncrease() {
        return Random.create().nextBetween(2, 5);
    }

    protected static float getGrowthChance() {
        return 7.0F;
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

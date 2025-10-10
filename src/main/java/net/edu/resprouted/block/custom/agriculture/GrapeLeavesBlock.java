package net.edu.resprouted.block.custom.agriculture;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import java.util.List;

public class GrapeLeavesBlock extends Block implements Fertilizable {
    private static final VoxelShape BRANCH_Z_SHAPE = Block.createCuboidShape(3.0, 3.0, 0.0, 13.0, 13.0, 16.0);
    private static final VoxelShape BRANCH_X_SHAPE = Block.createCuboidShape(0.0, 3.0, 3.0, 16.0, 13.0, 13.0);
    private static final VoxelShape FULL_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    public static final IntProperty DIST = IntProperty.of("distance", 0, 1);
    public static final IntProperty AGE = IntProperty.of("age", 0, 3);
    public static final EnumProperty<Direction.Axis> AXIS = Properties.HORIZONTAL_AXIS;
    public static final int MAX_AGE = 3;

    public GrapeLeavesBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0).with(DIST, 0).with(AXIS, Direction.Axis.X));
    }

    // ========= PROPERTIES & STATE =========
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE, AXIS, DIST);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        if (state.get(DIST) > 0) {
            return state.get(AGE) < MAX_AGE && world.isAir(pos.down());
        }
        return canSpread((World) world, pos, state);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState();
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return false;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock()) && !world.isClient) {
            Direction.Axis axis = state.get(AXIS);
            int dist = state.get(DIST);
            int age = state.get(AGE);

            if (age == MAX_AGE) {
                dropStack(world, pos, new ItemStack(ModItems.GRAPES));
            }

            if (dist == 0) {
                BlockPos pos1 = axis == Direction.Axis.X ? pos.west() : pos.north();
                BlockPos pos2 = axis == Direction.Axis.X ? pos.east() : pos.south();

                for (BlockPos neighbor : List.of(pos1, pos2)) {
                    BlockState neighborState = world.getBlockState(neighbor);

                    if (neighborState.isOf(this) && neighborState.get(DIST) == 1 && neighborState.get(AXIS) == axis) {
                        world.setBlockState(neighbor, ModBlocks.ROPE.getDefaultState().with(RopeBlock.AXIS, axis), Block.NOTIFY_ALL);
                    }
                }
            }

            world.setBlockState(pos, ModBlocks.ROPE.getDefaultState().with(RopeBlock.AXIS, axis), Block.NOTIFY_ALL);
        }

        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            int dist = state.get(DIST);
            Direction.Axis axis = state.get(AXIS);

            if (dist == 1) {
                BlockPos pos1 = axis == Direction.Axis.X ? pos.west() : pos.north();
                BlockPos pos2 = axis == Direction.Axis.X ? pos.east() : pos.south();

                boolean isValidPos1 = isCenterValid(world.getBlockState(pos1), axis);
                boolean isValidPos2 = isCenterValid(world.getBlockState(pos2), axis);

                if (!isValidPos1 && !isValidPos2) {
                    world.breakBlock(pos, true);
                }
            }
        }
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int dist = state.get(DIST);
        int age = state.get(AGE);

        if (dist > 0 && age < MAX_AGE && world.isAir(pos.down())) {
            world.setBlockState(pos, state.with(AGE, Math.min(age + 1, MAX_AGE)), Block.NOTIFY_ALL);
        } else if (dist < 1) {
            spread(world, pos, state);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (isBlockSupported(world, pos, state)) {
            world.breakBlock(pos, true);
            return;
        }
        if (world.getLightLevel(pos.up()) >= 9) {
            int dist = state.get(DIST);
            int age = state.get(AGE);

            if (dist > 0 && age < MAX_AGE && world.isAir(pos.down())) {
                float growthChance = getGrowthChance();

                if (random.nextInt((int)(35.0F / growthChance) + 1) == 0) {
                    world.setBlockState(pos, state.with(AGE, age + 1), Block.NOTIFY_ALL);
                }

            } else if (dist < 1 && canSpread(world, pos, state)) {
                float growthChance = getGrowthChance();

                if (random.nextInt((int)(30.0F / growthChance) + 1) == 0) {
                    spread(world, pos, state);
                }
            }
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        if (state.get(DIST) > 0) {
            return state.get(AGE) < MAX_AGE;
        } else {
            return true;
        }
    }

    // ========= INTERACTIONS =========

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.isOf(ModBlocks.GRAPE_LEAVES) && state.get(AGE) == MAX_AGE) {

            player.giveItemStack(new ItemStack(ModItems.GRAPES));

            world.setBlockState(pos, state.with(AGE, 0), Block.NOTIFY_ALL);

            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.FAIL;
    }

    // ========= FORM Y TRANSFORMATIONS =========

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(DIST) < 1) {
            return FULL_SHAPE;
        }
        return state.get(AXIS) == Direction.Axis.Z ? BRANCH_Z_SHAPE : BRANCH_X_SHAPE;
    }

    // ========= HELPER METHODS =========

    private boolean isCenterValid(BlockState state, Direction.Axis axis) {
        return state.isOf(this) && state.get(DIST) == 0 && state.get(AXIS) == axis;
    }

    private boolean isBlockSupported(World world, BlockPos pos, BlockState state) {
        if (state.get(AXIS) == Direction.Axis.X) {
            return isSideSupported(world, pos, state, Direction.WEST) || isSideSupported(world, pos, state, Direction.EAST);

        } else if (state.get(AXIS) == Direction.Axis.Z) {
            return isSideSupported(world, pos, state, Direction.NORTH) || isSideSupported(world, pos, state, Direction.SOUTH);
        }
        return true;
    }

    private boolean isSideSupported(World world, BlockPos pos, BlockState state, Direction facing) {
        BlockState testState = world.getBlockState(pos.offset(facing));

        boolean isSame = testState.isOf(this) && testState.get(AXIS) == state.get(AXIS);
        boolean isRope = testState.isOf(ModBlocks.ROPE) && testState.get(RopeBlock.AXIS) == state.get(AXIS);
        boolean isSideSolid = world.getBlockState(pos.offset(facing)).isSideSolidFullSquare(world, pos.offset(facing), facing.getOpposite());
        boolean isTiedStake = testState.isOf(ModBlocks.STAKE) && testState.getBlock() instanceof StakeBlock
                && ((StakeBlock)testState.getBlock()).hasRope(testState);

        return !isSame && !isRope && !isSideSolid && !isTiedStake;
    }

    private boolean canSpread(World world, BlockPos pos, BlockState state) {
        if (state.get(DIST) == 0) {
            switch (state.get(AXIS)) {
                case X:
                    return (world.getBlockState(pos.west()).isOf(ModBlocks.ROPE) &&
                            world.getBlockState(pos.west()).get(RopeBlock.AXIS) == state.get(AXIS)) ||
                            (world.getBlockState(pos.east()).isOf(ModBlocks.ROPE) &&
                                    world.getBlockState(pos.east()).get(RopeBlock.AXIS) == state.get(AXIS));
                case Z:
                    return (world.getBlockState(pos.north()).isOf(ModBlocks.ROPE) &&
                            world.getBlockState(pos.north()).get(RopeBlock.AXIS) == state.get(AXIS)) ||
                            (world.getBlockState(pos.south()).isOf(ModBlocks.ROPE) &&
                                    world.getBlockState(pos.south()).get(RopeBlock.AXIS) == state.get(AXIS));
            }
        }
        return false;
    }

    private void spread(World world, BlockPos pos, BlockState state) {
        if (state.get(DIST) < 1) {
            switch (state.get(AXIS)) {
                case X:
                    boolean westRope = world.getBlockState(pos.west()).isOf(ModBlocks.ROPE) &&
                            world.getBlockState(pos.west()).get(RopeBlock.AXIS) == state.get(AXIS);
                    boolean eastRope = world.getBlockState(pos.east()).isOf(ModBlocks.ROPE) &&
                            world.getBlockState(pos.east()).get(RopeBlock.AXIS) == state.get(AXIS);
                    if (westRope && eastRope) {
                        spreadToValidRope(world, world.random.nextBoolean() ? pos.west() : pos.east());
                    } else if (westRope) {
                        spreadToValidRope(world, pos.west());
                    } else if (eastRope) {
                        spreadToValidRope(world, pos.east());
                    }
                    break;
                case Z:
                    boolean northRope = world.getBlockState(pos.north()).isOf(ModBlocks.ROPE) &&
                            world.getBlockState(pos.north()).get(RopeBlock.AXIS) == state.get(AXIS);
                    boolean southRope = world.getBlockState(pos.south()).isOf(ModBlocks.ROPE) &&
                            world.getBlockState(pos.south()).get(RopeBlock.AXIS) == state.get(AXIS);
                    if (northRope && southRope) {
                        spreadToValidRope(world, world.random.nextBoolean() ? pos.north() : pos.south());
                    } else if (northRope) {
                        spreadToValidRope(world, pos.north());
                    } else if (southRope) {
                        spreadToValidRope(world, pos.south());
                    }
                    break;
            }
        }
    }

    private void spreadToValidRope(World world, BlockPos newPos) {
        Direction.Axis axis = world.getBlockState(newPos).get(RopeBlock.AXIS);
        world.setBlockState(newPos,
                getDefaultState().with(AXIS, axis).with(DIST, 1),
                Block.NOTIFY_ALL);
    }

    public static int getMaxAge(){
        return MAX_AGE;
    }

    protected float getGrowthChance() {
        return 7.0F;
    }

}

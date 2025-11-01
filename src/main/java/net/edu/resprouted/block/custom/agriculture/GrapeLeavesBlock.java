package net.edu.resprouted.block.custom.agriculture;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
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
        super.onStateReplaced(state, world, pos, newState, moved);

        if (!state.isOf(newState.getBlock()) && !world.isClient) {
            world.setBlockState(pos, ModBlocks.ROPE.getDefaultState().with(RopeBlock.AXIS, state.get(AXIS)), Block.NOTIFY_ALL);
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
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            Direction i = getDirectionFromNeighbor(pos, fromPos);
            if (i != null) {
                if ((state.get(AXIS) == i.getAxis()) && world.isAir(fromPos)) {
                    if (i == Direction.DOWN) {
                        world.breakBlock(pos, true);
                    } else if (!this.isBlockSupported(world, pos, state)) {
                        world.breakBlock(pos, true);
                    }
                }
            }
        }
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int j = state.get(DIST);
        int k = state.get(AGE);

        if (j > 0 && k < MAX_AGE && world.isAir(pos.down())) {
            world.setBlockState(pos, state.with(AGE, Math.min(k + 1, MAX_AGE)), Block.NOTIFY_ALL);

        } else if (j < 1) {
            spread(world, pos, state);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!this.isBlockSupported(world, pos, state)) {
            world.breakBlock(pos, true);
            return;
        }

        if (world.getLightLevel(pos.up()) >= 9) {
            int l = state.get(DIST);
            int m = state.get(AGE);

            if (l > 0 && m < MAX_AGE && world.isAir(pos.down())) {
                if (random.nextInt((int)(35.0F / getGrowthChance()) + 1) == 0) {
                    world.setBlockState(pos, state.with(AGE, m + 1), Block.NOTIFY_ALL);
                }
            } else if (l == 0 && canSpread(world, pos, state)) {
                if (random.nextInt((int)(30.0F / getGrowthChance()) + 1) == 0) {
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

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.isOf(ModBlocks.GRAPE_LEAVES) && state.get(AGE) == MAX_AGE) {

            player.giveItemStack(new ItemStack(getHarvest()));

            world.setBlockState(pos, state.with(AGE, 0), Block.NOTIFY_ALL);

            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.FAIL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(DIST) < 1) {
            return FULL_SHAPE;
        }
        return state.get(AXIS) == Direction.Axis.Z ? BRANCH_Z_SHAPE : BRANCH_X_SHAPE;
    }

    private boolean isBlockSupported(World world, BlockPos pos, BlockState state) {
        if (state.get(AXIS) == Direction.Axis.X) {
            return this.isSideSupported(world, pos, state, Direction.WEST) && this.isSideSupported(world, pos, state, Direction.EAST);

        } else if (state.get(AXIS) == Direction.Axis.Z) {
            return this.isSideSupported(world, pos, state, Direction.NORTH) && this.isSideSupported(world, pos, state, Direction.SOUTH);
        }
        return true;
    }

    private boolean isSideSupported(World world, BlockPos pos, BlockState state, Direction facing) {
        if (facing == Direction.DOWN) {
            return false;
        }
        BlockState n = world.getBlockState(pos.offset(facing));

        boolean isSame = n.getBlock() instanceof GrapeLeavesBlock && n.get(AXIS) == state.get(AXIS);
        boolean isRope = n.isOf(ModBlocks.ROPE) && n.get(RopeBlock.AXIS) == state.get(AXIS);
        boolean isSideSolid = n.isSideSolidFullSquare(world, pos.offset(facing), facing.getOpposite());
        boolean isTiedStake = n.getBlock() instanceof StakeBlock && ((StakeBlock)n.getBlock()).hasRope(n);

        return isSame || isRope || isSideSolid || isTiedStake;
    }

    private Direction getDirectionFromNeighbor(BlockPos pos, BlockPos fromPos) {
        if (fromPos.getX() != pos.getX()) {
            return (fromPos.getX() - pos.getX()) < 0 ? Direction.WEST : Direction.EAST;
        } else if (fromPos.getY() != pos.getY()) {
            return (fromPos.getY() - pos.getY()) < 0 ? Direction.DOWN : Direction.UP;
        } else if (fromPos.getZ() != pos.getZ()) {
            return (fromPos.getZ() - pos.getZ()) < 0 ? Direction.NORTH : Direction.SOUTH;
        }
        return null;
    }

    private boolean canSpread(World world, BlockPos pos, BlockState state) {
        if (state.get(DIST) == 0) {
            switch (state.get(AXIS)) {
                case X:
                    return (world.getBlockState(pos.west()).isOf(ModBlocks.ROPE)
                            && world.getBlockState(pos.west()).get(RopeBlock.AXIS) == state.get(AXIS)) || (world.getBlockState(pos.east()).isOf(ModBlocks.ROPE)
                            && world.getBlockState(pos.east()).get(RopeBlock.AXIS) == state.get(AXIS));
                case Z:
                    return (world.getBlockState(pos.north()).isOf(ModBlocks.ROPE)
                            && world.getBlockState(pos.north()).get(RopeBlock.AXIS) == state.get(AXIS)) || (world.getBlockState(pos.south()).isOf(ModBlocks.ROPE)
                            && world.getBlockState(pos.south()).get(RopeBlock.AXIS) == state.get(AXIS));
            }
        }

        return false;
    }

    private void spread(World world, BlockPos pos, BlockState state) {
        if (state.get(DIST) < 1) {
            switch (state.get(AXIS)) {
                case X:
                    boolean westRope = world.getBlockState(pos.west()).isOf(ModBlocks.ROPE)
                            && world.getBlockState(pos.west()).get(RopeBlock.AXIS) == state.get(AXIS);

                    boolean eastRope = world.getBlockState(pos.east()).isOf(ModBlocks.ROPE)
                            && world.getBlockState(pos.east()).get(RopeBlock.AXIS) == state.get(AXIS);

                    if (westRope && eastRope) {
                        spreadToValidRope(world, world.random.nextBoolean() ? pos.west() : pos.east());

                    } else if (westRope) {
                        spreadToValidRope(world, pos.west());

                    } else if (eastRope) {
                        spreadToValidRope(world, pos.east());
                    }
                    break;

                case Z:
                    boolean north = world.getBlockState(pos.north()).isOf(ModBlocks.ROPE)
                            && world.getBlockState(pos.north()).get(RopeBlock.AXIS) == state.get(AXIS);

                    boolean south = world.getBlockState(pos.south()).isOf(ModBlocks.ROPE)
                            && world.getBlockState(pos.south()).get(RopeBlock.AXIS) == state.get(AXIS);

                    if (north && south) {
                        spreadToValidRope(world, world.random.nextBoolean() ? pos.north() : pos.south());

                    } else if (north) {
                        spreadToValidRope(world, pos.north());

                    } else if (south) {
                        spreadToValidRope(world, pos.south());
                    }
                    break;
            }
        }
    }

    private void spreadToValidRope(World world, BlockPos pos) {
        BlockState o = world.getBlockState(pos);
        if (o.isOf(ModBlocks.ROPE)) {
            Direction.Axis axis = o.get(RopeBlock.AXIS);
            world.setBlockState(pos, getDefaultState().with(AXIS, axis).with(DIST, 1), Block.NOTIFY_ALL);
        }
    }

    public static int getMaxAge(){
        return MAX_AGE;
    }

    protected float getGrowthChance() {
        return 7.0F;
    }

    public Item getHarvest() {
        return ModItems.GRAPES;
    }
}

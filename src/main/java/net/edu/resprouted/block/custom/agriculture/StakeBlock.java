package net.edu.resprouted.block.custom.agriculture;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.util.ModTags;
import net.edu.resprouted.registry.ModStakeCropRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import java.util.Optional;

public class StakeBlock extends Block {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(6.0F, 0.0F, 6.0F, 10.0F, 16.0F, 10.0F);
    protected static final VoxelShape SHAPE_WITH_ROPE = VoxelShapes.union(VoxelShapes.union(Block.createCuboidShape(5.0F, 4.0F, 5.0F, 11.0F, 12.0F, 11.0F), Block.createCuboidShape(6.0F, 0.0F, 6.0F, 10.0F, 16.0F, 10.0F)));
    public static final BooleanProperty CONNECT_NORTH = BooleanProperty.of("connect_north");
    public static final BooleanProperty CONNECT_SOUTH = BooleanProperty.of("connect_south");
    public static final BooleanProperty CONNECT_EAST = BooleanProperty.of("connect_east");
    public static final BooleanProperty CONNECT_WEST = BooleanProperty.of("connect_west");
    public static final BooleanProperty HAS_ROPE = BooleanProperty.of("has_rope");

    public StakeBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState()
                .with(HAS_ROPE, false)
                .with(CONNECT_NORTH, false)
                .with(CONNECT_SOUTH, false)
                .with(CONNECT_EAST, false)
                .with(CONNECT_WEST, false));
    }

    // ========= PROPIEDADES Y ESTADO ========
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HAS_ROPE, CONNECT_NORTH, CONNECT_SOUTH, CONNECT_EAST, CONNECT_WEST);
    }
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!world.isClient && state.get(HAS_ROPE) && !state.isOf(newState.getBlock())) {
            world.setBlockState(pos, state.with(HAS_ROPE, false), Block.NOTIFY_ALL);
            ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModBlocks.ROPE));
            world.playSound(null, pos, SoundEvents.BLOCK_WOOL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return;
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }
    @Override
    public BlockSoundGroup getSoundGroup(BlockState state) {
        if (state.get(HAS_ROPE)) {
            return BlockSoundGroup.WOOL;
        }
        return super.getSoundGroup(state);
    }
    @Override
    public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
        if (state.get(HAS_ROPE)) {
            return super.calcBlockBreakingDelta(state, player, world, pos) * 2.0f;
        }
        return super.calcBlockBreakingDelta(state, player, world, pos);
    }
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction.getAxis().isHorizontal()) {
            //Conexión con cuerda si coincide el eje
            boolean connected = neighborState.getBlock() instanceof RopeBlock
                    && neighborState.contains(RopeBlock.AXIS)
                    && neighborState.get(RopeBlock.AXIS) == direction.getAxis();
            //Conexión con GrapeLeavesBlock
            if (neighborState.getBlock() instanceof GrapeLeavesBlock) {
                connected = true;
            }
            return state.with(getPropertyForDirection(direction), connected);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
    private BooleanProperty getPropertyForDirection(Direction dir) {
        return switch (dir) {
            case NORTH -> CONNECT_NORTH;
            case SOUTH -> CONNECT_SOUTH;
            case EAST -> CONNECT_EAST;
            case WEST -> CONNECT_WEST;
            default -> throw new IllegalArgumentException("Invalid direction: " + dir);
        };
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        WorldAccess world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();

        return this.getDefaultState()
                .with(CONNECT_NORTH, world.getBlockState(pos.north()).getBlock() instanceof RopeBlock)
                .with(CONNECT_SOUTH, world.getBlockState(pos.south()).getBlock() instanceof RopeBlock)
                .with(CONNECT_EAST, world.getBlockState(pos.east()).getBlock() instanceof RopeBlock)
                .with(CONNECT_WEST, world.getBlockState(pos.west()).getBlock() instanceof RopeBlock);
    }
    public boolean hasRope(BlockState state) {
        return state.contains(HAS_ROPE) && state.get(HAS_ROPE);
    }

    // ========= INTERACCIÓN =========
    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!state.isOf(ModBlocks.STAKE)) {
            return ItemActionResult.FAIL;
        }
        //Caso 1: Plantar un cultivo
        if (!state.get(StakeBlock.HAS_ROPE) && world.getBlockState(pos.down()).isIn(ModTags.Blocks.FERTILE_SOILS)) {
            Optional<BlockState> crop = ModStakeCropRegistry.getCropForSeed(stack.getItem());
            if (crop.isPresent()) {
                world.setBlockState(pos, crop.get(), Block.NOTIFY_ALL);
                world.playSound(null, pos, SoundEvents.ITEM_CROP_PLANT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!player.isCreative()) stack.decrement(1);
                return ItemActionResult.SUCCESS;
            }
        }
        //Caso 2: Colocar cuerda
        if (stack.isOf(ModBlocks.ROPE.asItem()) && !state.get(StakeBlock.HAS_ROPE)) {
            world.setBlockState(pos, state.with(StakeBlock.HAS_ROPE, true), Block.NOTIFY_ALL);
            world.playSound(null, pos, SoundEvents.BLOCK_WOOL_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!player.isCreative()) stack.decrement(1);
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.FAIL;
    }

    // ========= FORMA Y TRANSFORMACIONES =========
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(HAS_ROPE)) {
            return SHAPE_WITH_ROPE;
        }
        return SHAPE;
    }
}

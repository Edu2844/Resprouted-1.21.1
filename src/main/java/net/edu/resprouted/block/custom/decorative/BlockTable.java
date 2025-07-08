package net.edu.resprouted.block.custom.decorative;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class BlockTable extends Block {
    public static final BooleanProperty NW = BooleanProperty.of("nw");
    public static final BooleanProperty NE = BooleanProperty.of("ne");
    public static final BooleanProperty SE = BooleanProperty.of("se");
    public static final BooleanProperty SW = BooleanProperty.of("sw");
    private static final VoxelShape SHAPE = Block.createCuboidShape(0.0F, 14.0F, 0.0F, 16.0F, 16.0F, 16.0F);

    public BlockTable(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(NW, true)
                .with(NE, true)
                .with(SE, true)
                .with(SW, true));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NW, NE, SE, SW);
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getConnections(ctx.getWorld(), ctx.getBlockPos());
    }
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
                                                WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return getConnections(world, pos);
    }

    private BlockState getConnections(BlockView world, BlockPos pos) {
        boolean blockNorth = world.getBlockState(pos.north()).getBlock() instanceof BlockTable;
        boolean blockSouth = world.getBlockState(pos.south()).getBlock() instanceof BlockTable;
        boolean blockEast = world.getBlockState(pos.east()).getBlock() instanceof BlockTable;
        boolean blockWest = world.getBlockState(pos.west()).getBlock() instanceof BlockTable;

        return this.getDefaultState()
                .with(NW, !blockNorth && !blockWest)
                .with(NE, !blockNorth && !blockEast)
                .with(SE, !blockSouth && !blockEast)
                .with(SW, !blockSouth && !blockWest);
    }
}

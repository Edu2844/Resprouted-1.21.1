package net.edu.resprouted.block.custom.alchemy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class RetortBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    private static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(7.0F, 10.0F, 12.0F, 9.0F, 12.0F, 16.0F),
            Block.createCuboidShape(4.0F, 0.0F, 4.0F, 12.0F, 16.0F, 12.0F)
    );
    private static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(7.0F, 10.0F, 0.0F, 9.0F, 12.0F, 4.0F),
            Block.createCuboidShape(4.0F, 0.0F, 4.0F, 12.0F, 16.0F, 12.0F)
    );
    private static final VoxelShape EAST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0.0F, 10.0F, 7.0F, 4.0F, 12.0F, 9.0F),
            Block.createCuboidShape(4.0F, 0.0F, 4.0F, 12.0F, 16.0F, 12.0F)
    );
    private static final VoxelShape WEST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(12.0F, 10.0F, 7.0F, 16.0F, 12.0F, 9.0F),
            Block.createCuboidShape(4.0F, 0.0F, 4.0F, 12.0F, 16.0F, 12.0F)
    );

    public RetortBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    // ========= PROPIEDADES Y ESTADO =========
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    // ========= INTERACCIÓN =========
    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockPos condenserPos = pos.offset(state.get(FACING).getOpposite());
        BlockState condenserState = world.getBlockState(condenserPos);

        if (!(condenserState.getBlock() instanceof CondenserBlock condenserBlock)) {
            return ItemActionResult.FAIL;
        }
        return condenserBlock.onUseWithItem(stack, condenserState, world, condenserPos, player, hand, hit);
    }

    // ========= FORMA Y TRANSFORMACIONES =========
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        return switch (direction) {
            case EAST -> EAST_SHAPE;
            case WEST -> WEST_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            default -> NORTH_SHAPE;
        };
    }
}

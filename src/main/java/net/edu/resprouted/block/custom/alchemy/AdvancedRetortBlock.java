package net.edu.resprouted.block.custom.alchemy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class AdvancedRetortBlock extends RetortBlock{
    private static final VoxelShape NORTH_SHAPE = VoxelShapes.combineAndSimplify(
            Block.createCuboidShape(7.0, 5.0, 12.0, 9.0, 7.0, 16.0),
            Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 16.0, 12.0),
            BooleanBiFunction.OR
    );
    private static final VoxelShape SOUTH_SHAPE = VoxelShapes.combineAndSimplify(
            Block.createCuboidShape(7.0, 5.0, 0.0, 9.0, 7.0, 4.0),
            Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 16.0, 12.0),
            BooleanBiFunction.OR
    );
    private static final VoxelShape EAST_SHAPE = VoxelShapes.combineAndSimplify(
            Block.createCuboidShape(0.0, 5.0, 7.0, 4.0, 7.0, 9.0),
            Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 16.0, 12.0),
            BooleanBiFunction.OR
    );
    private static final VoxelShape WEST_SHAPE = VoxelShapes.combineAndSimplify(
            Block.createCuboidShape(12.0, 5.0, 7.0, 16.0, 7.0, 9.0),
            Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 16.0, 12.0),
            BooleanBiFunction.OR
    );
    public AdvancedRetortBlock(Settings settings) {
        super(settings);
    }

    // ========= INTERACCIÓN =========
    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockPos condenserPos = pos.offset(state.get(FACING).getOpposite());
        BlockState condenserState = world.getBlockState(condenserPos);
        if (!(condenserState.getBlock() instanceof AdvancedCondenserBlock condenserBlock)) {
            return ItemActionResult.FAIL;
        }
        return condenserBlock.onUseWithItem(stack, condenserState, world, condenserPos, player, hand, hit);
    }

    // ========= FORMA Y TRANSFORMACIONES =========
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

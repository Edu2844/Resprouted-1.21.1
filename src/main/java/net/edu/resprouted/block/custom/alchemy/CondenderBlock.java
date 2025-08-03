package net.edu.resprouted.block.custom.alchemy;

import com.mojang.serialization.MapCodec;
import net.edu.resprouted.block.entity.custom.CondenserBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class CondenderBlock extends BlockWithEntity {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty BOTTOM = BooleanProperty.of("bottom");
    private static final VoxelShape TOP_SHAPE = VoxelShapes.combineAndSimplify(
            Block.createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F, 4.0F, 16.0F),
            Block.createCuboidShape(4.0F, 4.0F, 4.0F, 12.0F, 8.0F, 12.0F),
            BooleanBiFunction.OR
    );
    public static final MapCodec<CondenderBlock> CODEC = CondenderBlock.createCodec(CondenderBlock::new);

    public CondenderBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(BOTTOM, true)
        );
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, BOTTOM);
    }
    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }
    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return state.get(BOTTOM) ? new CondenserBlockEntity(pos, state) : null;
    }
    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        World world = ctx.getWorld();

        if (!world.getBlockState(pos.up()).isReplaceable()) {
            return null;
        }
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(BOTTOM, true);
    }
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (state.get(BOTTOM)) {
            BlockPos topPos = pos.up();
            world.setBlockState(topPos, state.with(BOTTOM, false), Block.NOTIFY_ALL);
        }
    }
    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockPos otherPos = state.get(BOTTOM) ? pos.up() : pos.down();
        if (world.getBlockState(otherPos).isOf(this)) {
            world.setBlockState(otherPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
        }
        return super.onBreak(world, pos, state, player);
    }
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (state.get(BOTTOM)) {
            return world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP) && world.getBlockState(pos.up()).isReplaceable();
        }
        return world.getBlockState(pos.down()).isOf(this);
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(BOTTOM) ? VoxelShapes.fullCube() : TOP_SHAPE;
    }
}

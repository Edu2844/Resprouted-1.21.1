package net.edu.resprouted.block.custom.agriculture;

import com.mojang.serialization.MapCodec;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.entity.custom.BrewingBarrelBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BrewingBarrelBlock extends BlockWithEntity {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    private static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(1.0, 2.0, 1.0, 15.0, 16.0, 15.0),
            Block.createCuboidShape(1.0, 0.0, 3.0, 15.0, 2.0, 13.0)
    );
    private static final VoxelShape EAST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(1.0, 2.0, 1.0, 15.0, 16.0, 15.0),
            Block.createCuboidShape(3.0, 0.0, 1.0, 13.0, 2.0, 15.0)
    );
    public static final MapCodec<BrewingBarrelBlock> CODEC = BrewingBarrelBlock.createCodec(BrewingBarrelBlock::new);

    public BrewingBarrelBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity be = world.getBlockEntity(pos);

        if (!(be instanceof BrewingBarrelBlockEntity bb))
            return ItemActionResult.FAIL;

        if (!world.isClient ) {
            player.openHandledScreen(bb);
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BrewingBarrelBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case EAST, WEST -> EAST_SHAPE;
            default -> NORTH_SHAPE;
        };
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.BREWING_BARREL_BE,
                (world1, pos, state1, blockEntity) -> BrewingBarrelBlockEntity.tick(world1, blockEntity));
    }
}

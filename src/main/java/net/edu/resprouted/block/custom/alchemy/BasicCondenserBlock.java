package net.edu.resprouted.block.custom.alchemy;

import com.mojang.serialization.MapCodec;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.abstracts.AbstractCondenserBlock;
import net.edu.resprouted.block.entity.custom.BasicCondenserBE;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

public class BasicCondenserBlock extends AbstractCondenserBlock {
    private static final VoxelShape TOP_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F, 4.0F, 16.0F),
            Block.createCuboidShape(4.0F, 4.0F, 4.0F, 12.0F, 8.0F, 12.0F)
    );
    public static final MapCodec<BasicCondenserBlock> CODEC = BasicCondenserBlock.createCodec(BasicCondenserBlock::new);

    public BasicCondenserBlock(Settings settings) {
        super(settings, TOP_SHAPE, CODEC);
    }

    @Override
    protected BlockEntity createSpecificBlockEntity(BlockPos pos, BlockState state) {
        return new BasicCondenserBE(pos, state);
    }

    @Override
    public boolean hasRequiredRetorts(World world, BlockPos pos, BlockState state) {
        if (state.getBlock() != this) {
            return false;
        }
        Direction facing = state.get(FACING);
        BlockPos east = pos.east();
        BlockPos west = pos.west();
        BlockPos north = pos.north();
        BlockPos south = pos.south();

        switch (facing) {
            case NORTH:
            case SOUTH:
                if (!world.getBlockState(east).isOf(ModBlocks.RETORT)) return false;
                if (!world.getBlockState(west).isOf(ModBlocks.RETORT)) return false;
                if (world.getBlockState(east).get(RetortBlock.FACING) != Direction.EAST) return false;
                if (world.getBlockState(west).get(RetortBlock.FACING) != Direction.WEST) return false;
                break;
            case WEST:
            case EAST:
                if (!world.getBlockState(north).isOf(ModBlocks.RETORT)) return false;
                if (!world.getBlockState(south).isOf(ModBlocks.RETORT)) return false;
                if (world.getBlockState(north).get(RetortBlock.FACING) != Direction.NORTH) return false;
                if (world.getBlockState(south).get(RetortBlock.FACING) != Direction.SOUTH) return false;
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    protected BlockEntityType<?> getBlockEntityType() {
        return ModBlockEntities.CONDENSER_BE;
    }
}

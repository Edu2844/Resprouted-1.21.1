package net.edu.resprouted.block.custom.alchemy;

import com.mojang.serialization.MapCodec;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.entity.custom.AdvancedCondenserBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

public class AdvancedCondenserBlock extends AbstractCondenserBlock {
    private static final VoxelShape TOP_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F, 12.0F, 16.0F),
            Block.createCuboidShape(4.0F, 12.0F, 4.0F, 12.0F, 16.0F, 12.0F)
    );
    public static final MapCodec<AdvancedCondenserBlock> CODEC = AdvancedCondenserBlock.createCodec(AdvancedCondenserBlock::new);

    public AdvancedCondenserBlock(Settings settings) {
        super(settings, TOP_SHAPE, CODEC);
    }
    @Override
    protected BlockEntity createSpecificBlockEntity(BlockPos pos, BlockState state) {
        return new AdvancedCondenserBlockEntity(pos, state);
    }
    @Override
    public boolean hasRequiredRetorts(World world, BlockPos pos, BlockState state) {
        if (state.getBlock() != this) {
            return false;
        }
        Direction facing = state.get(FACING);
        BlockPos eastPos = pos.east();
        BlockPos westPos = pos.west();
        BlockPos northPos = pos.north();
        BlockPos southPos = pos.south();

        switch (facing) {
            case NORTH:
                if (!world.getBlockState(eastPos).isOf(ModBlocks.ADVANCED_RETORT)) return false;
                if (!world.getBlockState(westPos).isOf(ModBlocks.ADVANCED_RETORT)) return false;
                if (!world.getBlockState(southPos).isOf(ModBlocks.ADVANCED_RETORT)) return false;
                if (world.getBlockState(eastPos).get(RetortBlock.FACING) != Direction.EAST) return false;
                if (world.getBlockState(westPos).get(RetortBlock.FACING) != Direction.WEST) return false;
                if (world.getBlockState(southPos).get(RetortBlock.FACING) != Direction.SOUTH) return false;
                break;
            case SOUTH:
                if (!world.getBlockState(eastPos).isOf(ModBlocks.ADVANCED_RETORT)) return false;
                if (!world.getBlockState(westPos).isOf(ModBlocks.ADVANCED_RETORT)) return false;
                if (!world.getBlockState(northPos).isOf(ModBlocks.ADVANCED_RETORT)) return false;
                if (world.getBlockState(eastPos).get(RetortBlock.FACING) != Direction.EAST) return false;
                if (world.getBlockState(westPos).get(RetortBlock.FACING) != Direction.WEST) return false;
                if (world.getBlockState(northPos).get(RetortBlock.FACING) != Direction.NORTH) return false;
                break;
            case WEST:
                if (!world.getBlockState(northPos).isOf(ModBlocks.ADVANCED_RETORT)) return false;
                if (!world.getBlockState(southPos).isOf(ModBlocks.ADVANCED_RETORT)) return false;
                if (!world.getBlockState(eastPos).isOf(ModBlocks.ADVANCED_RETORT)) return false;
                if (world.getBlockState(northPos).get(RetortBlock.FACING) != Direction.NORTH) return false;
                if (world.getBlockState(southPos).get(RetortBlock.FACING) != Direction.SOUTH) return false;
                if (world.getBlockState(eastPos).get(RetortBlock.FACING) != Direction.EAST) return false;
                break;
            case EAST:
                if (!world.getBlockState(northPos).isOf(ModBlocks.ADVANCED_RETORT)) return false;
                if (!world.getBlockState(southPos).isOf(ModBlocks.ADVANCED_RETORT)) return false;
                if (!world.getBlockState(westPos).isOf(ModBlocks.ADVANCED_RETORT)) return false;
                if (world.getBlockState(northPos).get(RetortBlock.FACING) != Direction.NORTH) return false;
                if (world.getBlockState(southPos).get(RetortBlock.FACING) != Direction.SOUTH) return false;
                if (world.getBlockState(westPos).get(RetortBlock.FACING) != Direction.WEST) return false;
                break;
            default:
                return false;
        }
        return true;
    }
    @Override
    protected BlockEntityType<?> getBlockEntityType() {
        return ModBlockEntities.ADVANCED_CONDENSER_BE;
    }
}
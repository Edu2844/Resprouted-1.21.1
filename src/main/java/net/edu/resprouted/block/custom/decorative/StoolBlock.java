package net.edu.resprouted.block.custom.decorative;

import com.mojang.serialization.MapCodec;
import net.edu.resprouted.entity.ModEntities;
import net.edu.resprouted.entity.custom.StoolEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

public class StoolBlock extends ChairBlock {
    public static final MapCodec<StoolBlock> CODEC = createCodec(StoolBlock::new);
    private static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2.0F, 7.0F, 2.0F, 14.0F, 9.0F, 14.0F),
            Block.createCuboidShape(11.0F, 0.0F, 11.0F, 13.0F, 8.0F, 13.0F),
            Block.createCuboidShape(11.0F, 0.0F, 3.0F, 13, 8.0F, 5.0F),
            Block.createCuboidShape(3.0F, 0.0F, 3.0F, 5.0F, 8.0F, 5.0F),
            Block.createCuboidShape(3.0F, 0.0F, 11.0F, 5.0F, 8.0F, 13.0F),
            Block.createCuboidShape(4.0F, 5.0F, 4.0F, 12.0F, 7.0F, 12.0F)

    );

    public StoolBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(!world.isClient()) {
            Entity entity;
            List<StoolEntity> entities = world.getEntitiesByType(ModEntities.STOOL, new Box(pos), stool -> true);
            if(entities.isEmpty()) {
                entity = ModEntities.STOOL.spawn((ServerWorld) world, pos, SpawnReason.TRIGGERED);
            } else {
                entity = entities.getFirst();
            }
            player.startRiding(entity);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }
}

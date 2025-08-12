package net.edu.resprouted.block.custom.agriculture;

import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.util.ModTags;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import java.util.function.Supplier;

public class HerbBlock extends CropBlock {
    private final Supplier<ItemConvertible> seedsSupplier;
    public static final int MAX_AGE = 6;
    public static final IntProperty AGE = IntProperty.of("age", 0, 6);
    private static final VoxelShape[] SHAPE_TO_AGE = new VoxelShape[]{
            Block.createCuboidShape(6.0F, 0.0F, 6.0F, 10.0F, 5.0F, 10.0F),
            Block.createCuboidShape(5.0F, 0.0F, 5.0F, 11.0F, 6.0F, 11.0F),
            Block.createCuboidShape(4.0F, 0.0F, 4.0F, 12.0F, 8.0F, 12.0F),
            Block.createCuboidShape(3.0F, 0.0F, 3.0F, 13.0F, 11.0F, 13.0F),
            Block.createCuboidShape(3.0F, 0.0F, 3.0F, 13.0F, 12.0F, 13.0F),
            Block.createCuboidShape(3.0F, 0.0F, 3.0F, 13.0F, 13.0F, 13.0F),
            Block.createCuboidShape(3.0F, 0.0F, 3.0F, 13.0F, 14.0F, 13.0F),
    };
    public HerbBlock(Settings settings, Supplier<ItemConvertible> seedsSupplier) {
        super(settings);
        this.seedsSupplier = seedsSupplier;
    }

    // ========= PROPIEDADES Y ESTADO =========
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return  seedsSupplier.get();
    }
    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }
    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        if(this == ModBlocks.ALOE_VERA_BLOCK) {
            return floor.isIn(ModTags.Blocks.FERTILE_SOILS)||floor.isIn(ModTags.Blocks.ALLOWS_GROWTH) ||floor.isOf(Blocks.SAND);
        }
        return floor.isIn(ModTags.Blocks.FERTILE_SOILS)||floor.isIn(ModTags.Blocks.ALLOWS_GROWTH);
    }
    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    // ========= INTERACCIÓN =========
    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if(this == ModBlocks.WIND_THISTLE_BLOCK) {
            if (entity instanceof LivingEntity && entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE) {
                entity.slowMovement(state, new Vec3d(0.8F, 0.75F, 0.8F));
                if (!world.isClient && state.get(AGE) > 0 && (entity.lastRenderX != entity.getX() || entity.lastRenderZ != entity.getZ())) {
                    double d = Math.abs(entity.getX() - entity.lastRenderX);
                    double e = Math.abs(entity.getZ() - entity.lastRenderZ);
                    if (d >= (double) 0.003F || e >= (double) 0.003F) {
                        entity.damage(world.getDamageSources().sweetBerryBush(), 1.0F);
                    }
                }
            }
        }
    }

    // ========= FORMA Y TRANSFORMACIONES =========
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE_TO_AGE[this.getAge(state)];
    }
}

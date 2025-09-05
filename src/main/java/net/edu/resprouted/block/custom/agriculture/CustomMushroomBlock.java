package net.edu.resprouted.block.custom.agriculture;

import net.edu.resprouted.util.ModTags;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class CustomMushroomBlock extends CropBlock {
    private final Supplier<ItemConvertible> mushroomSupplier;
    private final Predicate<BlockState> growthCondition;
    public static final int MAX_AGE = 3;
    public static final IntProperty AGE = IntProperty.of("age", 0, 3);
    private static final VoxelShape[] SHAPE_TO_AGE = new VoxelShape[]{
            Block.createCuboidShape(5.0F, 0.0F, 5.0F, 11.0F, 5.0F, 11.0F),
            Block.createCuboidShape(4.0F, 0.0F, 4.0F, 12.0F, 8.0F, 12.0F),
            Block.createCuboidShape(3.0F, 0.0F, 3.0F, 13.0F, 9.0F, 13.0F),
            Block.createCuboidShape(2.0F, 0.0F, 2.0F, 14.0F, 12.0F, 14.0F),
    };

    public CustomMushroomBlock(Settings settings, Supplier<ItemConvertible> mushroomSupplier, Predicate<BlockState> growthCondition) {
        super(settings);
        this.mushroomSupplier = mushroomSupplier;
        this.growthCondition = growthCondition;
    }
    public CustomMushroomBlock(Settings settings, Supplier<ItemConvertible> mushroomSupplier) {
        this(settings, mushroomSupplier,
                floor ->
                        floor.isOf(Blocks.FARMLAND) ||
                        floor.isIn(ModTags.Blocks.FERTILE_SOILS) ||
                        floor.isOf(Blocks.PODZOL) ||
                        floor.isOf(Blocks.STONE) ||
                        floor.isOf(Blocks.GRASS_BLOCK));
    }
    public static CustomMushroomBlock NetherMushroom(Settings settings, Supplier<ItemConvertible> mushroomSupplier) {
        return new CustomMushroomBlock(settings, mushroomSupplier,
                floor -> floor.isOf(Blocks.NETHERRACK));
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return  mushroomSupplier.get();
    }
    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return growthCondition.test(floor);
    }
    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }
    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE_TO_AGE[this.getAge(state)];
    }
}

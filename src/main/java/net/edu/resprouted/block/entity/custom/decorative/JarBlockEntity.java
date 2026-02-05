package net.edu.resprouted.block.entity.custom.decorative;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.abstracts.entity.AbstractAnimatedSingleFluidStorageBlockEntity;
import net.edu.resprouted.block.custom.decorative.JarBlock;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.block.BlockState;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class JarBlockEntity extends AbstractAnimatedSingleFluidStorageBlockEntity {
    private final DyeColor cachedColor;

    public JarBlockEntity(@Nullable DyeColor color, BlockPos pos, BlockState state) {
        super(ModBlockEntities.JAR_BE, pos, state, FluidConstants.BUCKET * 4);
        this.cachedColor = color;
    }

    public JarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.JAR_BE, pos, state, FluidConstants.BUCKET * 4);
        this.cachedColor = JarBlock.getColor(state.getBlock());
    }

    @SuppressWarnings("unused")
    public static void tick(World world, BlockPos pos, BlockState state, JarBlockEntity entity) {
        entity.tickFluidAnimation();
    }

    @Nullable
    public DyeColor getColor() {
        return this.cachedColor;
    }
}

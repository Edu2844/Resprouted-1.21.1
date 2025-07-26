package net.edu.resprouted.block.custom.decorative;

import net.edu.resprouted.block.entity.custom.CabinetBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.DoubleBlockProperties;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Supplier;

public abstract class AbstractCabinetBlock <E extends BlockEntity> extends BlockWithEntity {
    protected final Supplier<BlockEntityType<? extends E>> entityTypeRetriever;

    protected AbstractCabinetBlock(Settings settings, Supplier<BlockEntityType<? extends E>> supplier) {
        super(settings);
        this.entityTypeRetriever = supplier;
    }
    public abstract DoubleBlockProperties.PropertySource<? extends CabinetBlockEntity> getBlockEntitySource(BlockState state, World world, BlockPos pos, boolean ignoreBlocked);
}

package net.edu.resprouted.block.custom.decorative;

import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;

import java.util.function.Supplier;

public abstract class AbstractCabinetBlock <E extends BlockEntity> extends BlockWithEntity {
    protected final Supplier<BlockEntityType<? extends E>> entityTypeRetriever;

    protected AbstractCabinetBlock(Settings settings, Supplier<BlockEntityType<? extends E>> supplier) {
        super(settings);
        this.entityTypeRetriever = supplier;
    }
}

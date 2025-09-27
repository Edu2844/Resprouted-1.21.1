package net.edu.resprouted.mixin.helper;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(BlockEntityType.class)

public interface BlockEntityTypeAccessor {
    @Accessor("blocks")
    Set<Block> getBlocks();

    @Accessor("blocks")
    @Mutable
    @Final
    void setBlocks(Set<Block> blocks);
}
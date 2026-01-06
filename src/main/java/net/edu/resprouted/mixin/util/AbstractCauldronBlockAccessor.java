package net.edu.resprouted.mixin.util;

import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.fluid.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractCauldronBlock.class)
public interface AbstractCauldronBlockAccessor {
    @Invoker("canBeFilledByDripstone")
    boolean invokeCanBeFilledByDripstone(Fluid fluid);
}
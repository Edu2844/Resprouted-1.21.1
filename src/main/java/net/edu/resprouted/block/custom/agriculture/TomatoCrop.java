package net.edu.resprouted.block.custom.agriculture;

import net.edu.resprouted.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import java.util.List;

public class TomatoCrop extends StakeCropBlock {
    public TomatoCrop(Settings settings) {
        super(settings);
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.TOMATO_SEEDS;
    }
    @Override
    protected List<ItemStack> getHarvestResult(Random random) {
        return List.of(new ItemStack(ModItems.TOMATO));
    }
    @Override
    protected void dropMatureItem(World world, BlockPos pos, BlockState state) {
        dropStack(world, pos, new ItemStack(ModItems.TOMATO, 1));
    }
}

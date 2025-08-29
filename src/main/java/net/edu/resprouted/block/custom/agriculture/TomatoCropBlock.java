package net.edu.resprouted.block.custom.agriculture;

import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.util.HarvestUtils;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import java.util.List;

public class TomatoCropBlock extends StakeCropBlock {
    public TomatoCropBlock(Settings settings) {
        super(settings);
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.TOMATO_SEEDS;
    }
    @Override
    protected List<ItemStack> getHarvestResult(Random random) {
        return HarvestUtils.create(random)
                .add(ModItems.TOMATO)
                .generate();
    }
    @Override
    protected void dropMatureItem(World world, BlockPos pos, BlockState state) {
        dropStack(world, pos, new ItemStack(ModItems.TOMATO, 1));
    }
}

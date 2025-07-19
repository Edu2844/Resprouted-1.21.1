package net.edu.resprouted.block.custom.agriculture;

import net.edu.resprouted.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import java.util.ArrayList;
import java.util.List;

public class ChiliCropBlock extends StakeCropBlock {
    public ChiliCropBlock(Settings settings) {
        super(settings);
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.CHILI_PEPPER_SEEDS;
    }
    @Override
    protected int getMaxVerticalGrowth() {
        return 1; //Este + 1 = 2 de alto
    }
    @Override
    protected List<ItemStack> getHarvestResult(Random random) {
        List<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(ModItems.CHILI_PEPPER));
        if (random.nextFloat() < 0.03f) {
            drops.add(new ItemStack(ModItems.GHOST_PEPPER));
        }
        return drops;
    }
    @Override
    protected void dropMatureItem(World world, BlockPos pos, BlockState state) {
        dropStack(world, pos, new ItemStack(ModItems.CHILI_PEPPER, 1));
    }
}

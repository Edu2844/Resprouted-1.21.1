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

public class ChiliCropBlock extends CropStakeBlock {
    public ChiliCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.CHILI_PEPPER_SEEDS;
    }

    @Override
    protected int getMaxVerticalGrowth() {
        return 1; //This + 1 = 2 high
    }

    @Override
    protected List<ItemStack> getHarvestResult(Random random) {
        return HarvestUtils.create(random)
                .add(ModItems.CHILI_PEPPER)
                .add(ModItems.GHOST_PEPPER, 0.03f)
                .generate();
    }

    @Override
    protected void dropMatureItem(World world, BlockPos pos, BlockState state) {
        dropStack(world, pos, new ItemStack(ModItems.CHILI_PEPPER, 1));
    }
}

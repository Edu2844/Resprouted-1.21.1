package net.edu.resprouted.block.custom.decorative;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChainBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class CustomChainBlock extends ChainBlock {
    public CustomChainBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.getItem() != asItem())
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        Direction d = hit.getSide(); if (d.getAxis() == state.get(AXIS) && d != Direction.UP)
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        BlockPos.Mutable e = pos.mutableCopy();

        for (int length = 1; length <= 64; length++) {
            e.move(Direction.DOWN);
            BlockState f = world.getBlockState(e);

            if (f.isAir()) {
                BlockState newOne = getDefaultState().with(AXIS, Direction.Axis.Y);

                if (world.setBlockState(e, newOne, Block.NOTIFY_ALL)) {
                    if (!player.getAbilities().creativeMode)
                        stack.decrement(1);

                    world.playSound(null, e, getSoundGroup(newOne).getPlaceSound(),
                            SoundCategory.BLOCKS, 1.0F, 0.8F + world.getRandom().nextFloat() * 0.4F);

                    return ItemActionResult.SUCCESS; }
                break;
            }

            if (!f.isOf(this) || f.get(AXIS) != Direction.Axis.Y)
                break;
        }

        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}

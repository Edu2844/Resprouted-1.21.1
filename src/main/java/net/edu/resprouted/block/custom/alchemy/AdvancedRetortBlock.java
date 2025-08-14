package net.edu.resprouted.block.custom.alchemy;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AdvancedRetortBlock extends RetortBlock{

    public AdvancedRetortBlock(Settings settings) {
        super(settings);
    }
    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockPos condenserPos = pos.offset(state.get(FACING).getOpposite());
        BlockState condenserState = world.getBlockState(condenserPos);
        if (!(condenserState.getBlock() instanceof AdvancedCondenserBlock condenserBlock)) {
            return ItemActionResult.FAIL;
        }
        return condenserBlock.onUseWithItem(stack, condenserState, world, condenserPos, player, hand, hit);
    }
}

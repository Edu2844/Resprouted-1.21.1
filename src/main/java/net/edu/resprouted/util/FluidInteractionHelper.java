package net.edu.resprouted.util;

import net.edu.resprouted.fluid.ModFluids;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FluidInteractionHelper {
    public static ItemActionResult handleFluidUse(PlayerEntity player, ItemStack stack, Storage<FluidVariant> storage, World world, BlockPos pos, boolean allowInsert, boolean allowExtract) {
        if (player.getWorld().isClient) return ItemActionResult.FAIL;
        try (Transaction tx = Transaction.openOuter()) {
            for (FluidContainerMapping mapping : FluidContainerLoader.getEntries()) {
                //Insert
                if (allowInsert && mapping.direction().allowsInsert() && ItemStack.areItemsEqual(stack, mapping.fullItem())) {
                    long inserted = storage.insert(mapping.fluid(), mapping.amount(), tx);
                    if (inserted == mapping.amount()) {
                        tx.commit();
                        stack.decrement(1);

                        ItemStack empty = mapping.emptyItem().copy();
                        if (!player.getInventory().insertStack(empty)) {
                            player.dropItem(empty, false);
                        }
                        playSound(world, pos, mapping.fluid(), true, isBottle(mapping.fullItem()));
                        return ItemActionResult.SUCCESS;
                    }
                }
                //Extract
                if (allowExtract && mapping.direction().allowsExtract() && stack.isOf(mapping.emptyItem().getItem())) {
                    long extracted = storage.extract(mapping.fluid(), mapping.amount(), tx);
                    if (extracted == mapping.amount()) {
                        tx.commit();
                        stack.decrement(1);

                        ItemStack filled = mapping.fullItem().copy();
                        if (!player.getInventory().insertStack(filled)) {
                            player.dropItem(filled, false);
                        }
                        playSound(world, pos, mapping.fluid(), false, isBottle(filled));
                        return ItemActionResult.SUCCESS;
                    }
                }
            }
        }
        return ItemActionResult.FAIL;
    }
    private static boolean isBottle(ItemStack stack) {
        return stack.isIn(ModTags.Items.GLASS_BOTTLES);
    }
    private static void playSound(World world, BlockPos pos, FluidVariant fluid, boolean isInsert, boolean isBottle) {
        SoundEvent sound;
        boolean isHoney = fluid.getFluid() == ModFluids.HONEY_STILL;
        if (isHoney) {
            sound = isInsert ? SoundEvents.BLOCK_HONEY_BLOCK_PLACE : SoundEvents.BLOCK_HONEY_BLOCK_BREAK;
        } else {
            sound = isBottle ? (isInsert ? SoundEvents.ITEM_BOTTLE_EMPTY : SoundEvents.ITEM_BOTTLE_FILL) : (isInsert ? SoundEvents.ITEM_BUCKET_EMPTY : SoundEvents.ITEM_BUCKET_FILL);
        }
        world.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }
}

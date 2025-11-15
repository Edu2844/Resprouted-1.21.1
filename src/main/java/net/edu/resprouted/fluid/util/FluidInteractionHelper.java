package net.edu.resprouted.fluid.util;

import net.edu.resprouted.fluid.data.FluidContainerMapping;
import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.resource.reload.FluidContainerLoader;
import net.edu.resprouted.util.ModTags;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FluidInteractionHelper {
    public static ItemActionResult fluidStorageUse(PlayerEntity player, ItemStack stack, Storage<FluidVariant> storage, World world, BlockPos pos, boolean allowInsert, boolean allowExtract) {

        if (player.getWorld().isClient) return ItemActionResult.FAIL;

        try (Transaction tx = Transaction.openOuter()) {
            for (FluidContainerMapping mapping : FluidContainerLoader.getEntries()) {

                if (allowInsert && mapping.direction().allowsInsert() && ItemStack.areItemsEqual(stack, mapping.fullItem())) {

                    long inserted = storage.insert(mapping.fluid(), mapping.amount(), tx);
                    if (inserted == mapping.amount()) {
                        tx.commit();

                        if (!player.isCreative()) {

                            stack.decrement(1);
                            ItemStack empty = mapping.emptyItem().copy();

                            if (!player.getInventory().insertStack(empty)) {
                                player.dropItem(empty, false);
                            }
                        }

                        playSound(world, pos, mapping.fluid(), true, isBottle(mapping.fullItem()));

                        return ItemActionResult.SUCCESS;
                    }
                }

                if (allowExtract && mapping.direction().allowsExtract() && stack.isOf(mapping.emptyItem().getItem())) {

                    long extracted = storage.extract(mapping.fluid(), mapping.amount(), tx);
                    if (extracted == mapping.amount()) {
                        tx.commit();

                        ItemStack filled = mapping.fullItem().copy();

                        ItemStack resultStack = ItemUsage.exchangeStack(stack, player, filled);

                        if (player.getMainHandStack() == stack) {
                            player.setStackInHand(Hand.MAIN_HAND, resultStack);

                        } else if (player.getOffHandStack() == stack) {

                            player.setStackInHand(Hand.OFF_HAND, resultStack);
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
            if (isInsert) {
                sound = SoundEvents.BLOCK_HONEY_BLOCK_PLACE;
            } else {
                sound = SoundEvents.BLOCK_HONEY_BLOCK_BREAK;
            }
        } else {
            if (isBottle) {
                if (isInsert) {
                    sound = SoundEvents.ITEM_BOTTLE_EMPTY;
                } else {
                    sound = SoundEvents.ITEM_BOTTLE_FILL;
                }
            } else {
                if (isInsert) {
                    sound = SoundEvents.ITEM_BUCKET_EMPTY;
                } else {
                    sound = SoundEvents.ITEM_BUCKET_FILL;
                }
            }
        }

        world.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }
}

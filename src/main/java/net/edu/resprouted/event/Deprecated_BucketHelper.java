package net.edu.resprouted.event;

import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.item.ModItems;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.Map;

public class Deprecated_BucketHelper {
    //BALDE A FLUIDO
    public static final Map<Item, FluidVariant> BUCKET_TO_VARIANT = Map.of(
            Items.POTION, FluidVariant.of(Fluids.WATER),
            ModItems.SWEET_BERRY_JUICE_BUCKET, FluidVariant.of(ModFluids.SWEET_BERRY_JUICE_STILL),
            ModItems.IRON_BERRY_JUICE_BUCKET, FluidVariant.of(ModFluids.IRON_BERRY_JUICE_STILL),
            ModItems.APPLE_JUICE_BUCKET, FluidVariant.of(ModFluids.APPLE_JUICE_STILL),
            ModItems.GOLDEN_APPLE_JUICE_BUCKET, FluidVariant.of(ModFluids.GOLDEN_APPLE_JUICE_STILL),
            ModItems.HONEY_BUCKET, FluidVariant.of(ModFluids.HONEY_STILL),
            ModItems.GRAPE_JUICE_BUCKET, FluidVariant.of(ModFluids.GRAPE_JUICE_STILL),
            ModItems.GLOW_BERRY_JUICE_BUCKET, FluidVariant.of(ModFluids.GLOW_BERRY_JUICE_STILL),
            ModItems.OLIVE_OIL_BUCKET, FluidVariant.of(ModFluids.OLIVE_OIL_STILL)
    );
    //FLUIDO A BALDE
    public static final Map<FluidVariant, Item> VARIANT_TO_BUCKET = Map.of(
            FluidVariant.of(Fluids.WATER), Items.WATER_BUCKET,
            FluidVariant.of(ModFluids.SWEET_BERRY_JUICE_STILL), ModItems.SWEET_BERRY_JUICE_BUCKET,
            FluidVariant.of(ModFluids.IRON_BERRY_JUICE_STILL), ModItems.IRON_BERRY_JUICE_BUCKET,
            FluidVariant.of(ModFluids.APPLE_JUICE_STILL), ModItems.APPLE_JUICE_BUCKET,
            FluidVariant.of(ModFluids.GOLDEN_APPLE_JUICE_STILL), ModItems.GOLDEN_APPLE_JUICE_BUCKET,
            FluidVariant.of(ModFluids.HONEY_STILL), ModItems.HONEY_BUCKET,
            FluidVariant.of(ModFluids.GRAPE_JUICE_STILL), ModItems.GRAPE_JUICE_BUCKET,
            FluidVariant.of(ModFluids.GLOW_BERRY_JUICE_STILL), ModItems.GLOW_BERRY_JUICE_BUCKET,
            FluidVariant.of(ModFluids.OLIVE_OIL_STILL), ModItems.OLIVE_OIL_BUCKET
    );
    //BOTELLA A FLUIDO
    public static final Map<Item, FluidVariant> BOTTLE_TO_VARIANT = Map.of(
            Items.WATER_BUCKET, FluidVariant.of(Fluids.WATER),
            ModItems.SWEET_BERRY_JUICE_BOTTLE, FluidVariant.of(ModFluids.SWEET_BERRY_JUICE_STILL),
            ModItems.IRON_BERRY_JUICE_BOTTLE, FluidVariant.of(ModFluids.IRON_BERRY_JUICE_STILL),
            ModItems.APPLE_JUICE_BOTTLE, FluidVariant.of(ModFluids.APPLE_JUICE_STILL),
            ModItems.GOLDEN_APPLE_JUICE_BOTTLE, FluidVariant.of(ModFluids.GOLDEN_APPLE_JUICE_STILL),
            Items.HONEY_BOTTLE, FluidVariant.of(ModFluids.HONEY_STILL),
            ModItems.GRAPE_JUICE_BOTTLE, FluidVariant.of(ModFluids.GRAPE_JUICE_STILL),
            ModItems.GLOW_BERRY_JUICE_BOTTLE, FluidVariant.of(ModFluids.GLOW_BERRY_JUICE_STILL),
            ModItems.OLIVE_OIL_BOTTLE, FluidVariant.of(ModFluids.OLIVE_OIL_STILL)
    );
    //FLUIDO A BOTELLA
    public static final Map<FluidVariant, Item> VARIANT_TO_BOTTLE = Map.of(
            FluidVariant.of(ModFluids.SWEET_BERRY_JUICE_STILL), ModItems.SWEET_BERRY_JUICE_BOTTLE,
            FluidVariant.of(ModFluids.IRON_BERRY_JUICE_STILL), ModItems.IRON_BERRY_JUICE_BOTTLE,
            FluidVariant.of(ModFluids.APPLE_JUICE_STILL), ModItems.APPLE_JUICE_BOTTLE,
            FluidVariant.of(ModFluids.GOLDEN_APPLE_JUICE_STILL), ModItems.GOLDEN_APPLE_JUICE_BOTTLE,
            FluidVariant.of(ModFluids.HONEY_STILL), Items.HONEY_BOTTLE,
            FluidVariant.of(ModFluids.GRAPE_JUICE_STILL), ModItems.GRAPE_JUICE_BOTTLE,
            FluidVariant.of(ModFluids.GLOW_BERRY_JUICE_STILL), ModItems.GLOW_BERRY_JUICE_BOTTLE,
            FluidVariant.of(ModFluids.OLIVE_OIL_STILL), ModItems.OLIVE_OIL_BOTTLE
    );
    public static ItemActionResult handleFluidBucketUse(PlayerEntity player, Hand hand, ItemStack stack, Storage<FluidVariant> storage, World world, BlockPos pos)
    {
        if (player.getWorld().isClient) return ItemActionResult.FAIL;
        try (Transaction tx = Transaction.openOuter()) {
            // INSERTAR: Si es un balde lleno
            if (BUCKET_TO_VARIANT.containsKey(stack.getItem())) {
                FluidVariant fluid = BUCKET_TO_VARIANT.get(stack.getItem());
                long inserted = storage.insert(fluid, FluidConstants.BUCKET, tx);
                if (inserted == FluidConstants.BUCKET) {
                    tx.commit();
                    player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                    world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    return ItemActionResult.SUCCESS;
                }
            }
            // INSERTAR: Desde botella llena
            if (BOTTLE_TO_VARIANT.containsKey(stack.getItem())) {
                FluidVariant fluid = BOTTLE_TO_VARIANT.get(stack.getItem());
                long inserted = storage.insert(fluid, FluidConstants.BOTTLE, tx);
                if (inserted == FluidConstants.BOTTLE) {
                    tx.commit();
                    stack.decrement(1);

                    ItemStack emptyBottle = new ItemStack(Items.GLASS_BOTTLE);
                    if (!player.getInventory().insertStack(emptyBottle)) {
                        player.dropItem(emptyBottle, false);
                    }
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    return ItemActionResult.SUCCESS;
                }
            }
            // EXTRAER: Si es un balde vacío
            if (stack.isOf(Items.BUCKET)) {
                for (FluidVariant variant : VARIANT_TO_BUCKET.keySet()) {
                    long extracted = storage.extract(variant, FluidConstants.BUCKET, tx);
                    if (extracted == FluidConstants.BUCKET) {
                        tx.commit();
                        stack.decrement(1);

                        // Darle un balde lleno
                        ItemStack filledBucket = new ItemStack(VARIANT_TO_BUCKET.get(variant));
                        if (!player.getInventory().insertStack(filledBucket)) {
                            player.dropItem(filledBucket, false);
                        }
                        world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        return ItemActionResult.SUCCESS;
                    }
                }
            }
            // EXTRAER: Si es una botella vacia
            if (stack.isOf(Items.GLASS_BOTTLE)) {
                for (FluidVariant variant : VARIANT_TO_BOTTLE.keySet()) {
                    long extracted = storage.extract(variant, FluidConstants.BOTTLE, tx);
                    if (extracted == FluidConstants.BOTTLE) {
                        tx.commit();
                        stack.decrement(1);
                        ItemStack filledBottle = new ItemStack(VARIANT_TO_BOTTLE.get(variant));
                        if (!player.getInventory().insertStack(filledBottle)) {
                            player.dropItem(filledBottle, false);
                        }
                        world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        return ItemActionResult.SUCCESS;
                    }
                }
            }
        }
        return ItemActionResult.FAIL;
    }
}

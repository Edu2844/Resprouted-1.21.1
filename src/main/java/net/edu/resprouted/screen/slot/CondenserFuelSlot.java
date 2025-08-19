package net.edu.resprouted.screen.slot;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import static net.minecraft.screen.slot.FurnaceFuelSlot.isBucket;

public class CondenserFuelSlot extends Slot {

    public CondenserFuelSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }
    public boolean canInsert(ItemStack stack) {
        return isFuel(stack) || isBucket(stack);
    }
    public static boolean isFuel(ItemStack itemStack) {
        return AbstractFurnaceBlockEntity.canUseAsFuel(itemStack);
    }
}
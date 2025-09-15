package net.edu.resprouted.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.screen.slot.Slot;

public class BucketSlot extends Slot {
    public BucketSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }
    public boolean canInsert(ItemStack stack) {
        return matches(stack);
    }
    public static boolean matches(ItemStack stack) {
        Item item = stack.getItem();
        return (item instanceof BucketItem);
    }
}

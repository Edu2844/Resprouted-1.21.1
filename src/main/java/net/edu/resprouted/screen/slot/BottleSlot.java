package net.edu.resprouted.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

public class BottleSlot extends Slot {
    public BottleSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }
    public boolean canInsert(ItemStack stack) {
        return matches(stack);
    }
    public static boolean matches(ItemStack stack) {
        return stack.isOf(Items.GLASS_BOTTLE);
    }
}

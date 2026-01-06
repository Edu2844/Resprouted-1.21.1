package net.edu.resprouted.screen.slot;

import net.edu.resprouted.item.custom.BoozeBottleItem;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

public class BoozeBottleSlot extends Slot {
    public BoozeBottleSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    public boolean canInsert(ItemStack stack) {
        return matches(stack);
    }

    public static boolean matches(ItemStack stack) {
        Item item = stack.getItem();
        return (item instanceof BoozeBottleItem) || stack.isOf(Items.GLASS_BOTTLE);
    }
}

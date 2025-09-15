package net.edu.resprouted.screen.slot;

import net.edu.resprouted.util.ModTags;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class ModifierSlot extends Slot {
    public ModifierSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }
    public boolean canInsert(ItemStack stack) {
        return matches(stack);
    }
    public static boolean matches(ItemStack stack) {
        return stack.isIn(ModTags.Items.MODIFIERS);
    }
}

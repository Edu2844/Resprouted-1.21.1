package net.edu.resprouted.screen.custom;

import net.edu.resprouted.block.entity.custom.BrewingBarrelBE;
import net.edu.resprouted.screen.ModScreenHandlers;
import net.edu.resprouted.screen.slot.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

public class BrewingBarrelScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final BrewingBarrelBE blockEntity;

    public BrewingBarrelScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, (BrewingBarrelBE) playerInventory.player.getWorld().getBlockEntity(pos), new ArrayPropertyDelegate(4));
    }

    public BrewingBarrelScreenHandler(int syncId, PlayerInventory playerInventory, BrewingBarrelBE blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreenHandlers.BREWING_BARREL_SCREEN_HANDLER, syncId);
        this.inventory = blockEntity;
        this.blockEntity = blockEntity;
        this.propertyDelegate = propertyDelegate;

        this.addSlot(new BucketSlot(inventory, 0, 62, 7));
        this.addSlot(new BottleSlot(inventory, 1, 116, 7));
        this.addSlot(new BoozeBottleSlot(inventory, 2, 26, 15));

        this.addSlot(new OutputSlot(inventory, 3, 62, 63));
        this.addSlot(new OutputSlot(inventory, 4, 116, 63));
        this.addSlot(new OutputSlot(inventory, 5, 26, 55));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        this.addProperties(propertyDelegate);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        Slot slot = this.slots.get(index);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            if (index < inventory.size()) {
                if (!this.insertItem(originalStack, inventory.size(), slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (BucketSlot.matches(originalStack)) {
                    if (!this.insertItem(originalStack, 0, 1, false))
                        return ItemStack.EMPTY;
                }
                else if (BottleSlot.matches(originalStack)) {
                    if (!this.insertItem(originalStack, 1, 2, false))
                        return ItemStack.EMPTY;
                }
                else if (BoozeBottleSlot.matches(originalStack)) {
                    if (!this.insertItem(originalStack, 2, 3, false))
                        return ItemStack.EMPTY;
                }
                else if (!this.insertItem(originalStack, 0, 3, false)) {
                    return ItemStack.EMPTY;
                }
            }
            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; i++) {
            for (int l = 0; l < 9; l++) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public BrewingBarrelBE getBlockEntity() {
        return this.blockEntity;
    }

    public int getBrewingTime() {
        return this.propertyDelegate.get(0);
    }

    public int getScaledArrowProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        int arrowProgressWidth = 24;

        return (maxProgress == 0 || progress == 0) ? 0 : progress * arrowProgressWidth / maxProgress;
    }

    public int getScaledAuxArrowProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        int arrowProgressWidth = 13;

        return (maxProgress == 0 || progress == 0) ? 0 : progress * arrowProgressWidth / maxProgress;
    }
}

package net.edu.resprouted.screen.custom;

import net.edu.resprouted.block.entity.custom.AdvancedCondenserBlockEntity;
import net.edu.resprouted.screen.ModScreenHandlers;
import net.edu.resprouted.screen.slot.BottleSlot;
import net.edu.resprouted.screen.slot.CondenserFuelSlot;
import net.edu.resprouted.screen.slot.OutputSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class AdvancedCondenserScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final AdvancedCondenserBlockEntity blockEntity;

    public AdvancedCondenserScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, (AdvancedCondenserBlockEntity) playerInventory.player.getWorld().getBlockEntity(pos), new ArrayPropertyDelegate(5));
    }

    public AdvancedCondenserScreenHandler(int syncId, PlayerInventory playerInventory, AdvancedCondenserBlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreenHandlers.ADVANCED_CONDENSER_SCREEN_HANDLER, syncId);
        this.inventory = blockEntity;
        this.blockEntity = blockEntity;
        this.propertyDelegate = propertyDelegate;

        this.addSlot(new Slot(inventory, 0, 27, 59));
        this.addSlot(new Slot(inventory, 1, 27, 35));
        this.addSlot(new Slot(inventory, 2, 27, 11));
        this.addSlot(new Slot(inventory, 3, 66, 7));
        this.addSlot(new CondenserFuelSlot(inventory, 4, 66, 62));
        this.addSlot(new BottleSlot(inventory, 5, 105, 7));
        this.addSlot(new OutputSlot(inventory, 6, 105, 35));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        this.addProperties(propertyDelegate);
    }
    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
    }
    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }
    public int getScaledArrowProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        int arrowPixelWidth = 50;
        return (maxProgress == 0 || progress == 0) ? 0 : progress * arrowPixelWidth / maxProgress;
    }
    public boolean isBurning() {
        return this.propertyDelegate.get(2) > 0;
    }
    public float getFuelProgress() {
        int total = this.propertyDelegate.get(3);
        if (total == 0) {
            total = 200;
        }
        return MathHelper.clamp((float) this.propertyDelegate.get(2) / (float) total, 0.0F, 1.0F);
    }
    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (index < inventory.size()) {
                if (!this.insertItem(originalStack, inventory.size(), slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (CondenserFuelSlot.isFuel(originalStack)) {
                    if (!this.insertItem(originalStack, 4, 5, false))
                        return ItemStack.EMPTY;
                }
                else if (BottleSlot.matches(originalStack)) {
                    if (!this.insertItem(originalStack, 5, 6, false))
                        return ItemStack.EMPTY;
                }
                else if (!this.insertItem(originalStack, 0, 4, false)) {
                    return ItemStack.EMPTY;
                }
            }
            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
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
    public AdvancedCondenserBlockEntity getBlockEntity() {
        return this.blockEntity;
    }
}

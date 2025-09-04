package net.edu.resprouted.block.entity.custom;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.custom.decorative.CabinetBlock;
import net.edu.resprouted.block.enums.CabinetType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.entity.ViewerCountManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CabinetBE extends LootableContainerBlockEntity implements NamedScreenHandlerFactory {
    private DefaultedList<ItemStack> inventory;

    public CabinetBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CABINET_BE, pos, state);
        inventory = DefaultedList.ofSize(27, ItemStack.EMPTY);
    }
    @Override
    protected Text getContainerName() {
        return Text.translatable("container.resprouted.cabinet");
    }
    @Override
    public int size() {
        return inventory.size();
    }
    @Override
    protected DefaultedList<ItemStack> getHeldStacks() {
        return inventory;
    }
    @Override
    protected void setHeldStacks(DefaultedList<ItemStack> list) {
        this.inventory = list;
    }
    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory inv) {
        return GenericContainerScreenHandler.createGeneric9x3(syncId, inv, this);
    }
    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
        super.writeNbt(nbt, lookup);
        Inventories.writeNbt(nbt, inventory, lookup);
    }
    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
        super.readNbt(nbt, lookup);
        inventory = DefaultedList.ofSize(size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt, inventory, lookup);
    }
    @Override
    public void onOpen(PlayerEntity player) {
        super.onOpen(player);
        stateManager.openContainer(player, world, pos, this.getCachedState());
    }
    @Override
    public void onClose(PlayerEntity player) {
        super.onClose(player);
        stateManager.closeContainer(player, world, pos, this.getCachedState());
    }
    public boolean isMainPart() {
        BlockState state = getCachedState();
        CabinetType type = state.get(CabinetBlock.CABINET_TYPE);

        if (type == CabinetType.SINGLE) return true;
        return type == CabinetType.BOTTOM;
    }
    @Override
    public void setStack(int slot, ItemStack stack) {
        super.setStack(slot, stack);
        markDirtyAndUpdate();
    }
    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack stack = super.removeStack(slot, amount);
        markDirtyAndUpdate();
        return stack;
    }
    @Override
    public ItemStack removeStack(int slot) {
        ItemStack stack = super.removeStack(slot);
        markDirtyAndUpdate();
        return stack;
    }
    private void markDirtyAndUpdate() {
        this.markDirty();
        if (this.world != null && this.isMainPart()) {
            this.world.updateComparators(this.pos, this.getCachedState().getBlock());
        }
    }
    public final ViewerCountManager stateManager = new ViewerCountManager() {
        @Override
        protected void onContainerOpen(World world, BlockPos pos, BlockState state) {
            if (CabinetBE.this.isMainPart()) {
                if (!state.get(CabinetBlock.OPEN)) {
                    world.setBlockState(pos, state.with(CabinetBlock.OPEN, true), 3);
                    world.playSound(null, pos, SoundEvents.BLOCK_BARREL_OPEN, SoundCategory.BLOCKS, 0.5f, world.random.nextFloat() * 0.1f + 0.9f);
                }
            }
        }
        @Override
        protected void onContainerClose(World world, BlockPos pos, BlockState state) {
            if (CabinetBE.this.isMainPart()) {
                if (state.get(CabinetBlock.OPEN)) {
                    world.setBlockState(pos, state.with(CabinetBlock.OPEN, false), 3);
                    world.playSound(null, pos, SoundEvents.BLOCK_BARREL_CLOSE, SoundCategory.BLOCKS, 0.5f, world.random.nextFloat() * 0.1f + 0.9f);
                }
            }
        }
        @Override
        public void onViewerCountUpdate(World world, BlockPos pos, BlockState state, int oldCount, int newCount) {
            boolean open = state.get(CabinetBlock.OPEN);
            if (oldCount > 0 && newCount == 0) {
                if (open) {
                    world.setBlockState(pos, state.with(CabinetBlock.OPEN, false), 3);
                }
            } else if (oldCount == 0 && newCount > 0) {
                if (!open) {
                    world.setBlockState(pos, state.with(CabinetBlock.OPEN, true), 3);
                }
            }
        }
        @Override
        protected boolean isPlayerViewing(PlayerEntity player) {
            if (player.currentScreenHandler instanceof GenericContainerScreenHandler handler) {
                Inventory inventory = handler.getInventory();
                return inventory == CabinetBE.this
                        || (inventory instanceof DoubleInventory di && di.isPart(CabinetBE.this));
            }
            return false;
        }

    };
}

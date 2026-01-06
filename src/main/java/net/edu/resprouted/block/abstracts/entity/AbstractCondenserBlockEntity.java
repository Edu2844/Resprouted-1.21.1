package net.edu.resprouted.block.abstracts.entity;

import net.edu.resprouted.block.abstracts.AbstractCondenserBlock;
import net.edu.resprouted.block.interfaces.ImplementedInventory;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AbstractCondenserBlockEntity extends AbstractSingleFluidStorageBlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    protected final DefaultedList<ItemStack> inventory;
    protected int burnTime = 0;
    protected int fuelTime = 0;
    protected int progress = 0;
    protected int maxProgress = 380;
    protected final PropertyDelegate propertyDelegate;
    protected static final long RECIPE_FLUID_COST = 10125L; //10125L = 125mB
    private int smokeTimer = 0;

    protected AbstractCondenserBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int inventorySize, long fluidCapacity) {
        super(type, pos, state, fluidCapacity);
        this.inventory = DefaultedList.ofSize(inventorySize, ItemStack.EMPTY);
        this.propertyDelegate = createPropertyDelegate();
    }

    // ========= ABSTRACT METHODS ========= //
    protected abstract PropertyDelegate createPropertyDelegate();
    protected abstract boolean hasRecipe();
    protected abstract void craftItem();
    public abstract Text getDisplayName();
    public abstract ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player);
    protected abstract void spawnSmokeParticles(World world, BlockPos pos, BlockState state);
    protected abstract ItemStack getFuelStack();
    protected abstract void updateLitState(World world, BlockPos pos, BlockState state, boolean shouldBeLit);
    protected abstract int getOutputSlot();


    // ========= OVERRIDES ========= //
    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);

        nbt.putInt("condenser.progress", progress);
        nbt.putInt("condenser.max_progress", maxProgress);
        nbt.putInt("BurnTime", this.burnTime);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);

        progress = nbt.getInt("condenser.progress");
        maxProgress = nbt.getInt("condenser.max_progress");
        this.burnTime = nbt.getInt("BurnTime");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    // ========= TICK ========= //
    public void clientTick(World world, BlockPos pos, BlockState state) {
        boolean shouldEmitSmoke = this.progress > 0;

        if (shouldEmitSmoke) {
            smokeTimer++;

            if (smokeTimer >= 3) {
                spawnSmokeParticles(world, pos, state);
                smokeTimer = 0;
            }
        }
    }

    public void serverTick(World world, BlockPos pos, BlockState state) {
        boolean dirty = false;
        boolean hasFuelAvailable = hasFuel();

        if (!isBurning() && hasRecipe() && hasFuelAvailable) {
            ItemStack fuelStack = getFuelStack();
            int fuelBurnTime = FuelRegistry.INSTANCE.get(fuelStack.getItem());

            if (fuelBurnTime > 0) {
                this.burnTime = fuelBurnTime;
                this.fuelTime = fuelBurnTime;
                fuelStack.decrement(1);
                dirty = true;
            }
        }

        if (isBurning()) {
            this.burnTime--;

            if (hasRecipe()) {
                increaseCraftingProgress();

                if (hasCraftingFinished()) {
                    craftItem();
                    resetProgress();
                    dirty = true;
                }

            } else {
                resetProgress();
            }

            markDirty(world, pos, state);

        } else {
            resetProgress();
        }
        boolean shouldBeLit = isBurning() || (hasFuelAvailable && hasRecipe());

        if (state.get(AbstractCondenserBlock.LIT) != shouldBeLit) {
            updateLitState(world, pos, state, shouldBeLit);
            dirty = true;
        }

        if (dirty) {
            markDirty(world, pos, state);
        }
    }


    // ========= METHODS ========= //
    protected void spawnSmoke(World world, double x, double y, double z) {
        double yVel = 0.06D;
        double randomOffsetY = world.random.nextDouble() * 0.02D;
        world.addParticle(ParticleTypes.SMOKE, x, y + randomOffsetY, z, 0, yVel, 0);
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public boolean hasFluid() {
        SingleFluidStorage fluidStorage = getFluidStorage();
        return !fluidStorage.variant.isBlank() && fluidStorage.amount >= RECIPE_FLUID_COST &&
                fluidStorage.variant.isOf(Fluids.WATER);
    }

    protected boolean hasFuel() {
        ItemStack fuelStack = getFuelStack();
        return !fuelStack.isEmpty() && FuelRegistry.INSTANCE.get(fuelStack.getItem()) > 0;
    }

    protected void resetProgress() {
        if (this.progress != 0) {
            this.progress = 0;

            markDirty();

            if (world != null) {
                world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
            }
        }
    }

    protected boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    protected void increaseCraftingProgress() {
        this.progress++;

        if (this.progress % 20 == 0) {
            markDirty();

            if (world != null) {
                world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
            }
        }
    }

    protected boolean canInsertItemIntoOutputSlot(ItemStack result) {
        ItemStack outputStack = this.getStack(getOutputSlot());
        if (outputStack.isEmpty()) return true;

        return ItemStack.areItemsAndComponentsEqual(outputStack, result)
                && outputStack.getCount() + result.getCount() <= outputStack.getMaxCount();
    }

    protected void consumeFluidAndFinishCrafting() {
        SingleFluidStorage fluidStorage = getFluidStorage();
        fluidStorage.amount -= RECIPE_FLUID_COST;
        if (fluidStorage.amount < 0) {
            fluidStorage.amount = 0;
        }
        markDirty();
        if (world != null) {
            world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
            world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }
}
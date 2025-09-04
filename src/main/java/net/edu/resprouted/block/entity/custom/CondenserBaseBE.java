package net.edu.resprouted.block.entity.custom;

import net.edu.resprouted.block.custom.alchemy.AbstractCondenserBlock;
import net.edu.resprouted.block.interfaces.ImplementedInventory;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public abstract class CondenserBaseBE extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    protected final DefaultedList<ItemStack> inventory;
    protected int burnTime = 0;
    protected int fuelTime = 0;
    protected int progress = 0;
    protected int maxProgress = 380;
    protected final PropertyDelegate propertyDelegate;
    protected final SingleFluidStorage fluidStorage;
    protected static final long RECIPE_FLUID_COST = 10125L; //10125L = 125mB

    private int smokeTimer = 0;

    protected CondenserBaseBE(BlockEntityType<?> type, BlockPos pos, BlockState state, int inventorySize) {
        super(type, pos, state);
        this.inventory = DefaultedList.ofSize(inventorySize, ItemStack.EMPTY);
        this.propertyDelegate = createPropertyDelegate();
        this.fluidStorage = SingleFluidStorage.withFixedCapacity(FluidConstants.BUCKET * 8, this::update);
    }

    // ========= ABSTRACT METHODS =========
    protected abstract PropertyDelegate createPropertyDelegate();
    protected abstract boolean hasRecipe();
    protected abstract void craftItem();
    public abstract Text getDisplayName();
    public abstract ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player);

    // ========= PROPERTIES & NBT =========
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
        NbtCompound fluidNbt = new NbtCompound();
        SingleVariantStorage.writeNbt(fluidStorage, FluidVariant.CODEC, fluidNbt, registryLookup);
        nbt.put("Fluid", fluidNbt);
    }
    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("condenser.progress");
        maxProgress = nbt.getInt("condenser.max_progress");
        this.burnTime = nbt.getInt("BurnTime");
        if (nbt.contains("Fluid", NbtElement.COMPOUND_TYPE)) {
            SingleVariantStorage.readNbt(
                    fluidStorage,
                    FluidVariant.CODEC,
                    FluidVariant::blank,
                    nbt.getCompound("Fluid"),
                    registryLookup
            );
        }
    }

    // ========= TICK METHODS =========
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
    protected abstract void spawnSmokeParticles(World world, BlockPos pos, BlockState state);

    public void serverTick(World world, BlockPos pos, BlockState state) {
        boolean dirty = false;
        boolean hasFuelAvailable = hasFuelAvailable();

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
    protected abstract ItemStack getFuelStack();
    protected abstract boolean hasFuelAvailable();
    protected abstract void updateLitState(World world, BlockPos pos, BlockState state, boolean shouldBeLit);

    // ========= METHODS =========
    protected void spawnSmoke(World world, double x, double y, double z) {
        double yVel = 0.06D;
        double randomOffsetY = world.random.nextDouble() * 0.02D;
        world.addParticle(ParticleTypes.SMOKE, x, y + randomOffsetY, z, 0, yVel, 0);
    }
    public boolean isBurning() {
        return this.burnTime > 0;
    }
    public boolean hasFluid() {
        return !fluidStorage.variant.isBlank() && fluidStorage.amount >= 10125 &&
                fluidStorage.variant.isOf(Fluids.WATER);
    }
    protected void resetProgress() {
        if (this.progress != 0) {
            this.progress = 0;
            this.maxProgress = 380;
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

    // ========= INVENTORY AND FLUID =========
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
    private void update() {
        markDirty();
        if (world != null)
            world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
    }
    public SingleFluidStorage getFluidStorage() {
        return this.fluidStorage;
    }
    public SingleFluidStorage getFluidTankProvider(Direction direction) {
        return this.fluidStorage;
    }
    public SingleFluidStorage getFluidTank() {
        return this.fluidStorage;
    }
}

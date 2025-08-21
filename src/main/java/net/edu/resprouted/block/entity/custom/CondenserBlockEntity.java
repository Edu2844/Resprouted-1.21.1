package net.edu.resprouted.block.entity.custom;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.custom.alchemy.CondenserBlock;
import net.edu.resprouted.block.interfaces.ImplementedInventory;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.recipe.custom.CondenserRecipe;
import net.edu.resprouted.recipe.Input.CondenserRecipeInput;
import net.edu.resprouted.screen.custom.CondenserScreenHandler;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CondenserBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);

    private int burnTime = 0;
    private int fuelTime = 0;
    public int progress = 0;
    private int maxProgress = 380;
    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int FUEL_SLOT = 2;
    private static final int BOTTLE_SLOT = 3;
    private static final int OUTPUT_SLOT = 4;
    protected final PropertyDelegate propertyDelegate;

    private static final long RECIPE_FLUID_COST = 10125L; //10125L = 125mB

    public CondenserBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CONDENSER_BE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CondenserBlockEntity.this.progress;
                    case 1 -> CondenserBlockEntity.this.maxProgress;
                    case 2 -> CondenserBlockEntity.this.burnTime;
                    case 3 -> CondenserBlockEntity.this.fuelTime;
                    default -> 0;
                };
            }
            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: CondenserBlockEntity.this.progress = value; break;
                    case 1: CondenserBlockEntity.this.maxProgress = value; break;
                    case 2: CondenserBlockEntity.this.burnTime = value; break;
                    case 3: CondenserBlockEntity.this.fuelTime = value; break;
                }
            }
            @Override
            public int size() {
                return 4;
            }
        };
    }
    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }
    @Override
    public Text getDisplayName() {
        return Text.translatable("block.resprouted.condenser");
    }
    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CondenserScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("condenser.progress", progress);
        nbt.putInt("condenser.max_progress", maxProgress);
        nbt.putInt("BurnTime", this.burnTime);
        //Fluids
        NbtCompound fluidNbt = new NbtCompound();
        SingleVariantStorage.writeNbt(condenser, FluidVariant.CODEC, fluidNbt, registryLookup);
        nbt.put("Fluid", fluidNbt);
    }
    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("condenser.progress");
        maxProgress = nbt.getInt("condenser.max_progress");
        this.burnTime = nbt.getInt("BurnTime");
        //Fluids
        if (nbt.contains("Fluid", NbtElement.COMPOUND_TYPE)) {
            SingleVariantStorage.readNbt(
                    condenser,
                    FluidVariant.CODEC,
                    FluidVariant::blank,
                    nbt.getCompound("Fluid"),
                    registryLookup
            );
        }
    }
    private int smokeTimer = 0;
    public void clientTick(World world, BlockPos pos, BlockState state) {
        boolean shouldEmitSmoke = this.progress > 0;
        if (shouldEmitSmoke) {
            smokeTimer++;
            if (smokeTimer >= 3) {
                Direction facing = state.get(CondenserBlock.FACING);

                if (facing == Direction.NORTH || facing == Direction.SOUTH) {
                    spawnSmoke(world, pos.getX() - 0.5D, pos.getY() + 1.0625D, pos.getZ() + 0.5D);
                    spawnSmoke(world, pos.getX() + 1.5D, pos.getY() + 1.0625D, pos.getZ() + 0.5D);
                } else if (facing == Direction.EAST || facing == Direction.WEST) {
                    spawnSmoke(world, pos.getX() + 0.5D, pos.getY() + 1.0625D, pos.getZ() - 0.5D);
                    spawnSmoke(world, pos.getX() + 0.5D, pos.getY() + 1.0625D, pos.getZ() + 1.5D);
                }
                smokeTimer = 0;
            }
        }
    }
    private void spawnSmoke(World world, double x, double y, double z) {
        double yVel = 0.06D;
        double randomOffsetY = world.random.nextDouble() * 0.02D;

        world.addParticle(ParticleTypes.SMOKE, x, y + randomOffsetY, z, 0, yVel, 0);
    }
    public void serverTick(World world, BlockPos pos, BlockState state) {
        boolean dirty = false;
        boolean hasFuelAvailable = !this.inventory.get(FUEL_SLOT).isEmpty() && FuelRegistry.INSTANCE.get(this.inventory.get(FUEL_SLOT).getItem()) > 0;

        if (!isBurning() && hasRecipe() && hasFuelAvailable) {
            ItemStack fuelStack = this.inventory.get(FUEL_SLOT);
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
        if (state.get(CondenserBlock.LIT) != shouldBeLit) {
            world.setBlockState(pos, state.with(CondenserBlock.LIT, shouldBeLit), Block.NOTIFY_ALL);
            dirty = true;
        }
        if (dirty) {
            markDirty(world, pos, state);
        }
    }
    public boolean isBurning() {
        return this.burnTime > 0;
    }
    private boolean hasRecipe() {
        CondenserRecipeInput input = new CondenserRecipeInput(
                inventory.getFirst(),
                inventory.get(INPUT_SLOT_2),
                inventory.get(FUEL_SLOT),
                inventory.get(BOTTLE_SLOT),
                this.pos
        );
        assert world != null;
        Optional<RecipeEntry<CondenserRecipe>> match = world.getRecipeManager().getFirstMatch(ModRecipes.CONDENSER_TYPE, input, world);
        return match.isPresent() && canInsertItemIntoOutputSlot(match.get().value().craft(input, world.getRegistryManager()));
    }
    private boolean canInsertItemIntoOutputSlot(ItemStack result) {
        ItemStack outputStack = this.getStack(OUTPUT_SLOT);
        if (outputStack.isEmpty()) return true;

        return ItemStack.areItemsAndComponentsEqual(outputStack, result)
                && outputStack.getCount() + result.getCount() <= outputStack.getMaxCount();
    }
    private void craftItem() {
        CondenserRecipeInput input = new CondenserRecipeInput(
                inventory.getFirst(),
                inventory.get(INPUT_SLOT_2),
                inventory.get(FUEL_SLOT),
                inventory.get(BOTTLE_SLOT),
                this.pos
        );
        assert world != null;
        Optional<RecipeEntry<CondenserRecipe>> match = world.getRecipeManager()
                .getFirstMatch(ModRecipes.CONDENSER_TYPE, input, world);

        if (match.isEmpty()) return;

        ItemStack result = match.get().value().craft(input, world.getRegistryManager());
        ItemStack outputStack = this.getStack(OUTPUT_SLOT);

        if (outputStack.isEmpty()) {
            this.setStack(OUTPUT_SLOT, result.copy());
        } else if (ItemStack.areItemsAndComponentsEqual(outputStack, result)) {
            outputStack.increment(result.getCount());
        }
        this.removeStack(INPUT_SLOT_1, 1);
        this.removeStack(INPUT_SLOT_2, 1);
        this.removeStack(BOTTLE_SLOT, 1);

        this.condenser.amount -=  RECIPE_FLUID_COST;
        if (this.condenser.amount < 0) {
            this.condenser.amount = 0;
        }
        markDirty();
        world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
        world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
    private void resetProgress() {
        if (this.progress != 0) {
            this.progress = 0;
            this.maxProgress = 380;
            markDirty();
            if (world != null) {
                world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
            }
        }
    }
    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }
    private void increaseCraftingProgress() {
        this.progress++;
        if (this.progress % 20 == 0) {
            markDirty();
            if (world != null) {
                world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
            }
        }
    }
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
    public boolean hasFluid() {
        return !condenser.variant.isBlank() && condenser.amount > 0;
    }
    public final SingleFluidStorage condenser = SingleFluidStorage.withFixedCapacity(FluidConstants.BUCKET * 8, this::update);
    private void update() {
        markDirty();
        if(world != null)
            world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
    }
    public SingleFluidStorage getFluidTankProvider(Direction direction) {
        return this.condenser;
    }
    public SingleFluidStorage getFluidTank() {
        return this.condenser;
    }
}

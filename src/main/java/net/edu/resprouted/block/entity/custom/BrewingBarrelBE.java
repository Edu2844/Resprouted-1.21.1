package net.edu.resprouted.block.entity.custom;

import dev.architectury.fluid.FluidStack;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.interfaces.ImplementedInventory;
import net.edu.resprouted.item.custom.BoozeBottleItem;
import net.edu.resprouted.recipe.custom.BrewingRecipes;
import net.edu.resprouted.screen.custom.BrewingBarrelScreenHandler;
import net.edu.resprouted.util.FluidQualityHelper;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;


public class BrewingBarrelBE extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>,ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(6, ItemStack.EMPTY);
    public static final int INPUT_IN_SLOT = 0;
    public static final int OUTPUT_IN_SLOT = 1;
    public static final int AUX_IN_SLOT = 2;
    public static final int INPUT_OUT_SLOT = 3;
    public static final int OUTPUT_OUT_SLOT = 4;
    public static final int AUX_OUT_SLOT = 5;

    protected final PropertyDelegate propertyDelegate;
    protected int progress = 0;
    protected int maxProgress = Resprouted.CONFIG.getMaxBrewTime();
    private int inputQuality = 0;
    private int outputQuality = 0;

    public BrewingBarrelBE (BlockPos pos, BlockState state) {
        super(ModBlockEntities.BREWING_BARREL_BE, pos, state);
        this.propertyDelegate = new PropertyDelegate(){
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BrewingBarrelBE.this.progress;
                    case 1 -> BrewingBarrelBE.this.maxProgress;
                    case 2 -> BrewingBarrelBE.this.inputQuality;
                    case 3 -> BrewingBarrelBE.this.outputQuality;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: BrewingBarrelBE.this.progress = value;
                    case 1: BrewingBarrelBE.this.maxProgress = value;
                    case 2: BrewingBarrelBE.this.inputQuality = value;
                    case 3: BrewingBarrelBE.this.outputQuality = value;
                }
            }

            @Override
            public int size() {
                return 4;
            }
        };
    }
    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);

        nbt.putInt("brewing.progress", progress);
        nbt.putInt("brewing.max_progress", maxProgress);
        nbt.putInt("brewing.inputquality", inputQuality);
        nbt.putInt("brewing.outputquality", outputQuality);

        NbtCompound inputFluidNbt = new NbtCompound();
        SingleVariantStorage.writeNbt(input, FluidVariant.CODEC, inputFluidNbt, registryLookup);
        nbt.put("brewing.inputFluid", inputFluidNbt);

        NbtCompound outputFluidNbt = new NbtCompound();
        SingleVariantStorage.writeNbt(output, FluidVariant.CODEC, outputFluidNbt, registryLookup);
        nbt.put("brewing.outputFluid", outputFluidNbt);

        NbtCompound auxiliaryFluidNbt = new NbtCompound();
        SingleVariantStorage.writeNbt(auxiliary, FluidVariant.CODEC, auxiliaryFluidNbt, registryLookup);
        nbt.put("brewing.auxiliaryFluid", auxiliaryFluidNbt);
    }
    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);

        progress = nbt.getInt("brewing.progress");
        maxProgress = nbt.getInt("brewing.max_progress");
        this.inputQuality = nbt.getInt("brewing.primerquality");
        this.outputQuality = nbt.getInt("brewing.outputquality");

        if (nbt.contains("brewing.inputFluid", NbtElement.COMPOUND_TYPE)) {
            SingleVariantStorage.readNbt(
                    input,
                    FluidVariant.CODEC,
                    FluidVariant::blank,
                    nbt.getCompound("brewing.inputFluid"),
                    registryLookup
            );
        }
        if (nbt.contains("brewing.outputFluid", NbtElement.COMPOUND_TYPE)) {
            SingleVariantStorage.readNbt(
                    output,
                    FluidVariant.CODEC,
                    FluidVariant::blank,
                    nbt.getCompound("brewing.outputFluid"),
                    registryLookup
            );
        }
        if (nbt.contains("brewing.auxiliaryFluid", NbtElement.COMPOUND_TYPE)) {
            SingleVariantStorage.readNbt(
                    auxiliary,
                    FluidVariant.CODEC,
                    FluidVariant::blank,
                    nbt.getCompound("brewing.auxiliaryFluid"),
                    registryLookup
            );
        }
    }
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        NbtCompound nbt = super.toInitialChunkDataNbt(registryLookup);

        NbtCompound inputFluidNbt = new NbtCompound();
        SingleVariantStorage.writeNbt(input, FluidVariant.CODEC, inputFluidNbt, registryLookup);
        nbt.put("brewing.inputFluid", inputFluidNbt);

        NbtCompound outputFluidNbt = new NbtCompound();
        SingleVariantStorage.writeNbt(output, FluidVariant.CODEC, outputFluidNbt, registryLookup);
        nbt.put("brewing.outputFluid", outputFluidNbt);

        NbtCompound auxiliaryFluidNbt = new NbtCompound();
        SingleVariantStorage.writeNbt(auxiliary, FluidVariant.CODEC, auxiliaryFluidNbt, registryLookup);
        nbt.put("brewing.auxiliaryFluid", auxiliaryFluidNbt);

        return nbt;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BrewingBarrelScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
    private final SingleFluidStorage input = SingleFluidStorage.withFixedCapacity(FluidConstants.BUCKET * 8, this::update);
    private final SingleFluidStorage output = SingleFluidStorage.withFixedCapacity(FluidConstants.BUCKET * 8, this::update);
    private final SingleFluidStorage auxiliary = SingleFluidStorage.withFixedCapacity(FluidConstants.BUCKET, this::update);


    private void update() {
        markDirty();
        if(world != null)
            world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
    }

    public SingleFluidStorage getInputTank() {
        return this.input;
    }

    public SingleFluidStorage getOutputTank() {
        return output;
    }

    public SingleFluidStorage getAuxiliaryTank() {
        return auxiliary;
    }

    public static void tick(World world, BlockPos pos, BlockState state, BrewingBarrelBE blockEntity) {
        if (world.isClient()) return;

        blockEntity.maxProgress = Resprouted.CONFIG.getMaxBrewTime();
        blockEntity.handleBucketInteractions();
        blockEntity.handleBottleInteractions();
        blockEntity.handleOutputBottleInteractions();
        blockEntity.tryCraft();
    }

    public void handleBucketInteractions() {
        ItemStack inputStack = getStack(INPUT_IN_SLOT);
        ItemStack outputStack = getStack(INPUT_OUT_SLOT);

        boolean isInsertingEmptyBucket = inputStack.isOf(Items.BUCKET);

        if (!outputStack.isEmpty() && isInsertingEmptyBucket) return;
        if (inputStack.isEmpty()) return;

        try (Transaction transaction = Transaction.openOuter()) {

            if (inputStack.getItem() instanceof BucketItem && inputStack.getItem() != Items.BUCKET) {
                Fluid fluid = getFluidFromBucket(inputStack);

                if (fluid != Fluids.EMPTY) {
                    FluidVariant variant = FluidVariant.of(fluid);
                    long amount = FluidConstants.BUCKET;

                    if ((input.isResourceBlank() || input.getResource().isOf(fluid)) &&
                            input.insert(variant, amount, transaction) == amount) {

                        inputStack.decrement(1);
                        if (inputStack.isEmpty()) {
                            setStack(INPUT_IN_SLOT, ItemStack.EMPTY);
                        }

                        ItemStack currentOutput = getStack(INPUT_OUT_SLOT);

                        if (currentOutput.isEmpty()) {
                            setStack(INPUT_OUT_SLOT, new ItemStack(Items.BUCKET));
                        } else if (currentOutput.isOf(Items.BUCKET) && currentOutput.getCount() < currentOutput.getMaxCount()) {
                            currentOutput.increment(1);
                            setStack(INPUT_OUT_SLOT, currentOutput);
                        } else {
                            return;
                        }

                        transaction.commit();
                        markDirty();

                        return;
                    }
                }
            }

            if (inputStack.isOf(Items.BUCKET) && !input.isResourceBlank()) {
                FluidVariant currentFluid = input.getResource();
                long amount = FluidConstants.BUCKET;

                if (input.extract(currentFluid, amount, transaction) == amount) {
                    Item filledBucket = currentFluid.getFluid().getBucketItem();

                    if (filledBucket != null && filledBucket != Items.AIR) {

                        inputStack.decrement(1);
                        if (inputStack.isEmpty()) {
                            setStack(INPUT_IN_SLOT, ItemStack.EMPTY);
                        }

                        setStack(INPUT_OUT_SLOT, new ItemStack(filledBucket));

                        transaction.commit();
                        markDirty();
                    }
                }
            }
        }
    }

    private Fluid getFluidFromBucket(ItemStack stack) {
        if (stack.isOf(Items.WATER_BUCKET))
            return Fluids.WATER;

        if (stack.isOf(Items.LAVA_BUCKET))
            return Fluids.LAVA;

        Item item = stack.getItem();
        if (item instanceof BucketItem) {
            for (Fluid fluid : Registries.FLUID) {
                if (fluid.getBucketItem() == item) {
                    return fluid;
                }
            }
        }

        return Fluids.EMPTY;
    }
    public void handleBottleInteractions() {
        ItemStack auxInputStack = getStack(AUX_IN_SLOT);

        if (auxInputStack.getItem() instanceof BoozeBottleItem boozeBottle) {
            try (Transaction transaction = Transaction.openOuter()) {

                FluidStack fluidFromBottle = boozeBottle.toFluidStack(auxInputStack);

                FluidVariant variant = FluidVariant.of(fluidFromBottle.getFluid())
                        .withComponentChanges(fluidFromBottle.getComponents().getChanges());

                long amount = FluidConstants.BUCKET;

                if ((auxiliary.isResourceBlank() || auxiliary.getResource().equals(variant)) &&
                        auxiliary.insert(variant, amount, transaction) == amount) {

                    auxInputStack.decrement(1);
                    if (auxInputStack.isEmpty()) {
                        setStack(AUX_IN_SLOT, ItemStack.EMPTY);
                    }

                    ItemStack currentAuxOutput = getStack(AUX_OUT_SLOT);
                    if (currentAuxOutput.isEmpty()) {
                        setStack(AUX_OUT_SLOT, new ItemStack(Items.GLASS_BOTTLE));
                    } else if (currentAuxOutput.isOf(Items.GLASS_BOTTLE) &&
                            currentAuxOutput.getCount() < currentAuxOutput.getMaxCount()) {
                        currentAuxOutput.increment(1);
                        setStack(AUX_OUT_SLOT, currentAuxOutput);
                    } else {
                        return;
                    }

                    transaction.commit();
                    markDirty();
                }
            }
        }

        else if (auxInputStack.isOf(Items.GLASS_BOTTLE) && !auxiliary.isResourceBlank()) {
            try (Transaction transaction = Transaction.openOuter()) {

                FluidVariant currentFluidVariant = auxiliary.getResource();
                long amount = FluidConstants.BUCKET;

                if (auxiliary.extract(currentFluidVariant, amount, transaction) == amount) {

                    FluidStack extractedFluid = FluidStack.create(currentFluidVariant.getFluid(), amount);
                    extractedFluid.applyComponents(currentFluidVariant.getComponents());

                    ItemStack filledBottle = BoozeBottleItem.fromFluidStack(extractedFluid);

                    if (!filledBottle.isEmpty()) {
                        auxInputStack.decrement(1);
                        if (auxInputStack.isEmpty()) {
                            setStack(AUX_IN_SLOT, ItemStack.EMPTY);
                        }

                        ItemStack currentAuxOutput = getStack(AUX_OUT_SLOT);
                        if (currentAuxOutput.isEmpty()) {
                            setStack(AUX_OUT_SLOT, filledBottle);
                        } else if (currentAuxOutput.getItem() == filledBottle.getItem() &&
                                BoozeBottleItem.getQuality(currentAuxOutput) == BoozeBottleItem.getQuality(filledBottle) &&
                                currentAuxOutput.getCount() < currentAuxOutput.getMaxCount()) {
                            currentAuxOutput.increment(1);
                            setStack(AUX_OUT_SLOT, currentAuxOutput);
                        } else {
                            return;
                        }

                        transaction.commit();
                        markDirty();
                    }
                }
            }
        }
    }
    public void handleOutputBottleInteractions() {
        ItemStack in = getStack(OUTPUT_IN_SLOT);
        ItemStack out = getStack(OUTPUT_OUT_SLOT);

        if (!in.isOf(Items.GLASS_BOTTLE) || output.isResourceBlank()) return;

        if (!out.isEmpty() && output.getResource().getFluid() != BoozeBottleItem.getFluidFromBottle(out)) return;

        try (Transaction t = Transaction.openOuter()) {
            FluidVariant variant = output.getResource();
            long amount = FluidConstants.BUCKET;

            if (output.extract(variant, amount, t) == amount) {
                FluidStack fluid = FluidStack.create(variant.getFluid(), amount);
                fluid.applyComponents(variant.getComponents());

                ItemStack bottle = BoozeBottleItem.fromFluidStack(fluid);
                if (bottle.isEmpty()) return;

                if (!out.isEmpty() && (out.getItem() != bottle.getItem() ||
                        BoozeBottleItem.getQuality(out) != BoozeBottleItem.getQuality(bottle) ||
                        out.getCount() >= out.getMaxCount())) return;

                in.decrement(1);
                if (in.isEmpty()) setStack(OUTPUT_IN_SLOT, ItemStack.EMPTY);

                if (out.isEmpty()) {
                    setStack(OUTPUT_OUT_SLOT, bottle);
                } else {
                    out.increment(1);
                    setStack(OUTPUT_OUT_SLOT, out);
                }

                t.commit();
                markDirty();
            }
        }
    }
    public void tryCraft() {
        if (world == null) return;

        FluidStack inputFluid = FluidStack.create(
                input.getResource().getFluid(),
                (int) input.getAmount()
        );

        inputFluid.applyComponents(input.getResource().getComponents());

        FluidStack auxiliaryFluid = null;
        if (!auxiliary.isResourceBlank()) {
            auxiliaryFluid = FluidStack.create(
                    auxiliary.getResource().getFluid(),
                    (int) auxiliary.getAmount()
            );

            auxiliaryFluid.applyComponents(auxiliary.getResource().getComponents());
        }

        Optional<BrewingRecipes.BrewingRecipe> recipeOpt = BrewingRecipes.findMatchingRecipe(
                inputFluid, auxiliaryFluid
        );


        if (recipeOpt.isPresent() && canCraft()) {
            progress++;

            if (progress >= maxProgress) {
                craft(recipeOpt.get(), inputFluid, auxiliaryFluid);
                progress = 0;
            }
            markDirty();
        } else {
            if (progress != 0) {
                progress = 0;
                markDirty();
            }
        }
    }

    private boolean canCraft() {
        return input.getAmount() > 0 &&
                output.getAmount() < output.getCapacity();
    }

    private void craft(BrewingRecipes.BrewingRecipe recipe, FluidStack inputFluid, FluidStack auxiliaryFluid) {
        try (Transaction transaction = Transaction.openOuter()) {

            long availableSpace = output.getCapacity() - output.getAmount();
            long amountToConvert = Math.min(availableSpace, input.getAmount());

            if (amountToConvert <= 0) return;

            input.extract(input.getResource(), amountToConvert, transaction);

            assert world != null;
            FluidStack newResult = recipe.getResult(inputFluid, auxiliaryFluid, world.random);

            if (newResult != null) {
                newResult.setAmount(amountToConvert);

                if (!output.isResourceBlank() && output.getResource().isOf(newResult.getFluid())) {

                    FluidStack existingFluid = FluidStack.create(
                            output.getResource().getFluid(),
                            (int) output.getAmount()
                    );
                    existingFluid.applyComponents(output.getResource().getComponents());

                    float existingQuality = FluidQualityHelper.getQuality(existingFluid);
                    FluidQualityHelper.setQuality(newResult, existingQuality);
                }

                FluidVariant resultVariant = FluidVariant.of(newResult.getFluid())
                        .withComponentChanges(newResult.getComponents().getChanges());

                output.insert(resultVariant, newResult.getAmount(), transaction);
            }

            transaction.commit();
            markDirty();
        }
    }
}

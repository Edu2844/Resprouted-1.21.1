package net.edu.resprouted.block.entity.custom;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.custom.alchemy.CondenserBlock;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.recipe.custom.CondenserRecipe;
import net.edu.resprouted.recipe.Input.CondenserRecipeInput;
import net.edu.resprouted.screen.custom.CondenserScreenHandler;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Optional;

public class CondenserBE extends CondenserBaseBE {
    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int FUEL_SLOT = 2;
    private static final int BOTTLE_SLOT = 3;
    private static final int OUTPUT_SLOT = 4;

    public CondenserBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CONDENSER_BE, pos, state, 5);
    }
    @Override
    protected PropertyDelegate createPropertyDelegate() {
        return new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CondenserBE.this.progress;
                    case 1 -> CondenserBE.this.maxProgress;
                    case 2 -> CondenserBE.this.burnTime;
                    case 3 -> CondenserBE.this.fuelTime;
                    default -> 0;
                };
            }
            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: CondenserBE.this.progress = value; break;
                    case 1: CondenserBE.this.maxProgress = value; break;
                    case 2: CondenserBE.this.burnTime = value; break;
                    case 3: CondenserBE.this.fuelTime = value; break;
                }
            }
            @Override
            public int size() {
                return 4;
            }
        };
    }
    @Override
    public Text getDisplayName() {
        return Text.translatable("block.resprouted.condenser");
    }
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CondenserScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
    @Override
    protected void spawnSmokeParticles(World world, BlockPos pos, BlockState state) {
        Direction facing = state.get(CondenserBlock.FACING);

        if (facing == Direction.NORTH || facing == Direction.SOUTH) {
            spawnSmoke(world, pos.getX() - 0.5D, pos.getY() + 1.0625D, pos.getZ() + 0.5D);
            spawnSmoke(world, pos.getX() + 1.5D, pos.getY() + 1.0625D, pos.getZ() + 0.5D);
        } else if (facing == Direction.EAST || facing == Direction.WEST) {
            spawnSmoke(world, pos.getX() + 0.5D, pos.getY() + 1.0625D, pos.getZ() - 0.5D);
            spawnSmoke(world, pos.getX() + 0.5D, pos.getY() + 1.0625D, pos.getZ() + 1.5D);
        }
    }
    @Override
    protected ItemStack getFuelStack() {
        return this.inventory.get(FUEL_SLOT);
    }
    @Override
    protected boolean hasFuelAvailable() {
        return !this.inventory.get(FUEL_SLOT).isEmpty() &&
                FuelRegistry.INSTANCE.get(this.inventory.get(FUEL_SLOT).getItem()) > 0;
    }
    @Override
    protected void updateLitState(World world, BlockPos pos, BlockState state, boolean shouldBeLit) {
        world.setBlockState(pos, state.with(CondenserBlock.LIT, shouldBeLit), Block.NOTIFY_ALL);
    }
    @Override
    protected boolean hasRecipe() {
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
        return match.isPresent() && canInsertItemIntoOutputSlot(match.get().value().craft(input, world.getRegistryManager()));
    }
    private boolean canInsertItemIntoOutputSlot(ItemStack result) {
        ItemStack outputStack = this.getStack(OUTPUT_SLOT);
        if (outputStack.isEmpty()) return true;

        return ItemStack.areItemsAndComponentsEqual(outputStack, result)
                && outputStack.getCount() + result.getCount() <= outputStack.getMaxCount();
    }
    @Override
    protected void craftItem() {
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

        this.fluidStorage.amount -= RECIPE_FLUID_COST;
        if (this.fluidStorage.amount < 0) {
            this.fluidStorage.amount = 0;
        }
        markDirty();
        world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
        world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}


package net.edu.resprouted.block.entity.custom;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.abstracts.AbstractCondenserBlockEntity;
import net.edu.resprouted.block.custom.alchemy.AdvancedCondenserBlock;
import net.edu.resprouted.recipe.Input.AdvancedCondenserRecipeInput;
import net.edu.resprouted.recipe.Input.CondenserRecipeInput;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.recipe.custom.AdvancedCondenserRecipe;
import net.edu.resprouted.recipe.custom.CondenserRecipe;
import net.edu.resprouted.screen.custom.AdvancedCondenserScreenHandler;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
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

public class AdvancedCondenserBE extends AbstractCondenserBlockEntity {
    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int INPUT_SLOT_3 = 2;
    private static final int MODIFIER_SLOT = 3;
    private static final int FUEL_SLOT = 4;
    private static final int BOTTLE_SLOT = 5;
    private static final int OUTPUT_SLOT = 6;

    public AdvancedCondenserBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ADVANCED_CONDENSER_BE, pos, state, 7, FluidConstants.BUCKET * 8);

    }

    @Override
    protected PropertyDelegate createPropertyDelegate() {
        return new PropertyDelegate() {

            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> AdvancedCondenserBE.this.progress;
                    case 1 -> AdvancedCondenserBE.this.maxProgress;
                    case 2 -> AdvancedCondenserBE.this.burnTime;
                    case 3 -> AdvancedCondenserBE.this.fuelTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: AdvancedCondenserBE.this.progress = value; break;
                    case 1: AdvancedCondenserBE.this.maxProgress = value; break;
                    case 2: AdvancedCondenserBE.this.burnTime = value; break;
                    case 3: AdvancedCondenserBE.this.fuelTime = value; break;
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
        return Text.literal("");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new AdvancedCondenserScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void spawnSmokeParticles(World world, BlockPos pos, BlockState state) {
        Direction facing = state.get(AdvancedCondenserBlock.FACING);

        if (facing == Direction.NORTH || facing == Direction.SOUTH) {
            spawnSmoke(world, pos.getX() - 0.5D, pos.getY() + 1.0625D, pos.getZ() + 0.5D);
            spawnSmoke(world, pos.getX() + 1.5D, pos.getY() + 1.0625D, pos.getZ() + 0.5D);

            BlockPos i = pos.offset(facing.getOpposite());

            spawnSmoke(world, i.getX() + 0.5D, pos.getY() + 1.0625D, i.getZ() + 0.5D);

        } else if (facing == Direction.EAST || facing == Direction.WEST) {
            spawnSmoke(world, pos.getX() + 0.5D, pos.getY() + 1.0625D, pos.getZ() - 0.5D);
            spawnSmoke(world, pos.getX() + 0.5D, pos.getY() + 1.0625D, pos.getZ() + 1.5D);

            BlockPos backPos = pos.offset(facing.getOpposite());

            spawnSmoke(world, backPos.getX() + 0.5D, pos.getY() + 1.0625D, backPos.getZ() + 0.5D);
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
        world.setBlockState(pos, state.with(AdvancedCondenserBlock.LIT, shouldBeLit), Block.NOTIFY_ALL);
        BlockPos j = pos.up();
        BlockState k = world.getBlockState(j);

        if (k.isOf(ModBlocks.ADVANCED_CONDENSER)) {
            world.setBlockState(j, k.with(AdvancedCondenserBlock.LIT, shouldBeLit), Block.NOTIFY_ALL);
        }
    }

    @Override
    protected boolean hasRecipe() {
        AdvancedCondenserRecipeInput advancedInput = new AdvancedCondenserRecipeInput(
                inventory.getFirst(),
                inventory.get(INPUT_SLOT_2),
                inventory.get(INPUT_SLOT_3),
                inventory.get(MODIFIER_SLOT),
                inventory.get(FUEL_SLOT),
                inventory.get(BOTTLE_SLOT),
                this.pos
        );
        assert world != null;
        Optional<RecipeEntry<AdvancedCondenserRecipe>> advancedMatch = world.getRecipeManager()
                .getFirstMatch(ModRecipes.ADVANCED_CONDENSER_TYPE, advancedInput, world);

        if (advancedMatch.isPresent()) {
            return canInsertItemIntoOutputSlot(advancedMatch.get().value().craft(advancedInput, world.getRegistryManager()));
        }

        CondenserRecipeInput basicInput = new CondenserRecipeInput(
                inventory.getFirst(),
                inventory.get(INPUT_SLOT_2),
                inventory.get(FUEL_SLOT),
                inventory.get(BOTTLE_SLOT),
                this.pos
        );

        Optional<RecipeEntry<CondenserRecipe>> basicMatch = world.getRecipeManager()
                .getFirstMatch(ModRecipes.CONDENSER_TYPE, basicInput, world);

        return basicMatch.isPresent() && canInsertItemIntoOutputSlot(basicMatch.get().value().craft(basicInput, world.getRegistryManager()));
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack result) {
        ItemStack outputStack = this.getStack(OUTPUT_SLOT);
        if (outputStack.isEmpty()) return true;

        return ItemStack.areItemsAndComponentsEqual(outputStack, result)
                && outputStack.getCount() + result.getCount() <= outputStack.getMaxCount();
    }

    @Override
    protected void craftItem() {
        AdvancedCondenserRecipeInput advancedInput = new AdvancedCondenserRecipeInput(
                inventory.getFirst(),
                inventory.get(INPUT_SLOT_2),
                inventory.get(INPUT_SLOT_3),
                inventory.get(MODIFIER_SLOT),
                inventory.get(FUEL_SLOT),
                inventory.get(BOTTLE_SLOT),
                this.pos
        );
        assert world != null;

        //Advanced Condenser Recipes
        Optional<RecipeEntry<AdvancedCondenserRecipe>> adv = world.getRecipeManager()
                .getFirstMatch(ModRecipes.ADVANCED_CONDENSER_TYPE, advancedInput, world);

        if (adv.isPresent()) {
            ItemStack result = adv.get().value().craft(advancedInput, world.getRegistryManager());
            ItemStack outputStack = this.getStack(OUTPUT_SLOT);

            if (outputStack.isEmpty()) {
                this.setStack(OUTPUT_SLOT, result.copy());
            } else if (ItemStack.areItemsAndComponentsEqual(outputStack, result)) {
                outputStack.increment(result.getCount());
            }

            for (Ingredient ing : adv.get().value().ingredients()) {
                removeOneMatching(ing, INPUT_SLOT_1, INPUT_SLOT_2, INPUT_SLOT_3);
            }
            adv.get().value().modifier().ifPresent(modIng -> this.removeStack(MODIFIER_SLOT, 1));
            this.removeStack(BOTTLE_SLOT, 1);

            SingleFluidStorage fluidStorage = getFluidStorage();
            fluidStorage.amount -= RECIPE_FLUID_COST;
            if (fluidStorage.amount < 0) {
                fluidStorage.amount = 0;
            }

            markDirty();
            world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
            world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return;
        }

        CondenserRecipeInput basicInput = new CondenserRecipeInput(
                inventory.getFirst(),
                inventory.get(INPUT_SLOT_2),
                inventory.get(FUEL_SLOT),
                inventory.get(BOTTLE_SLOT),
                this.pos
        );

        //Basic Condenser Recipes
        Optional<RecipeEntry<CondenserRecipe>> basic = world.getRecipeManager()
                .getFirstMatch(ModRecipes.CONDENSER_TYPE, basicInput, world);

        if (basic.isEmpty()) return;

        ItemStack result = basic.get().value().craft(basicInput, world.getRegistryManager());
        ItemStack outputStack = this.getStack(OUTPUT_SLOT);

        if (outputStack.isEmpty()) {
            this.setStack(OUTPUT_SLOT, result.copy());
        } else if (ItemStack.areItemsAndComponentsEqual(outputStack, result)) {
            outputStack.increment(result.getCount());
        }

        this.removeStack(INPUT_SLOT_1, 1);
        this.removeStack(INPUT_SLOT_2, 1);
        this.removeStack(BOTTLE_SLOT, 1);

        SingleFluidStorage fluidStorage = getFluidStorage();
        fluidStorage.amount -= RECIPE_FLUID_COST;
        if (fluidStorage.amount < 0) {
            fluidStorage.amount = 0;
        }

        markDirty();
        world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
        world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

    private void removeOneMatching(Ingredient ingredient, int... slots) {
        for (int slot : slots) {
            ItemStack stack = this.getStack(slot);

            if (!stack.isEmpty() && ingredient.test(stack)) {
                this.removeStack(slot, 1);
                return;
            }
        }
    }
}

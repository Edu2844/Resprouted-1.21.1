package net.edu.resprouted.block.entity.custom;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.block.abstracts.entity.AbstractCondenserBlockEntity;
import net.edu.resprouted.block.custom.alchemy.AdvancedCondenserBlock;
import net.edu.resprouted.recipe.Input.AdvancedCondenserRecipeInput;
import net.edu.resprouted.recipe.Input.BasicCondenserRecipeInput;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.recipe.custom.AdvancedCondenserRecipe;
import net.edu.resprouted.recipe.custom.BasicCondenserRecipe;
import net.edu.resprouted.screen.custom.AdvancedCondenserScreenHandler;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdvancedCondenserBlockEntity extends AbstractCondenserBlockEntity {
    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int INPUT_SLOT_3 = 2;
    private static final int MODIFIER_SLOT = 3;
    private static final int FUEL_SLOT = 4;
    private static final int BOTTLE_SLOT = 5;
    private static final int OUTPUT_SLOT = 6;

    public AdvancedCondenserBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ADVANCED_CONDENSER_BE, pos, state, 7, FluidConstants.BUCKET * 8);
    }

    @Override
    protected PropertyDelegate createPropertyDelegate() {
        return new PropertyDelegate() {

            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> AdvancedCondenserBlockEntity.this.progress;
                    case 1 -> AdvancedCondenserBlockEntity.this.maxProgress;
                    case 2 -> AdvancedCondenserBlockEntity.this.burnTime;
                    case 3 -> AdvancedCondenserBlockEntity.this.fuelTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: AdvancedCondenserBlockEntity.this.progress = value; break;
                    case 1: AdvancedCondenserBlockEntity.this.maxProgress = value; break;
                    case 2: AdvancedCondenserBlockEntity.this.burnTime = value; break;
                    case 3: AdvancedCondenserBlockEntity.this.fuelTime = value; break;
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

            BlockPos backPos = pos.offset(facing.getOpposite());
            spawnSmoke(world, backPos.getX() + 0.5D, pos.getY() + 1.0625D, backPos.getZ() + 0.5D);

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
    protected void updateLitState(World world, BlockPos pos, BlockState state, boolean shouldBeLit) {
        world.setBlockState(pos, state.with(AdvancedCondenserBlock.LIT, shouldBeLit), Block.NOTIFY_ALL);
        BlockPos upperPos = pos.up();
        BlockState upperState = world.getBlockState(upperPos);

        if (upperState.isOf(ModBlocks.ADVANCED_CONDENSER)) {
            world.setBlockState(upperPos, upperState.with(AdvancedCondenserBlock.LIT, shouldBeLit), Block.NOTIFY_ALL);
        }
    }

    @Override
    protected int getOutputSlot() {
        return OUTPUT_SLOT;
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


        List<ItemStack> nonEmptyInputs = new ArrayList<>();
        if (!inventory.getFirst().isEmpty()) nonEmptyInputs.add(inventory.getFirst());
        if (!inventory.get(INPUT_SLOT_2).isEmpty()) nonEmptyInputs.add(inventory.get(INPUT_SLOT_2));
        if (!inventory.get(INPUT_SLOT_3).isEmpty()) nonEmptyInputs.add(inventory.get(INPUT_SLOT_3));

        ItemStack inputA = !nonEmptyInputs.isEmpty() ? nonEmptyInputs.get(0) : ItemStack.EMPTY;
        ItemStack inputB = nonEmptyInputs.size() > 1 ? nonEmptyInputs.get(1) : ItemStack.EMPTY;

        BasicCondenserRecipeInput basicInput = new BasicCondenserRecipeInput(
                inputA,
                inputB,
                inventory.get(FUEL_SLOT),
                inventory.get(BOTTLE_SLOT),
                this.pos
        );

        Optional<RecipeEntry<BasicCondenserRecipe>> basicMatch = world.getRecipeManager()
                .getFirstMatch(ModRecipes.CONDENSER_TYPE, basicInput, world);

        return basicMatch.isPresent() && canInsertItemIntoOutputSlot(basicMatch.get().value().craft(basicInput, world.getRegistryManager()));
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

        // Advanced Condenser Recipes
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

            consumeFluidAndFinishCrafting();
            return;
        }

        List<ItemStack> nonEmptyInputs = new ArrayList<>();
        if (!inventory.getFirst().isEmpty()) nonEmptyInputs.add(inventory.getFirst());
        if (!inventory.get(INPUT_SLOT_2).isEmpty()) nonEmptyInputs.add(inventory.get(INPUT_SLOT_2));
        if (!inventory.get(INPUT_SLOT_3).isEmpty()) nonEmptyInputs.add(inventory.get(INPUT_SLOT_3));

        ItemStack inputA = !nonEmptyInputs.isEmpty() ? nonEmptyInputs.get(0) : ItemStack.EMPTY;
        ItemStack inputB = nonEmptyInputs.size() > 1 ? nonEmptyInputs.get(1) : ItemStack.EMPTY;

        BasicCondenserRecipeInput basicInput = new BasicCondenserRecipeInput(
                inputA,
                inputB,
                inventory.get(FUEL_SLOT),
                inventory.get(BOTTLE_SLOT),
                this.pos
        );

        // Basic Condenser Recipes
        Optional<RecipeEntry<BasicCondenserRecipe>> basic = world.getRecipeManager()
                .getFirstMatch(ModRecipes.CONDENSER_TYPE, basicInput, world);

        if (basic.isEmpty()) return;

        ItemStack result = basic.get().value().craft(basicInput, world.getRegistryManager());
        ItemStack outputStack = this.getStack(OUTPUT_SLOT);

        if (outputStack.isEmpty()) {
            this.setStack(OUTPUT_SLOT, result.copy());
        } else if (ItemStack.areItemsAndComponentsEqual(outputStack, result)) {
            outputStack.increment(result.getCount());
        }

        for (Ingredient ing : basic.get().value().ingredients()) {
            removeOneMatching(ing, INPUT_SLOT_1, INPUT_SLOT_2, INPUT_SLOT_3);
        }
        this.removeStack(BOTTLE_SLOT, 1);

        consumeFluidAndFinishCrafting();
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
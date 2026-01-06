package net.edu.resprouted.block.entity.custom;

import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.abstracts.entity.AbstractCondenserBlockEntity;
import net.edu.resprouted.block.custom.alchemy.BasicCondenserBlock;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.recipe.custom.BasicCondenserRecipe;
import net.edu.resprouted.recipe.Input.BasicCondenserRecipeInput;
import net.edu.resprouted.screen.custom.BasicCondenserScreenHandler;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Optional;

public class BasicCondenserBlockEntity extends AbstractCondenserBlockEntity {
    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int FUEL_SLOT = 2;
    private static final int BOTTLE_SLOT = 3;
    private static final int OUTPUT_SLOT = 4;

    public BasicCondenserBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CONDENSER_BE, pos, state, 5, FluidConstants.BUCKET * 8);
    }

    @Override
    protected PropertyDelegate createPropertyDelegate() {
        return new PropertyDelegate() {

            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BasicCondenserBlockEntity.this.progress;
                    case 1 -> BasicCondenserBlockEntity.this.maxProgress;
                    case 2 -> BasicCondenserBlockEntity.this.burnTime;
                    case 3 -> BasicCondenserBlockEntity.this.fuelTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: BasicCondenserBlockEntity.this.progress = value; break;
                    case 1: BasicCondenserBlockEntity.this.maxProgress = value; break;
                    case 2: BasicCondenserBlockEntity.this.burnTime = value; break;
                    case 3: BasicCondenserBlockEntity.this.fuelTime = value; break;
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
        return Text.translatable("container.resprouted.condenser");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BasicCondenserScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void spawnSmokeParticles(World world, BlockPos pos, BlockState state) {
        Direction facing = state.get(BasicCondenserBlock.FACING);

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
    protected void updateLitState(World world, BlockPos pos, BlockState state, boolean shouldBeLit) {
        world.setBlockState(pos, state.with(BasicCondenserBlock.LIT, shouldBeLit), Block.NOTIFY_ALL);
    }

    @Override
    protected int getOutputSlot() {
        return OUTPUT_SLOT;
    }

    @Override
    protected boolean hasRecipe() {
        BasicCondenserRecipeInput input = new BasicCondenserRecipeInput(
                inventory.getFirst(),
                inventory.get(INPUT_SLOT_2),
                inventory.get(FUEL_SLOT),
                inventory.get(BOTTLE_SLOT),
                this.pos
        );
        assert world != null;
        Optional<RecipeEntry<BasicCondenserRecipe>> match = world.getRecipeManager()
                .getFirstMatch(ModRecipes.CONDENSER_TYPE, input, world);

        return match.isPresent() && canInsertItemIntoOutputSlot(match.get().value().craft(input, world.getRegistryManager()));
    }

    @Override
    protected void craftItem() {
        BasicCondenserRecipeInput input = new BasicCondenserRecipeInput(
                inventory.getFirst(),
                inventory.get(INPUT_SLOT_2),
                inventory.get(FUEL_SLOT),
                inventory.get(BOTTLE_SLOT),
                this.pos
        );
        assert world != null;
        Optional<RecipeEntry<BasicCondenserRecipe>> match = world.getRecipeManager()
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

        consumeFluidAndFinishCrafting();
    }
}
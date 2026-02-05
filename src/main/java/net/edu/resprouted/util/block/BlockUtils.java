package net.edu.resprouted.util.block;

import net.edu.resprouted.block.interfaces.LuminousFluidStorage;
import net.edu.resprouted.util.fluid.FluidItemInteractionHelper;
import net.edu.resprouted.util.fluid.FluidUtils;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.RegistryOps;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;

import java.util.List;
import java.util.function.Function;

public class BlockUtils {
    private static final RegistryOps<NbtElement> TOOLTIP_OPS = RegistryOps.of(NbtOps.INSTANCE, BuiltinRegistries.createWrapperLookup());
    private static final float FILL_WITH_RAIN_CHANCE = 0.05F;
    private static final float FILL_WITH_SNOW_CHANCE = 0.1F;

    /**
     * Add fluid tooltip to the ItemStack
     */

    public static void appendFluidTooltip(ItemStack stack, List<Text> tooltip) {
        var data = stack.get(DataComponentTypes.BLOCK_ENTITY_DATA);
        if (data == null) return;

        NbtCompound fluidNbt = data.copyNbt().getCompound("Fluid");
        if (fluidNbt.isEmpty() || !fluidNbt.contains("variant") || !fluidNbt.contains("amount")) {
            return;
        }

        try {
            FluidVariant fluidVariant = FluidVariant.CODEC.parse(TOOLTIP_OPS, fluidNbt.get("variant")).result().orElse(FluidVariant.blank());

            if (fluidVariant.isBlank()) return;

            Text fluidName = FluidVariantAttributes.getName(fluidVariant);
            long amount = FluidUtils.convertDropletsToMb(fluidNbt.getLong("amount"));

            tooltip.add(Text.translatable("tooltip.resprouted.fluid", fluidName).formatted(Formatting.GOLD));
            tooltip.add(Text.translatable("tooltip.resprouted.amount", amount).formatted(Formatting.GOLD));

        } catch (Exception e) {
            tooltip.add(Text.translatable("tooltip.resprouted.fluid_error").formatted(Formatting.RED));
        }
    }

    /**
     * Dripstone event copied and modified from CauldronBlock
     */

    public static void fillFromDripstone(BlockState state, World world, BlockPos pos, Fluid fluid, SingleFluidStorage storage, LuminousFluidStorage luminousBlock) {
        if (fluid != Fluids.WATER) return;

        if (storage.getResource().isOf(Fluids.WATER) || storage.isResourceBlank()) {
            long capacity = storage.getCapacity();
            long currentAmount = storage.getAmount();
            long spaceAvailable = capacity - currentAmount;

            if (spaceAvailable > 0) {
                long amountToAdd = Math.min(20250, spaceAvailable);

                try (Transaction tx = Transaction.openOuter()) {
                    storage.insert(FluidVariant.of(Fluids.WATER), amountToAdd, tx);
                    tx.commit();
                    luminousBlock.updateBlockWithLight(world.getBlockEntity(pos), world, pos, state);
                    world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(state));
                    world.syncWorldEvent(WorldEvents.POINTED_DRIPSTONE_DRIPS_WATER_INTO_CAULDRON, pos, 0);
                }
            }
        }
    }

    public static void handlePrecipitation(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation, SingleFluidStorage storage, LuminousFluidStorage luminousBlock) {
        if (!world.isClient && canFillWithPrecipitation(world, precipitation)) {
            if (storage.getResource().isOf(Fluids.WATER) || storage.isResourceBlank()) {
                long capacity = storage.getCapacity();
                long currentAmount = storage.getAmount();
                long available = capacity - currentAmount;

                if (available > 0) {
                    long amountToAdd = Math.min(20250, available);

                    try (Transaction tx = Transaction.openOuter()) {
                        storage.insert(FluidVariant.of(Fluids.WATER), amountToAdd, tx);
                        tx.commit();
                        luminousBlock.updateBlockWithLight(world.getBlockEntity(pos), world, pos, state);
                    }
                }
            }
        }
    }

    public static boolean canFillWithPrecipitation(World world, Biome.Precipitation precipitation) {
        if (precipitation == Biome.Precipitation.RAIN) {
            return world.getRandom().nextFloat() < FILL_WITH_RAIN_CHANCE;
        } else {
            return precipitation == Biome.Precipitation.SNOW && world.getRandom().nextFloat() < FILL_WITH_SNOW_CHANCE;
        }
    }

    public static boolean canBeFilledByDripstone(Fluid fluid) {
        return fluid == Fluids.WATER;
    }

    /**
     * Checks if the fluid can be stored in a liquid container.
     */

    public static boolean canStoreFluid(FluidVariant fluid) {
        if (fluid.isBlank()) {
            return true;
        }

        if (FluidVariantAttributes.isLighterThanAir(fluid)) {
            return false;
        }

        int temperature = FluidVariantAttributes.getTemperature(fluid);
        return temperature <= 400;
    }

    /**
     * Manage fluid interactions
     */

    public static ItemActionResult handleFluidInteraction(World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, LuminousFluidStorage luminousBlock, BlockState state) {
        Storage<FluidVariant> fluidStorage = FluidStorage.SIDED.find(world, pos, hit.getSide());
        if (fluidStorage == null) {
            return ItemActionResult.FAIL;
        }

        ItemStack stack = player.getStackInHand(hand);

        Storage<FluidVariant> handStorage = ContainerItemContext.ofPlayerHand(player, hand).find(FluidStorage.ITEM);
        if (handStorage != null) {
            for (StorageView<FluidVariant> view : handStorage) {
                FluidVariant fluid = view.getResource();
                if (!fluid.isBlank() && !canStoreFluid(fluid)) {
                    if (!world.isClient) {
                        if (FluidVariantAttributes.isLighterThanAir(fluid)) {
                            player.sendMessage(
                                    Text.translatable("message.resprouted.cannot_store_gaseous").formatted(Formatting.RESET), true);
                        } else {
                            player.sendMessage(
                                    Text.translatable("message.resprouted.cannot_store_too_hot").formatted(Formatting.RESET), true);
                        }
                    }
                    return ItemActionResult.CONSUME;
                }
            }
        }

        ItemActionResult result = FluidItemInteractionHelper.onFluidStorageUse(player, stack, fluidStorage, world, pos, true, true);

        if (result == ItemActionResult.SUCCESS) {
            luminousBlock.updateBlockWithLight(world.getBlockEntity(pos), world, pos, state);
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.CONSUME;
    }

    /**
     * Attempts to dye a block with a dye item
     */

    public static ItemActionResult tryToDye(World world, BlockPos pos, BlockState state, PlayerEntity player, ItemStack stack, Function<DyeColor, Block> blockGetter) {
        if (!(stack.getItem() instanceof DyeItem dyeItem)) {
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity == null) {
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        DyeColor newColor = dyeItem.getColor();
        Block currentBlock = state.getBlock();
        Block newBlock = blockGetter.apply(newColor);

        if (currentBlock == newBlock) {
            if (!world.isClient) {
                player.sendMessage(Text.translatable("message.resprouted.already_dyed").formatted(Formatting.RESET), true);
            }
            return ItemActionResult.CONSUME;
        }

        if (world.isClient) {
            return ItemActionResult.SUCCESS;
        }

        NbtCompound nbt = blockEntity.createNbt(world.getRegistryManager());
        ComponentMap components = blockEntity.createComponentMap();
        BlockState newState = newBlock.getDefaultState();

        for (Property<?> property : state.getProperties()) {
            if (newState.contains(property)) {
                newState = copyProperty(state, newState, property);
            }
        }

        world.setBlockState(pos, newState, Block.NOTIFY_ALL | Block.SKIP_DROPS);
        BlockEntity newBlockEntity = world.getBlockEntity(pos);

        if (newBlockEntity != null) {
            newBlockEntity.read(nbt, world.getRegistryManager());
            newBlockEntity.setComponents(components);
            newBlockEntity.markDirty();
        }

        if (!player.isCreative()) {
            stack.decrement(1);
        }
        world.playSound(null, pos, SoundEvents.ITEM_DYE_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);

        return ItemActionResult.SUCCESS;
    }

    private static <T extends Comparable<T>> BlockState copyProperty(BlockState from, BlockState to, Property<T> property) {
        return to.with(property, from.get(property));
    }
}

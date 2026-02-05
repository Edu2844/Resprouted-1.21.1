package net.edu.resprouted.util.fluid;

import net.edu.resprouted.fluid.ModFluids;
import net.edu.resprouted.fluid.data.FluidItemMapping;
import net.edu.resprouted.resource.FluidItemLoader;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.Set;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Helper class for handling fluid interactions between fluid items and storage blocks.
 * Manages the insertion and extraction of fluids from storage, including component preservation,
 * sound effects, and proper item handling.
 */

public class FluidItemInteractionHelper {
    private static final Map<Identifier, ComponentType<?>> COMPONENT_TYPE_CACHE = new ConcurrentHashMap<>();

    public static ItemActionResult onFluidStorageUse(PlayerEntity player, ItemStack stack, Storage<FluidVariant> storage, World world, BlockPos pos, boolean allowInsert, boolean allowExtract) {
        if (player.getWorld().isClient) {
            return ItemActionResult.FAIL;
        }

        if (allowInsert && tryInsertFluid(player, stack, storage, world, pos)) {
            return ItemActionResult.SUCCESS;
        }

        if (allowExtract && tryExtractFluid(player, stack, storage, world, pos)) {
            return ItemActionResult.SUCCESS;
        }

        return ItemActionResult.FAIL;
    }

    private static boolean tryInsertFluid(PlayerEntity player, ItemStack stack, Storage<FluidVariant> storage, World world, BlockPos pos) {
        try (Transaction tx = Transaction.openOuter()) {
            for (FluidItemMapping mapping : FluidItemLoader.getEntries()) {
                if (!mapping.direction().allowsInsert()) {
                    continue;
                }

                if (!ItemStack.areItemsEqual(stack, mapping.fullItem())) {
                    continue;
                }

                if (mapping.fluid().isOf(ModFluids.POTION) || mapping.fluid().isOf(ModFluids.POTION_FLOWING)) {
                    PotionContentsComponent potionContents = stack.get(DataComponentTypes.POTION_CONTENTS);

                    if (potionContents != null && potionContents.potion().isPresent() && potionContents.potion().get() == Potions.WATER) {
                        FluidVariant waterVariant = FluidVariant.of(Fluids.WATER);
                        long inserted = storage.insert(waterVariant, mapping.amount(), tx);

                        if (inserted == mapping.amount()) {
                            tx.commit();
                            handleInsertSuccess(player, stack, mapping, world, pos);
                            return true;
                        }
                        continue;
                    }
                }

                if (!canInsertWithComponents(storage, mapping.fluid(), stack, mapping.preservedComponents())) {
                    continue;
                }

                FluidVariant fluidWithComponents = createFluidWithComponents(mapping.fluid(), stack, mapping.preservedComponents());

                long inserted = storage.insert(fluidWithComponents, mapping.amount(), tx);

                if (inserted == mapping.amount()) {
                    tx.commit();
                    handleInsertSuccess(player, stack, mapping, world, pos);
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean tryExtractFluid(PlayerEntity player, ItemStack stack, Storage<FluidVariant> storage, World world, BlockPos pos) {
        try (Transaction tx = Transaction.openOuter()) {
            for (FluidItemMapping mapping : FluidItemLoader.getEntries()) {
                if (!mapping.direction().allowsExtract()) {
                    continue;
                }

                if (!stack.isOf(mapping.emptyItem().getItem())) {
                    continue;
                }

                if ((mapping.fluid().isOf(ModFluids.POTION) || mapping.fluid().isOf(ModFluids.POTION_FLOWING)) && stack.isOf(Items.GLASS_BOTTLE)) {
                    FluidVariant waterVariant = FluidVariant.of(Fluids.WATER);
                    long extracted = storage.extract(waterVariant, mapping.amount(), tx);

                    if (extracted == mapping.amount()) {
                        tx.commit();

                        ItemStack waterBottle = new ItemStack(Items.POTION);
                        waterBottle.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(Potions.WATER));

                        ItemStack resultStack = ItemUsage.exchangeStack(stack, player, waterBottle);
                        updatePlayerHand(player, stack, resultStack);
                        playSound(world, pos, mapping, false);
                        return true;
                    }
                }

                FluidVariant fluidToExtract = findFluidWithComponents(storage, mapping.fluid());
                FluidVariant extractVariant = fluidToExtract != null ? fluidToExtract : mapping.fluid();

                long extracted = storage.extract(extractVariant, mapping.amount(), tx);

                if (extracted == mapping.amount()) {
                    tx.commit();
                    handleExtractSuccess(player, stack, mapping, fluidToExtract, world, pos);
                    return true;
                }
            }
        }
        return false;
    }

    private static void handleInsertSuccess(PlayerEntity player, ItemStack stack, FluidItemMapping mapping, World world, BlockPos pos) {
        if (!player.isCreative()) {
            stack.decrement(1);
            ItemStack empty = mapping.emptyItem().copy();

            if (!player.getInventory().insertStack(empty)) {
                player.dropItem(empty, false);
            }
        }

        playSound(world, pos, mapping, true);
    }

    private static void handleExtractSuccess(PlayerEntity player, ItemStack stack, FluidItemMapping mapping, FluidVariant fluidToExtract, World world, BlockPos pos) {
        ItemStack filled = mapping.fullItem().copy();

        if (fluidToExtract != null) {
            applyComponentsFromFluid(fluidToExtract, filled, mapping.preservedComponents());
        }

        ItemStack resultStack = ItemUsage.exchangeStack(stack, player, filled);
        updatePlayerHand(player, stack, resultStack);

        playSound(world, pos, mapping, false);
    }

    private static void updatePlayerHand(PlayerEntity player, ItemStack oldStack, ItemStack newStack) {
        if (player.getMainHandStack() == oldStack) {
            player.setStackInHand(Hand.MAIN_HAND, newStack);
        } else if (player.getOffHandStack() == oldStack) {
            player.setStackInHand(Hand.OFF_HAND, newStack);
        }
    }

    private static void applyComponentsFromFluid(FluidVariant fluid, ItemStack item, Set<Identifier> preservedComponents) {
        for (Identifier componentId : preservedComponents) {
            ComponentType<?> componentType = getComponentType(componentId);
            if (componentType == null) continue;

            Object fluidValue = unwrapOptional(fluid.getComponents().get(componentType));
            if (fluidValue != null) {
                setComponentToItem(item, componentType, fluidValue);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> void setComponentToItem(ItemStack item, ComponentType<T> componentType, Object value) {
        item.set(componentType, (T) value);
    }

    private static boolean canInsertWithComponents(Storage<FluidVariant> storage, FluidVariant fluidToInsert, ItemStack itemStack, Set<Identifier> preservedComponents) {
        if (preservedComponents.isEmpty()) return true;

        for (StorageView<FluidVariant> view : storage) {
            FluidVariant existingFluid = view.getResource();

            if (!existingFluid.isOf(fluidToInsert.getFluid()) || view.getAmount() == 0) {
                continue;
            }

            if (!componentsMatch(existingFluid, itemStack, preservedComponents)) {
                return false;
            }
        }

        return true;
    }

    private static boolean componentsMatch(FluidVariant fluid, ItemStack item, Set<Identifier> preservedComponents) {
        for (Identifier componentId : preservedComponents) {
            ComponentType<?> componentType = getComponentType(componentId);
            if (componentType == null) continue;

            Object itemValue = item.get(componentType);
            Object fluidValue = unwrapOptional(fluid.getComponents().get(componentType));

            if (itemValue != null && fluidValue != null && !itemValue.equals(fluidValue)) {
                return false;
            }
        }
        return true;
    }

    private static FluidVariant createFluidWithComponents(FluidVariant baseVariant, ItemStack itemStack, Set<Identifier> preservedComponents) {
        if (preservedComponents.isEmpty()) return baseVariant;

        ComponentChanges.Builder builder = ComponentChanges.builder();

        for (Identifier componentId : preservedComponents) {
            ComponentType<?> componentType = getComponentType(componentId);
            if (componentType == null) continue;

            Object value = itemStack.get(componentType);
            if (value != null) {
                addComponentToBuilder(builder, componentType, value);
            }
        }

        return baseVariant.withComponentChanges(builder.build());
    }

    private static FluidVariant findFluidWithComponents(Storage<FluidVariant> storage, FluidVariant fluidType) {
        for (StorageView<FluidVariant> view : storage) {
            FluidVariant resource = view.getResource();

            if (resource.isOf(fluidType.getFluid()) && view.getAmount() > 0) {
                return resource;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static <T> void addComponentToBuilder(ComponentChanges.Builder builder, ComponentType<T> componentType, Object value) {
        builder.add(componentType, (T) value);
    }

    private static ComponentType<?> getComponentType(Identifier componentId) {
        return COMPONENT_TYPE_CACHE.computeIfAbsent(componentId, Registries.DATA_COMPONENT_TYPE::get);
    }

    private static Object unwrapOptional(Object value) {
        return value instanceof Optional<?> opt ? opt.orElse(null) : value;
    }

    private static void playSound(World world, BlockPos pos, FluidItemMapping mapping, boolean isInsert) {
        SoundEvent sound = getSoundEvent(mapping, isInsert);
        world.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    private static SoundEvent getSoundEvent(FluidItemMapping mapping, boolean isInsert) {
        if (mapping.customSound().isPresent()) {
            return Registries.SOUND_EVENT.get(mapping.customSound().get());
        }

        if (isInsert) {
            return FluidVariantAttributes.getEmptySound(mapping.fluid());
        } else {
            return FluidVariantAttributes.getFillSound(mapping.fluid());
        }
    }
}
package net.edu.resprouted.block.custom.decorative;

import com.mojang.serialization.MapCodec;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.entity.custom.LiquidBarrelBlockEntity;
import net.edu.resprouted.block.interfaces.LuminousFluidStorage;
import net.edu.resprouted.fluid.data.FluidItemInteractionHelper;
import net.edu.resprouted.util.FluidUtils;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class LiquidBarrelBlock extends BlockWithEntity implements LuminousFluidStorage {
    private static final RegistryOps<NbtElement> TOOLTIP = RegistryOps.of(NbtOps.INSTANCE, BuiltinRegistries.createWrapperLookup());
    private static final float FILL_WITH_RAIN_CHANCE = 0.05F;
    private static final float FILL_WITH_SNOW_CHANCE = 0.1F;
    private static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 1.0, 12.0),
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 16.0, 4.0),
            Block.createCuboidShape(2.0, 0.0, 12.0, 14.0, 16.0, 14.0),
            Block.createCuboidShape(12.0, 0.0, 4.0, 14.0, 16.0, 12.0),
            Block.createCuboidShape(2.0, 0.0, 4.0, 4.0, 16.0, 12.0)
    );
    private static final VoxelShape COLLISION = VoxelShapes.union(
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 1.0, 14.0),
            Block.createCuboidShape(2.0, 1.0, 2.0, 14.0, 16.0, 2.5),
            Block.createCuboidShape(2.0, 1.0, 13.5, 14.0, 16.0, 14.0),
            Block.createCuboidShape(13.5, 1.0, 2.5, 14.0, 16.0, 13.5),
            Block.createCuboidShape(2.0, 1.0, 2.5, 2.5, 16.0, 13.5)
    );
    public static final MapCodec<LiquidBarrelBlock> CODEC = LiquidBarrelBlock.createCodec(LiquidBarrelBlock::new);

    public LiquidBarrelBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(LIGHT_LEVEL, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIGHT_LEVEL);
    }

    @Override
    @Nullable
    public BlockEntity getBlockEntity(World world, BlockPos pos) {
        return world.getBlockEntity(pos);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new LiquidBarrelBlockEntity(pos, state);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        var data = stack.get(DataComponentTypes.BLOCK_ENTITY_DATA);
        if (data == null) return;

        var nbt = data.copyNbt().getCompound("Fluid");
        if (!nbt.contains("variant") || !nbt.contains("amount")) return;

        try {
            FluidVariant fluidVariant = FluidVariant.CODEC
                    .parse(TOOLTIP, nbt.get("variant"))
                    .result()
                    .orElse(FluidVariant.blank());

            if (fluidVariant.isBlank()) return;

            Text name = FluidVariantAttributes.getName(fluidVariant);
            long amount = FluidUtils.convertDropletsToMb(nbt.getLong("amount"));

            tooltip.add(Text.translatable("tooltip.resprouted.fluid", name).formatted(Formatting.GOLD));
            tooltip.add(Text.translatable("tooltip.resprouted.amount", amount).formatted(Formatting.GOLD));

        } catch (Exception e) {
            tooltip.add(Text.translatable("tooltip.resprouted.fluid_error").formatted(Formatting.RED));
        }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.isOf(newState.getBlock())) {
            super.onStateReplaced(state, world, pos, newState, moved);
            return;
        }

        if (world.isClient || !(world instanceof ServerWorld serverWorld)) {
            super.onStateReplaced(state, world, pos, newState, moved);
            return;
        }

        PlayerEntity player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5.0, false);
        boolean isCreative = player != null && player.isCreative();

        if (!isCreative && world.getBlockEntity(pos) instanceof LiquidBarrelBlockEntity barrel) {
            ItemStack barrelItem = createLiquidBarrelItem(serverWorld, barrel);
            ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), barrelItem);
        }

        super.onStateReplaced(state, world, pos, newState, moved);
    }

    private ItemStack createLiquidBarrelItem(ServerWorld world, LiquidBarrelBlockEntity barrel) {
        FluidVariant fluid = barrel.getFluidStorage().getResource();
        long amount = barrel.getFluidStorage().getAmount();

        Item barrelItem = Registries.ITEM.get(Registries.BLOCK.getId(this));
        ItemStack stack = new ItemStack(barrelItem);

        if (fluid.isBlank() || amount <= 0) {
            return stack;
        }

        NbtCompound blockEntityNbt = createFluidNbt(world, fluid, amount);
        stack.set(DataComponentTypes.BLOCK_ENTITY_DATA, NbtComponent.of(blockEntityNbt));

        return stack;
    }

    private NbtCompound createFluidNbt(ServerWorld world, FluidVariant fluid, long amount) {
        RegistryWrapper.WrapperLookup registryLookup = world.getRegistryManager();
        RegistryOps<NbtElement> ops = RegistryOps.of(NbtOps.INSTANCE, registryLookup);

        NbtCompound fluidNbt = new NbtCompound();
        FluidVariant.CODEC.encodeStart(ops, fluid)
                .resultOrPartial(error -> System.err.println("Error encoding FluidVariant: " + error))
                .ifPresent(encoded -> fluidNbt.put("variant", encoded));

        fluidNbt.putLong("amount", amount);

        NbtCompound blockEntityNbt = new NbtCompound();
        blockEntityNbt.put("Fluid", fluidNbt);
        blockEntityNbt.putString("id", Objects.requireNonNull(Registries.BLOCK_ENTITY_TYPE.getId(ModBlockEntities.LIQUID_BARREL_BE)).toString());

        return blockEntityNbt;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world.isClient || !entity.isOnFire()) return;

        if (world.getBlockEntity(pos) instanceof LiquidBarrelBlockEntity barrel) {
            FluidVariant fluid = barrel.getFluidStorage().getResource();
            long amount = barrel.getFluidStorage().getAmount();

            if (!fluid.isBlank() && amount >= FluidConstants.BOTTLE) {
                if (isEntityTouchingFluid(pos, entity, barrel)) {
                    if (entity instanceof LivingEntity livingEntity) {
                        livingEntity.extinguish();
                        world.syncWorldEvent(1009, pos, 0);
                    }
                }
            }
        }
    }

    private boolean isEntityTouchingFluid(BlockPos pos, Entity entity, LiquidBarrelBlockEntity barrel) {
        Box entityBox = entity.getBoundingBox();
        double fluidHeight = pos.getY() + getFluidHeight(barrel);

        if (entity.getY() >= fluidHeight || entityBox.maxY <= pos.getY() + 0.0625) {
            return false;
        }
        double interiorMin = 0.25;
        double interiorMax = 0.75;

        return entityBox.maxX > pos.getX() + interiorMin && entityBox.minX < pos.getX() + interiorMax
                && entityBox.maxZ > pos.getZ() + interiorMin && entityBox.minZ < pos.getZ() + interiorMax;
    }

    private double getFluidHeight(LiquidBarrelBlockEntity barrel) {
        long amount = barrel.getFluidStorage().getAmount();

        if (amount <= 0) return 0.0625;

        long capacity = barrel.getFluidStorage().getCapacity();
        double fillPercentage = (double) amount / capacity;

        return 0.0625 + (0.8125 * fillPercentage);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockPos dripPos = PointedDripstoneBlock.getDripPos(world, pos);
        if (dripPos != null) {
            Fluid fluid = PointedDripstoneBlock.getDripFluid(world, dripPos);
            if (fluid != Fluids.EMPTY && canBeFilledByDripstone(fluid)) {
                fillFromDripstone(state, world, pos, fluid);
            }
        }
    }

    protected boolean canBeFilledByDripstone(Fluid fluid) {
        return fluid == Fluids.WATER;
    }

    protected void fillFromDripstone(BlockState state, World world, BlockPos pos, Fluid fluid) {
        if (fluid != Fluids.WATER) return;

        if (world.getBlockEntity(pos) instanceof LiquidBarrelBlockEntity barrel) {
            SingleFluidStorage storage = barrel.getFluidStorage();

            // Only fill if barrel is empty or already contains water
            if (storage.getResource().isOf(Fluids.WATER) || storage.isResourceBlank()) {
                long capacity = storage.getCapacity();
                long currentAmount = storage.getAmount();
                long spaceAvailable = capacity - currentAmount;

                if (spaceAvailable > 0) {
                    long amountToAdd = Math.min(FluidConstants.BOTTLE, spaceAvailable);

                    try (Transaction tx = Transaction.openOuter()) {
                        storage.insert(FluidVariant.of(Fluids.WATER), amountToAdd, tx);
                        tx.commit();
                        updateBlockWithLight(barrel, world, pos, state);
                        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(state));
                        world.syncWorldEvent(WorldEvents.POINTED_DRIPSTONE_DRIPS_WATER_INTO_CAULDRON, pos, 0);
                    }
                }
            }
        }
    }

    @Override
    public void precipitationTick(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation) {
        if (!world.isClient && canFillWithPrecipitation(world, precipitation)) {
            BlockEntity be = world.getBlockEntity(pos);

            if (be instanceof LiquidBarrelBlockEntity barrel) {
                SingleFluidStorage storage = barrel.getFluidStorage();

                if (storage.getResource().isOf(Fluids.WATER) || storage.isResourceBlank()) {
                    long capacity = storage.getCapacity();
                    long currentAmount = storage.getAmount();
                    long spaceAvailable = capacity - currentAmount;

                    if (spaceAvailable > 0) {
                        long amountToAdd = Math.min(FluidConstants.BOTTLE, spaceAvailable);

                        try (Transaction tx = Transaction.openOuter()) {
                            storage.insert(FluidVariant.of(Fluids.WATER), amountToAdd, tx);
                            tx.commit();
                            updateBlockWithLight(barrel, world, pos, state);
                        }
                    }
                }
            }
        }
    }

    protected static boolean canFillWithPrecipitation(World world, Biome.Precipitation precipitation) {
        if (precipitation == Biome.Precipitation.RAIN) {
            return world.getRandom().nextFloat() < FILL_WITH_RAIN_CHANCE;
        } else {
            return precipitation == Biome.Precipitation.SNOW && world.getRandom().nextFloat() < FILL_WITH_SNOW_CHANCE;
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof LiquidBarrelBlockEntity barrel)) {
            return ItemActionResult.FAIL;
        }

        Storage<FluidVariant> fluidStorage = FluidStorage.SIDED.find(world, pos, hit.getSide());
        if (fluidStorage == null) {
            return ItemActionResult.FAIL;
        }

        // Validate fluid before insertion
        Storage<FluidVariant> handStorage = ContainerItemContext.ofPlayerHand(player, hand).find(FluidStorage.ITEM);
        if (handStorage != null) {
            for (StorageView<FluidVariant> view : handStorage) {
                FluidVariant fluid = view.getResource();
                if (!fluid.isBlank() && !canStoreFluid(fluid)) {
                    if (!world.isClient) {

                        if (FluidVariantAttributes.isLighterThanAir(fluid)) {
                            player.sendMessage(
                                    Text.translatable("message.resprouted.cannot_store_gaseous")
                                            .formatted(Formatting.RED), true
                            );
                        } else {
                            player.sendMessage(
                                    Text.translatable("message.resprouted.cannot_store_too_hot")
                                            .formatted(Formatting.RED), true
                            );
                        }
                    }
                    return ItemActionResult.FAIL;
                }
            }
        }

        ItemActionResult result = FluidItemInteractionHelper.onFluidStorageUse(
                player, stack, fluidStorage, world, pos, true, true
        );

        if (result == ItemActionResult.SUCCESS) {
            updateBlockWithLight(barrel, world, pos, state);
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.CONSUME;
    }

    /**
     * Checks if the fluid can be stored in a liquid barrel.
     * Returns false if the fluid is:
     * - Lighter than air (gaseous)
     * - Too hot (above 400K / ~127°C)
     *
     * @param fluid the fluid variant to check
     * @return true if the fluid can be stored safely
     */
    private boolean canStoreFluid(FluidVariant fluid) {
        if (fluid.isBlank()) {
            return true;
        }

        //Don't store gaseous fluids (lighter than air)
        if (FluidVariantAttributes.isLighterThanAir(fluid)) {
            return false;
        }

        //Don't store fluids that are too hot
        //400K = ~127°C
        int temperature = FluidVariantAttributes.getTemperature(fluid);
        return temperature <= 400;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? validateTicker(type, ModBlockEntities.LIQUID_BARREL_BE, LiquidBarrelBlockEntity::tick) : null;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return COLLISION;
    }
}
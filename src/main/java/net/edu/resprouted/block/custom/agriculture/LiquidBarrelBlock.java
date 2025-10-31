package net.edu.resprouted.block.custom.agriculture;

import com.mojang.serialization.MapCodec;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.entity.custom.LiquidBarrelBE;
import net.edu.resprouted.fluid.util.FluidInteractionHelper;
import net.edu.resprouted.util.FluidUtils;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class LiquidBarrelBlock extends BlockWithEntity {
    private static final RegistryOps<NbtElement> TOOLTIP_OPS = RegistryOps.of(NbtOps.INSTANCE, BuiltinRegistries.createWrapperLookup());
    private static final float FILL_WITH_RAIN_CHANCE = 0.05F;
    private static final float FILL_WITH_SNOW_CHANCE = 0.1F;
    private static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 2.0),
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 1.0, 14.0),
            Block.createCuboidShape(0.0, 0.0, 14.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(14.0, 0.0, 2.0, 16.0, 16.0, 14.0),
            Block.createCuboidShape(0.0, 0.0, 2.0, 2.0, 16.0, 14.0)
    );
    public static final MapCodec<LiquidBarrelBlock> CODEC = LiquidBarrelBlock.createCodec(LiquidBarrelBlock::new);

    public LiquidBarrelBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
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
        return new LiquidBarrelBE(pos, state);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        var i = stack.get(DataComponentTypes.BLOCK_ENTITY_DATA);

        if (i == null) return;

        var j = i.copyNbt().getCompound("Fluid");

        if (!j.contains("variant") || !j.contains("amount")) return;
        try {
            var k = FluidVariant.CODEC.parse(TOOLTIP_OPS, j.get("variant")).result().orElse(FluidVariant.blank());

            if (!k.isBlank()) {
                var name = FluidVariantAttributes.getName(k);
                long amount = FluidUtils.convertDropletsToMb(j.getLong("amount"));

                tooltip.add(Text.translatable("tooltip.resprouted.fluid", name).formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("tooltip.resprouted.amount", amount).formatted(Formatting.GRAY));
            }
        }
        catch (Exception e) {
            tooltip.add(Text.translatable("tooltip.resprouted.fluid_error").formatted(Formatting.RED));
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity be = world.getBlockEntity(pos);

        if (!(be instanceof LiquidBarrelBE b)) {
            return ItemActionResult.FAIL;
        }

        var l = FluidStorage.SIDED.find(world, pos, hit.getSide());
        if (l == null) {
            return ItemActionResult.FAIL;
        }

        ItemActionResult result = FluidInteractionHelper.handleFluidUse(player, stack, l, world, pos, true, true);

        if (result == ItemActionResult.SUCCESS) {
            b.markDirty();
            world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);

            return ItemActionResult.SUCCESS;
        }

        return ItemActionResult.CONSUME;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            if (!world.isClient && world instanceof ServerWorld serverWorld) {
                PlayerEntity player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5.0, false);

                boolean isCreative = player != null && player.isCreative();

                if (!isCreative) {
                    if (world.getBlockEntity(pos) instanceof LiquidBarrelBE b) {

                        FluidVariant m = b.getFluidStorage().getResource();
                        long amount = b.getFluidStorage().getAmount();

                        Item o = Registries.ITEM.get(Registries.BLOCK.getId(this));
                        ItemStack stack = new ItemStack(o);

                        if (!FluidVariant.blank().equals(m) && amount > 0) {
                            RegistryWrapper.WrapperLookup registryLookup = serverWorld.getRegistryManager();
                            RegistryOps<NbtElement> ops = RegistryOps.of(NbtOps.INSTANCE, registryLookup);

                            NbtCompound fluidNbt = new NbtCompound();
                            FluidVariant.CODEC.encodeStart(ops, m)
                                    .resultOrPartial(error -> System.err.println("Error al codificar FluidVariant: " + error))
                                    .ifPresent(encoded -> fluidNbt.put("variant", encoded));

                            fluidNbt.putLong("amount", amount);

                            NbtCompound beTag = new NbtCompound();
                            beTag.put("Fluid", fluidNbt);
                            beTag.putString("id", Objects.requireNonNull(Registries.BLOCK_ENTITY_TYPE.getId(ModBlockEntities.LIQUID_BARREL_BE)).toString());

                            stack.set(DataComponentTypes.BLOCK_ENTITY_DATA, NbtComponent.of(beTag));
                        }

                        ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                    }
                }
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world.isClient) return;

        double p = pos.getY() + 0.4;

        if (entity.getY() <= p) {
            if (world.getBlockEntity(pos) instanceof LiquidBarrelBE barrel) {
                FluidVariant fluid = barrel.getFluidStorage().getResource();
                long amount = barrel.getFluidStorage().getAmount();

                if (!fluid.isBlank() && amount >= FluidConstants.BOTTLE) {
                    if (entity instanceof LivingEntity livingEntity && livingEntity.isOnFire()) {
                        livingEntity.extinguish();
                        world.syncWorldEvent(1009, pos, 0);
                    }
                    if (entity.getVelocity().y < -0.1) {

                        world.playSound(
                                null,
                                pos,
                                SoundEvents.ENTITY_GENERIC_SPLASH,
                                SoundCategory.BLOCKS,
                                0.1F,
                                1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.4F
                        );
                    }
                }
            }
        }
    }

    @Override
    public void precipitationTick(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation) {
        if (!world.isClient && canFillWithPrecipitation(world, precipitation)) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof LiquidBarrelBE barrel) {
                SingleFluidStorage storage = barrel.getFluidStorage();

                if (storage.getResource().isOf(Fluids.WATER) || storage.isResourceBlank()) {
                    long q = storage.getCapacity();
                    long r = storage.getAmount();
                    long s = q - r;

                    if (s > 0) {

                        long amountToAdd = Math.min(FluidConstants.BOTTLE, s);

                        try (Transaction t = Transaction.openOuter()) {
                            if (storage.isResourceBlank()) {

                                storage.insert(FluidVariant.of(Fluids.WATER), amountToAdd, t);
                            } else if (storage.getResource().isOf(Fluids.WATER)) {

                                storage.insert(FluidVariant.of(Fluids.WATER), amountToAdd, t);
                            }

                            t.commit();
                            barrel.markDirty();
                            world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
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
}
package net.edu.resprouted.block.custom.agriculture;

import com.mojang.serialization.MapCodec;
import net.edu.resprouted.block.ModBlockEntities;
import net.edu.resprouted.block.entity.custom.LiquidBarrelBE;
import net.edu.resprouted.fluid.util.FluidInteractionHelper;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import java.util.Objects;

public class LiquidBarrelBlock extends BlockWithEntity {
    private static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 2.0F),
            Block.createCuboidShape(2.0F, 0.0F, 2.0F, 14.0F, 1.0F, 14.0F),
            Block.createCuboidShape(0.0F, 0.0F, 14.0F, 16.0F, 16.0F, 16.0F),
            Block.createCuboidShape(14.0F, 0.0F, 2.0F, 16.0F, 16.0F, 14.0F),
            Block.createCuboidShape(0.0F, 0.0F, 2.0F, 2.0F, 16.0F, 14.0F)
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
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!(blockEntity instanceof LiquidBarrelBE barrel)) {
            return ItemActionResult.FAIL;
        }
        var storage = FluidStorage.SIDED.find(world, pos, hit.getSide());
        if (storage == null) {
            return ItemActionResult.FAIL;
        }
        ItemActionResult result = FluidInteractionHelper.handleFluidUse(player, stack, storage, world, pos, true, true);

        if (result == ItemActionResult.SUCCESS) {
            barrel.markDirty();
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
                //Avoid drop on creative
                boolean isCreative = player != null && player.isCreative();

                if (!isCreative) {
                    BlockEntity be = world.getBlockEntity(pos);
                    if (be instanceof LiquidBarrelBE barrel) {

                        //Get fluid
                        FluidVariant variant = barrel.getFluidStorage().getResource();
                        long amount = barrel.getFluidStorage().getAmount();

                        Item blockItem = Registries.ITEM.get(Registries.BLOCK.getId(this));
                        ItemStack stack = new ItemStack(blockItem);

                        if (!FluidVariant.blank().equals(variant) && amount > 0) {
                            RegistryWrapper.WrapperLookup registryLookup = serverWorld.getRegistryManager();
                            RegistryOps<NbtElement> ops = RegistryOps.of(NbtOps.INSTANCE, registryLookup);

                            NbtCompound fluidNbt = new NbtCompound();
                            FluidVariant.CODEC.encodeStart(ops, variant)
                                    .resultOrPartial(error -> System.err.println("Error al codificar FluidVariant: " + error))
                                    .ifPresent(encoded -> fluidNbt.put("variant", encoded));

                            fluidNbt.putLong("amount", amount);

                            NbtCompound blockEntityTag = new NbtCompound();
                            blockEntityTag.put("Fluid", fluidNbt);
                            blockEntityTag.putString("id", Objects.requireNonNull(Registries.BLOCK_ENTITY_TYPE.getId(ModBlockEntities.LIQUID_BARREL_BE)).toString());

                            stack.set(DataComponentTypes.BLOCK_ENTITY_DATA, NbtComponent.of(blockEntityTag));
                        }
                        //Drop ítem with NBT fluid
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
        if (!(entity instanceof LivingEntity livingEntity)) return;
        if (!livingEntity.isOnFire()) return;

        double fluidSurfaceY = pos.getY() + 0.4;
        if (entity.getY() <= fluidSurfaceY) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof LiquidBarrelBE barrel) {
                FluidVariant fluid = barrel.getFluidStorage().getResource();
                long amount = barrel.getFluidStorage().getAmount();

                if (!fluid.isBlank() && amount >= FluidConstants.BOTTLE) {
                    livingEntity.extinguish();world.syncWorldEvent(1009, pos, 0);
                }
            }
        }
    }
}
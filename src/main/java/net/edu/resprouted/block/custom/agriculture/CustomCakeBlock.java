package net.edu.resprouted.block.custom.agriculture;

import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

@SuppressWarnings("deprecation")
public class CustomCakeBlock extends Block {
    public static final int MAX_BITES = 6;
    public static final IntProperty BITES = IntProperty.of("bites", 0, 6);
    private static final VoxelShape[] BITES_TO_SHAPE = new VoxelShape[] {
            Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(3.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(5.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(7.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(9.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(11.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(13.0, 0.0, 1.0, 15.0, 8.0, 15.0)
    };
    private final RegistryEntry<StatusEffect> statusEffect;
    private final int effectDuration;
    private final int effectAmplifier;

    public CustomCakeBlock(Settings settings) {
        this(settings, null, 0, 0);
    }
    public CustomCakeBlock(Settings settings, RegistryEntry<StatusEffect> statusEffect, int effectDuration, int effectAmplifier) {
        super(settings);
        this.statusEffect = statusEffect;
        this.effectDuration = effectDuration;
        this.effectAmplifier = effectAmplifier;
        setDefaultState(this.stateManager.getDefaultState().with(BITES, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BITES);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int bites = state.get(BITES);
        return BITES_TO_SHAPE[bites];
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            if (tryEat(world, pos, state, player).isAccepted()) {
                return ActionResult.SUCCESS;
            }
            if (player.getStackInHand(Hand.MAIN_HAND).isEmpty()) {
                return ActionResult.CONSUME;
            }
        }

        return tryEat(world, pos, state, player);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isSolid();
    }

    @Override
    protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return getComparatorOutput(state.get(BITES));
    }

    public static int getComparatorOutput(int bites) {
        return (MAX_BITES + 1 - bites) * 2;
    }

    @Override
    protected boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    protected ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        }

        player.incrementStat(Stats.EAT_CAKE_SLICE);
        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.BLOCKS, 1.0F, 1.0F);
        player.getHungerManager().add(2, 0.1F);

        if (statusEffect != null && !world.isClient()) {
            player.addStatusEffect(new StatusEffectInstance(
                    statusEffect,
                    effectDuration,
                    effectAmplifier
            ));
        }
        if (world instanceof World serverWorld) {
            BlockState h = world.getBlockState(pos);

            for (int i = 0; i < 8; i++) {
                double offsetX = (serverWorld.random.nextDouble() - 0.5) * 0.5;
                double offsetY = serverWorld.random.nextDouble() * 0.5;
                double offsetZ = (serverWorld.random.nextDouble() - 0.5) * 0.5;

                serverWorld.addParticle(
                        new BlockStateParticleEffect(ParticleTypes.BLOCK, h),
                        pos.getX() + 0.5 + offsetX,
                        pos.getY() + 0.5 + offsetY,
                        pos.getZ() + 0.5 + offsetZ,
                        offsetX * 0.1,
                        offsetY * 0.1 + 0.05,
                        offsetZ * 0.1
                );
            }
        }

        int i = state.get(BITES);
        world.emitGameEvent(player, GameEvent.EAT, pos);

        if (i < MAX_BITES) {
            world.setBlockState(pos, state.with(BITES, i + 1), 3);
        } else {
            world.removeBlock(pos, false);
            world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        }

        return ActionResult.SUCCESS;
    }
}

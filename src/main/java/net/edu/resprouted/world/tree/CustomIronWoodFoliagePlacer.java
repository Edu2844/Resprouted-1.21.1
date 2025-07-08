package net.edu.resprouted.world.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.edu.resprouted.world.ModFoliagePlacerTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class CustomIronWoodFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<CustomIronWoodFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance -> fillFoliagePlacerFields(instance).apply(instance, CustomIronWoodFoliagePlacer::new)
    );
    public CustomIronWoodFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }
    @Override
    protected FoliagePlacerType<?> getType() {
        return ModFoliagePlacerTypes.CUSTOM_IRONWOOD_PLACER;
    }
    @Override
    public void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius) {
        BlockPos trunkBase = treeNode.getCenter().down(trunkHeight - 1); // Y=1 (base del tronco)
        generateCircularLayer(world, placer, random, config, trunkBase.up(7), 1);  // Y=8 (3x3)
        generateCircularLayer(world, placer, random, config, trunkBase.up(8), 3);  // Y=9 (7x7)
        generateCircularLayer(world, placer, random, config, trunkBase.up(9), 2);  // Y=10 (5x5)
        generateSphericalLayer(world, placer, random, config, trunkBase.up(10), 2); // Y=11 (5x5 esférico)
        generateSphericalLayer(world, placer, random, config, trunkBase.up(11), 1); // Y=12 (3x3)
        generateSphericalLayer(world, placer, random, config, trunkBase.up(12), 2); // Y=13 (3x3 esférico)
        generateSphericalLayer(world, placer, random, config, trunkBase.up(13), 1); // Y=14 (3x3 esférico)
        placer.placeBlock(trunkBase.up(14), config.foliageProvider.get(random, trunkBase.up(14)));// Capa final (Y=15) - 1x1
        // Hojas laterales
        addSimpleLeaves(world, placer, random, config, trunkBase.up(2)); // Y=5
        addSimpleLeaves(world, placer, random, config, trunkBase.up(3)); // Y=6
        addSimpleLeaves(world, placer, random, config, trunkBase.up(4)); // Y=7
    }
    private void addSimpleLeaves(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, BlockPos trunkPos) {
        for (int i = 0; i < 2 + random.nextInt(3); i++) {
            Direction direction = Direction.Type.HORIZONTAL.random(random);BlockPos leafPos = trunkPos.offset(direction);
            if (isAirOrLeaves(world, leafPos)) {placer.placeBlock(leafPos, config.foliageProvider.get(random, leafPos));
            }
        }
    }
    private boolean isAirOrLeaves(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, state -> state.isAir() || state.isIn(BlockTags.LEAVES));
    }
    private void generateCircularLayer(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, BlockPos center, int radius) {
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x * x + z * z <= radius * radius + radius) {BlockPos pos = center.add(x, 0, z);
                    if (!(x == 0 && z == 0)) {placer.placeBlock(pos, config.foliageProvider.get(random, pos));
                    }
                }
            }
        }
    }
    private void generateSphericalLayer(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, BlockPos center, int radius) {
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x * x + z * z <= radius * radius) {BlockPos pos = center.add(x, 0, z);
                    if (!(x == 0 && z == 0)) {placer.placeBlock(pos, config.foliageProvider.get(random, pos));
                    }
                }
            }
        }
    }
    @Override
    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
    }
    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return 0;
    }
    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }
}

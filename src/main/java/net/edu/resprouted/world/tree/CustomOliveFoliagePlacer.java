package net.edu.resprouted.world.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.edu.resprouted.world.ModFoliagePlacerTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class CustomOliveFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<CustomOliveFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> fillFoliagePlacerFields(instance).apply(instance, CustomOliveFoliagePlacer::new)
    );
    public CustomOliveFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }
    @Override
    protected FoliagePlacerType<?> getType() {
        return ModFoliagePlacerTypes.CUSTOM_OLIVE_PLACER;
    }
    @Override
    public void generate(TestableWorld world, BlockPlacer placer, Random random,
                         TreeFeatureConfig config, int trunkHeight,
                         TreeNode treeNode, int foliageHeight, int radius) {
        BlockPos topPos = treeNode.getCenter();
        BlockPos baseLeavesPos = topPos.down(3); // Donde comienzan las Hojas

        // Capa 1 (Y=3): 3x3
        generateSquareLayer(world, placer, random, config, baseLeavesPos, 1, true);
        // Capa 2 (Y=4): 5x5
        generateSquareLayer(world, placer, random, config, baseLeavesPos.up(1), 2, true);
        // Capas 3 y 4 (Y=5-6): 3x3 c
        generateSquareLayer(world, placer, random, config, baseLeavesPos.up(2), 1, true);
        generateSquareLayer(world, placer, random, config, baseLeavesPos.up(3), 1, false);
        // Punta (Y=7-8): 1x1
        placer.placeBlock(baseLeavesPos.up(4), config.foliageProvider.get(random, baseLeavesPos.up(4)));
        placer.placeBlock(baseLeavesPos.up(5), config.foliageProvider.get(random, baseLeavesPos.up(5)));
    }
    private void generateSquareLayer(TestableWorld world, BlockPlacer placer,
                                     Random random, TreeFeatureConfig config,
                                     BlockPos center, int radius, boolean keepTrunkCenter) {
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                BlockPos pos = center.add(x, 0, z);
                if (!(keepTrunkCenter && x == 0 && z == 0)) {
                    if (Math.abs(x) + Math.abs(z) <= radius * 1.5) {
                        placer.placeBlock(pos, config.foliageProvider.get(random, pos));
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
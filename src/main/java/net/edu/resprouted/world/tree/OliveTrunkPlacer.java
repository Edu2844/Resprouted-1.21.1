package net.edu.resprouted.world.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.edu.resprouted.world.ModTrunkPlacerTypes;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class OliveTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<OliveTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            fillTrunkPlacerFields(instance).apply(instance, OliveTrunkPlacer::new));

    public OliveTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }
    @Override
    protected TrunkPlacerType<?> getType() {
        return ModTrunkPlacerTypes.CUSTOM_OLIVE_TRUNK_PLACER;
    }
    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                                                 Random random, int height, BlockPos startPos, TreeFeatureConfig config) {

        setToDirt(world, replacer, random, startPos.down(), config);
        List<FoliagePlacer.TreeNode> foliageNodes = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            getAndSetState(world, replacer, random, startPos.up(i), config);
        }
        BlockPos northBranchEnd = generateHorizontalBranch(replacer, random, startPos.up(2), Direction.NORTH, 1, config);
        foliageNodes.add(new FoliagePlacer.TreeNode(northBranchEnd, 1, false));

        BlockPos southBranchEnd = generateHorizontalBranch(replacer, random, startPos.up(2), Direction.SOUTH, 1, config);
        foliageNodes.add(new FoliagePlacer.TreeNode(southBranchEnd, 1, false));

        BlockPos eastBranchEnd = generateHorizontalBranch(replacer, random, startPos.up(3), Direction.EAST, 2, config);
        foliageNodes.add(new FoliagePlacer.TreeNode(eastBranchEnd, 1, false));

        BlockPos westBranchEnd = generateHorizontalBranch(replacer, random, startPos.up(3), Direction.WEST, 2, config);
        foliageNodes.add(new FoliagePlacer.TreeNode(westBranchEnd, 1, false));

        foliageNodes.add(new FoliagePlacer.TreeNode(startPos.up(4), 2, false));

        return foliageNodes;
    }
    private BlockPos generateHorizontalBranch(BiConsumer<BlockPos, BlockState> replacer,
                                              Random random, BlockPos start, Direction direction, int length,
                                              TreeFeatureConfig config) {

        BlockPos currentPos = start;
        for (int i = 1; i <= length; i++) {
            currentPos = start.offset(direction, i);
            BlockState branchState = config.trunkProvider.get(random, currentPos)
                    .with(Properties.AXIS, direction.getAxis());
            replacer.accept(currentPos, branchState);
        }
        return currentPos;
    }
}

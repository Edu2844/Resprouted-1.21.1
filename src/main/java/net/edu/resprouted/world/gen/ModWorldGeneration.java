package net.edu.resprouted.world.gen;

public class ModWorldGeneration {
    public static void generateModWorldGen() {
        ModHerbsGeneration.generateHerbs();
        ModTreeGeneration.generateTrees();
        ModOreGeneration.generateOres();

    }
}
package net.edu.resprouted.book;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.block.ModBlocks;
import net.edu.resprouted.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class CatalogData {
    public static final List<Category> CATEGORIES = new ArrayList<>();

    public static void init() {
        Category agriculture = new Category("agriculture");
        Category decoration = new Category("decoration");
        Category production = new Category("production");

        CATEGORIES.add(agriculture);
        CATEGORIES.add(decoration);
        CATEGORIES.add(production);

        // =================================================
        // ||                 AGRICULTURE                 ||
        // =================================================
        agriculture.addEntry(new Entry(Text.translatable("block.resprouted.fertile_soil"), new ItemStack(ModBlocks.FERTILE_SOIL))
                .addPage(new TextPage(Text.translatable("text.resprouted.fertile_soil"))));

        agriculture.addEntry(new Entry(Text.translatable("entry.resprouted.crop_stakes"), new ItemStack(ModBlocks.STAKE))
                .addPage(new TextPage(Text.translatable("text.resprouted.crop_stakes.1"))));

        agriculture.addEntry(new Entry(Text.translatable("entry.resprouted.tomatoes"), new ItemStack(ModItems.TOMATO))
                .addPage(new TextPage(Text.translatable("text.resprouted.tomatoes.1"), Text.translatable("text.resprouted.tomatoes.2"))));

        agriculture.addEntry(new Entry(Text.translatable("entry.resprouted.chili_peppers"), new ItemStack(ModItems.CHILI_PEPPER))
                .addPage(new TextPage(Text.translatable("text.resprouted.chili_peppers.1"), Text.translatable("text.resprouted.chili_peppers.2")))
                .addPage(new TextPage(Text.translatable("text.resprouted.chili_peppers.3"))));

        agriculture.addEntry(new Entry(Text.translatable("item.resprouted.grapes"), new ItemStack(ModItems.GRAPES))
                .addPage(new TextPage(Text.translatable("text.resprouted.grapes.1"), Text.translatable("text.resprouted.grapes.2"))));

        agriculture.addEntry(new Entry(Text.translatable("entry.resprouted.apple_tree"), new ItemStack(Items.APPLE))
                .addPage(new TextPage(Text.translatable("text.resprouted.apple_tree.1"), Text.translatable("text.resprouted.apple_tree.2")))
                .addPage(new TextPage(Text.translatable("text.resprouted.apple_tree.3"))));

        agriculture.addEntry(new Entry(Text.translatable("entry.resprouted.olives"), new ItemStack(ModItems.OLIVES))
                .addPage(new TextPage(Text.translatable("text.resprouted.olives.desc1"), Text.translatable("text.resprouted.olives.desc2"))));

        agriculture.addEntry(new Entry(Text.translatable("entry.resprouted.ironwood_trees"), new ItemStack(ModItems.IRON_BERRIES))
                .addPage(new TextPage(Text.translatable("text.resprouted.ironwood_trees.desc1"), Text.translatable("text.resprouted.ironwood_trees.desc2"))));

        agriculture.addEntry(new Entry(Text.translatable("entry.resprouted.herbs"), new ItemStack(ModItems.CHAMOMILE))
                .addPage(new TextPage(Text.translatable("text.resprouted.herbs.desc1"), Text.translatable("text.resprouted.herbs.desc2")))
                .addPage(new TextPage(Text.translatable("text.resprouted.herbs.desc3"), Text.translatable("text.resprouted.herbs.desc4")))
                .addPage(new TextPage(Text.translatable("text.resprouted.herbs.desc5"))));

        // =================================================
        // ||                 DECORATION                  ||
        // =================================================
        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.liquid_barrel"), new ItemStack(ModBlocks.LIQUID_BARREL))
                .addPage(new TextPage(Text.translatable("text.resprouted.liquid_barrel.desc"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.jars"), new ItemStack(ModBlocks.RED_JAR))
                .addPage(new TextPage(Text.translatable("text.resprouted.jars.desc"), Text.translatable("text.resprouted.jars.desc2"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.urns"), new ItemStack(ModBlocks.RED_URN))
                .addPage(new TextPage(Text.translatable("text.resprouted.urns.desc"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.cabinets"), new ItemStack(ModBlocks.OAK_CABINET))
                .addPage(new TextPage(Text.translatable("text.resprouted.cabinets.desc1"), Text.translatable("text.resprouted.cabinets.desc2"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.rope"), new ItemStack(ModBlocks.ROPE))
                .addPage(new TextPage(Text.translatable("text.resprouted.rope.desc1"), Text.translatable("text.resprouted.rope.desc2"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.chains"), new ItemStack(ModBlocks.GOLDEN_CHAIN))
                .addPage(new TextPage(Text.translatable("text.resprouted.chains.desc"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.candle_holder"), new ItemStack(ModBlocks.IRON_CANDLE_HOLDER))
                .addPage(new TextPage(Text.translatable("text.resprouted.candle_holder.desc1"), Text.translatable("text.resprouted.candle_holder.desc2"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.chandeliers"), new ItemStack(ModBlocks.CHANDELIER))
                .addPage(new TextPage(Text.translatable("text.resprouted.chandeliers.desc1"), Text.translatable("text.resprouted.chandeliers.desc2"))));

        if (Resprouted.COMMON_CONFIG.getGeneral().isEnableTables() && ModBlocks.OAK_TABLE != null) {
            decoration.addEntry(new Entry(Text.translatable("entry.resprouted.table"), new ItemStack(ModBlocks.OAK_TABLE))
                    .addPage(new TextPage(Text.translatable("text.resprouted.table.desc"))));
        }

        if (Resprouted.COMMON_CONFIG.getGeneral().isEnableChairs() && ModBlocks.OAK_CHAIR != null) {
            decoration.addEntry(new Entry(Text.translatable("entry.resprouted.chair"), new ItemStack(ModBlocks.OAK_CHAIR))
                    .addPage(new TextPage(Text.translatable("text.resprouted.chair.desc"))));
        }

        if (Resprouted.COMMON_CONFIG.getGeneral().isEnableStools() && ModBlocks.OAK_STOOL != null) {
            decoration.addEntry(new Entry(Text.translatable("entry.resprouted.stool"), new ItemStack(ModBlocks.OAK_STOOL))
                    .addPage(new TextPage(Text.translatable("text.resprouted.stool.desc"))));
        }

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.gargoyle"), new ItemStack(ModBlocks.GARGOYLE))
                .addPage(new TextPage(Text.translatable("text.resprouted.gargoyle.desc"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.slate"), new ItemStack(ModBlocks.SLATE))
                .addPage(new TextPage(Text.translatable("text.resprouted.slate.desc"))));

        if (Resprouted.COMMON_CONFIG.getGeneral().isEnableVanillaBlockVariations() && ModBlocks.ANDESITE_PILLAR != null) {
            decoration.addEntry(new Entry(Text.translatable("entry.resprouted.variations"), new ItemStack(ModBlocks.ANDESITE_PILLAR))
                    .addPage(new TextPage(Text.translatable("text.resprouted.variations.desc"))));
        }

        if (Resprouted.COMMON_CONFIG.getGeneral().isEnableClayWalls() && ModBlocks.CLAY_WALL != null) {
            decoration.addEntry(new Entry(Text.translatable("entry.resprouted.clay_walls"), new ItemStack(ModBlocks.CLAY_WALL))
                    .addPage(new TextPage(Text.translatable("text.resprouted.clay_walls.desc"))));
        }

        if (Resprouted.COMMON_CONFIG.getGeneral().isEnablePaintedPlanks() && ModBlocks.ORANGE_PAINTED_PLANKS != null) {
            decoration.addEntry(new Entry(Text.translatable("entry.resprouted.painted_planks"), new ItemStack(ModBlocks.ORANGE_PAINTED_PLANKS))
                    .addPage(new TextPage(Text.translatable("text.resprouted.painted_planks.desc"))));
        }

        // =================================================
        // ||                 PRODUCTION                  ||
        // =================================================
        production.addEntry(new Entry(Text.translatable("entry.resprouted.crushing"), new ItemStack(ModBlocks.CRUSHING_TUB))
                .addPage(new TextPage(Text.translatable("text.resprouted.crushing.desc1"), Text.translatable("text.resprouted.crushing.desc2"))));

        production.addEntry(new Entry(Text.translatable("entry.resprouted.drying"), new ItemStack(ModBlocks.DRYING_BASIN))
                .addPage(new TextPage(Text.translatable("text.resprouted.drying.desc1"), Text.translatable("text.resprouted.drying.desc2")))
                .addPage(new TextPage(Text.translatable("text.resprouted.drying.desc3"))));

        production.addEntry(new Entry(Text.translatable("entry.resprouted.condenser"), new ItemStack(ModBlocks.BASIC_CONDENSER))
                .addPage(new TextPage(Text.translatable("text.resprouted.condenser.desc1"), Text.translatable("text.resprouted.condenser.desc2")))
                .addPage(new TextPage(Text.translatable("text.resprouted.condenser.desc3"))));

        production.addEntry(new Entry(Text.translatable("entry.resprouted.advanced_condenser"), new ItemStack(ModBlocks.ADVANCED_CONDENSER))
                .addPage(new TextPage(Text.translatable("text.resprouted.advanced_condenser.desc1"), Text.translatable("text.resprouted.advanced_condenser.desc2")))
                .addPage(new TextPage(Text.translatable("text.resprouted.advanced_condenser.desc3"))));

        production.addEntry(new Entry(Text.translatable("entry.resprouted.elixirs"), new ItemStack(ModItems.ELIXIR_ICON))
                .addPage(new TextPage(Text.translatable("text.resprouted.elixirs.desc1"))));

        production.addEntry(new Entry(Text.translatable("entry.resprouted.vanta_oil"), new ItemStack(ModItems.VANTA_OIL_BOTTLE))
                .addPage(new TextPage(Text.translatable("text.resprouted.vanta_oil.desc1"), Text.translatable("text.resprouted.vanta_oil.desc2"))));

        production.addEntry(new Entry(Text.translatable("entry.resprouted.alcohol"), new ItemStack(ModItems.WINE_BOTTLE))
                .addPage(new TextPage(Text.translatable("text.resprouted.alcohol.desc1"), Text.translatable("text.resprouted.alcohol.desc2")))
                .addPage(new TextPage(Text.translatable("text.resprouted.alcohol.desc3"), Text.translatable("text.resprouted.alcohol.desc4")))
                .addPage(new TextPage(Text.translatable("text.resprouted.alcohol.desc5"), Text.translatable("text.resprouted.alcohol.desc6")))
                .addPage(new TextPage(Text.translatable("text.resprouted.alcohol.desc7"), Text.translatable("text.resprouted.alcohol.desc8")))
                .addPage(new TextPage(Text.translatable("text.resprouted.alcohol.desc9"), Text.translatable("text.resprouted.alcohol.desc10"))));

        production.addEntry(new Entry(Text.translatable("entry.resprouted.brewing"), new ItemStack(ModBlocks.BREWING_BARREL))
                .addPage(new TextPage(Text.translatable("text.resprouted.brewing.desc1"), Text.translatable("text.resprouted.brewing.desc2")))
                .addPage(new TextPage(Text.translatable("text.resprouted.brewing.desc3"), Text.translatable("text.resprouted.brewing.desc4"))));
    }

    public static class TextPage {
        private final Text leftText;
        private final Text rightText;

        public TextPage(Text leftText) {
            this(leftText, null);
        }

        public TextPage(Text leftText, Text rightText) {
            this.leftText = leftText;
            this.rightText = rightText;
        }

        public Text getLeftText() {
            return leftText;
        }

        public Text getRightText() {
            return rightText;
        }
    }

    public static class Entry {
        private final Text name;
        private final ItemStack icon;
        private final List<TextPage> pages = new ArrayList<>();

        public Entry(Text name, ItemStack icon) {
            this.name = name;
            this.icon = icon;
        }

        public Text getName() {
            return name;
        }

        public ItemStack getIcon() {
            return icon;
        }

        public List<TextPage> getPages() {
            return pages;
        }

        public Entry addPage(TextPage page) {
            pages.add(page);
            return this;
        }
    }

    public static class Category {
        private final String name;
        private final List<Entry> entries = new ArrayList<>();

        public Category(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public List<Entry> getEntries() {
            return entries;
        }

        public void addEntry(Entry entry) {
            entries.add(entry);
        }
    }
}
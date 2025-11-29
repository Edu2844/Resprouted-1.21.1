package net.edu.resprouted.book;

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

        //Categoríes
        Category agriculture = new Category("agriculture");
        Category decoration = new Category("decoration");
        Category production = new Category("production");

        CATEGORIES.add(agriculture);
        CATEGORIES.add(decoration);
        CATEGORIES.add(production);


        // =================================================
        // ||                 AGRICULTURE                 ||
        // =================================================
        agriculture.addEntry(new Entry(
                Text.translatable("block.resprouted.fertile_soil"), new ItemStack(ModBlocks.FERTILE_SOIL),
                Entry.Side.LEFT)
                .addPage(new TextPage(Text.translatable("text.resprouted.fertile_soil"))));

        agriculture.addEntry(new Entry(
                Text.translatable("entry.resprouted.crop_stakes"), new ItemStack(ModBlocks.STAKE), Entry.Side.LEFT)
                .addPage(new TextPage(Text.translatable("text.resprouted.crop_stakes.1"))));

        agriculture.addEntry(new Entry(
                Text.translatable("entry.resprouted.tomatoes"), new ItemStack(ModItems.TOMATO), Entry.Side.LEFT)
                .addPage(new TextPage(Text.translatable("text.resprouted.tomatoes.1"),Text.translatable("text.resprouted.tomatoes.2")))
        );

        agriculture.addEntry(new Entry(
                Text.translatable("entry.resprouted.chili_peppers"), new ItemStack(ModItems.CHILI_PEPPER),
                Entry.Side.LEFT)
                .addPage(new TextPage(Text.translatable("text.resprouted.chili_peppers.1"),Text.translatable("text.resprouted.chili_peppers.2")))
                .addPage(new TextPage(Text.translatable("text.resprouted.chili_peppers.3"))));

        agriculture.addEntry(new Entry(Text.translatable("item.resprouted.grapes"), new ItemStack(ModItems.GRAPES),
                Entry.Side.LEFT)
                .addPage(new TextPage(Text.translatable("text.resprouted.grapes.1"),Text.translatable("text.resprouted.grapes.2"))));

        agriculture.addEntry(new Entry(Text.translatable("entry.resprouted.apple_tree"), new ItemStack(Items.APPLE),
                Entry.Side.LEFT)
                .addPage(new TextPage(Text.translatable("text.resprouted.apple_tree.1"),Text.translatable("text.resprouted.apple_tree.2")))
                .addPage(new TextPage(Text.translatable("text.resprouted.apple_tree.3"))));

        agriculture.addEntry(new Entry(Text.translatable("entry.resprouted.olives"),
                new ItemStack(ModItems.OLIVES), Entry.Side.RIGHT)
                .addPage(new TextPage(Text.translatable("text.resprouted.olives.desc1"),Text.translatable("text.resprouted.olives.desc2"))));

        agriculture.addEntry(new Entry(Text.translatable("entry.resprouted.ironwood_trees"), new ItemStack(ModItems.IRON_BERRIES),
                Entry.Side.LEFT)
                .addPage(new TextPage(Text.translatable("text.resprouted.ironwood_trees.desc1"),Text.translatable("text.resprouted.ironwood_trees.desc2"))));

        agriculture.addEntry(new Entry(Text.translatable("entry.resprouted.herbs"),
                new ItemStack(ModItems.CHAMOMILE), Entry.Side.RIGHT)
                .addPage(new TextPage(Text.translatable("text.resprouted.herbs.desc1"),Text.translatable("text.resprouted.herbs.desc2")))
                .addPage(new TextPage(Text.translatable("text.resprouted.herbs.desc3"),Text.translatable("text.resprouted.herbs.desc4")))
                .addPage(new TextPage(Text.translatable("text.resprouted.herbs.desc5")))
        );


        // =================================================
        // ||                 DECORATION                  ||
        // =================================================

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.liquid_barrel"), new ItemStack(ModBlocks.LIQUID_BARREL))
                .addPage(new TextPage(Text.translatable("text.resprouted.liquid_barrel.desc"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.cabinets"), new ItemStack(ModBlocks.OAK_CABINET_BLOCK))
                .addPage(new TextPage(Text.translatable("text.resprouted.cabinets.desc1"),Text.translatable("text.resprouted.cabinets.desc2"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.rope"), new ItemStack(ModBlocks.ROPE))
                .addPage(new TextPage(Text.translatable("text.resprouted.rope.desc1"),Text.translatable("text.resprouted.rope.desc2"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.chains"), new ItemStack(ModBlocks.GOLDEN_CHAIN))
                .addPage(new TextPage(Text.translatable("text.resprouted.chains.desc"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.candle_holder"), new ItemStack(ModBlocks.IRON_CANDLE_HOLDER))
                .addPage(new TextPage(Text.translatable("text.resprouted.candle_holder.desc1"),Text.translatable("text.resprouted.candle_holder.desc2"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.chandeliers"), new ItemStack(ModBlocks.CHANDELIER))
                .addPage(new TextPage(Text.translatable("text.resprouted.chandeliers.desc1"),Text.translatable("text.resprouted.chandeliers.desc2"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.table"), new ItemStack(ModBlocks.OAK_TABLE))
                .addPage(new TextPage(Text.translatable("text.resprouted.table.desc"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.chair"), new ItemStack(ModBlocks.OAK_CHAIR), Entry.Side.RIGHT)
                .addPage(new TextPage(Text.translatable("text.resprouted.chair.desc"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.stool"), new ItemStack(ModBlocks.OAK_STOOL), Entry.Side.RIGHT)
                .addPage(new TextPage(Text.translatable("text.resprouted.stool.desc"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.gargoyle"), new ItemStack(ModBlocks.GARGOYLE), Entry.Side.RIGHT)
                .addPage(new TextPage(Text.translatable("text.resprouted.gargoyle.desc"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.slate"), new ItemStack(ModBlocks.SLATE), Entry.Side.RIGHT)
                .addPage(new TextPage(Text.translatable("text.resprouted.slate.desc"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.pillar"), new ItemStack(ModBlocks.ANDESITE_PILLAR), Entry.Side.RIGHT)
                .addPage(new TextPage(Text.translatable("text.resprouted.pillar.desc"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.clay_walls"), new ItemStack(ModBlocks.CLAY_WALL), Entry.Side.RIGHT)
                .addPage(new TextPage(Text.translatable("text.resprouted.clay_walls.desc"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.painted_planks"), new ItemStack(ModBlocks.ORANGE_PAINTED_PLANKS), Entry.Side.RIGHT)
                .addPage(new TextPage(Text.translatable("text.resprouted.painted_planks.desc"))));


        // =================================================
        // ||                 PRODUCTION                  ||
        // =================================================
        production.addEntry(new Entry(Text.translatable("entry.resprouted.crushing"), new ItemStack(ModBlocks.CRUSHING_TUB))
                .addPage(new TextPage(Text.translatable("text.resprouted.crushing.desc1"),Text.translatable("text.resprouted.crushing.desc2")))
        );

        production.addEntry(new Entry(Text.translatable("entry.resprouted.drying"), new ItemStack(ModBlocks.EVAPORATING_BASIN))
                .addPage(new TextPage(Text.translatable("text.resprouted.drying.desc1"),Text.translatable("text.resprouted.drying.desc2")))
                .addPage(new TextPage(Text.translatable("text.resprouted.drying.desc3")))
        );

        production.addEntry(new Entry(Text.translatable("entry.resprouted.condenser"), new ItemStack(ModBlocks.CONDENSER))
                .addPage(new TextPage(Text.translatable("text.resprouted.condenser.desc1"),Text.translatable("text.resprouted.condenser.desc2")))
                .addPage(new TextPage(Text.translatable("text.resprouted.condenser.desc3")))
        );

        production.addEntry(new Entry(Text.translatable("entry.resprouted.advanced_condenser"), new ItemStack(ModBlocks.ADVANCED_CONDENSER))
                .addPage(new TextPage(Text.translatable("text.resprouted.advanced_condenser.desc1"),Text.translatable("text.resprouted.advanced_condenser.desc2")))
                .addPage(new TextPage(Text.translatable("text.resprouted.advanced_condenser.desc3")))
        );

        production.addEntry(new Entry(Text.translatable("entry.resprouted.elixirs"), new ItemStack(ModItems.ELIXIR_ICON))
                .addPage(new TextPage(Text.translatable("text.resprouted.elixirs.desc1"))))
        ;

        production.addEntry(new Entry(Text.translatable("entry.resprouted.vanta_oil"), new ItemStack(ModItems.VANTA_OIL_BOTTLE))
                .addPage(new TextPage(Text.translatable("text.resprouted.vanta_oil.desc1"),Text.translatable("text.resprouted.vanta_oil.desc2")))
        );

        production.addEntry(new Entry(Text.translatable("entry.resprouted.alcohol"), new ItemStack(ModItems.WINE_BOTTLE))
                .addPage(new TextPage(Text.translatable("text.resprouted.alcohol.desc1"),Text.translatable("text.resprouted.alcohol.desc2")))
                .addPage(new TextPage(Text.translatable("text.resprouted.alcohol.desc3"),Text.translatable("text.resprouted.alcohol.desc4")))
                .addPage(new TextPage(Text.translatable("text.resprouted.alcohol.desc5"),Text.translatable("text.resprouted.alcohol.desc6")))
                .addPage(new TextPage(Text.translatable("text.resprouted.alcohol.desc7"),Text.translatable("text.resprouted.alcohol.desc8")))
                .addPage(new TextPage(Text.translatable("text.resprouted.alcohol.desc9"),Text.translatable("text.resprouted.alcohol.desc10")))
        );

        production.addEntry(new Entry(Text.translatable("entry.resprouted.brewing"), new ItemStack(ModBlocks.BREWING_BARREL), Entry.Side.RIGHT)
                .addPage(new TextPage(Text.translatable("text.resprouted.brewing.desc1"),Text.translatable("text.resprouted.brewing.desc2")))
                .addPage(new TextPage(Text.translatable("text.resprouted.brewing.desc3"),Text.translatable("text.resprouted.brewing.desc4")))
        );
    }

    // =================================================
    // ||               STATIC CLASSES                ||
    // =================================================
    public static abstract class Page {

    }

    public static class TextPage extends Page {
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
        private final List<Page> pages = new ArrayList<>();
        private final Side side;

        public Entry(Text name, ItemStack icon) {
            this(name, icon, Side.LEFT);
        }

        public Entry(Text name, ItemStack icon, Side side) {
            this.name = name;
            this.icon = icon;
            this.side = side;
        }

        public Text getName() {
            return name;
        }

        public ItemStack getIcon() {
            return icon;
        }

        public List<Page> getPages() {
            return pages;
        }

        public Side getSide() {
            return side;
        }

        public Entry addPage(Page page) {
            pages.add(page);
            return this;
        }

        public enum Side {
            LEFT, RIGHT
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
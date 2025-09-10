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
        // Categorías
        Category agriculture = new Category("agriculture");
        Category decoration = new Category("decoration");
        Category production = new Category("production");

        CATEGORIES.add(agriculture);
        CATEGORIES.add(decoration);
        CATEGORIES.add(production);


        // Agricultura
        agriculture.addEntry(new Entry(
                Text.translatable("block.resprouted.fertile_soil"), new ItemStack(ModBlocks.FERTILE_SOIL),
                Entry.Side.LEFT)
                .addPage(new TextPage(Text.translatable("text.resprouted.fertile_soil"))));

        agriculture.addEntry(new Entry(
                Text.translatable("entry.resprouted.stake_crops"), new ItemStack(ModBlocks.STAKE), Entry.Side.LEFT)
                .addPage(new TextPage(Text.translatable("text.resprouted.stake_crops.1"))));

        agriculture.addEntry(new Entry(
                Text.translatable("entry.resprouted.tomatoes"), new ItemStack(ModItems.TOMATO), Entry.Side.LEFT)
                .addPage(new TextPage(Text.translatable("text.resprouted.tomatoes.1"),Text.translatable("text.resprouted.tomatoes.2")))
        );

        agriculture.addEntry(new Entry(
                Text.translatable("entry.resprouted.chili_peppers"), new ItemStack(ModItems.CHILI_PEPPER),
                Entry.Side.LEFT)
                .addPage(new TextPage(Text.translatable("text.resprouted.chili_peppers.1"),Text.translatable("text.resprouted.chili_peppers.2"))));

        agriculture.addEntry(new Entry(Text.translatable("item.resprouted.grapes"), new ItemStack(ModItems.GRAPES),
                Entry.Side.LEFT)
                .addPage(new TextPage(Text.translatable("text.resprouted.grapes.1"),Text.translatable("text.resprouted.grapes.2"))));

        agriculture.addEntry(new Entry(Text.translatable("entry.resprouted.apple_tree"), new ItemStack(Items.APPLE),
                Entry.Side.LEFT)
                .addPage(new TextPage(Text.translatable("text.resprouted.apple_tree.1"),Text.translatable("text.resprouted.apple_tree.2"))));

        agriculture.addEntry(new Entry(Text.translatable("entry.resprouted.olives"),
                new ItemStack(ModItems.OLIVES), Entry.Side.RIGHT)
                .addPage(new TextPage(Text.translatable("page.resprouted.olives.desc"))));

        agriculture.addEntry(new Entry(Text.translatable("entry.resprouted.ironwood_trees"), new ItemStack(ModItems.IRON_BERRIES),
                Entry.Side.LEFT)
                .addPage(new TextPage(Text.translatable("page.resprouted.ironwood_trees.desc1"),Text.translatable("page.resprouted.ironwood_trees.desc2"))));

        agriculture.addEntry(new Entry(Text.translatable("entry.resprouted.herbs"),
                new ItemStack(ModItems.ALOE_VERA), Entry.Side.RIGHT)
                .addPage(new TextPage(Text.translatable("page.resprouted.herbs.desc"))));


        // Decoración
        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.cabinets"), new ItemStack(ModBlocks.OAK_CABINET_BLOCK))
                .addPage(new TextPage(Text.translatable("page.resprouted.cabinets.desc"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.rope"), new ItemStack(ModBlocks.ROPE))
                .addPage(new TextPage(Text.translatable("page.resprouted.rope.desc"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.golden_chain"), new ItemStack(ModBlocks.GOLDEN_CHAIN))
                .addPage(new TextPage(Text.translatable("page.resprouted.golden_chain.desc"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.table"), new ItemStack(ModBlocks.OAK_TABLE))
                .addPage(new TextPage(Text.translatable("page.resprouted.table.desc"))));

        decoration.addEntry(new Entry(Text.translatable("entry.resprouted.chair"), new ItemStack(ModBlocks.OAK_CHAIR))
                .addPage(new TextPage(Text.translatable("page.resprouted.chair.desc"))));


        // Producción
        production.addEntry(new Entry(
                Text.translatable("entry.resprouted.condenser"),
                new ItemStack(ModBlocks.CONDENSER)
        ).addPage(new TextPage(Text.translatable("page.resprouted.condenser.desc"))));

        production.addEntry(new Entry(
                Text.translatable("entry.resprouted.elixirs"),
                new ItemStack(ModItems.ELIXIR_ICON)
        ).addPage(new TextPage(Text.translatable("page.resprouted.elixirs.desc"))));

        production.addEntry(new Entry(
                Text.translatable("entry.resprouted.vanta_oil"),
                new ItemStack(ModItems.VANTA_OIL_BOTTLE)
        ).addPage(new TextPage(Text.translatable("page.resprouted.vanta_oil.desc"))));

        production.addEntry(new Entry(
                Text.translatable("entry.resprouted.brewing"),
                new ItemStack(ModBlocks.BREWING_BARREL)
        ).addPage(new TextPage(Text.translatable("page.resprouted.brewing.desc"))));

        production.addEntry(new Entry(
                Text.translatable("entry.resprouted.crushing"),
                new ItemStack(ModBlocks.CRUSHING_TUB)
        ).addPage(new TextPage(Text.translatable("page.resprouted.crushing.desc"))));

        production.addEntry(new Entry(
                Text.translatable("entry.resprouted.drying"),
                new ItemStack(ModBlocks.EVAPORATING_BASIN)
        ).addPage(new TextPage(Text.translatable("page.resprouted.drying.desc"))));
    }

    public static class Category {
        private final String name;
        private final List<Entry> entries = new ArrayList<>();

        public Category(String name) { this.name = name; }
        public String getName() { return name; }
        public List<Entry> getEntries() { return entries; }
        public void addEntry(Entry entry) { entries.add(entry); }
    }

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

        public Text getLeftText() { return leftText; }
        public Text getRightText() { return rightText; }
    }
    public static class Entry {
        private final Text name;
        private final ItemStack icon;
        private final List<Page> pages = new ArrayList<>();
        private final Side side;

        public Entry(String name, ItemStack icon) {
            this(Text.literal(name), icon, Side.LEFT);
        }

        public Entry(String name, ItemStack icon, Side side) {
            this(Text.literal(name), icon, side);
        }

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
}
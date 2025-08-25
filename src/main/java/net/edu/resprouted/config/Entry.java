package net.edu.resprouted.config;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.TooltipListEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public abstract class Entry<T> {
    private final String text;
    private final String[] tooltip;
    private final Supplier<T> current;
    private final Consumer<T> saver;
    private final T defaultValue;
    private final T min;
    private final T max;

    public static Entry<Boolean> booleanEntry(String name, Supplier<Boolean> current, Consumer<Boolean> saver, Boolean defaultValue, String... tooltip) {
        return new Entry<>(name, current, saver, defaultValue, tooltip) {
            @Override
            TooltipListEntry<Boolean> build(ConfigEntryBuilder builder) {
                return builder.startBooleanToggle(Text.translatable(getText()), getCurrent().get())
                        .setSaveConsumer(getSaver())
                        .setTooltip(Arrays.stream(getTooltip()).map(Text::translatable).toArray(Text[]::new))
                        .setDefaultValue(getDefaultValue())
                        .build();
            }
        };
    }
    public static Entry<List<String>> stringListEntry(String name, Supplier<List<String>> current, Consumer<List<String>> saver, List<String> defaultValue, String... tooltip) {
        return new Entry<>(name, current, saver, defaultValue, tooltip) {
            @Override
            TooltipListEntry<List<String>> build(ConfigEntryBuilder builder) {
                return builder.startStrList(Text.translatable(getText()), getCurrent().get())
                        .setSaveConsumer(getSaver())
                        .setTooltip(Arrays.stream(getTooltip()).map(Text::translatable).toArray(Text[]::new))
                        .setDefaultValue(getDefaultValue())
                        .build();
            }
        };
    }
    public static Entry<Integer> integerEntry(String name, Supplier<Integer> current, Consumer<Integer> saver, Integer defaultValue, Integer min, Integer max, String... tooltip) {
        return new Entry<>(name, current, saver, defaultValue, min, max, tooltip) {
            @Override
            TooltipListEntry<Integer> build(ConfigEntryBuilder builder) {
                return builder.startIntField(Text.translatable(getText()), getCurrent().get())
                        .setSaveConsumer(getSaver())
                        .setTooltip(Arrays.stream(getTooltip()).map(Text::translatable).toArray(Text[]::new))
                        .setDefaultValue(getDefaultValue())
                        .setMin(getMin())
                        .setMax(getMax())
                        .build();
            }
        };
    }
    public static Entry<Float> floatEntry(String name, Supplier<Float> current, Consumer<Float> saver, Float defaultValue, Float min, Float max, String... tooltip) {
        return new Entry<>(name, current, saver, defaultValue, min, max, tooltip) {
            @Override
            TooltipListEntry<Float> build(ConfigEntryBuilder builder) {
                return builder.startFloatField(Text.translatable(getText()), getCurrent().get())
                        .setSaveConsumer(getSaver())
                        .setTooltip(Arrays.stream(getTooltip()).map(Text::translatable).toArray(Text[]::new))
                        .setDefaultValue(getDefaultValue())
                        .setMin(getMin())
                        .setMax(getMax())
                        .build();
            }
        };
    }
    private Entry(String name, Supplier<T> current, Consumer<T> saver, T defaultValue, String... tooltip) {
        this(name, current, saver, defaultValue, null, null, tooltip);
    }
    private Entry(String name, Supplier<T> current, Consumer<T> saver, T defaultValue, T min, T max, String... tooltip) {
        this.text = name;
        this.current = current;
        this.saver = saver;
        this.defaultValue = defaultValue;
        this.min = min;
        this.max = max;
        this.tooltip = tooltip;
    }
    abstract TooltipListEntry<T> build(ConfigEntryBuilder builder);

    public String getText() {
        return text;
    }
    public String[] getTooltip() {
        return tooltip;
    }
    public Supplier<T> getCurrent() {
        return current;
    }
    public Consumer<T> getSaver() {
        return saver;
    }
    public T getDefaultValue() {
        return defaultValue;
    }
    public T getMin() {
        return min;
    }
    public T getMax() {
        return max;
    }
}

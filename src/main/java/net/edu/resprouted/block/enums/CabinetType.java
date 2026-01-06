package net.edu.resprouted.block.enums;

import net.minecraft.util.StringIdentifiable;

public enum CabinetType implements StringIdentifiable {
    SINGLE("single"),
    TOP("top"),
    BOTTOM("bottom");
    private final String name;

    CabinetType(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return name;
    }

    public CabinetType getOpposite() {
        return switch (this) {
            case SINGLE -> SINGLE;
            case TOP -> BOTTOM;
            case BOTTOM -> TOP;
        };
    }
}
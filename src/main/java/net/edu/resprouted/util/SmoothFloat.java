package net.edu.resprouted.util;

import net.minecraft.util.math.MathHelper;

public class SmoothFloat {
    private static final float speed = 0.2f;
    private float previous = 0f;
    private float current = 0f;
    private float target = 0f;

    public void setTarget(float newTarget) {
        this.target = newTarget;
    }

    public void tick() {
        previous = current;
        current = MathHelper.lerp(speed, current, target);
    }

    public float get(float partialTicks) {
        return MathHelper.lerp(partialTicks, previous, current);
    }

    public void set(float value) {
        previous = value;
        current = value;
        target = value;
    }
}

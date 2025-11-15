package net.edu.resprouted.util;

import net.minecraft.util.math.MathHelper;

public class SmoothFloat {
    private static final float SPEED = 0.2f;
    private static final float EPSILON = 0.001f;

    private float previous = 0f;
    private float current = 0f;
    private float target = 0f;
    private boolean isAnimating = false;

    public void setTarget(float newTarget) {
        if (Math.abs(this.target - newTarget) > EPSILON) {
            this.target = newTarget;
            this.isAnimating = true;
        }
    }

    public void tick() {
        if (!isAnimating) return;

        previous = current;
        current = MathHelper.lerp(SPEED, current, target);

        if (Math.abs(current - target) < EPSILON) {
            current = target;
            previous = target;
            isAnimating = false;
        }
    }

    public float get(float partialTicks) {
        return MathHelper.lerp(partialTicks, previous, current);
    }

    public void set(float value) {
        previous = value;
        current = value;
        target = value;
        isAnimating = false;
    }
}
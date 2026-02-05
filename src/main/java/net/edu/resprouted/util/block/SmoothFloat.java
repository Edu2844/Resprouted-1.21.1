package net.edu.resprouted.util.block;

import net.minecraft.util.math.MathHelper;

//Handles smooth interpolation for fluid level animations
public class SmoothFloat {
    private static final float SPEED = 0.2f; // Interpolation speed (20% per tick)
    private static final float EPSILON = 0.001f; // Stop when difference is less than 0.001 (0.1% of full range)

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

    // Called every tick to smoothly interpolate towards the target
    public void tick() {
        if (!isAnimating) return;

        previous = current;
        current = MathHelper.lerp(SPEED, current, target);

        // Stop animating once we're close enough to the target
        if (Math.abs(current - target) < EPSILON) {
            current = target;
            previous = target;
            isAnimating = false;
        }
    }

    // Interpolates between ticks
    public float get(float partialTicks) {
        return MathHelper.lerp(partialTicks, previous, current);
    }

    // Set value directly without animating
    public void set(float value) {
        previous = value;
        current = value;
        target = value;
        isAnimating = false;
    }
}
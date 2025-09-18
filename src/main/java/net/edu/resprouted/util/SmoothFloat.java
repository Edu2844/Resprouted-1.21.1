package net.edu.resprouted.util;

import net.minecraft.util.math.MathHelper;

public class SmoothFloat {
    private float previous = 0f;
    private float current = 0f;
    private long lastUpdateTime = System.currentTimeMillis();

    public void update(float target, float speed) {
        long currentTime = System.currentTimeMillis();
        float delta = (currentTime - lastUpdateTime) / 1000f;
        previous = current;
        current = MathHelper.lerp(speed * delta * 20f, current, target);
        lastUpdateTime = currentTime;
    }

    public float get(float partialTicks) {
        return MathHelper.lerp(partialTicks, previous, current);
    }

    public void set(float value) {
        previous = value;
        current = value;
        lastUpdateTime = System.currentTimeMillis();
    }
}
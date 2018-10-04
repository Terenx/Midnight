package com.mushroom.midnight.common.entities;

import net.minecraft.util.math.MathHelper;

import javax.vecmath.Point2f;
import java.util.Random;

public class RiftGeometry {
    private static final int POINT_COUNT = 12;
    private static final float DISPLACEMENT_SCALE = 0.4F;

    private final long seed;
    private final float width;
    private final float height;

    public RiftGeometry(long seed, float width, float height) {
        this.seed = seed;
        this.width = width;
        this.height = height;
    }

    public Point2f[] computePath(float animation, float unstableAnimation, float time) {
        Random random = new Random(this.seed);
        Point2f[] ring = new Point2f[POINT_COUNT];

        float idleSpeed = 0.08F;
        float idleIntensity = 0.1F * animation * (unstableAnimation * 5.0F + 1.0F);

        float displacementAnimation = Math.min(2.0F * animation, 1.0F);

        float sizeX = (this.width / 2.0F) * animation;
        float sizeY = (this.height / 2.0F) * 0.5F * (animation + 1.0F);

        // Generates a circle around (0; 0), stretching it horizontally and displacing each vertex by a random amount

        float tau = (float) (Math.PI * 2.0F);
        for (int i = 0; i < POINT_COUNT; i++) {
            float angle = i * tau / POINT_COUNT;

            float idleAnimation = MathHelper.sin(time * idleSpeed + i * 10) * idleIntensity;
            float displacement = (random.nextFloat() * 2.0F - 1.0F) + idleAnimation;
            float scaledDisplacement = displacement * DISPLACEMENT_SCALE * displacementAnimation;

            float pointX = -MathHelper.sin(angle) * (sizeX + scaledDisplacement);
            float pointY = MathHelper.cos(angle) * (sizeY + scaledDisplacement);

            ring[i] = new Point2f(pointX, pointY);
        }

        return ring;
    }

    public long getSeed() {
        return this.seed;
    }
}

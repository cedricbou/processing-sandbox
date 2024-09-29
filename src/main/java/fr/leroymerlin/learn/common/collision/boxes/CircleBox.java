package fr.leroymerlin.learn.common.collision.boxes;

import processing.core.PVector;

public class CircleBox implements Box {
    private final PVector position;
    private final float radius;

    public CircleBox(PVector position, float radius) {
        this.position = position;
        this.radius = radius;
    }

    public PVector getPosition() {
        return position;
    }

    public float getRadius() {
        return radius;
    }
}

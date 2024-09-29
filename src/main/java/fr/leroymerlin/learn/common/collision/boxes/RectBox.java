package fr.leroymerlin.learn.common.collision.boxes;

import processing.core.PVector;

public class RectBox implements Box {
    private final PVector center;
    private final PVector size;

    public RectBox(final PVector center, final PVector size) {
        this.center = center;
        this.size = size;
    }

    public PVector getCenter() {
        return center;
    }

    public PVector getSize() {
        return size;
    }
}

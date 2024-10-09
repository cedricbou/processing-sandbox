package fr.leroymerlin.learn.common.collision.maths.gjk.shapes;

import fr.leroymerlin.learn.common.collision.maths.gjk.GjkShape;
import processing.core.PMatrix;
import processing.core.PMatrix2D;
import processing.core.PVector;

public class Circle implements GjkShape {

    private final PMatrix transformationMatrix = new PMatrix2D();

    // Circle properties
    private final PVector center;
    private final float radius;

    public Circle(final PVector center, final float radius) {
        this.center = center.copy();
        this.radius = radius;
    }

    public void setCenter(final float x, final float y) {
        center.set(x, y);
    }

    public void translate(final float x, final float y) {
        center.add(x, y);
    }

    @Override
    public PVector support(PVector direction, PVector target) {
        // Move the center in the direction of the radius.
        return target.set(direction).normalize().mult(radius).add(center);
    }

    @Override
    public PVector center(PVector target) {
        return target.set(center);
    }
}

package fr.leroymerlin.learn.common.collision.maths.gjk.shapes;

import fr.leroymerlin.learn.common.collision.maths.gjk.GjkShape;
import processing.core.PVector;

public class Circle implements GjkShape {

    private final PVector center;
    private final float radius;

    public Circle(final PVector center, final float radius) {
        this.center = center.copy();
        this.radius = radius;
    }

    @Override
    public PVector support(PVector direction, PVector target) {
        return target.set(center).add(PVector.mult(PVector.fromAngle(direction.heading()), radius));
    }

    @Override
    public PVector center(PVector target) {
        return target.set(center);
    }
}

package fr.leroymerlin.learn.common.collision.maths.gjk.shapes;

import fr.leroymerlin.learn.common.collision.maths.gjk.GjkShape;
import net.jcip.annotations.NotThreadSafe;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

@NotThreadSafe
public class Ngon implements GjkShape {

    private final static int INITIAL_POINTS_CAPACITY = 5;

    private final List<PVector> points = new ArrayList<>(INITIAL_POINTS_CAPACITY);
    private final PVector support = new PVector();
    private final PVector center = new PVector();

    public Ngon() {
    }

    public void addPoint(final PVector point) {
        points.add(point.copy());
    }

    public List<PVector> viewPoints() {
        return points;
    }

    @Override
    public void translate(float x, float y) {
        for(PVector point : points) {
            point.add(x, y);
        }
    }

    @Override
    public void setCenter(float x, float y) {
        center(this.center);
        final float xDisplacement = x - center.x;
        final float yDisplacement = y - center.y;
        translate(xDisplacement, yDisplacement);
    }
    
    private float signedArea() {
        float area = 0.0f;
        for(int i = 0; i < points.size(); i++) {
            final PVector p1 = points.get(i);
            final PVector p2 = points.get((i + 1) % points.size());
            area += p1.x * p2.y - p2.x * p1.y;
        }
        return area * 0.5f;
    }

    private float centerX(float signedArea) {
        float cx = 0.0f;
        for(int i = 0; i < points.size(); i++) {
            final PVector p1 = points.get(i);
            final PVector p2 = points.get((i + 1) % points.size());
            cx += (p1.x + p2.x) * (p1.x * p2.y - p2.x * p1.y);
        }
        return cx / (6.0f * signedArea);
    }

    private float centerY(float signedArea) {
        float cy = 0.0f;
        for(int i = 0; i < points.size(); i++) {
            final PVector p1 = points.get(i);
            final PVector p2 = points.get((i + 1) % points.size());
            cy += (p1.y + p2.y) * (p1.x * p2.y - p2.x * p1.y);
        }
        return cy / (6.0f * signedArea);
    }

    /**
     * Compute the support point of the Ngon in the given direction.
     * The support point is the point of the Ngon that is the furthest in the given direction.
     * It is equivalent to the point of the Ngon that has the highest dot product with the direction.
     * @param direction the direction vector
     *                  (it is not normalized, the support point will be the point with the highest dot product with the direction)
     *                  (it is not modified)
     *                  (must be non null)
     * @param target the target vector to store the result
     *               (it is modified)
     *               (must be non null)
     * return the target vector for chaining
     */
    @Override
    public PVector support(PVector direction, PVector target) {
        float maxDot = Float.NEGATIVE_INFINITY;
        for(PVector point : points) {
            final float dot = point.dot(direction);
            if(dot > maxDot) {
                maxDot = dot;
                support.set(point);
            }
        }
        return target.set(support);
    }

    /**
     * Compute the center of the Ngon.
     * The center of the Ngon is the center of mass of the Ngon
     * It is computed as the average of the points of the Ngon.
     * @param target the target point to store the result
     *               (it is modified)
     *               (must be non null)
     * return the target point for chaining
     */
    @Override
    public PVector center(PVector target) {
        final float signedArea = signedArea();
        return target.set(centerX(signedArea), centerY(signedArea));
    }


    public static Ngon rectangle(PVector center, float width, float height) {
        final Ngon rectangle = new Ngon();
        final PVector lowerLeft = new PVector(center.x - (width / 2.f), center.y - (height / 2.f));
        final PVector upperRight = new PVector(center.x + (width / 2.f), center.y + (height / 2.f));
        return rectangle(lowerLeft, upperRight);
    }

    public static Ngon rectangle(PVector lowerLeft, PVector upperRight) {
        final Ngon rectangle = new Ngon();
        rectangle.addPoint(lowerLeft);
        rectangle.addPoint(new PVector(upperRight.x, lowerLeft.y));
        rectangle.addPoint(upperRight);
        rectangle.addPoint(new PVector(lowerLeft.x, upperRight.y));
        return rectangle;
    }

    public static Ngon triangle(PVector p1, PVector p2, PVector p3) {
        final Ngon triangle = new Ngon();
        triangle.addPoint(p1);
        triangle.addPoint(p2);
        triangle.addPoint(p3);
        return triangle;
    }
}

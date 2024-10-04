package fr.leroymerlin.learn.common.collision.maths.gjk.shapes;

import fr.leroymerlin.learn.common.collision.maths.gjk.GjkShape;
import net.jcip.annotations.NotThreadSafe;
import processing.core.PVector;

import java.util.LinkedList;

@NotThreadSafe
public class Ngon implements GjkShape {

    private final LinkedList<PVector> points = new LinkedList<PVector>();
    private final PVector center = new PVector();

    private boolean updateCenter = true;

    public Ngon() {

    }

    public void addPoint(final PVector point) {
        points.add(point.copy());
        updateCenter = true;
    }

    private float signedArea() {
        float area = 0.0f;
        for(int i = 0; i < points.size() - 1; i++) {
            final PVector p1 = points.get(i);
            final PVector p2 = points.get((i + 1) % points.size());
            area += p1.x * p2.y - p2.x * p1.y;
        }
        return area * 0.5f;
    }

    private float centerX(float signedArea) {
        float cx = 0.0f;
        for(int i = 0; i < points.size() - 1; i++) {
            final PVector p1 = points.get(i);
            final PVector p2 = points.get((i + 1) % points.size());
            cx += (p1.x + p2.x) * (p1.x * p2.y - p2.x * p1.y);
        }
        return cx / (6.0f * signedArea);
    }

    private float centerY(float signedArea) {
        float cy = 0.0f;
        for(int i = 0; i < points.size() - 1; i++) {
            final PVector p1 = points.get(i);
            final PVector p2 = points.get((i + 1) % points.size());
            cy += (p1.y + p2.y) * (p1.x * p2.y - p2.x * p1.y);
        }
        return cy / (6.0f * signedArea);
    }

    @Override
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
    public PVector support(PVector direction, PVector target) {
        float maxDot = Float.NEGATIVE_INFINITY;
        for(PVector point : points) {
            final float dot = point.dot(direction);
            if(dot > maxDot) {
                maxDot = dot;
                target.set(point);
            }
        }
        return target;
    }

    @Override
    /**
     * Compute the center of the Ngon.
     * The center of the Ngon is the center of mass of the Ngon
     * It is computed as the average of the points of the Ngon.
     * @param target the target point to store the result
     *               (it is modified)
     *               (must be non null)
     * return the target point for chaining
     */
    public PVector center(PVector target) {
        if(updateCenter) {
            final float signedArea = signedArea();
            center.set(centerX(signedArea), centerY(signedArea));
            updateCenter = false;
        }
        return target.set(center);
    }
}

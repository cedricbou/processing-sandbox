package fr.leroymerlin.learn.common.collision.maths.gjk;

import net.jcip.annotations.NotThreadSafe;
import processing.core.PVector;

/**
 * A reusable simplex for the GJK algorithm (not thread safe).
 *
 */
@NotThreadSafe
public class GjkSimplex123 {

    private final PVector[] points = new PVector[3];
    private int size = 0;

    private final PVector preAllocatedAB = new PVector();
    private final PVector preAllocatedAC = new PVector();
    private final PVector preAllocatedAO = new PVector();
    private final PVector preAllocatedABPerp = new PVector();
    private final PVector preAllocatedACPerp = new PVector();

    protected GjkSimplex123() {
        for(int i = 0; i < 3; i++) {
            points[i] = new PVector();
        }
    }

    public void add(final PVector point) {
        this.add(point.x, point.y);
    }

    public void add(final float x, final float y) {
        points[size++].set(x, y);
    }

    private void removeC() {
        points[1].set(points[2]);
        points[0].set(points[1]);
        size = 2;
    }

    private void removeB() {
        points[1].set(points[2]);
        size = 2;
    }

    /**
     * Update the simplex to look for one containing the origin and set new direction to keep looking for.
     * @return true if the simplex contains the origin, false otherwise.
     */
    public boolean handleSimplex(final PVector targetDirection) {
        if (size == 2) {
            return handleSimplex2(targetDirection);
        } else if (size == 3) {
            return handleSimplex3(targetDirection);
        }
        return false;
    }

    private boolean handleSimplex2(final PVector targetDirection) {
        final PVector a = points[1];
        final PVector b = points[0];

        final PVector ab = preAllocatedAB.set(b).sub(a);
        final PVector ao = preAllocatedAO.set(a).mult(-1);

        // AB x AO x AB
        final PVector abPerp = preAllocatedABPerp.set(ab).cross(ao).cross(ab);

        // Set New direction as the perpendicular to AB
        targetDirection.set(abPerp);

        // Origin is not contained in the simplex (line)
        return false;
    }

    private boolean handleSimplex3(final PVector direction) {
        // ABC, by convention A is the last point added
        final PVector a = points[2];
        final PVector b = points[1];
        final PVector c = points[0];

        final PVector ab = preAllocatedAB.set(b).sub(a);
        final PVector ac = preAllocatedAC.set(c).sub(a);
        final PVector ao = preAllocatedAO.set(a).mult(-1);

        // (AC x AB) x AB
        final PVector abPerp = preAllocatedABPerp.set(ac).cross(ab).cross(ab);

        // If the origin is in the direction of AB, remove C and set the new direction
        // (meaning the origin is not in ABC but is in the region perpendicular to AB)
        if(abPerp.dot(ao) > 0.0f) {
            removeC();
            direction.set(abPerp);
            return false;
        }

        // (AB x AC) x AC
        final PVector acPerp = preAllocatedACPerp.set(ab).cross(ac).cross(ac);

        // If the origin is in the direction of AC, remove B and set the new direction
        // (meaning the origin is not in ABC but is in the region perpendicular to AC)
        if(acPerp.dot(ao) > 0.0f) {
            removeB();
            direction.set(acPerp);
            return false;
        }

        // By deduction origin must be in ABC
        return true;
    }

    /**
     * Reinitialize the simplex without new allocation.
     */
    public void reuse() {
        size = 0;
    }
}

package fr.leroymerlin.learn.common.collision.maths.gjk;

import net.jcip.annotations.NotThreadSafe;
import processing.core.PVector;

/**
 * Implementation of the GJK algorithm (not thread safe).
 */
@NotThreadSafe
public class Gjk {
    private final GjkSimplex123 preAllocatedSimplex = new GjkSimplex123();
    private final PVector preAllocatedDirection = new PVector();
    private final PVector preAllocatedShapeACenter = new PVector();
    private final PVector preAllocatedShapeBCenter = new PVector();
    private final PVector preAllocatedOppositeDirection = new PVector();
    private final PVector preAllocatedSupportShapeB = new PVector();
    private final PVector preAllocatedMinkowskiDifferenceSupport = new PVector();
    private final static PVector ORIGIN = new PVector(0, 0);


    public boolean intersect(final GjkShape a, final GjkShape b) {
        preAllocatedSimplex.reuse();

        // Starting direction is B center - A center
        preAllocatedDirection.set(b.center(preAllocatedShapeBCenter)).sub(a.center(preAllocatedShapeACenter));

        // Compute Minkowski difference support ( support(A, d) - support(B, -d) )
        minkowskiDifferenceSupport(a, b, preAllocatedDirection, preAllocatedMinkowskiDifferenceSupport);

        // Add the first point to the simplex
        preAllocatedSimplex.add(preAllocatedMinkowskiDifferenceSupport);

        // Compute the new direction from Origin to the simplex
        preAllocatedDirection.set(ORIGIN).sub(preAllocatedMinkowskiDifferenceSupport);

        while(true) {
            // Compute the Minkowski difference support
            minkowskiDifferenceSupport(a, b, preAllocatedDirection, preAllocatedMinkowskiDifferenceSupport);

            // If the support point is not in the direction of the origin, the shapes do not intersect
            if(preAllocatedMinkowskiDifferenceSupport.dot(preAllocatedDirection) < 0.0f) {
                return false;
            }

            // Add the support point to the simplex
            preAllocatedSimplex.add(preAllocatedMinkowskiDifferenceSupport);

            // If the simplex contains the origin, the shapes intersect
            // Otherwise direction has been updated to keep looking for the origin in the next simplex.
            if(preAllocatedSimplex.handleSimplex(preAllocatedDirection)) {
                return true;
            }
        }
    }

    private void minkowskiDifferenceSupport(final GjkShape a, final GjkShape b, final PVector direction, final PVector target) {
        PVector.mult(direction, -1, preAllocatedOppositeDirection);
        target.set(a.support(direction, target)).sub(b.support(preAllocatedOppositeDirection, preAllocatedSupportShapeB));
    }
}

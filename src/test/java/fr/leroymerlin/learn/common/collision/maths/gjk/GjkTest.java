package fr.leroymerlin.learn.common.collision.maths.gjk;

import fr.leroymerlin.learn.common.collision.maths.gjk.shapes.Circle;
import fr.leroymerlin.learn.common.collision.maths.gjk.shapes.Ngon;
import org.junit.jupiter.api.Test;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GjkTest {

    // Algorithm instance.
    private final Gjk gjk = new Gjk();

    // Triangle Assets
    // Triangle 1 intersect with Triangle 2
    // Triangle 2 intersect with Triangle 3
    // Triangle 3 do not intersect with Triangle 1
    private final Ngon triangle1 = new Ngon();
    private final Ngon triangle2 = new Ngon();
    private final Ngon triangle3 = new Ngon();

    // Circle1 do not intersect any triangles
    // Circle2 intersect with Triangle 2 and Triangle 3
    // Circle3 intersect with Triangle 1
    private final Circle circle1 = new Circle(new PVector(-5, 5), 2);
    private final Circle circle2 = new Circle(new PVector(-3, 0), 3);
    private final Circle circle3 = new Circle(new PVector(8, -3), 3.5f);

    public GjkTest() {
        // Init triangles.
        triangle1.addPoint(new PVector(4, 1));
        triangle1.addPoint(new PVector(6, -3));
        triangle1.addPoint(new PVector(2, -2));

        triangle2.addPoint(new PVector(1, 5));
        triangle2.addPoint(new PVector(3, -1));
        triangle2.addPoint(new PVector(-3, -2));

        triangle3.addPoint(new PVector(-1, 4));
        triangle3.addPoint(new PVector(-3, -2));
        triangle3.addPoint(new PVector(-5, -1));
    }

    @Test
    public void testTwoOverlappingTriangleMustIntersect() {
        assertTrue(gjk.intersect(triangle1, triangle2));
        assertTrue(gjk.intersect(triangle2, triangle3));

    }

    @Test
    public void testTwoNonOverlappingTriangleMustNotIntersect() {
        assertFalse(gjk.intersect(triangle1, triangle3));
    }

    @Test
    public void testNotOverlappingCircleWithTriangleMustNotIntersect() {
        assertFalse(gjk.intersect(circle1, triangle1));
        assertFalse(gjk.intersect(circle1, triangle2));
        assertFalse(gjk.intersect(circle1, triangle3));
    }

    @Test
    public void testOverlappingCircleWithTwoTrianglesMustIntersect() {
        assertTrue(gjk.intersect(circle2, triangle2));
        assertTrue(gjk.intersect(circle2, triangle3));
        assertFalse(gjk.intersect(circle2, triangle1));
    }

    @Test
    public void testOverlappingCircleWithOneTriangleMustIntersect() {
        assertTrue(gjk.intersect(circle3, triangle1));
        assertFalse(gjk.intersect(circle3, triangle2));
        assertFalse(gjk.intersect(circle3, triangle3));
    }
}

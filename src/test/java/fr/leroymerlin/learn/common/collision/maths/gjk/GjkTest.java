package fr.leroymerlin.learn.common.collision.maths.gjk;

import fr.leroymerlin.learn.common.collision.maths.gjk.shapes.Circle;
import fr.leroymerlin.learn.common.collision.maths.gjk.shapes.Ngon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import processing.core.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GjkTest {

    // Algorithm instance.
    private final Gjk gjk = new Gjk();

    // Transformation matrices
    private final PMatrix transformedMatrix = new PMatrix2D();
    private final PMatrix identityMatrix = new PMatrix2D();

    // Triangle Assets
    // Triangle 1 intersect with Triangle 2
    // Triangle 2 intersect with Triangle 3
    // Triangle 3 do not intersect with Triangle 1
    // Triangle 4 intersect with Triangle 3 when translated by -10, 10 and rotated by 90 degrees
    private final Ngon triangle1 = Ngon.triangle(new PVector(4, 1), new PVector(6, -3), new PVector(2, -2));
    private final Ngon triangle2 = Ngon.triangle(new PVector(1, 5), new PVector(3, -1), new PVector(-3, -2));
    private final Ngon triangle3 = Ngon.triangle(new PVector(-1, 4), new PVector(-3, -2), new PVector(-5, -1));
    private final Ngon triangle4 = Ngon.triangle(new PVector(10, -10), new PVector(10, -13), new PVector(13, -13));

    private final Ngon rectangle1 = Ngon.rectangle(new PVector(0, 0), 10, 10);
    private final Ngon rectangle2 = Ngon.rectangle(new PVector(5, 5), 10, 10);
    private final Ngon rectangle3 = Ngon.rectangle(new PVector(-20, -20), 10, 10);


    // Circle1 do not intersect any triangles
    // Circle2 intersect with Triangle 2 and Triangle 3
    // Circle3 intersect with Triangle 1
    // Circle4 intersect with Triangle 1 when translated by -10, 10
    private final Circle circle1 = new Circle(new PVector(-5, 5), 2);
    private final Circle circle2 = new Circle(new PVector(-3, 0), 3);
    private final Circle circle3 = new Circle(new PVector(8, -3), 3.5f);
    private final Circle circle4 = new Circle(new PVector(18, -18), 3.5f);

    public GjkTest() {
        circle4.translate(-13, 13);
    }

    @BeforeEach
    public void setup() {

    }

    @Test
    public void testTwoOverlappingTriangleMustIntersect() {
        assertTrue(gjk.intersect(triangle1, triangle2));
        assertTrue(gjk.intersect(triangle2, triangle1));
        assertTrue(gjk.intersect(triangle2, triangle3));
        assertTrue(gjk.intersect(triangle3, triangle2));

    }

    @Test
    public void testTwoNonOverlappingTriangleMustNotIntersect() {
        assertFalse(gjk.intersect(triangle1, triangle3));
        assertFalse(gjk.intersect(triangle3, triangle1));
    }

    @Test
    public void testNotOverlappingCircleWithTriangleMustNotIntersect() {
        assertFalse(gjk.intersect(circle1, triangle1));
        assertFalse(gjk.intersect(triangle1, circle1));
        assertFalse(gjk.intersect(circle1, triangle2));
        assertFalse(gjk.intersect(triangle2, circle1));
        assertFalse(gjk.intersect(circle1, triangle3));
        assertFalse(gjk.intersect(triangle3, circle1));
    }

    @Test
    public void testOverlappingCircleWithTwoTrianglesMustIntersect() {
        assertTrue(gjk.intersect(circle2, triangle2));
        assertTrue(gjk.intersect(triangle2, circle2));
        assertTrue(gjk.intersect(circle2, triangle3));
        assertTrue(gjk.intersect(triangle3, circle2));
        assertFalse(gjk.intersect(circle2, triangle1));
        assertFalse(gjk.intersect(triangle1, circle2));
    }

    @Test
    public void testOverlappingCircleWithOneTriangleMustIntersect() {
        assertTrue(gjk.intersect(circle3, triangle1));
        assertTrue(gjk.intersect(triangle1, circle3));
        assertFalse(gjk.intersect(circle3, triangle2));
        assertFalse(gjk.intersect(triangle2, circle3));
        assertFalse(gjk.intersect(circle3, triangle3));
        assertFalse(gjk.intersect(triangle3, circle3));
    }

    @Test
    public void testOverlappingTransformedCircleWithOneTriangleMustIntersect() {
        assertTrue(gjk.intersect(circle4, triangle1));
        assertTrue(gjk.intersect(triangle1, circle4));
        assertFalse(gjk.intersect(circle4, triangle2));
        assertFalse(gjk.intersect(triangle2, circle4));
        assertFalse(gjk.intersect(circle4, triangle3));
        assertFalse(gjk.intersect(triangle3, circle4));
    }

    @Test
    public void testOverlappingRectangleWithOtherRectangles() {
        assertTrue(gjk.intersect(rectangle1, rectangle2));
        assertTrue(gjk.intersect(rectangle2, rectangle1));
        assertFalse(gjk.intersect(rectangle1, rectangle3));
        assertFalse(gjk.intersect(rectangle3, rectangle1));
        assertFalse(gjk.intersect(rectangle3, rectangle2));
        assertFalse(gjk.intersect(rectangle2, rectangle3));
    }

}

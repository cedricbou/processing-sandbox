package fr.leroymerlin.learn.common.collision.maths.gjk;

import processing.core.PVector;

/**
 * Interface for a shape that can be used with the GJK algorithm.
 */
public interface GjkShape {

    /**
     * Support function to return the furthest point of the shape in a given direction.
     * @param direction the direction vector
     * @param target the target vector to store the result
     * @return the target vector for chaining
     */
    public PVector support(PVector direction, PVector target);

    /**
     * Center point of the shape.
     * @param target the target point to store the result
     * @return the target point for chaining
     */
    public PVector center(PVector target);
}

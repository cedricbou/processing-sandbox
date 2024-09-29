package fr.leroymerlin.learn.common.collision.boxes;

import processing.core.PApplet;

public class BoxMaths {

    /*
     * Intersection between two circles.
     */
    public static boolean intersect(CircleBox a, CircleBox b) {
        return a.getPosition().dist(b.getPosition()) < a.getRadius() + b.getRadius();
    }

    /*
     * Intersection between a circle and a rectangle.
     */
    public static boolean intersect(CircleBox a, RectBox b) {
        float x = Math.max(b.getCenter().x - b.getSize().x / 2, Math.min(a.getPosition().x, b.getCenter().x + b.getSize().x / 2));
        float y = Math.max(b.getCenter().y - b.getSize().y / 2, Math.min(a.getPosition().y, b.getCenter().y + b.getSize().y / 2));
        
        return PApplet.sqrt(PApplet.sq(x - a.getPosition().x) + PApplet.sq(y - a.getPosition().y)) < a.getRadius();
    }

    public static <A extends Box, B extends Box> boolean insersect(A a, B b) {
        if (a instanceof CircleBox && b instanceof CircleBox) {
            return intersect((CircleBox) a, (CircleBox) b);
        } else if (a instanceof CircleBox && b instanceof RectBox) {
            return intersect((CircleBox) a, (RectBox) b);
        } else if (a instanceof RectBox && b instanceof CircleBox) {
            return intersect((CircleBox) b, (RectBox) a);
        } else {
            return false;
        }
    }

}

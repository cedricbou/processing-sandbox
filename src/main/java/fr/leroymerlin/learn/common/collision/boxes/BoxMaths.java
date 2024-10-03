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

    /*
     * Intersection between two rectangles
     */
    public static boolean intersect(RectBox a, RectBox b) {
        return a.getCenter().x - a.getSize().x / 2 < b.getCenter().x + b.getSize().x / 2 &&
                a.getCenter().x + a.getSize().x / 2 > b.getCenter().x - b.getSize().x / 2 &&
                a.getCenter().y - a.getSize().y / 2 < b.getCenter().y + b.getSize().y / 2 &&
                a.getCenter().y + a.getSize().y / 2 > b.getCenter().y - b.getSize().y / 2;
    }

    public static <A extends Box, B extends Box> boolean intersect(A a, B b) {
        return switch (a) {
            case CircleBox circleBox when b instanceof CircleBox -> intersect(circleBox, (CircleBox) b);
            case CircleBox circleBox when b instanceof RectBox -> intersect(circleBox, (RectBox) b);
            case RectBox rectBox when b instanceof CircleBox -> intersect((CircleBox) b, rectBox);
            case RectBox rectBox when b instanceof RectBox -> intersect(rectBox, (RectBox) b);
            case null, default -> false;
        };
    }

}

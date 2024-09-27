package fr.leroymerlin.learn;

import processing.core.PVector;

public class BouncingBall {

    private final float radius;
    private final Frame frame;

    private final PVector position;
    private final PVector velocity;

    public BouncingBall(Frame frame, PVector position, PVector velocity, float radius) {
        this.frame = frame;
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
    }

    public void update(float delta) {
        System.out.println("delta: " + delta + " velocity: " + velocity + " position: " + position);
        position.add(velocity.x * delta, velocity.y * delta);
        frame.framePosition(position);
    }

    public PVector getPosition() {
        return position;
    }

    public float getRadius() {
        return radius;
    }
}

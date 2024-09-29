package fr.leroymerlin.learn.game;

import fr.leroymerlin.learn.common.Actor;
import fr.leroymerlin.learn.common.collision.Collider;
import fr.leroymerlin.learn.common.collision.boxes.CircleBox;
import processing.core.PVector;

public class BouncingBall implements Actor, Collider<CircleBox> {

    private final float radius;
    private final Arena arena;

    private final PVector position;
    private final PVector velocity;

    private final CircleBox box;

    public BouncingBall(Arena arena, PVector position, PVector velocity, float radius) {
        this.arena = arena;
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
        this.box = new CircleBox(position, radius);
    }

    @Override
    public void update(float delta) {
        position.add(velocity.x * delta, velocity.y * delta);

        if( arena.collidesBottomWall(this) || arena.collidesTopWall(this) ) {
            velocity.y *= -1;
        }

        if( arena.collidesLeftWall(this) || arena.collidesRightWall(this) ) {
            velocity.x *= -1;
        }
    }

    @Override
    public CircleBox getBox() {
        return box;
    }

    public PVector getPosition() {
        return position;
    }

    public float getRadius() {
        return radius;
    }
}

package fr.leroymerlin.learn.game;

import fr.leroymerlin.learn.common.collision.Collider;
import fr.leroymerlin.learn.common.collision.boxes.Box;
import fr.leroymerlin.learn.common.collision.boxes.BoxMaths;
import fr.leroymerlin.learn.common.collision.boxes.RectBox;
import processing.core.PVector;

/**
 * The Arena is the frame where the pong game is played
 * It is a rectangle with a given size.
 * Each border of the arena is a wall implemented with Collider Box
 */
public class Arena {
    private final float width;
    private final float height;
    
    private final RectBox topWall;
    private final RectBox bottomWall;
    private final RectBox leftWall;
    private final RectBox rightWall;

    private final static float WALL_THICKNESS = 100;

    public Arena(float width, float height) {
        this.width = width;
        this.height = height;

        this.topWall = new RectBox(
            new PVector(width / 2, -WALL_THICKNESS / 2),
            new PVector(width, WALL_THICKNESS));
        this.bottomWall = new RectBox(
            new PVector(width / 2, height + WALL_THICKNESS / 2),
            new PVector(width, WALL_THICKNESS));
        this.leftWall = new RectBox(
            new PVector(-WALL_THICKNESS / 2, height / 2),
            new PVector(WALL_THICKNESS, height));
        this.rightWall = new RectBox(
            new PVector(width + WALL_THICKNESS / 2, height / 2),
            new PVector(WALL_THICKNESS, height));
    }

    public boolean collidesTopWall(Collider<? extends Box> collider) {
        return BoxMaths.intersect(collider.getBox(), topWall);
    }

    public boolean collidesBottomWall(Collider<? extends Box> collider) {
        return BoxMaths.intersect(collider.getBox(), bottomWall);
    }

    public boolean collidesLeftWall(Collider<? extends Box> collider) {
        return BoxMaths.intersect(collider.getBox(), leftWall);
    }

    public boolean collidesRightWall(Collider<? extends Box> collider) {
        return BoxMaths.intersect(collider.getBox(), rightWall);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}

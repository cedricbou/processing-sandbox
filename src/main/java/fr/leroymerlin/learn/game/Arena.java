package fr.leroymerlin.learn.game;

import fr.leroymerlin.learn.common.Drawable;
import fr.leroymerlin.learn.common.collision.Collider;
import fr.leroymerlin.learn.common.collision.boxes.Box;
import fr.leroymerlin.learn.common.collision.boxes.BoxMaths;
import fr.leroymerlin.learn.common.collision.boxes.RectBox;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

import java.util.Arrays;

/**
 * The Arena is the frame where the pong game is played
 * It is a rectangle with a given size.
 * Each border of the arena is a wall implemented with Collider Box
 */
public class Arena implements Drawable {
    private static final PVector CENTER_POSITION = new PVector(0.f, 0.f);

    private final float width;
    private final float height;
    
    private final RectBox topWall;
    private final RectBox bottomWall;
    private final RectBox leftWall;
    private final RectBox rightWall;

    private final RectBox[] walls;

    private int hue = 0;

    private final static float WALL_THICKNESS = 6;

    public Arena(float width, float height) {
        this.width = width;
        this.height = height;

        final PVector topWallCenter = new PVector(CENTER_POSITION.x, CENTER_POSITION.y - height / 2 + WALL_THICKNESS / 2);
        final PVector bottomWallCenter = new PVector(CENTER_POSITION.x, CENTER_POSITION.y + height / 2 - WALL_THICKNESS / 2);
        final PVector leftWallCenter = new PVector(CENTER_POSITION.x - width / 2 + WALL_THICKNESS / 2, CENTER_POSITION.y);
        final PVector rightWallCenter = new PVector(CENTER_POSITION.x + width / 2 - WALL_THICKNESS / 2, CENTER_POSITION.y);

        final PVector horizontalWallSize = new PVector(width, WALL_THICKNESS);
        final PVector verticalWallSize = new PVector(WALL_THICKNESS, height);

        this.topWall = new RectBox(topWallCenter, horizontalWallSize);
        this.bottomWall = new RectBox(bottomWallCenter, horizontalWallSize);
        this.leftWall = new RectBox(leftWallCenter, verticalWallSize);
        this.rightWall = new RectBox(rightWallCenter, verticalWallSize);

        this.walls = new RectBox[]{topWall, bottomWall, leftWall, rightWall};
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

    @Override
    public PVector getPosition() {
        return CENTER_POSITION;
    }

    @Override
    public void draw(final PApplet P) {
        P.colorMode(PConstants.HSB, 1024);
        P.fill(hue, 500, 500);
        P.rectMode(PConstants.CENTER);
        for(RectBox wall : walls) {
            P.rect(wall.getCenter().x, wall.getCenter().y, wall.getSize().x, wall.getSize().y);
        }
        hue += 1;
        hue %= 1024;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}

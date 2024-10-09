package fr.leroymerlin.learn.game;

import fr.leroymerlin.learn.common.Drawable;
import fr.leroymerlin.learn.common.collision.GjkCollider;
import fr.leroymerlin.learn.common.collision.maths.gjk.GjkShape;
import fr.leroymerlin.learn.common.collision.maths.gjk.shapes.Ngon;
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
    
    private final Ngon topWall;
    private final Ngon bottomWall;
    private final Ngon leftWall;
    private final Ngon rightWall;

    private final Ngon[] walls;

    private int hue = 0;

    private final static float WALL_THICKNESS = 6;

    public Arena(float width, float height) {
        this.width = width;
        this.height = height;

        final PVector topWallCenter = new PVector(CENTER_POSITION.x, CENTER_POSITION.y - height / 2 + WALL_THICKNESS / 2);
        final PVector bottomWallCenter = new PVector(CENTER_POSITION.x, CENTER_POSITION.y + height / 2 - WALL_THICKNESS / 2);
        final PVector leftWallCenter = new PVector(CENTER_POSITION.x - width / 2 + WALL_THICKNESS / 2, CENTER_POSITION.y);
        final PVector rightWallCenter = new PVector(CENTER_POSITION.x + width / 2 - WALL_THICKNESS / 2, CENTER_POSITION.y);

        this.topWall = Ngon.rectangle(topWallCenter, width, WALL_THICKNESS);
        this.bottomWall = Ngon.rectangle(bottomWallCenter, width, WALL_THICKNESS);
        this.leftWall = Ngon.rectangle(leftWallCenter, WALL_THICKNESS, height);
        this.rightWall = Ngon.rectangle(rightWallCenter, WALL_THICKNESS, height);

        this.walls = new Ngon[] {topWall, bottomWall, leftWall, rightWall};
    }

    private boolean collidesWall(GjkCollider collider, GjkShape wall) {
        return collider.getCollisionAlgorithm().intersect(collider.getCollisionShape(), wall);
    }

    public boolean collidesTopWall(GjkCollider collider) {
        return this.collidesWall(collider, topWall);
    }

    public boolean collidesBottomWall(GjkCollider collider) {
        return this.collidesWall(collider, bottomWall);
    }

    public boolean collidesLeftWall(GjkCollider collider) {
        return this.collidesWall(collider, leftWall);
    }

    public boolean collidesRightWall(GjkCollider collider) {
        return this.collidesWall(collider, rightWall);
    }

    @Override
    public PVector getPosition() {
        return CENTER_POSITION;
    }

    @Override
    public void draw(final PApplet P) {
        P.colorMode(PConstants.HSB, 1024);
        P.fill(hue, 500, 500);
        P.rectMode(PConstants.CORNERS);
        for(Ngon wall : walls) {
            P.rect(wall.viewPoints().get(0).x, wall.viewPoints().get(0).y, wall.viewPoints().get(2).x, wall.viewPoints().get(2).y);
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

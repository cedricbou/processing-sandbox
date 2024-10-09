package fr.leroymerlin.learn.game;

import fr.leroymerlin.learn.common.Actor;
import fr.leroymerlin.learn.common.Drawable;
import fr.leroymerlin.learn.common.collision.GjkCollider;
import fr.leroymerlin.learn.common.collision.maths.gjk.GjkShape;
import fr.leroymerlin.learn.common.collision.maths.gjk.shapes.Ngon;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Pad extends GjkCollider implements Actor, Drawable {

    private final Arena arena;

    private final PVector position;
    private final Ngon collisionBox;

    private final float speed;

    private final int width;
    private final int height;

    public Pad(final Arena arena, final int width, final int height, final float xPosition, final float speed) {
        this.arena = arena;
        this.position = new PVector(xPosition, 0.0f);
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.collisionBox = Ngon.rectangle(this.position, width, height);
    }

    public void moveUp() {
        this.position.y -= this.speed;
        this.collisionBox.setCenter(this.position.x, this.position.y);

        if(arena.collidesTopWall(this)) {
            this.position.y += this.speed;
            this.collisionBox.setCenter(this.position.x, this.position.y);

        }
    }

    public void moveDown() {
        this.position.y += this.speed;
        this.collisionBox.setCenter(this.position.x, this.position.y);

        if(arena.collidesBottomWall(this)) {
            this.position.y -= this.speed;
            this.collisionBox.setCenter(this.position.x, this.position.y);
        }

    }

    @Override
    public PVector getPosition() {
        return position;
    }

    @Override
    public void draw(PApplet P) {
        P.rectMode(PConstants.CENTER);
        P.colorMode(PConstants.HSB, 1024);
        P.fill(580, 500, 500);
        P.rect(this.position.x, this.position.y, this.width, this.height);
        P.fill(210, 500, 500);
    }

    @Override
    public GjkShape getCollisionShape() {
        return collisionBox;
    }
}

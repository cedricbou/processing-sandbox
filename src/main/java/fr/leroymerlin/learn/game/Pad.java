package fr.leroymerlin.learn.game;

import fr.leroymerlin.learn.common.Actor;
import fr.leroymerlin.learn.common.Drawable;
import fr.leroymerlin.learn.common.collision.Collider;
import fr.leroymerlin.learn.common.collision.boxes.RectBox;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Pad implements Actor, Drawable, Collider<RectBox> {

    private final Arena arena;

    private final PVector position;
    private final RectBox boundingBox;

    private final float speed;

    private final int width;
    private final int height;

    public Pad(final Arena arena, final int width, final int height, final float xPosition, final float speed) {
        this.arena = arena;
        this.position = new PVector(xPosition, 0.0f);
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.boundingBox = new RectBox(this.position, new PVector(width, height));
    }

    public void moveUp() {
        this.position.y -= this.speed;

        if(arena.collidesTopWall(this)) {
            this.position.y += this.speed;
        }
    }

    public void moveDown() {
        this.position.y += this.speed;

        if(arena.collidesBottomWall(this)) {
            this.position.y -= this.speed;
        }
    }

    @Override
    public PVector getPosition() {
        return position;
    }

    @Override
    public void draw(PApplet P) {
        P.rectMode(PConstants.CENTER);
        P.pushMatrix();
        P.translate(position.x, position.y);
        P.colorMode(PConstants.HSB, 1024);
        P.fill(580, 500, 500);
        P.rect(0.f, 0.f, this.width, this.height);
        P.popMatrix();
    }

    @Override
    public RectBox getBox() {
        return this.boundingBox;
    }
}

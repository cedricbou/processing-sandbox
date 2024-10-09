package fr.leroymerlin.learn.game;

import fr.leroymerlin.learn.common.Actor;
import fr.leroymerlin.learn.common.Drawable;
import fr.leroymerlin.learn.common.collision.Collider;
import fr.leroymerlin.learn.common.collision.GjkCollider;
import fr.leroymerlin.learn.common.collision.maths.gjk.GjkShape;
import fr.leroymerlin.learn.common.collision.maths.gjk.shapes.Circle;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public final class BouncingBall extends GjkCollider implements Actor, Drawable {

    private final float diameter;
    private final Arena arena;
    private final Pad[] pads;

    private final PVector position;
    private final PVector velocity;

    private final Circle collisionBox;

    private final static int[] colorHues = { 800, 200, 400, 600 };
    private int colorHueIndex = 0;

    public BouncingBall(Arena arena, PVector position, PVector velocity, float radius, Pad player, Pad opponent) {
        this.arena = arena;
        this.position = position;
        this.velocity = velocity;
        this.diameter = radius * 2;
        this.collisionBox = new Circle(position, radius);
        this.pads = new Pad[] { player, opponent };
    }

    public void move(float delta) {
        position.add(velocity.x * delta, velocity.y * delta);
        collisionBox.translate(velocity.x * delta, velocity.y * delta);
    }

    @Override
    public void update(float delta) {
        this.move(delta);

        if( arena.collidesBottomWall(this) || arena.collidesTopWall(this) ) {
            velocity.y *= -1;
        }

        if( arena.collidesLeftWall(this) || arena.collidesRightWall(this) ) {
            velocity.x *= -1;
        }

        for( Pad pad : this.pads ) {
            if( this.collides(pad) ) {
                this.changeColorHue();
                velocity.x *= -1;
            }
        }
    }

    @Override
    public void draw(PApplet P) {
        P.colorMode(PConstants.HSB, 1024);
        P.fill(colorHues[colorHueIndex], 512, 512);
        P.ellipse(position.x, position.y, diameter, diameter);
    }

    public PVector getPosition() {
        return position;
    }

    private void changeColorHue() {
        colorHueIndex++;
        colorHueIndex %= colorHues.length;
    }

    @Override
    public GjkShape getCollisionShape() {
        return collisionBox;
    }

}

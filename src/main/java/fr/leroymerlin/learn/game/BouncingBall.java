package fr.leroymerlin.learn.game;

import fr.leroymerlin.learn.common.Actor;
import fr.leroymerlin.learn.common.Drawable;
import fr.leroymerlin.learn.common.collision.Collider;
import fr.leroymerlin.learn.common.collision.boxes.CircleBox;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class BouncingBall implements Actor, Collider<CircleBox>, Drawable {

    private final float diameter;
    private final Arena arena;
    private final Pad[] pads;

    private final PVector position;
    private final PVector velocity;

    private final CircleBox box;

    private final static int[] colorHues = { 800, 200, 400, 600 };
    private int colorHueIndex = 0;

    public BouncingBall(Arena arena, PVector position, PVector velocity, float radius, Pad player, Pad opponent) {
        this.arena = arena;
        this.position = position;
        this.velocity = velocity;
        this.diameter = radius * 2;
        this.box = new CircleBox(position, radius);
        this.pads = new Pad[] { player, opponent };
    }

    @Override
    public void update(float delta) {
        position.add(velocity.x * delta, velocity.y * delta);

        if( arena.collidesBottomWall(this) || arena.collidesTopWall(this) ) {
            this.changeColorHue();
            velocity.y *= -1;
        }

        if( arena.collidesLeftWall(this) || arena.collidesRightWall(this) ) {
            this.changeColorHue();
            velocity.x *= -1;
        }

        for( Pad pad : this.pads ) {
            if( this.collides(pad) ) {
                this.changeColorHue();
            }
        }
    }

    @Override
    public void draw(PApplet P) {
        P.colorMode(PConstants.HSB, 1024);
        P.fill(colorHues[colorHueIndex], 512, 512);
        P.ellipse(position.x, position.y, diameter, diameter);
    }

    @Override
    public CircleBox getBox() {
        return box;
    }

    public PVector getPosition() {
        return position;
    }

    private void changeColorHue() {
        colorHueIndex++;
        colorHueIndex %= colorHues.length;
    }
}

package fr.leroymerlin.learn;

import fr.leroymerlin.learn.common.Clock;
import fr.leroymerlin.learn.game.Arena;
import fr.leroymerlin.learn.game.BouncingBall;
import fr.leroymerlin.learn.game.Pad;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

/**
 * Processing.org Application
 */
public class App extends PApplet {

    public static final float BALL_SPEED = 120.f;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private float offset_center_x;
    private float offset_center_y;

    private final Clock clock = new Clock(this);
    private final Arena arena = new Arena((float)WIDTH, (float)HEIGHT);
    private final Pad player = new Pad(
            arena,
            15,
            80,
            -WIDTH / 2.f + 20.f,
            3.f);

    private final Pad opponent = new Pad(
            arena,
            15,
            80,
            WIDTH / 2.f - 20.f,
            3.f);

    private final BouncingBall ball = new BouncingBall(
            arena,
            new PVector(200, 200),
            new PVector(BALL_SPEED, BALL_SPEED),
            20,
                player,
                opponent);

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        offset_center_x = width / 2.0f;
        offset_center_y = height / 2.0f;
        clock.start();
    }



    @Override
    public void draw() {
        pushMatrix();
        translate(offset_center_x, offset_center_y);

        if(keyPressed) {
            if (key == PConstants.CODED) {
                if (keyCode == PConstants.UP) {
                    player.moveUp();
                } else if (keyCode == PConstants.DOWN) {
                    player.moveDown();
                }
            } else {
                stop();
                exit();
            }
        }

        final float delta = clock.delta();
        colorMode(RGB, 1024);
        noStroke();

        background(128);

        ball.update(delta);
        ball.draw(this);
        arena.draw(this);
        player.draw(this);
        opponent.draw(this);

        popMatrix();
    }
    public static void main(String[] args) {
        String[] appletArgs = new String[] { "fr.leroymerlin.learn.App" };
        PApplet.main(appletArgs);
    }
}

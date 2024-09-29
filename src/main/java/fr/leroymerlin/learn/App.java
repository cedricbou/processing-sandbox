package fr.leroymerlin.learn;

import fr.leroymerlin.learn.common.Clock;
import fr.leroymerlin.learn.game.Arena;
import fr.leroymerlin.learn.game.BouncingBall;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * Processing.org Application
 */
public class App extends PApplet {

    public static final float BALL_SPEED = 40.f;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private int hue = 300;
    private final Clock clock = new Clock(this);
    private final Arena arena = new Arena((float)WIDTH, (float)HEIGHT);
    private final BouncingBall ball = new BouncingBall(
        arena,
        new PVector(200, 200),
        new PVector(BALL_SPEED, BALL_SPEED),
        20);

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        clock.start();
    }

    @Override
    public void draw() {
        if(keyPressed) {
            stop();
            exit();
        }

        final float delta = clock.delta();
        colorMode(RGB, 1024);
        noStroke();

        background(64, 50);

        ball.update(delta);
        fill(800);
        ellipse(ball.getPosition().x, ball.getPosition().y, ball.getRadius(), ball.getRadius());

        colorMode(HSB, 1024);

        stroke(hue, 500, 500);
        noFill();
        strokeWeight(4.0f);
        rectMode(CORNERS);
        rect(0, 0, arena.getWidth() - 4, arena.getHeight() - 4);

        hue += 1;
        hue %= 1024;
    }
    public static void main(String[] args) {
        String[] appletArgs = new String[] { "fr.leroymerlin.learn.App" };
        PApplet.main(appletArgs);
    }
}

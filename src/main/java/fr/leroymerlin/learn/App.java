package fr.leroymerlin.learn;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Processing.org Application
 */
public class App extends PApplet {

    private long previousTime = frameRateLastNanos;

    private final long skipFrameEllapsedTime = (long)Math.ceil(1e9 / 60);

    private final Frame frame = new Frame(800, 600);
    private final BouncingBall ball = new BouncingBall(frame, new PVector(200, 200), new PVector(1.f, 1.f), 20);

    @Override
    public void settings() {
        size(frame.width(), frame.height());
    }

    @Override
    public void draw() {
        float delta = (-previousTime + (previousTime = frameRateLastNanos)) / 1e9f;

        if(delta > skipFrameEllapsedTime) {
            // Skip frame if delta too long.
            return;
        }

        background(64);
        ball.update(delta);
        ellipse(ball.getPosition().x, ball.getPosition().y, ball.getRadius(), ball.getRadius());
    }
    public static void main(String[] args) {
        String[] appletArgs = new String[] { "fr.leroymerlin.learn.App" };
        PApplet.main(appletArgs);
    }
}

package fr.leroymerlin.learn.common;

import processing.core.PApplet;

public class Clock {
    private final PApplet P;

    private long previousTime = 0;

    public Clock(PApplet P) {
        this.P = P;
    }

    public void start() {
        this.previousTime = P.millis();
    }

    public float delta() {
        float delta = (-previousTime + (previousTime = P.millis())) / 1000.f;
        return delta;
    }
}

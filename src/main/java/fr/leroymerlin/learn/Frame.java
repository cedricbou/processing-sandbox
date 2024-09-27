package fr.leroymerlin.learn;

import processing.core.PVector;

public record Frame(int width, int height) {

    public Frame {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive");
        }
    }

    public void framePosition(PVector position) {
        position.x = Math.min(Math.max(0, position.x), width);
        position.y = Math.min(Math.max(0, position.y), height);
    }
}

package fr.leroymerlin.learn.common;

import processing.core.PApplet;

public interface Actor {

    public default void update(float elapsedTime) {
    }
}

package fr.leroymerlin.learn.common.collision;

import processing.core.PMatrix;

public interface Shape {

    void setCenter(final float x, final float y);
    void translate(final float x, final float y);
}

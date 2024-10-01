package fr.leroymerlin.learn.common;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public interface Drawable {

    public PVector getPosition();
    public default void draw(final PApplet P) {
        P.colorMode(PConstants.RGB, 1024);
        P.textSize(12);
        P.fill(0, 408, 612);
        P.text(this.toString(), this.getPosition().x, this.getPosition().y);
    }
}

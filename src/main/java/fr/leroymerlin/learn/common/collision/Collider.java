package fr.leroymerlin.learn.common.collision;

import fr.leroymerlin.learn.common.collision.boxes.Box;
import fr.leroymerlin.learn.common.collision.boxes.BoxMaths;

public interface Collider<B extends Box> {

    public B getBox();

    public default boolean collides(Collider<? extends Box> other) {
        return BoxMaths.intersect(getBox(), other.getBox());
    }
    
} 

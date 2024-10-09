package fr.leroymerlin.learn.common.collision;

public interface Collider<S extends Shape> {
    boolean collides(Collider<S> other);
    S getCollisionShape();
} 

package fr.leroymerlin.learn.common.collision;

import fr.leroymerlin.learn.common.collision.maths.gjk.Gjk;
import fr.leroymerlin.learn.common.collision.maths.gjk.GjkShape;

public abstract class GjkCollider implements Collider<GjkShape> {

    private static final Gjk collisionAlgorithm = new Gjk();

    public Gjk getCollisionAlgorithm() {
        return collisionAlgorithm;
    }

    @Override
    public boolean collides(Collider<GjkShape> other) {
        return getCollisionAlgorithm().intersect(getCollisionShape(), other.getCollisionShape());
    }
}

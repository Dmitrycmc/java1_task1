package ru.gb.star.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.gb.star.pool.Pool;
import ru.gb.star.pool.PoolItem;

public class Meteor extends PoolItem {
    private Vector2 pos = new Vector2();
    private Vector2 vel = new Vector2();
    private float angle = MathUtils.random(0, 360f);
    private float angleSpeed = MathUtils.random(-360f, 360f);

    final static float RADIUS = 60;

    public Meteor(Pool pool) {
        super(pool);
    }

    public Vector2 getPos() {
        return pos.cpy();
    }

    public Vector2 getVel() {
        return vel.cpy();
    }

    public float getAngle() {
        return angle;
    }

    public float getAngleSpeed() {
        return angleSpeed;
    }

    void setPos(Vector2 pos) {
        this.pos = pos;
    }

    void setVel(Vector2 vel) {
        this.vel = vel;
    }

    public void activate(float x, float y, float vx, float vy) {
        pos.set(x, y);
        vel.set(vx, vy);
    }

    public void update(float dt) {
        pos.mulAdd(vel, dt);

        if (pos.x < -Constants.width - Meteor.RADIUS) {
            deactivate();
        }
        if (pos.y < -Constants.height - RADIUS) {
            deactivate();
        }
        if (pos.x > 2 * Constants.width + RADIUS) {
            deactivate();
        }
        if (pos.y > 2 * Constants.height + RADIUS) {
            deactivate();
        }

        angle += angleSpeed * dt;
    }
}

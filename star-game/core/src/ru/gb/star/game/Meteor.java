package ru.gb.star.game;

import com.badlogic.gdx.math.Vector2;
import ru.gb.star.pool.Poolable;

public class Meteor implements Poolable {
    private boolean active = false;
    private Vector2 pos = new Vector2();
    private Vector2 vel = new Vector2();

    final static float RADIUS = 60;

    public Vector2 getPos() {
        return pos.cpy();
    }

    public Vector2 getVel() {
        return vel.cpy();
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
        active = true;
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
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }
}

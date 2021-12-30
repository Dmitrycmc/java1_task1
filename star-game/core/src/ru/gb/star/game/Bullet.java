package ru.gb.star.game;

import com.badlogic.gdx.math.Vector2;
import ru.gb.star.pool.Poolable;

public class Bullet implements Poolable {
    private boolean active = false;
    private Vector2 pos = new Vector2();
    private Vector2 vel = new Vector2();

    final static float RADIUS = 8;

    public Vector2 getPos() {
        return pos.cpy();
    }

    public Vector2 getVel() {
        return vel.cpy();
    }

    public void activate(float x, float y, float vx, float vy) {
        pos.set(x, y);
        vel.set(vx, vy);
        active = true;
    }

    public void update(float dt) {
        pos.mulAdd(vel, dt);
        if (pos.x < -RADIUS) {
            deactivate();
        }
        if (pos.y < -RADIUS) {
            deactivate();
        }
        if (pos.x > Constants.width + RADIUS) {
            deactivate();
        }
        if (pos.y > Constants.height + RADIUS) {
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

package ru.gb.star.game;

import com.badlogic.gdx.math.Vector2;
import ru.gb.star.pool.PoolItem;

public class Bullet extends PoolItem {
    private Vector2 pos = new Vector2();
    private Vector2 vel = new Vector2();

    final static float RADIUS = 8;

    public Bullet(BulletController bulletController) {
        super(bulletController);
    }

    public Vector2 getPos() {
        return pos.cpy();
    }

    public Vector2 getVel() {
        return vel.cpy();
    }

    public void activate(float x, float y, float vx, float vy) {
        pos.set(x, y);
        vel.set(vx, vy);
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
}

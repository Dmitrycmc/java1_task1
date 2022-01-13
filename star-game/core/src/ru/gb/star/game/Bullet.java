package ru.gb.star.game;

import com.badlogic.gdx.math.MathUtils;
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
        if (pos.x < -RADIUS || pos.y < -RADIUS || pos.x > Constants.width + RADIUS || pos.y > Constants.height + RADIUS) {
            deactivate();
            return;
        }

        for (int i = 0; i < 2; i++) {
            ((BulletController)pool).gc.getParticleController().setup(pos.x + MathUtils.random(-4, 4), pos.y + MathUtils.random(-4, 4),
                    vel.x * 0.1f + MathUtils.random(-20, 20), vel.y * 0.1f + MathUtils.random(-20, 20),
                    0.1f, 1.2f, 0.2f,
                    1.0f, 0.7f, 0, 1,
                    1, 1, 1, 0);
        }
    }
}

package ru.gb.star.game;

import com.badlogic.gdx.ai.utils.random.FloatDistribution;
import com.badlogic.gdx.ai.utils.random.TriangularFloatDistribution;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.gb.star.pool.Pool;
import ru.gb.star.pool.PoolItem;

public class Meteor extends PoolItem implements Collidable {
    private Vector2 pos = new Vector2();
    private Vector2 vel = new Vector2();
    private float angle;
    private float angleSpeed;
    private float radius;
    private float mass;
    private int hp;
    private Circle hitArea;

    final static float BASE_RADIUS = 60;
    final static float BASE_MASS = 200;

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

    public float getRadius() {
        return radius;
    }

    public float getMass() {
        return mass;
    }

    public Circle getHitArea() {
        return hitArea;
    }

    public boolean takeDamage(int dmg) {
        hp -= dmg;
        return hp <= 0;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public void setVel(Vector2 vel) {
        this.vel = vel;
    }

    public void activate(float x, float y, float vx, float vy) {
        FloatDistribution a = new TriangularFloatDistribution(0.2f, 1.6f, 0.7f);
        activate(x, y, vx, vy, a.nextFloat());
    }

    public void activate(float x, float y, float vx, float vy, float scale) {

        angle = MathUtils.random(0, 360f);
        angleSpeed = MathUtils.random(-360f, 360f);

        pos.set(x, y);
        vel.set(vx, vy);
        radius = BASE_RADIUS * scale;
        mass = BASE_MASS * scale * scale * scale;
        hitArea = new Circle(pos, radius);

        hp = (int) (40 * scale);
    }

    public void update(float dt) {
        pos.mulAdd(vel, dt);

        if (pos.x < -Constants.width - getRadius()) {
            deactivate();
        }
        if (pos.y < -Constants.height - getRadius()) {
            deactivate();
        }
        if (pos.x > 2 * Constants.width + getRadius()) {
            deactivate();
        }
        if (pos.y > 2 * Constants.height + getRadius()) {
            deactivate();
        }

        hitArea.setPosition(pos);

        angle += angleSpeed * dt;
    }
}

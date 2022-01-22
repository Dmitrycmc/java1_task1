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

        if (pos.x < -getRadius() && vel.x < 0) {
            pos.x += Constants.width + 2 * BASE_RADIUS;
        }
        if (pos.y < -getRadius() && vel.y < 0) {
            pos.y += Constants.height + 2 * BASE_RADIUS;
        }
        if (pos.x > Constants.width + getRadius() && vel.x > 0) {
            pos.x -= Constants.width + 2 * BASE_RADIUS;
        }
        if (pos.y > Constants.height + getRadius() && vel.y > 0) {
            pos.y -= Constants.height + 2 * BASE_RADIUS;
        }

        hitArea.setPosition(pos);

        angle += angleSpeed * dt;
    }

    public void spawn(boolean saveScale) {
        int screenNumber = (int) (Math.random() * 8);
        if (screenNumber >= 4) {
            screenNumber++;
        }
        int screenRow = screenNumber / 3 - 1;
        int screenCol = screenNumber % 3 - 1;

        pos = new Vector2(MathUtils.random(0, Constants.width - Meteor.BASE_RADIUS) + Constants.width * screenCol, MathUtils.random(0, Constants.height - Meteor.BASE_RADIUS) + Constants.height * screenRow);
        vel = new Vector2(MathUtils.random(-1f * Constants.pixelsPerMeter, 1f * Constants.pixelsPerMeter), MathUtils.random(-1f * Constants.pixelsPerMeter, 1f * Constants.pixelsPerMeter));

        if (saveScale) {
            activate(pos.x, pos.y, vel.x, vel.y, radius / BASE_RADIUS);
        } else {
            activate(pos.x, pos.y, vel.x, vel.y);
        }
    }
}

package ru.gb.star.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import ru.gb.star.screen.utils.Assets;

public class Hero implements Collidable{
    GameController gc;

    public static float RADIUS = 32;
    public static float MASS = 100;
    static public int MAX_HP = 100;

    private TextureRegion texture = Assets.get().getTextureAtlas().findRegion("ship");

    public float getAngle() {
        return angle;
    }

    private Vector2 pos = new Vector2(Constants.width / 2, Constants.height / 2);
    private Vector2 vel = new Vector2(0, 0);
    private float angle = 0f;
    private Vector2 lastDisplacement = new Vector2(0, 0);
    private int score = 0;
    private int scoreView = 0;
    private int hp = MAX_HP;
    private Circle hitBox = new Circle(pos, RADIUS);
    private Weapon currentWeapon;

    public Vector2 getLastDisplacement() {
        return lastDisplacement;
    }

    public int getScore() {
        return scoreView;
    }

    public int getHp() {
        return hp;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public boolean takeDamage(int val) {
        hp = Math.max(0, hp - val);
        return hp == 0;
    }

    public void takeHeal(int val) {
        hp = Math.min(MAX_HP, hp + val);
    }

    public Circle getHitBox() {
        return hitBox;
    }

    public void addScore(int ds) {
        score += ds;
    }

    public Hero(GameController gc) {
        this.gc = gc;
        currentWeapon = new Weapon(gc, "Plasmatic", 0.2f, 1, 600.0f, 300,
                new Vector3[]{
                        new Vector3(28, 0, 0),
                        new Vector3(28, 90, 20),
                        new Vector3(28, -90, -20)
                });
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture,
            pos.x - RADIUS, pos.y - RADIUS,
            RADIUS, RADIUS,
            2 * RADIUS, 2 * RADIUS,
            1, 1,
            angle
        );
    }

    private float t = 0;
    public void update(float dt) {
        if (scoreView < score) {
            scoreView += 500 * dt;
            if (scoreView > score) {
                scoreView = score;
            }
        }

        if (t > 0) {
            t -= dt;
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                t = currentWeapon.getFirePeriod();
                currentWeapon.fire();
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            angle += 180.0f * dt;

            float bx = pos.x + MathUtils.cosDeg(angle + 90) * 20;
            float by = pos.y + MathUtils.sinDeg(angle + 90) * 20;
            for (int i = 0; i < 2; i++) {
                gc.getParticleController().setup(bx + MathUtils.random(-4, 4), by + MathUtils.random(-4, 4),
                        vel.x * 0.1f + MathUtils.random(-20, 20), vel.y * 0.1f + MathUtils.random(-20, 20),
                        0.4f, 1.2f, 0.2f,
                        1.0f, 0.5f, 0, 1,
                        1, 1, 1, 0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            angle -= 180.0f * dt;

            float bx = pos.x + MathUtils.cosDeg(angle - 90) * 20;
            float by = pos.y + MathUtils.sinDeg(angle - 90) * 20;
            for (int i = 0; i < 2; i++) {
                gc.getParticleController().setup(bx + MathUtils.random(-4, 4), by + MathUtils.random(-4, 4),
                        vel.x * 0.1f + MathUtils.random(-20, 20), vel.y * 0.1f + MathUtils.random(-20, 20),
                        0.4f, 1.2f, 0.2f,
                        1.0f, 0.5f, 0, 1,
                        1, 1, 1, 0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            vel.x += MathUtils.cosDeg(angle) * 240.0f * dt;
            vel.y += MathUtils.sinDeg(angle) * 240.0f * dt;

            float bx = pos.x + MathUtils.cosDeg(angle + 180) * 20;
            float by = pos.y + MathUtils.sinDeg(angle + 180) * 20;
            for (int i = 0; i < 3; i++) {
                gc.getParticleController().setup(bx + MathUtils.random(-4, 4), by + MathUtils.random(-4, 4),
                        vel.x * -0.3f + MathUtils.random(-20, 20), vel.y * -0.3f + MathUtils.random(-20, 20),
                        0.5f, 1.2f, 0.2f,
                        1.0f, 0.5f, 0, 1,
                        1, 1, 1, 0);
            }

            lastDisplacement.set(MathUtils.cosDeg(angle) * 240.0f * dt, MathUtils.sinDeg(angle) * 240.0f * dt);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            vel.x -= MathUtils.cosDeg(angle) * 240.0f * dt;
            vel.y -= MathUtils.sinDeg(angle) * 240.0f * dt;

            float bx = pos.x + MathUtils.cosDeg(angle + 90) * 20;
            float by = pos.y + MathUtils.sinDeg(angle + 90) * 20;
            for (int i = 0; i < 2; i++) {
                gc.getParticleController().setup(bx + MathUtils.random(-4, 4), by + MathUtils.random(-4, 4),
                        vel.x * 0.1f + MathUtils.random(-20, 20), vel.y * 0.1f + MathUtils.random(-20, 20),
                        0.4f, 1.2f, 0.2f,
                        1.0f, 0.5f, 0, 1,
                        1, 1, 1, 0);
            }
            bx = pos.x + MathUtils.cosDeg(angle - 90) * 20;
            by = pos.y + MathUtils.sinDeg(angle - 90) * 20;
            for (int i = 0; i < 2; i++) {
                gc.getParticleController().setup(bx + MathUtils.random(-4, 4), by + MathUtils.random(-4, 4),
                        vel.x * 0.1f + MathUtils.random(-20, 20), vel.y * 0.1f + MathUtils.random(-20, 20),
                        0.4f, 1.2f, 0.2f,
                        1.0f, 0.5f, 0, 1,
                        1, 1, 1, 0);
            }

            lastDisplacement.set(-1 * MathUtils.cosDeg(angle) * 240.0f * dt, -1 * MathUtils.sinDeg(angle) * 240.0f * dt);
        } else {
            lastDisplacement.set(0, 0);
        }

        pos.mulAdd(vel, dt);

        if (pos.x < RADIUS) {
            vel.x *= -(1 - Constants.reflectionLoss);
            pos.x = RADIUS;
        }
        if (pos.y < RADIUS) {
            vel.y *= -(1 - Constants.reflectionLoss);
            pos.y = RADIUS;
        }
        if (pos.x > Constants.width - RADIUS) {
            vel.x *= -(1 - Constants.reflectionLoss);
            pos.x = Constants.width - RADIUS;
        }
        if (pos.y > Constants.height - RADIUS) {
            vel.y *= -(1 - Constants.reflectionLoss);
            pos.y = Constants.height - RADIUS;
        }

        hitBox.setPosition(pos);
    }

    public Vector2 getPos() {
        return pos;
    }

    public Vector2 getVel() {
        return vel;
    }

    public void setPos(Vector2 pos) {
        pos.set(pos);
    }

    public void setVel(Vector2 vel) {
        vel.set(vel);
    }

    public float getMass() {
        return MASS;
    }

    public float getRadius() {
        return RADIUS;
    }
}

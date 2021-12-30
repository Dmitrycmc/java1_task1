package ru.gb.star.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    GameController gc;

    public static float RADIUS = 32;

    private Texture texture = new Texture("ship.png");
    private Vector2 pos = new Vector2(Constants.width / 2, Constants.height / 2);
    private Vector2 vel = new Vector2(0, 0);
    private float angle = 0f;
    private Vector2 lastDisplacement = new Vector2(0, 0);

    public Vector2 getLastDisplacement() {
        return lastDisplacement;
    }

    public Hero(GameController gc) {
        this.gc = gc;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, pos.x - RADIUS, pos.y - RADIUS, 32, 32,
                64, 64, 1, 1,
                angle, 0, 0, 64, 64, false, false);
    }

    private float t = 0;

    public void update(float dt) {
        if (t > 0) {
            t -= dt;
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                t = Constants.reloadTime;
                gc.getBulletController().spawn(pos.x, pos.y,
                        MathUtils.cosDeg(angle) * 500.0f + vel.x,
                        MathUtils.sinDeg(angle) * 500.0f + vel.y);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            angle += 180.0f * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            angle -= 180.0f * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            vel.x += MathUtils.cosDeg(angle) * 240.0f * dt;
            vel.y += MathUtils.sinDeg(angle) * 240.0f * dt;
            lastDisplacement.set(MathUtils.cosDeg(angle) * 240.0f * dt, MathUtils.sinDeg(angle) * 240.0f * dt);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            vel.x -= MathUtils.cosDeg(angle) * 240.0f * dt;
            vel.y -= MathUtils.sinDeg(angle) * 240.0f * dt;
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
    }

    public void dispose() {
        texture.dispose();
    }
}

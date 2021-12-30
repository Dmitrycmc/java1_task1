package ru.gb.star.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.gb.star.pool.Pool;

public class MeteorController extends Pool<Meteor> {
    private GameController gc;
    Texture meteorTexture = new Texture("asteroid.png");

    public MeteorController(GameController gc) {
        this.gc = gc;
        for (int i = 0; i < Constants.meteorsNumber; i++) {
            spawn();
        }
    }

    @Override
    protected Meteor newObject() {
        return new Meteor();
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            Meteor b = activeList.get(i);
            batch.draw(meteorTexture, b.getPos().x - Meteor.RADIUS, b.getPos().y - Meteor.RADIUS, 2 * Meteor.RADIUS, 2 * Meteor.RADIUS);
        }
    }

    public static void handleCollision(Meteor a, Meteor b) {
        Vector2 centersDifference = a.getPos().cpy().sub(b.getPos()).nor();
        float distance = a.getPos().cpy().sub(b.getPos()).len();
        float pr1 = centersDifference.cpy().dot(a.getVel());
        float pr2 = centersDifference.cpy().dot(b.getVel());
        a.setVel(a.getVel().add(centersDifference.cpy().scl(-1f * pr1 + (1f - Constants.reflectionLoss) * pr2)));
        b.setVel(b.getVel().add(centersDifference.cpy().scl(-1f * pr2 + (1f - Constants.reflectionLoss) * pr1)));
        a.setPos(a.getPos().add(centersDifference.cpy().scl((2f * Meteor.RADIUS - distance) / 2)));
        b.setPos(b.getPos().sub(centersDifference.cpy().scl((2f * Meteor.RADIUS - distance) / 2)));
    }

    public void update(float dt){
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }

        for (int i = 0; i < freeList.size(); i++) {
            spawn();
        }

        for (int i = 0; i < activeList.size() - 1; i++) {
            for (int j = i + 1; j < activeList.size(); j++) {
                if (activeList.get(i).getPos().sub(activeList.get(j).getPos()).len() < 2 * Meteor.RADIUS) {
                    handleCollision(activeList.get(i), activeList.get(j));
                }
            }
        }

        for (int i = 0; i < activeList.size() - 1; i++) {
            for (int j = 0; j < gc.getBulletController().getActiveList().size(); j++) {
                if (activeList.get(i).getPos().sub(gc.getBulletController().getActiveList().get(j).getPos()).len() < Meteor.RADIUS + Bullet.RADIUS) {
                    activeList.get(i).deactivate();
                    gc.getBulletController().getActiveList().get(j).deactivate();
                }
            }
        }

        checkPool();
    }

    public void spawn(float x, float y, float vx, float vy){
        getActiveElement().activate(x, y, vx, vy);
    }

    public void spawn() {
        int screenNumber = (int) (Math.random() * 8);
        if (screenNumber >= 4) {
            screenNumber++;
        }
        int screenRow = screenNumber / 3 - 1;
        int screenCol = screenNumber % 3 - 1;

        Vector2 pos = new Vector2(MathUtils.random(0, Constants.width - Meteor.RADIUS) + Constants.width * screenCol, MathUtils.random(0, Constants.height - Meteor.RADIUS) + Constants.height * screenRow);
        Vector2 vel = new Vector2(MathUtils.random(-1f * Constants.pixelsPerMeter, 1f * Constants.pixelsPerMeter), MathUtils.random(-1f * Constants.pixelsPerMeter, 1f * Constants.pixelsPerMeter));

        spawn(pos.x, pos.y, vel.x, vel.y);
    }

    public void dispose() {
        meteorTexture.dispose();
    }
}

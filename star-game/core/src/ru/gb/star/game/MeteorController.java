package ru.gb.star.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.gb.star.pool.Pool;
import ru.gb.star.screen.utils.Assets;

public class MeteorController extends Pool<Meteor> {
    private GameController gc;
    TextureRegion meteorTexture = Assets.getInstance().getAtlas().findRegion("asteroid");

    public MeteorController(GameController gc) {
        this.gc = gc;
        for (int i = 0; i < Constants.meteorsNumber; i++) {
            spawn();
        }
    }

    @Override
    protected Meteor newObject() {
        return new Meteor(this);
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            Meteor b = activeList.get(i);
            batch.draw(meteorTexture,
                b.getPos().x - Meteor.RADIUS, b.getPos().y - Meteor.RADIUS,
                    Meteor.RADIUS, Meteor.RADIUS,
                    2 * Meteor.RADIUS, 2 * Meteor.RADIUS,
                    1.1f, 1.1f,
                    b.getAngle()
            );
        }
    }

    public static void handleCollision(Meteor a, Meteor b) {
        Vector2 centersDifference = a.getPos().sub(b.getPos()).nor();
        float distance = a.getPos().sub(b.getPos()).len();
        float pr1 = centersDifference.cpy().dot(a.getVel());
        float pr2 = centersDifference.cpy().dot(b.getVel());
        a.setVel(a.getVel().mulAdd(centersDifference, -1f * pr1 + (1f - Constants.reflectionLoss) * pr2));
        b.setVel(b.getVel().mulAdd(centersDifference, -1f * pr2 + (1f - Constants.reflectionLoss) * pr1));
        a.setPos(a.getPos().mulAdd(centersDifference, (2f * Meteor.RADIUS - distance) / 2));
        b.setPos(b.getPos().mulAdd(centersDifference, -(2f * Meteor.RADIUS - distance) / 2));
    }

    public void update(float dt){
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }

        for (int i = 0; i < inactiveList.size(); i++) {
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
            for (int j = 0; j < gc.getBulletController().getActiveElementsCount(); j++) {
                if (activeList.get(i).getPos().sub(gc.getBulletController().getActiveElementAt(j).getPos()).len() < Meteor.RADIUS + Bullet.RADIUS) {
                    if (activeList.get(i).takeDamage(10)) {
                        gc.getHero().addScore(100);
                    }
                    gc.getBulletController().getActiveElementAt(j).deactivate();
                }
            }
        }
    }

    public void spawn(float x, float y, float vx, float vy){
        getInactiveElement().activate(x, y, vx, vy);
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

    }
}

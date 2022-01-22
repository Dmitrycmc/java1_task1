package ru.gb.star.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.gb.star.pool.Pool;
import ru.gb.star.screen.utils.Assets;

public class MeteorController extends Pool<Meteor> {
    private GameController gc;
    TextureRegion meteorTexture = Assets.get().getTextureAtlas().findRegion("asteroid");

    public MeteorController(GameController gc) {
        this.gc = gc;
    }

    public void init(int meteorNumber) {
        for (int i = 0; i < meteorNumber; i++) {
            getInactiveElement().spawn(false);
        }
    }

    @Override
    protected Meteor newObject() {
        return new Meteor(this);
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            Meteor b = getActiveElementAt(i);
            batch.draw(meteorTexture,
                b.getPos().x - b.getRadius(), b.getPos().y - b.getRadius(),
                    b.getRadius(), b.getRadius(),
                    2 * b.getRadius(), 2 * b.getRadius(),
                    1.1f, 1.1f,
                    b.getAngle()
            );
        }
    }

    public static void handleCollision(Collidable meteor1, Collidable meteor2) {
        Vector2 centersDifference = meteor1.getPos().sub(meteor2.getPos()).nor();
        float distance = meteor1.getPos().sub(meteor2.getPos()).len();

        float m1 = meteor1.getMass();
        float m2 = meteor2.getMass();
        // Projections on centersDifference
        float v1 = centersDifference.dot(meteor1.getVel());
        float v2 = centersDifference.dot(meteor2.getVel());

        meteor1.setVel(meteor1.getVel().mulAdd(centersDifference,
                -1 * v1 + (1 - Constants.reflectionLoss) * ((m1 - m2) * v1 + 2 * m2 * v2) / (m1 + m2))
        );
        meteor2.setVel(meteor2.getVel().mulAdd(centersDifference,
                -1 * v2 + (1 - Constants.reflectionLoss) * ((m2 - m1) * v2 + 2 * m1 * v1) / (m1 + m2))
        );
        meteor1.setPos(meteor1.getPos().mulAdd(centersDifference,
                (meteor1.getRadius() + meteor2.getRadius() - distance) / 2)
        );
        meteor2.setPos(meteor2.getPos().mulAdd(centersDifference,
                -(meteor1.getRadius() + meteor2.getRadius() - distance) / 2)
        );
    }

    public void update(float dt){
        for (int i = 0; i < activeList.size(); i++) {
            getActiveElementAt(i).update(dt);
        }

        for (int i = 0; i < activeList.size() - 1; i++) {
            Meteor meteor1 = getActiveElementAt(i);
            for (int j = i + 1; j < activeList.size(); j++) {
                Meteor meteor2 = getActiveElementAt(j);

                if (meteor1.getHitArea().overlaps(meteor2.getHitArea())) {
                    handleCollision(meteor1, meteor2);
                }
            }
        }

        for (int i = 0; i < activeList.size(); i++) {
            Meteor meteor = getActiveElementAt(i);
            if (gc.getHero().getHitBox().overlaps(meteor.getHitArea())) {
                gc.getHero().takeDamage((int) gc.getHero().getVel().sub(meteor.getVel()).len() / 100);
                handleCollision(meteor, gc.getHero());
            }

            for (int j = 0; j < gc.getBulletController().getActiveElementsCount(); j++) {
                Bullet bullet = gc.getBulletController().getActiveElementAt(j);

                if (meteor.getHitArea().contains(bullet.getPos())) {
                    gc.getParticleController().setup(bullet.getPos().x +MathUtils.random(-4, 4), bullet.getPos().y + MathUtils.random(-4, 4),
                            bullet.getVel().x * -0.3f + MathUtils.random(-30, 30), bullet.getVel().y * -0.3f + MathUtils.random(-30, 30),
                            0.2f, 2.2f, 1.5f,
                            1.0f, 1.0f, 1.0f, 1,
                            0, 0, 1, 0);

                    if (meteor.takeDamage(10)) {
                        spawnChildren(meteor);
                        gc.getBenefitController().tryToSpawn(meteor.getPos().x, meteor.getPos().y);
                        meteor.deactivate();
                        gc.getHero().addScore(100);
                    }
                    bullet.deactivate();
                }
            }
        }
    }

    public void spawnChildren(Meteor meteor) {
        if (meteor.getRadius() / Meteor.BASE_RADIUS < 0.5f || Math.random() > 0.1f * gc.getLevel() ) {
            return;
        }

        float baseAngle = MathUtils.random(0, 120);
        float r = (float) (Math.sqrt(3) * meteor.getRadius() / (2 + Math.sqrt(3)));
        float v = MathUtils.random(100f, 200f);
        for (int i = 0; i < 3; i++) {
            float angle = baseAngle + i * 120;

            getInactiveElement().activate(
                    meteor.getPos().x + MathUtils.cosDeg(angle) * (meteor.getRadius() - r),
                    meteor.getPos().y + MathUtils.sinDeg(angle) * (meteor.getRadius() - r),
                    meteor.getVel().x + MathUtils.cosDeg(angle) * v,
                    meteor.getVel().y + MathUtils.sinDeg(angle) * v,
                    r / Meteor.BASE_RADIUS
            );
        }
    }
}

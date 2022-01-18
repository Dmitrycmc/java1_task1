package ru.gb.star.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import ru.gb.star.pool.Pool;

import javax.management.MBeanInfo;

public class BenefitController extends Pool<Benefit> {
    GameController gc;
    Texture textureAid = new Texture("images/aid.png");
    Texture texturePowerUp = new Texture("images/powerup.png");
    Texture textureWeapons = new Texture("images/weapons.png");

    public BenefitController(GameController gc) {
        this.gc = gc;
    }

    @Override
    protected Benefit newObject() {
        return new Benefit(this);
    }

    public void render(SpriteBatch batch) {
        float scale = MathUtils.sin(4 * t) * 0.15f + 1f;

        for (int i = 0; i < activeList.size(); i++) {
            Benefit b = getActiveElementAt(i);
            Texture texture;

            switch (b.getType()) {
                case POWER_UP:
                    texture = texturePowerUp;
                    break;
                case AID:
                    texture = textureAid;
                    break;
                case WEAPONS:
                default:
                    texture = textureWeapons;
            }

            batch.draw(texture,
                    b.getPos().x - Benefit.RADIUS, b.getPos().y - Benefit.RADIUS,
                    Benefit.RADIUS, Benefit.RADIUS,
                    2 * Benefit.RADIUS, 2 * Benefit.RADIUS,
                    scale, scale,
                    0f,
                    0, 0,
                    512, 512, false, false

            );
        }
    }

    private float t = 0;

    public void update(float dt) {
        t += dt;

        Hero hero = gc.getHero();
        for (int i = 0; i < activeList.size(); i++) {
            Benefit benefit = getActiveElementAt(i);
            if (benefit.getHitBox().overlaps(hero.getHitBox())) {
                switch (benefit.getType()) {
                    case POWER_UP:
                        hero.getCurrentWeapon().powerUp();
                        break;
                    case AID:
                        hero.takeHeal(20);
                        break;
                    case WEAPONS:
                    default:
                        hero.getCurrentWeapon().addWeapons(50);
                }
                gc.getParticleController().getEffectBuilder().takePowerUpEffect(benefit);
                benefit.deactivate();
            }
        }

        for (int i = 0; i < activeList.size(); i++) {
            //getActiveElementAt(i).update(dt);
        }
    }

    public void spawn(float x, float y) {
        BenefitType type;
        float rand = MathUtils.random(0f, 1f);

        if (rand < 0.2f) {
            type = BenefitType.POWER_UP;
        } else if (rand < 0.6f) {
            type = BenefitType.AID;
        } else {
            type = BenefitType.WEAPONS;
        }

        getInactiveElement().activate(x, y, type);
    }

    public void dispose() {
        texturePowerUp.dispose();
        textureAid.dispose();
    }

    public void tryToSpawn(float x, float y) {
        if (MathUtils.random(0f, 1f) < 0.3f) {
            spawn(x, y);
        }
    }
}

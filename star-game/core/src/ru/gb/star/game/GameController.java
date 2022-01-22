package ru.gb.star.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.StringBuilder;
import ru.gb.star.screen.utils.Assets;

public class GameController {
    private final Background background;
    private final BulletController bulletController;
    private final MeteorController meteorController;
    private final BenefitController benefitController;
    private final ParticleController particleController;
    private final Hero hero;
    private final BitmapFont font32;
    private final BitmapFont font108;
    private boolean paused = false;
    private int level = 0;

    public int getLevel() {
        return level;
    }

    private void togglePause() {
        paused = !paused;
    }

    public Background getBackground() {
        return background;
    }

    public BulletController getBulletController() {
        return bulletController;
    }

    public MeteorController getMeteorController() {
        return meteorController;
    }

    public BenefitController getBenefitController() {
        return benefitController;
    }

    public ParticleController getParticleController() {
        return particleController;
    }

    public Hero getHero() {
        return hero;
    }

    public GameController() {
        background = new Background(this);
        bulletController = new BulletController(this);
        meteorController = new MeteorController(this);
        benefitController = new BenefitController(this);
        particleController = new ParticleController();
        hero = new Hero(this);
        font32 = Assets.get().getFont(32);
        font108 = Assets.get().getFont(108);
    }

    public void render(SpriteBatch batch) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        background.render(batch);
        if (paused) {
            batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        } else {
            batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        }
        bulletController.render(batch);
        meteorController.render(batch);
        benefitController.render(batch);
        particleController.render(batch);
        hero.render(batch);
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        sb
                .append("Level: ").append(level).append("\n")
                .append("Score: ").append(hero.getScore()).append("\n")
                .append("Health: ").append(hero.getHp()).append(" / ").append(Hero.MAX_HP).append("\n")
                .append("Weapons: ").append(hero.getCurrentWeapon().getCurBullets()).append(" / ").append(hero.getCurrentWeapon().getMaxBullets()).append("\n")
                .append("Meteors: ").append(meteorController.getActiveElementsCount());
        font32.draw(batch, sb, Constants.scoreMargin, Constants.height - Constants.scoreMargin);

        if (levelCaptionTimer > 0) {
            sb.setLength(0);
            sb.append("Level ").append(level);
            font108.draw(batch, sb, 0, 600, 1280, Align.center, false);
        }

        batch.end();
    }

    private float throttlePauseTimer = 0;
    private float levelCaptionTimer = 0;
    public void update(float dt) {
        throttlePauseTimer += dt;
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && throttlePauseTimer > 0.5f) {
            togglePause();
            throttlePauseTimer = 0;
        }

        if (!paused) {
            background.update(dt);
            bulletController.update(dt);
            meteorController.update(dt);
            benefitController.update(dt);
            particleController.update(dt);
            hero.update(dt);

            if (levelCaptionTimer > 0) {
                levelCaptionTimer -= dt;
            }

            if (meteorController.getActiveElementsCount() == 0) {
                level++;
                levelUp();
            }
        }
    }

    private void levelUp() {
        meteorController.init(level * Constants.meteorsNumber);
        levelCaptionTimer = 2f;
        hero.getCurrentWeapon().setCurBulletsMax();
        hero.takeMaxHeal();
    }

    public void dispose() {
        background.dispose();
        benefitController.dispose();
    }
}

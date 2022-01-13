package ru.gb.star.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.StringBuilder;
import ru.gb.star.screen.utils.Assets;

public class GameController {
    private Background background;
    private BulletController bulletController;
    private MeteorController meteorController;
    private BenefitController benefitController;
    private ParticleController particleController;
    private Hero hero;
    private BitmapFont font32;
    private StringBuilder sb = new StringBuilder();
    private boolean paused = false;

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
        sb.setLength(0);
        sb.append("Score: ").append(hero.getScore()).append("\n")
                .append("Health: ").append(hero.getHp()).append(" / ").append(Hero.MAX_HP).append("\n")
                .append("Weapons: ").append(hero.getCurrentWeapon().getCurBullets()).append(" / ").append(hero.getCurrentWeapon().getMaxBullets());
        font32.draw(batch, sb, Constants.scoreMargin, Constants.height - Constants.scoreMargin);
        batch.end();
    }

    private float throttlePauseTimer = 0;
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
        }
    }

    public void dispose() {
        background.dispose();
        benefitController.dispose();
    }
}

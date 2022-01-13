package ru.gb.star.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameController {
    private Background background;
    private BulletController bulletController;
    private MeteorController meteorController;
    private Hero hero;

    public Background getBackground() {
        return background;
    }

    public BulletController getBulletController() {
        return bulletController;
    }

    public MeteorController getMeteorController() {
        return meteorController;
    }

    public Hero getHero() {
        return hero;
    }

    public GameController() {
        background = new Background(this);
        bulletController = new BulletController();
        meteorController = new MeteorController(this);
        hero = new Hero(this);
    }

    public void render(SpriteBatch batch) {
        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();
        background.render(batch);
        bulletController.render(batch);
        meteorController.render(batch);
        hero.render(batch);
        batch.end();
    }

    public void update(float dt) {
        background.update(dt);
        bulletController.update(dt);
        meteorController.update(dt);
        hero.update(dt);
    }

    public void dispose() {
        background.dispose();
        bulletController.dispose();
        meteorController.dispose();
        hero.dispose();
    }
}

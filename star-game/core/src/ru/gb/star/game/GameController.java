package ru.gb.star.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.StringBuilder;
import ru.gb.star.screen.utils.Assets;

public class GameController {
    private Background background;
    private BulletController bulletController;
    private MeteorController meteorController;
    private Hero hero;
    private BitmapFont font32;
    private StringBuilder sb = new StringBuilder();

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
        font32 = Assets.getInstance().getFont();
    }

    public void render(SpriteBatch batch) {
        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();
        background.render(batch);
        bulletController.render(batch);
        meteorController.render(batch);
        hero.render(batch);
        sb.setLength(0);
        sb.append("Score: ").append(hero.getScore()).append("\n")
                .append("Health: ").append(hero.getHp()).append(" / ").append(Hero.MAX_HP);
        font32.draw(batch, sb, Constants.scoreMargin, Constants.height - Constants.scoreMargin);
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

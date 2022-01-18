package ru.gb.star.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.gb.star.game.Background;
import ru.gb.star.screen.utils.Assets;

public class GameOverScreen extends AbstractScreen {
    Background background;
    private BitmapFont font108;

    public GameOverScreen(SpriteBatch batch) {
        super(batch);
    }

    @Override
    public void show() {
        font108 = Assets.get().getFont(108);
        background = new Background(null);
    }

    public void update(float dt) {
        background.update(dt);
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            ScreenManager.get().changeScreen(ScreenManager.ScreenType.MENU);
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        ScreenUtils.clear(0.0f, 0.0f, 0.0f, 1);
        batch.begin();
        background.render(batch);
        font108.draw(batch, "GAME OVER", 0, 600, 1280, Align.center, false);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}

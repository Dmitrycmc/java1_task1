package ru.gb.star.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.gb.star.screen.utils.Assets;

public class GameOverScreen extends AbstractScreen {
    private BitmapFont font108;

    public GameOverScreen(SpriteBatch batch) {
        super(batch);
    }

    @Override
    public void show() {
        this.font108 = Assets.get().getFont(108);
    }

    public void update(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            ScreenManager.get().changeScreen(ScreenManager.ScreenType.MENU);
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        ScreenUtils.clear(0.0f, 0.0f, 0.0f, 1);
        batch.begin();
        font108.draw(batch, "GAME OVER", 0, 600, 1280, Align.center, false);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}

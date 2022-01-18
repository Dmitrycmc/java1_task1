package ru.gb.star.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.gb.star.game.GameController;
import ru.gb.star.screen.utils.Assets;

public class GameScreen extends AbstractScreen{
    private GameController gc;

    public GameScreen(SpriteBatch batch) {
        super(batch);
    }

    @Override
    public void show() {
        Assets.get().loadAssets(ScreenManager.ScreenType.GAME);
        this.gc = new GameController();
    }

    @Override
    public void render(float delta) {
        gc.update(delta);
        gc.render(batch);
    }

    @Override
    public void dispose() {
        gc.dispose();
    }
}

package ru.gb.star.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.gb.star.game.GameController;
import ru.gb.star.screen.utils.Assets;

public class GameScreen extends AbstractScreen{
    private SpriteBatch batch;
    private GameController gc;

    public GameScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void show() {
        System.out.println("show");
        Assets.getInstance().loadAssets(ScreenManager.ScreenType.GAME);
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

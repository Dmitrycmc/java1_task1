package ru.gb.star.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.gb.star.game.GameController;

public class GameScreen extends AbstractScreen{
    private SpriteBatch batch;
    private GameController gc;

    public GameScreen(SpriteBatch batch, GameController gc) {
        this.batch = batch;
        this.gc = gc;
    }

    @Override
    public void show() {
        System.out.println("show");
    }

    @Override
    public void render(float delta) {
        gc.update(delta);
        gc.render(batch);
    }
}

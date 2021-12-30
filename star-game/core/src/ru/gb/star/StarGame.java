package ru.gb.star;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.gb.star.game.GameController;
import ru.gb.star.screen.GameScreen;

public class StarGame extends Game {
	private SpriteBatch batch;
	private GameController gc;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gc = new GameController();
		Screen gameScreen = new GameScreen(batch, gc);
		setScreen(gameScreen);
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		getScreen().render(dt);
	}

	@Override
	public void dispose () {
		batch.dispose();
		gc.dispose();
	}
}

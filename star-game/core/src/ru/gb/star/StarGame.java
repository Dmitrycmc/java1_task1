package ru.gb.star;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.gb.star.screen.GameScreen;
import ru.gb.star.screen.ScreenManager;

public class StarGame extends Game {
	private SpriteBatch batch;
	private Screen gameScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
		ScreenManager.get().init(this, batch);
		ScreenManager.get().changeScreen(ScreenManager.ScreenType.MENU);
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		getScreen().render(dt);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}

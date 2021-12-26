package game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class BallGame extends ApplicationAdapter {
	SpriteBatch batch;
	Hero hero;
	Background background;
	Ball[] balls;

	@Override
	public void create () {
		batch = new SpriteBatch();
		hero = new Hero();
		background = new Background(this);
		balls = new Ball[Constants.ballsNumber];
		for (int i = 0; i < Constants.ballsNumber; i++) {
			balls[i] = new Ball(i);
		}
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		ScreenUtils.clear(1, 1, 1, 1);
		batch.begin();
		background.render(batch);
		hero.render(batch);
		for (int i = 0; i < Constants.ballsNumber; i++) {
			balls[i].render(batch);
		}
		batch.end();
	}

	public void update(float dt) {
		background.update(dt);
		hero.update(dt);
		for (int i = 0; i < Constants.ballsNumber; i++) {
			balls[i].update(dt);
		}
		for (int i = 0; i < Constants.ballsNumber - 1; i++) {
			for (int j = i + 1; j < Constants.ballsNumber; j++) {
				if (balls[i].pos.cpy().sub(balls[j].pos).len() <= 2 * Constants.ballRadius) {
					Ball.handleCollision(balls[i], balls[j]);
				}
			}
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		background.getTextureCosmos().dispose();
		background.getTextureStar().dispose();
		Ball.texture.dispose();
	}

    public Hero getHero() {
		return hero;
    }
}

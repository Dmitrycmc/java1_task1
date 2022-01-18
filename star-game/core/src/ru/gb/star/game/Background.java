package ru.gb.star.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.gb.star.screen.utils.Assets;

public class Background {
    private class Star {
        Vector2 position;
        Vector2 velocity;
        float scale;

        public Star() {
            scale = MathUtils.random(0.1f, 1);
            position = new Vector2(MathUtils.random(-8, Constants.width + 8),
                    MathUtils.random(-8, Constants.height + 8));
            velocity = new Vector2(40 * scale, 0);
        }

        public void update(float dt) {
            if (gc != null) {
                position.mulAdd(velocity.cpy().sub(gc.getHero().getVel()), scale * scale * dt / 5);
            } else {
                position.mulAdd(velocity, scale * dt / 5);
            }

            if (position.x < -8) {
                position.x = Constants.width + 8;
                position.y = MathUtils.random(-8, Constants.height + 8);
            }
            if (position.x > Constants.width + 8) {
                position.x = -8;
                position.y = MathUtils.random(-8, Constants.height + 8);
            }
            if (position.y < -8) {
                position.y = Constants.height + 8;
                position.x = MathUtils.random(-8, Constants.width + 8);
            }
            if (position.y > Constants.height + 8) {
                position.y = -8;
                position.x = MathUtils.random(-8, Constants.width + 8);
            }
        }
    }

    private final int STAR_COUNT = 1000;
    private GameController gc;
    private Texture textureCosmos;
    private TextureRegion textureStar;
    private Star[] stars;

    public Background(GameController gc) {
        this.gc = gc;
        this.textureCosmos = new Texture("images/space.png");
        this.textureStar = Assets.get().getTextureAtlas().findRegion("star16");
        this.stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star();
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(textureCosmos, 0, 0);
        for (int i = 0; i < stars.length; i++) {
            batch.draw(textureStar,
                stars[i].position.x - 8, stars[i].position.y - 8,
                8, 8,
                16, 16,
                stars[i].scale, stars[i].scale,
                0
            );

            if (MathUtils.random(0, 300) < 1) {
                batch.draw(textureStar,
                    stars[i].position.x - 8, stars[i].position.y - 8,
                    8, 8,
                    16, 16,
                    stars[i].scale * 2, stars[i].scale * 2,
                    0
                );
            }
        }
    }

    public void update(float dt) {
        for (int i = 0; i < stars.length; i++) {
            stars[i].update(dt);
        }
    }

    public void dispose() {
        textureCosmos.dispose();
    }
}

package game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Ball {
    static Texture texture = new Texture("asteroid.png");;
    public Vector2 pos;
    public Vector2 vel;

    private void initBall() {
        int screenNumber = (int) (Math.random() * 8);
        if (screenNumber >= 4) {
            screenNumber++;
        }
        int screenRow = screenNumber / 3 - 1;
        int screenCol = screenNumber % 3 - 1;

        pos = new Vector2(MathUtils.random(0, Constants.width - Constants.ballRadius) + Constants.width * screenCol, MathUtils.random(0, Constants.height - Constants.ballRadius) + Constants.height * screenRow);
        vel = new Vector2(MathUtils.random(-1f * Constants.pixelsPerMeter, 1f * Constants.pixelsPerMeter), MathUtils.random(-1f * Constants.pixelsPerMeter, 1f * Constants.pixelsPerMeter));
    }

    public Ball(int i) {
        initBall();
    }

    public static void handleCollision(Ball a, Ball b) {
        Vector2 centersDifference = a.pos.cpy().sub(b.pos).nor();
        float distance = a.pos.cpy().sub(b.pos).len();
        float pr1 = centersDifference.cpy().dot(a.vel);
        float pr2 = centersDifference.cpy().dot(b.vel);
        a.vel.add(centersDifference.cpy().scl(-1f * pr1 + (1f - Constants.reflectionLoss) * pr2));
        b.vel.add(centersDifference.cpy().scl(-1f * pr2 + (1f - Constants.reflectionLoss) * pr1));
        a.pos.add(centersDifference.cpy().scl((2f * Constants.ballRadius - distance) / 2));
        b.pos.sub(centersDifference.cpy().scl((2f * Constants.ballRadius - distance) / 2));
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, pos.x, pos.y, 2 * Constants.ballRadius, 2 * Constants.ballRadius);
    }

    public void update(float dt) {
        pos.add(vel.cpy().scl(dt * (1 - Constants.airResistance * dt)));
        if (
                pos.x < -Constants.width ||
                pos.x > 2 * Constants.width ||
                pos.y < -Constants.height ||
                pos.y > 2 * Constants.height
        ) {
            initBall();
        }
    }
}

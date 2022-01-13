package ru.gb.star.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import ru.gb.star.pool.Pool;
import ru.gb.star.pool.PoolItem;

public class Benefit extends PoolItem {
    private Vector2 pos = new Vector2();
    private Circle hitBox = new Circle();
    private BenefitType type;

    public BenefitType getType() {
        return type;
    }

    public Circle getHitBox() {
        return hitBox;
    }

    public static float RADIUS = 24f;

    public Vector2 getPos() {
        return pos;
    }

    public Benefit(Pool pool) {
        super(pool);
    }

    public void activate(float x, float y, BenefitType type) {
        pos.set(x, y);
        hitBox.set(x, y, RADIUS);
        this.type = type;
    }
}

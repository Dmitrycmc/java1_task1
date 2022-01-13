package ru.gb.star.game;

import com.badlogic.gdx.math.Vector2;

public interface Collidable {
    Vector2 getPos();
    Vector2 getVel();

    void setPos(Vector2 pos);
    void setVel(Vector2 vel);

    float getMass();
    float getRadius();
}

package ru.gb.star.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class Weapon {
    private GameController gc;
    private String title;
    private float firePeriod;
    private int damage;
    private float bulletSpeed;
    private int maxBullets;
    private int curBullets;

    // x - растояние от центра
    // y угол от основного направления корабля
    // z угол стрельбы
    private Vector3[] slots;

    public float getFirePeriod() {
        return firePeriod;
    }

    public int getDamage() {
        return damage;
    }

    public int getMaxBullets() {
        return maxBullets;
    }

    public int getCurBullets() {
        return curBullets;
    }

    public boolean addWeapons(int val) {
        curBullets = Math.min(maxBullets, curBullets + val);
        return curBullets <= 0;
    }

    public Weapon(GameController gc, String title, float firePeriod, int damage,
                  float bulletSpeed, int maxBullets, Vector3[] slots) {
        this.gc = gc;
        this.title = title;
        this.firePeriod = firePeriod;
        this.damage = damage;
        this.bulletSpeed = bulletSpeed;
        this.maxBullets = maxBullets;
        this.slots = slots;
        this.curBullets = maxBullets;
    }

    public void fire() {
        Hero hero = gc.getHero();
        if (curBullets > 0) {
            curBullets--;
            for (int i = 0; i < slots.length; i++) {
                float x, y, vx, vy;
                x = hero.getPos().x + MathUtils.cosDeg(hero.getAngle() + slots[i].y) * slots[i].x;
                y = hero.getPos().y + MathUtils.sinDeg(hero.getAngle() + slots[i].y) * slots[i].x;
                vx = hero.getVel().x + bulletSpeed * MathUtils.cosDeg(hero.getAngle() + slots[i].z);
                vy = hero.getVel().y + bulletSpeed * MathUtils.sinDeg(hero.getAngle() + slots[i].z);
                gc.getBulletController().spawn(x, y, vx, vy);
            }
        }
    }

    public void powerUp() {
        switch (MathUtils.random(0, 4)) {
            case 0:
                this.bulletSpeed *= 2;
                break;
            case 1:
                this.damage *= 2;
                break;
            case 2:
                this.firePeriod /= 2;
                break;
        }
    }
}

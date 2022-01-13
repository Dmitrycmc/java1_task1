package ru.gb.star.pool;

public class PoolItem {
    Pool pool;

    public PoolItem(Pool pool) {
        this.pool = pool;
    }

    public void deactivate() {
        pool.deactivate(this);
    }
}

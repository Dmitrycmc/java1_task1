package ru.gb.star.pool;

public class PoolItem {
    protected Pool pool;

    public PoolItem(Pool pool) {
        this.pool = pool;
    }

    public void deactivate() {
        pool.deactivate(this);
    }
}

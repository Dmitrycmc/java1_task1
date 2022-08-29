package ru.geekbrains;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentCounter implements Counter {
    private int counter;
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    public int inc() {
        writeLock.lock();
        try {
            counter += 1;
            return counter;
        } finally {
            writeLock.unlock();
        }
    }

    public int dec() {
        writeLock.lock();
        try {
            counter -= 1;
            return counter;
        } finally {
            writeLock.unlock();
        }
    }

    public int get() {
        readLock.lock();
        try {
            return counter;
        } finally {
            readLock.unlock();
        }
    }
}

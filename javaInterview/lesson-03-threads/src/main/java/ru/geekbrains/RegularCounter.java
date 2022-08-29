package ru.geekbrains;

public class RegularCounter implements Counter {
    private int cnt = 0;

    public int inc() {
        return ++cnt;
    }

    public int dec() {
        return --cnt;
    }

    public int get() {
        return cnt;
    }
}

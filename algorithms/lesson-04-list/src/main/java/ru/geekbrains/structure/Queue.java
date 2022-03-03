package ru.geekbrains.structure;

public interface Queue<T> extends Iterable<T> {
    void push(T value);

    T shift();
}

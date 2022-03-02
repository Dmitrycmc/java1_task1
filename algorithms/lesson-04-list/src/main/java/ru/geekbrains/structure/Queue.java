package ru.geekbrains.structure;

public interface Queue<T> {
    void push(T value);

    T shift();
}

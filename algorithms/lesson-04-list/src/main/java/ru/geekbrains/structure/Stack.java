package ru.geekbrains.structure;

public interface Stack<T> extends Iterable<T> {
    void push(T value);

    T pop();
}

package ru.geekbrains.structure;

public interface Stack<T> {
    void push(T value);

    T pop();

    @Override
    String toString();
}

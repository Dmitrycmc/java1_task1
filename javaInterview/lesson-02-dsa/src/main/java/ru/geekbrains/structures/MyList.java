package ru.geekbrains.structures;

public interface MyList<T> {
    void push(T value);

    T pop();

    void unshift(T value);

    T shift();

    int length();

    void clear();

    Object[] toArray();
}

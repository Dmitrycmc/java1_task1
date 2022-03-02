package ru.geekbrains.structure;

public interface Deque<T> extends Stack<T>, Queue<T> {
    void unshift(T value);

    boolean remove(T el);

    int removeAll(T el);

    boolean insertAfter(T after, T el);

    boolean insertBefore(T before, T el);
}

package ru.geekbrains.structure;

public class Queue<T> {
    private Deck<T> deck = new Deck<>();

    public void push(T value) {
        deck.push(value);
    }

    public T shift() {
        return deck.shift();
    }

    @Override
    public String toString() {
        return deck.toString();
    }
}

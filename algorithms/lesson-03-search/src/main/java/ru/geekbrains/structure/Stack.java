package ru.geekbrains.structure;

public class Stack<T> {
    private Deck<T> deck = new Deck<>();

    public void push(T value) {
        deck.push(value);
    }

    public T pop() {
        return deck.pop();
    }

    @Override
    public String toString() {
        return deck.toString();
    }
}

package ru.geekbrains.structure;

public class Deck<T> {
    private class Node {
        T value;
        Node next = null;
        Node prev = null;
    }

    private Node start = null;
    private Node end = null;

    public Deck() {}

    public void push(T value) {
        Node node = new Node();
        node.value = value;

        if (start == null) {
            start = node;
            end = node;
        } else {
            end.next = node;
            node.prev = end;
            end = node;
        }
    }

    public void unshift(T value) {
        Node node = new Node();
        node.value = value;

        if (start == null) {
            start = node;
            end = node;
        } else {
            start.prev = node;
            node.next = start;
            start = node;
        }
    }

    public T pop() {
        if (end == null) {
            return null;
        }
        Node node = end;

        if (node.prev == null) {
            start = end = null;
        } else {
            end = node.prev;
        }
        return node.value;
    }

    public T shift() {
        if (start == null) {
            return null;
        }
        Node node = start;

        if (node.next == null) {
            start = end = null;
        } else {
            start = node.next;
        }
        return node.value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node i = start;

        while (i != null) {
            sb.append(i.value).append("\n");
            i = i.next;
        }

        return sb.toString();
    }
}

package ru.geekbrains.structure;

public class Deck<T> implements Queue<T>, Stack<T> {
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
            node.prev = null;
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
            node.next = null;
        }
        return node.value;
    }

    private void remove(Node node) {
        if (node.prev == null) {
            start = node.next;
        } else {
            node.prev.next = node.next;
        }

        if (node.next == null) {
            end = node.prev;
        } else {
            node.next.prev = node.prev;
        }

        node.prev = node.next = null;
    }

    public boolean remove(T el) {
        Node node = start;

        while (true) {
            if (node == null) {
                return false;
            }

            if (node.value.equals(el)) {
                remove(node);
                return true;
            }

            node = node.next;
        }
    }

    public int removeAll(T el) {
        int counter = 0;
        while (remove(el)) {
            counter++;
        }
        return counter;
    }

    public void insertAfter(Node after, T el) {
        if (after.next == null) {
            push(el);
        } else {
            Node node = new Node();
            node.value = el;
            node.prev = after;
            node.next = after.next;
            after.next = node;
            node.next.prev = node;
        }
    }

    public boolean insertAfter(T after, T el) {
        Node node = start;

        while (true) {
            if (node == null) {
                return false;
            }

            if (node.value.equals(after)) {
                insertAfter(node, el);
                return true;
            }

            node = node.next;
        }

    }

    public void insertBefore(Node before, T el) {
        if (before.prev == null) {
            unshift(el);
        } else {
            Node node = new Node();
            node.value = el;
            node.next = before;
            node.prev = before.prev;
            before.prev = node;
            node.prev.next = node;
        }
    }

    public boolean insertBefore(T before, T el) {
        Node node = start;

        while (true) {
            if (node == null) {
                return false;
            }

            if (node.value.equals(before)) {
                insertBefore(node, el);
                return true;
            }

            node = node.next;
        }

    }

    @Override
    public String toString() {
        if (start == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Node i = start;

        while (true) {
            sb.append(i.value);
            i = i.next;

            if (i == null) {
                return sb.toString();
            }

            sb.append(" <-> ");
        }
    }
}

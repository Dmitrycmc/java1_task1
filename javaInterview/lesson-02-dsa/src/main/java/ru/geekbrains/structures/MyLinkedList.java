package ru.geekbrains.structures;

public class MyLinkedList<T> implements MyList<T> {
    private Node root = null;
    private Node tag = null;

    class Node {
        private T value;
        private Node next;
        private Node prev;

        private Node(T value) {
            this.value = value;
        }
    }

    public void push(T value) {
        Node node = new Node(value);
        if (tag == null) {
            tag = root = node;
        } else {
            tag.next = node;
            node.prev = tag;
            tag = node;
        }
    }

    public T pop() {
        if (tag == null) {
            return null;
        } else {
            Node node = tag;
            T value = tag.value;

            if (tag.prev != null) {
                tag.prev.next = null;
            } else {
                root = null;
            }

            tag = tag.prev;
            node.prev = null;

            return value;
        }
    }

    public void unshift(T value) {
        Node node = new Node(value);
        if (root == null) {
            tag = root = node;
        } else {
            root.prev = node;
            node.next = root;
            root = node;
        }
    }

    public T shift() {
        if (root == null) {
            return null;
        } else {
            Node node = root;
            T value = root.value;

            if (root.next != null) {
                root.next.prev = null;
            } else {
                tag = null;
            }

            root = root.next;
            node.next = null;

            return value;
        }
    }

    public int length() {
        Node head = root;
        int cnt = 0;

        while (head != null) {
            cnt++;
            head = head.next;
        }

        return cnt;
    }

    public void clear() {
        Node head = root;

        while (head != null) {
            Node next = head.next;
            head.prev = null;
            head.next = null;

            head = next;
        }

        root = tag = null;
    }

    public Object[] toArray() {
        Object[] arr = new Object[this.length()];

        Node head = root;
        int cnt = 0;

        while (head != null) {
            arr[cnt++] = head.value;
            head = head.next;
        }

        return arr;
    }

}

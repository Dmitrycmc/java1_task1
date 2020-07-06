package ru.gb.java2.lesson7;

public class OneDirectionalList<T> implements DirectionalList<T> {
    private OneDirectionalNode<T> emptyRootNode = new OneDirectionalNode<>(null, null);

    @Override
    public Node<T> insertToStart(T value) throws Exception {
        return insertAt(value, 0);
    }

    @Override
    public Node<T> insertAt(T value, int index) throws Exception {
        OneDirectionalNode<T> current = emptyRootNode;
        for (int i = 0; i < index; i++) {
            if (current.getNext() == null) {
                throw new Exception();
            }
            current = current.getNext();
        }
        return current.next = new OneDirectionalNode<>(value, current.next);
    }

    @Override
    public boolean remove(T val) {
        return false;
    }

    @Override
    public boolean removeAt(T val) {
        return false;
    }

    @Override
    public Node<T> getFirst() {
        return emptyRootNode.next;
    }

    @Override
    public Node<T> getAt() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        OneDirectionalNode<T> current = emptyRootNode.next;
        if (current != null) {
            sb.append(current.getValue());
            current = current.next;
        }
        while (current != null) {
            sb.append(", ").append(current.getValue());
            current = current.next;
        }
        return sb.append(" ]").toString();
    }

    public class OneDirectionalNode<T> implements Node<T> {
        private T value;
        private OneDirectionalNode<T> next;

        public OneDirectionalNode(T value, OneDirectionalNode<T> next) {
            this.value = value;
            this.next = next;

        }

        @Override
        public T getValue() {
            return value;
        }

        public OneDirectionalNode<T> getNext() {
            return next;
        }
    }
}

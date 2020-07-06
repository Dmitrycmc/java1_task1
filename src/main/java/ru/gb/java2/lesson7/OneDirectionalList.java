package ru.gb.java2.lesson7;

public class OneDirectionalList<T> implements DirectionalList<T> {
    private OneDirectionalNode<T> emptyHeadNode = new OneDirectionalNode<>(null, null);

    @Override
    public Node<T> insertToStart(T value) throws Exception {
        return insertAt(value, 0);
    }

    @Override
    public Node<T> insertAt(T value, int index) throws Exception {
        OneDirectionalNode<T> current = emptyHeadNode;
        for (int i = 0; i < index; i++) {
            if (current.getNext() == null) {
                throw new Exception();
            }
            current = current.getNext();
        }
        return current.next = new OneDirectionalNode<>(value, current.next);
    }

    @Override
    public boolean remove(T value) {
        OneDirectionalNode<T> current = emptyHeadNode;
        while (current.next != null) {
            if (current.next.value.equals(value)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public void removeAt(int index) throws Exception {
        OneDirectionalNode<T> current = emptyHeadNode;
        for (int i = 0; i < index; i++) {
            current = current.next;
            if (current == null) {
                throw new Exception();
            }
        }
        if (current.next == null) {
            throw new Exception();
        }
        current.next = current.next.next;
    }

    @Override
    public Node<T> getFirst() throws Exception {
        return getAt(0);
    }

    @Override
    public Node<T> getAt(int index) throws Exception {
        OneDirectionalNode<T> current = emptyHeadNode;
        for (int i = 0; i < index; i++) {
            current = current.next;
            if (current == null) {
                throw new Exception();
            }
        }
        return current.next;
    }

    @Override
    public int getSize() {
        OneDirectionalNode<T> current = emptyHeadNode;
        int cnt = 0;
        while (current.next != null) {
            cnt++;
            current = current.next;
        }
        return cnt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Length = ").append(getSize()).append(" [ ");
        OneDirectionalNode<T> current = emptyHeadNode;
        if (current.next != null) {
            current = current.next;
            sb.append(current.getValue()).append(" ");
        }
        while (current.next != null) {
            current = current.next;
            sb.append(", ").append(current.getValue()).append(" ");
        }
        return sb.append("]").toString();
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

package ru.gb.java2.lesson7;

public class OneDirectionalList<T> implements DirectionalList<T> {
    private OneDirectionalNode<T> emptyHeadNode;
    private int size;

    public OneDirectionalList(T[] arr) {
        emptyHeadNode = new OneDirectionalNode<>(null, null);
        OneDirectionalNode<T> current = emptyHeadNode;
        for (int i = 0; i < arr.length; i++) {
            current.next = new OneDirectionalNode<>(arr[i], null);
            current = current.next;
        }
        size = arr.length;
    }

    public OneDirectionalList() {
        emptyHeadNode = new OneDirectionalNode<>(null, null);
        size = 0;
    }

    @Override
    public OneDirectionalNode insertToStart(T value) throws Exception {
        return insertAt(value, 0);
    }

    @Override
    public OneDirectionalNode insertAt(T value, int index) throws Exception {
        OneDirectionalNode<T> current = emptyHeadNode;
        for (int i = 0; i < index; i++) {
            if (current.getNext() == null) {
                throw new Exception();
            }
            current = current.getNext();
        }
        size++;
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
        size--;
        current.next = current.next.next;
    }

    @Override
    public OneDirectionalNode getFirst() throws Exception {
        return getAt(0);
    }

    @Override
    public OneDirectionalNode getAt(int index) throws Exception {
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
        return size;
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

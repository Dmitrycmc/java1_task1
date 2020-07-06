package ru.gb.java2.lesson7;

public class TwoDirectionalList<T> implements DirectionalList<T> {
    private TwoDirectionalNode<T> emptyHeadNode;
    private TwoDirectionalNode<T> emptyTagNode;
    private int size = 0;

    public TwoDirectionalList() {
        emptyHeadNode = new TwoDirectionalNode<>(null, null, null);
        emptyTagNode = new TwoDirectionalNode<>(null, null, null);
        emptyHeadNode.next = emptyTagNode;
        emptyTagNode.prev = emptyHeadNode;
    }

    @Override
    public Node<T> insertToStart(T value) throws Exception {
        return insertAt(value, 0);
    }

    public Node<T> insertToEnd(T value) throws Exception {
        return insertAt(value, getSize());
    }

    @Override
    public Node<T> insertAt(T value, int index) throws Exception {
        if (index < size / 2) {
            //straight by pass
            TwoDirectionalNode<T> current = emptyHeadNode;

            for (int i = 0; i < index; i++) {
                if (current.getNext() == null) {
                    throw new Exception();
                }
                current = current.getNext();
            }
            TwoDirectionalNode<T> newNode = new TwoDirectionalNode<>(value, current, current.next);
            current.next.prev = newNode;
            current.next = newNode;
            size++;
            return newNode;
        } else {
            //revers by pass
            TwoDirectionalNode<T> current = emptyTagNode;

            for (int i = size; i > index; i--) {
                if (current.prev == null) {
                    throw new Exception();
                }
                current = current.prev;
            }
            TwoDirectionalNode<T> newNode = new TwoDirectionalNode<>(value, current.prev, current);
            current.prev.next = newNode;
            current.prev = newNode;
            size++;
            return newNode;
        }
    }

    @Override
    public boolean remove(T value) {
        TwoDirectionalNode<T> current = emptyHeadNode;
        while (current.next != emptyTagNode) {
            if (current.next.value.equals(value)) {
                current.next = current.next.next;
                current.next.prev = current;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public void removeAt(int index) throws Exception {
        if (index < size / 2) {
            //straight by pass
            TwoDirectionalNode<T> current = emptyHeadNode;
            for (int i = 0; i < index; i++) {
                current = current.next;
                if (current == emptyTagNode) {
                    throw new Exception();
                }
            }
            if (current.next == emptyTagNode) {
                throw new Exception();
            }
            current.next = current.next.next;
            current.next.prev = current;
            size--;
        } else {
            //revers by pass
            TwoDirectionalNode<T> current = emptyTagNode;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
                if (current == emptyHeadNode) {
                    throw new Exception();
                }
            }
            if (current.prev == emptyHeadNode) {
                throw new Exception();
            }
            current.prev = current.prev.prev;
            current.prev.next = current;
            size--;
        }
    }

    @Override
    public Node<T> getFirst() throws Exception {
        return getAt(0);
    }

    public Node<T> getLast() throws Exception {
        return getAt(getSize() - 1);
    }

    @Override
    public Node<T> getAt(int index) throws Exception {
        if (index < size / 2) {
            //straight by pass
            TwoDirectionalNode<T> current = emptyHeadNode;
            for (int i = 0; i < index; i++) {
                current = current.next;
                if (current == emptyTagNode) {
                    throw new Exception();
                }
            }
            if (current.next == emptyTagNode) {
                throw new Exception();
            }
            return current.next;
        } else {
            //revers by pass
            TwoDirectionalNode<T> current = emptyTagNode;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
                if (current == emptyHeadNode) {
                    throw new Exception();
                }
            }
            if (current.prev == emptyHeadNode) {
                throw new Exception();
            }
            return current.prev;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Length = ").append(getSize()).append(" [ ");
        TwoDirectionalNode<T> current = emptyHeadNode;
        if (current.next != emptyTagNode) {
            current = current.next;
            sb.append(current.getValue()).append(" ");
        }
        while (current.next != emptyTagNode) {
            current = current.next;
            sb.append(", ").append(current.getValue()).append(" ");
        }
        return sb.append("]").toString();
    }

    public class TwoDirectionalNode<T> implements Node<T> {
        private T value;
        private TwoDirectionalNode<T> next;
        private TwoDirectionalNode<T> prev;

        public TwoDirectionalNode(T value, TwoDirectionalNode<T> prev, TwoDirectionalNode<T> next) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public T getValue() {
            return value;
        }

        public TwoDirectionalNode<T> getNext() {
            return next;
        }
        public TwoDirectionalNode<T> getPrev() {
            return prev;
        }
    }
}

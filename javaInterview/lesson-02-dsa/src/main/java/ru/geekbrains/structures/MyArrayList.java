package ru.geekbrains.structures;

public class MyArrayList<T> implements MyList<T> {
    private int capacity = 16;
    private int length = 0;
    private Object[] data = new Object[capacity];

    private void reallocate() {
        Object[] oldData = data;
        data = new Object[capacity *= 2];
        for (int i = 0; i < length; i++) {
            data[i] = oldData[i];
        }
    }

    public void push(T value) {
        data[length++] = value;

        if (length == capacity) {
            this.reallocate();
        }
    }

    public T pop() {
        if (length == 0) {
            return null;
        }

        return (T)data[length-- - 1];
    }

    public void unshift(T value) {
        if (length >= 0) {
            System.arraycopy(data, 0, data, 1, length);
        }
        data[0] = value;
        length++;

        if (length == capacity) {
            this.reallocate();
        }
    }

    public T shift() {
        if (length == 0) {
            return null;
        }
        T value = (T)data[0];

        for (int i = 0; i < length - 1 ; i++) {
            data[i] = data[i + 1];
        }

        length--;

        return value;
    }

    public int length() {
        return length;
    }

    public void clear() {
        data = new Object[capacity];
        length = 0;
    }

    public Object[] toArray() {
        Object[] result = new Object[length];

        System.arraycopy(data, 0, result, 0, length);

        return result;
    }
}

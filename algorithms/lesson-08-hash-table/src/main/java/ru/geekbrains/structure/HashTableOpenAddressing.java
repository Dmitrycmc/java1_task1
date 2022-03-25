package ru.geekbrains.structure;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class HashTableOpenAddressing<K, V> implements Table<K, V> {
    private final Entry<K, V> EMPTY = new Entry<>(null, null);

    Entry<K, V>[] data;
    int size = 0;

    public HashTableOpenAddressing(int capacity) {
        data = new Entry[2 * capacity];
    }

    public HashTableOpenAddressing() {
        this(50);
    }

    public void recreate() {
        Entry<K, V>[] oldData = data;
        data = new Entry[2 * oldData.length];
        size = 0;

        for (int i = 0; i < oldData.length; i++) {
            if (oldData[i] != null && oldData[i] != EMPTY)
            put(oldData[i].getKey(), oldData[i].getValue()); //
        }
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int hashCode = key.hashCode();
        int i = Math.abs(hashCode % capacity());

        while (data[i] != null && data[i] != EMPTY && !data[i].getKey().equals(key)) {
            i = (i + 1) % capacity();
        }

        return data[i] != null && data[i] != EMPTY && data[i].getKey().equals(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < capacity(); i++) {
            if (data[i] != null && data[i] != EMPTY && data[i].getValue().equals(value)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public V get(Object key) {
        int hashCode = key.hashCode();
        int i = Math.abs(hashCode % capacity());

        while (data[i] != null && data[i] != EMPTY && !data[i].getKey().equals(key)) {
            i = (i + 1) % capacity();
        }

        if (data[i] != null && data[i] != EMPTY) {
            return data[i].getValue();
        } else {
            return null;
        }
    }

    @Override
    public V put(K key, V value) {
        if ((float) size() / capacity() > 0.75 / 2) {
            recreate();
        }

        int hashCode = key.hashCode();
        int i = Math.abs(hashCode % capacity());

        while (data[i] != null && data[i] != EMPTY && !data[i].getKey().equals(key)) {
            i = (i + 1) % capacity();
        }

        if (data[i] != null && data[i] != EMPTY) {
            data[i].setValue(value);
        } else {
            data[i] = new Entry<>(key, value);
            size++;
        }

        return value;
    }

    @Override
    public boolean delete(K key) {
        int hashCode = key.hashCode();
        int i = Math.abs(hashCode % capacity());

        while (data[i] != null && data[i] != EMPTY && !data[i].getKey().equals(key)) {
            i = (i + 1) % capacity();
        }

        if (data[i] != null && data[i] != EMPTY) {
            data[i] = null;
            size--;
            return true;
        } else {
            data[i] = EMPTY;
            return false;
        }
    }

    public void clear() {
        Arrays.fill(data, null);
        size = 0;
    }

    @Override
    public Collection<K> keySet() {
        return Arrays.stream(data).map(Entry::getKey).collect(Collectors.toList());
    }

    @Override
    public Collection<V> values() {
        return Arrays.stream(data).map(Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public List<Entry<K, V>> entrySet() {
        return Arrays.stream(data).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("HashTableOpenAddressing ").append(size).append("/").append(capacity()).append(" {").append("\n");

        for (int i = 0; i < capacity(); i++) {
            sb.append(i).append(": ");

            if (data[i] == null) {
                sb.append("null");
            } else if (data[i] == EMPTY) {
                sb.append("empty");
            } else {
                sb.append(data[i].getKey()).append(" - ").append(data[i].getValue());
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}

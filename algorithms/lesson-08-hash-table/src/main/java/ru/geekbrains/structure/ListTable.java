package ru.geekbrains.structure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ListTable<K, V> implements Table<K, V> {
    List<Entry<K, V>> data;

    public ListTable(int capacity) {
        data = new ArrayList<>(capacity);
    }

    public ListTable() {
        this(50);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return data.stream().anyMatch(e -> e.getKey().equals(key));
    }

    @Override
    public boolean containsValue(Object value) {
        return data.stream().anyMatch(e -> e.getValue().equals(value));
    }

    @Override
    public V get(Object key) {
        return data.stream().filter(e -> e.getKey().equals(key)).findFirst().map(Entry::getValue).orElse(null);
    }

    @Override
    public V put(K key, V value) {
        Optional<Entry<K, V>> entry = data.stream().filter(e -> e.getKey().equals(key)).findFirst();

        if (entry.isPresent()) {
            entry.get().setValue(value);
        } else {
            data.add(new Entry<>(key, value));
        }

        return value;
    }

    @Override
    public boolean delete(K key) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getKey().equals(key)) {
                data.remove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public Collection<K> keySet() {
        return data.stream().map(Entry::getKey).collect(Collectors.toList());
    }

    @Override
    public Collection<V> values() {
        return data.stream().map(Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public Collection<Entry<K, V>> entrySet() {
        return new ArrayList<>(data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("HashTableOpenAddressing ").append(data.size()).append(" {").append("\n");

        for (int i = 0; i < data.size(); i++) {
            sb.append(i).append(": ");

            if (data.get(i) == null) {
                sb.append("null");
            } else {
                sb.append(data.get(i).getKey()).append(" - ").append(data.get(i).getValue());
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}

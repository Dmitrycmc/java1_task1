package ru.geekbrains.structure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class JavaTable<K, V> implements Table<K, V> {
    Map<K, V> data;


    public JavaTable(int capacity) {
        data = new HashMap<>(capacity);
    }

    public JavaTable() {
        this(50);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return data.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return data.get(key);
    }

    @Override
    public V put(K key, V value) {
        return data.put(key, value);
    }

    @Override
    public boolean delete(K key) {
        return data.remove(key) != null;
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public Collection<K> keySet() {
        return data.keySet();
    }

    @Override
    public Collection<V> values() {
        return data.values();
    }

    @Override
    public Collection<Entry<K, V>> entrySet() {
        return null;
    }
}

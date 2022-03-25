package ru.geekbrains.structure;

import java.util.Collection;

public interface Table<K, V> {

    class Entry<K, V> {
        public K key;
        public V value;

        public Entry() {
        }

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    int size();

    boolean isEmpty();

    boolean containsKey(Object key);

    boolean containsValue(Object value);

    V get(Object key);

    V put(K key, V value);

    boolean delete(K key);

    void clear();

    Collection<K> keySet();

    Collection<V> values();

    Collection<Entry<K, V>> entrySet();
}

package ru.geekbrains.structure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HashTableСhaining<K, V> implements Table<K, V> {
    List<Entry<K, V>>[] data;
    int size = 0;

    public HashTableСhaining(int capacity) {
        data = new List[capacity];
    }

    public HashTableСhaining() {
        this(50);
    }

    public void recreate() {
        List<Entry<K, V>>[] oldData = data;
        data = new List[2 * oldData.length];
        size = 0;

        for (List<Entry<K, V>> list: oldData) {
            if (list != null) {
                list.forEach(e -> put(e.getKey(), e.getValue())); //
            }
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
        List<Entry<K, V>> list = data[i];

        if (list == null) {
            return false;
        }

        return list.stream().anyMatch(e -> e.getKey().equals(key));
    }

    @Override
    public boolean containsValue(Object value) {
        for (List<Entry<K, V>> list: data) {
            if (list != null && list.stream().anyMatch(e -> e.getValue().equals(value))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public V get(Object key) {
        int hashCode = key.hashCode();
        int i = Math.abs(hashCode % capacity());
        List<Entry<K, V>> list = data[i];

        if (list == null) {
            return null;
        }

        return list.stream().filter(e -> e.getKey().equals(key)).findFirst().map(Entry::getValue).orElse(null);
    }

    @Override
    public V put(K key, V value) {
        if ((float) size() / capacity() > 0.75) {
            recreate();
        }

        int hashCode = key.hashCode();
        int i = Math.abs(hashCode % capacity());

        if (data[i] == null) {
            data[i] = new ArrayList<>();
            data[i].add(new Entry<>(key, value));
            size++;
            return value;
        }

        Optional<Entry<K, V>> entry = data[i].stream().filter(e -> e.getKey().equals(key)).findFirst();

        if (entry.isPresent()) {
            entry.get().setValue(value);
        } else {
            data[i].add(new Entry<>(key, value));
            size++;
        }

        return value;
    }

    @Override
    public boolean delete(K key) {
        int hashCode = key.hashCode();
        int i = Math.abs(hashCode % capacity());

        if (data[i] == null) {
            return false;
        }

        for (int j = 0; j < data[i].size(); j++) {
            if (data[i].get(j).getKey().equals(key)) {
                data[i].remove(j);
                size--;
                return true;
            }
        }

        return false;
    }

    public void clear() {
        for (List<Entry<K, V>> list: data) {
            if (list != null) {
                list.clear();
            }
        }
        size = 0;
    }

    @Override
    public Collection<K> keySet() {
        List<K> set = new ArrayList<>();
        for (List<Entry<K, V>> list: data) {
            if (list != null) {
                set.addAll(list.stream().map(Entry::getKey).collect(Collectors.toList()));
            }
        }

        return set;
    }

    @Override
    public Collection<V> values() {
        List<V> set = new ArrayList<>();
        for (List<Entry<K, V>> list: data) {
            if (list != null) {
                set.addAll(list.stream().map(Entry::getValue).collect(Collectors.toList()));
            }
        }

        return set;
    }

    @Override
    public List<Entry<K, V>> entrySet() {
        List<Entry<K, V>> set = new ArrayList<>();
        for (List<Entry<K, V>> list: data) {
            if (list != null) {
                set.addAll(list);
            }
        }

        return set;
    }

    public int getMaxDepth() {
        int result = 0;
        for (List<Entry<K, V>> list: data) {
            if (list != null && list.size() > result) {
                result = list.size();
            }
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("HashTableChaining ").append(size).append("/").append(capacity()).append(" {").append("\n");

        for (List<Entry<K, V>> list: data) {
            if (list == null) {
                sb.append("0: ");
            } else {
                sb.append(list.size()).append(": ");
                for (int i = 0; i < list.size(); i++) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(list.get(i).getKey()).append(" - ").append(list.get(i).getValue());
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}

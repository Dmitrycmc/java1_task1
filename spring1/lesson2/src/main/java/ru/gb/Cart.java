package ru.gb;

import java.util.Map;

public interface Cart {
    public void add(int id);

    public void sub(int id);

    public Map<Integer, Integer> getProducts();
}

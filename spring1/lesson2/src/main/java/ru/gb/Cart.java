package ru.gb;

import java.util.Map;

public interface Cart {
    void add(int id);

    void sub(int id);

    Map<Integer, Integer> getProducts();
}

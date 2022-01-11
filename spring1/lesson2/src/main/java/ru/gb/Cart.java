package ru.gb;

import java.util.Map;

public interface Cart {
    void add(int id);

    void sub(int id)  throws Exception ;

    Map<Integer, Integer> getProducts();
}

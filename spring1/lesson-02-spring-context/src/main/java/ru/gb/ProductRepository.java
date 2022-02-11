package ru.gb;

import java.util.List;

public interface ProductRepository {
    void add(Product product);

    List<Product> getAll();

    Product getById(int id);
}

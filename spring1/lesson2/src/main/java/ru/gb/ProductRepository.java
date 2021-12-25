package ru.gb;

import java.util.List;

public interface ProductRepository {
    public void add(Product product);

    public List<Product> getAll();

    public Product getById(int id);
}

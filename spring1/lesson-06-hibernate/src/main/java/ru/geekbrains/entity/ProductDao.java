package ru.geekbrains.entity;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    List<Product> findAll();

    Optional<Product> findById(long id);

    void save(Product product);

    void delete(long id);
}

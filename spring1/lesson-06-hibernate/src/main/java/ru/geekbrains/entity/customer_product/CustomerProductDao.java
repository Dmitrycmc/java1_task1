package ru.geekbrains.entity.customer_product;

import java.util.List;
import java.util.Optional;

public interface CustomerProductDao {

    List<CustomerProduct> findAll();

    Optional<CustomerProduct> findById(long id);

    void save(CustomerProduct product);

    void delete(long id);
}

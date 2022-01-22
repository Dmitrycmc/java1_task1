package ru.geekbrains.entity.product;

import ru.geekbrains.entity.customer.Customer;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    List<Product> findAll();

    Optional<Product> findById(long id);

    void save(Product product);

    void delete(long id);

    List<Customer> getProductCustomers(Product product);
}

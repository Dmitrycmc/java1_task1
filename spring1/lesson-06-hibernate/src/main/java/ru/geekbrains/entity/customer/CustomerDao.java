package ru.geekbrains.entity.customer;

import ru.geekbrains.entity.product.Product;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {

    List<Customer> findAll();

    Optional<Customer> findById(long id);

    void save(Customer product);

    void delete(long id);

    void savePurchases(Customer customer, Product[] products);

    List<Product> getCustomerProducts(Customer customer);
}

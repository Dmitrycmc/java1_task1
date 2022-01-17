package ru.geekbrains.entity.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {

    List<Customer> findAll();

    Optional<Customer> findById(long id);

    void save(Customer product);

    void delete(long id);
}

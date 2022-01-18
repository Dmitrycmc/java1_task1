package ru.geekbrains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.entity.customer.Customer;
import ru.geekbrains.entity.customer.CustomerDao;
import ru.geekbrains.entity.product.Product;
import ru.geekbrains.entity.product.ProductDao;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerDao customerDao;
    private final ProductDao productDao;

    @Autowired
    public CustomerService(CustomerDao customerDao, ProductDao productDao) {
        this.customerDao = customerDao;
        this.productDao = productDao;
    }

    public void go() {
        Optional<Customer> me = customerDao.findById(1);

        if (me.isPresent()) {
            System.out.println(customerDao.getCustomerProducts(me.get()));
        }

        Optional<Product> bread = productDao.findById(1);

        if (me.isPresent()) {
            System.out.println(productDao.getProductCustomers(bread.get()));
        }
    }
}

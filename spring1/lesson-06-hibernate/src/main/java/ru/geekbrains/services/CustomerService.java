package ru.geekbrains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.entity.customer.Customer;
import ru.geekbrains.entity.customer.CustomerDao;
import ru.geekbrains.entity.product.Product;
import ru.geekbrains.entity.product.ProductDao;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerDao customerDao;
    private final ProductDao productDao;

    @Autowired
    public CustomerService(CustomerDao customerDao, ProductDao productDao) {
        this.customerDao = customerDao;
        this.productDao = productDao;
    }

    public void start() {
        List<Customer> customerList = customerDao.findAll();
        List<Product> productList = productDao.findAll();

        customerDao.savePurchases(customerList.get(0), productList.toArray(new Product[0]));

        productList.get(0).setPrice(productList.get(0).getPrice().multiply(new BigDecimal(2)));
        productDao.save(productList.get(0));

        customerDao.savePurchases(customerList.get(0), productList.subList(0, 1).toArray(new Product[0]));

        System.out.println(customerDao.getCustomerProducts(customerList.get(0)));

        System.out.println(productDao.getProductCustomers(productList.get(0)));
    }
}

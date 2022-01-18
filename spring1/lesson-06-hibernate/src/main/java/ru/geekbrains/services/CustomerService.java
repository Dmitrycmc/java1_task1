package ru.geekbrains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.entity.customer.Customer;
import ru.geekbrains.entity.customer.CustomerDao;
import ru.geekbrains.entity.product.Product;
import ru.geekbrains.entity.product.ProductDao;

import java.math.BigDecimal;

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
        Product bread = new Product("Хлеб", new BigDecimal(30));
        Product milk = new Product("Молоко", new BigDecimal(70));
        Product cake = new Product("Торт", "Вкусный торт", new BigDecimal(300));
        productDao.save(bread);
        productDao.save(milk);
        productDao.save(cake);

        Customer me = new Customer("Me");
        customerDao.save(me);

        customerDao.savePurchases(me, new Product[]{milk, bread});
    }
}

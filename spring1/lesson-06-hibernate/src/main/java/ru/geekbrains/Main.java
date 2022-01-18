package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.customer.Customer;
import ru.geekbrains.entity.customer.CustomerDao;
import ru.geekbrains.entity.customer.CustomerDaoImpl;
import ru.geekbrains.entity.product.Product;
import ru.geekbrains.entity.product.ProductDao;
import ru.geekbrains.entity.product.ProductDaoImpl;

import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();


        ProductDao productDao = new ProductDaoImpl(emFactory);
        CustomerDao customerDao = new CustomerDaoImpl(emFactory);

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

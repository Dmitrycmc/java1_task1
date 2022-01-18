package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.entity.customer.Customer;
import ru.geekbrains.entity.customer.CustomerDao;
import ru.geekbrains.entity.customer.CustomerDaoImpl;
import ru.geekbrains.entity.product.Product;
import ru.geekbrains.entity.product.ProductDao;
import ru.geekbrains.entity.product.ProductDaoImpl;
import ru.geekbrains.services.CustomerService;

import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        CustomerService customerService = context.getBean("customerService", CustomerService.class);

        customerService.go();
    }
}

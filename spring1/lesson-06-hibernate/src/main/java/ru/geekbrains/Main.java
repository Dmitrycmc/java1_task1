package ru.geekbrains;

import org.hibernate.cfg.Configuration;
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

        productDao.save(new Product("Хлеб", new BigDecimal(30)));
        productDao.save(new Product("Молоко", new BigDecimal(70)));
        productDao.save(new Product("Торт", "Вкусный торт", new BigDecimal(300)));

        if (productDao.findById(1).isPresent()) {
            Product product = productDao.findById(1).get();
            product.setDescription("1st product");
            productDao.save(product);
        }

        productDao.delete(2);

        System.out.println(productDao.findAll());
    }
}

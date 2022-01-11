package ru.gb;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepositoryImpl implements ProductRepository {
    private final ArrayList<Product> products;

    public ProductRepositoryImpl() {
        products = new ArrayList<>();
        Product product1 = new Product("Хлеб", 30);
        Product product2 = new Product("Молоко", 60);
        Product product3 = new Product("Сыр", 150);
        Product product4 = new Product("Торт", 350);
        Product product5 = new Product("Мороженное", 500);

        add(product1);
        add(product2);
        add(product3);
        add(product4);
        add(product5);
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product getById(int id) {
        for (Product product: products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}

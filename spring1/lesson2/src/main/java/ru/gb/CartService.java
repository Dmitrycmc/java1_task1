package ru.gb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@Scope("prototype")
public class CartService {
    ProductRepository productRepository;
    Cart cart;

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public Cart getCart() {
        return cart;
    }

    private void checkProductExist(int id) {
        if (productRepository.getById(id) == null) {
            throw new IllegalArgumentException("Product is not exist");
        }
    }

    public void add(int id) {
        checkProductExist(id);
        cart.add(Math.abs(id));
    }

    public void sub(int id) throws Exception {
        checkProductExist(id);
        cart.sub(Math.abs(id));
    }

    @Autowired
    public CartService(ProductRepository productRepository, Cart cart) {
        this.productRepository = productRepository;
        this.cart = cart;
    }
}

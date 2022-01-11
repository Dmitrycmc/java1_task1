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

    public void add(int id) {
        cart.add(Math.abs(id));
    }

    public void sub(int id) throws Exception {
        cart.sub(Math.abs(id));
    }

    @Autowired
    public CartService(ProductRepository productRepository, Cart cart) {
        this.productRepository = productRepository;
        this.cart = cart;
    }
}

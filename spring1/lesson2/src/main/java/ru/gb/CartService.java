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

    @Autowired
    public CartService(ProductRepository productRepository, Cart cart) {
        this.productRepository = productRepository;
        this.cart = cart;
    }

    public void start() {
        System.out.println(productRepository.getAll().stream());
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите id товара, который хотите добавить в корзину, если хотите убрать - введите со знаком минус:");
            int id = scanner.nextInt();
            if (id > 0) {
                cart.add(Math.abs(id));
            } else {
                cart.sub(Math.abs(id));
            }
            System.out.println(cart.getProducts());
        }

    }
}

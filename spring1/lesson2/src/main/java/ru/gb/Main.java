package ru.gb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        CartService cartService = context.getBean("cartService", CartService.class);

        System.out.println(cartService.getProductRepository().getAll());
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Введите id товара, который хотите добавить в корзину, если хотите убрать - введите со знаком минус:");
                String command = scanner.nextLine();
                int numberCommand = Integer.parseInt(command);
                int id = Math.abs(numberCommand);
                if (cartService.getProductRepository().getById(id) == null) {
                    throw new Exception("Not exist");
                }
                int sign = (int)Math.signum(numberCommand);
                if (sign > 0) {
                    cartService.add(id);
                } else {
                    cartService.sub(id);
                }
                System.out.println(cartService.getCart().getProducts());
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}

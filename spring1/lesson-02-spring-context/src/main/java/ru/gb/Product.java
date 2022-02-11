package ru.gb;

public class Product {
    private static int counter = 1;
    private int id;
    private String name;
    private int price;

    public Product(String name, int price) {
        this.id = counter++;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String toString() {
        return id + ". " + name + " (" + price + " ₽)";
    }
}

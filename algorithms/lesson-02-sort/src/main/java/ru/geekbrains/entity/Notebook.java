package ru.geekbrains.entity;

import ru.geekbrains.utils.MathUtils;

public class Notebook implements Comparable<Notebook> {
    private int price;
    private int ram;
    private Brand brand;

    public enum Brand {
        Lenuvo,
        Asos,
        MacNote,
        Eser,
        Xamiou
    }

    @Override
    public int compareTo(Notebook notebook) {
        return hashCode() - notebook.hashCode();
    }

    public Notebook(int price, int ram, Brand brand) {
        this.price = price;
        this.ram = ram;
        this.brand = brand;
    }

    public static Notebook generateRandom() {
        int brandIndex = MathUtils.randomInt(Brand.values().length - 1);
        Brand brand = Brand.values()[brandIndex];
        int price = MathUtils.randomInt(500, 2000, 50);
        int ram = MathUtils.randomInt(4, 24, 4);

        return new Notebook(price, ram, brand);
    }

    public static Notebook[] generateRandomArray(int n) {
        Notebook[] array = new Notebook[n];

        for (int i = 0; i < n; i++) {
            array[i] = Notebook.generateRandom();
        }

        return array;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "price=" + price +
                ", ram=" + ram +
                ", brand='" + brand + '\'' +
                ", hashCode=" + hashCode() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notebook notebook = (Notebook) o;
        return price == notebook.price && ram == notebook.ram && brand == notebook.brand;
    }

    @Override
    public int hashCode() {
        return (price - 500) / 50 * 6 * 5 + (ram - 4) / 4 * 5 + Brand.valueOf(brand.toString()).ordinal();
    }
}

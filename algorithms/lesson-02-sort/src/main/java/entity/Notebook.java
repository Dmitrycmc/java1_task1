package entity;

import utils.MathUtils;

public class Notebook implements Comparable {
    private int price;
    private int ram;
    private Brand brand;

    public static boolean isSorted(Notebook[] notebooks) {
        for (int i = 0; i < notebooks.length - 1; i++) {
            if (notebooks[i].compareTo(notebooks[i + 1]) > 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int compareTo(Object o) {
        Notebook notebook = (Notebook) o;
        if (price != notebook.price) {
            return price - notebook.price;
        }
        if (ram != notebook.ram) {
            return ram - notebook.ram;
        }
        if (brand != notebook.brand) {
            return Brand.valueOf(notebook.brand.toString()).ordinal()
                    - Brand.valueOf(brand.toString()).ordinal();
        }
        return 0;
    }

    public enum Brand {
        Lenuvo,
        Asos,
        MacNote,
        Eser,
        Xamiou
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
                '}';
    }
}

package entity;

import utils.MathUtils;

public class Notebook {
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

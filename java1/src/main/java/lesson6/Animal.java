package lesson6;

import java.util.Random;

public class Animal {
    private static int counter = 0;

    private String name;
    private int runLimit;
    private int swimLimit;
    private double jumpHeight;

    Animal(String name, int runLimit, int swimLimit, double jumpHeight) {
        this.name = name;

        Random random = new Random();

        this.runLimit = (int) (runLimit * (1 + random.nextGaussian() / 4));
        this.swimLimit = (int) (swimLimit * (1 + random.nextGaussian() / 4));
        this.jumpHeight = jumpHeight * (1 + random.nextGaussian() / 4);

        counter++;
    }

    void run(int distance) {
        if (distance <= runLimit) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не смог пробежать " + distance + " м., т.к. пробегает не более " + runLimit + " м.");
        }
    }

    void swim(int distance) {
        if (distance <= swimLimit) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не смог проплыть " + distance + " м., т.к. проплывает не более " + swimLimit + " м.");
        }
    }

    void jump(double height) {
        if (height <= jumpHeight) {
            System.out.println(name + " прыгнул на " + height + " м.");
        } else {
            System.out.println(name + " не смог прыгнуть " + height + " м., т.к. прыгает не более, чем на " + jumpHeight + " м.");
        }
    }

    void info() {
        System.out.println(name);
    }

    static void printAmount() {
        System.out.println("Создано животных: " + counter);
    }
}


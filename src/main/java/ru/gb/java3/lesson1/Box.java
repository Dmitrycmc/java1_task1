package ru.gb.java3.lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Box<T extends Fruit> {
    private ArrayList<T> fruits;

    Box() {
        this.fruits = new ArrayList<>();
    }

    void add(T newFruit) {
        fruits.add(newFruit);
    }

    void add(List<T> newFruits) {
        fruits.addAll(newFruits);
    }

    void add(T[] newFruits) {
        add(Arrays.asList(newFruits));
    }

    float getWeight() {
        float sum = 0;
        for (T fruit : fruits) {
            sum += fruit.getWeight();
        }
        return sum;
    }

    boolean compare(Box<? extends Fruit> anotherBox) {
        return getWeight() == anotherBox.getWeight();
    }

    void moveTo(Box<T> anotherBox) {
        anotherBox.add(fruits);
        this.fruits.clear();
    }
}

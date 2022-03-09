package ru.geekbrains.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Написать программу «Задача о рюкзаке» с помощью рекурсии.
public class Thief {
    public static class Item {
        String name;
        int weight;
        int price;

        public Item(String name, int weight, int price) {
            this.name = name;
            this.weight = weight;
            this.price = price;
        }

        public double value() {
            return (double) price / weight;
        }

        @Override
        public String toString() {
            return "Item " + name + "{" +
                    "weight=" + (double) weight / 1000 + " kg" +
                    ", price=$" + (double) price / 100 +
                    ", value=" + 10 * value() + " $/kg" +
                    '}';
        }
    }

    private static void rec(List<Item> itemList, boolean[] drawn, int i, int bagMaxWeight, boolean[] best) {
        int currentWeight = 0;
        for (int j = 0; j < i; j++) {
            if (drawn[j]) {
                currentWeight += itemList.get(j).weight;
            }
        }
        if (currentWeight > bagMaxWeight) {
            return;
        }

        if (i != itemList.size()) {
            drawn[i] = false;
            rec(itemList, drawn, i + 1, bagMaxWeight, best);
            drawn[i] = true;
            rec(itemList, drawn, i + 1, bagMaxWeight, best);
            return;
        }

        int currentPrice = 0;
        int bestPrice = 0;
        for (int j = 0; j < i; j++) {
            if (drawn[j]) {
                currentPrice += itemList.get(j).price;
            }
            if (best[j]) {
                bestPrice += itemList.get(j).price;
            }
        }
        if (currentPrice > bestPrice) {
            System.arraycopy(drawn, 0, best, 0, i);
        }
    }

    public static List<Item> resolve(List<Item> itemList, int bagMaxWeight) {
        List<Item> result = new ArrayList<>();
        boolean[] drawn = new boolean[itemList.size()];
        boolean[] best = new boolean[itemList.size()];

        rec(itemList, drawn, 0, bagMaxWeight, best);

        for (int i = 0; i < itemList.size(); i++) {
            if (best[i]) {
                result.add(itemList.get(i));
            }
        }

        return result;
    }

}

package ru.gb.java3.lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class MainClass {
    public static void main(String[] args) {
        Integer[] arr = new Integer[] {0, 1, 2, 3, 4};
        task1(arr, 2,3);
        System.out.println(task2(arr));
        task3();
    }

    /*
        1. Написать метод, который меняет два элемента массива местами.
            (массив может быть любого ссылочного типа);
    */
    private static <T> void task1(T[] arr, int index1, int index2) {
        T buffer = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = buffer;
    }

    /*
        2. Написать метод, который преобразует массив в ArrayList;
    */
    private static <T> ArrayList<T> task2(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }

    /*
        3. Большая задача:

        a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)

        b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта,
        поэтому в одну коробку нельзя сложить и яблоки, и апельсины;

        c. Для хранения фруктов внутри коробки можете использовать ArrayList;

        d. Сделать метод getWeight() который высчитывает вес коробки,
        зная количество фруктов и вес одного фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);

        e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
        которую подадут в compare в качестве параметра, true - если их веса равны,
        false в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);

        f. Написать метод, который позволяет пересыпать фрукты из текущей коробки
        в другую коробку(помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами),
        соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;

        g. Не забываем про метод добавления фрукта в коробку.
    */
    private static void task3() {
        Orange orange = new Orange();
        Apple[] apples = new Apple[5];
        for (int i = 0; i < apples.length; i++) {
            apples[i] = new Apple();
        }

        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        appleBox1.moveTo(appleBox2);
        System.out.println(appleBox1.getWeight());
        System.out.println(appleBox2.getWeight());

        System.out.println(appleBox1.compare(appleBox2));
        appleBox1.add(apples);
        System.out.println(appleBox1.getWeight());
        System.out.println(appleBox2.getWeight());
        System.out.println(appleBox1.compare(appleBox2));
        appleBox1.moveTo(appleBox2);
        System.out.println(appleBox1.getWeight());
        System.out.println(appleBox2.getWeight());
        System.out.println(appleBox1.compare(appleBox2));
        orangeBox.add(orange);
        System.out.println(orangeBox.getWeight());
        System.out.println(appleBox1.compare(orangeBox));
    }
}



package ru.gb.java1.lesson2;

import java.util.Arrays;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("Task 1:");
        task1();

        System.out.println();
        System.out.println("Task 2:");
        task2();

        System.out.println();
        System.out.println("Task 3:");
        task3();

        System.out.println();
        System.out.println("Task 4:");
        task4();

        System.out.println();
        System.out.println("Task 5:");
        task5();

        System.out.println();
        System.out.println("Task 6:");
        System.out.println(task6(new int[]{2, 2, 2, 1, 2, 2, 10, 1}) ? "true" : "false");

        System.out.println();
        System.out.println("Task 7:");
        System.out.println(Arrays.toString(task7(new int[]{1, 2, 2, 1, 2, 2, 10, 1}, -2)));
    }

    /*
        1. Задать целочисленный массив, состоящий из элементов 0 и 1.
        Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
        С помощью цикла и условия заменить 0 на 1, 1 на 0;
    */
    private static void task1() {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        System.out.println("Source array:");
        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1 - arr[i];
        }

        System.out.println("Result:");
        System.out.println(Arrays.toString(arr));
    }

    /*
        2. Задать пустой целочисленный массив размером 8.
        С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
    */
    private static void task2() {
        int[] arr = new int[8];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 3 * i;
        }

        System.out.println("Result:");
        System.out.println(Arrays.toString(arr));
    }

    /*
        3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
        пройти по нему циклом, и числа меньшие 6 умножить на 2;
    */
    private static void task3() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        System.out.println("Source array:");
        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }

        System.out.println("Result:");
        System.out.println(Arrays.toString(arr));
    }

    /*
        4. Создать квадратный двумерный целочисленный массив
        (количество строк и столбцов одинаковое),
        и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
    */
    private static void task4() {
        int[][] arr = new int[8][8];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i == j) {
                    arr[i][j] = 1;
                }
            }
        }

        System.out.println("Result:");
        for (int[] line : arr) {
            System.out.println(Arrays.toString(line));
        }
    }

    /*
        5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
    */
    private static void task5() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        System.out.println("Source array:");
        System.out.println(Arrays.toString(arr));

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int el : arr) {
            if (el < min) {
                min = el;
            }
            if (el > max) {
                max = el;
            }
        }

        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
    }

    /*
        6. ** Написать метод, в который передается не пустой одномерный целочисленный массив,
        метод должен вернуть true, если в массиве есть место,
        в котором сумма левой и правой части массива равны.

        Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
        граница показана символами ||, эти символы в массив не входят.
    */
    private static boolean task6(int[] arr) {
        int sum = 0;
        int partSum = 0;

        for (int el : arr) {
            sum += el;
        }

        for (int el : arr) {
            partSum += el;
            if (partSum * 2 == sum) {
                return true;
            }
        }

        return false;
    }

    /*
        7. **** Написать метод, которому на вход подается одномерный массив и число n
        (может быть положительным, или отрицательным),
        при этом метод должен сместить все элементымассива на n позиций.
        Для усложнения задачи нельзя пользоваться вспомогательными массивами.
    */
    private static int[] task7_singleShift(int[] arr) {
        int lastElement = arr[arr.length - 1];

        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }

        arr[0] = lastElement;

        return arr;
    }

    private static int[] task7(int[] arr, int n) {
        if (n < 0) {
            n += arr.length;
        }

        for (int i = 0; i < n; i++) {
            arr = task7_singleShift(arr);
        }

        return arr;
    }

}



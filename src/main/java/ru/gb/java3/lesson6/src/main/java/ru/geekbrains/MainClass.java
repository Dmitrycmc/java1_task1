package ru.geekbrains;

import java.util.Arrays;

public class MainClass {
    public static void main(String[] args) {
    }

    /*
        2. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
        Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
        идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку,
        иначе в методе необходимо выбросить RuntimeException.
        Написать набор тестов для этого метода (по 3-4 варианта входных данных).
        Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
    */
    static int[] task2(int[] arr) {
        if (arr == null) {
            throw new RuntimeException("Passed null");
        }

        int i = arr.length - 1;
        while (i > 0 && arr[i] != 4) {
            i--;
        }

        if (arr[i] != 4) {
            throw new RuntimeException("Array not contains '4': " + Arrays.toString(arr));
        }
        int[] answer = new int[arr.length - i - 1];
        for (int j = 0; j < arr.length - i - 1; j++) {
            answer[j] = arr[i + j + 1];
        }

        return answer;
    }

    /*
        3. Написать метод, который проверяет состав массива из чисел 1 и 4.
        Если в нем нет хоть одной четверки или единицы, то метод вернет false;
        Написать набор тестов для этого метода (по 3-4 варианта входных данных).
     */
    static boolean task3(int[] arr) {
        if (arr == null) {
            throw new RuntimeException("Passed null");
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1 || arr[i] == 4) {
                return true;
            }
        }
        return false;
    }
}



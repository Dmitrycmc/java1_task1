package ru.geekbrains.sort;

import static ru.geekbrains.utils.ArrayUtils.swap;

public class ShakerSort {

    public static <T extends Comparable<T>> T[] sort(T[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            boolean sorted = true;
            for (int i = left; i < right; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    swap(array, i, i + 1);
                    sorted = false;
                }
            }
            if (sorted) {
                return array;
            }
            right--;

            sorted = true;
            for (int i = right - 1; i >= left; i--) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    swap(array, i, i + 1);
                    sorted = false;
                }
            }
            if (sorted) {
                return array;
            }
            left++;
        }
        return array;
    }
}

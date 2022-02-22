package ru.geekbrains.sort;

import java.util.HashMap;

public class CountingSort {

    public static <T> T[] sort(T[] array) {
        int max = array[0].hashCode();
        HashMap<Integer, T> hashMap = new HashMap<>();

        for (T k : array) {
            hashMap.put(k.hashCode(), k);
            if (k.hashCode() > max) {
                max = k.hashCode();
            }
        }

        int[] count = new int[max + 1];

        for (T key: array) {
            count[key.hashCode()] = count[key.hashCode()] + 1;
        }
        
        int i = 0;

        for (int j = 0; j < count.length; j++) {
            for (int k = 0; k < count[j]; k++) {
                array[i++] = hashMap.get(j);
            }
        }

        return array;
    }
}

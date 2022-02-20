package sort;

import static utils.ArrayUtils.swap;

public class BubbleSort {

    public static <T extends Comparable<T>> T[] sort(T[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            boolean sorted = true;
            for (int j = 0; j < i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
        }

        return array;
    }
}

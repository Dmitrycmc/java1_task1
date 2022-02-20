package sort;

import static utils.ArrayUtils.swap;

public class SelectionSort {

    public static <T extends Comparable<T>> T[] sort(T[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int minIndex = left, maxIndex = left;

            for (int i = left; i <= right; i++) {
                if (array[i].compareTo(array[minIndex]) < 0) {
                    minIndex = i;
                } else if (array[i].compareTo(array[maxIndex]) > 0) {
                    maxIndex = i;
                }
            }

            if (maxIndex == left && minIndex == right) {
                swap(array, left, right);
            } else if (maxIndex == left) {
                swap(array, right, maxIndex);
                swap(array, left, minIndex);
            } else {
                swap(array, left, minIndex);
                swap(array, right, maxIndex);
            }

            left++;
            right--;
        }
        return array;
    }
}

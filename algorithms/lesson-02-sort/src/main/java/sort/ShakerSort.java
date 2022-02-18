package sort;

import static utils.ArrayUtils.swap;

public class ShakerSort {

    public static int[] sort(int[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            boolean sorted = true;
            for (int i = left; i < right; i++) {
                if (array[i] > array[i + 1]) {
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
                if (array[i] > array[i + 1]) {
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

package sort;

import static utils.ArrayUtils.swap;

public class SelectionSort {

    public static int[] sort(int[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int minIndex = left, maxIndex = left;

            for (int i = left; i <= right; i++) {
                if (array[i] < array[minIndex]) {
                    minIndex = i;
                } else if (array[i] > array[maxIndex]) {
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

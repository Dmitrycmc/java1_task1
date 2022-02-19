package utils;

public class ArrayUtils {
    public static int[] generateArray(int n, int max) {
        int[] arr = new int[n];;

        for (int i = 0; i < n; i++) {
            arr[i] = MathUtils.randomInt(max);
        }
        return arr;
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

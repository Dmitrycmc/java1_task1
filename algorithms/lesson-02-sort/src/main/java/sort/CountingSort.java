package sort;

public class CountingSort {

    public static int[] sort(int[] array) {
        int max = array[0];
        for (int k : array) {
            if (k > max) {
                max = k;
            }
        }

        int[] count = new int[max + 1];

        for (int key: array) {
            count[key] = count[key] + 1;
        }
        
        int i = 0;

        for (int j = 0; j < count.length; j++) {
            for (int k = 0; k < count[j]; k++) {
                array[i++] = j;
            }
        }

        return array;
    }
}

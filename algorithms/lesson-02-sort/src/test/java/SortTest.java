import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sort.BubbleSort;
import sort.CountingSort;
import sort.LsdSort;
import sort.SelectionSort;
import sort.ShakerSort;

import java.util.Arrays;

import static utils.ArrayUtils.generateArray;
import static utils.ArrayUtils.isSorted;

public class SortTest {
    private static int[] array;

    @BeforeAll
    static void prepareArray() {
        array = generateArray(100000, 10000000);
    }

    @Test
    void bubbleSort() {
        Assertions.assertTrue(isSorted(BubbleSort.sort(Arrays.copyOf(array, array.length))));
    }

    @Test
    void shakerSort() {
        Assertions.assertTrue(isSorted(ShakerSort.sort(Arrays.copyOf(array, array.length))));
    }

    @Test
    void countingSort() {
        Assertions.assertTrue(isSorted(CountingSort.sort(Arrays.copyOf(array, array.length))));
    }

    @Test
    void lsdSort() {
        Assertions.assertTrue(isSorted(LsdSort.sort(Arrays.copyOf(array, array.length))));
    }

    @Test
    void selectionSort() {
        Assertions.assertTrue(isSorted(SelectionSort.sort(Arrays.copyOf(array, array.length))));
    }
}

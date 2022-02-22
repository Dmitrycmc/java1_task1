import ru.geekbrains.entity.Notebook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.geekbrains.sort.BubbleSort;
import ru.geekbrains.sort.CountingSort;
import ru.geekbrains.sort.LsdSort;
import ru.geekbrains.sort.SelectionSort;
import ru.geekbrains.sort.ShakerSort;
import ru.geekbrains.utils.TimeUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortTest {
    static Notebook[] array;
    static Notebook[] sortedArray;

    static final int N = 10000;

    static Notebook[] getArrayCopy() {
        return Arrays.copyOf(array, array.length);
    }

    @BeforeAll
    static void prepareArray() {
        array = Notebook.generateRandomArray(N);
        List<Notebook> list = Arrays.asList(getArrayCopy());
        Collections.sort(list);
        sortedArray = list.toArray(new Notebook[0]);
    }

    static boolean isSorted(Notebook[] array) {
        if (sortedArray.length != array.length) {
            return false;
        }

        for (int i = 0; i < array.length; i++) {
            if (!array[i].equals(sortedArray[i])) {
                return false;
            }
        }

        return true;
    }

    @Test
    void bubbleSort() {
        long t0 = System.currentTimeMillis();
        Notebook[] sortedArray = BubbleSort.sort(getArrayCopy());
        long t1 = System.currentTimeMillis();

        long time = t1 - t0;

        System.out.println("BubbleSort: " + TimeUtils.format(time));

        Assertions.assertTrue(isSorted(sortedArray));
    }

    @Test
    void shakerSort() {
        long t0 = System.currentTimeMillis();
        Notebook[] sortedArray = ShakerSort.sort(getArrayCopy());
        long t1 = System.currentTimeMillis();

        long time = t1 - t0;

        System.out.println("ShakerSort: " + TimeUtils.format(time));

        Assertions.assertTrue(isSorted(sortedArray));
    }

    @Test
    void selectionSort() {
        long t0 = System.currentTimeMillis();
        Notebook[] sortedArray = SelectionSort.sort(getArrayCopy());
        long t1 = System.currentTimeMillis();

        long time = t1 - t0;

        System.out.println("SelectionSort: " + TimeUtils.format(time));

        Assertions.assertTrue(isSorted(sortedArray));
    }

    @Test
    void countingSort() {
        long t0 = System.currentTimeMillis();
        Notebook[] sortedArray = CountingSort.sort(getArrayCopy());
        long t1 = System.currentTimeMillis();

        long time = t1 - t0;

        System.out.println("CountingSort: " + TimeUtils.format(time));

        Assertions.assertTrue(isSorted(sortedArray));
    }

    @Test
    void lsdSort() {
        long t0 = System.currentTimeMillis();
        Notebook[] sortedArray = LsdSort.sort(getArrayCopy());
        long t1 = System.currentTimeMillis();

        long time = t1 - t0;

        System.out.println("LsdSort: " + TimeUtils.format(time));

        Assertions.assertTrue(isSorted(sortedArray));
    }
}

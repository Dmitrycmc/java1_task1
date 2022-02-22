import ru.geekbrains.entity.Notebook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import gu.geekbrains.search.BinarySearch;
import ru.geekbrains.utils.MathUtils;
import gu.geekbrains.search.ExponentialSearch;
import gu.geekbrains.search.InterpolationSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SearchTest {
    static List<Notebook> sortedList;
    static Notebook element;

    static final int N = 1000000;

    @BeforeAll
    static void prepareArray() {
        Notebook[] array = Notebook.generateRandomArray(N);
        List<Notebook> list = Arrays.asList(array);
        Collections.sort(list);
        sortedList = list;
        element = sortedList.get(MathUtils.randomInt(N - 1));
    }

    @Test
    void binarySearchInEmptyList() {
        Assertions.assertEquals(BinarySearch.findIndex(new ArrayList<>(), Notebook.generateRandom()), -1);
    }

    @Test
    void binarySearch() {
        int index = BinarySearch.findIndex(sortedList, element);
        Assertions.assertEquals(sortedList.get(index), element);
    }

    @Test
    void exponentialSearch() {
        int index = ExponentialSearch.findIndex(sortedList::get, N - 1, element);
        Assertions.assertEquals(sortedList.get(index), element);
    }

    @Test
    void interpolationSearch() {
        int index = InterpolationSearch.findIndex(sortedList::get, N - 1, element);
        Assertions.assertEquals(sortedList.get(index), element);
    }
}

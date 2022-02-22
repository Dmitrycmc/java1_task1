package search;

import java.util.function.Function;

public class ExponentialSearch {
    public static <T extends Comparable<T>> int findIndex(Function<Integer, T> calculate, int maxIndex, T element) {
        if (maxIndex < 5) {
            return BinarySearch.findIndex(calculate, maxIndex, element);
        }

        int start = 0;
        int end = 2;

        while (true) {
            if (end > maxIndex) {
                return BinarySearch.findIndex(calculate, start, maxIndex, element);
            }
            if (element.compareTo(calculate.apply(end)) < 0) {
                return BinarySearch.findIndex(calculate, start, end - 1, element);
            }
            if (element.compareTo(calculate.apply(end)) == 0) {
                return end;
            }

            start = end;
            end *= 2;
        }
    }
}

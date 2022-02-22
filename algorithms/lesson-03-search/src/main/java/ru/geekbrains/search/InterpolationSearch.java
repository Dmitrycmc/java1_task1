package ru.geekbrains.search;

import java.util.List;
import java.util.function.Function;

public class InterpolationSearch {
    public static <T extends Comparable<T>> int findIndex(List<T> list, T element) {
        return findIndex(list::get, list.size() - 1, element);
    }

    public static <T extends Comparable<T>> int findIndex(Function<Integer, T> calculate, int end, T element) {
        return findIndex(calculate, 0, end, element);
    }

    public static <T extends Comparable<T>> int findIndex(Function<Integer, T> calculate, int start, int end, T element) {
        while (start <= end) {
            int startHashCode = calculate.apply(start).hashCode();
            int endHashCode = calculate.apply(end).hashCode();
            double k = (double) (element.hashCode() - startHashCode) / (endHashCode - startHashCode);
            int i = (int) ((end - start) * k + start);

            if (element.compareTo(calculate.apply(i)) == 0) {
                return i;
            }

            if (element.compareTo(calculate.apply(i)) < 0) {
                end = i - 1;
            } else {
                start = i + 1;
            }
        }

        return -1;
    }
}

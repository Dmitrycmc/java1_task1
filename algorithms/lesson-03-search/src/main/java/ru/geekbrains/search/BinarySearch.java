package ru.geekbrains.search;

import java.util.List;
import java.util.function.Function;

public class BinarySearch {
    public static <T extends Comparable<T>> int findIndex(List<T> list, T element) {
        return findIndex(list::get, list.size() - 1, element);
    }

    public static <T extends Comparable<T>> int findIndex(Function<Integer, T> calculate, int end, T element) {
        return findIndex(calculate, 0, end, element);
    }

    public static <T extends Comparable<T>> int findIndex(Function<Integer, T> calculate, int start, int end, T element) {
        while (start <= end) {
            int i = (start + end) / 2;

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

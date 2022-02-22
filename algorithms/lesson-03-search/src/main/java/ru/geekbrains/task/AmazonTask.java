package ru.geekbrains.task;

public class AmazonTask {
    public static int resolve(int[] input) {
        int start = 0;
        int end = input.length - 1;

        while (start <= end) {
            int i = (start + end) / 2;

            if (input[i] > i + 1) {
                end = i - 1;
            } else {
                start = i + 1;
            }
        }

        return start + 1;
    }
}
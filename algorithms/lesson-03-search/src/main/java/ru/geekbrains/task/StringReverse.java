package ru.geekbrains.task;

import ru.geekbrains.structure.Stack;

public class StringReverse {
    public static String resolve(String input) {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            stack.push(input.substring(i, i + 1));
        }

        String element;
        StringBuilder sb = new StringBuilder();

        while (true) {
            element = stack.pop();
            if (element == null) {
                return sb.toString();
            }
            sb.append(element);
        }
    }
}

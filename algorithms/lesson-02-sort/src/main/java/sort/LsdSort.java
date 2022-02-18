package sort;

import java.util.ArrayList;
import java.util.List;

public class LsdSort {

    private static int getDigitWithEnd(int number, int digitNumber) {
        String str = Integer.toString(number);

        if (digitNumber > str.length()) {
            return 0;
        }

        int characterIndex = str.length() - digitNumber;

        return Integer.parseInt(str.substring(characterIndex, characterIndex + 1));
    }

    private static int getDigitsNumber(int number) {
        String str = Integer.toString(number);

        return str.length();
    }

    public static int[] sort(int[] array) {
        List<List<Integer>> groups = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            groups.add(new ArrayList<>());
        }

        int maxDigitsNumber = 0;

        for (int i = 0; i < array.length; i++) {
            int digitsNumber = getDigitsNumber(array[i]);
            if (digitsNumber > maxDigitsNumber) {
                maxDigitsNumber = digitsNumber;
            }
        }

        for (int digitNumber = 1; digitNumber <= maxDigitsNumber; digitNumber++) {
            for (int i = 0; i < 10; i++) {
                groups.get(i).clear();
            }

            for (int value: array) {
                groups.get(getDigitWithEnd(value, digitNumber)).add(value);
            }

            int k = 0;
            for (int i = 0; i < 10; i++) {
                List<Integer> group = groups.get(i);
                for (Integer value : group) {
                    array[k++] = value;
                }
            }
        }

        return array;
    }
}
